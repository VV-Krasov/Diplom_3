import model.LoginPage;
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
import user.UserCredentials;
import user.UserGenerator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private final User user = UserGenerator.createRandomUser();
    private static final UserClient userClient = new UserClient();
    @Before
    public void setUp()
    {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.open();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }

    @Test
    public void checkRegisterWithCorrectData()
    {
        registerPage.fillFields(user);
        registerPage.clickRegisterButton();

        userClient.login(new UserCredentials(user))
                .assertThat().statusCode(SC_OK)
                .body("success", is(true));
        Assert.assertTrue(loginPage.isLoginButtonClickable());
    }

    @Test
    public void checkRegisterWithIncorrectPassword()
    {
        user.setPassword(RandomStringUtils.randomAlphabetic(new Random().nextInt(6)));
        registerPage.fillFields(user);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.incorrectPasswordErrorPresent());
        Assert.assertEquals(RegisterPage.getPAGE_URL(), driver.getCurrentUrl());
    }

}