package tests;

import dao.EmployeeDAO;
import model.Employee;
import org.junit.jupiter.api.Test;
import service.EmployeeService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeCRUDTest {

    /*
    This test case will verify the CRUD (Create, Read, Update, Delete) operations for the Employees table. It will check that we can perform these operations successfully and validate the changes in the database.

    Test Case: CRUD Operations

    Objective:
    Ensure that the CRUD operations for the Employees table work as expected.

    Steps:
    Create a new employee record.
    Read the employee record to ensure it was created.
    Update the employee record.
    Read the employee record to ensure it was updated.
    Delete the employee record.
    Ensure the employee record was deleted.
     */

    @Test
    public void testEmployeeCRUDOperations() throws SQLException {
        EmployeeService employeeService = new EmployeeService();

        // Step 1: Create a new employee
        Employee newEmployee = new Employee(9999999999L, "Test", "User", "TEST123456", 1);
        employeeService.createEmployee(newEmployee);

        // Step 2: Read the employee to ensure it was created
        Employee createdEmployee = employeeService.getEmployeeById(9999999999L);
        assertNotNull(createdEmployee, "Failed to create employee");
        assertEquals("Test", createdEmployee.getFirstName());
        assertEquals("User", createdEmployee.getLastName());

        // Step 3: Update the employee
        createdEmployee.setFirstName("UpdatedTest");
        employeeService.updateEmployee(createdEmployee);

        // Step 4: Read the employee to ensure it was updated
        Employee updatedEmployee = employeeService.getEmployeeById(9999999999L);
        assertNotNull(updatedEmployee, "Failed to update employee");
        assertEquals("UpdatedTest", updatedEmployee.getFirstName());

        // Step 5: Delete the employee
        employeeService.deleteEmployee(9999999999L);

        // Step 6: Ensure the employee was deleted
        Employee deletedEmployee = employeeService.getEmployeeById(9999999999L);
        assertNull(deletedEmployee, "Failed to delete employee");
    }
}
