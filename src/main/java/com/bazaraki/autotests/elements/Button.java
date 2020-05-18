package com.bazaraki.autotests.elements;

import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class Button extends WebElementFacade{

    public Button(WebElement element, String elementName) {
        super(element, elementName);
    }

    @Override
    public void type(Object value) {
       throw new UnsupportedOperationException("Элемент не поддерживает операцию присваивания");
    }
}
