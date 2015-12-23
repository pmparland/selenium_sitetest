package TestCases;

import UserJourneys.CarJourney;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
public class SimpleCarJourneyTestCase extends TestCase {

    public  Boolean execute(){
        logger.info(">>> execute SimpleCarJourneyTestCase");

        setName("SimpleCarJourneyTestCase");
        setBrowser("FireFox");
        CarJourney journey = new CarJourney();
        journey.simpleCarJourney(new FirefoxDriver());

        return getResult();
    }
    public static void main(String[] args) throws Exception {
        Boolean res = false;
        SimpleCarJourneyTestCase tc = new SimpleCarJourneyTestCase();
        res = tc.execute();
        if (res) {
            logger.info("Test case: " + tc.getName() + "Passed");
        }
        else {
            logger.info("Test case: " + tc.getName() + "Failed" );
        }
    }
}
