package ModelsEntities;

import Controllers.CartCreate;
import Helpers.CheckStatus;
import Helpers.CheckerRegister;
import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class UserRepository extends User {

    Scanner scanner = new Scanner(System.in);

    public void reg() throws SQLException {

        System.out.print("Name:");
        setName(scanner.next());

        System.out.print("Surname:");
        setSurname(scanner.next());
        System.out.print("Age:");
        setAge(scanner.nextInt());
        System.out.print("Email:");
        String inputemail = scanner.next();
        setEmail(inputemail);
        System.out.print("Password:");
        setPassword(scanner.next());

        String sql1 = "INSERT INTO userinfo (username , surname ,age, isregistered,useremail,userpass,userbalance) VALUES (?,?,?,?,?,?,0)";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.setString(1, getName());
        preparedStatement.setString(2, getSurname());
        preparedStatement.setInt(3, getAge());
        preparedStatement.setBoolean(4, true);
        preparedStatement.setString(5, getEmail());
        preparedStatement.setString(6, getPassword());
        setBalance(0.0);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("User created successfully");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        } else {
            System.out.println("User didn't created");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        setId(inputemail);
        connection.close();
    }


    public void deleteUser() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql2 = "Delete from userinfo where iduser = '" + getId() + "'";
        String sql3 = "Drop table cart"+getId() + " cascade;";
        truncateTempId();
        Statement statement = conn.createStatement();
        statement.execute(sql2);
        statement.execute(sql3);

        System.out.println("User deleted successfully!");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        conn.close();
        statement.close();
    }

    public void login() throws SQLException {
        System.out.println("Please login  | or register");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        String response1 = scanner.next();
        if (response1.equals("login")) {
            while (true) {
                Connection connection = DatabaseUtil.getConnection();
                System.out.print("Email:");
                String emailInput = scanner.next();

                System.out.print("Password:");
                String passwordInput = scanner.next();

                String sql2 = "SELECT useremail, userpass FROM userinfo WHERE useremail ='" + emailInput + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql2);

                boolean loginSuccess = false;
                if (resultSet.next()) {
                    String em = resultSet.getString("useremail");
                    String pass = resultSet.getString("userpass");
                    if (emailInput.equals(em) && passwordInput.equals(pass)) {
                        setId(emailInput);
                        CheckStatus checkStatus = new CheckStatus();

                        if(checkStatus.checkStatus()){
                            System.out.println("Hello admin Mr Daulet!!!");
                            break;
                        }
                        else{
                            System.out.println("Login successful");
                            System.out.println("————————————————————————————————————————————————————————————————————————————————");

                            break;
                        }

                    }
                    else{
                        System.out.println("Login failed, try again");
                    }

                }

            }
        }
        else if (response1.equals("register")){
            CartCreate cartCreate = new CartCreate();
            CheckStatus checkStatus = new CheckStatus();

            reg();
            if(!checkStatus.checkStatus()){
                cartCreate.cartCreate();
            }


        }
        else{
            System.out.println("Wrong option try again");
            login();
        }

    }

}
