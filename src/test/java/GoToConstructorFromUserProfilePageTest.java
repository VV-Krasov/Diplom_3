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

public class GoToConstructorFromUserProfilePageTest {
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        userProfilePage = new UserProfilePage(driver);
        mainPage.open();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }

    @Test
    public void checkGoToConstructorByLink()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        loginPage.open();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();
        mainPage.header.clickUserCabinetLink();
        userProfilePage.header.clickConstructorLink();

        Assert.assertTrue(mainPage.isMakerBurgerSectionTitleClickable());
    }
    @Test
    public void checkGoToConstructorByLogo()
    {
        userClient.create(user)
                .assertThat()
                .body("success", is(true));
        loginPage.open();
        loginPage.fillFields(user);
        loginPage.clickLoginButton();
        mainPage.header.clickUserCabinetLink();
        userProfilePage.header.clickStellarBurgersLogo();

        Assert.assertTrue(mainPage.isMakerBurgerSectionTitleClickable());
    }

}