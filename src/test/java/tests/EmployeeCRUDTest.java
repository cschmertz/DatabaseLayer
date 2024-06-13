package tests;

import dao.EmployeeDAO;
import model.Employee;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeCRUDTest {

    @Test
    public void testEmployeeCRUDOperations() throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Step 1: Create a new employee
        Employee newEmployee = new Employee(9999999999L, "Test", "User", "TEST123456", 1);
        employeeDAO.createEmployee(newEmployee);

        // Step 2: Read the employee to ensure it was created
        Employee createdEmployee = employeeDAO.getEmployeeById(9999999999L);
        assertNotNull(createdEmployee, "Failed to create employee");
        assertEquals("Test", createdEmployee.getFirstName());
        assertEquals("User", createdEmployee.getLastName());

        // Step 3: Update the employee
        createdEmployee.setFirstName("UpdatedTest");
        employeeDAO.updateEmployee(createdEmployee);

        // Step 4: Read the employee to ensure it was updated
        Employee updatedEmployee = employeeDAO.getEmployeeById(9999999999L);
        assertNotNull(updatedEmployee, "Failed to update employee");
        assertEquals("UpdatedTest", updatedEmployee.getFirstName());

        // Step 5: Delete the employee
        employeeDAO.deleteEmployee(9999999999L);

        // Step 6: Ensure the employee was deleted
        Employee deletedEmployee = employeeDAO.getEmployeeById(9999999999L);
        assertNull(deletedEmployee, "Failed to delete employee");
    }
}
