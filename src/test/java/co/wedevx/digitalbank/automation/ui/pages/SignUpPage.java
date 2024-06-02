package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckMark;

    @FindBy(id = "submit")
    private WebElement signInButton;

    public void signUp(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        rememberMeCheckMark.click();
        signInButton.click();
    }
}
