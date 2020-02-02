package ru.vdudvdud.testdata.objects.yopmail.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class YopmailMainPage extends BasePage {

    private static final String ENTER_EMAIL_TXB = "//input[@id='login']";
    private static final String MAIL_CHECK_BTN = "//input[@class='sbut']";

    @Override
    protected SelenideElement getMainElement() {
        return null;
    }

    public void openYopmailAndNagivateToInboxByEmail(String email) {
        open();
        nagivateToInboxByEmail(email);
    }

    public void open() {
        String url = Configuration.getInstance().getProperty("yopmail.inbox");
        LOG.info("Navigate to URL: " + url);
        WebDriverRunner.getWebDriver().navigate().to(url);
    }

    /**
     * Открытие почтового ящика выбранного пользователя
     *
     * @param email почтовый адрес пользователя
     */
    private void nagivateToInboxByEmail(String email) {
        SelenideElement enterEmailTextBox = $x(ENTER_EMAIL_TXB);
        enterEmailTextBox.shouldBe(visible);
        enterEmailTextBox.setValue(email);
        $x(MAIL_CHECK_BTN).click();
    }
}
