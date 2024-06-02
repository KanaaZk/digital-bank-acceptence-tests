package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.NewAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.SignUpPage;
import co.wedevx.digitalbank.automation.ui.pages.TransferPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferSteps {
    SignUpPage signUpPage = new SignUpPage(getDriver());
    NewAccountPage newAccountPage = new NewAccountPage(getDriver());
    TransferPage transferPage = new TransferPage(getDriver());

    @Given("a signed up user")
    public void a_signed_up_user() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalBank.signInPageUrl"));
        signUpPage.signUp("jessa@gmail.com", "Jessica123");

    }
    @Given("the user has the following accounts:")
    public void the_user_has_the_following_accounts(List<Map<String, String>> accountList) {
        for (Map<String, String> account : accountList) {
            newAccountPage.newAccount(account.get("accountName"), Integer.parseInt(account.get("initialDeposit")));
            //System.out.println("Created account: " + account.get("accountName") + " with initial deposit: " + account.get("initialDeposit"));
        }
    }
    @When("the user navigates to the {string} page")
    public void the_user_navigates_to_the_page(String string) {
        transferPage.navigateToTransferPage();


    }
    @When("the user transfers {int} from {string} to {string}")
    public void the_user_transfers_from_to(Integer amount, String fromAccount, String toAccount) {
        //transferPage.selectFromAccount(fromAccount);
        //transferPage.selectToAccount(toAccount);
       // transferPage.enterAmount(amount);
        //transferPage.clickSubmit();

    }
    @Then("the new amount in {string} should be {int}")
    public void the_new_amount_in_should_be(String toAccount, Integer expectedBalance) {

        //int actualBalance = transferPage.getNewBalance(toAccount);
       // assertEquals(expectedBalance, actualBalance, "Expected balance and the actual balance are mismatch");

    }
    @Then("the operation should be saved in the history table with the appropriate message")
    public void the_operation_should_be_saved_in_the_history_table_with_the_appropriate_message() {

        //assertTrue(transferPage.validateHistoryTable());
    }

    @When("the user selects {string} in the From selector")
    public void the_user_selects_in_the_from_selector(String fromAccount) {
        transferPage.selectFromAccount(fromAccount);
    }
    @When("the user selects {string} in the To selector")
    public void the_user_selects_in_the_to_selector(String toAccount) {
        transferPage.selectToAccount(toAccount);
    }
    @When("the user enters amount {int}")
    public void the_user_enters_amount(Integer amount) {
        transferPage.enterAmount(amount);

    }
    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String submitBtn) {
        transferPage.clickSubmit();
    }
    @Then("the transfer should be successful")
    public void the_transfer_should_be_successful() {
        //assertTrue(transferPage.isSuccessMessageDisplayed(), "Error message is not displayed");
    }

}
