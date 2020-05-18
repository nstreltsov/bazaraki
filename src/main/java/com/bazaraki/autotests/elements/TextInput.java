package com.bazaraki.autotests.elements;

import com.bazaraki.autotests.drivers.WebDriverFacade;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class TextInput extends WebElementFacade{

    public TextInput(WebElement element, String elementName) {
        super(element, elementName);
    }

    @Override
    public void type(Object value) {
       super.type(value);
       ((AppiumDriver)WebDriverFacade.getDriver()).hideKeyboard();
    }
}
