package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class MainPage extends AbstractPageObject{

    @FindBy(xpath = ".//*[contains(@class, 'index-search') and text()='Filters']")
    private Button filters;

    @Step("Открыть фильтр")
    public FiltersPage openFilters(){
        filters.click();
        return new FiltersPage();
    }

    public boolean isLoaded() {
        return filters.isDisplayed();
    }
}
