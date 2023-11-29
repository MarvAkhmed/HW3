package repository;

import Models.User;
import repository.UsersDisplayRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDisplayRepositoryImpl implements UsersDisplayRepository {
    private final Connection connection;
    private final Statement statement1;
    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from users";

    public UserDisplayRepositoryImpl(Connection connection,Statement statement1) {
        this.connection = connection;
        this.statement1 = statement1;
    }

    @Override
    public List<User> displayUsers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user =  User.builder()
                        .id(resultSet.getLong("id"))
                        .nameOfUser(resultSet.getString("first_name"))
                        .surnameOfUser(resultSet.getString("last_name"))
                        .emailOfUser(resultSet.getString("email"))
                        .pwdOfUser(resultSet.getString("password"))
                        .confirmedPwdForUser(resultSet.getString("confirm_password"))
                        .ageOfUser(resultSet.getInt("age"))
                        .genderOfUser(resultSet.getString("gender"))
                        .build();

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}