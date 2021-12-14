<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>Crypto Sim | Signup</title>
</head>

<jsp:include page="../common/header.jsp"/>
<body>

<div class="container">
${Error}
<form:form action="/signup" modelAttribute="user" method="post" >
    <form:label path="firstName">Enter firstname:</form:label>
    <form:input name ="firstName" path="firstName"/>
    <form:errors path="firstName"/><br/>

    <form:label path="lastName">Enter lastname:</form:label>
    <form:input name = "lastName" path="lastName"/>
    <form:errors path="lastName"/><br/>

    <form:label path="username">Enter username:</form:label>
    <form:input name ="username" path="username"/>
    <form:errors path="username"/><br/>


    <form:label path="hashPassword">Enter password:</form:label>
    <form:input name = "hashPassword" path="hashPassword"/>
    <form:errors path="hashPassword"/><br/>


    <input type="submit"/><form:errors/>
</form:form>

</div>
</body>
</html>