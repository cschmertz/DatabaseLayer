package dao;

import config.DatabaseConfig;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    public List<Vehicle> getAllVehicles() throws SQLException {
        String query = "SELECT * FROM Vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt("vehicle_id"), rs.getInt("mileage"), rs.getDate("last_maintenance_check"), rs.getInt("company_id"));
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    // Other CRUD operations (create, update, delete) can be added here
}
