package webElements.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginForm {
    public SelenideElement authForm = $(new By.ByXPath(".//div[@data-testid='authentication-form']"));
    public SelenideElement loginInput = $(new By.ByCssSelector("#_input-1"));
    public SelenideElement continueButton = $(new By.ByXPath(".//button[@data-testid='auth-continue-button']"));
    public SelenideElement passFormTitle = $(new By.ByXPath(".//h2[text()='Enter your password']"));
    public SelenideElement passInput = $(new By.ByXPath(".//input[@data-testid='UI_KIT_INPUT']"));
    public SelenideElement authLoginButton = $(new By.ByXPath(".//button[@data-testid='auth-login-button']"));
    public SelenideElement communityContinueButton = $(new By.ByXPath(".//button[@data-testid='community-onboarding-continue']"));

    public void checkIsOpened(){
        authForm.should(Condition.exist);
        loginInput.should(Condition.exist);
    }

    public void login(String email, String pass) {

        checkIsOpened();

        loginInput.sendKeys(email);
        continueButton.click();
        passFormTitle.should(Condition.exist);
        passInput.sendKeys(pass);
        authLoginButton.click();
        communityContinueButton.should(Condition.exist);
        communityContinueButton.click();
    }
}
