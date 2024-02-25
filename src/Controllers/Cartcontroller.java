package Controllers;

import java.sql.*;
import java.util.Scanner;
import Database.DBController;
import Getters.GetBalance;
import Getters.GetTotalPrice;
import Helpers.CheckDelivery;
import ModelsEntities.BalanceManager;
import ModelsEntities.PrintCart;
import ModelsEntities.PrintData;
import ModelsEntities.User;
import Repositories.DeliveryRepository;
import Helpers.CheckerRegister;
import Utilities.DatabaseUtil;

public class Cartcontroller {
    Scanner scanner = new Scanner(System.in);

    BalanceManager balanceManager = new BalanceManager();
    GetBalance getBalance = new GetBalance();
    DeliveryRepository deliveryRepository = new DeliveryRepository();
    private CheckDelivery checkDelivery = CheckDelivery.getInstance();
    User user = new User();
    GetTotalPrice getTotalPrice = new GetTotalPrice();


    public void cartMenu() throws SQLException {
        System.out.println("Your Cart:");
        PrintCart printCart = new PrintCart();
        printCart.printCart();
        totalMenu();
    }
    public void totalMenu() throws SQLException {
        double total = getTotalPrice.getTotalPrice();
        if(total>0){
            System.out.println("Total price");
            System.out.println(total);
            System.out.print("Your balance:");
            System.out.println(getBalance.getBalance());
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("1.Purchase");
            System.out.println("2.Delete a detail");
            System.out.println("3.Back");

            System.out.print("Option:"); int choiceMain = scanner.nextInt();
            System.out.println("————————————————————————————————————————————————————————————————————————————————");

            if(choiceMain == 1){
                System.out.print("Are you sure you want to order this?y/n:");
                String choice = scanner.next();

                if (choice.equals("y")){
                    if (deliveryRepository.deliveryMethod()){
                        if(checkDelivery.checkDelivery()){
                            total+=2000;
                            if(getBalance.getBalance()>=total){
                                System.out.print("Total:");
                                System.out.println(total);
                                balanceManager.pay(total);

                                System.out.println("Successful purchase");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                                afterPurchase();
                            }
                            else{
                                System.out.println("You dont have enough money :( Top up your balance");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                            }
                        }
                        else{
                            System.out.println("You arent registered your delivery.Please go back and register");
                            System.out.println("————————————————————————————————————————————————————————————————————————————————");
                        }
                    }
                    else{
                        if(checkDelivery.checkDelivery()){
                            if(getBalance.getBalance()>=total){
                                balanceManager.pay(total);

                                System.out.println("Successful purchase");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                                afterPurchase();
                            }
                            else{
                                System.out.println("You dont have enough money :( Top up your balance");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                            }
                        }
                        else{
                            System.out.println("You arent registered your delivery.Please go back and register");
                            System.out.println("————————————————————————————————————————————————————————————————————————————————");
                        }
                    }

                }
                else{
                    totalMenu();
                }
            }
            else if(choiceMain == 2){
                System.out.print("Enter indexkey to delete:");
                int key = scanner.nextInt();
                deleteDetail(key);
            }



        }
        else{
            System.out.println("Your cart is empty");
            System.out.println();
            System.out.println();

        }
    }


    public void deleteDetail(int key) throws SQLException {
        String deleteSql = "delete from cart" + user.getId()+ " where indexkeyincart = ?";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1,key);
        int affected = preparedStatement.executeUpdate();
        if(affected>0){
            System.out.println("Successfully deleted");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            totalMenu();
        }
        else {
            System.out.println("An entry with this indexkey was not found.Try again");
            totalMenu();
            System.out.println();
            System.out.println();
        }
    }
    public void afterPurchase() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sqlgetInxdex = "Select indexkeyincart from cart" + user.getId();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlgetInxdex);

        while(resultSet.next()){
            int index = resultSet.getInt("indexkeyincart");
            String tablename ="";
            if(index>=100 && index<=199) tablename = "batteriesforiphone";
            else if (index>=200 && index<=299) tablename = "displayforiphone";
            else if (index>=300 && index<=399) tablename = "glassforiphone3in1";
            else if(index>=400 && index<=499) tablename = "touchglassipad";
            else if(index>=500 && index<=599) tablename = "displayonipad";
            else if(index>=600 && index<=699) tablename = "batteryforipad";
            else if(index>=700 && index<=799) tablename = "batteryformacbook";
            else if(index>=800 && index<=899) tablename = "matricesformacbook";
            else if(index>=900 && index<=999) tablename = "keyboardsformacbook";


            String updateSQL = "UPDATE " + tablename + " set quantity = quantity-1 where indexkey =" + String.valueOf(index);
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();
        }
        String clearSQL = "truncate table cart";
        statement.executeUpdate(clearSQL);
    }
    public void addToCart(String tablename,String choice) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "Select * from " + tablename +" where indexkey = " + choice;
        String cartSql = "INSERT into cart" + user.getId() + " (modelincart,quantityincart,priceincart,indexkeyincart,item) values (?,?,?,?,?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        PreparedStatement preparedStatement2 = connection.prepareStatement(cartSql);
        ResultSet resultSet =   preparedStatement1.executeQuery();
        if(resultSet.next()){
            preparedStatement2.setString(1,resultSet.getString("model"));
            preparedStatement2.setDouble(2,resultSet.getDouble("quantity"));
            preparedStatement2.setInt(3,resultSet.getInt("price"));
            preparedStatement2.setInt(4,resultSet.getInt("indexkey"));
            preparedStatement2.setString(5,resultSet.getString("item"));
            preparedStatement2.executeUpdate();
        }

        System.out.println("Your product has been successfully added to the shopping cart");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
    }





}
