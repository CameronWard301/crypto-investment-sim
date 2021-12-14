<%--suppress ELValidationInJSP --%>
<%--suppress HtmlUnknownTarget --%>
<%--suppress JspAbsolutePathInspection --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <title>Crypto Sim | Signup</title>
</head>

<jsp:include page="../common/header.jsp"/>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-8 col-12 mx-auto">
            <h1 class="mb-3">Become a crypto master</h1>
            <h3>Sign up below to continue</h3>
            <div class="card mt-3">
                <div class="card-body">
<%--suppress JspAbsolutePathInspection, HtmlUnknownTarget --%>
                    <form:form id="userForm" modelAttribute="userSignUp" action="/createAccount">

                        <div class="form-group mb-3">
                            <form:label path="username" for="username">Enter Username:</form:label>
                            <form:input path="username" id="username" class="form-control"/>
                            <form:errors path="username" class="text-error"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:label path="firstName" for="firstName">Enter First Name:</form:label>
                            <form:input path="firstName" id="firstName" class="form-control"/>
                            <form:errors path="firstName" class="text-error"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:label path="lastName" for="lastName">Enter Last Name:</form:label>
                            <form:input path="lastName" id="lastName" class="form-control"/>
                            <form:errors path="lastName" class="text-error"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:label path="password1" for="password1">Enter Password:</form:label>
                            <form:input path="password1" type="password" id="password1" class="form-control"/>
                            <form:errors path="password1" class="text-error"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:label path="password2" for="password2">Confirm Password:</form:label>
                            <form:input path="password2" type="password" id="password2" class="form-control"/>
                            <form:errors path="password2" class="text-error"/>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-5">Sign Up</button>

                    </form:form>
                    <p>Have an account?
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