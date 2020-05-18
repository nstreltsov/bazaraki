 package com.bazaraki.autotests.page_factory.handlers;

 import org.openqa.selenium.support.pagefactory.ElementLocator;
 import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

 import java.lang.reflect.Method;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class ElementProxyHandler extends LocatingElementHandler {

        private final String name;

    public ElementProxyHandler(ElementLocator locator, String name) {
        super(locator);
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }
        return super.invoke(o, method, objects);
    }

}
