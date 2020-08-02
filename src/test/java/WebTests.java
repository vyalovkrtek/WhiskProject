import core.Config;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testBase.WebTestBase;
import webElements.pages.HomePage;
import webElements.pages.ShoppingListPage;

import java.util.ArrayList;

public class WebTests extends WebTestBase {

    public HomePage homePage = new HomePage("");

    public ShoppingListPage shoppingListPage = new ShoppingListPage("/shopping-list");

    public String shoppingListName = "guacomole";
    public ArrayList<String> shoppingList = new ArrayList<String>(){
        {
            add("avocado");
            add("oil");
            add("onion");
            add("salt");
            add("chili");
        }
    };

    @DataProvider(name = "getShoppingList")
    public Object[][] shoppingList(){
        return new Object[][]{
                {shoppingList.get(0)},
                {shoppingList.get(1)},
                {shoppingList.get(2)},
                {shoppingList.get(3)},
                {shoppingList.get(4)}
        };
    }

    @BeforeClass
    public void precondition(){
        homePage.open(Config.startUrl);
        homePage.signIn("vyalov.krtek@yandex.ru", "WhiskTestTask");
        homePage.navigationBar.goToShoppingList();
        shoppingListPage.createShoppingList(shoppingListName);
        shoppingListPage.addItemsToShoppingList(shoppingListName, shoppingList);
    }

    @Test(dataProvider = "getShoppingList")
    public void a_checkItemsInShoppingList(String item){
        shoppingListPage.checkItemAdded(item);
    }

    @Test
    public void b_deleteShoppingListTest(){
        shoppingListPage.deleteShoppingList(shoppingListName);
        shoppingListPage.checkThereIsNoShoppingLists();
    }
}
