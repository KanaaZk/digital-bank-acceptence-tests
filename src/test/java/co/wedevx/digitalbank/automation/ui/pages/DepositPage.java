package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DepositPage {
    WebDriver driver;

    public DepositPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"deposit-menu-item\"]")
    private WebElement depositBtn;

    @FindBy(xpath = "//select[@id='selectedAccount']")
    private WebElement selectAccountForDeposit;

    @FindBy(xpath = "//*[@id=\"selectedAccount\"]/option[7]")
    private WebElement optionValue;

    @FindBy(xpath = "//input[@id='amount']")
    private WebElement depositAmountBtn;

    @FindBy(xpath = "//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")
    private WebElement submitForDepositBtn;

    @FindBy(xpath = "/html/head/title")
    private  WebElement balance;

    @FindBy(id = "historyTable")
    private WebElement historyTable;


    public void navigateToDepositPage(String accountName) {
        depositBtn.click();
        Select accountDropdown = new Select(selectAccountForDeposit);
        accountDropdown.selectByVisibleText(accountName);
    }

    public void inputDepositAmount(String amount, String field) {
        if (field.equals("Deposit Amount")) {
            depositAmountBtn.sendKeys(amount);
        }
    }

    public void clickButton(String button) {
       button.equals("Submit");
       submitForDepositBtn.click();

    }

   public String getNewBalance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(balance));
        return balance.getText();
    }

    public boolean checkHistoryTable(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(historyTable));

        return true;
    }

    public boolean checkDateAmountBalance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(historyTable));
        return true;
    }
}

