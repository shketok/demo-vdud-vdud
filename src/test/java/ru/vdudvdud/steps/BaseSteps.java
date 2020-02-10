package ru.vdudvdud.steps;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public abstract class BaseSteps {
    protected SoftAssert softAssert = new SoftAssert();

    @Step("Проверка видимости основных элементов на странице")
    public abstract void checkThatMainElementsOfThePageAreVisible();
}
