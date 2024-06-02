package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewAccountPage {
    private WebDriver driver;

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checking-menu")
    private WebElement checkingMenu;

    @FindBy(id = "new-checking-menu-item")
    private WebElement newCheckingMenuItem;

    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingTypeRadioButton;

    @FindBy(id = "Individual")
    private  WebElement IndividualField;

    @FindBy(id = "name")
    private WebElement accountNameField;

    @FindBy(id = "openingBalance")
    private WebElement initialDepositField;

    @FindBy(id = "newCheckingSubmit")
    private WebElement newCheckingSubmit;

    public void newAccount(String accountName, int initialDeposit) {

        checkingMenu.click();
        newCheckingMenuItem.click();
        assertEquals(ConfigReader.getPropertiesValue("digitalBank.createNewCheckingUrl"), driver.getCurrentUrl(), "new Checking Button didn't take the " + ConfigReader.getPropertiesValue("digitalBank.createNewCheckingUrl"));

        standardCheckingTypeRadioButton.click();
        IndividualField.click();
        accountNameField.sendKeys(accountName);
        initialDepositField.sendKeys(String.valueOf(initialDeposit));
        newCheckingSubmit.click();
    }
}
