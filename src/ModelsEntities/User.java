package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.*;

public class User {

    private String name;
    private String surname;
    private  String email;
    private int age;
    private String password;

    private String city;
    private String address;
    private int postindex;

    private double balance;


    public User() {
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {this.age = age;}

    public void setPassword(String password) {this.password = password;}

    public void setBalance(double balance) {this.balance = balance;}


    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostindex(int postindex) {
        this.postindex = postindex;
    }


    public int getPostindex() {
        return postindex;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {return age;}

    public String getPassword() {return password;}
    public String getId() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "select tempid from tempid";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Integer userid = 0;
        if(resultSet.next()){
            userid = resultSet.getInt("tempid");
        }
        String res = userid.toString();
        return res;
    }
    public void setId(String useremail) throws SQLException{
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
    public void truncateTempId() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "truncate table tempid";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }


}
