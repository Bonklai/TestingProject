package Database;

import Controllers.Cartcontroller;
import Controllers.CatalogController;
import Getters.GetBalance;
import Getters.GetPrice;
import Helpers.CheckStatus;
import Helpers.CheckerRegister;
import ModelsEntities.BalanceManager;
import ModelsEntities.PrintOneDetail;
import ModelsEntities.PrintTable;
import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class DBController  {


    GetBalance getBalance = new GetBalance();
    PrintOneDetail printOneDetail = new PrintOneDetail();
    Cartcontroller cartcontroller = new Cartcontroller();
    ManageDatabase manageDatabase = new ManageDatabase();


    Scanner scanner = new Scanner(System.in);





    public void buyPanel(String tablename) throws SQLException {
        GetPrice getPrice = new GetPrice();
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        CheckStatus checkStatus = new CheckStatus();
        if(checkStatus.checkStatus()){
            manageDatabase.manageDatabaseMenu(tablename);
        }
        else{
            System.out.println("Enter index:");
            System.out.println("0.Back");
            String choice = scanner.next();
            if(choice.equals("0")){

            }
            else{

                printOneDetail.printOneDetail(tablename,choice);
                System.out.print("Balance:"); System.out.println(getBalance.getBalance());
                System.out.print("Price:"); System.out.println(getPrice.getPrice(tablename,choice));
                System.out.print("Are you sure you want to add this product to cart? y/n:");
                String choice2 = scanner.next();

                if(choice2.equals("y")){
                    System.out.println("————————————————————————————————————————————————————————————————————————————————");
                    cartcontroller.addToCart(tablename,choice);

                }
                else{
                    System.out.println("————————————————————————————————————————————————————————————————————————————————");
                }



                statement.close();
                connection.close();

            }
        }
    }












}
