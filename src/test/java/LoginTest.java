import model.ForgotPasswordPage;
import model.LoginPage;
import model.MainPage;
import model.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import user.User;
import user.UserClient;
import user.UserGenerator;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final User user = UserGenerator.createRandomUser();
    private static final UserClient userClient = new UserClient();

    @Before
    public void setUp()
    {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        mainPage.open();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }

    @Test
    public void checkLoginWithCorrectDataUsingButtonAtMainPage()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        mainPage.clickLoginButton();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();

        Assert.assertTrue(mainPage.isOrderButtonClickable());
    }

    @Test
    public void checkLoginWithCorrectData()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        loginPage.open();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();

        Assert.assertTrue("При корректной авторизации не стала доступна кнопка создания заказа", mainPage.isOrderButtonClickable());
    }

    @Test
    public void checkLoginWithIncorrectData()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        loginPage.open();
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        loginPage.fillFields(user);
        loginPage.clickLoginButton();
        Assert.assertFalse("При некорректной авторизации для нажатия стала доступна кнопка создания заказа", mainPage.isOrderButtonClickable());
    }

    @Test
    public void checkLoginWithCorrectDataUsingUserProfileLinkAtMainPage()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        mainPage.header.clickUserCabinetLink();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();

        Assert.assertTrue(mainPage.isOrderButtonClickable());
    }

    @Test
    public void checkLoginWithCorrectDataUsingLoginLinkAtRegisterPage()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();

        Assert.assertTrue(mainPage.isOrderButtonClickable());
    }

    @Test
    public void checkLoginWithCorrectDataUsingLoginLinkAtForgotPasswordPage()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();

        Assert.assertTrue(mainPage.isOrderButtonClickable());
    }
}