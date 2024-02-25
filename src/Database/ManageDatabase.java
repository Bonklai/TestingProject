package Database;

import ModelsEntities.PrintOneDetail;
import ModelsEntities.PrintTable;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ManageDatabase {
    Scanner scanner = new Scanner(System.in);
    PrintOneDetail printOneDetail = new PrintOneDetail();
    public void manageDatabaseMenu(String tablename) throws SQLException {
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("0.Back");
        System.out.println("1.Add a spare part");
        System.out.println("2.Change quantity");
        System.out.println("3.Change price");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.print("Enter option:");
        int choice = scanner.nextInt();

        if (choice == 2){
            changeQuantity(tablename);
        }
        else if(choice == 3){
            changePrice(tablename);
        } else if (choice == 1) {
            addSparePart(tablename);
        }
        else if(choice == 0){
            System.out.println();
        }
        else{
            System.out.println("Wrong option. Try again!");
            manageDatabaseMenu(tablename);
        }
    }

    public void changeQuantity(String tablename) throws SQLException {
        System.out.println("0.Back");
        System.out.print("Index:");
        String index = scanner.next();
        if(index.equals("0")){}
        else{
            printOneDetail.printOneDetail(tablename,index);
            System.out.print("Enter quantity:");
            String quantity = scanner.next();
            if(quantity.matches("^\\d+$")){
                Connection connection = DatabaseUtil.getConnection();
                String sql = "update " + tablename + " set quantity =" + quantity + " where indexkey=" + index ;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("Successfull");
                preparedStatement.close();
                connection.close();
            }
            else{
                System.out.println("Wrong index.Try again");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }

        }

    }
    public void changePrice(String tablename) throws SQLException {
        System.out.println("0.Back");
        System.out.print("Index:");
        String index = scanner.next();
        if (index.equals("0")){}
        else{
            printOneDetail.printOneDetail(tablename,index);
            System.out.print("Enter price:");
            String price = scanner.next();
            if(price.matches("^\\d+$")){
                Connection connection = DatabaseUtil.getConnection();
                String sql = "update " + tablename + " set quantity =" + price + " where indexkey=" + index ;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();

                System.out.println("Successfull");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                preparedStatement.close();
                connection.close();
            }
            else{
                System.out.println("Wrong index.Try again");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
        }

    }
    public void addSparePart(String tablename) throws SQLException {
        System.out.print("Print model:"); String modell = scanner.next();
        System.out.print("Print quantity:"); String quantityy = scanner.next();
        System.out.print("Print price:"); String pricee = scanner.next();
        System.out.print("Create indexkey:"); String indexkeyy = scanner.next();
        System.out.print("Print detailname:"); String itemm = scanner.next();
        String sql = "insert into "+tablename+" (model,quantity,price,indexkey,item) values ('" + modell + "'," + quantityy+","+pricee+","+indexkeyy+",'"+itemm+"');";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println("Added succesfully!");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        connection.close();
        preparedStatement.close();
        PrintTable printTable = new PrintTable();
        printTable.printTable(tablename);


    }
}
