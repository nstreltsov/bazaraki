package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import com.bazaraki.autotests.elements.StaticText;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class OfferPage extends AbstractPageObject{

    @FindBy(xpath = ".//*[@class='shell item']//div[@class='item__details']")
    private StaticText details;

    @FindBy(xpath = "//*[@class='clearfix']//*[contains(@class, 'favorites') and @data-id]")
    private Button favorites;

    @Step("Добавить в избранное")
    public LoginPage toFavorites(){
        favorites.scrollIntoView();
        favorites.click();
        return new LoginPage();
    }

    @Step("Получить ID предложения")
    public String getId(){
        Pattern pattern = Pattern.compile(".*id\\s(\\d*)");
        Matcher matcher = pattern.matcher(details.getText());
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public boolean isLoaded() {
        return details.isDisplayed();
    }
}
