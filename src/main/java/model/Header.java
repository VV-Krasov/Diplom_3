package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    private static final By userCabinetLink = By.xpath("//p[text()='Личный Кабинет']");
    private static final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private static final By stellarBurgersLogo = By.className("AppHeader_header__logo__2D0X2");

    private final WebDriver driver;

    public void clickUserCabinetLink() {driver.findElement(userCabinetLink).click();
    }
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }
    public void clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }
    public Header(WebDriver driver)
    {
        this.driver = driver;
    }
}
