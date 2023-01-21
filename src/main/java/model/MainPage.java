package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.ElementsHelper.isElementClickableInTime;

public class MainPage {
    private final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private static final By makeBurgerSectionTitle = By.xpath("//h1[text()='Соберите бургер']");

    private static final By makeBurgerSectionTabBuns = By.xpath("//span[text()='Булки']");
    private static final By makeBurgerSectionTabBunsFluorescentBunR2D3 = By.xpath("//p[text()='Флюоресцентная булка R2-D3']");

    private static final By makeBurgerSectionTabSouses = By.xpath("//span[text()='Соусы']");
    private static final By makeBurgerSectionTabSousesSpecialSpaceSauce = By.xpath("//p[text()='Соус фирменный Space Sauce']");

    private static final By makeBurgerSectionTabToppings = By.xpath("//span[text()='Начинки']");
    private static final By makeBurgerSectionTabToppingsMartianMagnoliaBioCutlet = By.xpath("//p[text()='Биокотлета из марсианской Магнолии']");
    public Header header;
    private WebDriver driver;

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
        this.header = new Header(driver);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickMakeBurgerSectionTabBuns() {
        if(isElementClickableInTime(driver, makeBurgerSectionTabBuns)) {
            driver.findElement(makeBurgerSectionTabBuns).click();
        }
    }

    public String getCurrentConstructorTabTitle()
    {
        return driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span")).getText();
    }
    public void clickMakeBurgerSectionTabSouses() {
        if(isElementClickableInTime(driver, makeBurgerSectionTabSouses)) {
            driver.findElement(makeBurgerSectionTabSouses).click();
        }
    }
    public void clickMakeBurgerSectionTabToppings() {
        if(isElementClickableInTime(driver, makeBurgerSectionTabToppings)) {
            driver.findElement(makeBurgerSectionTabToppings).click();
        }
    }

    public boolean isMartianMagnoliaBioCutletClickable()
    {
        return isElementClickableInTime(driver,10, makeBurgerSectionTabToppingsMartianMagnoliaBioCutlet);
    }
    public boolean isFluorescentBunR2D3Clickable()
    {
        return isElementClickableInTime(driver,10, makeBurgerSectionTabBunsFluorescentBunR2D3);
    }

    public boolean isSpecialSpaceSauceClickable()
    {
        return isElementClickableInTime(driver,10, makeBurgerSectionTabSousesSpecialSpaceSauce);
    }
    public boolean isMakerBurgerSectionTitleClickable()
    {
        return isElementClickableInTime(driver,10,makeBurgerSectionTitle);
    }

    public void open() {
        driver.get(PAGE_URL);
    }
    public boolean isOrderButtonClickable() {
        return isElementClickableInTime(driver,10,orderButton);
    }
}
