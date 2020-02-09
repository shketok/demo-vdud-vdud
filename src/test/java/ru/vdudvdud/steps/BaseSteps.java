package ru.vdudvdud.steps;

import io.qameta.allure.Step;

public abstract class BaseSteps {

    @Step("Проверка видимости основных элементов на странице")
    public abstract void checkThatMainElementsOfThePageAreVisible();
}
