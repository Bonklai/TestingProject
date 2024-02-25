package Controllers;

import java.sql.*;
import java.util.Scanner;
import Database.DBController;
import Helpers.CheckStatus;
import Helpers.CheckerRegister;
import ModelsEntities.PrintTable;

public class CatalogController extends DBController {
    public CatalogController(){}
    CheckStatus checkStatus = new CheckStatus();
    Scanner scanner = new Scanner(System.in);
    PrintTable printTable = new PrintTable();


    public void startCatalog() throws SQLException {
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("0.Back");
        System.out.println("1.Batteries for iPhone");
        System.out.println("2.Display for iPhone");
        System.out.println("3.Glass for iPhone");
        System.out.println("4.Touch glass for iPad");
        System.out.println("5.Display for iPad");
        System.out.println("6.Battery for iPad");
        System.out.println("7.Battery for Mac");
        System.out.println("8.Matrices for Mac");
        System.out.println("9.Keyboards for Mac");
        System.out.print("Option:");
        int option = scanner.nextInt();
        boolean accessAdmin = false;
        if(checkStatus.checkStatus() ){
            System.out.println("0.Manage catalog");
            accessAdmin = true;
        }

        if(option == 1) {

            printTable.printTable("batteriesforiphone");
            buyPanel("batteriesforiphone");
        }
        else if(option==2){
            printTable.printTable("displayforiphone");
            buyPanel("displayforiphone");
        }
        else if(option == 3){
            printTable.printTable("glassforiphone3in1");
            buyPanel("glassforiphone3in1");
        }
        else if(option == 4){
            printTable.printTable("touchglassipad");
            buyPanel("touchglassipad");
        }
        else if(option == 5){
            printTable.printTable("displayonipad");
            buyPanel("displayonipad");
        }
        else if(option == 6){
            printTable.printTable("batteryforipad");
            buyPanel("batteryforipad");
        }
        else if(option == 7){
            printTable.printTable("batteryformacbook");
            buyPanel("batteryformacbook");
        }
        else if(option == 8){
            printTable.printTable("matricesformacbook");
            buyPanel("matricesformacbook");
        }
        else if(option == 9){
            printTable.printTable("keyboardsformacbook");
            buyPanel("matricesformacbook");
        }
        else if(option == 0){
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        else if(option!=0){
            System.out.println("Wrong option. Try again!");
            startCatalog();
        }
    }
}


