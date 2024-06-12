package service;

import model.Employee;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeServiceTest {

    @Test
    public void testGetAllEmployees() throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    // Add more tests for other business logic
}
