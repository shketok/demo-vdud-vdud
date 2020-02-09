package ru.vdudvdud.testdata.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.Urls;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс описания страницы личного кабинета. В нем так же описываются заказы, и работа с профилем, так как страницу они включены.
 * По умолчанию страница личного кабинета открыта на заказе.
 */
public class PersonalRoomPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("div.account");

    private static final String PERSONAL_ROOM_MENU_PATTERN = "//div[@class='account__menu-content']//a[contains(@href, '%s')]";

    private static final SelenideElement USER_GREETING_SECTION = $("div.account__user-section");
    private static final SelenideElement ACCOUNT_ORDERS_HISTORY = $("div.account__history");
    private static final SelenideElement TITLE = $("*.account__title");
    private static final SelenideElement ORDERS_TAB = $x(String.format(PERSONAL_ROOM_MENU_PATTERN, Urls.MY_ORDERS.getUrlPart()));
    private static final SelenideElement PROFILE_TAB = $x(String.format(PERSONAL_ROOM_MENU_PATTERN, Urls.MY_PROFILE.getUrlPart()));
    private static final SelenideElement LOGOUT_TAB = $x(String.format(PERSONAL_ROOM_MENU_PATTERN, Urls.LOGOUT.getUrlPart()));

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void checkThatUserGreetingSectionInState(Condition condition) {
        USER_GREETING_SECTION.shouldBe(condition);
    }

    public void checkThatAccountOrdersHistoryInState(Condition condition) {
        ACCOUNT_ORDERS_HISTORY.shouldBe(condition);
    }

    public void checkThatTitleInState(Condition condition) {
        TITLE.shouldBe(condition);
    }

    public void checkThatOrdersTabInState(Condition condition) {
        ORDERS_TAB.shouldBe(condition);
    }

    public void checkThatProfileTabInState(Condition condition) {
        PROFILE_TAB.shouldBe(condition);
    }

    public void checkThatLogoutTabInState(Condition condition) {
        LOGOUT_TAB.shouldBe(condition);
    }

    public void clickOrdersTab() {
        ORDERS_TAB.click();
    }

    public void clickProfileTab() {
        PROFILE_TAB.click();
    }

    public void clickLogoutTab() {
        LOGOUT_TAB.click();
    }
}
