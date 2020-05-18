package com.bazaraki.autotests.elements;

import com.bazaraki.autotests.drivers.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class WebElementFacade implements WebElement, WrapsElement  {

    protected WebElement element;

    //TODO в дальнейшем можно использовать для отчета, например "поле elementName заполняется таким-то значением или выполнено нажатие на поле elementName"
    private String elementName;

    public WebElementFacade(final WebElement element, final String elementName) {
        this.element = element;
        this.elementName = elementName;
    }

    /**
     * скролл до центра экрана
     */
    public void scrollIntoView() {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) WebDriverFacade.getDriver()).executeScript(scrollElementIntoMiddle, element);
    }

    public void click() {
        try {
            element.click();
        }catch (ElementClickInterceptedException e){
            ((JavascriptExecutor) WebDriverFacade.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        }
    }

    public void submit() {
        element.submit();
    }

    public void type(Object value){
        sendKeys(String.valueOf(value));
    }

    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    public void clear() {
        element.clear();
    }

    public String getTagName() {
        return element.getTagName();
    }

    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public String getText() {
        return element.getText();
    }

    public <T extends WebElement> List<T> findElements(By by) {
        return element.findElements(by);
    }

    public <T extends WebElement> T findElement(By by) {
        return element.findElement(by);
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public Point getLocation() {
        return element.getLocation();
    }

    public Dimension getSize() {
        return element.getSize();
    }

    public Rectangle getRect() {
        return element.getRect();
    }

    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }


    public WebElement getWrappedElement() {
        return element;
    }
}
