package dao;

import model.Vehicle;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleDAOTest {
    @Test
    public void testGetAllVehicles() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        assertNotNull(vehicles);
        assertTrue(vehicles.size() > 0);
    }

    // Add more tests for create, update, delete operations
}
