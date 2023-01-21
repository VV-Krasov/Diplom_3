import model.LoginPage;
import model.MainPage;
import model.UserProfilePage;
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

public class LogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private UserProfilePage userProfilePage;
    private final User user = UserGenerator.createRandomUser();
    private static final UserClient userClient = new UserClient();

    @Before
    public void setUp()
    {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        userProfilePage = new UserProfilePage(driver);

        loginPage.open();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }
    @Test
    public void checkLogoutUsingUserProfilePage()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        loginPage.fillFields(user);
        loginPage.clickLoginButton();
        mainPage.header.clickUserCabinetLink();
        userProfilePage.clickLogOutButton();
        Assert.assertTrue(loginPage.isLoginButtonClickable());
    }
}