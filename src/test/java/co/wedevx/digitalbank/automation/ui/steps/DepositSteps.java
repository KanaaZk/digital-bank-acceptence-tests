package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.AccountPage;
import co.wedevx.digitalbank.automation.ui.pages.DepositPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositSteps {

    WebDriver driver;
    LoginPage loginPage;
    AccountPage accountPage;
    DepositPage depositPage;

    @Given("I am logged into the Digital Bank home page with {string} and {string}")
    public void i_am_logged_into_the_digital_bank_home_page_with_and(String email, String password) {
        driver = getDriver();
        driver.get(ConfigReader.getPropertiesValue("digitalBank.depositPageUrl"));
        assertEquals("Digital Bank", driver.getTitle(), "Registration title is mismatch");

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(email, password);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
    }
    @When("I create a new checking account with the following details:")
    public void i_create_a_new_checking_account_with_the_following_details(List<Map<String, String>> accountDetailsListOfMap) {
        accountPage.createCheckingAccount(accountDetailsListOfMap.get(0));
        depositPage = PageFactory.initElements(driver, DepositPage.class);

    }
    @Then("I navigate to the deposit page by selecting the account {string}")
    public void i_navigate_to_the_deposit_page_by_selecting_the_account(String accountName) {
        depositPage.navigateToDepositPage(accountName);
    }
    @Then("I input the amount {string} into the {string} field")
    public void i_input_the_amount_into_the_field(String amount, String field) {
        depositPage.inputDepositAmount(amount, field);
    }
    @When("I click on the {string} button")
    public void i_click_on_the_button(String button) {
        depositPage.clickButton(button);
    }
    @Then("I should see the new balance on the {string} page reflecting the deposit amount")
    public void i_should_see_the_new_balance_on_the_page_reflecting_the_deposit_amount(String page) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.titleContains(page));
        } catch (TimeoutException e) {
            System.out.println("Current title: " + driver.getTitle());
           throw e;
        }
       String newBalance = depositPage.getNewBalance();
        assertEquals("200", newBalance, "newBalance is mismatch");

    }
    @Then("I should see the operation saved in the history table with the appropriate message:")
    public void i_should_see_the_operation_saved_in_the_history_table_with_the_appropriate_message( List<Map<String, String>> expectedMessages) {
        boolean messageFound = depositPage.checkHistoryTable(expectedMessages.get(0).get("Description"));
        assertEquals(true, messageFound, "Message not found");

    }
    @Then("the Date, Amount, and Balance columns should be correctly updated")
    public void the_date_amount_and_balance_columns_should_be_correctly_updated() {
        boolean columnsUpdated = depositPage.checkDateAmountBalance();
        assertEquals(true, columnsUpdated);
    }

}
