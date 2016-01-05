package com.seopa.tests.automated.test.cases;

import com.seopa.tests.automated.questions.ExcelQuestionList;
import com.seopa.tests.automated.questions.Question;

import java.util.List;

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

    @Override
    protected List<Question> loadQuestions() {
        return new ExcelQuestionList(excelFileName).getQuestions();
    }

    @Override
    protected String determineStartingAddress() {
        return address;
    }
}
