package com.seopa.tests.automated.test.cases;

import com.seopa.tests.automated.questions.Question;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

/**
 * Defines the methods used to represent a test case.
 *
 * Created by patrick.mcparland on 23/12/2015.
 */
@Data
public abstract class TestCase {

    private String browser;
    private Boolean result = true;

    // TODO Pat - Need something for device

    /**
     * Loads the list of questions from the source specified in the child test case.
     *
     * @return  A non-null reference to the List of Questions loaded for this TestCase has been returned.
     */
    protected abstract List<Question> loadQuestions();

    /**
     * Determines the starting web page address for this Test Case.
     *
     * @return  A non-null, non-empty, String representing the starting URL for the TestCase has been returned.
     */
    protected abstract String determineStartingAddress();

    /**
     * Determines which Web Drvier has been selected to execute the TestCase.
     *
     * @return  If <code>browser</code> has been set to a recognised value for the available browsers then a
     *          new instance of the appropriate <code>WebDriver</code> has been returned.
     */
    private WebDriver createWebDriver() {
        WebDriver driver = null;
        switch (browser.toLowerCase()) {
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            case "ie" :
                driver = new InternetExplorerDriver();
                break;
            default :
                throw new RuntimeException("Browser [" + browser + "] not found");
        }
        return driver;
    }

    /**
     * Executes the tests.
     */
    public void executeTests() {

        List<Question> questionList = loadQuestions();
        String startingAddress = determineStartingAddress();

        // Go to first question page
        WebDriver driver = createWebDriver();
        driver.get(startingAddress);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        questionList.stream().forEach((question -> {
            question.executeQuestion(driver);
        }));
    }
}
