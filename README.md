
Selenium Test
==============

Selenium Test provides a set of APIs that accept and Excel file and a web address and then execute the commands in the excel spreadsheet. This can be used to quickly enter a quote into a web site.


Development
--------------

    Checkout the sources
    Build and run using mvn clean install
    Browse the API using the built in HAL browser http://localhost:9000
        HAL browser is not available in the Production deployment
        Spring dev-tools are available in development mode only

Production deployment
--------------

    Package as a WAR file using mvn clean package -P war-file

Production deployment as Docker Container
--------------

    To package as a Docker container use mvn clean package docker:build

Who do I talk to?
--------------

    Repo owner or admin
    Other community or team contact


This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact