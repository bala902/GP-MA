package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected void handleException(String methodName, Exception e) throws Exception {
        System.err.println("Exception in method: " + methodName);
        e.printStackTrace();
        throw e;
    }

}
