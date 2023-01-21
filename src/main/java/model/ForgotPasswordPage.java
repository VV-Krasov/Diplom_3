package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    public static String getPAGE_URL() {
        return PAGE_URL;
    }

    private final static String PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By loginLink = By.xpath("//a[text()='Войти']");
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickLoginLink()
    {
        driver.findElement(loginLink).click();
    }

    public ForgotPasswordPage open() {
        driver.get(PAGE_URL);
        return this;
    }
}
