package ru.vdudvdud.page.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.localization.SignInLocalizationHolder;
import ru.vdudvdud.testdata.enums.CommonDataNames;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;

/**
 * Класс описывающий страницу входа на сайт. Авторизация.
 */
public class SignInPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = "//div[contains(@class, 'signing__sign-in')]";

    private static final String AUTH_LINKS_PATTERN = ".//div[@class='auth-links']//a[contains(@href, '%s')]";
    private static final String ERROR_MSG_PATTERN = "//*[@class='wa-error-msg' and @data-name='%s']";

    private SelenideElement login = getMainElement().$x(".//input[@name='login']");
    private SelenideElement password = getMainElement().$x(".//input[@name='password']");
    private SelenideElement title = getMainElement().$x(".//*[@class='signing__title']");
    private SelenideElement confirm = getMainElement().$x(".//input[@type='submit']");
    private SelenideElement forgotPassword = getMainElement().$x(String.format(AUTH_LINKS_PATTERN,
            BaseUrls.FORGOT_PASSWORD.getUrlPart()));
    private SelenideElement registration = getMainElement().$x(String.format(AUTH_LINKS_PATTERN,
            BaseUrls.SIGN_UP.getUrlPart()));
    private static final SelenideElement eitherEmailOrPasswordIncorrect = $x(String.format(ERROR_MSG_PATTERN, CommonDataNames.AUTH.getName()));
    private static final SelenideElement loginIsMandatoryMsg = $x(String.format(ERROR_MSG_PATTERN,CommonDataNames.LOGIN.getName()));
    private static final SelenideElement passwordIsMandatoryMsg = $x(String.format(ERROR_MSG_PATTERN, CommonDataNames.PASSWORD.getName()));

    /**
     * Конструктор основного элемента.
     */
    public SignInPage() {
        super($x(MAIN_ELEMENT_LOC));
    }


    public void clickLogin() {
        login.click();
    }

    public void clickPassword() {
        password.click();
    }

    public void clickConfirm() {
        confirm.click();
    }

    public void clickForgotPassword() {
        forgotPassword.click();
    }

    public void clickRegistration() {
        registration.click();
    }

    public void checkThatLoginInState(Condition condition) {
        login.shouldBe(condition);
    }

    public void checkThatPasswordInState(Condition condition) {
        password.shouldBe(condition);
    }

    public void checkThatTitleInState(Condition condition) {
        title.shouldBe(condition);
    }

    public void checkThatConfirmInState(Condition condition) {
        confirm.shouldBe(condition);
    }

    public void checkThatMandatoryLoginErrorMsgIsVisible(){
        loginIsMandatoryMsg.shouldHave(
            Condition.exactText(SignInLocalizationHolder.SIGN_IN_LOGIN_IS_MANDATORY_MESSAGE.i18n())
        );
    }

    public void checkThatMandatoryPasswordErrorMsgIsVisible(){
        passwordIsMandatoryMsg.shouldHave(
            Condition.exactText(SignInLocalizationHolder.SIGN_IN_PASSWORD_IS_MANDATORY_MESSAGE.i18n())
        );
    }

    public void checkThatEitherPasswordOrEmailIncorrectMessageIsVisible(){
        eitherEmailOrPasswordIncorrect.shouldHave(
            Condition.exactText(SignInLocalizationHolder.SIGN_IN_EITHER_LOGIN_OR_PASSWORD_IS_INCORRECT.i18n())
        );
    }

    public void checkThatForgotPasswordInState(Condition condition) {
        forgotPassword.shouldBe(condition);
    }

    public void checkThatRegistrationInState(Condition condition) {
        registration.shouldBe(condition);
    }

    public String getLoginText() {
        return login.getText();
    }

    public String getTitleText() {
        return title.getText();
    }

    public void fillLogin(String value) {
        login.setValue(value);
    }

    public void fillPassword(String value) {
        password.setValue(value);
    }
}
