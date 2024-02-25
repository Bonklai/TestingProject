package Database;

import java.sql.SQLException;

public interface IdManager {
    public void setId(String useremail) throws SQLException;
    public void truncateTempId() throws SQLException;
}
