package dataProvider;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class makeAppointment {

    @DataProvider(name = "makeAppointmentData")
    public Object[][] getAppointmentData() {
        String path = "src/test/resources/Book.xlsx";
        return ExcelUtils.getExcelData(path, "patient");
    }
	
	
}
