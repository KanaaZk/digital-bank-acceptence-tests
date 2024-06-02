package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WithdrawPage {
    WebDriver driver;

    public WithdrawPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"selectedAccount\"]")
    private WebElement accountForWithdraw;

    @FindBy(id = "amount")
    private WebElement withdrawAmount;

    @FindBy(xpath = "//form/div[2]/button[1]")
    private WebElement withdrawButton;

    public void navigateTo() {
        driver.get("http://dbank.com/withdraw");
    }

    public void withdrawAmount(String account, String amount) {
        accountForWithdraw.sendKeys(account);
        withdrawAmount.sendKeys(amount);
        withdrawButton.click();
    }
}
