package UserJourneys;

import Pages.CarYourVehiclePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by patrick.mcparland on 23/12/2015.
 */
public class CarJourney extends Journey {

    /**
     * Submit a simple car journey
     * @param driver
     *      the current web driver.
     */
    public static void simpleCarJourney (WebDriver driver) {
        logger.info(">>> simpleCarJourney");

        CarYourVehiclePage yourVehiclePage = new CarYourVehiclePage();
        yourVehiclePage.submitSimpleData(driver);
    }

}
