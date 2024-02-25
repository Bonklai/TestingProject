package Repositories;

import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeliveryRepository {
    Scanner scanner = new Scanner(System.in);

    public void reg() throws SQLException {
        System.out.println("Please add your city , address , Post Index");
        System.out.println();
        System.out.print("City:");
        String city = scanner.next();
        System.out.println();
        System.out.print("Address:");
        String address = scanner.next();
        System.out.println();
        System.out.print("Post Index:");
        int postindex = scanner.nextInt();
        User user = new User();
        String sql = "Update userinfo set city = ? , address=? , postindex = ? , regdelivery = true where iduser ='" + user.getId()+"'"; ;
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,city);
        preparedStatement.setString(2,address);
        preparedStatement.setInt(3,postindex);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public boolean deliveryMethod(){
        System.out.println("Please choose the shipping method:\n" +
                "1.Home delivery . Inside the city +2000\n" +
                "2. Delivery to KazPost. Free shipping");
        System.out.print("Option:");
        int choiceDelivery = scanner.nextInt();
        if(choiceDelivery==1) return true;
        else return false;
    }
}
