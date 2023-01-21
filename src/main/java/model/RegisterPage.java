package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

import static helpers.ElementsHelper.isElementClickableInTime;

public class RegisterPage {
    public static String getPAGE_URL() {
        return PAGE_URL;
    }

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private static final By registerInputName = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By registerInputEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By registerInputPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By registerErrorIncorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    private static final By loginLink = By.xpath("//a[text()='Войти']");
    private WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
    }
    public void fillFields(String name, String email, String password) {
        driver.findElement(registerInputName).sendKeys(name);
        driver.findElement(registerInputEmail).sendKeys(email);
        driver.findElement(registerInputPassword).sendKeys(password);
    }

    public void fillFields(User user) {
        this.fillFields(user.getName(),user.getEmail(),user.getPassword());
    }

    public void clickRegisterButton()
    {
        driver.findElement(registerButton).click();
    }

    public boolean incorrectPasswordErrorPresent()
    {
        return isElementClickableInTime(driver,10,registerErrorIncorrectPassword);
    }

    public RegisterPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
