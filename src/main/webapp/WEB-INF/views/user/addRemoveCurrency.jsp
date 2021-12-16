<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<%--@elvariable id="fiat" type="java.lang.Integer"--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Add/Remove Currency</title>
    <jsp:include page="../common/head.jsp"/>
    <script src="js/markets.js" type="text/javascript"></script>
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
    <p><b>Available Balance:</b> ${sign}${currency}</p>
    <form:form modelAttribute="userAddFunds" action="/doAddRemoveCurrency?fiat=${fiat}">
        <form:label path="value">Amount: <br></form:label>
        <form:input path="value" type="value"/>
        <span class="text-error">${errors}</span>

        <form:input path="fiat" type="hidden" value="${fiat}"/>
        <form:input path="GBP" type="hidden" value="${GBP}"/>
        <form:input path="USD" type="hidden" value="${USD}"/>
        <form:input path="EUR" type="hidden" value="${EUR}"/>
        <br>
        <br>
        <button type="submit" class="btn btn-primary w-30 mb-5">Submit</button>
    </form:form>
</div>
</body>
</html>