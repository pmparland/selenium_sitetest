package com.seopa.tests.automated.test.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by patrickmcparland on 30/12/2015.
 */
public class ExcelTestCaseTest {

    private static String CAR_ADDRESS = "https://car-insurance.quotezone.co.uk/car/index.php?page=1";
    private static String HOME_ADDRESS = "https://home-insurance.quotezone.co.uk/home/";
    private static String FIREFOX = "FireFox";
    private static String CHROME = "Chrome"; //For chrome you need to install the chrome driver
    private static String IE = "IE"; //For chrome you need to install the IE driver


    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecuteSimpleCarQuote() throws Exception {
        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String excelFile = workingDir+separator+"src"+separator+"test"+separator+"java"+separator+ "com" + separator +"seopa"+separator+"tests"+separator+"automated"+separator+"test"+separator+"cases"+separator+ "SimpleCarJourney01.xlsx";

        ExcelTestCase test = new ExcelTestCase();

        assertTrue(test.execute(excelFile, CAR_ADDRESS, FIREFOX));
    }

    @Test
    public void testExecuteComplexCarQuote() throws Exception {
        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String excelFile = workingDir+separator+"src"+separator+"test"+separator+"java"+separator+ "com" + separator +"seopa"+separator+"tests"+separator+"automated"+separator+"test"+separator+"cases"+separator+ "ComplexCarJourney01.xlsx";

        ExcelTestCase test = new ExcelTestCase();

        assertTrue(test.execute(excelFile, CAR_ADDRESS, FIREFOX));
    }

    @Test
    public void testExecuteCarPageErrors() throws Exception {
        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String excelFile = workingDir+separator+"src"+separator+"test"+separator+"java"+separator+ "com" + separator +"seopa"+separator+"tests"+separator+"automated"+separator+"test"+separator+"cases"+separator+ "CarPageAboutCarErrors01.xlsx";

        ExcelTestCase test = new ExcelTestCase();

        assertTrue(test.execute(excelFile, CAR_ADDRESS, FIREFOX));
    }

    @Test
    public void testCarVehicleRegLookUp() throws Exception {
        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String excelFile = workingDir+separator+"src"+separator+"test"+separator+"java"+separator+ "com" + separator +"seopa"+separator+"tests"+separator+"automated"+separator+"test"+separator+"cases"+separator+ "CarRegistrationLookUp01.xlsx";

        ExcelTestCase test = new ExcelTestCase();

        assertTrue(test.execute(excelFile, CAR_ADDRESS, FIREFOX));
    }

    @Test
    public void testHomeSimpleQuote01() throws Exception {
        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String excelFile = workingDir+separator+"src"+separator+"test"+separator+"java"+separator+ "com" + separator +"seopa"+separator+"tests"+separator+"automated"+separator+"test"+separator+"cases"+separator+ "SimpleHomeQuote01.xlsx";

        ExcelTestCase test = new ExcelTestCase();

        assertTrue(test.execute(excelFile, HOME_ADDRESS, FIREFOX));
    }
}