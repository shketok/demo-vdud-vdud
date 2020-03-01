package ru.vdudvdud.page.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.adaptors.selenide.utils.SmartWait;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс по работе со страницей регистрации нового пользователя
 */
public class SignUpPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = "//div[@class='wa-form']";

    private SelenideElement firstName = getMainElement().$x(".//div[contains(@class, 'field-firstname')]//input");
    private SelenideElement lastName = getMainElement().$x(".//div[contains(@class, 'field-lastname')]//input");
    private SelenideElement password = getMainElement().$x(".//div[contains(@class, 'field-password') and not(contains(@class, 'confirm'))]//input");
    private SelenideElement confirmPassword = getMainElement().$x(".//div[contains(@class, 'field-password_confirm')]//input");
    private SelenideElement email = getMainElement().$x(".//div[contains(@class, 'field-email')]//input");
    private SelenideElement signUpBtn = getMainElement().$x(".//form//input[@type='submit']");

    /**
     * Конструктор основного элемента.
     */
    public SignUpPage() {
        super($x(MAIN_ELEMENT_LOC));
    }


    public void fillName(String value) {
        firstName.setValue(value);
    }

    public void fillSurname(String value) {
        lastName.setValue(value);
    }

    public void fillPassword(String value) {
        password.setValue(value);
    }

    public void fillRepeatPassword(String value) {
        confirmPassword.setValue(value);
    }

    public void fillEmail(String value) {
        email.setValue(value);
    }

    public void confirmSignUp() {
        signUpBtn.click();
    }

    public boolean isSignUpBtnVisible() {
        return !SmartWait.isElementNotInState(signUpBtn, Condition.visible);
    }

    public void checkThatFirstNameInState(Condition condition) {
        firstName.shouldBe(condition);
    }

    public void checkThatLastNameInState(Condition condition) {
        lastName.shouldBe(condition);
    }

    public void checkThatPasswordInState(Condition condition) {
        password.shouldBe(condition);
    }

    public void checkThatConfirmPasswordInState(Condition condition) {
        confirmPassword.shouldBe(condition);
    }

    public void checkThatEmailInState(Condition condition) {
        email.shouldBe(condition);
    }

    public void checkThatSignUpButtonInState(Condition condition) {
        signUpBtn.shouldBe(condition);
    }
}
