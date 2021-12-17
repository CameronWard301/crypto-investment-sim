<%--suppress HtmlUnknownTarget --%>
<%--@elvariable id="GBP" type="java.lang.Float"--%>
<%--@elvariable id="EUR" type="java.lang.Float"--%>
<%--@elvariable id="USD" type="java.lang.Float"--%>
<%--@elvariable id="selected" type="java.lang.String"--%>
<%--@elvariable id="fiat" type="java.lang.Integer"--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Add/Remove Currency</title>
    <jsp:include page="../common/head.jsp"/>
    <script src="js/addFunds.js" type="text/javascript"></script>
</head>

<jsp:include page="../common/header.jsp"/>

<body>
<input type="hidden" id="selected" value="${selected}">
<div class="container">
    <div class="row mb-3">
        <div class="col-lg-12">
            <h1>Add/Remove Currency</h1>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h3>Select Currency</h3>
            <button id="poundBtn" class="btn btn-outline-primary">&#163;</button>
            <%--£--%>
            <button id="dollarBtn" class="btn btn-outline-primary">$</button>
            <%--$--%>
            <button id="euroBtn" class="btn btn-outline-primary">&#8364;</button>
            <%--€--%>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-8 col-12">
            <p id="available"><b>Available Balance:</b></p>
            <%--@elvariable id="userAddFunds" type="com.crypto.investment.sim.model.UserAddFunds"--%>
            <form:form modelAttribute="userAddFunds" action="/updateCurrency">
                <form:hidden path="fiat" value="GBP" id="fiat"/>
                <div class="form-group">
                    <form:label path="value">Amount: <br></form:label>
                    <form:input path="value" class="form-control"/>
                    <form:errors path="value" class="text-error"/>
                </div>
                <form:input path="GBP" type="hidden" id="GBP" value="${GBP}"/>
                <form:input path="EUR" type="hidden" id="EUR" value="${EUR}"/>
                <form:input path="USD" type="hidden" id="USD" value="${USD}"/>


                <button type="submit" class="btn btn-primary w-30 mt-3">Submit</button>
            </form:form>
        </div>
    </div>


</div>
</body>
</html>