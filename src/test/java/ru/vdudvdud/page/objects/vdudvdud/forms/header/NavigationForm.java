package ru.vdudvdud.page.objects.vdudvdud.forms.header;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.adaptors.selenide.conditions.CustomCollectionCondition;

public class NavigationForm extends PageObject {

    private static final String MAIN_ELEMENT_LOC = "//div[@class='home-items__tabs']";
    private static final String TAB_PATTERN = ".//span[contains(text(), '%s')]";

    private static final String ALL_TABS_LOC = "//li[contains(@class, 'home-items__tabs-item')]";

    private SelenideElement activeTab = $x(
        "//li[contains(@class, 'home-items__tabs-item') and contains(@class, 'active')]");

    public NavigationForm() {
        super($x(MAIN_ELEMENT_LOC));
    }

    public void navigateToTabByName(String tabName) {
        $x(MAIN_ELEMENT_LOC).$x(String.format(TAB_PATTERN, tabName)).click();
    }

    public ElementsCollection getAllTabs(){
        return $$x(ALL_TABS_LOC).shouldHave(CustomCollectionCondition.sizeGreaterThanOrEqualOne);
    }

    /**
     * Получает индекс активной табины
     * @return индекс табины из листа + 1 (счет на сайте идет с 1)
     */
    public Integer getActiveTabIndex(){
        ElementsCollection allTabs = getAllTabs();
        for (SelenideElement tab : allTabs) {
            if(tab.getAttribute("class").contains("active")){
                return allTabs.indexOf(tab) + 1;
            }
        }
        return -1;
    }

}
