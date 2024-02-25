package Helpers;

import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckStatus implements  Checker{
    @Override
    public boolean checkRegister() throws SQLException {
        return false;
    }

    @Override
    public boolean checkDelivery() throws SQLException {
        return false;
    }

    public boolean checkStatus() throws SQLException {
        User user = new User();
        Connection connection = DatabaseUtil.getConnection();
        String sql = "select stat from userinfo where iduser = '" + user.getId() + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean isTrue = false;
        if(resultSet.next()){
            isTrue = resultSet.getBoolean("stat");
        }
        if(isTrue){
            return true;
        }
        return false;
    }
}
