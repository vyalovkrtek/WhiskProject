package webElements.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NavigationBar {
    public SelenideElement homeLink = $(new By.ByXPath(".//div[@data-testid='home-nav-link']"));
    public SelenideElement recipesLink = $(new By.ByXPath(".//div[@data-testid='recipes-nav-link']"));
    public SelenideElement shoppingListLink = $(new By.ByXPath(".//div[@data-testid='shopping-list-nav-link']"));

    public void goToHome(){
        homeLink.click();
    }

    public void goToRecipes(){
        recipesLink.click();
    }

    public void goToShoppingList(){
        shoppingListLink.click();
    }
}
