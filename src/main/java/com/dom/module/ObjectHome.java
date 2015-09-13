package com.dom.module;

/**
 * Created by Alisi on 9/12/2015.
 * Page Object: Описание путей для Локаторов для отображения домашней страницы:
 * Url:
 * http://sitepokupok.ru/
 */
public class ObjectHome {

    // <li>Войти</li>
    public static String login = "//a[contains(@href, '#openLoginDialog')]";

    // <li>Закупки</li>
    public static String Purchase = "/html/body/div[1]/div[2]/div/div[2]/ul/li[1]/a";

    // <li>AccountUser</li>
    public static String AccountUser = "/html/body/div/div[1]/ul/li[1]/a";
}
