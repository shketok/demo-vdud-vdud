package ru.vdudvdud.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
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
        vdudMainPage.waitUntilPageMainElementsLoad();
    }



}
