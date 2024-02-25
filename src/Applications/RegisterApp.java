package Applications;

import ModelsEntities.UserRepository;

import java.sql.*;
import java.util.Scanner;
import Helpers.CheckerRegister;

public class RegisterApp  {

    UserRepository reg = new UserRepository();
    ApplicationMain applicationMain = new ApplicationMain();
    Scanner scanner = new Scanner(System.in);

    public void start() throws SQLException {
        CheckerRegister temp = new CheckerRegister();
        while(true){
            if (!temp.checkRegister()){
                System.out.println("You arent registered , please register");
                System.out.println("-----------------------------------------------");
                System.out.print("Exit/Register:");
                String exit = scanner.next();
                if( exit.equalsIgnoreCase("Exit")) break;
                reg.reg();
                if (applicationMain.startAppMain()) break;}
            else {
                reg.login();
                if (applicationMain.startAppMain()) break;

            }
        }

    }
}


