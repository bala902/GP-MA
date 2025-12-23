package Factory;

import org.openqa.selenium.WebDriver;
import Pages.SlotSelection;

public class PageFactoryManager {

    private WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public SlotSelection getSlotSelectionPage() {
        return new SlotSelection(driver);
    }

    // add other pages here
}
