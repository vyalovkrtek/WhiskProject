package core.driver;

public class TestConfig {

    public static String browser = "chrome";

    public static void initConfig() {
        browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
    }
}
