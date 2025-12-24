package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class rescsheduleAndCancel extends BasePage{

	// ==============================
	// Page Class
	// ==============================

		// WebDriver and Wait instances
		private WebDriver driver;
		private WebDriverWait wait;

		// ==============================
		// Constructor
		// ==============================
		public rescsheduleAndCancel(WebDriver driver) {
			super(driver);
			this.driver = driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		
		
		
}
	
	
	
	
	

