<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>

    <form action="/addlogin">
        <label>Enter Username:</label> <br>
        <input name="username"> <br>
        <b>${Error}</b> <br>
        <label>Enter Password</label><br>
        <input name="password"><br> <br>
        <input type="submit">
    </form>



</body>
</html>
