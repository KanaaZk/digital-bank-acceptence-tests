package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class AccountPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"checking-menu\"]")
    private WebElement checkingMenu;

    @FindBy(xpath = "//*[@id=\"new-checking-menu-item\"]")
    private WebElement newCheckingMenuItem;

    @FindBy(xpath = "//input[@id='Interest Checking']")
    private WebElement interestCheckingOption;

    @FindBy(xpath = "//input[@id='Joint']")
    private WebElement jointOption;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement accountNameField;

    @FindBy(xpath = "//input[@id='openingBalance']")
    private WebElement initialDepositAmountField;

    @FindBy(xpath = "//button[@id='newCheckingSubmit']")
    private WebElement submitButton;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createCheckingAccount(Map<String, String> accountDetails) {
        checkingMenu.click();
        newCheckingMenuItem.click();
        interestCheckingOption.click();
        jointOption.click();
        accountNameField.sendKeys(accountDetails.get("accountName"));
        initialDepositAmountField.sendKeys(accountDetails.get("initialDepositAmount"));
        submitButton.click();
    }

    public static class WithdrawSignInPage {
        WebDriver driver;

        public WithdrawSignInPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(id = "username")
        private WebElement emailField;

        @FindBy(id = "password")
        private WebElement passwordField;

        @FindBy(id = "submit")
        private WebElement signInButton;

        public void enterEmail(String email) {
            emailField.sendKeys(email);
        }

        public void enterPassword(String password) {
            passwordField.sendKeys(password);
        }

        public void clickSignIn() {
            signInButton.click();
        }
    }
}
