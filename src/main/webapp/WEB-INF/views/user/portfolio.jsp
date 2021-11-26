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
    <p>Bitcoin: ${user.bitcoin} ,   £${btc.getCurrentPrice()*user.bitcoin} ,   ${(btc.getCurrentPrice()*user.bitcoin+eth.getCurrentPrice()*user.ethereum+ada.getCurrentPrice()*user.cardano)/(btc.getCurrentPrice()*user.bitcoin)*100}%</p>
    <p>Ethereum: ${user.ethereum} ,   £${eth.getCurrentPrice()*user.ethereum} ,   ${(btc.getCurrentPrice()*user.bitcoin+eth.getCurrentPrice()*user.ethereum+ada.getCurrentPrice()*user.cardano)/(eth.getCurrentPrice()*user.ethereum)*100}%</p>
    <p>Cardano: ${user.cardano} ,   £${ada.getCurrentPrice()*user.cardano} ,   ${(btc.getCurrentPrice()*user.bitcoin+eth.getCurrentPrice()*user.ethereum+ada.getCurrentPrice()*user.cardano)/(ada.getCurrentPrice()*user.cardano)*100}%</p>


</body>
</html>