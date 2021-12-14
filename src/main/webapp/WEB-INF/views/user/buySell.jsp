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
    <form>
        <form:form action="/buySell" modelAttribute="coin">
        <br class="row mb-4">
        <h1 class="mb-4">CURRENCY TO BE SOLD:</h1>

            <div style="width:1200px; margin:0 auto;">
            <div class="card px-5 py-3 h-100 zoom">
                <svg class="card-img-top w-25 mx-auto filter-primary" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M19 16.166c0-4.289-4.465-5.483-7.887-7.091-2.079-1.079-1.816-3.658 1.162-3.832 1.652-.1 3.351.39 4.886.929l.724-3.295c-1.814-.551-3.437-.803-4.885-.841v-2.036h-2v2.134c-3.89.535-5.968 2.975-5.968 5.7 0 4.876 5.693 5.62 7.556 6.487 2.54 1.136 2.07 3.5-.229 4.021-1.993.451-4.538-.337-6.45-1.079l-.909 3.288c1.787.923 3.931 1.417 6 1.453v1.996h2v-2.105c3.313-.464 6.005-2.293 6-5.729z"></path></svg>
                <div class="card-body">
                    <h4>

                    <label for="sell">Select Crypto/Fiat</label>
                    </h4>
                    <select name="sell" id="sell" class="btn btn-outline-primary me-2" onChange="update()">
                        <option value="BTC">BTC</option>
                        <option value="ETH">ETH</option>
                        <option value="ADA">ADA</option>
                        <option value="EUR">EUR</option>
                        <option value="USD">USD</option>
                        <option value="GBP">GBP</option>
                    </select>

                    <style>
                        h4 {text-align: center;}
                        select {text-align: center;}
                        div {text-align: center;}
                    </style>

                </div>
            </div>
        </div>
            <br></br>

            <div class="row mb-4">
            <h1 class="mb-4">CURRENCY TO BE BOUGHT:</h1>

            <form class="col-lg-4 mb-2">
                <div style="width:1220px; margin:0 auto;">
                <div class="card px-5 py-3 h-100 zoom">
                        <svg class="card-img-top w-25 mx-auto filter-primary" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M19 16.166c0-4.289-4.465-5.483-7.887-7.091-2.079-1.079-1.816-3.658 1.162-3.832 1.652-.1 3.351.39 4.886.929l.724-3.295c-1.814-.551-3.437-.803-4.885-.841v-2.036h-2v2.134c-3.89.535-5.968 2.975-5.968 5.7 0 4.876 5.693 5.62 7.556 6.487 2.54 1.136 2.07 3.5-.229 4.021-1.993.451-4.538-.337-6.45-1.079l-.909 3.288c1.787.923 3.931 1.417 6 1.453v1.996h2v-2.105c3.313-.464 6.005-2.293 6-5.729z"></path></svg>
                        <div class="card-body">
                            <h4>

                                <label for="sell">Select Crypto/Fiat</label>
                            </h4>
                            <select name="buy" id="buy" class="btn btn-outline-primary me-2" onChange="update()">
                                <option value="BTC">BTC</option>
                                <option value="ETH">ETH</option>
                                <option value="ADA">ADA</option>
                                <option value="EUR">EUR</option>
                                <option value="USD">USD</option>
                                <option value="GBP">GBP</option>
                            </select>
                            <input id='userBTC' type='hidden' value="${user.bitcoin}" />
                            <input id='userADA' type='hidden' value="${user.cardano}" />
                            <input id='userEUR' type='hidden' value="${user.EUR}" />
                            <input id='userETH' type='hidden' value="${user.ethereum}" />
                            <input id='userGBP' type='hidden' value="${user.GBP}" />
                            <input id='userUSD' type='hidden' value="${user.USD}" />

                            <input id='BTCprice' type='hidden' value="${btc.getCurrentPrice()}" />
                            <input id='ADAprice' type='hidden' value="${ada.getCurrentPrice()}" />
                            <input id='EURprice' type='hidden' value="${eur.getCurrentPrice()}" />
                            <input id='ETHprice' type='hidden' value="${eth.getCurrentPrice()}" />
                            <input id='GBPprice' type='hidden' value="${gbp.getCurrentPrice()}" />
                            <input id='USDprice' type='hidden' value="${usd.getCurrentPrice()}" />



                            <style>
                                h4 {text-align: center;}
                                select {text-align: center;}
                                div {text-align: center;}
                            </style>

                        </div>
                    </div>
                </div>


            <script type="text/javascript">
                function update() {
                    var select = document.getElementById('sell');
                    var option = select.options[select.selectedIndex];
                    var BTCprice = document.getElementById("userBTC").value;
                    var ETHprice = document.getElementById("userETH").value;
                    var ADAprice = document.getElementById("userADA").value;
                    var USDprice = document.getElementById("userUSD").value;
                    var EURprice = document.getElementById("userEUR").value;
                    var GBPprice = document.getElementById("userGBP").value;


                    var finalBTCprice = document.getElementById("BTCprice").value;
                    var finalETHprice = document.getElementById("ETHprice").value;
                    var finalADAprice = document.getElementById("ADAprice").value;
                    var finalUSDprice = document.getElementById("USDprice").value;
                    var finalEURprice = document.getElementById("EURprice").value;
                    var finalGBPprice = document.getElementById("GBPprice").value;

                    if(option.value == "BTC") {
                        var sellprice = finalBTCprice;
                        var portfoliocurr = BTCprice;
                    }
                    if(option.value == "ADA") {
                        var sellprice = finalADAprice;
                        var portfoliocurr = ADAprice;
                    }
                    if(option.value == "ETH") {
                        var sellprice = finalETHprice;
                        var portfoliocurr = ETHprice;
                    }
                    if(option.value == "EUR") {
                        var sellprice = finalEURprice;
                        var portfoliocurr = EURprice;
                    }
                    if(option.value == "GBP") {
                        var sellprice = finalGBPprice;
                        var portfoliocurr = GBPprice;
                    }
                    if(option.value == "USD") {
                        var sellprice = finalUSDprice;
                        var portfoliocurr = USDprice;
                    }

                    document.getElementById('finalsell').value = option.value;
                    document.getElementById('finalsellprice').value = sellprice;
                    document.getElementById('portfoliocurrency').value = portfoliocurr;



                    var select = document.getElementById('buy');
                    var option = select.options[select.selectedIndex];

                    if(option.value == "BTC") {
                        var buyprice = "bitcoin price";
                        var portfoliomon = BTCprice;

                    }
                    if(option.value == "ADA") {
                        var buyprice = "cardano price";
                        var portfoliomon = ADAprice;

                    }
                    if(option.value == "ETH") {
                        var buyprice = "ethereum price";
                        var portfoliomon = ETHprice;

                    }
                    if(option.value == "EUR") {
                        var buyprice = "euro price";
                        var portfoliomon = EURprice;

                    }
                    if(option.value == "GBP") {
                        var buyprice = "pounds price";
                        var portfoliomon = GBPprice;

                    }
                    if(option.value == "USD") {
                        var buyprice = "dollar price";
                        var portfoliomon = USDprice;

                    }

                    document.getElementById('finalbuy').value = option.value;
                    document.getElementById('finalbuyprice').value = buyprice;
                    document.getElementById('portfoliocurrencymon').value = portfoliomon;


                }

                update();
            </script>
            </form:form>






                

            </form>

            </div>

        </form>


    <div class="row mb-2">
        <h1 class="mb-4">Transaction Overview:</h1>
        <div class="row mb-2">
            <div class="col-lg-4 mb-2">
                <div class="card px-5 py-3 h-100 zoom">
                    <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/bitcoin-btc-logo.svg?v=014" alt="Bitcoin">
                    <div class="card-body">
                        <h2 class="card-title">You have: </h2>

                        <input type="text" id="portfoliocurrency" disabled class="btn btn-outline-primary me-2">
                        <input type="text" id="finalsell" disabled class="btn btn-outline-primary me-2">
                        <br></br>
                        <p class="card-text">At Current Value compared to 1 GBP:</p>
                        <input type="text" id="finalsellprice" disabled class="btn btn-outline-primary me-2">

                        <p class="card-text">&#163;${user.bitcoin/btc.getCurrentPrice()}</p>
                        <p class="card-text">${(user.bitcoin/btc.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
                    </div>
                </div>
            </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/ethereum-eth-logo.svg?v=014" alt="Ethereum">
                <div class="card-body">
                    <h2 class="card-title">You want: </h2>
                    <input type="text" id="portfoliocurrencymon" disabled class="btn btn-outline-primary me-2">
                    <input type="text" id="finalbuy" disabled class="btn btn-outline-primary me-2">
                    <br></br>
                    <p class="card-text">At Current Value:</p>
                    <input type="text" id="finalbuyprice" disabled class="btn btn-outline-primary me-2">
                    <p class="card-text">&#163;${user.ethereum/eth.getCurrentPrice()}</p>
                    <p class="card-text">${(user.ethereum/eth.getCurrentPrice())/(user.bitcoin/btc.getCurrentPrice()+user.ethereum/eth.getCurrentPrice()+user.cardano/ada.getCurrentPrice())*100}%</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-2">
            <div class="card px-5 py-3 h-100 zoom">
                <img class="card-img-top w-25 mx-auto" src="https://cryptologos.cc/logos/cardano-ada-logo.svg?v=014" alt="Cardano">
                <div class="card-body">
                    <h2 class="card-title">Final Quote: </h2>
                    <p class="card-title">Select Amount to sell: </p>

                    <input type="number" id="quantity" class="btn btn-outline-primary me-2">
                    <br></br>
                    <p class="card-text">You receive: </p>
                    <input type="text" id="quote" disabled class="btn btn-outline-primary me-2">


                </div>
                <button type="button" class="btn btn-outline-primary me-2" onclick="function myFunction() {
                let r = document.getElementById('quantity');
                let j = document.getElementById('quote').value = r.value/100;


                }
                myFunction()">CONVERT</button>

            </div>

        </div>

    </div>

</div>
    <button type="button" class="btn btn-primary" onclick="function finalizeFunction(){

    }
       finalizeFunction()"> FINALIZE TRANSACTION </button>
    <br></br>
    <br></br>
</body>
</html>