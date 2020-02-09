package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.testdata.enums.Urls;
import ru.vdudvdud.testdata.objects.vdudvdud.pages.VdudMainPage;

public class MainPageSteps extends BaseSteps {
    private VdudMainPage vdudMainPage = new VdudMainPage();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        vdudMainPage.shouldBe(Condition.visible);
    }

    @Step("Открытие главной страницы сайта и проверка корректного открытия страницы")
    public void openMainPage() {
        WebDriverRunner.getWebDriver().navigate().to(Urls.BASE.getHomeUrl());
        checkThatMainPageIsOpen();
    }

    @Step("Проверка корректного открытия главной страницы")
    public void checkThatMainPageIsOpen() {
        Assert.assertTrue(vdudMainPage.isMainElement(Condition.visible), "Проверка корректного открытия главной страницы");

    }

    @Step("Проверка, что главная страница не была открыта")
    public void waitUntilMainPageNotPresent() {
        vdudMainPage.shouldNotBe(Condition.visible);
    }


}
