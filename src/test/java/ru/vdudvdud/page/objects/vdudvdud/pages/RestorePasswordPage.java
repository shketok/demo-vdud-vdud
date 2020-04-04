package ru.vdudvdud.page.objects.vdudvdud.pages;

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
 * Класс, описывающий страницу восстановления пароля по данным пользователя
 */
public class RestorePasswordPage extends BasePage {

    private static final String MAIN_ELEMENT_LOC = String.format("//form[contains(@action, '%s')]",
            FORGOT_PASSWORD.getUrlPart());

    private SelenideElement email = getMainElement().$x(".//input[@name='login']");
    private SelenideElement confirm = getMainElement().$x(".//input[@type='submit']");
    private SelenideElement incorrectEmailMessage = getMainElement().$x(".//*[@class='wa-error-msg']");

    /**
     * Конструктор основного элемента.
     */
    public RestorePasswordPage() {
        super($x(MAIN_ELEMENT_LOC));
    }

    public void checkThatLoginInState(Condition condition) {
        email.shouldBe(condition);
    }

    public void checkThatConfirmInState(Condition condition) {
        confirm.shouldBe(condition);
    }

    public void checkThatEmailFieldFilledWithUserEmail(User user) {
        email.shouldBe(Condition.exactValue(user.getEmail())).shouldBe(Condition.visible);
    }

    public void fillLogin(String value) {
        actions().sendKeys(email.shouldBe(Condition.visible).toWebElement(), value).build().perform();
    }

    public void clickConfirm() {
        actions().click(confirm.shouldBe(Condition.visible).toWebElement()).build().perform();
    }

    public void checkIncorrectEmailMessageIsVisible() {
        Assert.assertEquals(RestorePasswordLocalization.INCORRECT_EMAIL_MESSAGE.getValue(),
            incorrectEmailMessage.getText(),
            "Ожидаемое сообщение о некорректно введеном email не соответвует фактическому");
    }
}
