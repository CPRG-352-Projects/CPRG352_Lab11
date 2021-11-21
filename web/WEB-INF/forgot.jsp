<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Page</title>
    </head>
    <body>
        <h1>Forgot Password</h1>

        <p>Please enter your email address to retrieve your password</p>
        <form action="forgot" method="post">
            Email Address: <input type="text" name="email" value=""><br>
            <input type="submit" value="Submit">
        </form>
        <c:if test="${message}">
            <h4 class="text-success">If the address you entered is valid, you will receive an email very soon. Please check your email for your password.</h4>
        </c:if>
    </body>
</html>
