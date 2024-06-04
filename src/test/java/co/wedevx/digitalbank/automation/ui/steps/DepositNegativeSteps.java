package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.DBankDepositPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositNegativeSteps {
    WebDriver driver;
    LoginPage loginPage;
    DBankDepositPage depositPage;

    @Given("I am logged in as a registered user to the Digital bank home page")
    public void i_am_logged_in_as_a_registered_user_to_the_digital_bank_home_page() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertiesValue("digitalBank.homepageUrl"));
        assertEquals("Digital Bank", driver.getTitle(), "Registration title is mismatch");

        loginPage = new LoginPage(driver);
        loginPage.login("ayana@gmail.com", "Ayana123");

    }

    @When("I navigate to the {string} page and click the deposit button")
    public void i_navigate_to_the_page_and_click_the_deposit_button(String pageName) {
        driver.get(ConfigReader.getPropertiesValue("digitalBank.depositPageUrl"));

        depositPage = new DBankDepositPage(driver);
        depositPage.clickDepositButton();
    }

    @When("I input {int} balance to the {string} field")
    public void i_input_balance_to_the_field(Integer amount, String fieldName) {
        depositPage.enterDepositAmount(amount.toString());

    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        depositPage.clickSubmitButton();

    }

    @Then("I should see an error message displaying {string}")
    public void i_should_see_an_error_message_displaying(String expectedMessage) {

    }
}
