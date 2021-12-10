<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>Crypto Sim | Login</title>
</head>

<jsp:include page="../common/header.jsp"/>
<body>

<div class="container">
    <form action="/addlogin">
        <label>Enter Username:</label> <br>
        <input name="username"> <br>
        <b>${Error}</b> <br>
        <label>Enter Password</label><br>
        <input name="password"><br> <br>
        <input type="submit">
    </form>
</div>


</body>
</html>
