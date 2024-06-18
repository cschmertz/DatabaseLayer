package dao;

import config.DatabaseConfig;
import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    public List<Company> getAllCompanies() throws SQLException {
        String query = "SELECT * FROM Companies";
        List<Company> companies = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("company_id"),
                        rs.getString("company_name"));
                companies.add(company);
            }
        }
        return companies;
    }

    public void createCompany(Company company) throws SQLException {
        String query = "INSERT INTO Companies (company_id, company_name) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, company.getCompanyId());
            stmt.setString(2, company.getCompanyName());
            stmt.executeUpdate();
        }
    }

    public Company getCompanyById(int companyId) throws SQLException {
        String query = "SELECT * FROM Companies WHERE company_id = ?";
        Company company = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, companyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    company = new Company(
                            rs.getInt("company_id"),
                            rs.getString("company_name"));
                }
            }
        }
        return company;
    }

    public void updateCompany(Company company) throws SQLException {
        String query = "UPDATE Companies SET company_name = ? WHERE company_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, company.getCompanyName());
            stmt.setInt(2, company.getCompanyId());
            stmt.executeUpdate();
        }
    }

    public void deleteCompany(int companyId) throws SQLException {
        String query = "DELETE FROM Companies WHERE company_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, companyId);
            stmt.executeUpdate();
        }
    }

}
