package com.bazaraki.autotests.elements;

import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class CheckBox extends WebElementFacade{

    public CheckBox(WebElement element, String elementName) {
        super(element, elementName);
    }

    @Override
    public void type(Object value) {
        if ((Boolean) value == isSelected()){
            return;
        }
        super.click();
    }
}
