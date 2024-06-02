package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSavingsAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public ViewSavingsAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "view-savings-menu-item")
    private WebElement viewSavingsBtn;

    @FindBy(id = "page-title")
    private WebElement viewSavingsAccountTitle;

    @FindBy(xpath = "//*[@id=\"29577\"]/div/div[7]")
    private WebElement balanceElement;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransaction;

    public void navigateToViewSavingsPage() {
        waitForPageLoad();
        scrollToElement(viewSavingsBtn);
        retryingFindClick(viewSavingsBtn);
    }

    public String getViewSavingsAccountPageTitle() {
        return wait.until(ExpectedConditions.visibilityOf(viewSavingsAccountTitle)).getText();
    }

    public void validateNewAmount(String expectedAmount) {
        String actualAmount = wait.until(ExpectedConditions.visibilityOf(balanceElement)).getText();
        assertEquals(expectedAmount, actualAmount, "The displayed amount did not match the expected amount");
    }

    public Map<String, String> getNewlyAddedSavingsAccountTransactionInfoMap() {
        WebElement firstRowOfTransaction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='transactionTable']/tbody/tr")));
        List<WebElement> firstRowColumns = firstRowOfTransaction.findElements(By.xpath("td"));

        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualDate", firstRowColumns.get(0).getText());
        actualResultMap.put("actualCategory", firstRowColumns.get(1).getText());
        actualResultMap.put("actualDescription", firstRowColumns.get(2).getText());
        actualResultMap.put("actualAmount", firstRowColumns.get(3).getText().substring(1)); // Assuming the amount has a currency symbol
        actualResultMap.put("actualBalance", firstRowColumns.get(4).getText().substring(1)); // Assuming the balance has a currency symbol

        return actualResultMap;
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void retryingFindClick(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return;
            } catch (ElementNotInteractableException | StaleElementReferenceException e) {
                scrollToElement(element);
                attempts++;
            }
        }
        throw new ElementNotInteractableException("Element not interactable after multiple attempts: " + element);
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}