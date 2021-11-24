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
    <p>Bitcoin: ${user.bitcoin}</p>
    <p>Ethereum: ${user.ethereum}</p>
    <p>Cardano: ${user.cardano}</p>
</body>
</html>