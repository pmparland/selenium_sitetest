package TestCases;

import QuestionMapRepo.QuestionMap;
import UserJourneys.CarJourney;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
public class ExcelTestCase extends TestCase {

    static String ADDRESS = "https://car-insurance.quotezone.co.uk/car/index.php?page=1";


    public  Boolean execute() throws  Exception {
        logger.info(">>> execute ExcelTestCase");

        //Get current working directory
        String workingDir=System.getProperty("user.dir");
        String testData = workingDir+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+ "objectmap.properties";
        //Get object map file
        logger.info(">>> load file:" + testData);
        WebDriver page = new FirefoxDriver();
        QuestionMap objmap = new QuestionMap(page, testData);

        // Go to first questions page
        page.get(ADDRESS);

        //Start test
        setName("ExcelTestCase");
        setBrowser("FireFox");
        CarJourney journey = new CarJourney();
        objmap.populateQuestions();

        return getResult();
    }
    public static void main(String[] args) throws Exception {
        Boolean res = false;
        ExcelTestCase tc = new ExcelTestCase();
        res = tc.execute();
        if (res) {
            logger.info("Test case: " + tc.getName() + "Passed");
        }
        else {
            logger.info("Test case: " + tc.getName() + "Failed" );
        }
    }
}
