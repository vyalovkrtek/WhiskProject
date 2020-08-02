package webElements.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import webElements.forms.NavigationBar;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingListPage extends BasePage {

    public NavigationBar navigationBar = new NavigationBar();
    public SelenideElement shoppingListName = $(new By.ByXPath(".//div[@data-testid='shopping-list-name']/h2"));
    public SelenideElement createNewListButton = $(new By.ByXPath(".//a[@data-testid='create-new-shopping-list-button']"));
    public SelenideElement listNameInput = $(new By.ByXPath(".//input[@data-testid='UI_KIT_INPUT']"));
    public SelenideElement createShoppingListButton = $(new By.ByXPath(".//button[@data-testid='create-new-shopping-list-create-button']"));
    public ElementsCollection shoppingLists = $$(new By.ByXPath(".//div[@data-testid='shopping-lists-list-name']"));
    public SelenideElement itemInput = $(new By.ByXPath(".//input[@data-testid='desktop-add-item-autocomplete']"));
    public ElementsCollection items = $$(new By.ByXPath(".//span[@data-testid='shopping-list-item-name']"));
    public SelenideElement verticalDotsMenuButton = $(new By.ByXPath(".//button[@data-testid='vertical-dots-shopping-list-button']"));
    public SelenideElement verticalDotsMenuDeleteButton = $(new By.ByXPath(".//button[@data-testid='shopping-list-delete-menu-button']"));
    public SelenideElement confirmDeleteButton = $(new By.ByXPath(".//button[@data-testid='confirm-delete-button']"));
    public SelenideElement listWasDeletedMessage = $(new By.ByXPath(".//span[text()='List was deleted']"));

    public ShoppingListPage(String pageUrl) {
        super(pageUrl);
    }

    public void createShoppingList(String name){
        createNewListButton.click();
        Assert.assertTrue(listNameInput.should(Condition.exist).isDisplayed(), "ListName input should be displayed");
        Selenide.executeJavaScript("arguments[0].value='';", listNameInput);
        listNameInput.sendKeys(name);
        createShoppingListButton.click();
        Assert.assertTrue(createNewListButton.should(Condition.enabled).isDisplayed(), "ShoppingList page" + pageUrl + "should be opened");
        shoppingLists.shouldHave(CollectionCondition.anyMatch("Shopping list" +
                " with name " + name + " should exists", element -> element.getText().equals(name)));
    }

    public void addItemsToShoppingList(String shoppingList, ArrayList<String> items){
        selectShoppingList(shoppingList);
        items.forEach(this::addItem);
    }

    public void selectShoppingList(String name){
        SelenideElement shoppingList = shoppingLists.stream().
                filter(element -> element.getText().equals(name)).findFirst().get();
        shoppingList.click();
        Assert.assertEquals(shoppingListName.text(), name);
    }

    public void addItem(String name){
        itemInput.sendKeys(name);
        itemInput.sendKeys(Keys.ENTER);
    }

    public void checkItemAdded(String name){
        items.shouldHave(CollectionCondition.anyMatch("Item "+name+" should exist",
                element -> element.getText().equals(name)));
    }

    public void deleteShoppingList(String shoppingListName){
        selectShoppingList(shoppingListName);
        verticalDotsMenuButton.click();
        verticalDotsMenuDeleteButton.click();
        confirmDeleteButton.should(Condition.enabled);
        confirmDeleteButton.click();
        listWasDeletedMessage.waitUntil(Condition.disappear, 10000); //it's strange, but it seems to list will not be deleted if page will be closed before message disappears
    }

    public void checkThereIsNoShoppingLists(){
        Assert.assertEquals(shoppingLists.toArray().length, 1, "Shopping list is not empty");
    }

}
