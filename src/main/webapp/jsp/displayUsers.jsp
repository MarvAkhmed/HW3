<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>USERNAME</th>
            <th>SURNAME</th>
            <th>EMAIL</th>
            <th>PASSWORD</th>
            <th>CHECK PASSWORD</th>
            <th>AGE</th>
            <th>GENDER</th>
        </tr>
        <c:forEach var="user" items="${myUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.nameOfUser}</td>
                <td>${user.surnameOfUser}</td>
                <td>${user.emailOfUser}</td>
                <td>${user.pwdOfUser}</td>
                <td>${user.confirmedPwdForUser}</td>
                <td>${user.ageOfUser}</td>
                <td>${user.genderOfUser}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
