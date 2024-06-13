package model;

import java.sql.Date;
import java.time.LocalDate;

public class Vehicle {
    private long vehicleId;
    private int mileage;
    private Date lastMaintenanceCheck;
    private int companyId;

    public Vehicle(long vehicleId, int mileage, Date lastMaintenanceCheck, int companyId) {
        this.vehicleId = vehicleId;
        this.mileage = mileage;
        this.lastMaintenanceCheck = lastMaintenanceCheck;
        this.companyId = companyId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getLastMaintenanceCheck() {
        return lastMaintenanceCheck.toLocalDate();
    }

    public void setLastMaintenanceCheck(Date lastMaintenanceCheck) {
        this.lastMaintenanceCheck = lastMaintenanceCheck;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
