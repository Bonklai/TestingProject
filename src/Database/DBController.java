package Database;

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

    User user = new User();
    GetBalance getBalance = new GetBalance();
    PrintOneDetail printOneDetail = new PrintOneDetail();


    Scanner scanner = new Scanner(System.in);





    public void buyPanel(String tablename) throws SQLException {
        GetPrice getPrice = new GetPrice();
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        CheckStatus checkStatus = new CheckStatus();
        if(checkStatus.checkStatus()){
            manageDatabaseMenu(tablename);
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
                    addToCart(tablename,choice);

                }
                else{
                    System.out.println("————————————————————————————————————————————————————————————————————————————————");
                }



                statement.close();
                connection.close();

            }
        }
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
