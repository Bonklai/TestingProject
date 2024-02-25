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

public class Cartcontroller extends DBController {
    Scanner scanner = new Scanner(System.in);

    BalanceManager balanceManager = new BalanceManager();
    GetBalance getBalance = new GetBalance();
    DeliveryRepository deliveryRepository = new DeliveryRepository();
    CheckDelivery checkDelivery = new CheckDelivery();
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





}
