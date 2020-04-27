package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.objects.vdudvdud.pages.PageNotFound404;

public class PageNotFound404Steps extends BaseSteps {

    private PageNotFound404 pageNotFound404 = new PageNotFound404();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        pageNotFound404.isMainElement(Condition.visible);
    }
}
