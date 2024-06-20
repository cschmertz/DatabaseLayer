package tests;

import model.Employee;
import org.junit.jupiter.api.Test;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConcurrencyTest {

    /*
    This test will:
    Simulate multiple threads performing CRUD operations simultaneously.
    Ensure that the database maintains consistency and integrity under concurrent access.

    Explanation:
    Setup: The test sets up an ExecutorService with a fixed thread pool and a CountDownLatch to manage concurrent execution.
    Create Employee: A new employee is created to be used in the concurrent operations.
    Concurrent Updates: Multiple threads are spawned to perform updates on the same employee record simultaneously.
    Verification: After all threads have finished, the test verifies that the employee record exists and checks its final state.
    Cleanup: The employee record is deleted, and the test ensures that the deletion was successful.
    Shutdown: The ExecutorService is shut down to clean up resources.
     */

    private static final int THREAD_COUNT = 10;
    private static final long TEST_EMPLOYEE_ID = 9999999999L;

    @Test
    public void testConcurrentCRUDOperations() throws InterruptedException, SQLException {
        EmployeeService employeeService = new EmployeeService();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // Step 1: Create a new employee
        Employee newEmployee = new Employee(TEST_EMPLOYEE_ID, "Concurrent", "User", "CONCURRENT123", 1);
        employeeService.createEmployee(newEmployee);

        // Step 2: Perform concurrent updates
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    Employee employee = employeeService.getEmployeeById(TEST_EMPLOYEE_ID);
                    if (employee != null) {
                        employee.setFirstName("Concurrent" + Thread.currentThread().getId());
                        employeeService.updateEmployee(employee);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        // Wait for all threads to finish
        latch.await();

        // Step 3: Verify the final state of the employee
        Employee updatedEmployee = employeeService.getEmployeeById(TEST_EMPLOYEE_ID);
        assertNotNull(updatedEmployee, "Employee should exist after concurrent updates");

        // Step 4: Delete the employee
        employeeService.deleteEmployee(TEST_EMPLOYEE_ID);

        // Step 5: Ensure the employee was deleted
        Employee deletedEmployee = employeeService.getEmployeeById(TEST_EMPLOYEE_ID);
        assertNull(deletedEmployee, "Employee should be deleted after the test");

        // Shutdown the executor service
        executorService.shutdown();
    }
}
