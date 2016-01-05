package TestCases;

import Questions.ExcelQuestionList;
import Questions.Question;

<<<<<<< HEAD
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
=======
import java.util.List;
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a

/**
 * A test case which excepts an Excel file and places the answers for each question on the correct page.
 *
 * Created by patrick.mcparland on 23/12/2015.
 */
public class ExcelTestCase extends TestCase {

    private String excelFileName;
    private String address;

    /**
     * Execute the questions in an excel file against a give web address.
     *
     * @param   excelFile The name and path of the excel file in a string.
     * @param   address The web page address.
     * @param   browser The browser to be used, Firefox, chrome or IE.
     *
     * @return  <code>true</code> has been returned if the TestCase executed successfully, otherwise an Exception
     *          will have been thrown by the component that failed.
     */
    public boolean execute(String excelFile, String address, String browser) throws Exception {

        setBrowser(browser);
        this.address = address;
        this.excelFileName = excelFile;

        executeTests();
        return true;
    }

    /**
     * Execute the questions in an excel file against a give web address - defaults to firefox.
     *
     * @param   excelFile The name and path of the excel file in a string.
     * @param   address The web page address.
     *
     * @return  <code>true</code> has been returned if the TestCase executed successfully, otherwise an Exception
     *          will have been thrown by the component that failed.
     */
    public boolean execute(String excelFile, String address) throws  Exception {

        setBrowser("firefox");
        this.address = address;
        this.excelFileName = excelFile;

        executeTests();
        return true;
    }

<<<<<<< HEAD
    private void executeQuestions (WebDriver page, String excelFile, String address) throws Exception {
        logger.info(">>> execute executeQuestions");

        //Get questions
        QuestionList qList = new QuestionList(excelFile);

        // Go to first question page
        page.get(address);
        // Wait for first page to load - up to 10 seconds
        page.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //Start test
        setName("ExcelTestCase");
        qList.questions.stream().forEach((question -> {
            if (!question.executeQuestion(page)) {
                setResult(Boolean.FALSE);
            }
        }));

=======
    @Override
    protected List<Question> loadQuestions() {
        return new ExcelQuestionList(excelFileName).getQuestions();
>>>>>>> 140b33d9f958ff3df832f4f773f071c5bdc5c89a
    }

    @Override
    protected String determineStartingAddress() {
        return address;
    }
}
