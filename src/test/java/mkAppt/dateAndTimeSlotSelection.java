package mkAppt;

import org.testng.annotations.Test;
import Base.BaseTest;
import dataProvider.makeAppointment;

public class dateAndTimeSlotSelection extends BaseTest {

	private String bookingType;

	@Test(dataProvider = "makeAppointmentData", dataProviderClass = makeAppointment.class)
	public void test(String provider, String location, String service, String firstName, String lastName, String DOB,
			String gendar, String cellPhone, String email) throws Exception {

		bookingType = pages.getSlotSelectionPage().typeOfBooking();
		pages.getSlotSelectionPage().clickConfirm();
		

		pages.getSlotSelectionPage().handelProvider(provider);
		pages.getSlotSelectionPage().handleLocation(location);
		
		
		if ( !pages.getSlotSelectionPage().isServiceUnavailable()) {
			pages.getSlotSelectionPage().handleService(service);

			pages.getSlotSelectionPage().selectTheFirstDate();
			pages.getSlotSelectionPage().selectTheFirstSlot();

			pages.getSlotSelectionPage().fill(firstName, lastName, DOB, gendar, cellPhone, email);
			System.out.println(DOB + "---------------");

			if (bookingType.equals("Request New Appointment")) {
				pages.getSlotSelectionPage().sentAppRequest();
			}
			else
				pages.getSlotSelectionPage().sentAppointment();
	   }else 
			System.out.println("END");
		
	}

}
