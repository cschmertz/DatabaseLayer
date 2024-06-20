package service;

import dao.EmployeeDAO;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService{

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    public void createEmployee(Employee employee) throws SQLException {
        // Business logic: Validate the employee's data
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (employee.getDriverLicense() == null || employee.getDriverLicense().isEmpty()) {
            throw new IllegalArgumentException("Driver license cannot be empty");
        }
        // Additional business logic can be added here
        employeeDAO.createEmployee(employee);
    }

    public Employee getEmployeeById(long id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        return employeeDAO.getEmployeeById(id);
    }

    public void updateEmployee(Employee employee) throws SQLException {
        // Business logic: Validate the employee's data before updating
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        // Additional validation or processing can be added here
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(long id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        // Additional business rules can be enforced here
        employeeDAO.deleteEmployee(id);
    }

}
