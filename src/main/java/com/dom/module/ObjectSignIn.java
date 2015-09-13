package com.dom.module;

/**
 * Created by Alisi on 9/12/2015.
 * Page Object: Описание путей для Локаторов для отображения окна "Войти":
 * Url:
 * http://sitepokupok.ru/#openLoginDialog
 */
public class ObjectSignIn {

    // id="loginDialog" "Вход"
    public static String LoginDialog = "loginDialog";

    // id="User_login" "Логин или e-mail"
    public static String UserLogin = "User_login";

    // id="User_password" "Пароль"
    public static String UserPassword = "User_password";

    // id="login-submit" button "Войти"
    public static String LoginSubmit = "login-submit";

    // error "Неверное имя пользователя или пароль" проверяю как по заданию "Неверное имя или пароль"
    public static String ErrorUserLoginOrPassword = "//div[contains(@class, 'errorMessage') and text()='Неверное имя или пароль']";

    // "Я забыл пароль"
    public static String ForgotMyPassword = "//a[contains(@href, '#userrecoverDialog') and text()='Я забыл пароль']";

    // id="userrecoverDialog" "Восстановление доступа"
    public static String AccessRecovery = "userrecoverDialog";

    // id="User_email" "Ваш Email"
    public static String UserEmail = "//input[contains(@placeholder, 'Ваш Email')]";

    // id="recover-submit" "Восстановить"
    public static String Recovery = "recover-submit";

    // id="User_email_em_" error "Электронная почта не является правильным E-Mail адресом"
    public static String ErrorEmailAddress = "//div[contains(@class, 'errorMessage') and text()='Электронная почта не является правильным E-Mail адресом']";
}
