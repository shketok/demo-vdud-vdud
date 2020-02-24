package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static ru.vdudvdud.testdata.enums.urls.BaseUrls.FORGOT_PASSWORD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.localization.RestorePasswordLocalization;
import ru.vdudvdud.testdata.models.essences.User;

/**
 * Класс, описывающий страницу восстановления пароля
 */
public class RestorePasswordPage extends BasePage {

    private static final SelenideElement MAIN_ELEMENT = $x(
        String.format("//form[contains(@action, '%s')]", FORGOT_PASSWORD.getUrlPart()));
    private static final SelenideElement EMAIL = MAIN_ELEMENT.$x(".//input[@name='login']");
    private static final SelenideElement CONFIRM = MAIN_ELEMENT.$x(".//input[@type='submit']");
    private static final SelenideElement INCORRECT_EMAIL_MESSAGE = MAIN_ELEMENT
        .$x(".//*[@class='wa-error-msg']");

    public void checkThatLoginInState(Condition condition) {
        EMAIL.shouldBe(condition);
    }

    public void checkThatConfirmInState(Condition condition) {
        CONFIRM.shouldBe(condition);
    }

    public void checkThatEmailFieldFilledWithUserEmail(User user) {
        EMAIL.shouldBe(Condition.exactValue(user.getEmail())).shouldBe(Condition.visible);
    }

    public void fillLogin(String value) {
        actions().sendKeys(EMAIL.toWebElement(), value).build().perform();
    }

    public void clickConfirm() {
        actions().click(CONFIRM.toWebElement()).build().perform();
    }

    public void checkIncorrectEmailMessageIsVisible() {
        Assert.assertEquals(RestorePasswordLocalization.INCORRECT_EMAIL_MESSAGE.getValue(),
            INCORRECT_EMAIL_MESSAGE.getText(),
            "Ожидаемое сообщение о некорректно введеном email не соответвует фактическому");
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }
}
