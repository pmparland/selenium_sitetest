Selenium Test
==============

Selenium Test provides a set of APIs that accept and Excel file and a web address and then execute the commands in the excel spreadsheet. This can be used to quickly enter a quote into a web site.


Development
--------------

    Checkout the sources
    Build and run using 
        mvn clean install


Production deployment
--------------

    Create a fat jar and copy to the target machine
        mvn clean compile assembly:single


Production execution
--------------
    java -jar target/sitetest-jar-with-dependencies.jar <excel file> <http address of site>

    Currently defaults to use Firefox!
    

Who do I talk to?
--------------

    Repo owner or admin
    Other community or team contact