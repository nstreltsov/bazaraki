package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import com.bazaraki.autotests.elements.ComboBox;
import com.bazaraki.autotests.elements.TextInput;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class FiltersPage extends AbstractPageObject{

    @FindBy(xpath = ".//span[text()='Search']//following-sibling::input")
    private TextInput searchField;

    @FindBy(xpath = ".//li[./span[text()='City']]")
    private Button city;

    @FindBy(xpath = ".//*[@data-name='price_min']")
    private ComboBox priceMin;

    @FindBy(xpath = ".//*[@data-name='price_max']")
    private ComboBox priceMax;

    @FindBy(xpath = ".//div[contains(@class, 'footer') and normalize-space()='Search']")
    private Button search;

    public boolean isLoaded() {
        return searchField.isDisplayed();
    }

    @Step("Поиск по фильтру текст: [{0}] минимальная цена: [{1}] максимальная цена [{2}] город [{3}]")
    public ResultsPage filter(String text, String priceMin, String priceMax, String district){
        searchField.type(text);
        this.priceMin.type(priceMin);
        this.priceMax.type(priceMax);
        city.click();
        ChooseDistrictPage chooseDistrictPage = new ChooseDistrictPage();
        chooseDistrictPage.choose(district);
        search.click();
        return new ResultsPage();
    }
}