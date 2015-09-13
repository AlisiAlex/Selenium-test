package com.dom.module;

/**
 * Created by Alisi on 9/12/2015.
 * Page Object: Описание путей для Локаторов для отображения домашней страницы:
 * Url:
 * http://sitepokupok.ru/#registerDialog
 */
public class ObjectRegistration {

    // <li>Зарегистрироваться</li>
    public static String RegistrationLi = "//a[contains(@href, '#registerDialog')]";

    // id="registerDialog" "registerDialog"
    public static String RegistrationDialog = "registerDialog";

    // id="User_email" input "Электронная почта"
    public static String InputUserEmail = "//input[contains(@placeholder, 'Электронная почта')]";

    // id="User_password" input "Придумайте пароль"
    public static String InventPassword = "//input[contains(@placeholder, 'Придумайте пароль')]";

    // id="User_email_em_" error "Электронная почта не является правильным E-Mail адресом"
    public static String ErrorIncorrectEmail = "//div[contains(@id, 'User_email_em_') and text()='Электронная почта не является правильным E-Mail адресом']";

    // <span> Ваш логин </span>
    public static String SpanLogin = "//form[@id='register-form']/table/tbody/tr[3]/td/span[2]";

    // id="User_login_em_" error "Логин должен быть не менее 3-х и не более 20-ти символов"
    public static String ErrorIncorrectLogin = "//div[contains(@id,'User_login_em_') and text()='Логин должен быть не менее 3-х и не более 20-ти символов']";

    // id="city-name-reg-view" "Ваш город"
    public static String City = "city-name-reg-view";

    // id="User_profileCityName_em_" error "Необходимо заполнить поле Город"
    public static String ErrorIncorrectCity = "//div[contains(@id,'User_profileCityName_em_') and text()='Необходимо заполнить поле Город']";

    // class="autocomplete-suggestion"
    public static String TextCity = "/html/body/div[3]/div/strong";

    // id="city-name-reg-view" "Казань"
    public static String TextSelectCity = "/html/body/div[1]/div[6]/div/div[4]/div[2]/div[2]/form/table/tbody/tr[4]/td/span/span";

    // class="pseudolink promocodeinputlink" "У меня есть промокод"
    public static String LinkPromoCode = "/html/body/div[1]/div[6]/div/div[4]/div[2]/div[2]/form/table/tbody/tr[5]/td/span";

    // id="User_promoCode_em_" "Вы ввели неверный промокод"
    public static String ErrorTextPromoCode = "User_promoCode_em_";

    // id="User_promoCode" "Промокод"
    public static String UserPromoCode = "User_promoCode";

    // Link "условиями использования сервиса"
    public static String Link = "//a[contains(@href,'/rules') and text()='условиями использования сервиса']";
}
