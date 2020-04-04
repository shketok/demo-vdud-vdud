package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.page.objects.vdudvdud.pages.PersonalRoomPage;

public class PersonalRoomSteps extends BaseSteps {
    private PersonalRoomPage personalRoomPage = new PersonalRoomPage();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        personalRoomPage.checkThatUserGreetingSectionInState(Condition.visible);
        personalRoomPage.checkThatAccountOrdersHistoryInState(Condition.visible);
        personalRoomPage.checkThatTitleInState(Condition.visible);
        personalRoomPage.checkThatOrdersTabInState(Condition.visible);
        personalRoomPage.checkThatProfileTabInState(Condition.visible);
        personalRoomPage.checkThatLogoutTabInState(Condition.visible);
    }
}
