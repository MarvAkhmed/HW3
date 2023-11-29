package repository;


import Models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDisplayRepository{
List<User> displayUsers() ;
}
