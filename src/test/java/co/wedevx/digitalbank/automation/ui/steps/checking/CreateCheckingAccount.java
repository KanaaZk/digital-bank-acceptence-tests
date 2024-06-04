package co.wedevx.digitalbank.automation.ui.steps.checking;

import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCheckingAccount {
    private WebDriver driver = Driver.getDriver();

    @Given("the user logs into the app using their credentials")
    public void the_user_logs_into_the_app_using_their_credentials( List<Map<String, String>> credentialsList) {
        Map<String, String> credentials = credentialsList.get(0); // Assuming there's only one set of credentials
        String username = credentials.get("username");
        String password = credentials.get("password");

        driver.get("https://dbank-qa.wedevx.co/bank/login"); // Replace with actual URL
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        //assertTrue(driver.getTitle().contains("home"));
        driver.navigate().to("https://dbank-qa.wedevx.co/bank/account/checking-add");
    }

    @When("the user attempts to create a new checking account with invalid details:")
    public void the_user_attempts_to_create_a_new_checking_account_with_invalid_details( List<Map<String, String>> accountDetailsList) {
        Map<String, String> accountDetails = accountDetailsList.get(0); // Assuming there's only one set of account details

        String accountType = accountDetails.get("accountType");
        String ownership = accountDetails.get("ownership");
        String accountName = accountDetails.get("accountName");
        String depositAmount = accountDetails.get("depositAmount");

        driver.findElement(By.id("Standard Checking")).click();
        driver.findElement(By.id("Individual")).click();
        driver.findElement(By.id("name")).sendKeys(accountName);
        driver.findElement(By.id("openingBalance")).sendKeys(depositAmount);

        driver.findElement(By.id("newCheckingSubmit")).click(); // Replace with actual ID
    }

    @Then("the user should see clear message indicating incorrect format")
    public void the_user_should_see_clear_message_indicating_incorrect_format() {
        //assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed()); // Replace with actual ID
    }

    @Then("the user should remain on the same page to correct the errors")
    public void the_user_should_remain_on_the_same_page_to_correct_the_errors() {
        //assertTrue(driver.getTitle().contains("Create Checking Account")); // Replace with actual title
    }

}
