package Questions;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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
     */
    public void executeQuestion(WebDriver page) {
        // Ignore empty rows
        if (!isExecutable()) {return ;}

        // Answer the Question
        applyInput(page);
    }

    /**
     * Get the location of the question.
     *
     * @return  The location of the element representing this Question.
     */
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
        return element;

    }

    /**
     * Insert or the select the input.
     *
     * @param   page The driver for the web page to test.
     */
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

    }

}
