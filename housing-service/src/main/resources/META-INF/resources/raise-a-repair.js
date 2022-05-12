$(document).ready(function() {
    const findAResidentSection = $("#find-a-resident-section");
    const errorMessage = $("#error-message");
    const raiseARepairButton = $("#raise-a-repair-button");

    findAResidentSection.hide();
    errorMessage.hide();
    raiseARepairButton.hide();
    // instruct how to get an api key in the readme
    // "ak_l2sxknjt5J2bI04h1jkzhYDrw6e4l"
    
    $("#api-key").change((event) => {
        const idealPostcodesApiKey = $("#api-key").val();
        setupIdealPostcodes(idealPostcodesApiKey);  
    });  

    $("#postcode").change((event) => {
        const firstLineOfAddressValue = $("#first_line").val();

        if (firstLineOfAddressValue) {
            findAResidentSection.show();
        }
    });
    
    $("#find-a-resident-button").click(async function(e) {
        e.preventDefault();
        const residentInfo = getResidentInfo();
        callHousingService(residentInfo);
    });

    function setupIdealPostcodes(apiKey) {
        IdealPostcodes.AddressFinder.setup({
            apiKey: apiKey,
            inputField: "#postcode-input",
            outputFields: {
                line_1: "#first_line",
                line_2: "#second_line",
                line_3: "#third_line",
                post_town: "#post_town",
                postcode: "#postcode",
                uprn: "#uprn"
            }
        });
    }

    function getResidentInfo() {
        const firstNameInput = $("#first-name");
        const surnameInput = $("#surname");
        const uprnInput = $("#uprn");

        const firstNameValue = firstNameInput.val();
        const surnameValue = surnameInput.val();
        const uprnValue = uprnInput.val();

        const residentInfoObject = {
            firstName: `${firstNameValue}`,
            surname: `${surnameValue}`,
            uprn: `${uprnValue}`
        }

        return residentInfoObject;
    }

    async function callHousingService(residentInfoObject) {
        const jsonResidentInfo = JSON.stringify(residentInfoObject);

        $.getJSON(`/camel/housing/${jsonResidentInfo}`, function (res) {
            if (res) {
                const housingMessage = res.housingMessage;
                const isValidResident = housingMessage.residentInfo.isValidResident;
                const nameNearlyMatchingFirstName = housingMessage.residentInfo.nameNearlyMatchingFirstName;

                const findAResidentMessage = getFindAResidentMessageByResidentValidity(isValidResident, housingMessage);
                
                $("#housing-result").replaceWith(findAResidentMessage);

                showNextStepByResidentValidity(isValidResident, nameNearlyMatchingFirstName);
            }
        });
    }

    function getFindAResidentMessageByResidentValidity(isValidResident, housingMessage) {
        let findAResidentMessage = "<div id='housing-result'></div>";

        if (isValidResident) {
            const residentName = housingMessage.residentInfo.firstName;
            const northgatePropertyNumber = housingMessage.northgatePropertyNumber;
            const partyReference = housingMessage.partyReference;

            findAResidentMessage = 
            `<div id='housing-result'>
                <p>We have found a resident matching those details.</p>
                <p>Name: ${residentName}</p>
                <p>Northgate property reference: ${northgatePropertyNumber}</p>
                <p>Party reference: ${partyReference}</p>
            </div>`
        }

        return findAResidentMessage;
    }

    function showNextStepByResidentValidity(isValidResident, nameNearlyMatchingFirstName) {
        if (nameNearlyMatchingFirstName) {
            showErrorMessage(nameNearlyMatchingFirstName);
        } else {
            isValidResident? showRaiseARepairButton() : showErrorMessage();
        }
    }

    function showErrorMessage(nameNearlyMatchingFirstName) {
        raiseARepairButton.hide();

        const errorMessage = $("#error-message");

        const residentFirstNameValue = $("#first-name").val();
        const residentSurnameValue = $("#surname").val();
        const addressFirstLineValue = $("#first_line").val();

        if (nameNearlyMatchingFirstName) {
            errorMessage.replaceWith(
                `<div class='alert alert-warning' id='error-message'>
                    We couldn't find a resident named ${residentFirstNameValue} ${residentSurnameValue} at ${addressFirstLineValue}. 
                    Did you mean ${nameNearlyMatchingFirstName}?
                </div>`
                );
        } else {
            errorMessage.replaceWith(
                `<div class='alert alert-danger' id='error-message'>
                    We couldn't find a resident named ${residentFirstNameValue} ${residentSurnameValue} at ${addressFirstLineValue}.
                    Please phone us to raise your repair:  <strong>020 7974 4444</strong>
                </div>`
                );
        }

        errorMessage.show();
    }

    function showRaiseARepairButton() {
        const errorMessage = $("#error-message");
        errorMessage.hide();
        
        const raiseARepairButton = $("#raise-a-repair-button");

        const residentFirstNameValue = $("#first-name").val();
        const addressFirstLineValue = $("#first_line").val();

        const residentNameInsideRaiseARepairButton = $("#resident-name");
        const addressInsideRaiseARepairButton = $("#address-first-line");

        residentNameInsideRaiseARepairButton.replaceWith(`<span id="resident-name">${residentFirstNameValue}</span>`);
        addressInsideRaiseARepairButton.replaceWith(`<span id="address-first-line">${addressFirstLineValue}</span>`);
        
        raiseARepairButton.show();
    }
});