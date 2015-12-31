package TestCases;

import Questions.QuestionList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */

/**
 * A test case which excepts an Excel file and places the answers for each question on the correct page
 */
public class ExcelTestCase extends TestCase {

    /**
     * Execute the questions in an excel file against a give web address
     * @param excelFile
     *       the name and path of the excel file in a string
     * @param address
     *       the web page address
     * @param browser
     *       the browser to be used, Firefox, chrome or IE
     * @return Boolean
     *      test pass or fail.
     */
    public  Boolean execute(String excelFile, String address, String browser) throws  Exception {
        logger.info(">>> execute ExcelTestCase");

        //Set browser
        WebDriver page;
        if (browser.equalsIgnoreCase("chrome")){
            setBrowser("Chrome");
            page = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("FireFox")){
            setBrowser("FireFox");
            page = new FirefoxDriver();
        }
        else if (browser.equals("IE")){
            setBrowser("IE");
            page=new InternetExplorerDriver();
        }
        else {
            setResult(Boolean.FALSE);
            throw new Exception("Browser '" + browser + "' not found!!");
        }

        executeQuestions(page, excelFile, address);

        return getResult();
    }

    /**
     * Execute the questions in an excel file against a give web address - defaults to firefox
     * @param excelFile
     *       the name and path of the excel file in a string
     * @param address
     *       the web page address
     * @return Boolean
     *      test pass or fail.
     */
    public  Boolean execute(String excelFile, String address) throws  Exception {
        logger.info(">>> execute ExcelTestCase");

        //Set browser
        setBrowser("FireFox");
        WebDriver page = new FirefoxDriver();

        executeQuestions(page, excelFile, address);

        return getResult();
    }

    private void executeQuestions (WebDriver page, String excelFile, String address) throws Exception {
        logger.info(">>> execute executeQuestions");

        //Get questions
        QuestionList qList = new QuestionList(excelFile);

        // Go to first question page
        page.get(address);
        Thread.sleep(3000);

        //Start test
        setName("ExcelTestCase");
        qList.questions.stream().forEach((question -> {
            if (!question.executeQuestion(page)) setResult(Boolean.FALSE);
        }));

    }

}
