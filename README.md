# Camden Borough Council - Raise a Repair Proof of Concept

## Ubiquitous Language
- Resident = a person living in council-provided accommodation in Camden. Residents are our users.
- Raise a repair = request that someone from the council come and fix a problem in a council property. E.g one would raise a repair for mould developing on one's wall.
- UPRN = Unique Property Reference Number. Every property in the UK has a unique identifier, no matter if it is owned by the council or not. We use this to uniquely identify the property that our resident wants to have repaired.
- Party Reference = a unique identifier for the resident raising the repair.
- Camden Resident Index/MDM = a database containing Camden residents and personally identifiable information about them.

## Purpose and Vision
- Who are the users? The main user/person we care about is someone living in a council house in Camden who has something wrong with their property.
- They use the Raise a Repair service to ask the council to send someone to fix the problem.
- Usage of the current online Raise a Repair service amongst Camden residents is 26%; the remaining 74% phone the council's call centre.
- Made Tech realised that requiring the resident to log in with a Camden account was proving to be a significant barrier to the resident efficiently requesting a repair online.
- Made Tech undertook a tech alpha investigation to see if people living in council accommodation in Camden would be able to request a repair on their property without having to log in.
- This proof of concept is the result of that investigation! (Spoiler alert: residents can do so without having to log in)

## Tools and Technologies Used
- Java 18
- Maven
- JBoss/RedHat Fuse
- Apache Camel
- Spring Boot
- jQuery
- HTML
- Bootstrap
- Docker

For more info on the key technologies used, please read [this document](https://lbcamden.sharepoint.com/:w:/r/sites/MSTHousingRepairTransformation/_layouts/15/doc.aspx?sourcedoc=%7B38bec72f-e9d3-4227-a6a2-964bbc3886c2%7D&action=edit&cid=417b76eb-05ea-4d9f-b172-e3bf6b0b1113) on Camden's Sharepoint site.

## Architecture Overview
- We built this from a RedHat Fuse booster (quickstart) project
- It has a front end composed of a single HTML page, which uses a form to capture user input
- For the purposes of this POC, the 'Ideal Postcodes API key' field allows us to use the Ideal Postcodes API (an external API that does an address lookup), without having to include an API key in the codebase. You can enter an API key that's unique to you (see instructions on how to generate the API key below).
- We built it that way because it's not possible to use jQuery to access environment variables in an .env file. We could have done this with a Vue or React app, but that was beyond the scope of this investigation.
- The 'Postcode' field takes a postcode and uses the [Ideal Postcodes postcode lookup API](https://docs.ideal-postcodes.co.uk/docs/postcode-lookup) to return a list of addresses found at that postcode. 
- Ideal Postcodes returns a UPRN in each address object returned. When the user selects their address from the list, the form populates a UPRN field with that address's UPRN.
- The front end uses jQuery to invoke the get() endpoint _/housing/{residentInfo}_ in the housingService, submitting a JSON object of the details captured in the form
- The housingService, defined in our Fuse ESB layer as a Camel route, takes that request and forwards it on to the repairService.
- The repairService also has a get() endpoint _/repairs/residents_ defined as a Camel route: this takes the residentInfo passed to it from housingService and builds up a Resident object with some hard coded values for two of the object's properties (RepairServiceImpl.java)
- The housingService takes the new Resident object and uses that to build up a HousingResponse (HousingServiceImpl.java)
- Within the HousingMessage object, the housingService takes the string properties of the Resident object and converts the residentInfo JSON string into a ResidentInfo Java object (bean)
- The housingService performs some fake validation on the resident name. We did this so we could mock the behaviour of the Camden Resident Index (otherwise known as MDM).
- The housingService returns the fully hydrated HousingResponse back to the front end, where it is consumed by showing a 'raise a repair' button or one of two error messages.
- housingService and repairService are two separate RESTful Java APIs that can communicate with eachother via the RedHat Fuse middleware.

## How to Run the App

### Prerequesites
- Install [Java](https://www.java.com/en/download/)
- Install Maven: 
    - https://maven.apache.org/what-is-maven.html 
    - Via Homebrew https://formulae.brew.sh/formula/maven#default 
- Install [Docker Desktop](https://docs.docker.com/get-docker/)
- You will need to generate an API key in order to use the form and access the postcode lookup API.
    - Sign up for a free [Ideal Postcodes account](https://ideal-postcodes.co.uk/users/sign_up)
    - Once you have activated your account, navigate to your [Ideal Postcodes dashboard](https://ideal-postcodes.co.uk/tokens) to find your API key
    - Copy the API key (a long string of mainly letters and numbers) and keep it safe for later

### Installation Steps
- Clone this repo
- In your Terminal, navigate to the housing-service directory
`cd careless-thumb/housing-service`
- Restore all the project dependencies for the housing-service
`mvn clean package`
- Navigate to the repair-service directory
`cd ../repair-service`
- Restore all the project dependencies for the repair-service
`mvn clean package`

### How to Run via Docker
- Navigate back to the root directory
`cd ../`
- Create the Docker images from the root directory's Docker file
`docker-compose build`
- Run the two Docker containers
`docker-compose up`

### How to Run via Maven
- Navigate to the repair-service directory
`cd repair-service`
- Run the repair-service API
`mvn`
- Open up another Terminal tab by pressing command + T
- Navigate to the housing-service
`cd ../housing-service`
- Run the housing-service API
`mvn`


- In your browser, navigate to localhost:8080 to see the action!