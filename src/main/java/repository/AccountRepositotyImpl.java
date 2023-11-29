package repository;

import Models.User;
//задержать метод и показать как они работають
//клас
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositotyImpl implements AccountRepository {
    private  final Connection connection;
    private static final String SQL_INSERT = "INSERT INTO users(first_name, last_name, email, password, confirm_password, age, gender)values";

    public AccountRepositotyImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT + "(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getNameOfUser());
        preparedStatement.setString(2,user.getSurnameOfUser());
        preparedStatement.setString(3, user.getEmailOfUser());
        preparedStatement.setString(4,user.getPwdOfUser());
        preparedStatement.setString(5,user.getConfirmedPwdForUser());
        preparedStatement.setInt(6,user.getAgeOfUser());
        preparedStatement.setString(7,user.getGenderOfUser());
              preparedStatement.executeUpdate();
        System.out.println("done");
                }
    @Override
    public boolean login(String username, String password, User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getNameOfUser());
        ResultSet resultSet = preparedStatement.executeQuery();
        String userAcc = "";
        String passAcc = "";

        while (resultSet.next()){
            userAcc = resultSet.getString("first_name");
            passAcc = resultSet.getString("password");
            System.out.println("Retrieved username: " + userAcc);
            System.out.println("Retrieved password: " + passAcc);
        }

        if(userAcc != null && passAcc != null && userAcc.equals(username) && passAcc.equals(password)){
            return true;
        }else {
            return false;
        }

    }
}