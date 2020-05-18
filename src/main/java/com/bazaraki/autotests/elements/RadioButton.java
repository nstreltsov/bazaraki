package com.bazaraki.autotests.elements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class RadioButton extends WebElementFacade{

    //TODO учитывать что список может быть древовидным
    private By items = By.xpath(".//li");
    private By item = By.xpath("./span");
    public RadioButton(WebElement element, String elementName) {
        super(element, elementName);
    }

    @Override
    public void type(Object value) {
        for (WebElement element: this.findElements(items)) {
            if (element.getText().equals(String.valueOf(value))){
                new WebElementFacade(element.findElement(item), element.getText()).click();
                return;
            }
        }
        Assert.fail(String.format("В выпадающем списке на найдено значение: %s", String.valueOf(value)));
    }
}
