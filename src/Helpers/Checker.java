package Helpers;

import java.sql.SQLException;

public interface Checker {
    public boolean checkRegister () throws SQLException;
    public boolean checkDelivery () throws SQLException;
    public boolean checkStatus() throws SQLException;


}
