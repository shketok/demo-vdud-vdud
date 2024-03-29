package ru.vdudvdud.page.objects.yopmail.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.adaptors.selenide.utils.SimpleWait;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;

public class YopmailInboxPage extends BasePage {
    // TODO отдельная форма для писем
    private static final String MSG_LOC = "//div[@class='m']";
    private static final String MSG_CONTENT = "//div[@id='mailmillieu']";
    private static final SelenideElement RESTORE_PASSWORD_MSG_LINK = $x(
        MSG_CONTENT.concat(String.format("//a[contains(@href, '%s')]", BaseUrls.FORGOT_PASSWORD.getUrlPart())));
    private static final String EMAIL_SUBJECT_LOC = MSG_LOC.concat("//span[contains(text(), '%s')]");
    private static final String INBOX_FRAME_LOC = "//iframe[@id='ifinbox']";
    private static final String CURRENT_MESSAGE_FRAME_LOC = "//iframe[@id='ifmail']";
    private static final String REFRESH_BUTTON_LOC = "//a[@id='lrefr']";
    private static final String EMAIL_NUMBER_LOC = "//span[@id='nbmail']";
    private static final Long REFRESH_ATTEMPT_NUMBER = Configuration.getInstance()
        .getTimeout("timeout.yopmail.refresh_attempts");
    private static final int NEW_USER_MESSAGE_NUMBER = 1;

    private static final long EMAIL_WAITER_TIME = 15000;

    /**
     * Конструктор основного элемента.
     */
    public YopmailInboxPage() {
        super($x(MSG_LOC));
    }

    /**
     * Ожидание получения заданного количества сообщений
     *
     * @param number ожидаемое количество сообщений
     */
    private void waitForMailNumber(int number) {
        int attempts = 0;
        do {
            //В случае отсутствия подобной задержки почтовый сервис определяет активность подозрительной и требует ввода капчи
            SimpleWait.waitMillis(EMAIL_WAITER_TIME);
            Selenide.switchTo().defaultContent();
            $x(REFRESH_BUTTON_LOC).click();
            attempts++;
            $x(EMAIL_NUMBER_LOC).shouldBe(visible);
            Selenide.switchTo().frame($x(INBOX_FRAME_LOC));
        }
        while ($$x(MSG_LOC).size() < number && attempts < REFRESH_ATTEMPT_NUMBER);
        Selenide.switchTo().defaultContent();
    }

    private Integer getMessagesCount() {
        Selenide.switchTo().frame($x(INBOX_FRAME_LOC));
        Integer size = $$x(MSG_LOC).size();
        Selenide.switchTo().defaultContent();
        return size;
    }

    private void clickMessage(int index) {
        Selenide.switchTo().frame($x(INBOX_FRAME_LOC));
        $$x(MSG_LOC).get(index).click();
        Selenide.switchTo().defaultContent();
    }


    /**
     * Переход в полученное письмо с заданной темой.
     *
     * @param subject ожидаемая тема
     */
    public void clickMessageWithSubjectReceived(String subject) {
        Selenide.switchTo().frame($x(INBOX_FRAME_LOC));
        $x(String.format(EMAIL_SUBJECT_LOC, subject)).click();
        Selenide.switchTo().defaultContent();
    }

    public void checkThatInOpenedMailLinkForRestorePasswordVisible() {
        Selenide.switchTo().frame($x(CURRENT_MESSAGE_FRAME_LOC));
        RESTORE_PASSWORD_MSG_LINK.shouldBe(visible);
    }

    public String getLinkForRestorePassword() {
        return RESTORE_PASSWORD_MSG_LINK.getText();
    }
}
