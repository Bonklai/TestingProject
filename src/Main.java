import Applications.RegisterApp;
import Database.TruncateTempId;
import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.*;

import static java.lang.Class.forName;
public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()){
            System.out.println("Connected");
            RegisterApp start = new RegisterApp();
            start.start();
        }catch(Exception e){
            TruncateTempId truncateTempId = new TruncateTempId();
            truncateTempId.truncateTempId();
            System.out.println("Couldn't connect with database");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();}}
}