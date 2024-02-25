package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintDeliveryData implements PrintData{
    @Override
    public void printProfile() throws SQLException {
    }

    public  void printDeliveryData() throws SQLException {
        String sql3 = "select city,address,postindex from userinfo ";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()){
            String city = resultSet3.getString("city");
            String address = resultSet3.getString("address");
            int postindex = resultSet3.getInt("postindex");

            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("|City:"+city);
            System.out.println("|Address:"+address);
            System.out.println("|PostIndex:"+ postindex);
            System.out.println("————————————————————————————————————————————————————————————————————————————————");

        }
    }

    @Override
    public void printCart() throws SQLException {

    }

    @Override
    public void printOneDetail(String tablename, String indexkey) throws SQLException {

    }
}
