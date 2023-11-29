package Servlets;

import Models.User;
import repository.AccountRepository;
import repository.AccountRepositotyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/loginP")
public class loginServlet extends HttpServlet {

        private static final String DB_USERNAME = "postgres";
        private static final String DB_PASSWORD = "reallyStrongPwd123";
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/hw3";

        AccountRepository accountRepository;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            accountRepository = new AccountRepositotyImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/html/loginP.html").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String accountUsername = request.getParameter("first_name");
            String accountPassword = request.getParameter("password");

            System.out.println(" first_name: " + accountUsername);
            System.out.println(" password: " + accountPassword);

            User user = User.builder()
                    .nameOfUser(accountUsername)
                    .pwdOfUser(accountPassword)
                    .build();


            try {
                if (accountRepository.login(accountUsername, accountPassword, user)) {
                    System.out.println("logged in");
                    response.sendRedirect("/profile");
                } else {
                    // Failed login, redirect to another page
                    System.out.println("incorrect username or password");
                    response.sendRedirect("/tryAgain");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        }
    }