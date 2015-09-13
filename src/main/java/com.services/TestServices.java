package com.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alisi on 9/12/2015.
 * Service - для поиска элементов по DOM на web странице
 */
public class TestServices {

    private WebDriver driver;

    public TestServices(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Получение элемента на web-странице через xPath-локатор
     * @param xPath
     * @return WebElement
     */
    public WebElement GetXPathWebElement(String xPath){
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Получение элемента на web-странице по id
     * @param id
     * @return WebElement
     */
    public WebElement GetIdWebElement(String id){
        return driver.findElement(By.id(id));
    }
}
