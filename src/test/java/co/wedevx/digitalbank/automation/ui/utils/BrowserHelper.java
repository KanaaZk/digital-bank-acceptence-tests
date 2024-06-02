package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 *WebDriver Helper Extensions is designed to simplify Java based Selenium/WebDriver tests.
 * It's built on top of Selenium/WebDriver to make your tests more readable, reusable and
 * maintainable by providing easy handling towards browser and advance identifiers.
 */

public class BrowserHelper {

    //wait until the element visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return  wait.until(ExpectedConditions.visibilityOf(element));

    }

    //wait until the element is clickable and click on it
    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;
    }

    // Scroll the web page to bring the specified element into view
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            if (element != null) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            } else {
                throw new NoSuchElementException("Element is null and cannot be scrolled into view.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while trying to scroll the element into view: " + e.getMessage());
        }
    }
}
