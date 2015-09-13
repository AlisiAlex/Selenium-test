package com.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Driver;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alisi on 9/12/2015.
 * Service - содержит вспомогательные методы
 */
public class TestHelperServices {

    private static final String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static WebDriver driver;

    public TestHelperServices(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Генерирует строку содержащую случайные символы
     * @param length - длина строки не может быть меньше 1
     * @return generated String
     */
    public static String GenerateRandomString(int length){

        if(length < 1)
            throw new IllegalArgumentException("length < 1");

        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(string.length());
            char ch = string.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * Переключение вкладки
     * @param handles
     */
    public static void SwitchPage(Set<String> handles){
        for(String handle : handles) {
            if(handle != driver.getWindowHandle()) {
                driver.switchTo().window(handle);
                driver.switchTo().activeElement();
            }
        }
    }

    /**
     * Формирование отчёта по тестам
     * @param string
     * @throws IOException
     */
    public static void WriteResultTest(String string) {
        try
        {
            FileWriter writer = new FileWriter("\\ResultTest.txt", true);
            writer.append("\r\n" + Calendar.getInstance().getTime().toString() + "\r\n");
            writer.write(string);
            writer.append("\r\n");
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
