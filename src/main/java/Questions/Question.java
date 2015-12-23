package Questions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
public class Question {

    static final Logger logger = Logger.getLogger(Question.class);

    private static WebDriver page;

    public Question (WebDriver driver) {
        page = driver;
    }

    /**
     * Select a button
     * @param linkText
     *      the button name as it appears in the html.
     */
    public Question  selectButton(String linkText) {
        logger.info(">>> selectButton");

        page.findElement(By.linkText(linkText)).click();
        return this;

    }

    /**
     * Select a button.
     * @param xPath
     *      the xPath to the button.
     */
    public Question selectButtonByXPath(String xPath) {
        logger.info(">>> selectButtonByXPath");

        page.findElement(By.xpath(xPath)).click();
        return this;
    }

    /**
     * Enter numeric data for a question
     * @param questionName
     *      the question Name as it appears in the html.
     * @param number
     *      the number to be inserted for that question.
     */
    public  Question enterNumber(String questionName, String number) {
        logger.info(">>> enterNumber");

        WebElement question = page.findElement(By.name(questionName));
        question.sendKeys(number);
        return this;
    }

    /**
     * Select a dropdown value for a question
     * @param selectName
     *      the selectName Name as it appears in the html.
     * @param selectValue
     *      the selectValue to be selected for that question as it appears in the html option value
     */
    public  Question makeSelection(String selectName, String selectValue) {
        logger.info(">>> makeSelection");

        org.openqa.selenium.support.ui.Select dropDown = new org.openqa.selenium.support.ui.Select(page.findElement(By.name(selectName)));
        dropDown.selectByValue(selectValue);
        return this;
    }

    /**
     * Enter text for a question
     * @param questionName
     *      the question Name as it appears in the html.
     * @param text
     *      the text to be inserted for that question.
     */
    public Question enterText(String questionName, String text) {
        logger.info(">>> makeSelection");

        WebElement question = page.findElement(By.name(questionName));
        question.sendKeys(text);
        return this;
    }
}
