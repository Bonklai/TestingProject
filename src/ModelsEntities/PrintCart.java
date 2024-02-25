package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.*;

public class PrintCart implements PrintData {
    @Override
    public void printProfile() throws SQLException {

    }

    @Override
    public void printDeliveryData() throws SQLException {

    }

    public void printCart()  throws SQLException {
        User user = new User();
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Select * from cart" + user.getId() ;
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("|Model                 Quantity    price       Indexkey     Detail             |");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        while (resultSet.next()) {
            int colCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                String formattedValue = "";
                switch (metaData.getColumnType(i)) {
                    case java.sql.Types.INTEGER:
                        if (i == colCount) {
                            formattedValue = String.format("%10d|", resultSet.getInt(i));
                        } else {
                            formattedValue = String.format("%10d  |", resultSet.getInt(i));
                        }
                        break;
                    case java.sql.Types.DOUBLE:
                        formattedValue = String.format("%8.2f  |", resultSet.getDouble(i));
                        break;
                    default:
                        if (i == 1) {
                            formattedValue = String.format("|%-20s|", resultSet.getString(i));
                        }
                        else if(i==5){
                            formattedValue = String.format("%-20s|", resultSet.getString(i)) ;
                        }
                        else {
                            formattedValue = String.format("%-20s|", resultSet.getString(i));
                        }
                        break;
                }
                System.out.print(formattedValue);
            }
            System.out.println();
            System.out.println("————————————————————————————————————————————————————————————————————————————————");


        }
    }

    @Override
    public void printOneDetail(String tablename, String indexkey) throws SQLException {

    }
}
