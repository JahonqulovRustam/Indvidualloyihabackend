package com.medicore.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbCheck {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Medicore_hms", "postgres",
                "Rustam2005")) {
            ResultSet rs = conn.getMetaData().getColumns(null, null, "invoices", null);
            while (rs.next()) {
                System.out.println("COLUMN: " + rs.getString("COLUMN_NAME"));
            }
        }
    }
}
