package com.bazaraki.autotests.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public interface IWebElementFacade extends WebElement, WrapsElement, org.openqa.selenium.interactions.internal.Locatable {
}
