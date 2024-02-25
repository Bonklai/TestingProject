package Helpers;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDelivery implements Checker {
    public CheckDelivery(){}

    @Override
    public boolean checkRegister() throws SQLException {
        return false;
    }

    public boolean checkDelivery() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "Select regdelivery from userinfo";
        Statement statement = connection.createStatement();
        ResultSet resultSet =statement.executeQuery(sql);
        if(resultSet.next()){
            boolean temp = resultSet.getBoolean("regdelivery");
            if(!temp) return false;
            else return true;
        }
        return false;
    }

    @Override
    public boolean checkStatus() throws SQLException {
        return false;
    }
}
