package Questions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */

/**
 * Defines the set of data to answer a question
 */
public class Question {

    static final Logger logger = Logger.getLogger(Question.class);

    private String locatorType;  // How to locate the element, e.g. name
    private String locatorValue; //Data to use with the locator type
    private String inputType;    // The type of input for this question, e.g. text
    private String inputValue;   // The value that goesd with this input type

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
        return String.format("%s - %s - %s - %s", locatorType, locatorValue, inputType, inputValue);
    }

    /**
     * Set the locator type such as name or linktext
     * @param type
     *      the type of the locator
     */
    public void setLocatorType(String type){
        locatorType = type;
    }

    /**
     * Set the locator value such as text
     * @param val
     *      the value for the locator
     */
    public void setLocatorValue(String val){
        locatorValue = val;
    }

    /**
     * Set the input type such as select
     * @param type
     *      the type of the input
     */
    public void setInputType(String type){
        inputType = type;
    }

    /**
     * Set the value of the input type such as a select value
     * @param val
     *      the value
     */
    public void setInputValue (String val){
        inputValue = val;
    }

    /**
     * Execute a question by finding its location on the page and applying the input
     * @param page
     *      the web page
     * @return the question
     */
    public Boolean executeQuestion(WebDriver page){
        logger.info(">>> executeQuestion " + locatorValue );

        //Ignore empty rows
        if (locatorType == null || locatorType.isEmpty()) return Boolean.TRUE;

        try {
            By element = getLocator();
            applyInput(page, element);
            return Boolean.TRUE;
        } catch (Exception e) {logger.error(e.getMessage());}
        return Boolean.FALSE;
    }

    /**
     * Get the location of the question
     * @return element
     *      the element on the page
     */
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
        return element;
    }

    /**
     * Insert or the select the input
     * @param page
     *      the web page
     * @param element
     *      the element representing the location of the question
     *           * @return the question
     */
    private void applyInput (WebDriver page, By element) throws Exception {
        logger.info(">>> applyInput: " + inputValue + " to " + locatorValue);

        // Get an instance of input type
        if(inputType.equalsIgnoreCase("text"))
            page.findElement(element).sendKeys(inputValue);
        else if (inputType.equalsIgnoreCase("button")){
            page.findElement(element).click();
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
        else if (inputType.equalsIgnoreCase("hidden_select")) {
            logger.info("hidden_select");
            Actions action = new Actions(page);
            WebElement we = page.findElement(element);
            action.moveToElement(we).perform();
            /*
            WebElement el = page.findElement(element*
            JavascriptExecutor js = (JavascriptExecutor)page;
            String arguments = "document.getElementsByName(" + "'" + locatorValue + "'" + ")[0].style.visibility='visible';";
            js.executeScript(arguments, el);
            Select dropDown = new Select(el);
            WebDriverWait wait = new WebDriverWait(page,300);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            dropDown.selectByIndex(Integer.parseInt(inputValue));*/
        }
        else if (inputType.equalsIgnoreCase("number"))
            page.findElement(element).sendKeys(inputValue);
        else
            throw new Exception("Input type '" + inputType + "' not defined!!");
    }


}
