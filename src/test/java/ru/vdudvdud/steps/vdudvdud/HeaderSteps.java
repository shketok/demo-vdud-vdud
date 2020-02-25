package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.objects.vdudvdud.forms.PersonalAreaDropdownForm;
import ru.vdudvdud.objects.vdudvdud.pages.HeaderPage;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.models.essences.Product;

public class HeaderSteps extends BaseSteps {
    private HeaderPage headerPage = new HeaderPage();
    private PersonalAreaDropdownForm personalAreaDropdownForm = headerPage.getPersonalAreaDropDownForm();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        headerPage.checkThatTopMenuInState(Condition.visible);
        headerPage.checkThatPersonalAreaInState(Condition.visible);
        headerPage.checkThatCurrencyTabInState(Condition.visible);
        headerPage.checkThatMainContactsLabelInState(Condition.visible);
        headerPage.checkThatLogoInState(Condition.visible);
        headerPage.checkThatBasketInState(Condition.visible);
    }

    @Step("Открытие формы регистрации сайта через верхнее меню")
    public void openSignUp() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.clickSignUp();
    }

    @Step("Проверка, что видна кнопка выхода из аккаунта")
    public void checkLogoutVisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.visible);
    }

    @Step("Проверка, что не видна кнопка выхода из аккаунта")
    public void checkLogoutInvisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.disappear);
    }

    @Step("Выход из авторизованного аккаунта")
    public void logout() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.visible);
        personalAreaDropdownForm.clickLogout();
    }

    @Step("Переход к форме входа в аккаунт")
    public void goToLoginPage() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatSignInBtnInState(Condition.visible);
        personalAreaDropdownForm.clickSignIn();
    }

    @Step("Проверка параметров мини корзины на соотвествие количеству продуктов, их цене и валюте")
    public void checkMiniCart(String currency, Product... products) {
        Integer countOfProducts = 0;
        Integer cost = 0;

        for (Product product : products) {
            countOfProducts += product.getCount();
            cost += product.getCost();
        }

        softAssert.assertEquals(headerPage.getCartAmountText(), countOfProducts.toString(),
                "Проверка совпадения количества товара на странице с ожидаемым значением");
        softAssert.assertEquals(headerPage.getProductCostText().replaceAll(RegexPatterns.SPACES.toString(), ""), cost.toString(),
                "Проверка совпадения общей цены товара на странице с ожидаемым значением");
        softAssert.assertEquals(headerPage.getProductCurrencyText(), currency,
                "Проверка совпадения валюты товара на странице с ожидаемым значением");
        softAssert.assertAll();
    }

    @Step("Переход на страницу корзины")
    public void goToTheCartPage() {
        headerPage.clickBasket();
    }
}
