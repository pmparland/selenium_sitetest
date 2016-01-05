package com.seopa.tests.automated.questions;

import lombok.Data;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Defines the set of data to answer a question.
 *
 * Created by patrick.mcparland on 23/12/2015.
 */
@Data
public class Question {

    private static org.apache.log4j.Logger log = Logger.getLogger(Question.class);

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
     * Execute a question by finding its location on the page and applying the input
     *
     * @param   page The driver for the web page to test.
     * @return  <code>true</code> has been returned if this Question can be executed, otherwise <code>false</code>
     *          has been returned.
     */
    public boolean executeQuestion(WebDriver page)  {
        // Ignore empty rows
        if (!isExecutable()) {return false;}

        // Answer the Question
        applyInput(page);

        // Check expect result
        boolean result;
        try {
            result = processExpectedResultOperator(page);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = false;
        }
        return result;
    }


    private By getLocator() {
        log.info(">>> getLocator " + locatorType + " using " + locatorValue);

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
        return element;
    }


    /**
     * Get the location of the question
     * @return element
     *      the element on the page
     */
    private boolean processExpectedResultOperator(WebDriver page) throws Exception {
        log.info(">>> processExpectedResultOperator " + expectedResultOperator + " using " + expectedResultValue);

        Boolean result;
        if (expectedResultOperator.equalsIgnoreCase("contains")) {
            if (page.getPageSource().contains(expectedResultValue))
                result = true;
            else
                result = false;
        } else
            throw new Exception("Expected Result Operator '" + expectedResultOperator + "' not defined!!");
        return result;
    }

    private void applyInput (WebDriver page) {
        log.info(">>> applyInput " + inputType + " using " + inputValue);

        // Get an instance of input type
        By element = getLocator();
        switch (inputType.toLowerCase()) {
            case "text" :
                page.findElement(element).sendKeys(inputValue);
                break;
            case "button" :
                page.findElement(element).click();
                if (locatorType.equalsIgnoreCase("nextButton"))
                    //Wait longer for a page change
                    page.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                else {
                    // Wait after a button press
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "select_by_value" :
                Select dropDown = new Select(page.findElement(element));
                dropDown.selectByValue(inputValue);
                break;
            case "select_by_text" :
                Select textDropDown = new Select(page.findElement(element));
                textDropDown.selectByVisibleText(inputValue);
                break;
            case "select_by_index" :
                Select indexDropDown = new Select(page.findElement(element));
                indexDropDown.selectByIndex(Integer.parseInt(inputValue));
                break;
            case "hidden_select" :
                Select hiddenDropDown = new Select(page.findElement(element));
                hiddenDropDown.selectByIndex(Integer.parseInt(inputValue));
                break;
            case "number" :
                page.findElement(element).sendKeys(inputValue);
                break;
            default :
                throw new RuntimeException("Input type [" + inputType + "] not defined");
        }
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
}
