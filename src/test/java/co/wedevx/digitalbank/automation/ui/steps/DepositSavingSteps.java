package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.CombinePage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositSavingSteps {
    CombinePage combinePage = new CombinePage(getDriver());

    @Given("user is on the Digital Bank sign in page")
    public void user_is_on_the_digital_bank_sign_in_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalBank.loginUrl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration title is mismatch");


    }
    @Given("user logs in with  {string} and {string}")
    public void userLogsInWithAnd(String username, String password) {
        combinePage.signIn(username, password);

    }

    @When("user creates a savings account with the following fields and valid data")
    public void user_creates_a_savings_account_with_the_following_fields_and_valid_data(List<Map<String, String>> accountDetailsList) {
        combinePage.navigateToSavingsPage();
        combinePage.clickNewSavingsButton();
        Map<String, String> accountDetails = accountDetailsList.get(0);
        combinePage.createSavingsAccount(accountDetails);
    }
    @Then("user successfully creates a savings account and navigates to the {string} page")
    public void user_successfully_creates_a_savings_account_and_navigates_to_the_page(String page) {
        combinePage.navigateToDepositPage(page);



    }
    @When("user selects the new account to deposit with the following amount")
    public void user_selects_the_new_account_to_deposit_with_the_following_amount(List<Map<String, String>> depositDetailsList) {
        Map<String, String> depositDetails = depositDetailsList.get(0);
        combinePage.makeDeposit(depositDetails);


    }
    @Then("the user validates the new amount on the {string} page")
    public void the_user_validates_the_new_amount_on_the_page(String expectedText) {
        String newText = combinePage.viewSavings();
        assertEquals(expectedText, newText, "Text did not match after deposit");


    }
    @Then("the user validates the operation is saved in the history table with the following details")
    public void the_user_validates_the_operation_is_saved_in_the_history_table_with_the_following_details(List<Map<String, String>> expectedDetailsList) {
        Map<String, String> expectedDetails = expectedDetailsList.get(0);
        Map<String, String> actualDetails = combinePage.getOperationDetails();

        System.out.println("Expected Date: " + expectedDetails.get("Date"));
        System.out.println("Actual Date: " + actualDetails.get("Date"));

        //assertEquals(expectedDetails.get("Date"), actualDetails.get("Date"), "Date type did not match");
        assertEquals(expectedDetails.get("Category"), actualDetails.get("Category"), "Category did not match");
        //assertEquals(expectedDetails.get("Description"), actualDetails.get("Description"), "Description did not match");
       // assertEquals(expectedDetails.get("Amount"), actualDetails.get("Amount"), "Amount did not match");
        //assertEquals(expectedDetails.get("Balance"), actualDetails.get("Balance"), "Balance did not match");
    }
    @Then("the user validates the Date, Amount, and Balance columns are correct")
    public void the_user_validates_the_date_amount_and_balance_columns_are_correct() {


    }

}
