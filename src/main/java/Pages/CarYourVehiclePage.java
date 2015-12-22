package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by patrick.mcparland on 22/12/2015.
 */
public class CarYourVehiclePage {

    static String ADDRESS = "https://car-insurance.quotezone.co.uk/car/index.php?page=1";

    public static void submitSimpleData (WebDriver driver){
        // Go to first questions page
        driver.get(ADDRESS);

        Questions.TextQuestion.enterText(driver, "vehicle_registration_number", "YEZ 8097");
        Questions.Button.selectButton(driver, "Find");
        Questions.SelectQuestion.makeSelection(driver, "vehicle_kept", "D");            //On a private driveway
        Questions.SelectQuestion.makeSelection(driver, "vehicle_kept_day", "W");        //Work place car park
        Questions.SelectQuestion.makeSelection(driver, "vehicle_bought_yyyy", "2015");  //2015
        Questions.SelectQuestion.makeSelection(driver, "vehicle_bought_mm", "01");      //January
        Questions.NumericQuestion.enterNumber(driver, "estimated_value", "4003");
        Questions.Button.selectButtonByXPath(driver, "//input[@name='vehicle_alarm' and @value='Y']");
        Questions.Button.selectButtonByXPath(driver, "//input[@name='vehicle_immobiliser' and @value='Y']");
        Questions.Button.selectButtonByXPath(driver, "//input[@name='vehicle_tracking_device' and @value='Y']");
        Questions.SelectQuestion.makeSelection(driver, "vehicle_grey_or_import", "N");      //No
        Questions.Button.selectButtonByXPath(driver, "//input[@name='vehicle_modified_from_manufacturer' and @value='N']");
        Questions.SelectQuestion.makeSelection(driver, "seats_of_vehicle", "5");            //5

        //driver.quit();
    }

    public static void main(String[] args) throws Exception {
        submitSimpleData(new FirefoxDriver());
    }

}
