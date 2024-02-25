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

    public void balanceMenu() throws SQLException {
        GetBalance getBalance = new GetBalance();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("Your balance:"+getBalance.getBalance());
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.Top up your balance");
        System.out.println("2.Back");
        System.out.print("Option:");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.print("Enter card number:");
            long cardNum = scanner.nextLong();
            System.out.print("Enter card expiration date MM/YY:");
            int expirationDate = scanner.nextInt();
            System.out.print("CVV:");
            int cvv = scanner.nextInt();


            System.out.print("Enter balance:");
            int amount = scanner.nextInt();

            if (amount>0){
                addBalance(amount);
                System.out.println("Balance updated");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            else{
                System.out.println("OH MANN YOU WANNA DECREASE YOUR BALANCE??? ITS NOT POSSIBLE");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }

        }
        else if(choice == 2){
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
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

