package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransferPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public TransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "fromAccount")
    private WebElement fromAccountSelect;

    @FindBy(id = "toAccount")
    private WebElement toAccountSelect;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(xpath = "//form/div[2]/button[1]")
    private WebElement submitButton;

    @FindBy(id = "transactionTable_wrapper")
    private WebElement historyTable;

    @FindBy(id = "successMessage")
    private WebElement successMessage;


    public void navigateToTransferPage() {
        driver.get("https://dbank-qa.wedevx.co/bank/account/xfer-between");
    }

    public void selectFromAccount(String fromAccount) {
//        wait.until(ExpectedConditions.visibilityOf(fromAccountSelect));
//        printDropdownOptions(fromAccountSelect);  // Print options for debugging
//        Select fromAccountDropdown = new Select(fromAccountSelect);
//        fromAccountDropdown.selectByVisibleText(fromAccount);
        fromAccountSelect.sendKeys(fromAccount);
        fromAccountSelect.click();
    }

    public void selectToAccount(String toAccount) {
//        wait.until(ExpectedConditions.visibilityOf(toAccountSelect));
//        printDropdownOptions(toAccountSelect);  // Print options for debugging
//        Select toAccountDropdown = new Select(toAccountSelect);
//        toAccountDropdown.selectByVisibleText(toAccount);
        toAccountSelect.sendKeys(toAccount);
        toAccountSelect.click();

    }

    public void enterAmount(int amount) {
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.sendKeys(String.valueOf(amount));
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public int getNewBalance(String toAccount) {

        WebElement balanceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + toAccount + "')]/following-sibling::td[@class='balance']")));
        return Integer.parseInt(balanceElement.getText().replaceAll("[^0-9]", ""));
    }

    public boolean validateHistoryTable() {
        wait.until(ExpectedConditions.visibilityOf(historyTable));
        List<WebElement> rows = historyTable.findElements(By.tagName("tr"));
        boolean transferFound = false;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 0) {
                String operationType = cells.get(0).getText();
                String category = cells.get(1).getText();
                String description = cells.get(2).getText();
                if (operationType.contains("Transfer") && (category.contains("Income") || category.contains("Misc")) && description.contains("Transfer from Account")) {
                    transferFound = true;
                    break;
                }
            }
        }
        return transferFound;
    }

    private void printDropdownOptions(WebElement dropdown) {
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        System.out.println("Dropdown options:");
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }
}


