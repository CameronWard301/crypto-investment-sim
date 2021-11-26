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
    <p>GBP: £${user.GBP}</p>
    <p>USD: $${user.USD}</p>
    <p>EUR: €${user.EUR}</p>
    <br>
    <h1>Crypto:</h1>
    <p>Bitcoin: ${user.bitcoin} ,   £${user.bitcoin/btc.getCurrentPrice()} ,   ${(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice())*100}%</p>
    <p>Ethereum: ${user.ethereum} ,   £${user.ethereum/eth.getCurrentPrice()} ,   ${(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())/(user.ethereum/eth.getCurrentPrice())*100}%</p>
    <p>Cardano: ${user.cardano} ,   £${user.cardano/ada.getCurrentPrice()} ,   ${(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())/(user.cardano/ada.getCurrentPrice())*100}%</p>
</body>
</html>