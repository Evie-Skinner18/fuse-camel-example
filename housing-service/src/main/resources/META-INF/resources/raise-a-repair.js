
$(document).ready(function() {
    const findAResidentSection = $("#find-a-resident-section");
    const errorMessage = $("#error-message");
    const raiseARepairButton = $("#raise-a-repair-button");

    findAResidentSection.hide();
    errorMessage.hide();
    raiseARepairButton.hide();

                            // abstract into separate function
    IdealPostcodes.AddressFinder.setup({
        apiKey: "ak_l2sxknjt5J2bI04h1jkzhYDrw6e4l",
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

    $("#postcode").change((event) => {
                                // abstract into separate function
        const firstLineOfAddressValue = $("#first_line").val();

        if (firstLineOfAddressValue) {
            findAResidentSection.show();
        }
    });
    
    $("#find-a-resident-button").click(async function(e) {
        e.preventDefault();
        callHousingService();
    });

    async function callHousingService() {
                                // abstract into separate function
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

        const jsonResidentInfo = JSON.stringify(residentInfoObject);

        $.getJSON(`/camel/housing/${jsonResidentInfo}`, function (res) {
            if (res) {
                // abstract into separate function
                const housingMessage = res.housingMessage;
                const residentName = housingMessage.residentInfo.firstName;
                const isValidResident = housingMessage.residentInfo.isValidResident;
                const nameNearlyMatchingFirstName = housingMessage.residentInfo.nameNearlyMatchingFirstName;
                const northgatePropertyNumber = housingMessage.northgatePropertyNumber;
                const partyReference = housingMessage.partyReference;

                const findAResidentMessage = isValidResident? 
                    `<div id='housing-result'>
                        <p>We have found a resident matching those details.</p>
                        <p>Name: ${residentName}</p>
                        <p>Northgate property reference: ${northgatePropertyNumber}</p>
                        <p>Party reference: ${partyReference}</p>
                    </div>`
                    :
                    "<div id='housing-result'></div>";
                
                $("#housing-result").replaceWith(findAResidentMessage);

                showNextStepByResidentValidity(isValidResident, nameNearlyMatchingFirstName);
            }
        });
    }

    function showNextStepByResidentValidity(isValidResident, nameNearlyMatchingFirstName) {
        if (nameNearlyMatchingFirstName) {
            showErrorMessage(nameNearlyMatchingFirstName);
        } else {
            isValidResident? showRaiseARepairButton() : showErrorMessage();
        }
    }

    // not very dry
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