package dao;

import model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleDAO {
    List<Vehicle> getAllVehicles() throws SQLException;

    void createVehicle(Vehicle vehicle) throws SQLException;

    Vehicle getVehicleById(long vehicleId) throws SQLException;

    void updateVehicle(Vehicle vehicle) throws SQLException;

    void deleteVehicle(long vehicleId) throws SQLException;

}
