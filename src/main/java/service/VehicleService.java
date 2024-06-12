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

    // Other business logic can be added here
}
