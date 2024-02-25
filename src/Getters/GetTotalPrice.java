package Getters;

import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetTotalPrice implements GetFromDatabase {
    public double getTotalPrice() throws SQLException {
        User user = new User();
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String getSumSql = "select sum(priceincart) from cart" + user.getId();
        ResultSet resultSet = statement.executeQuery(getSumSql);
        if (resultSet.next()){
            return resultSet.getDouble("sum");
        }
        return 0;
    }

    @Override
    public double getBalance() throws SQLException {
        return 0;
    }

    @Override
    public double getPrice(String tablename, String choice) throws SQLException {
        return 0;
    }
}
