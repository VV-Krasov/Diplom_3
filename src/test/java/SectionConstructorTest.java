import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SectionConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp()
    {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }
    @Test
    public void checkGoingToSectionBuns()
    {
        mainPage.clickMakeBurgerSectionTabToppings();
        mainPage.clickMakeBurgerSectionTabBuns();
        Assert.assertTrue(mainPage.isFluorescentBunR2D3Clickable());
        Assert.assertEquals("Булки", mainPage.getCurrentConstructorTabTitle());

    }

    @Test
    public void checkGoingToSectionSouses()
    {
        mainPage.clickMakeBurgerSectionTabSouses();
        Assert.assertTrue(mainPage.isSpecialSpaceSauceClickable());
        Assert.assertEquals("Соусы", mainPage.getCurrentConstructorTabTitle());
    }
    @Test
    public void checkGoingToSectionToppings()
    {
        mainPage.clickMakeBurgerSectionTabToppings();
        Assert.assertTrue(mainPage.isMartianMagnoliaBioCutletClickable());
        Assert.assertEquals("Начинки", mainPage.getCurrentConstructorTabTitle());
    }

}