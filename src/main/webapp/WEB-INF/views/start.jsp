<%--suppress JspAbsolutePathInspection --%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Crypto Sim - Home</title>
    <jsp:include page="common/head.jsp"/>
</head>

<jsp:include page="common/header.jsp"/>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12 mb-5">
            <h1 class="text-primary fw-bold text-center">Welcome to Crypto Currency Simulator</h1>
        </div>
        <div class="col-lg-12 mb-5">
            <div class="card">
                <div class="card-body">
                    <h2>Getting Started</h2>
                    <p>Sign up to create your account here: </p>
                    <%--suppress HtmlUnknownTarget --%>
                    <a class="btn btn-primary" href="/login">Sign Up</a>
                </div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-lg-4 mb-5">
            <div class="card h-100">
                <%--suppress HtmlUnknownTarget --%>
                <img class="card-img-top w-25 mx-auto mt-3" src="/images/portfolio.svg" alt="Portfolio">
                <div class="card-body">
                    <h2 class="big-number text-center fw-bold">1</h2>
                    <h2 class="text-center">Add Fiat</h2>
                    <p>The first step is to add "Fiat" (Regular currency e.g. pounds, dollars or Euros) to your
                        account.</p>
                    <%--            TODO:  Add link to add fiat page--%>
                    <p>This will enable you to then trade your fiat with Bitcoin Ethereum or Cardano</p>
                    <p>You can add as much fiat as you like, we recommend starting with a 1000 of your chosen
                        currency</p>
                    <%--suppress HtmlUnknownTarget --%>
                    <a class="btn btn-primary w-100" href="/addRemoveCurrency">Add Funds</a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 mb-5">
            <div class="card h-100">
                <%--suppress HtmlUnknownTarget --%>
                <img class="card-img-top w-25 mx-auto mt-3" src="/images/global-markets.svg" alt="Portfolio">
                <div class="card-body">
                    <h2 class="big-number text-center fw-bold">2</h2>
                    <h2 class="text-center">Monitor the markets!</h2>
                    <p>The best advice we can give: <i class="text-primary fw-bold">"Buy when others are fearful, sell
                        when
                        others are greedy."</i></p>
                    <p>This means that you should buy when the market dips and sell when the market is high.</p>
                    <p>There are two main strategies when trading in any market. You can either "time the market" where
                        you try
                        to estimate when it will dip and rise. Or the other (and more safer approach) is "time in the
                        market".</p>
                    <p>
                        This is often used with a technique called
                        <a href="https://www.investopedia.com/terms/d/dollarcostaveraging.asp" target="_blank"
                           rel="nofollow">
                            Dollar Cost Averaging</a>, you buy the market on a regular basis no matter the price. This
                        could mean you
                        buy every week, month or 3-months.
                        If you spread your investments out over a longer period of time you reduce the of price
                        fluctuations.<br>
                        With Dollar Cost Averaging you get a return (hopefully a profit) on your investment that is
                        equal to the average price of the market
                    </p>
                    <%--suppress HtmlUnknownTarget --%>
                    <a class="btn btn-primary w-100" href="/buySell">View the markets</a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 mb-5">
            <div class="card h-100">
                <%--suppress HtmlUnknownTarget --%>
                <img class="card-img-top w-25 mx-auto mt-3" src="/images/finances.svg" alt="Portfolio">
                <div class="card-body">
                    <h2 class="big-number text-center fw-bold">3</h2>
                    <h2 class="text-center">Trade Trade Trade</h2>
                    <p>Go to the Trading Page to exchange your fiat for crypto or exchange one crypto for another</p>
                    <p>Once you have made your trade you can track your progress on the portfolio page.</p>
                    <p>This will let you track your profit/loss and the value of each of your crypto currencies</p>
                    <%--suppress HtmlUnknownTarget --%>
                    <a class="btn btn-primary w-100" href="/buySell">Start Trading</a>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>