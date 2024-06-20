package service;

import model.Company;

import java.sql.SQLException;
import java.util.List;

public interface ICompanyService {
    List<Company> getAllCompanies() throws SQLException;

    void createCompany(Company company) throws SQLException;

    Company getCompanyById(int id) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    void deleteCompany(int id) throws SQLException;
}
