package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.drivers.WebDriverFacade;
import com.bazaraki.autotests.page_factory.DefaultFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public abstract class AbstractPageObject {

    public AbstractPageObject(){
        PageFactory.initElements(new DefaultFieldDecorator(WebDriverFacade.getDriver()), this);
        isLoaded();
    }

    public AbstractPageObject(SearchContext searchContext){
        PageFactory.initElements(new DefaultFieldDecorator(searchContext), this);;
        isLoaded();
    }

    @Step("Получить текущий URL")
    public String getCurrentUrl(){
        return WebDriverFacade.getDriver().getCurrentUrl();
    }

    public abstract boolean isLoaded();
}
