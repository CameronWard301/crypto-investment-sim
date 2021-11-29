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
    <title>Crypto Sim | ${user.name}'s Portfolio</title>
</head>

<jsp:include page="../common/header.jsp"/>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg">
            <h1>Fiat:</h1>
            <p>GBP: &#163;${user.GBP}</p>
            <p>USD: $${user.USD}</p>
            <p>EUR: &#8364;${user.EUR}</p>
        </div>
    </div>
    <div class="row">
        <h1>Crypto:</h1>
        <div class="col-lg-4">
            <h2>Bitcoin: </h2>
            <p>&#8383;${user.bitcoin}</p>
            <p>&#163;${user.bitcoin/btc.getCurrentPrice()}</p>
            <p>${(user.bitcoin/btc.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
        </div>
        <div class="col-lg-4">
            <h2>Ethereum: </h2>
            <p>${user.ethereum}</p>
            <p>&#163;${user.ethereum/eth.getCurrentPrice()}</p>
            <p>${(user.ethereum/eth.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
        </div>
        <div class="col-lg-4">
            <h2>Cardano: </h2>
            <p>${user.cardano}</p>
            <p>&#163;${user.cardano/ada.getCurrentPrice()}</p>
            <p>${(user.cardano/ada.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
        </div>
    </div>
</div>

</body>
</html>