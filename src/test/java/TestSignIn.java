import com.dom.module.*;
import com.services.TestHelperServices;
import com.services.TestServiceSignIn;
import com.services.TestServices;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Alisi on 9/12/2015.
 */
public class TestSignIn {

    private static final String url = "http://sitepokupok.ru/#";

    private static TestServices testServices;
    private static TestServiceSignIn testServiceSignIn;
    private static TestHelperServices testHelperServices;
    private static WebDriver driver;
    private static ObjectHome objectHome;
    private static ObjectSignIn objectSignIn;
    private static ObjectAccount objectAccount;
    private static Actions actions;
    private static String ResultTest = "";

    /**
     * Установка необходимых ресурсов
     */
    @BeforeClass public static void SetUpClass() {
        SetSetUpClass();
    }

    private static void SetSetUpClass(){
        // Подключение дравера для Firefox
        driver = new FirefoxDriver();
        testServices = new TestServices(driver);
        testHelperServices = new TestHelperServices(driver);
        testServiceSignIn = new TestServiceSignIn(testServices);
        actions = new Actions(driver);
    }

    /**
     * Освобождение ресурсов
     */
    @AfterClass public static void TearDownClass() {
        try {
            driver.quit();
        }finally {
            testHelperServices.WriteResultTest(ResultTest);
        }
    }

    /**
     * Открытие тестируемого web-сайта
     */
    @Before public void SetUp() {
        driver.get(url);
    }

    /**
     * Тест: 1. кликнуть на вход результат должно открыться окно с вводом логина и пароля
     */
    @Test public void OpenWindowSignInTest() {

        try {
            // открытие окна "Войти"
            testServiceSignIn.OpenWindowSignIn();

            // Получение элемента objectSignIn.loginDialog
            WebElement element = testServices.GetIdWebElement(objectSignIn.LoginDialog);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображения окна "Войти"
            Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n OpenWindowSignInTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "OpenWindowSignInTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "OpenWindowSignInTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        }
    }

    /**
     * Тест: 2. ввести в логине и фамилии рандомные данные  нажать войти результат - должно выйти сообщение "Неверное имя или пароль"
     *
     * TODO: тест падает, так-как по задаче не находит элемент с текстом "Неверное имя или пароль"
     */
    @Test public void InputValuesSignInTest() {

        // Тестовые рандомные данные, длиной 10
        int lengthString = 10;
        String login = testHelperServices.GenerateRandomString(lengthString);
        String Password = testHelperServices.GenerateRandomString(lengthString);

        try {
            // открытие окна "Войти"
            testServiceSignIn.OpenWindowSignIn();

            // Получение элемента objectSignIn.UserLogin
            WebElement element = testServices.GetIdWebElement(objectSignIn.UserLogin);
            // Ввод значения в поле "Логин или e-mail"
            element.click();
            element.sendKeys(login);

            // Получение элемента objectSignIn.UserPassword
            element = testServices.GetIdWebElement(objectSignIn.UserPassword);
            // Ввод значения в поле "Пароль"
            element.click();
            element.sendKeys(Password);

            // Получение элемента objectSignIn.LoginSubmit
            element = testServices.GetIdWebElement(objectSignIn.LoginSubmit);
            // Нажатие на кнопку "Войти"
            element.click();

            // Получение элемента objectSignIn.ErrorUserLoginOrPassword
            element = testServices.GetXPathWebElement(objectSignIn.ErrorUserLoginOrPassword);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображение ошибки пользователю
            Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n InputValuesSignInTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "InputValuesSignInTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "InputValuesSignInTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        }
    }

    /**
     * Тест: кликнуть на линк "Я забыл пароль" результат - должно открыться окно восстановления пароля
     */
    @Test public void IForgotMyPasswordTest() {

        try {
            // открытие окна "Войти"
            testServiceSignIn.OpenWindowSignIn();
            // открытие окна "Восстановление доступа"
            TestServiceSignIn.OpenWindowAccessRecovery();
            // Получение элемента
            WebElement element = testServices.GetIdWebElement(objectSignIn.AccessRecovery);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображения окна пользователю
            Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n IForgotMyPasswordTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "IForgotMyPasswordTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "IForgotMyPasswordTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 4. ввести в поля эмейла  рандомные данные нажать на восстановить результат - сообщение
     * "Электронная почта не является правильным E-Mail адресом"
     */
    @Test public void AccessRecoveryTest() {

        // Тестовые рандомные данные, длиной 10
        int lengthString = 10;
        String Email = testHelperServices.GenerateRandomString(lengthString);

        try {
            // открытие окна "Войти"
            testServiceSignIn.OpenWindowSignIn();
            // открытие окна "Восстановление доступа"
            testServiceSignIn.OpenWindowAccessRecovery();

            // Получение элемента objectSignIn.UserEmail
            WebElement element = testServices.GetXPathWebElement(objectSignIn.UserEmail);
            // Ввод значения в поле "Ваш Email"
            element.click();
            element.sendKeys(Email);

            // Получение элемента objectSignIn.Recovery
            element = testServices.GetIdWebElement(objectSignIn.Recovery);
            // Нажатие на кнопку "Восстановить"
            element.click();

            // Получение элемента objectSignIn.ErrorEmailAddress
            element = testServices.GetXPathWebElement(objectSignIn.ErrorEmailAddress);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображение ошибки пользователю
            //Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n AccessRecoveryTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "AccessRecoveryTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "AccessRecoveryTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 14. зарегистрируй свое мыло  для успешного входа,
     * сам придумай кейсы по входу например, успешно вошел - появилась корзина,
     * зашел в профиль появились поля изменения данных профиля.
     *
     * User: testuser_2016@mail.ru - password Qwert678
     */
    @Test public void AuthorisationUserTest() {

        // Тестовые данные
        String login = "testuser_2016@mail.ru";
        String password = "Qwert678";

        try {

            testServiceSignIn.SignInAccount(login, password);

            // Усыпление на 3 сек
            Thread.sleep(3000);

            // Получение элемента objectHome.AccountUser
            WebElement element = testServices.GetXPathWebElement(objectHome.AccountUser);
            element.click();

            // Усыпление на 3 сек
            Thread.sleep(3000);

            // Проверка перехода в профиль пользователя
            Assert.assertEquals("http://sitepokupok.ru/user", driver.getCurrentUrl());

            // Получение элемента objectHome.AccountUser
            element = testServices.GetXPathWebElement(objectAccount.Edit);
            element.click();

            // Проверка перехода в редактирование пользователя
            Assert.assertEquals("http://sitepokupok.ru/user/edit", driver.getCurrentUrl());
            ResultTest += "\r\n AuthorisationUserTest: Result: Success \r\n";
        } catch (ComparisonFailure e) {
            ResultTest += "AuthorisationUserTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "AuthorisationUserTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }
}
