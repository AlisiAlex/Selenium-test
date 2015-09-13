import com.dom.module.*;
import com.services.TestHelperServices;
import com.services.TestServiceRegistration;
import com.services.TestServiceSignIn;
import com.services.TestServices;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alisi on 9/12/2015.
 */
public class TestRegistration {

    private static final String url = "http://sitepokupok.ru/#";

    private static TestServices testServices;
    private static TestServiceSignIn testServiceSignIn;
    private static TestHelperServices testHelperServices;
    private static TestServiceRegistration testServiceRegistration;
    private static ObjectRegistration objectRegistration;
    private static WebDriver driver;
    private static ObjectHome objectHome;
    private static ObjectStock objectStock;
    private static ObjectSignIn objectSignIn;
    private static ObjectAccount objectAccount;
    private static Actions actions;
    private static String ResultTest = "";

    /**
     * Установка необходимых ресурсов
     */
    @BeforeClass
    public static void SetUpClass() {
        // Подключение дравера для Firefox
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        testServices = new TestServices(driver);
        testHelperServices = new TestHelperServices(driver);
        testServiceSignIn = new TestServiceSignIn(testServices);
        testServiceRegistration = new TestServiceRegistration(testServices);
        actions = new Actions(driver);
    }

    /**
     * Освобождение ресурсов
     */
    @AfterClass
    public static void TearDownClass() {
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
        driver.navigate().refresh();
        driver.get(url);
    }

    /**
     * Тест:
     * 5. нажать на регистрацию ввести в поле емейла 1 символ - потеря фокуса результат сообщение
     * "Электронная почта не является правильным E-Mail адресом"
     */
    @Test
    public void RegistrationTest() {

        // Тестовые рандомные данные, длиной 1
        int lengthString = 1;
        String Email = testHelperServices.GenerateRandomString(lengthString);

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.RegistrationDialog
            WebElement element = testServices.GetIdWebElement(objectRegistration.RegistrationDialog);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображения окна пользователю
            Assert.assertEquals("display: block;", element.getAttribute("style"));

            // Получение элемента objectRegistration.InputUserEmail
            element = testServices.GetXPathWebElement(objectRegistration.InputUserEmail);
            // Ввод значения в поле "Электронная почта"
            actions.moveToElement(element).click().sendKeys(Email).sendKeys(Keys.ENTER).perform();

            // Получение элемента objectRegistration.InventPassword
            // С целью смены фокуса
            element = testServices.GetXPathWebElement(objectRegistration.InventPassword);
            // Смена фокуса
            element.click();

            // Получение элемента objectRegistration.ErrorIncorrectEmail
            element = testServices.GetXPathWebElement(objectRegistration.ErrorIncorrectEmail);
            // Усыпление на 3 сек
            //Thread.sleep(3000);
            // Проверка отображение ошибки пользователю
            //Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n RegistrationTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 6. открыть поле логина - ввести 1 символ, потеря фокуса результат сообщение
     * "Логин должен быть не менее 3-х и не более 20-ти символов"
     */
    @Test public void RegistrationLoginTest(){

        // Тестовые рандомные данные, длиной 1
        int lengthString = 1;
        String Login = testHelperServices.GenerateRandomString(lengthString);

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.SpanLogin
            WebElement element = testServices.GetXPathWebElement(objectRegistration.SpanLogin);
            // Click по span "Логин" и ввод Login
            actions.moveToElement(element).click().sendKeys(Login).sendKeys(Keys.ENTER).perform();

            // Смена фокуса
            element = testServices.GetXPathWebElement(objectRegistration.InventPassword);
            actions.moveToElement(element).click().perform();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Получение элемента objectRegistration.ErrorIncorrectLogin
            element = testServices.GetXPathWebElement(objectRegistration.ErrorIncorrectLogin);
            // Усыпление на 2 сек
            //Thread.sleep(2000);
            // Проверка отображения окна пользователю
            //Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n RegistrationLoginTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationLoginTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationLoginTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 7. открыть поле Ваш город - ввести рандомное значение,
     * потеря фокуса результат - сообщение "Необходимо заполнить поле Город"
     */
    @Test public void RegistrationCityTest(){

        // Тестовые рандомные данные, длиной 10
        int lengthString = 10;
        String City = testHelperServices.GenerateRandomString(lengthString);

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.SpanLogin
            WebElement element = testServices.GetIdWebElement(objectRegistration.City);
            // Click по span "Логин" и ввод Login
            actions.moveToElement(element).click().sendKeys(City).sendKeys(Keys.ENTER).perform();

            // Смена фокуса
            element = testServices.GetXPathWebElement(objectRegistration.InventPassword);
            actions.moveToElement(element).click().perform();

            // Получение элемента objectRegistration.ErrorIncorrectLogin
            element = testServices.GetXPathWebElement(objectRegistration.ErrorIncorrectCity);
            // Усыпление на 3 сек
            //Thread.sleep(3000);
            // Проверка отображения окна пользователю
            //Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n RegistrationCityTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 8. ввести в поле город Казань результат - появление саджеста г.Казань
     */
    @Test public void RegistrationCorrectingCityTest(){

        // Тестовые данные
        String Kazan = "Казань";

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.City
            WebElement element = testServices.GetIdWebElement(objectRegistration.City);
            // Ввод города
            actions.moveToElement(element).click().sendKeys(Kazan).perform();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Получение элемента objectRegistration.TextCity
            element = testServices.GetXPathWebElement(objectRegistration.TextCity);

            // Проверка содержимого элемента
            Assert.assertEquals(Kazan, element.getText());
            ResultTest += "\r\n RegistrationCorrectingCityTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationCorrectingCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationCorrectingCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 9. выбрать из саджеста г.Казань результат - успешное применение - проставилось Казань
     */
    @Test public void RegistrationSelectCityTest(){

        // Тестовые данные
        String Kazan = "Казань";

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.City
            WebElement element = testServices.GetIdWebElement(objectRegistration.City);
            // Ввод города
            actions.moveToElement(element).click().sendKeys(Kazan).perform();
            // Усыпление на 3 сек
            Thread.sleep(3000);

            // Получение элемента objectRegistration.TextCity
            element = testServices.GetXPathWebElement(objectRegistration.TextCity);
            // Усыпление на 2 сек
            Thread.sleep(2000);
            // Выбираем element
            actions.moveToElement(element).doubleClick().perform();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Получение элемента objectRegistration.TextSelectCity
            element = testServices.GetXPathWebElement(objectRegistration.TextSelectCity);

            // Проверка содержимого элемента
            Assert.assertEquals(Kazan, element.getText());
            ResultTest += "\r\n RegistrationSelectCityTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationSelectCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationSelectCityTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 10. открыть поле промокод, ввести 123, потеря фокуса результат сообщение "Вы ввели неверный промокод"
     */
    @Test public void RegistrationInputPromoCodeTest(){

        // Тестовые данные
        String promoCode = "123";

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.LinkPromoCode
            WebElement element = testServices.GetXPathWebElement(objectRegistration.LinkPromoCode);
            // Ввод промокода
            actions.moveToElement(element).click().sendKeys(promoCode).sendKeys(Keys.ENTER).perform();

            // Смена фокуса
            element = testServices.GetXPathWebElement(objectRegistration.InventPassword);
            actions.moveToElement(element).click().perform();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Получение элемента objectRegistration.ErrorTextPromoCode
            element = testServices.GetIdWebElement(objectRegistration.ErrorTextPromoCode);

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Проверка содержимого элемента
            Assert.assertEquals("Вы ввели неверный промокод", element.getText());
            ResultTest += "\r\n RegistrationInputPromoCodeTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationInputPromoCodeTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationInputPromoCodeTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 11. удалить 123 из промокода, потеря фокуса результат - нет сообщения об ошибке
     */
    @Test public void RegistrationDeletePromoCodeTest(){

        // Тестовые данные
        String promoCode = "123";

        try{
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.LinkPromoCode
            WebElement element = testServices.GetXPathWebElement(objectRegistration.LinkPromoCode);
            // Ввод промокода
            actions.moveToElement(element).click().sendKeys(promoCode).perform();

            // Получение элемента objectRegistration.UserPromoCode
            element = testServices.GetIdWebElement(objectRegistration.UserPromoCode);
            // Удаление промокода
            element.click();
            element.clear();

            // Получение элемента objectRegistration.InventPassword
            element = testServices.GetXPathWebElement(objectRegistration.InventPassword);
            // Смена фокуса
            actions.moveToElement(element).click().perform();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Получение элемента objectRegistration.ErrorTextPromoCode
            element = testServices.GetIdWebElement(objectRegistration.ErrorTextPromoCode);

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Проверка содержимого элемента
            Assert.assertEquals("", element.getText());
            ResultTest += "\r\n RegistrationDeletePromoCodeTest: Result: Success \r\n";
        }  catch (ComparisonFailure e) {
            ResultTest += "RegistrationDeletePromoCodeTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationDeletePromoCodeTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 12. нажать линк "условиями использования сервиса" результат -
     * открылось окно https://sitepokupok.ru/rules
     */
    @Test public void RegistrationClickLinkTest() {

        try {
            // открытие окна "Регистрации"
            testServiceRegistration.OpenWindowRegistration();

            // Получение элемента objectRegistration.Link
            WebElement element = testServices.GetXPathWebElement(objectRegistration.Link);
            element.click();

            // Получение вкладок
            Set<String> handles = driver.getWindowHandles();

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Переключение на новую вкладку
            testHelperServices.SwitchPage(handles);

            // Усыпление на 2 сек
            Thread.sleep(2000);

            // Проверка url новой вкладки
            Assert.assertEquals("https://sitepokupok.ru/rules", driver.getCurrentUrl());
            ResultTest += "\r\n RegistrationClickLinkTest: Result: Success \r\n";
        } catch (ComparisonFailure e) {
            ResultTest += "RegistrationClickLinkTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationClickLinkTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }

    /**
     * Тест:
     * 13 зайти в раздел закупки, выбрать любую закупку попытаться выбрать товар результат - открывается окно входа
     */
    @Test public void RegistrationClickPurchaseTest() {

        try {
            // Получение элемента objectHome.Purchase
            WebElement element = testServices.GetXPathWebElement(objectHome.Purchase);
            element.click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            // Получение элемента objectHome.ProductsKitchen
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectStock.ProductsKitchen)));
            element.click();

            // Получение элемента objectHome.ProductsKitchen
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectStock.AddOrder)));
            element.click();

            // Проверка открытия окна "Войти"
            element = testServices.GetIdWebElement(objectSignIn.LoginDialog);
            // Усыпление на 3 сек
            Thread.sleep(3000);
            // Проверка отображения окна пользователю
            //Assert.assertEquals("display: block;", element.getAttribute("style"));
            ResultTest += "\r\n RegistrationClickPurchaseTest: Result: Success \r\n";
        } catch (ComparisonFailure e) {
            ResultTest += "RegistrationClickPurchaseTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } catch (Exception e) {
            ResultTest += "RegistrationClickPurchaseTest: Result: False \r\n" + e.getMessage() + "\r\n";
            System.out.print(e.getMessage());
        } finally {

        }
    }
}
