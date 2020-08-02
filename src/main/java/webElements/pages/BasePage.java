package webElements.pages;

import com.codeborne.selenide.Selenide;
import core.Config;

public class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
        String url = Config.baseUrl + pageUrl;
        Selenide.open(url);
    }

    public void open(String url){
        Selenide.open(url);
    }
}
