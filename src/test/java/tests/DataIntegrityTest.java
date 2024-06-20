package tests;

import dao.CompanyDAO;
import dao.EmployeeDAO;
import dao.VehicleDAO;
import model.Company;
import model.Employee;
import model.Vehicle;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.junit.jupiter.api.Test;
import service.CompanyService;
import service.EmployeeService;
import service.VehicleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataIntegrityTest {

    /*
    Overview:
    This test case will verify the consistency and correctness of data between the Companies, Employees, and Vehicles tables within the database. The test will ensure that the relationships defined by foreign keys are correctly maintained and that data integrity is preserved.
    Test Case: Verify Data Integrity and Relationships

    Objective:
    To verify that the data in the Employees and Vehicles tables correctly references the Companies table and that all entries have valid relationships.

    Test Steps:
    1. Setup the Database Connection:
    Ensure that the database connection is correctly established using the DatabaseConfig class.

    2. Fetch Data from Tables:
    Retrieve data from the Companies, Employees, and Vehicles tables using the corresponding DAO classes.

    3. Validate Employee Data:
    Ensure each employee_id in the Employees table is unique.
    Check that the company_id in the Employees table exists in the Companies table.

    4. Validate Vehicle Data:
    Ensure each vehicle_id in the Vehicles table is unique.
    Check that the company_id in the Vehicles table exists in the Companies table.

    5. Assert Data Integrity:
    Use assertions to validate the relationships and data consistency.
     */
    @Test
    public void testDataIntegrity() throws SQLException {
        // Fetch data from the services
        CompanyService companyService = new CompanyService();
        EmployeeService employeeService = new EmployeeService();
        VehicleService vehicleService = new VehicleService();

        List<Company> companies = companyService.getAllCompanies();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        // Ensure data is not null
        assertNotNull(companies);
        assertNotNull(employees);
        assertNotNull(vehicles);

        // Create sets of valid company IDs
        Set<Integer> validCompanyIds = companies.stream()
                .map(Company::getCompanyId)
                .collect(Collectors.toSet());

        // Validate Employees
        Set<Long> employeeIds = employees.stream()
                .map(Employee::getEmployeeId)
                .collect(Collectors.toSet());
        assertTrue(employeeIds.size() == employees.size(), "Duplicate Employee IDs found");

        for (Employee employee : employees) {
            assertTrue(validCompanyIds.contains(employee.getCompanyId()), "Invalid Company ID in Employees table");
        }

        // Validate Vehicles
        Set<Long> vehicleIds = vehicles.stream()
                .map(Vehicle::getVehicleId)
                .collect(Collectors.toSet());
        assertTrue(vehicleIds.size() == vehicles.size(), "Duplicate Vehicle IDs found");

        for (Vehicle vehicle : vehicles) {
            assertTrue(validCompanyIds.contains(vehicle.getCompanyId()), "Invalid Company ID in Vehicles table");
        }
    }

    /*
    Test Case: Validate Data Consistency

    Objective:
    Ensure that each company has employees and vehicles associated with it and that these employees and vehicles are correctly linked to the same company.

    Test Steps:
    1. Fetch all companies from the Companies table.
    2. Fetch all employees from the Employees table.
    3. Fetch all vehicles from the Vehicles table.
    4. Validate that each company has at least one employee.
    5. Validate that each company has at least one vehicle.
    6. Validate that the employees and vehicles for each company are linked correctly by company_id.
     */
    @Test
    public void testCompanyEmployeeVehicleConsistency() throws SQLException {
        // Fetch data from the tables
        CompanyService companyService = new CompanyService();
        EmployeeService employeeService = new EmployeeService();
        VehicleService vehicleService = new VehicleService();

        List<Company> companies = companyService.getAllCompanies();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        // Ensure data is not null
        assertNotNull(companies);
        assertNotNull(employees);
        assertNotNull(vehicles);

        // Create maps to group employees and vehicles by company ID
        var employeesByCompanyId = employees.stream().collect(Collectors.groupingBy(Employee::getCompanyId));
        var vehiclesByCompanyId = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getCompanyId));

        // Validate each company has at least one employee and one vehicle
        for (Company company : companies) {
            int companyId = company.getCompanyId();
            assertTrue(employeesByCompanyId.containsKey(companyId), "Company " + companyId + " has no employees");
            assertTrue(vehiclesByCompanyId.containsKey(companyId), "Company " + companyId + " has no vehicles");

            // Validate that employees and vehicles belong to the same company
            List<Employee> companyEmployees = employeesByCompanyId.get(companyId);
            List<Vehicle> companyVehicles = vehiclesByCompanyId.get(companyId);

            assertNotNull(companyEmployees, "Company " + companyId + " employees list is null");
            assertNotNull(companyVehicles, "Company " + companyId + " vehicles list is null");

            for (Employee employee : companyEmployees) {
                assertTrue(employee.getCompanyId() == companyId, "Employee " + employee.getEmployeeId() + " does not belong to company " + companyId);
            }

            for (Vehicle vehicle : companyVehicles) {
                assertTrue(vehicle.getCompanyId() == companyId, "Vehicle " + vehicle.getVehicleId() + " does not belong to company " + companyId);
            }
        }
    }

        /*
        testReferentialIntegrity: This test ensures that all foreign key constraints are maintained.
        It checks that every company_id in the Employees and Vehicles tables exists in the Companies table.
         */

        @Test
        public void testReferentialIntegrity() throws SQLException {
            // Fetch data from the services
            CompanyService companyService = new CompanyService();
            EmployeeService employeeService = new EmployeeService();
            VehicleService vehicleService = new VehicleService();

            List<Company> companies = companyService.getAllCompanies();
            List<Employee> employees = employeeService.getAllEmployees();
            List<Vehicle> vehicles = vehicleService.getAllVehicles();

            // Ensure data is not null
            assertNotNull(companies, "Companies list is null");
            assertNotNull(employees, "Employees list is null");
            assertNotNull(vehicles, "Vehicles list is null");

            // Create sets of valid company IDs
            Set<Integer> validCompanyIds = companies.stream()
                    .map(Company::getCompanyId)
                    .collect(Collectors.toSet());

            // Validate Employees
            Set<Long> employeeIds = employees.stream()
                    .map(Employee::getEmployeeId)
                    .collect(Collectors.toSet());
            assertTrue(employeeIds.size() == employees.size(), "Duplicate Employee IDs found");

            for (Employee employee : employees) {
                assertTrue(validCompanyIds.contains(employee.getCompanyId()), "Invalid Company ID in Employees table");
            }

            // Validate Vehicles
            Set<Long> vehicleIds = vehicles.stream()
                    .map(Vehicle::getVehicleId)
                    .collect(Collectors.toSet());
            assertTrue(vehicleIds.size() == vehicles.size(), "Duplicate Vehicle IDs found");

            for (Vehicle vehicle : vehicles) {
                assertTrue(validCompanyIds.contains(vehicle.getCompanyId()), "Invalid Company ID in Vehicles table");
            }
        }

        /*
        testOrphanRecords: This test ensures that there are no orphan records in the Employees and Vehicles tables.
        An orphan record is one that references a non-existent parent record in the Companies table.
         */
        @Test
        public void testOrphanRecords() throws SQLException {
            // Fetch data from the services
            CompanyService companyService = new CompanyService();
            EmployeeService employeeService = new EmployeeService();
            VehicleService vehicleService = new VehicleService();

            List<Company> companies = companyService.getAllCompanies();
            List<Employee> employees = employeeService.getAllEmployees();
            List<Vehicle> vehicles = vehicleService.getAllVehicles();

            // Ensure data is not null
            assertNotNull(companies, "Companies list is null");
            assertNotNull(employees, "Employees list is null");
            assertNotNull(vehicles, "Vehicles list is null");

            // Create sets of valid company IDs
            Set<Integer> validCompanyIds = companies.stream()
                    .map(Company::getCompanyId)
                    .collect(Collectors.toSet());

            // Check for orphan employees
            for (Employee employee : employees) {
                assertTrue(validCompanyIds.contains(employee.getCompanyId()), "Orphan Employee found with ID: " + employee.getEmployeeId());
            }

            // Check for orphan vehicles
            for (Vehicle vehicle : vehicles) {
                assertTrue(validCompanyIds.contains(vehicle.getCompanyId()), "Orphan Vehicle found with ID: " + vehicle.getVehicleId());
            }
        }

        /*
        testDataConstraints: This test validates that the data adheres to the expected constraints.
        It checks for non-null values in critical fields and ensures that numerical values like mileage are within valid ranges.
         */
        @Test
        public void testDataConstraints() throws SQLException {
            // Fetch data from the services
            CompanyService companyService = new CompanyService();
            EmployeeService employeeService = new EmployeeService();
            VehicleService vehicleService = new VehicleService();

            List<Company> companies = companyService.getAllCompanies();
            List<Employee> employees = employeeService.getAllEmployees();
            List<Vehicle> vehicles = vehicleService.getAllVehicles();

            // Ensure data is not null
            assertNotNull(companies, "Companies list is null");
            assertNotNull(employees, "Employees list is null");
            assertNotNull(vehicles, "Vehicles list is null");

            // Validate Company data constraints
            for (Company company : companies) {
                assertNotNull(company.getCompanyName(), "Company name is null for Company ID: " + company.getCompanyId());
            }

            // Validate Employee data constraints
            for (Employee employee : employees) {
                assertNotNull(employee.getFirstName(), "First name is null for Employee ID: " + employee.getEmployeeId());
                assertNotNull(employee.getLastName(), "Last name is null for Employee ID: " + employee.getEmployeeId());
                assertNotNull(employee.getDriverLicense(), "Driver license is null for Employee ID: " + employee.getEmployeeId());
            }

            // Validate Vehicle data constraints
            for (Vehicle vehicle : vehicles) {
                assertTrue(vehicle.getMileage() >= 0, "Invalid mileage for Vehicle ID: " + vehicle.getVehicleId());
                assertNotNull(vehicle.getLastMaintenanceCheck(), "Last maintenance check date is null for Vehicle ID: " + vehicle.getVehicleId());
            }
        }




}
