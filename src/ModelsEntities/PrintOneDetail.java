package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintOneDetail implements PrintData {
    @Override
    public void printProfile() throws SQLException {

    }

    @Override
    public void printDeliveryData() throws SQLException {

    }

    @Override
    public void printCart() throws SQLException {

    }

    public void printOneDetail(String tablename, String indexkey) throws SQLException {

        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String buySql = "select * from " + tablename + " where indexkey= " + indexkey;
        ResultSet resultSet = statement.executeQuery(buySql);
        if(resultSet.next()){
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("|Model                 Quantity    price       Indexkey     Detail             |");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.printf("|%-20s|", resultSet.getString("model"));
            System.out.printf("%10d  |", resultSet.getInt("quantity"));
            System.out.printf("%8.2f  |", resultSet.getDouble("price"));
            System.out.printf("%10d|",resultSet.getInt("indexkey"));
            System.out.printf("%-22s|", resultSet.getString("item"));
            System.out.println();
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
    }
}
