<%--suppress JspAbsolutePathInspection --%>
<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<%--@elvariable id="fiat" type="java.lang.Integer"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Markets</title>
    <jsp:include page="common/head.jsp"/>
<%--suppress HtmlUnknownTarget --%>
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
<%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
            <a href="/markets?fiat=GBP" id="pound" class="btn btn-outline-primary">&#163;</a> <%--£--%>
<%--suppress HtmlUnknownTarget --%>
            <a href="/markets?fiat=USD" id="dollar" class="btn btn-outline-primary">$</a> <%--$--%>
            <%--suppress HtmlUnknownTarget --%>
            <a href="/markets?fiat=EUR" id="euro" class="btn btn-outline-primary">&#8364;</a> <%--€--%>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Bitcoin</h2>
            <!-- TradingView Widget BEGIN -->
            <div class="tradingview-widget-container">
                <div id="tradingview_401ca" style="height: 500px"></div>
                <div class="tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/BTCGBP/?exchange=COINBASE" rel="noopener" target="_blank"><span class="blue-text">BTCGBP Chart</span></a> by TradingView</div>
                <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
                <script type="text/javascript">
                    new TradingView.widget(
                        {
                            "autosize": true,
                            "symbol": "COINBASE:BTC${fiat}",
                            "interval": "D",
                            "timezone": "Europe/London",
                            "theme": "light",
                            "style": "1",
                            "locale": "en",
                            "toolbar_bg": "#f1f3f6",
                            "enable_publishing": false,
                            "container_id": "tradingview_401ca"
                        }
                    );
                </script>
            </div>
            <!-- TradingView Widget END -->
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Ethereum</h2>
            <!-- TradingView Widget BEGIN -->
            <div class="tradingview-widget-container">
                <div id="tradingview_825e3" style="height: 500px"></div>
                <div class="tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/ETHGBP/?exchange=COINBASE" rel="noopener" target="_blank"><span class="blue-text">ETHGBP Chart</span></a> by TradingView</div>
                <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
                <script type="text/javascript">
                    new TradingView.widget(
                        {
                            "autosize": true,
                            "symbol": "COINBASE:ETH${fiat}",
                            "interval": "D",
                            "timezone": "Europe/London",
                            "theme": "light",
                            "style": "1",
                            "locale": "en",
                            "toolbar_bg": "#f1f3f6",
                            "enable_publishing": false,
                            "container_id": "tradingview_825e3"
                        }
                    );
                </script>
            </div>
            <!-- TradingView Widget END -->
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-lg-12">
            <h2>Cardano</h2>
            <!-- TradingView Widget BEGIN -->
            <div class="tradingview-widget-container">
                <div id="tradingview_ead17" style="height: 500px"></div>
                <div class="tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/ADAGBP/?exchange=COINBASE" rel="noopener" target="_blank"><span class="blue-text">ADAGBP Chart</span></a> by TradingView</div>
                <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
                <script type="text/javascript">
                    new TradingView.widget(
                        {
                            "autosize": true,
                            "symbol": "COINBASE:ADA${fiat}",
                            "interval": "D",
                            "timezone": "Europe/London",
                            "theme": "light",
                            "style": "1",
                            "locale": "en",
                            "toolbar_bg": "#f1f3f6",
                            "enable_publishing": false,
                            "container_id": "tradingview_ead17"
                        }
                    );
                </script>
            </div>
            <!-- TradingView Widget END -->
        </div>
    </div>

</div>
</body>
</html>