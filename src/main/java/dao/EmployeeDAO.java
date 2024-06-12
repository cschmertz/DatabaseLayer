package dao;

import config.DatabaseConfig;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getAllEmployees() throws SQLException {
        String query = "SELECT * FROM Employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("driver_license"), rs.getInt("company_id"));
                employees.add(employee);
            }
        }
        return employees;
    }

    // Other CRUD operations (create, update, delete) can be added here
}
