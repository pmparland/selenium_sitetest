package Pages;

import Questions.Question;
import org.openqa.selenium.WebDriver;


/**
 * Created by patrick.mcparland on 22/12/2015.
 */
public class CarYourVehiclePage extends Page {

    static String ADDRESS = "https://car-insurance.quotezone.co.uk/car/index.php?page=1";

    /**
     * Submit a simple set of data to the Car Your Vehicle Page
     * @param driver
     *      the current web driver.
     */
    public static void submitSimpleData (WebDriver driver){
        logger.info(">>> submitSimpleData");


        // Go to first questions page
        driver.get(ADDRESS);

        Question q = new Question(driver);

        q.enterText("vehicle_registration_number", "EXI5697");
        q.selectButton("Find");
        // Todo need to find some way to select when multiple vehicles returned
        q.makeSelection("vehicle_kept", "D");            //On a private driveway
        q.makeSelection("vehicle_kept_day", "W");        //Work place car park
        q.makeSelection("vehicle_bought_yyyy", "2015");  //2015
        q.makeSelection("vehicle_bought_mm", "01");      //January
        q.enterNumber("estimated_value", "4003");
        q.selectButtonByXPath("//input[@name='vehicle_alarm' and @value='Y']");
        q.selectButtonByXPath("//input[@name='vehicle_immobiliser' and @value='Y']");
        q.selectButtonByXPath("//input[@name='vehicle_tracking_device' and @value='Y']");
        q.makeSelection("vehicle_grey_or_import", "N");      //No
        q.selectButtonByXPath("//input[@name='vehicle_modified_from_manufacturer' and @value='N']");
        q.makeSelection("seats_of_vehicle", "5");            //5

    }

}
