package ModelsEntities;

import java.sql.SQLException;

public interface PrintData {
    public void printProfile() throws SQLException;
    public  void printDeliveryData() throws SQLException;
    public void printCart() throws SQLException;
    public void printOneDetail(String tablename,String indexkey) throws SQLException;
}
