package service;

import dao.CompanyDAO;
import model.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyService implements ICompanyService{

    private CompanyDAO companyDAO = new CompanyDAO();

    public List<Company> getAllCompanies() throws SQLException {
        return companyDAO.getAllCompanies();
    }

    public void createCompany(Company company) throws SQLException {
        // Business logic: Validate the company's data
        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        // Additional business logic can be added here
        companyDAO.createCompany(company);
    }

    public Company getCompanyById(int id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid company ID");
        }
        return companyDAO.getCompanyById(id);
    }

    public void updateCompany(Company company) throws SQLException {
        // Business logic: Validate the company's data before updating
        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        // Additional validation or processing can be added here
        companyDAO.updateCompany(company);
    }

    public void deleteCompany(int id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid company ID");
        }
        // Additional business rules can be enforced here
        companyDAO.deleteCompany(id);
    }

}
