package co.wedevx.digitalbank.automation.ui.steps;
import co.wedevx.digitalbank.automation.ui.pages.*;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawPositiveSteps {
    private final SignInPage signInPage = new SignInPage(getDriver());
    private final WithdrawSavingsAccountPage withdrawSavingsAccountPage = new WithdrawSavingsAccountPage(getDriver());
    private final WithdrawPage withdrawPage = new WithdrawPage(getDriver());
    private final ViewSavingsAccountPage viewSavingsAccountPage = new ViewSavingsAccountPage(getDriver());

    @Given("the user is on the DBank sign-in page")
        public void the_user_is_on_the_d_bank_sign_in_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalBank.signInPageUrl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Sign In title is mismatch");

        }

        @Given("the user signs in with {string} and {string}")
        public void the_user_signs_in_with_and(String email, String password) {
            signInPage.signIn(email, password);
        }

        @When("the user creates a new savings account with the following fields and valid data")
        public void the_user_creates_a_new_savings_account_with_the_following_fields_and_valid_data(List<Map<String, String>> savingsAccountListOfMap) {
            withdrawSavingsAccountPage.navigateToSavingsPage();
            withdrawSavingsAccountPage.clickNewSavingsButton();
            Map<String, String> accountDetails = savingsAccountListOfMap.get(0);
            withdrawSavingsAccountPage.createSavingsAccount(accountDetails);
        }

        @Then("the user creates the savings account and navigates to the {string} page")
        public void the_user_creates_the_savings_account_and_navigates_to_the_page(String pageName) {
            withdrawSavingsAccountPage.navigateToPage(pageName);
        }

        @When("the user selects the account for withdrawal with the following amount")
        public void the_user_selects_the_account_for_withdrawal_with_the_following_amount(List<Map<String, String>> withdrawalData) {
            for (Map<String, String> row : withdrawalData) {
                withdrawPage.withdrawAmount(row.get("accountForWithdraw"), row.get("withdrawAmount"));
            }
        }

        @Then("the user validates the new balance on the {string} page is {int}")
        public void the_user_validates_the_new_balance_on_the_page_is(String pageName, Integer newBalance) {
            //viewSavingsAccountPage.navigateToViewSavingsPage();  // Ensure correct page navigation
            //viewSavingsAccountPage.validateNewAmount(newBalance.toString());
        }

        @Then("the user validates the operation is saved in the history table with details")
        public void the_user_validates_the_operation_is_saved_in_the_history_table_with_details(List<Map<String, String>> transactionDetails) {
//            Map<String, String> expectedDetails = transactionDetails.get(0);
//           Map<String, String> actualDetails = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
//
//            assertEquals(expectedDetails.get("Category"), actualDetails.get("actualCategory"), "Category mismatch");
//            assertEquals(expectedDetails.get("Description"), actualDetails.get("actualDescription"), "Description mismatch");
//            assertEquals(expectedDetails.get("Amount"), actualDetails.get("actualAmount"), "Amount mismatch");
//            assertEquals(expectedDetails.get("Balance"), actualDetails.get("actualBalance"), "Balance mismatch");
       }

}


