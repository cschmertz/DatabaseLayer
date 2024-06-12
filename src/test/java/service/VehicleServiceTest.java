package service;

import model.Vehicle;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleServiceTest {
    @Test
    public void testGetAllVehicles() throws SQLException {
        VehicleService vehicleService = new VehicleService();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        assertNotNull(vehicles);
        assertTrue(vehicles.size() > 0);
    }

    // Add more tests for other business logic
}
