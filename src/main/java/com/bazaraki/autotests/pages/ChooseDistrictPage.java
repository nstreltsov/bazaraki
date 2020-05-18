package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import com.bazaraki.autotests.elements.RadioButton;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class ChooseDistrictPage extends AbstractPageObject{

    @FindBy(xpath = "//ul[contains(@class, 'cities-regions__list')]")
    private RadioButton district;

    @FindBy(xpath = ".//button[text()='Submit']")
    private Button submit;

    public void choose(String district){
        this.district.type(district);
        submit.click();
    }
    @Override
    public boolean isLoaded() {
        return false;
    }
}
