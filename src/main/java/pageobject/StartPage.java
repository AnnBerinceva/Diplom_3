package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {
    private final WebDriver driver;
    private final static String startPage = "https://stellarburgers.nomoreparties.site/";

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
    private final By loginAccountButton = By.xpath(".//*[text() = 'Войти в аккаунт']"); //кнопка "Войти в аккаунт"
    private final By personalAreaButton = By.xpath(".//*[text() = 'Личный Кабинет']"); //кнопка "Личный Кабинет"

    private final By bunsButton = By.xpath(".//span[text()='Булки']/.."); //вкладка "Булки"

    private final By sauceButton = By.xpath("//span[text()='Соусы']/.."); //вкладка "Соусы"
    private final By nameSauce = By.xpath(".//section[1]/div[2]/h2[2]"); //название "Соусы"

    private final By fillingButton = By.xpath("//span[text()='Начинки']/.."); //вкладка "Начинки"
    private final By nameFilling = By.xpath(".//section[1]/div[2]/h2[3]"); //название "Начинки"

    private final By orderButton = By.className("button_button__33qZ0"); //кнопка "Оформить заказ"
    private final By fluorescentBun = By.xpath(".//p[text() = 'Флюоресцентная булка R2-D3']");

    @Step("Открыть главную страницу")
    public void openStartPage (){
        driver.get(startPage);
    }

    @Step("Войти в аккаунт на главной странице")
    public void checkAuthorization() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginAccountButton));

        Object elementLoginAccountButton = driver.findElement(loginAccountButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLoginAccountButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginAccountButton));

        driver.findElement(loginAccountButton).click();
    }

    @Step("Войти в личный кабинет")
    public void checkPersonalArea() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(personalAreaButton));

        driver.findElement(personalAreaButton).click();
    }

    @Step("Загрузка главной страницы, отображение кнопки <Оформить заказ>")
    public Object checkOrderButton () {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));

        return textButton.getText();
    }

    @Step("Открытие вкладки с булками")
    public boolean checkBuns() {
        driver.findElement(fillingButton).click(); //перешли на вкладку начинки
        driver.findElement(bunsButton).click(); //кликаем по булкам
        return driver.findElement(fluorescentBun).isDisplayed(); //проверяем, что отображается Флюоресцентная булка
    }
    @Step("Открытие вкладки с соусами")
    public boolean checkSauce() {
        driver.findElement(sauceButton).click();
        return driver.findElement(nameSauce).isDisplayed();
    }

    @Step("Открытие вкладки с начинками")
    public boolean checkFillings() {
        driver.findElement(fillingButton).click();
        return driver.findElement(nameFilling).isDisplayed();
    }
}