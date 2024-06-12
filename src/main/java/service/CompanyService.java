package service;

import dao.CompanyDAO;
import model.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    private CompanyDAO companyDAO = new CompanyDAO();

    public List<Company> getAllCompanies() throws SQLException {
        return companyDAO.getAllCompanies();
    }

    // Other business logic can be added here
}
