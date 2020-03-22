package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;
import static ru.vdudvdud.testdata.enums.urls.BaseUrls.FORGOT_PASSWORD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

/**
 * Класс, описывающий страницу ввода нового пароля после подтверждения восстановления пароля на почте
 */
public class RestorePasswordConfirmationPage extends BasePage {

    private static final SelenideElement MAIN_ELEMENT = $x(
        String.format("//form[contains(@action, '%s')]", FORGOT_PASSWORD.getUrlPart()));
    private static final SelenideElement PASSWORD = MAIN_ELEMENT.$x(".//input[@name='password']");
    private static final SelenideElement REPEAT_PASSWORD = MAIN_ELEMENT.$x(".//input[@name='password_confirm']");
    private static final SelenideElement CONFIRM = MAIN_ELEMENT.$x(".//input[@type='submit']");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void checkThatPasswordInState(Condition condition) {
        PASSWORD.shouldBe(condition);
    }

    public void checkThatRepeatPasswordInState(Condition condition) {
        REPEAT_PASSWORD.shouldBe(condition);
    }

    public void checkThatConfirmInState(Condition condition) {
        CONFIRM.shouldBe(condition);
    }

    public void fillPassword(String password) {
        PASSWORD.setValue(password);
    }

    public void fillRepeatPassword(String repeatPassword) {
        REPEAT_PASSWORD.setValue(repeatPassword);
    }

    public void clickConfirm() {
        CONFIRM.click();
    }

}
