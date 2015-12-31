package TestCases;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */

import org.apache.log4j.Logger;


/**
 * Defines the methods used to represent a test case
 */
public abstract class TestCase {

    private String name;
    private String browser;
    private Boolean result = true;


    static final Logger logger = Logger.getLogger(TestCase.class);

    /**
     * get the name of the test
     * @return  name
     *      the name of the test.
     */
    public String getName () {
        return name;
    }

    /**
     * Set the test name
     * @param name
     *      the test name
     */
    public void setName (String n) {
        name = n;
    }

    /**
     * get the browser of the test
     * @return  browser
     *      the name of the test.
     */
    public String getBrowser (){
        return browser;
    }

    /**
     * Set the browser name
     * @param browser
     *      the browser type
     */
    public void setBrowser (String b){
        browser =b;
    }
    /**
     * get the result of the test
     * @return  true or false for pass or fail
     *      the result of the test.
     */
    public Boolean getResult (){
        return result;
    }

    /**
     * Set the result pass or fail
     * @param r
     *      the Boolean result
     */
    public void setResult (Boolean r){
        result = r;
    }

    //Need something for device
}
