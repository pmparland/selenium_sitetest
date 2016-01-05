package Questions;

<<<<<<< HEAD
import org.apache.log4j.Logger;
=======
import lombok.Data;
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
<<<<<<< HEAD
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
=======
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

<<<<<<< HEAD
import java.util.concurrent.TimeUnit;

=======
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
/**
 * Defines the set of data to answer a question.
 *
 * Created by patrick.mcparland on 23/12/2015.
 */
@Data
public class Question {

    private String locatorType;  // How to locate the element, e.g. name
    private String locatorValue; // Data to use with the locator type
    private String inputType;    // The type of input for this question, e.g. text
    private String inputValue;   // The value that goes with this input type
<<<<<<< HEAD
    private String expectedResultOperator;    // TThe operator to use to check the expected result
    private String expectedResultValue;   // The value that goes with this expected result

    /**
     * Creates a question
     * @return question
     *      an empty question.
     */
    public Question() {
    }

    /**
     * Get the string representation of a question
     * @return string
     *      the string
     */
    public String toString() {
        return String.format("%s - %s - %s - %s - %s - %s", locatorType, locatorValue, inputType, inputValue, expectedResultOperator, expectedResultValue);
    }

    /**
     * Set the locator type such as name or linktext
     * @param type
     *      the type of the locator
     */
    public void setLocatorType(String type){
        locatorType = type;
    }
=======
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a

    /**
     * Determines if the Question is executable based on whether or not the sufficient parameters
     * have been set.
     *
     * @return  <code>true</code> has been returned if this Question can be executed, otherwise <code>false</code>
     *          has been returned.
     */
    public boolean isExecutable() {
        return !(null == locatorType || locatorType.isEmpty());
    }

    /**
     * Set the expected result operator type
     * @param op
     *      the operator to use on the expected result such as contains
     */
    public void setExpectedResultOperator(String op){
        expectedResultOperator = op;
    }

    /**
     * Set the expected result value
     * @param val
     *      the value for the expected result
     */
    public void setExpectedResultValue(String val){
        expectedResultValue = val;
    }

    /**
     * Execute a question by finding its location on the page and applying the input
     *
     * @param   page The driver for the web page to test.
     */
<<<<<<< HEAD
    public Boolean executeQuestion(WebDriver page){
        logger.info(">>> executeQuestion " + locatorValue );

        //Ignore empty rows
        if (locatorType == null || locatorType.isEmpty()) return Boolean.TRUE;

        try {
            By element = getLocator();
            applyInput(page, element);
            if(expectedResultOperator != null && !expectedResultOperator.isEmpty())
                return processExpectedResultOperator(page);
            return Boolean.TRUE;
        } catch (Exception e) {logger.error(e.getMessage());}
        return Boolean.FALSE;
=======
    public void executeQuestion(WebDriver page) {
        // Ignore empty rows
        if (!isExecutable()) {return ;}

        // Answer the Question
        applyInput(page);
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
    }

    /**
     * Get the location of the question.
     *
     * @return  The location of the element representing this Question.
     */
<<<<<<< HEAD
    private By getLocator() throws Exception {
        logger.info(">>> getLocator");

        //Get an instance of By class based on type of locator
        By element;
        if (locatorType.equalsIgnoreCase("id"))
            element = By.id(locatorValue);
        else if (locatorType.equalsIgnoreCase("name"))
            element = By.name(locatorValue);
        else if ((locatorType.equalsIgnoreCase("classname")) || (locatorType.equalsIgnoreCase("class")))
            element = By.className(locatorValue);
        else if ((locatorType.equalsIgnoreCase("tagname")) || (locatorType.equalsIgnoreCase("tag")))
            element = By.className(locatorValue);
        else if ((locatorType.equalsIgnoreCase("linktext")) || (locatorType.equalsIgnoreCase("link")))
            element = By.linkText(locatorValue);
        else if (locatorType.equalsIgnoreCase("partiallinktext"))
            element = By.partialLinkText(locatorValue);
        else if ((locatorType.equalsIgnoreCase("cssselector")) || (locatorType.equalsIgnoreCase("css")))
            element = By.cssSelector(locatorValue);
        else if (locatorType.equalsIgnoreCase("xpath"))
            element = By.xpath(locatorValue);
        else
            throw new Exception("Locator type '" + locatorType + "' not defined!!");
=======
    private By getLocator() {

        // Get an instance of By class based on the type of locator
        By element = null;
        switch (locatorType.toLowerCase()) {
            case "id" :
                element = By.id(locatorValue);
                break;
            case "name" :
                element = By.name(locatorValue);
                break;
            case "classname" :
            case "class" :
            case "tagname" :
            case "tag" :
                element = By.className(locatorValue);
                break;
            case "linktext" :
            case "link":
                element = By.linkText(locatorValue);
                break;
            case "partiallinktext" :
                element = By.partialLinkText(locatorValue);
                break;
            case "cssselector" :
            case "css" :
                element = By.cssSelector(locatorValue);
                break;
            case "xpath" :
                element = By.xpath(locatorValue);
                break;
            default :
                throw new RuntimeException("Locator Type [" + locatorType + "] not defined");
        }
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
        return element;

    }

    /**
     * Insert or the select the input.
     *
     * @param   page The driver for the web page to test.
     */
<<<<<<< HEAD
    private void applyInput (WebDriver page, By element) throws Exception {
        logger.info(">>> applyInput: " + inputValue + " to " + locatorValue);

        //Wait for the presence of the element
        Wait<WebDriver> wait = new FluentWait<WebDriver>(page)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        // Get an instance of input type
        if(inputType.equalsIgnoreCase("text"))
            page.findElement(element).sendKeys(inputValue);
        else if (inputType.equalsIgnoreCase("button")){
            page.findElement(element).click();
            if (locatorType.equalsIgnoreCase("nextButton"))
                //Wait longer for a page change
                page.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            else
               Thread.sleep(500); //Wait after a button press
        }
        else if (inputType.equalsIgnoreCase("select_by_value")) {
            Select dropDown = new Select(page.findElement(element));
            dropDown.selectByValue(inputValue);
        }
        else if (inputType.equalsIgnoreCase("select_by_text")) {
            Select dropDown = new Select(page.findElement(element));
            dropDown.selectByVisibleText(inputValue);
        }
        else if (inputType.equalsIgnoreCase("select_by_index")) {
            Select dropDown = new Select(page.findElement(element));
            dropDown.selectByIndex(Integer.parseInt(inputValue));
        }
        else if (inputType.equalsIgnoreCase("number"))
            page.findElement(element).sendKeys(inputValue);
        else
            throw new Exception("Input type '" + inputType + "' not defined!!");
    }

    /**
     * Get the location of the question
     * @return element
     *      the element on the page
     */
    private Boolean processExpectedResultOperator(WebDriver page) throws Exception{
        logger.info(">>> getExpectedResultOperator " + expectedResultOperator);
        Boolean result;
        if (expectedResultOperator.equalsIgnoreCase("contains")){
            if(page.getPageSource().contains(expectedResultValue))
                result =  Boolean.TRUE;
            else
                result =  Boolean.FALSE;
        }
        else
            throw new Exception("Expected Result Operator '" + expectedResultOperator + "' not defined!!");
        if (!result)
            logger.info(">>> getExpectedResultOperator " + expectedResultOperator + " failed!");
        return result;
=======
    private void applyInput (WebDriver page) {

        // Get an instance of input type
        By element = getLocator();
        switch (inputType.toLowerCase()) {
            case "text" :
                page.findElement(element).sendKeys(inputValue);
                break;
            case "button" :
                page.findElement(element).click();
                // Wait after a button press
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            case "select" :
                Select dropDown = new Select(page.findElement(element));
                dropDown.selectByValue(inputValue);
                break;
            case "hidden_select" :
                Select hiddenDropDown = new Select(page.findElement(element));
                hiddenDropDown.selectByIndex(Integer.parseInt(inputValue));
                break;
            case "number" :
                page.findElement(element).sendKeys(inputValue);
            default :
                throw new RuntimeException("Input type [" + inputType + "] not defined");
        }

>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
    }

}
