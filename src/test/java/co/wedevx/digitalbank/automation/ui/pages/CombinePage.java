package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CombinePage {
    WebDriver driver;

    public CombinePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    WebElement usernameTxtBox;

    @FindBy(id = "password")
    WebElement passwordBox;

    @FindBy(id = "remember-me")
    WebElement rememberMeCheckMark;


    @FindBy(id = "submit")
    WebElement submitBtn;

    //Savings

    @FindBy(linkText = "Savings")
    WebElement savingsLink;

    @FindBy(id = "new-savings-menu-item")
    WebElement newSavingsBtn;

    @FindBy(id = "Savings")
    WebElement savingAccountBox;

    @FindBy(id = "Individual")
    WebElement ownershipBox;

    @FindBy(id = "name")
    WebElement accountNameBox;

    @FindBy(id = "openingBalance")
    WebElement initialDepositBox;

    @FindBy(id = "newSavingsSubmit")
    WebElement newSavingsSubmit;

    //deposit page
    @FindBy(linkText = "Deposit")
    WebElement depositPageLink;

    @FindBy(id = "selectedAccount")
    WebElement selectedAccountBox;

    @FindBy(id = "amount")
    WebElement depositAmountBox;

    @FindBy(xpath = "//form//div[2]//button[1]")
    WebElement submitDepositBtn;

    @FindBy(id ="page-title")
    WebElement viewSavings;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[1]")
    WebElement dateTbBox;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[2]")
    WebElement categoryField;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[3]")
    WebElement descriptionField;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")
    WebElement  amountField;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[5]")
    WebElement balanceTypeField;


    public void signIn(String email, String password) {
        usernameTxtBox.sendKeys(email);
        passwordBox.sendKeys(password);
        submitBtn.click();
    }
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
    public void navigateToDepositPage(String page) {
        switch (page.toLowerCase()) {
            case "deposit":
                depositPageLink.click();
                break;

        }
    }
    public void makeDeposit(Map<String, String> depositDetails) {
        selectedAccountBox.sendKeys(depositDetails.get("accountForDeposit"));
        depositAmountBox.sendKeys(depositDetails.get("depositAmount"));
        submitDepositBtn.click();
    }

    public String viewSavings() {
        return viewSavings.getText();
    }
    public Map<String, String> getOperationDetails() {
        Map<String, String> operationDetails = new HashMap<>();

        //operationDetails.put("Date", dateTbBox.getText()); // Assuming you are checking for deposit
        operationDetails.put("Category", categoryField.getText());
       // operationDetails.put("Description", descriptionField.getText());
       // operationDetails.put("Amount", amountField.getText());
        //operationDetails.put("Balance", balanceTypeField.getText());

        System.out.println("Operation Details: " + operationDetails);
        return operationDetails;
    }


}
