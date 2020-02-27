package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static ru.vdudvdud.testdata.enums.urls.BaseUrls.FORGOT_PASSWORD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.localization.RestorePasswordLocalization;

/**
 * Класс, описывающий страницу ввода нового пароля после подтверждения восстановления пароля на почте
 */
public class RestorePasswordConfirmationPage extends BasePage {

    private static final SelenideElement MAIN_ELEMENT = $x(
        String.format("//form[contains(@action, '%s')]", FORGOT_PASSWORD.getUrlPart()));
    private static final SelenideElement PASSWORD = MAIN_ELEMENT.$x(".//input[@name='password']");
    private static final SelenideElement ERROR_MSG_UNDER_PASSWORD = MAIN_ELEMENT
        .$x(".//*[@class='wa-error-msg' and @data-name='password']");
    private static final SelenideElement REPEAT_PASSWORD = MAIN_ELEMENT.$x(".//input[@name='password_confirm']");
    private static final SelenideElement ERROR_MSG_UNDER_REPEAT_PASSWORD = MAIN_ELEMENT
        .$x(".//*[@class='wa-error-msg' and @data-name='password_confirm']");

    private static final SelenideElement CONFIRM = MAIN_ELEMENT.$x(".//input[@type='submit']");

    public RestorePasswordConfirmationPage() {
        super($(MAIN_ELEMENT));
    }

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


    public void checkThatRepeatPasswordErrorMsgIsVisible() {
        Assert.assertEquals(RestorePasswordLocalization.DIFFERENT_PASSWORDS_MESSAGE.getValue(),
            ERROR_MSG_UNDER_REPEAT_PASSWORD.getText(),
            "Ожидаемое сообщение о несовпадение паролей не соответвует фактическому");
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
