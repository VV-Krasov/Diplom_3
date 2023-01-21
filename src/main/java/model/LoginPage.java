package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

import static helpers.ElementsHelper.isElementClickableInTime;

public class LoginPage {
    public static String getPAGE_URL() {
        return PAGE_URL;
    }

    private final static String PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final By loginInputEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By loginInputPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By loginButton = By.xpath("//button[text()='Войти']");
    private static final By loginErrorIncorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }
    public void fillFields(String email, String password) {
        driver.findElement(loginInputEmail).sendKeys(email);
        driver.findElement(loginInputPassword).sendKeys(password);
    }

    public void fillFields(User user) {
        this.fillFields(user.getEmail(),user.getPassword());
    }

    public void clickLoginButton()
    {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginButtonClickable()
    {
        return isElementClickableInTime(driver, loginButton);
    }

    public boolean isIncorrectPasswordErrorPresent()
    {
        return isElementClickableInTime(driver,10,loginErrorIncorrectPassword);
    }

    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }
}
