package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {
    List<Employee> getAllEmployees() throws SQLException;

    void createEmployee(Employee employee) throws SQLException;

    Employee getEmployeeById(long employeeId) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;

    void deleteEmployee(long employeeId) throws SQLException;
}
