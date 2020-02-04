package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.testdata.enums.Urls;
import ru.vdudvdud.testdata.objects.vdudvdud.pages.VdudMainPage;

public class MainPageSteps {
    private VdudMainPage vdudMainPage;

    public MainPageSteps() {
        vdudMainPage = new VdudMainPage();
    }

    @Step("Открытие главной страницы сайта и проверка корректного открытия страницы")
    public void openMainPage() {
        WebDriverRunner.getWebDriver().navigate().to(Urls.BASE.getHomeUrl());
        waitUntilMainPageOpen();
    }

    @Step("Проверка корректного открытия главной страницы")
    public void waitUntilMainPageOpen() {
        Assert.assertTrue(vdudMainPage.isMainElement(Condition.visible), "Проверка корректного открытия главной страницы");

    }

    @Step("Проверка, что главная страница не была открыта")
    public void waitUntilMainPageNotPresent() {
        vdudMainPage.shouldNotBe(Condition.visible);
    }



}
