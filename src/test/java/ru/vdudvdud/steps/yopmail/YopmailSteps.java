package ru.vdudvdud.steps.yopmail;

import ru.vdudvdud.localization.YopmailLocalizationHolder;
import ru.vdudvdud.page.objects.yopmail.pages.YopmailInboxPage;
import ru.vdudvdud.page.objects.yopmail.pages.YopmailMainPage;
import ru.vdudvdud.testdata.models.essences.User;

public class YopmailSteps {

    private YopmailMainPage yopmailMainPage = new YopmailMainPage();
    private YopmailInboxPage yopmailInboxPage = new YopmailInboxPage();

    public void openYopmail() {
        yopmailMainPage.open();
    }

    public void navigateToUserInbox(User user) {
        yopmailMainPage.openYopmailAndNagivateToInboxByEmail(user.getEmail());
    }

    public String getLinkForRestorePassword() {
        yopmailInboxPage.clickMessageWithSubjectReceived(YopmailLocalizationHolder.EMAIL_SUBJECT_PASSWORD_RESTORE.i18n());
        yopmailInboxPage.checkThatInOpenedMailLinkForRestorePasswordVisible();
        return yopmailInboxPage.getLinkForRestorePassword();
    }

}
