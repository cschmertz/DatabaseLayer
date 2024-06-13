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
                Employee employee = new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("driver_license"),
                        rs.getInt("company_id"));
                employees.add(employee);
            }
        }
        return employees;
    }

    public void createEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employees (employee_id, first_name, last_name, driver_license, company_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, employee.getEmployeeId());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getDriverLicense());
            stmt.setInt(5, employee.getCompanyId());
            stmt.executeUpdate();
        }
    }

    public Employee getEmployeeById(long employeeId) throws SQLException {
        String query = "SELECT * FROM Employees WHERE employee_id = ?";
        Employee employee = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(
                            rs.getLong("employee_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("driver_license"),
                            rs.getInt("company_id"));
                }
            }
        }
        return employee;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employees SET first_name = ?, last_name = ?, driver_license = ?, company_id = ? WHERE employee_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getDriverLicense());
            stmt.setInt(4, employee.getCompanyId());
            stmt.setLong(5, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(long employeeId) throws SQLException {
        String query = "DELETE FROM Employees WHERE employee_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, employeeId);
            stmt.executeUpdate();
        }
    }
}
