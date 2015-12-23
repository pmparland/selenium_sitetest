package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by patrick.mcparland on 22/12/2015.
 */
public class TextQuestion extends Question {

    /**
     * Enter text for a question
     * @param page
     *      the current page on which the question appears.
     * @param questionName
     *      the question Name as it appears in the html.
     * @param text
     *      the text to be inserted for that question.
     */
    public static void enterText(WebDriver page, String questionName, String text) {
        WebElement question = page.findElement(By.name(questionName));
        question.sendKeys(text);
    }
}
