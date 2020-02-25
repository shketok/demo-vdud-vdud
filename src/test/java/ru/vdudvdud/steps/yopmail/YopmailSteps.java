package ru.vdudvdud.steps.yopmail;

import ru.vdudvdud.objects.yopmail.pages.YopmailInboxPage;
import ru.vdudvdud.objects.yopmail.pages.YopmailMainPage;
import ru.vdudvdud.testdata.models.essences.User;

public class YopmailSteps {

    private YopmailMainPage yopmailMainPage = new YopmailMainPage();
    private YopmailInboxPage yopmailInboxPage = new YopmailInboxPage();

    public void openYopmail() {
        yopmailMainPage.open();
    }

    public void enter(User user) {
        yopmailMainPage.openYopmailAndNagivateToInboxByEmail(user.getEmail());
    }

    public String getRestoredLink() {
        yopmailInboxPage.waitForMailNumber(2);
        yopmailInboxPage.clickMessageWithSubjectReceived("Восстановление_пароля_для_входа");
        yopmailInboxPage.checkThatInOpenedMailLinkVisible();
        return yopmailInboxPage.getRestoredLink();
    }

}
