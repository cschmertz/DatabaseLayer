package dao;

import model.Company;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyDAOTest {

    @Test
    public void testGetAllCompanies() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAO();
        List<Company> companies = companyDAO.getAllCompanies();
        assertNotNull(companies);
        assertTrue(companies.size() > 0);
    }

    // Add more tests for create, update, delete operations
}
