<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<%--@elvariable id="fiat" type="java.lang.Integer"--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Add/Remove Currency</title>
    <jsp:include page="../common/head.jsp"/>
</head>

<jsp:include page="../common/header.jsp"/>

<body>
<div class="container">
    <div class="row mb-3">
        <div class="col-lg-12">
            <h1>Add/Remove Currency</h1>
        </div>
    </div>
    <div class="row mb-3">

        <div class="row mb-3">
            <div class="col-lg-12">
                <h3>Select Currency</h3>
                <a href="/addRemoveCurrency?fiat=1508" id="pound" class="btn btn-outline-primary">&#163;</a> <%--£--%>
                <a href="/addRemoveCurrency?fiat=1505" id="dollar" class="btn btn-outline-primary">$</a> <%--$--%>
                <a href="/addRemoveCurrency?fiat=1506" id="euro" class="btn btn-outline-primary">&#8364;</a> <%--€--%>
            </div>
        </div>

    </div>
    <br>
    <p><b>Available Balance:</b> ${user.GBP}</p>
    <form action="/doAddRemoveCurrency">
        <label name="gbp">GBP: </label>
        <input name="gbp" type="number"/>

        <input type="submit"/>
    </form>
</div>
</body>
</html>