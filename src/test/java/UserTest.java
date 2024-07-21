import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import pageobject.RegisterPage;
import pageobject.StartPage;

import static org.junit.Assert.assertEquals;

public class UserTest {
    WebDriverCreator webDriverCreator = new WebDriverCreator();

    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;
    private StartPage objStartPage;
    private WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void before() {
        driver = webDriverCreator.createWebDriver();


        UserData userData = new UserData();
        name = userData.getRandomName();
        email = userData.getRandomEmail();
        password = userData.getRandomPassword();
        objRegisterPage = new RegisterPage(driver);// создание объекта класса страницы регистрации
        objRegisterPage.openRegisterPage();
        objRegisterPage.createUser(name,email,password);
        objLoginPage = new LoginPage(driver);
        objStartPage = new StartPage(driver);// создание объекта класса main страницы
    }

    @Test
    @DisplayName("Вход в личный кабинет")
    public void personalAccountTest() {
        objLoginPage.login(email, password);
        objStartPage.checkPersonalArea();
        ProfilePage objProfilePage = new ProfilePage(driver);
        assertEquals("Entering was  Failed", "Выход", objProfilePage.checkLogInPersonalAccount());
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void checkExitTest() {
        objLoginPage.login(email, password);
        objStartPage.checkPersonalArea();
        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.clickExitButton();
        assertEquals("ExitFailed", "Войти", objLoginPage.checkLoginButton());
    }

    @Test
    @DisplayName("Выход на стартовую страницу из личного кабинета ")
    public void checkLogoTest() {
        objLoginPage.login(email, password);
        objStartPage.checkPersonalArea();
        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objStartPage.checkOrderButton());
    }

    @Test
    @DisplayName("Выход из личного кабинета в конструктор")
    public void checkConstructorTest() {
        objLoginPage.login(email, password);
        objStartPage.checkPersonalArea();
        ProfilePage objProfilePage = new ProfilePage(driver);
        objProfilePage.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objStartPage.checkOrderButton());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}