package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.ElementsHelper.isElementClickableInTime;

public class UserProfilePage {
    public static String getPAGE_URL() {
        return PAGE_URL;
    }

    private final static String PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private static final By buttonLogOut = By.xpath("//button[text()='Выход']");
    private WebDriver driver;
    public Header header;
    public UserProfilePage(WebDriver driver)
    {
        this.driver = driver;
        this.header = new Header(driver);
    }

    public void clickLogOutButton()
    {
        driver.findElement(buttonLogOut).click();
    }

    public boolean isLogOutButtonClickable()
    {
        return isElementClickableInTime(driver, buttonLogOut);
    }

    public UserProfilePage open() {
        driver.get(PAGE_URL);
        return this;
    }
}
