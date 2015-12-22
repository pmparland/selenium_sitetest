package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by patrick.mcparland on 22/12/2015.
 */
public class SelectQuestion {

    /**
     * Select a dropdown value for a question
     * @param page
     *      the current page on which the question appears.
     * @param selectName
     *      the selectName Name as it appears in the html.
     * @param selectValue
     *      the selectValue to be selected for that question as it appears in the html option value
     */
    public static void makeSelection(WebDriver page, String selectName, String selectValue) {
        org.openqa.selenium.support.ui.Select dropDown = new org.openqa.selenium.support.ui.Select(page.findElement(By.name(selectName)));
        dropDown.selectByValue(selectValue);
    }
}
