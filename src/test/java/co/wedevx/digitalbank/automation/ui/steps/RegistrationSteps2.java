package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankRegistration;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationSteps2 {
        WebDriver driver = new ChromeDriver();
        @BeforeAll
        public static void setup() {
            WebDriverManager.chromedriver().setup();
        }
        @Given("the user is on the sign up page")
        public void the_user_is_on_the_sign_up_page() {

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("https://dbank-qa.wedevx.co/bank/signup");

        }
        @When("the user fills out all the require fields")
        public void the_user_fills_out_all_the_require_fields(List<BankRegistration> registrationData) {
            BankRegistration personalData = registrationData.get(0);

            WebElement titleDropDown = driver.findElement(By.id("title"));
            Select titleSelect = new Select(titleDropDown);
            titleSelect.selectByVisibleText(personalData.getTitle());

            WebElement firstNameBox = driver.findElement(By.id("firstName"));
            firstNameBox.sendKeys(personalData.getFirstName());

            WebElement lastNameBox = driver.findElement(By.xpath("//input[@id='lastName']"));
            lastNameBox.sendKeys(personalData.getLastName());

            WebElement genderMRadioButton = driver.findElement(By.xpath("//input[@id='gender']"));
            genderMRadioButton.click();

            WebElement dateOfBirthBox = driver.findElement(By.xpath("//input[@id='dob']"));
            dateOfBirthBox.sendKeys(personalData.getDateOfBirth());

            WebElement ssnBox = driver.findElement(By.xpath("//input[@id='ssn']"));
            ssnBox.sendKeys(personalData.getSocialSecurityNumber());

            WebElement emailBox = driver.findElement(By.xpath("//input[@id='emailAddress']"));
            emailBox.sendKeys(personalData.getEmailAddress());

            WebElement passwordBox = driver.findElement(By.xpath("//input[@id='password']"));
            passwordBox.sendKeys(personalData.getPassword());

            WebElement confirmPasswordBox = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
            confirmPasswordBox.sendKeys(personalData.getConfirmPassword());

            WebElement nextButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']"));
            nextButton.click();

        }

        @Then("the user should see the success messageAddress")
        public void the_user_should_see_the_success_message_address() {
        }
    }
