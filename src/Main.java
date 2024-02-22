import Applications.Application;
import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.*;

import static java.lang.Class.forName;
public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()){
            System.out.println("Connected");
            Application start = new Application();
            start.start();

        }catch(Exception e){
            User user = new User();
            user.truncateTempId();
            System.out.println("Couldn't connect with database");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }
}