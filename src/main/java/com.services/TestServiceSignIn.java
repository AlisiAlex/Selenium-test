package com.services;

import com.dom.module.ObjectHome;
import com.dom.module.ObjectSignIn;
import org.openqa.selenium.WebElement;

/**
 * Created by Alisi on 9/12/2015.
 * Service - взаимодействие с окном "Войти"
 */
public class TestServiceSignIn {

    private static ObjectHome objectHome;
    private static ObjectSignIn objectSignIn;
    private static TestServices services;

    public TestServiceSignIn(TestServices services){
        this.services = services;
    }

    /**
     * открытие окна "Войти"
     */
    public static void OpenWindowSignIn(){
        // Получение элемента objectHome.login
        WebElement element = services.GetXPathWebElement(objectHome.login);
        // Click по ссылке objectHome.login
        element.click();
    }

    /**
     * открытие окна "Восстановление доступа"
     */
    public static void OpenWindowAccessRecovery(){
        // Получение элемента objectSignIn.ForgotMyPassword
        WebElement element = services.GetXPathWebElement(objectSignIn.ForgotMyPassword);
        // Click по ссыдке objectSignIn.ForgotMyPassword
        element.click();
    }

    /**
     * Вход в аккаунт пользователя
     * @param login
     * @param password
     */
    public static void SignInAccount(String login, String password){
        // открытие окна "Войти"
        OpenWindowSignIn();

        // Получение элемента objectSignIn.UserLogin
        WebElement element = services.GetIdWebElement(objectSignIn.UserLogin);
        // Ввод значения в поле "Логин или e-mail"
        element.click();
        element.sendKeys(login);

        // Получение элемента objectSignIn.UserPassword
        element = services.GetIdWebElement(objectSignIn.UserPassword);
        // Ввод значения в поле "Пароль"
        element.click();
        element.sendKeys(password);

        // Получение элемента objectSignIn.LoginSubmit
        element = services.GetIdWebElement(objectSignIn.LoginSubmit);
        // Нажатие на кнопку "Войти"
        element.click();
    }
}
