    package Helpers;

    import Utilities.DatabaseUtil;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import ModelsEntities.User;

    public class CheckerRegister implements Checker {

        public CheckerRegister(){
        }
        User user = new User();

        public boolean checkRegister() throws SQLException {
            Connection connection = DatabaseUtil.getConnection();
            String rq1 = "Select isRegistered From userinfo";
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery(rq1);
            if(resultSet1.next()){
                boolean temp = resultSet1.getBoolean("isregistered");
                if (!temp){
                    return false;
                }
                else{
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean checkDelivery() throws SQLException {
            return false;
        }

        @Override
        public boolean checkStatus() throws SQLException {
            return false;
        }


    }
