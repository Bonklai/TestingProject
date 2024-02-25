package Getters;

import java.sql.SQLException;

public interface GetFromDatabase {
    public double getTotalPrice() throws SQLException;
    public  double getBalance() throws SQLException;
    public double getPrice(String tablename, String choice) throws SQLException;
}
