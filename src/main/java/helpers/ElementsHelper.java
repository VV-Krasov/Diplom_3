package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsHelper {
    public static boolean isElementClickableInTime(WebDriver driver, int timeInSecondsToWait, By by){
        try {
            new WebDriverWait(driver, timeInSecondsToWait).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }
    public static boolean isElementClickableInTime(WebDriver driver, By by)
    {
        return isElementClickableInTime(driver,5, by);
    }
}