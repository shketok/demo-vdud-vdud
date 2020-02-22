package ru.vdudvdud.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.adaptors.selenide.utils.SmartWait;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс по работе со страницей регистрации нового пользователя
 */
public class SignUpPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $x("//div[@class='wa-form']");

    private static final SelenideElement FIRST_NAME = $x("//div[contains(@class, 'field-firstname')]//input");
    private static final SelenideElement LAST_NAME = $x("//div[contains(@class, 'field-lastname')]//input");
    private static final SelenideElement PASSWORD = $x("//div[contains(@class, 'field-password') and not(contains(@class, 'confirm'))]//input");
    private static final SelenideElement CONFIRM_PASSWORD = $x("//div[contains(@class, 'field-password_confirm')]//input");
    private static final SelenideElement EMAIL = $x("//div[contains(@class, 'field-email')]//input");

    private static final SelenideElement SIGN_UP_BUTTON = $x("//div[@class='wa-form']//form//input[@type='submit']");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }


    public void fillName(String value) {
        FIRST_NAME.setValue(value);
    }

    public void fillSurname(String value) {
        LAST_NAME.setValue(value);
    }

    public void fillPassword(String value) {
        PASSWORD.setValue(value);
    }

    public void fillRepeatPassword(String value) {
        CONFIRM_PASSWORD.setValue(value);
    }

    public void fillEmail(String value) {
        EMAIL.setValue(value);
    }

    public void confirmSignUp() {
        SIGN_UP_BUTTON.click();
    }

    public boolean isSignUpBtnVisible() {
        return !SmartWait.isElementNotInState(SIGN_UP_BUTTON, Condition.visible);
    }

    public void checkThatFirstNameInState(Condition condition) {
        FIRST_NAME.shouldBe(condition);
    }

    public void checkThatLastNameInState(Condition condition) {
        LAST_NAME.shouldBe(condition);
    }

    public void checkThatPasswordInState(Condition condition) {
        PASSWORD.shouldBe(condition);
    }

    public void checkThatConfirmPasswordInState(Condition condition) {
        CONFIRM_PASSWORD.shouldBe(condition);
    }

    public void checkThatEmailInState(Condition condition) {
        EMAIL.shouldBe(condition);
    }

    public void checkThatSignUpButtonInState(Condition condition) {
        SIGN_UP_BUTTON.shouldBe(condition);
    }
}
