package service;

import model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleService {
    List<Vehicle> getAllVehicles() throws SQLException;

    void createVehicle(Vehicle vehicle) throws SQLException;

    Vehicle getVehicleById(long id) throws SQLException;

    void updateVehicle(Vehicle vehicle) throws SQLException;

    void deleteVehicle(long id) throws SQLException;
}
