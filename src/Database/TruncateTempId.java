package Database;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TruncateTempId implements IdManager {
    @Override
    public void setId(String useremail) throws SQLException {

    }

    public void truncateTempId() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "truncate table tempid";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
