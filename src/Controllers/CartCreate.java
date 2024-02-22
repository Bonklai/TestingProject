package Controllers;

import ModelsEntities.User;
import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartCreate {
    public CartCreate(){}
    public void cartCreate() throws SQLException {
        User user = new User();
        Connection connection = DatabaseUtil.getConnection();
        String sql = "Create table cart" + user.getId() + " (modelincart VARCHAR(50), quantityincart int,priceincart DOUBLE PRECISION,indexkeyincart integer, item varchar(50));";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }

}
