package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static ThreadLocal<Browser> browsers = new ThreadLocal<>();


    public static WebDriver getDriver() {
        return getDriver(Browser.CHROME);
    }

    //TODO driveri güncelle
    public static WebDriver getDriver(Browser browser) {

        if (browsers.get()==null)
            browsers.set(browser);

        if (drivers.get() == null) {
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
            }
        }
        return drivers.get();
    }

    public static void quitDriver(){
        if (drivers.get()!=null){
            drivers.get().quit();
            drivers.set(null);
        }
    }

    public static Browser getBrowser(){
        return browsers.get();
    }

    public static void setBrowser(Browser browser){
        browsers.set(browser);
    }
}
