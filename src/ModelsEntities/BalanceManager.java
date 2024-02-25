package ModelsEntities;

import Getters.GetBalance;
import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class BalanceManager {
    User user = new User();

    public BalanceManager(){
    }
    Scanner scanner = new Scanner(System.in);

    public void addBalance(double amount) throws SQLException {

        Connection connection = DatabaseUtil.getConnection();
        String addBal = "update userinfo set userbalance = userbalance + " + amount + " where iduser ='" + user.getId() + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(addBal);
        statement.close();
        connection.close();
    }


    public void pay(double price) throws SQLException {
        User user = new User();
        Connection connection = DatabaseUtil.getConnection();
        String paySql = "update userinfo set userbalance = userbalance - " + price + " where iduser = '" + user.getId() + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(paySql);
        statement.close();
        connection.close();
        System.out.println("Successful purchase");
    }
}

