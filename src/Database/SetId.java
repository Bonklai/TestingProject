package Database;

import Utilities.DatabaseUtil;

import java.sql.*;

public class SetId implements  IdManager{
    public void setId(String useremail) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Select iduser from userinfo where useremail = '" + useremail + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        Integer temp = 0;
        if(resultSet.next()){
            temp = resultSet.getInt("iduser");
        }
        String tempid = temp.toString();
        String sql2 = "Insert into tempid (tempid) values(" + tempid + ");";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.executeUpdate();
    }

    @Override
    public void truncateTempId() throws SQLException {

    }
}
