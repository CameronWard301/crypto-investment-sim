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
    <div class="row my-3">
        <div class="p-3 w-100 col-lg-12 ${bannerColor} alert alert-dismissible fade ${hidden}" role="alert">
            <span><i>${message}</i></span>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-8 col-12 mx-auto">
            <h1 class="mb-3">Welcome Existing Member</h1>
            <h3>Please login to continue</h3>
            <div class="card mt-3">
                <div class="card-body">
<%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
                    <%--@elvariable id="user" type="UserLogin"--%>
                    <form:form id="userForm" action="/addLogin" modelAttribute="user">
                        <div class="form-group mb-3">
                            <form:label path="username" for="username">Enter Username:</form:label>
                            <form:input path="username" id="username" name="username" class="form-control"/>
                            <form:errors path="username" class="text-error"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:label path="password" for="password">Enter Password</form:label>
                            <form:input path="password" type="password" id="password" class="form-control" name="password"/>
                            <form:errors path="password" class="text-error"/>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-5">Login</button>
                    </form:form>
                    <p>Don't have an account?
                        <%--suppress HtmlUnknownTarget --%>
                        <a href="sign-up">Create one here</a>
                    </p>
                </div>
            </div>

        </div>
    </div>

</div>
</body>
</html>
