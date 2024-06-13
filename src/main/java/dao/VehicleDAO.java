package dao;

import config.DatabaseConfig;
import model.Vehicle;

import java.sql.*;
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
                Vehicle vehicle = new Vehicle(
                        rs.getLong("vehicle_id"), // Ensure vehicle_id is BIGINT in DB schema
                        rs.getInt("mileage"),
                        rs.getDate("last_maintenance_check"),
                        rs.getInt("company_id"));
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public void createVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO Vehicles (vehicle_id, mileage, last_maintenance_check, company_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, vehicle.getVehicleId()); // Use setLong for BIGINT
            stmt.setInt(2, vehicle.getMileage());
            stmt.setDate(3, Date.valueOf(vehicle.getLastMaintenanceCheck()));
            stmt.setInt(4, vehicle.getCompanyId());
            stmt.executeUpdate();
        }
    }

    public Vehicle getVehicleById(long vehicleId) throws SQLException {
        String query = "SELECT * FROM Vehicles WHERE vehicle_id = ?";
        Vehicle vehicle = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, vehicleId); // Use setLong for BIGINT
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle(
                            rs.getLong("vehicle_id"), // Ensure vehicle_id is BIGINT in DB schema
                            rs.getInt("mileage"),
                            rs.getDate("last_maintenance_check"),
                            rs.getInt("company_id"));
                }
            }
        }
        return vehicle;
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE Vehicles SET mileage = ?, last_maintenance_check = ?, company_id = ? WHERE vehicle_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getMileage());
            stmt.setDate(2, Date.valueOf(vehicle.getLastMaintenanceCheck()));
            stmt.setInt(3, vehicle.getCompanyId());
            stmt.setLong(4, vehicle.getVehicleId()); // Use setLong for BIGINT
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(long vehicleId) throws SQLException {
        String query = "DELETE FROM Vehicles WHERE vehicle_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, vehicleId); // Use setLong for BIGINT
            stmt.executeUpdate();
        }
    }


}
