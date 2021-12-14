<%--suppress ELValidationInJSP --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>Crypto Sim | Login</title>
</head>

<jsp:include page="../common/header.jsp"/>
<body>

<div class="container">

    <div class="row">
        <div class="col-lg-8 col-12 mx-auto">
            <h1 class="mb-3">Welcome Existing Member</h1>
            <h3>Please login to continue</h3>
            <div class="card mt-3">
                <div class="card-body">
<%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
                    <form id="userForm" action="/addLogin">
                        <div class="form-group mb-3">
                            <label for="username">Enter Username:</label>
                            <input id="username" name="username" class="form-control">
                            <span class="text-error">${Error}</span>
                        </div>

                        <div class="form-group mb-3">
                            <label for="password">Enter Password</label>
                            <input type="password" id="password" class="form-control" name="password">
                            <span class="text-error">${passwordError}</span>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-5">Login</button>
                    </form>
                    <p>Don't have an account?
                        <%--suppress HtmlUnknownTarget --%>
                        <a href="signup">Create one here</a>
                    </p>
                </div>
            </div>

        </div>
    </div>

</div>

</body>
</html>
