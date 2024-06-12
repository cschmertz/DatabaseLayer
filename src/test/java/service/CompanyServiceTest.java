package service;

import model.Company;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyServiceTest {

    @Test
    public void testGetAllCompanies() throws SQLException {
        CompanyService companyService = new CompanyService();
        List<Company> companies = companyService.getAllCompanies();
        assertNotNull(companies);
        assertTrue(companies.size() > 0);
    }
}
