package Pages;

// ==============================
// Imports
// ==============================
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// ==============================
// Page Class
// ==============================
public class SlotSelection extends BasePage {

	// WebDriver and Wait instances
	private WebDriver driver;
	private WebDriverWait wait;

	// ==============================
	// Constructor
	// ==============================
	public SlotSelection(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// ==============================
	// Page Elements
	// ==============================

	@FindBy(xpath = "(//button[@type='button'])[1]")
	private WebElement requestNewAppointmentBtn;

	@FindBy(xpath = "//div[contains(@class,'border') and contains(@class,'flex-row')]//h4")
	private List<WebElement> listOfProv;

	@FindBy(xpath = "(//div[contains(@class,'border') and contains(@class,'flex-row')]//h4)[position()>1]")
	private List<WebElement> listOfLoc;

	@FindBy(xpath = "(//div[contains(@class,'border') and contains(@class,'flex-row')]//h4)[position()>2]")
	private List<WebElement> listOfSer;

	@FindBy(xpath = "(//button[@type='button'][normalize-space()='Change'])[3]")
	private WebElement serChangeButton;

	// ==============================
	// Appointment Actions
	// ==============================

	public String typeOfBooking() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(requestNewAppointmentBtn));
			return requestNewAppointmentBtn.getText();
		} catch (Exception e) {
			handleException("typeOfBooking", e);
			return null;
		}
	}

	public void clickConfirm() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(requestNewAppointmentBtn));
			requestNewAppointmentBtn.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			handleException("clickConfirm", e);
		}
	}

	public void clickMethod(WebElement element) throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			handleException("clickMethod", e);
		}
	}

	// ==============================
	// Selection Handlers
	// ==============================

	public void handleService(String Service) throws Exception {
		try {
			WebElement service = driver.findElement(By.xpath("//h4[normalize-space()='"+ Service+"']"));
			clickMethod(service);
		} catch (Exception e) {
			handleException("handleService", e);
		}
	}

	public void handleLocation(String Location) throws Exception {
		try {
			WebElement location = driver.findElement(By.xpath("//h4[normalize-space()='"+ Location+"']"));
			clickMethod(location);
		} catch (Exception e) {
			handleException("handleLocation", e);
		}
	}

	public void handelProvider(String Provider) throws Exception {
		try {
			WebElement provider = driver.findElement(By.xpath("//h4[normalize-space()='"+ Provider +"']"));
			clickMethod(provider);
		} catch (Exception e) {
			handleException("handelProvider", e);
		}
	}
	
	// ==============================
	// Service or Date unavailable 
	// ==============================

	 public boolean isServiceUnavailable() {
		 By validationMsg = By.xpath(
			        "//span[normalize-space()='No services are currently available. Please select a different provider, location']"
			    );

			    List<WebElement> messages = driver.findElements(validationMsg);

			    if (!messages.isEmpty() && messages.get(0).isDisplayed()) {
			        System.out.println("✖ Service unavailable validation displayed");
			        return true;
			    }

			    System.out.println("✔ Services available or validation not shown");
			    return false;
	 }
	
	// ==============================
	// Action Class Clicks
	// ==============================

	public void actionClick(List<WebElement> elements) throws Exception {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(elements.get(0))
				   .pause(Duration.ofMillis(200))
				   .click()
				   .build()
				   .perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			handleException("actionClick", e);
		}
	}

	public void actionClickOne(WebElement element) throws Exception {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element)
				   .pause(Duration.ofMillis(200))
				   .click()
				   .build()
				   .perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			handleException("actionClickOne", e);
		}
	}

	// ==============================
	// Date & Slot Selection
	// ==============================

	public void selectTheFirstDate() throws Exception {
		try {
			//List<WebElement> dates = driver.findElements(By.xpath("//div[contains(@class,'react-datepicker__day') and @aria-disabled='false']"));
			  By datesBy = By.xpath("//div[contains(@class,'react-datepicker__day') and @aria-disabled='false']");
			    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(datesBy, 0));
			    List<WebElement> dates = driver.findElements(datesBy);
			
			actionClick(dates);
		} catch (Exception e) {
			handleException("selectTheFirstDate", e);
		}
	}

	public void selectTheFirstSlot() throws Exception {
		try {
			//List<WebElement> slots = driver.findElements(By.xpath("//button[@type='button' and contains(@class,'cursor-pointer')]"));
			//System.out.println(slots.size());
		    By slotsBy = By.xpath("//button[@type='button' and contains(@class,'cursor-pointer')]");
		    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(slotsBy, 0));
		    List<WebElement> slots = driver.findElements(slotsBy);
		
			actionClick(slots);
		} catch (Exception e) {
			handleException("selectTheFirstSlot", e);
		}
	}

	// ==============================
	// Patient Details Helpers
	// ==============================

	public void enterPatientDetails(WebElement element, String value) throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
		} catch (Exception e) {
			handleException("enterPatientDetails", e);
		}
	}

	public void selectDropDown(WebElement element, String value) throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			new Select(element).selectByVisibleText(value);
		} catch (Exception e) {
			handleException("selectDropDown", e);
		}
	}

	public void fill(String firstName, String lastName, String DOB, String gendar, String cellPhone, String email) throws Exception {
		try {
			enterPatientDetails(driver.findElement(By.id("saveFirstName")), firstName);
			enterPatientDetails(driver.findElement(By.id("saveLastName")), lastName);
			enterPatientDetails(driver.findElement(By.id("saveDOB")), DOB);
			selectDropDown(driver.findElement(By.id("saveGender")), gendar);
			enterPatientDetails(driver.findElement(By.id("saveCellPhone")), cellPhone);
			enterPatientDetails(driver.findElement(By.id("saveEmail")), email);
		} catch (Exception e) {
			handleException("fill", e);
		}
	}

	// ==============================
	// Submit & Confirmation
	// ==============================

	public void sentAppRequest() throws Exception {
		try {
			actionClickOne(driver.findElement(By.id("requestAppointmentBtn")));
			confirmToTheAppointmentRequest();
		} catch (Exception e) {
			handleException("sentAppRequest", e);
		}
	}

	public void sentAppointment() throws Exception {
		try {
			actionClickOne(driver.findElement(By.id("scheduleAppointmentBtn")));
		} catch (Exception e) {
			handleException("sentAppointment", e);
		}
	}

	public void confirmToTheAppointmentRequest() throws Exception {
		try {
			By confirmBtn = By.xpath("//button[normalize-space()='Confirm']");
			wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
		} catch (Exception e) {
			handleException("confirmToTheAppointmentRequest", e);
		}
	}
	// ==============================
	// Reschedule Appointment
	// ==============================
}
