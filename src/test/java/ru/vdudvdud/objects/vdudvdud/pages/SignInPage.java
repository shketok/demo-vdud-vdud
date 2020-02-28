package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.CommonDataNames;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;

/**
 * Класс описывающий страницу входа на сайт. Авторизация.
 */
public class SignInPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $x("//div[contains(@class, 'signing__sign-in')]");

    private static final String AUTH_LINKS_PATTERN = "//div[@class='auth-links']//a[contains(@href, '%s')]";
    private static final String ERROR_MSG_PATTERN = "//*[@class='wa-error-msg' and @data-name='%s']";

    private static final SelenideElement LOGIN = $x("//input[@name='login']");
    private static final SelenideElement LOGIN_IS_MANDATORY_MSG = $x(String.format(ERROR_MSG_PATTERN,
        CommonDataNames.LOGIN));
    private static final SelenideElement PASSWORD = $x("//input[@name='password']");
    private static final SelenideElement PASSWORD_IS_MANDATORY_MSG = $x(String.format(ERROR_MSG_PATTERN, CommonDataNames.PASSWORD));
    private static final SelenideElement TITLE = $x("//*[@class='signing__title']");
    private static final SelenideElement CONFIRM = $x("//input[@type='submit']");
    private static final SelenideElement FORGOT_PASSWORD = $x(String.format(AUTH_LINKS_PATTERN, BaseUrls.FORGOT_PASSWORD.getUrlPart()));
    private static final SelenideElement REGISTRATION = $x(String.format(AUTH_LINKS_PATTERN, BaseUrls.SIGN_UP.getUrlPart()));
    private static final SelenideElement EITHER_PASSWORD_OR_EMAIL_INCORRECT_MSG = $x(String.format(ERROR_MSG_PATTERN, CommonDataNames.AUTH));

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void clickLogin() {
        LOGIN.click();
    }

    public void clickPassword() {
        PASSWORD.click();
    }

    public void clickConfirm() {
        CONFIRM.click();
    }

    public void clickForgotPassword() {
        FORGOT_PASSWORD.click();
    }

    public void clickRegistration() {
        REGISTRATION.click();
    }

    public void checkThatLoginInState(Condition condition) {
        LOGIN.shouldBe(condition);
    }

    public void checkThatPasswordInState(Condition condition) {
        PASSWORD.shouldBe(condition);
    }

    public void checkThatTitleInState(Condition condition) {
        TITLE.shouldBe(condition);
    }

    public void checkThatConfirmInState(Condition condition) {
        CONFIRM.shouldBe(condition);
    }

    public void checkThatForgotPasswordInState(Condition condition) {
        FORGOT_PASSWORD.shouldBe(condition);
    }

    public void checkThatRegistrationInState(Condition condition) {
        REGISTRATION.shouldBe(condition);
    }

    public void checkThatMandatoryLoginErrorMsgInState(Condition condition){
        LOGIN_IS_MANDATORY_MSG.shouldBe(condition);
    }

    public void checkThatMandatoryPasswordErrorMsgInState(Condition condition){
        PASSWORD_IS_MANDATORY_MSG.shouldBe(condition);
    }

    public void checkThatEitherPasswordOrEmailIncorrectMessageInState(Condition condition){
        EITHER_PASSWORD_OR_EMAIL_INCORRECT_MSG.shouldBe(condition);
    }

    public String getLoginText() {
        return LOGIN.getText();
    }

    public String getTitleText() {
        return TITLE.getText();
    }

    public void fillLogin(String value) {
        LOGIN.setValue(value);
    }

    public void fillPassword(String value) {
        PASSWORD.setValue(value);
    }
}
