package Servlets;


import Models.User;
import lombok.SneakyThrows;
import repository.UserDisplayRepositoryImpl;
import repository.UsersDisplayRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.List;

@WebServlet("/displayUsers")
public class displayUsersServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "reallyStrongPwd123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hw3";

    private UsersDisplayRepository usersDisplayRepository;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersDisplayRepository = new UserDisplayRepositoryImpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List myUsers;

            myUsers = usersDisplayRepository.displayUsers();

            request.setAttribute("myUsers", myUsers);
            request.getRequestDispatcher("/jsp/displayUsers.jsp").forward(request, response);
        }
    }
