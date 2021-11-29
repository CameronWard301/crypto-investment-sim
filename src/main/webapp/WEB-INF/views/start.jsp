<%--@elvariable id="user" type="com.crypto.investment.sim.model.User"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>This is the home page</title>
    <jsp:include page="common/head.jsp" />
</head>
<body>
<p><a class="text-centre" href="/portfolio?user=${user}">portfolio</a></p>
</body>
</html>