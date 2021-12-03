<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<%--@elvariable id="fiat" type="java.lang.Integer"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Markets</title>
    <jsp:include page="common/head.jsp"/>
    <script src="js/markets.js" type="text/javascript"></script>
</head>

    <jsp:include page="common/header.jsp"/>

<body>
<div class="container">
    <div class="row mb-3">
        <div class="col-lg-12">
            <h1>Current Markets</h1>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h3>Select Currency</h3>
            <a href="/markets?fiat=1508" id="pound" class="btn btn-outline-primary">&#163;</a> <%--£--%>
            <a href="/markets?fiat=1505" id="dollar" class="btn btn-outline-primary">$</a> <%--$--%>
            <a href="/markets?fiat=1506" id="euro" class="btn btn-outline-primary">&#8364;</a> <%--€--%>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Bitcoin</h2>
            <div style="height:482px; background-color: #FFFFFF; overflow:hidden; box-sizing: border-box; border: 1px solid #56667F; border-radius: 4px; text-align: right; line-height:14px; font-size: 12px; font-feature-settings: normal; text-size-adjust: 100%; box-shadow: inset 0 -20px 0 0 #56667F;padding:1px;margin: 0; width: 100%;"><div style="height:540px; padding:0; margin:0; width: 100%;"><iframe src="https://widget.coinlib.io/widget?type=chart&theme=light&coin_id=859&pref_coin_id=${fiat}" width="100%" height="536px" border="0" style="border:0;margin:0;padding:0;line-height:14px;"></iframe></div><div style="color: #FFFFFF; line-height: 14px; font-weight: 400; font-size: 11px; box-sizing: border-box; padding: 2px 6px; width: 100%; font-family: Verdana, Tahoma, Arial, sans-serif;"><a href="https://coinlib.io" target="_blank" style="font-weight: 500; color: #FFFFFF; text-decoration:none; font-size:11px">Cryptocurrency Prices</a>&nbsp;by Coinlib</div></div>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Ethereum</h2>
            <div style="height:482px; background-color: #FFFFFF; overflow:hidden; box-sizing: border-box; border: 1px solid #56667F; border-radius: 4px; text-align: right; line-height:14px; font-size: 12px; font-feature-settings: normal; text-size-adjust: 100%; box-shadow: inset 0 -20px 0 0 #56667F;padding:1px;margin: 0; width: 100%;"><div style="height:540px; padding:0; margin:0; width: 100%;"><iframe src="https://widget.coinlib.io/widget?type=chart&theme=light&coin_id=145&pref_coin_id=${fiat}" width="100%" height="536px" border="0" style="border:0;margin:0;padding:0;line-height:14px;"></iframe></div><div style="color: #FFFFFF; line-height: 14px; font-weight: 400; font-size: 11px; box-sizing: border-box; padding: 2px 6px; width: 100%; font-family: Verdana, Tahoma, Arial, sans-serif;"><a href="https://coinlib.io" target="_blank" style="font-weight: 500; color: #FFFFFF; text-decoration:none; font-size:11px">Cryptocurrency Prices</a>&nbsp;by Coinlib</div></div>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Cardano</h2>
            <div style="height:482px; background-color: #FFFFFF; overflow:hidden; box-sizing: border-box; border: 1px solid #56667F; border-radius: 4px; text-align: right; line-height:14px; font-size: 12px; font-feature-settings: normal; text-size-adjust: 100%; box-shadow: inset 0 -20px 0 0 #56667F;padding:1px;margin: 0; width: 100%;"><div style="height:540px; padding:0; margin:0; width: 100%;"><iframe src="https://widget.coinlib.io/widget?type=chart&theme=light&coin_id=122882&pref_coin_id=${fiat}" width="100%" height="536px" border="0" style="border:0;margin:0;padding:0;line-height:14px;"></iframe></div><div style="color: #FFFFFF; line-height: 14px; font-weight: 400; font-size: 11px; box-sizing: border-box; padding: 2px 6px; width: 100%; font-family: Verdana, Tahoma, Arial, sans-serif;"><a href="https://coinlib.io" target="_blank" style="font-weight: 500; color: #FFFFFF; text-decoration:none; font-size:11px">Cryptocurrency Prices</a>&nbsp;by Coinlib</div></div>
        </div>
    </div>




</div>
</body>
</html>