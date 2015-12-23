package TestCases;

import UserJourneys.CarJourney;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
public class SimpleCarJourneyTestCase extends TestCase {
    public  Boolean execute(){
        setName("SimpleCarJourneyTestCase");
        setBrowser("FireFox");
        CarJourney journey = new CarJourney();
        journey.simpleCarJourney(new FirefoxDriver());

        return getResult();
    }
    public static void main(String[] args) throws Exception {
        SimpleCarJourneyTestCase tc = new SimpleCarJourneyTestCase();
        tc.execute();
    }
}
