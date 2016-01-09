package com.seopa.tests.automated.questions;

import lombok.Data;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Defines the set of data to answer a question.
 *
 * Created by patrick.mcparland on 23/12/2015.
 */
@Data
public class Question {

    private static org.apache.log4j.Logger log = Logger.getLogger(Question.class);

    private String qName;
    private String locatorType;  // How to locate the element, e.g. name
    private String locatorValue; // Data to use with the locator type
    private String inputType;    // The type of input for this question, e.g. text
    private String inputValue;   // The value that goes with this input type
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
     * Determines if the Question has an expected result
     *
     * @return  <code>true</code> if the question has a defined operator for expectred result
     */
    public boolean hasExpectedResult() {
        return !(null == expectedResultOperator || expectedResultOperator.isEmpty());
    }

    /**
     * Execute a question by finding its location on the page and applying the input
     *
     * @param   page The driver for the web page to test.
     * @return  <code>true</code> has been returned if this Question can be executed, otherwise <code>false</code>
     *          has been returned.
     */
    public boolean executeQuestion(WebDriver page)  {
        log.info("Executing question "+ qName);
        boolean result = true;

        // Ignore empty rows
        if (!isExecutable()) {result = true;}
        else {
            try {
                // Answer the Question
                applyInput(page);

                // Check expect result
                if (hasExpectedResult()) {
                    result = processExpectedResultOperator(page);
                }
            }
            catch (Exception e) {
                log.error("Failed at Question " + qName + "with error " +e.getMessage());
                System.exit(0);
            }
        }
        return result;
    }


    private By getLocator() throws Exception {

        // Get an instance of By class based on the type of locator
        By element;
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
        return element;
    }


    private boolean processExpectedResultOperator(WebDriver page) throws Exception {

        Boolean result;
        switch (expectedResultOperator.toLowerCase()) {
            case "contains" :
                result = page.getPageSource().contains(expectedResultValue);
                break;
            default :
                result = false;
                throw new Exception("Expected Result Operator '" + expectedResultOperator + "' not defined!!");
        }
        if (!result)
            log.error("Question expected result failed using operator " + expectedResultOperator + " with " + expectedResultValue);
        else
            log.info("Question expected result passed using operator " + expectedResultOperator + " with " + expectedResultValue);
        return result;
    }

    private void applyInput (WebDriver page) throws Exception {

        // Get an instance of input type
        By loc = getLocator();
        waitForElementToBeAvailable(page, loc);

        //Get the element
        WebElement element = page.findElement(loc);
        switch (inputType.toLowerCase()) {
            case "text" :
                element.sendKeys(inputValue);
                break;
            case "button" :
                scrollToElement(page, element);  //required if screen scrolls
                element.click();
                if (locatorValue.equalsIgnoreCase("nextButton")){
                    //Wait longer for a page change
                    log.info(">>> Next page");
                    page.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                }
                break;
            case "select_by_value" :
                Select dropDown = new Select(element);
                scrollToElement(page, element);  //required if screen scrolls
                waitForDropListToBeAvailable(page, dropDown);
                dropDown.selectByValue(inputValue);
                break;
            case "select_by_text" :
                Select textDropDown = new Select(element);
                scrollToElement(page, element);  //required if screen scrolls
                waitForDropListToBeAvailable(page, textDropDown);
                textDropDown.selectByVisibleText(inputValue);
                break;
            case "select_by_index" :
                Select indexDropDown = new Select(element);
                scrollToElement(page, element);  //required if screen scrolls
                waitForDropListToBeAvailable(page, indexDropDown);
                indexDropDown.selectByIndex(Integer.parseInt(inputValue));
                break;
            case "number" :
                element.sendKeys(inputValue);
                break;
            default :
                throw new RuntimeException("Input type [" + inputType + "] not defined");
        }
    }

    private void scrollToElement(WebDriver driver, WebElement el){
        // Create instance of Javascript executor
        JavascriptExecutor je = (JavascriptExecutor) driver;
        // now execute query which actually will scroll until that element appears on the page.
        je.executeScript("arguments[0].scrollIntoView(true);", el);
    }

    private void waitForElementToBeAvailable(WebDriver driver, By loc){
        //Wait for it to be visible
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        //Only one of these conditions will cause a wait but different elements have different properties
        wait.until(ExpectedConditions.presenceOfElementLocated(loc));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
        wait.until(ExpectedConditions.elementToBeClickable(loc));
    }

    private void waitForDropListToBeAvailable(WebDriver driver, Select sel){
        //Wait for the Select Drop values to be visible
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(new ExpectedCondition<Object>() {
            public Boolean apply(WebDriver d) {
                return (sel.getOptions().size()>1);
            }
        });
    }


    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public void setExpectedResultOperator(String expectedResultOperator) {
        this.expectedResultOperator = expectedResultOperator;
    }

    public void setExpectedResultValue(String expectedResultValue) {
        this.expectedResultValue = expectedResultValue;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }
}
