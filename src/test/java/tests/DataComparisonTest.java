package tests;

import com.example.utils.ExcelUtils;
import model.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CompanyService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataComparisonTest {
//
//    private static ExcelUtils employeesExcel;
//    private static ExcelUtils vehiclesExcel;
//    private static ExcelUtils companiesExcel;
//
//    @BeforeAll
//    public static void setup() throws IOException {
//        String desktopPath = System.getProperty("user.home") + "/Desktop/";
//        employeesExcel = new ExcelUtils(desktopPath + "Employees.xlsx", "Employees");
//        vehiclesExcel = new ExcelUtils(desktopPath + "Vehicles.xlsx", "Vehicles");
//        companiesExcel = new ExcelUtils(desktopPath + "Companies.xlsx", "Companies");
//    }
//
//    @Test
//    public void testCompareCompanyData() throws IOException, SQLException {
//        List<List<String>> companiesFromExcel = companiesExcel.getAllData();
//        List<Company> companiesFromDb = new CompanyService().getAllCompanies();
//        assertEquals(companiesFromExcel.size(), companiesFromDb.size(), "Company count mismatch");
//        for (int i = 0; i < companiesFromExcel.size(); i++) {
//            Company excelCompany = companiesFromExcel.get(i);
//            Company dbCompany = companiesFromDb.get(i);
//            assertEquals(excelCompany.getCompanyId(), dbCompany.getCompanyId(), "Company ID mismatch");
//            assertEquals(excelCompany.getCompanyName(), dbCompany.getCompanyName(), "Company name mismatch");
//        }
//    }
//
//    @Test
//    public void testEmployeesExcel() {
//        List<List<String>> employeesData = employeesExcel.getAllData();
//        assertEquals("Kariotta", employeesData.get(1).get(1)); // First name
//        assertEquals("McKernan", employeesData.get(1).get(2)); // Last name
//        assertEquals("E567890", employeesData.get(1).get(3)); // Driver license
//        assertEquals("1", employeesData.get(1).get(4)); // Company name
//    }

}
