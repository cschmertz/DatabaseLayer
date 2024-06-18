package service;

import dao.VehicleDAO;
import model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO = new VehicleDAO();

    public List<Vehicle> getAllVehicles() throws SQLException {
        return vehicleDAO.getAllVehicles();
    }

    public void createVehicle(Vehicle vehicle) throws SQLException {
        // Business logic: Validate the vehicle's data
        if (vehicle.getMileage() < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
        if (vehicle.getLastMaintenanceCheck() == null) {
            throw new IllegalArgumentException("Last maintenance check date cannot be null");
        }
        // Additional business logic can be added here
        vehicleDAO.createVehicle(vehicle);
    }

    public Vehicle getVehicleById(long id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid vehicle ID");
        }
        return vehicleDAO.getVehicleById(id);
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        // Business logic: Validate the vehicle's data before updating
        if (vehicle.getMileage() < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
        // Additional validation or processing can be added here
        vehicleDAO.updateVehicle(vehicle);
    }

    public void deleteVehicle(long id) throws SQLException {
        // Business logic: Ensure the ID is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid vehicle ID");
        }
        // Additional business rules can be enforced here
        vehicleDAO.deleteVehicle(id);
    }

}
