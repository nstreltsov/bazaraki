package com.bazaraki.autotests.page_factory;

import com.bazaraki.autotests.elements.WebElementFacade;
import com.bazaraki.autotests.exceptions.ElementCreationError;
import com.bazaraki.autotests.pages.ICollectionObject;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import java.lang.reflect.*;
import java.util.List;

import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class PageFactoryUtils {

    public static boolean isElement(Field field) {
        return !field.getType().isAssignableFrom(List.class) && isElement(field.getType());
    }

    public static boolean isCollectionElement(Field field) {
        return field.getType().isAssignableFrom(List.class) && ICollectionObject.class.isAssignableFrom(getGenericParameterClass(field));

    }

    public static boolean isListElement(Field field) {
        return field.getType().isAssignableFrom(List.class) && WebElementFacade.class.isAssignableFrom(getGenericParameterClass(field));
    }

    public static boolean isElement(Class<?> clazz) {
        return WebElementFacade.class.isAssignableFrom(clazz);
    }


    public static <T extends WebElementFacade> T createElement(Class<T> elementClass, WebElement elementToWrap,
                                                               String elementName) {
        try {
            T instance = newInstance(elementClass, elementToWrap, elementName);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new ElementCreationError(e);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Object... args) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
            Class outerClass = clazz.getDeclaringClass();
            Object outerObject = outerClass.newInstance();
            return invokeConstructor(clazz, Lists.asList(outerObject, args).toArray());
        }
        return invokeConstructor(clazz, args);
    }

    public static Class getGenericParameterClass(Field field) {
        Type genericType = field.getGenericType();
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }
}
