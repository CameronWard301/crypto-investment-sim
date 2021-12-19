<%--suppress ELValidationInJSP --%>
<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<%--@elvariable id="btc" type="com.crypto.investment.sim.model.Coin"--%>
<%--@elvariable id="ada" type="com.crypto.investment.sim.model.Coin"--%>
<%--@elvariable id="eth" type="com.crypto.investment.sim.model.Coin"--%>
<% response.setIntHeader("Refresh", 300); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>Crypto Sim | ${user.firstName}'s Portfolio</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<jsp:include page="../common/header.jsp"/>

<body>
<div class="container">
    <input id="chartData" type="hidden" value='${portfolioHistory}'>
    <div class="row">
        <div class="col-lg-12">
            <h2>Are you sure you want to reset all fiat and crypto values in your portfolio to 0?</h2>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-lg-12">
<%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
            <a href="/confirmReset" class="btn btn-red btn-primary btn-lg">Yes</a>
    <%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
            <a href="/portfolio" class="btn btn-primary btn-lg">No</a>
        </div>
    </div>
</div>
</body>
</html>