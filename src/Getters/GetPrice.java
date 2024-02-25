package Getters;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetPrice implements GetFromDatabase {
    @Override
    public double getTotalPrice() throws SQLException {
        return 0;
    }

    @Override
    public double getBalance() throws SQLException {
        return 0;
    }

    public double getPrice(String tablename, String choice) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String buySql = "select price from " + tablename + " where indexkey= " + choice;
        ResultSet resultSet = statement.executeQuery(buySql);
        while (resultSet.next()){
            return resultSet.getDouble("price");
        }
        return 0;
    }
}
