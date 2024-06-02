package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage {

    private final WebDriver driver;

    public CreateCheckingPage(WebDriver driver) {
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
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;


    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {

        NewCheckingAccountInfo testDataForCheckingAccount = checkingAccountInfoList.get(0);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checkingMenu.click();
        newCheckingMenuItem.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalBank.createNewCheckingUrl"), driver.getCurrentUrl(), "new Checking Button didn't take the " + ConfigReader.getPropertiesValue("digitalBank.createNewCheckingUrl"));

        if (testDataForCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingTypeRadioButton.click();
        } else if (testDataForCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking"))
        {
            interestCheckingTypeRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid checking account type option");
        }

        if (testDataForCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipTypeRadioButton.click();
        }
        else if (testDataForCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipTypeRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid ownership type option provided. Only supports Standard Checking and Interest Checking");
        }

        accountNameTxtBox.sendKeys(testDataForCheckingAccount.getAccountName());
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForCheckingAccount.getInitialDepositAmount()));
        submitBtn.click();
    }

}
