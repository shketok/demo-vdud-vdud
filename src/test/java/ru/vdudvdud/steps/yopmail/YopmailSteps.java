package ru.vdudvdud.steps.yopmail;

import ru.vdudvdud.page.objects.yopmail.pages.YopmailInboxPage;
import ru.vdudvdud.page.objects.yopmail.pages.YopmailMainPage;
import ru.vdudvdud.testdata.enums.localization.YopmailLocalization;
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
        yopmailInboxPage.clickMessageWithSubjectReceived(YopmailLocalization.EMAIL_SUBJECT_PASSWORD_RESTORE.getValue());
        yopmailInboxPage.checkThatInOpenedMailLinkForRestorePasswordVisible();
        return yopmailInboxPage.getLinkForRestorePassword();
    }

}
