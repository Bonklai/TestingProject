package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintProfile implements PrintData{
    public void printProfile() throws SQLException  {
        User user  = new User();
        String sql3 = "select * from userinfo where iduser = '" + user.getId() + "'";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()){
            String name = resultSet3.getString("username");
            String surname = resultSet3.getString("surname");
            String email = resultSet3.getString("useremail");
            int age = resultSet3.getInt("age");
            String city = resultSet3.getString("city");
            String address = resultSet3.getString("address");
            int postindex = resultSet3.getInt("postindex");


            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("|Name:"+ name);
            System.out.println("|Surname:" + surname);
            System.out.println("|Email:"+email);
            System.out.println("|Age: "+age);
            System.out.println("|City:"+city);
            System.out.println("|Address:"+address);
            System.out.println("|PostIndex:"+ postindex);
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        connection.close();
        preparedStatement3.close();
    }

    @Override
    public void printDeliveryData() throws SQLException {

    }

    @Override
    public void printCart() throws SQLException {

    }

    @Override
    public void printOneDetail(String tablename, String indexkey) throws SQLException {

    }

}
