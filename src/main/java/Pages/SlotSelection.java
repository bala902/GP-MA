package Pages;

// ==============================
// Imports
// ==============================
import java.time.Duration;
import java.util.List;
import java.util.Map;

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

    private WebDriverWait wait;

    // ==============================
    // Constructor
    // ==============================
    public SlotSelection(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==============================
    // Page Elements
    // ==============================

    @FindBy(xpath = "(//button[@type='button'])[1]")
    private WebElement requestNewAppointmentBtn;

    // ==============================
    // Generic Click (normal + action fallback)
    // ==============================

    private void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            new Actions(driver)
                    .moveToElement(element)
                    .pause(Duration.ofMillis(200))
                    .click()
                    .perform();
        }
    }

    // ==============================
    // Appointment Actions
    // ==============================

    public String typeOfBooking() throws Exception {
        return wait.until(ExpectedConditions.elementToBeClickable(requestNewAppointmentBtn)).getText();
    }

    public void clickConfirm() throws Exception {
        safeAction("clickConfirm", () -> click(requestNewAppointmentBtn));
    }

    // ==============================
    // Provider / Location / Service Selection
    // ==============================

    public void selectByName(String value) throws Exception {
        safeAction("selectByName", () -> {
            WebElement element = driver.findElement(
                    By.xpath("//h4[normalize-space()='" + value + "']")
            );
            click(element);
        });
    }

    // ==============================
    // Service or Date Unavailable
    // ==============================

    public boolean isServiceUnavailable() {
        return !driver.findElements(
                By.xpath("//span[normalize-space()='No services are currently available. Please select a different provider, location']")
        ).isEmpty();
    }

    // ==============================
    // Date & Slot Selection
    // ==============================

    private void selectFirst(By locator, String actionName) throws Exception {
        safeAction(actionName, () -> {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
            click(driver.findElements(locator).get(0));
        });
    }

    public void selectTheFirstDate() throws Exception {
        selectFirst(
                By.xpath("//div[contains(@class,'react-datepicker__day') and @aria-disabled='false']"),
                "selectTheFirstDate"
        );
    }

    public void selectTheFirstSlot() throws Exception {
        selectFirst(
                By.xpath("//button[@type='button' and contains(@class,'cursor-pointer')]"),
                "selectTheFirstSlot"
        );
    }

    // ==============================
    // Patient Details Helpers
    // ==============================

    public void selectDropDown(WebElement element, String value) throws Exception {
        safeAction("selectDropDown", () -> {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByVisibleText(value);
        });
    }

    public void fillPatientDetails(Map<String, String> data) throws Exception {
        safeAction("fillPatientDetails", () -> {
            data.forEach((id, value) -> {
                WebElement element = driver.findElement(By.id(id));
                element.clear();
                element.sendKeys(value);
            });
        });
    }

    // ==============================
    // Submit & Confirmation
    // ==============================

    public void sendAppointmentRequest() throws Exception {
        safeAction("sendAppointmentRequest", () -> {
            click(driver.findElement(By.id("requestAppointmentBtn")));
            confirmAppointment();
        });
    }

    public void sendAppointment() throws Exception {
        safeAction("sendAppointment", () ->
                click(driver.findElement(By.id("scheduleAppointmentBtn")))
        );
    }

    private void confirmAppointment() {
        try {
            click(wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Confirm']")
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
