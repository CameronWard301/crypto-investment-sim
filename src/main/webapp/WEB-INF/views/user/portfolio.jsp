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
    <div class="row mb-4">
        <h1 class="mb-4">Fiat:</h1>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <svg class="card-img-top w-25 mx-auto filter-primary" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M19.888 19.744c-1.229.588-1.88.732-3.018.732-2.735 0-4.233-2.064-7.453-.977.489-.641 2.698-3 2.431-6.5h4.152v-1.988h-4.559c-.708-2.295-1.913-4.278-.517-6.471 1.441-2.263 4.976-1.733 5.967 1.484l3.561-.521c-.654-4.05-4.069-5.503-6.884-5.503-4.528 0-9.066 3.621-6.973 11.012h-2.595v1.988h3.068c.692 3.823-.458 5.797-2.958 7.901l1.796 3.099c4.771-2.849 7.205 0 11.499 0 1.296-.008 2.394-.309 3.595-.994l-1.112-3.262z"></path></svg>
                <div class="card-body">
                    <h2 class="card-title">GBP:</h2>
                    <p class="card-text">Amount: &#163;${user.GBP}</p>
                    <form action="/addfunds">
                        <label for="number">Add funds </label>
                        <input id="number" type="number">
                        <input type="submit">
                    </form>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <svg class="card-img-top w-25 mx-auto filter-primary" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M19 16.166c0-4.289-4.465-5.483-7.887-7.091-2.079-1.079-1.816-3.658 1.162-3.832 1.652-.1 3.351.39 4.886.929l.724-3.295c-1.814-.551-3.437-.803-4.885-.841v-2.036h-2v2.134c-3.89.535-5.968 2.975-5.968 5.7 0 4.876 5.693 5.62 7.556 6.487 2.54 1.136 2.07 3.5-.229 4.021-1.993.451-4.538-.337-6.45-1.079l-.909 3.288c1.787.923 3.931 1.417 6 1.453v1.996h2v-2.105c3.313-.464 6.005-2.293 6-5.729z"></path></svg>
                <div class="card-body">
                    <h2 class="card-title">USD:</h2>
                    <p class="card-text">Amount: $${user.USD}</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <svg class="card-img-top w-25 mx-auto filter-primary" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M17.728 20.517c-3.488 0-5.613-2.461-6.443-5.517h6.715l.333-2h-7.398c-.059-.664-.064-1.335-.014-2h7.746l.333-2h-7.755c.786-3.106 2.855-5.626 6.154-5.626 1.133 0 2.391.203 3.836.62l.765-3.162c-1.854-.552-3.616-.832-5.244-.832-5.959 0-9.541 4.152-10.594 9h-2.162l-.333 2h2.203c-.049.666-.051 1.334-.007 2h-2.53l-.333 2h3.145c1.033 4.848 4.664 9 11.085 9 1.5 0 3.004-.276 4.476-.821l-.883-3.23c-1.048.378-2.088.568-3.095.568z"></path> </svg>
                <div class="card-body">
                    <h2 class="card-title">EUR:</h2>
                    <p class="card-text">Amount: &#8364;${user.EUR}</p>
                </div>
            </div>
        </div>

    </div>

    <div class="row mb-2">
        <h1 class="mb-4">Your purchased crypto:</h1>
        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/bitcoin-btc-logo.svg?v=014" alt="Bitcoin">
                <div class="card-body">
                    <h2 class="card-title">Bitcoin: </h2>
                    <p class="card-text">Amount: &#8383;${user.bitcoin}</p>
                    <p class="card-text">&#163;${user.bitcoin/btc.getCurrentPrice()}</p>
                    <p class="card-text">${(user.bitcoin/btc.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/ethereum-eth-logo.svg?v=014" alt="Ethereum">
                <div class="card-body">
                    <h2 class="card-title">Ethereum: </h2>
                    <p class="card-text">Amount: ${user.ethereum}</p>
                    <p class="card-text">&#163;${user.ethereum/eth.getCurrentPrice()}</p>
                    <p class="card-text">${(user.ethereum/eth.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/cardano-ada-logo.svg?v=014" alt="Cardano">
                <div class="card-body">
                    <h2 class="card-title">Cardano: </h2>
                    <p class="card-text">Amount: ${user.cardano}</p>
                    <p class="card-text">&#163;${user.cardano/ada.getCurrentPrice()}</p>
                    <p class="card-text">${(user.cardano/ada.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>