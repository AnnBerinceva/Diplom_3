import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.StartPage;

import static org.junit.Assert.assertTrue;
public class ConstructorTest {
    WebDriverCreator webDriverCreator = new WebDriverCreator();
    private StartPage objStartPage;
    private WebDriver driver;
    @Before
    public void before() {
        driver = webDriverCreator.createWebDriver();
        objStartPage = new StartPage(driver);
    }

    @Test
    @DisplayName("Открытие раздела <Конструктор>")
    public void checkSauce() {
        objStartPage.openStartPage();
        assertTrue("Ошибка", objStartPage.checkSauce());
    }

    @Test
    @DisplayName("Открытие раздела <Булки>")
    public void checkBuns() {
        objStartPage.openStartPage();
        assertTrue("Ошибка", objStartPage.checkBuns());
    }

    @Test
    @DisplayName("Раздел <Конструктор> на главной странице")
    public void checkFillings() {
        objStartPage.openStartPage();
        assertTrue("Ошибка", objStartPage.checkFillings());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}