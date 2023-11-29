package Servlets;

import Models.User;
import repository.AccountRepositotyImpl;
import repository.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/signUP")
public class SignUP_servlet extends HttpServlet {
public static final String DB_URL = "jdbc:postgresql://localhost:5432/hw3";
public static final String DB_USERNAME = "postgres";
public static final String DB_PASSWORD = "reallyStrongPwd123";
private AccountRepository accountsRepository;

@Override
public void init() throws ServletException{
    try{
        Class.forName("org.postgresql.Driver");
    }catch (ClassNotFoundException e){
        throw new RuntimeException(e);
    }
    try {
        Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        Statement statement = connection.createStatement();
        //Initialize the accounts Repository field
        accountsRepository = new AccountRepositotyImpl(connection);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
            request.getRequestDispatcher("/html/signUP.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userusername = request.getParameter("username");
        String userSurname = request.getParameter("surname");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String userConfirmedPassword = request.getParameter("confirm_password");
        Integer userAge = Integer.valueOf(request.getParameter("age"));
        String userGender = request.getParameter("gender");

        User user = User.builder()
                .nameOfUser(userusername)
                .surnameOfUser(userSurname)
                .emailOfUser(userEmail)
                .pwdOfUser(userPassword)
                .confirmedPwdForUser(userConfirmedPassword)
                .ageOfUser(userAge)
                .genderOfUser(userGender)
                .build();

        try{
            accountsRepository.save(user);
            System.out.println("user saved!");
            response.sendRedirect("/signUP_successfully");
        } catch (SQLException e) {
            response.sendRedirect("/tryAgain");
        }

    }


}
