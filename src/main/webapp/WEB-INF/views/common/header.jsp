<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between p-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
<%--        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"></svg>--%>
        <img src="images/cryptocurrency-logo.svg" aria-label="Crypto Sim Logo" class="bi me-2" width="80" height="64" alt="Crypto Sim Logo"/>
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/" class="nav-link px-2 link-dark">Home</a></li>
        <li><a href="/markets" class="nav-link px-2 link-dark">Markets</a></li>
        <li><a href="/buySell?user=1" class="nav-link px-2 link-dark">Buy/Sell</a></li>
        <li><a href="/portfolio" class="nav-link px-2 link-dark">Your Portfolio</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-primary me-2">Login</button>
        <button type="button" class="btn btn-primary">Sign-up</button>
    </div>
</header>