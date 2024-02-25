package Getters;

import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetBalance implements GetFromDatabase {

    @Override
    public double getTotalPrice() throws SQLException {
        return 0;
    }
    public  double getBalance() throws SQLException {
        User user = new User();
        String balanceData = "select userbalance from userinfo where iduser = '" + user.getId() + "'";
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(balanceData);
        while (resultSet.next()){
            return  resultSet.getDouble("userbalance");
        }
        return 0;
    }

    @Override
    public double getPrice(String tablename, String choice) throws SQLException {
        return 0;
    }


}
