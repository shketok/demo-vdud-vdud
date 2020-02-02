package ru.vdudvdud.testdata.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.objects.vdudvdud.forms.PersonalAreaDropdownForm;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignUpPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $x("//div[@class='wa-form']");

    private static final SelenideElement FIRSTNAME = $x("//div[contains(@class, 'field-firstname')]//input");
    private static final SelenideElement LASTNAME = $x("//div[contains(@class, 'field-lastname')]//input");
    private static final SelenideElement PASSWORD = $x("//div[contains(@class, 'field-password') and not(contains(@class, 'confirm'))]//input");
    private static final SelenideElement CONFIRM_PASSWORD = $x("//div[contains(@class, 'field-password_confirm')]//input");
    private static final SelenideElement EMAIL = $x("//div[contains(@class, 'field-email')]//input");

    private static final SelenideElement SIGN_UP_BUTTON = $x("//div[@class='wa-form']//form//input[@type='submit']");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }


    public void fillName(String value) {
        FIRSTNAME.setValue(value);
    }

    public void fillSurname(String value) {
        LASTNAME.setValue(value);
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

    public void waitUntilSignUpBtnInvisible() {
        SIGN_UP_BUTTON.shouldNotBe(Condition.visible);
    }
}
