package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {
    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    List<Map<String, Object>> nextValList = new ArrayList<>();

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalBank.registrationPageUrl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration title is mismatch");


    }
    @When("User creates account with following fields")
    public void user_creates_account_with_following_fields(List<Map<String, String>> registrationTestDataMapListMap) {
       registrationPage.fillOutRegistrationForm(registrationTestDataMapListMap);

    }
    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
       assertEquals(expectedSuccessMessage, registrationPage.getMessage(), "message is mismatch");
    }

    @Then("the User should see the {string} required field error message {string}")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = registrationPage.getRequiredErrorMessage(fieldName);
        assertEquals(expectedErrorMessage, actualErrorMessage, "the error message of required " + fieldName + " field mismatch");

    }

    @Then("the following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileInforDBList) {
        Map<String, String> expectedUserInforMap = expectedUserProfileInforDBList.get(0);
        String queryUserTable = String.format("SELECT * FROM users WHERE username = '%s'", expectedUserInforMap.get("username"));
        String queryUserProfile = String.format("SELECT * FROM user_profile WHERE email_address = '%s'", expectedUserInforMap.get("email"));


        List<Map<String, Object>> actualUserInforList = DBUtils.runSQLSelectQuery(queryUserTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfile);

        assertEquals(1, actualUserInforList.size(), "Registration generated unexpected numbers of users");
        assertEquals(1, actualUserProfileInfoList.size(), "Registration generated unexpected numbers of user profile");

        Map<String, Object> actualUserInforMap = actualUserInforList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserInforList.get(0);

        assertEquals(expectedUserInforMap.get("title"), actualUserInforMap.get("title"), "registration generated title");
        assertEquals(expectedUserInforMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "registration generated  wrong firstname");
        assertEquals(expectedUserInforMap.get("lastName"), actualUserProfileInfoMap.get("last_name"), "registration generated  wrong lastname");
        assertEquals(expectedUserInforMap.get("gender"), actualUserProfileInfoMap.get("gender"), "registration generated  wrong gender");
        assertEquals(expectedUserInforMap.get("dob"), actualUserProfileInfoMap.get("dob"), "registration generated  wrong dob");
        assertEquals(expectedUserInforMap.get("ssn"), actualUserProfileInfoMap.get("ssn"), "registration generated  wrong ssn");
        assertEquals(expectedUserInforMap.get("email"), actualUserProfileInfoMap.get("email_address"), "registration generated  wrong email");
        assertEquals(expectedUserInforMap.get("address"), actualUserProfileInfoMap.get("address"), "registration generated  wrong address");
        assertEquals(expectedUserInforMap.get("locality"), actualUserProfileInfoMap.get("locality"), "registration generated  wrong locality");
        assertEquals(expectedUserInforMap.get("region"), actualUserProfileInfoMap.get("region"), "registration generated  wrong region");
        assertEquals(expectedUserInforMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "registration generated  wrong postalCode");
        assertEquals(expectedUserInforMap.get("country"), actualUserProfileInfoMap.get("country"), "registration generated  wrong country");
        assertEquals(expectedUserInforMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "registration generated  wrong homePhone");
        assertEquals(expectedUserInforMap.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"), "registration generated  wrong mobilePhone");
        assertEquals(expectedUserInforMap.get("workPhone"), actualUserProfileInfoMap.get("work_phone"), "registration generated  wrong workPhone");

        //validate users table
        assertEquals(expectedUserInforMap.get("accountNonExpired"),String.valueOf(actualUserInforMap.get("account_non_expired")), "accountNon is mismatch");
        assertEquals(expectedUserInforMap.get("accountNonLocked"), String.valueOf(actualUserInforMap.get("account_non_locked")), "account locked is mismatch");
        assertEquals(expectedUserInforMap.get("credentialsNonExpired"), String.valueOf(actualUserInforMap.get("credentials_non_expired")), "credentialsNon expired mismatch");
        assertEquals(expectedUserInforMap.get("enabled"), String.valueOf(actualUserInforMap.get("enabled")), "account enabled");
        assertEquals(expectedUserInforMap.get("email"), actualUserInforMap.get("username"), "username mismatch upon registration");
        assertEquals(nextValList.get(0).get("next_val"), actualUserInforMap.get("id"), "ID mismatch");

        long expectedUserProfileField = Integer.parseInt(String.valueOf(nextValList.get(0).get("next_val")));
        assertEquals(++expectedUserProfileField, actualUserProfileInfoMap.get("id"), "ID mismatch");

    }


    @Given("The following user with {string} is not in DB")
    public void theFollowingUserWithIsNotInDB(String email) {
        String queryForUserProfile = String.format("DELETE FROM user_profile WHERE email_address ='%s'", email);
        String queryForUsers = String.format("DELETE FROM user_profile WHERE email_address ='%s'", email);

        String  queryToGetNextValInHibernateSeqTable = String.format("select* from hibernate_sequence ");
        nextValList = DBUtils.runSQLSelectQuery(queryToGetNextValInHibernateSeqTable);

        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);
    }
}
