package com.bazaraki.autotests.elements;

import com.bazaraki.autotests.drivers.WebDriverFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class ComboBox extends WebElementFacade{

    private By items = By.xpath(".//ul[@class='popover__list']/li");

    public ComboBox(WebElement element, String elementName) {
        super(element, elementName);
    }

    @Override
    public void type(Object value) {
        scrollIntoView();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverFacade.getDriver();
        js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", getWrappedElement());
        for (WebElement element: WebDriverFacade.getDriver().findElements(items)) {
            ((JavascriptExecutor) WebDriverFacade.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            if (element.getText().equals(String.valueOf(value))){
                new WebElementFacade(element, element.getText()).click();
                 return;
            }
        }
        Assert.fail(String.format("В выпадающем списке на найдено значение: %s", String.valueOf(value)));
    }
}
