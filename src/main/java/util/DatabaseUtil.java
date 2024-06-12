package util;

import config.DatabaseConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    public static void executeUpdate(String sql) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public static void executeSqlFile(String filePath) throws SQLException, IOException {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            StringBuilder sql = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sql.append(line);
                if (line.endsWith(";")) {
                    stmt.executeUpdate(sql.toString());
                    sql = new StringBuilder();
                }
            }
        }

        // Additional utility methods for database operations can be added here
    }
}
