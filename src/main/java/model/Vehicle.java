package model;

import java.sql.Date;

public class Vehicle {
    private int vehicleId;
    private int mileage;
    private Date lastMaintenanceCheck;
    private int companyId;

    public Vehicle(int vehicleId, int mileage, Date lastMaintenanceCheck, int companyId) {
        this.vehicleId = vehicleId;
        this.mileage = mileage;
        this.lastMaintenanceCheck = lastMaintenanceCheck;
        this.companyId = companyId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Date getLastMaintenanceCheck() {
        return lastMaintenanceCheck;
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
