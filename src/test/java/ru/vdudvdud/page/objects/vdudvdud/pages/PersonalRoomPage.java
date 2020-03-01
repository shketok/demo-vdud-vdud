package ru.vdudvdud.page.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.enums.urls.MyUrls;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описания страницы личного кабинета. В нем так же описываются заказы, и работа с профилем, так как страницу они включены.
 * По умолчанию страница личного кабинета открыта на заказе.
 */
public class PersonalRoomPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = "div.account";
    private static final String PERSONAL_ROOM_MENU_PATTERN = ".//div[@class='account__menu-content']//a[contains(@href, '%s')]";

    private SelenideElement userGreetingSection = getMainElement().$("div.account__user-section");
    private SelenideElement accountOrdersHistory = getMainElement().$("div.account__history");
    private SelenideElement title = getMainElement().$("*.account__title");
    private SelenideElement ordersTab = getMainElement().$x(String.format(PERSONAL_ROOM_MENU_PATTERN, MyUrls.MY_ORDERS.getUrlPart()));
    private SelenideElement profileTab = getMainElement().$x(String.format(PERSONAL_ROOM_MENU_PATTERN, MyUrls.MY_PROFILE.getUrlPart()));
    private SelenideElement logoutTab = getMainElement().$x(String.format(PERSONAL_ROOM_MENU_PATTERN, BaseUrls.LOGOUT.getUrlPart()));

    /**
     * Конструктор основного элемента.
     */
    public PersonalRoomPage() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void checkThatUserGreetingSectionInState(Condition condition) {
        userGreetingSection.shouldBe(condition);
    }

    public void checkThatAccountOrdersHistoryInState(Condition condition) {
        accountOrdersHistory.shouldBe(condition);
    }

    public void checkThatTitleInState(Condition condition) {
        title.shouldBe(condition);
    }

    public void checkThatOrdersTabInState(Condition condition) {
        ordersTab.shouldBe(condition);
    }

    public void checkThatProfileTabInState(Condition condition) {
        profileTab.shouldBe(condition);
    }

    public void checkThatLogoutTabInState(Condition condition) {
        logoutTab.shouldBe(condition);
    }

    public void clickOrdersTab() {
        ordersTab.click();
    }

    public void clickProfileTab() {
        profileTab.click();
    }

    public void clickLogoutTab() {
        logoutTab.click();
    }
}
