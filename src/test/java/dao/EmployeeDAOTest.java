package dao;

import model.Employee;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeDAOTest {
    @Test
    public void testGetAllEmployees() throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = employeeDAO.getAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    // Add more tests for create, update, delete operations
}
