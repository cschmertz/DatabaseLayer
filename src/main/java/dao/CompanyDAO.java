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
                Company company = new Company(rs.getInt("company_id"), rs.getString("company_name"));
                companies.add(company);
            }
        }
        return companies;
    }

    // Other CRUD operations (create, update, delete) can be added here
}
