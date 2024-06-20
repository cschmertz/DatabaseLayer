package dao;

import model.Company;

import java.sql.SQLException;
import java.util.List;

public interface ICompanyDAO {
    List<Company> getAllCompanies() throws SQLException;

    void createCompany(Company company) throws SQLException;

    Company getCompanyById(int companyId) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    void deleteCompany(int companyId) throws SQLException;

}
