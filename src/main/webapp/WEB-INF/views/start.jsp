<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crypto Sim - Home</title>
    <jsp:include page="common/head.jsp"/>
</head>

    <jsp:include page="common/header.jsp"/>

<body>
<div class="container">
    <div class="col-lg-12">
        <p class="text-center">
            <a href="/portfolio?user=${user}">Your Portfolio</a>


        </p>
    </div>
</div>
</body>
</html>