package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawSavingsAccountPage {
    private WebDriver driver;


    public WithdrawSavingsAccountPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Savings")
    private WebElement savingsLink;

    @FindBy(id = "new-savings-menu-item")
    private WebElement newSavingsBtn;

    @FindBy(id = "Savings")
    private WebElement savingAccountBox;

    @FindBy(id = "Individual")
    private WebElement ownershipBox;

    @FindBy(id = "name")
    private WebElement accountNameBox;

    @FindBy(id = "openingBalance")
    private WebElement initialDepositBox;

    @FindBy(id = "newSavingsSubmit")
    private WebElement newSavingsSubmit;


    @FindBy(id = "withdraw-menu-item")
    private WebElement withdrawMenu;

    @FindBy(id = "view-savings-menu-item")
    private WebElement viewSavingsAccountsMenuItem;

    @FindBy(xpath = "//span[@id='balance']")
    private WebElement balanceElement;


    public void navigateToSavingsPage() {
        savingsLink.click();
    }

    public void clickNewSavingsButton() {
        newSavingsBtn.click();
    }

    public void createSavingsAccount(Map<String, String> accountDetails) {
        savingAccountBox.sendKeys(accountDetails.get("savingAccountType"));
        ownershipBox.sendKeys(accountDetails.get("savingAccountOwnership"));
        accountNameBox.sendKeys(accountDetails.get("savingAccountName"));
        initialDepositBox.sendKeys(accountDetails.get("initialSavingDeposits"));
        newSavingsSubmit.click();
    }

    public void navigateToPage(String pageName) {
        switch (pageName.toLowerCase()) {
            case "withdraw":
                withdrawMenu.click();
                break;
            case "view savings accounts":
                viewSavingsAccountsMenuItem.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid page name: " + pageName);

        }
    }
   public void validateNewAmount(String pageName, String expectedAmount) {
        navigateToPage(pageName);
       String actualAmount = balanceElement.getText();
      assertEquals(expectedAmount, actualAmount, "The displayed amount did not match the expected amount");
    }

}


