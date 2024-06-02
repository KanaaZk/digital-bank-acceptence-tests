package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DBankDepositPage {
    WebDriver driver;
    WebDriverWait wait;

    public DBankDepositPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//*[@id='deposit-menu-item']")
    private WebElement depositBtn;

    @FindBy(id = "amount")
    private WebElement depositAmountBtn;

    @FindBy(xpath = "//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")
    private WebElement submitForDepositBtn;

   // @FindBy(id = "accountFieldError")
   // private WebElement accountFieldError;

    public void clickDepositButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(depositBtn));
        element.click();
    }

    public void enterDepositAmount(String amount) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(depositAmountBtn));
        element.sendKeys(amount);
    }

    public void clickSubmitButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(submitForDepositBtn));
        element.click();
    }

}
