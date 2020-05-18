package com.bazaraki.autotests.page_factory;

import com.bazaraki.autotests.elements.WebElementFacade;
import com.bazaraki.autotests.page_factory.handlers.CollectionProxyHandler;
import com.bazaraki.autotests.page_factory.handlers.ElementProxyHandler;
import com.bazaraki.autotests.pages.AbstractPageObject;
import com.bazaraki.autotests.pages.CollectionObject;
import org.junit.Assert;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.util.List;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class DefaultFieldDecorator implements FieldDecorator {

    SearchContext searchContext;

    public DefaultFieldDecorator(SearchContext searchContext){
        this.searchContext = searchContext;
    }

    public Object decorate(ClassLoader classLoader, Field field) {
        try {
            if (PageFactoryUtils.isCollectionElement(field)) {
                return decorateElementCollection(classLoader, field);
            }
            /*
            if (PageFactoryUtils.isListElement(field)){
                return proxyElementList(classLoader, field);
            }*/
            if (PageFactoryUtils.isElement(field)){
                return decorateWebElement(classLoader, field);
            }
            return null;
        } catch (ClassCastException e) {
            e.printStackTrace();
            Assert.fail("Не удалось создать прокси для элемента: " + field.getName());
            return null;
        }
    }

    private  <T extends WebElementFacade> T decorateWebElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = proxyWebElement(loader, field);
        return PageFactoryUtils.createElement((Class<T>) field.getType(), elementToWrap,field.getName());
    }

    private <T extends AbstractPageObject> List<T> decorateElementCollection(ClassLoader loader, Field field) {
        return proxyElementCollection(loader, field);
    }

    protected <T extends AbstractPageObject> List<T> proxyElementCollection(ClassLoader loader, Field field) {
        ElementLocator locator = new DefaultElementLocator(searchContext, field);
        InvocationHandler handler = new CollectionProxyHandler<CollectionObject>(PageFactoryUtils.getGenericParameterClass(field), locator, field.getName());
        return ProxyFactory.createElementCollectionProxy(loader, handler);
    }

    private WebElement proxyWebElement(ClassLoader loader, Field field) {
        ElementLocator locator = new DefaultElementLocator(searchContext, field);
        InvocationHandler handler = new ElementProxyHandler(locator, field.getName());
        return ProxyFactory.createWebElementProxy(loader, handler);
    }
}
