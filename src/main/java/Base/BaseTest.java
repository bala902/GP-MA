package Base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Factory.PageFactoryManager;
import Utils.ConfigReader;

public class BaseTest {


	    protected WebDriver driver;
	    protected ConfigReader config;
	    protected DriverFactory driverFactory;
	    protected PageFactoryManager pages;
	    
	    @BeforeSuite
	    public void loadConfig() {
	        config = new ConfigReader();
	        config.initProp();
	    }

	    @BeforeMethod
	    public void setup() {
	        driverFactory = new DriverFactory();
	        driver = driverFactory.initDriver(config.get("browser"));
	        driver.get(config.get("url"));
	        pages = new PageFactoryManager(DriverFactory.getDriver());
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
