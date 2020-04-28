package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.page.objects.vdudvdud.forms.header.NavigationForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.header.PersonalAreaDropdownForm;
import ru.vdudvdud.page.objects.vdudvdud.pages.HeaderPage;
import ru.vdudvdud.testdata.objects.Cart;

public class HeaderSteps extends BaseSteps {
    private HeaderPage headerPage = new HeaderPage();
    private PersonalAreaDropdownForm personalAreaDropdownForm = headerPage.getPersonalAreaDropDownForm();
    private NavigationForm navigationForm = headerPage.getNavigationForm();

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
        personalAreaDropdownForm.clickLogout();
    }

    @Step("Переход к форме входа в аккаунт")
    public void goToLoginPage() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.clickSignIn();
    }

    @Step("Проверка параметров мини корзины на соотвествие количеству продуктов, их цене и валюте")
    public void checkMiniCart() {
        headerPage.scrollToMainElement(true);
        softAssert.assertEquals(headerPage.getCartAmountText(), String.valueOf(Cart.getInstance().getTotalProductsCount()),
                "Проверка совпадения количества товара на странице с ожидаемым значением");
        softAssert.assertEquals(headerPage.getProductCostText(), String.valueOf(Cart.getInstance().getTotalCost()),
                "Проверка совпадения общей цены товара на странице с ожидаемым значением");
        softAssert.assertEquals(headerPage.getProductCurrencyText(), Cart.getInstance().getCartCurrentCurrency(),
                "Проверка совпадения валюты товара на странице с ожидаемым значением");
        softAssert.assertAll();
    }


    @Step("Наведение курсора на миникорзину")
    public void hoverOverMinicart(){
        headerPage.hoverBasket();
    }

    @Step("Проверка, что не видна мини корзина при отсутствии товаров")
    public void checkMinicartInvisible() {
        headerPage.checkThatMiniCartInState(Condition.disappear);
    }

    @Step("Переход на страницу корзины")
    public void goToTheCartPage() {
        headerPage.clickBasket();
    }

    @Step("Переход на главную страницу")
    public void goToTheMainPage() {
        headerPage.clickMainLogo();
    }

    @Step("Переход на вкладку с товарами")
    public void goToTheProductsTab(String tabName){
        navigationForm.navigateToTabByName(tabName);
    }
}
