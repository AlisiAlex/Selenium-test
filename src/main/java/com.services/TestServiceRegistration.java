package com.services;

import com.dom.module.ObjectRegistration;
import org.openqa.selenium.WebElement;

/**
 * Created by Alisi on 9/12/2015.
 */
public class TestServiceRegistration {

    private TestServices services;
    private ObjectRegistration objectRegistration;

    public TestServiceRegistration(TestServices services){
        this.services = services;
    }

    public void OpenWindowRegistration(){
        // Получение элемента objectRegistration.RegistrationLi
        WebElement element = services.GetXPathWebElement(objectRegistration.RegistrationLi);
        // Click по ссылке objectHome.login
        element.click();
    }
}
