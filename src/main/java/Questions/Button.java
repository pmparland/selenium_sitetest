package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by patrick.mcparland on 22/12/2015.
 */
public class Button {

    /**
     * Select a button
     * @param page
     *      the current page on which the question appears.
     * @param linkText
     *      the button name as it appears in the html.
     */
    public static void selectButton(WebDriver page, String linkText) {
        page.findElement(By.linkText(linkText)).click();

    }

    /**
     * Select a button
     * @param page
     *      the current page on which the question appears.
     * @param xPath
     *      the xPath to the button.
     */
    public static void selectButtonByXPath(WebDriver page, String xPath) {
        page.findElement(By.xpath(xPath)).click();
    }


}
