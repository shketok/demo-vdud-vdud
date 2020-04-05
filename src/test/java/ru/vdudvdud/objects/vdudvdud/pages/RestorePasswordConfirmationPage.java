package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static ru.vdudvdud.testdata.enums.urls.BaseUrls.FORGOT_PASSWORD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.localization.RestorePasswordLocalization;

/**
 * Класс, описывающий страницу ввода нового пароля после подтверждения восстановления пароля на почте
 */
public class RestorePasswordConfirmationPage extends BasePage {

    private static final String MAIN_ELEMENT =
        String.format("//form[contains(@action, '%s')]", FORGOT_PASSWORD.getUrlPart());
    private final SelenideElement PASSWORD = getMainElement().$x(".//input[@name='password']");
    private final SelenideElement ERROR_MSG_UNDER_PASSWORD = getMainElement()
        .$x(".//*[@class='wa-error-msg' and @data-name='password']");
    private final SelenideElement REPEAT_PASSWORD = getMainElement().$x(".//input[@name='password_confirm']");
    private final SelenideElement ERROR_MSG_UNDER_REPEAT_PASSWORD = getMainElement()
        .$x(".//*[@class='wa-error-msg' and @data-name='password_confirm']");

    private final SelenideElement CONFIRM = getMainElement().$x(".//input[@type='submit']");

    public RestorePasswordConfirmationPage() {
        super($x(MAIN_ELEMENT));
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


    public void checkThatRepeatPasswordErrorMsgIsVisible() {
        ERROR_MSG_UNDER_REPEAT_PASSWORD
            .shouldHave(Condition.exactText(RestorePasswordLocalization.DIFFERENT_PASSWORDS_MESSAGE.getValue()));
    }

    public void checkThatDifferentPasswordsErrorMsgIsVisible() {
        ERROR_MSG_UNDER_REPEAT_PASSWORD
            .shouldHave(Condition.exactText(RestorePasswordLocalization.DIFFERENT_PASSWORDS_MESSAGE.getValue()));
    }

    public void checkThatEmptyPasswordsErrorMsgIsVisible() {
        ERROR_MSG_UNDER_PASSWORD
            .shouldHave(Condition.exactText(RestorePasswordLocalization.EMPTY_PASSWORDS_MESSAGE.getValue()));
    }

    public void fillPassword(String password) {
        actions().sendKeys(PASSWORD.toWebElement(), password).build().perform();
    }

    public void fillRepeatPassword(String repeatPassword) {
        actions().sendKeys(REPEAT_PASSWORD.toWebElement(), repeatPassword).build().perform();
    }

    public void clickConfirm() {
        actions().click(CONFIRM.toWebElement()).build().perform();
    }

}
