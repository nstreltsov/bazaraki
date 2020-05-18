package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import com.bazaraki.autotests.elements.CheckBox;
import com.bazaraki.autotests.elements.StaticText;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class LoginPage extends AbstractPageObject{

    @FindBy(name = "confirm_terms")
    private CheckBox confirm_terms;

    @FindBy(xpath = ".//button[text()='Continue']")
    private Button continueBtn;

    @FindBy(xpath = ".//*[contains(@class, 'validation__error')]/p")
    private StaticText error;

    @Step("Согласиться с условиями")
    public void confirmTerms(){
        confirm_terms.type(true);
    }

    @Step("Отправить форму")
    public void submit(){
        continueBtn.click();
    }

    @Step("Получить сообщение об ошибке")
    public String getError(){
        return error.getText();
    }

    public boolean isLoaded() {
        return true;
    }
}
