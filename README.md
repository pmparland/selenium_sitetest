Selenium Test
==============

Selenium Test provides a set of APIs that accept and Excel file and a web address and then execute the commands in the excel spreadsheet. This can be used to quickly enter a quote into a web site.


Development
--------------

    Checkout the sources
    Build and run using mvn clean install


Production deployment
--------------

    Package as a WAR file using mvn clean package -P war-file

Production deployment as Docker Container
--------------

    To package as a Docker container use mvn clean package docker:build

    To use Chrome ensure that you Install Chrome Web Driver - https://code.google.com/p/selenium/wiki/ChromeDriver
    To use IE ensure that you install the IE Web Driver - https://code.google.com/p/selenium/wiki/InternetExplorerDriver
    By default Firefox is supported. This Firefox addon is extremely useful https://addons.mozilla.org/en-GB/firefox/addon/element-locator-for-webdriv/ 

    Test can only be executed from a Seopa ip!

Who do I talk to?
--------------

    Repo owner or admin
    Other community or team contact