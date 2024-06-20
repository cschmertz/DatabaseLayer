package service;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees() throws SQLException;

    void createEmployee(Employee employee) throws SQLException;

    Employee getEmployeeById(long id) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;

    void deleteEmployee(long id) throws SQLException;
}
