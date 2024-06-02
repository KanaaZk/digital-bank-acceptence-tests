package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.RandomUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserRegisterSteps {

    WebDriver driver;

    @Given("the new user is on the sign up page")
    public void the_new_user_is_on_the_sign_up_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dbank-qa.wedevx.co/bank/signup");

    }
    @When("the user fills out all the required fields")
    public void the_user_fills_out_all_the_required_fields(List<RandomUser> randomUserData) {
        RandomUser personalData = randomUserData.get(0);
        WebElement titleDropDown = driver.findElement(By.id("title"));
        Select titleSelect = new Select(titleDropDown);
        titleSelect.selectByVisibleText(personalData.getTitle());

        WebElement firstNameBox = driver.findElement(By.id("firstName"));
        firstNameBox.sendKeys(personalData.getFirstname());

        WebElement lastNameBox = driver.findElement(By.xpath("//input[@id='lastName']"));
        lastNameBox.sendKeys(personalData.getLastName());

        WebElement genderMRadioButton = driver.findElement(By.xpath("//input[@id='gender']"));
        genderMRadioButton.click();

        WebElement dateOfBirthBox = driver.findElement(By.xpath("//input[@id='dob']"));
        dateOfBirthBox.sendKeys(personalData.getDateOfBirth());

        WebElement ssnBox = driver.findElement(By.xpath("//input[@id='ssn']"));
        ssnBox.sendKeys(personalData.getSsn());

        WebElement emailBox = driver.findElement(By.xpath("//input[@id='emailAddress']"));
        emailBox.sendKeys(personalData.getEmail());

        WebElement passwordBox = driver.findElement(By.xpath("//input[@id='password']"));
        passwordBox.sendKeys(personalData.getPassword());

        WebElement confirmPasswordBox = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        confirmPasswordBox.sendKeys(personalData.getConfirmPassword());

        WebElement nextButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']"));
        nextButton.click();


    }
    @Then("the user should see the success message")
    public void the_user_should_see_the_success_message() {

        //WebElement successMessage = driver.findElement(By.id("success"));
       // Assertions.assertTrue(successMessage.isDisplayed());
        //driver.quit();
    }
}
