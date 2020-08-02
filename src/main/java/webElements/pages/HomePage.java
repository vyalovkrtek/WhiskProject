package webElements.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import webElements.forms.LoginForm;
import webElements.forms.NavigationBar;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage{
    public LoginForm loginForm = new LoginForm();
    public NavigationBar navigationBar = new NavigationBar();
    public SelenideElement signInButton = $(new By.ByXPath(".//a[@class='signin']"));

    public HomePage(String pageUrl) { super(pageUrl); }

    public void signIn(String email, String pass){
        signInButton.click();
        Selenide.switchTo().window(1);
        loginForm.login(email, pass);
    }
}
