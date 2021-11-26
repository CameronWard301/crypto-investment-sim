<%  response.setIntHeader("Refresh", 300); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${user.name}'s Portfolio</title>
</head>
<body>
    <h1>Fiat:</h1>
    <p>GBP: &#163;${user.GBP}</p>
    <p>USD: $${user.USD}</p>
    <p>EUR: &#8364;${user.EUR}</p>
    <br>
    <h1>Crypto:</h1>
    <h2>Bitcoin: </h2>
    <p>&#8383;${user.bitcoin}</p>
    <p>&#163;${user.bitcoin/btc.getCurrentPrice()}</p>
    <p>${(user.bitcoin/btc.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
    <br>
    <h2>Ethereum: </h2>
    <p>${user.ethereum}</p>
    <p>&#163;${user.ethereum/eth.getCurrentPrice()}</p>
    <p>${(user.ethereum/eth.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
    <br>
    <h2>Cardano: </h2>
    <p>${user.cardano}</p>
    <p>&#163;${user.cardano/ada.getCurrentPrice()}</p>
    <p>${(user.cardano/ada.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
</body>
</html>