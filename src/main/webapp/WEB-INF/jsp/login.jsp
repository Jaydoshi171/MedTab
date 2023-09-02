<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	<style>
        .login-form {
            margin: auto;
            width: 50%;
            padding: 20px;
            border: 1px solid #ccc;
        }

        .form-group {
            margin-bottom: 10px;
        }

        .form-control {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .errorMsg{
				color: red;
			}
    </style>
</head>
<body>
	<div class="login-form">
		<h2>Login</h2>
		<form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/login">
			<div class="form-group">
				<form:label path="email">Email:</form:label>
				<form:input path="email" type="email" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<form:label path="password">Password:</form:label>
				<form:input path="password" type="password" class="form-control" required="true"/>
			</div>
            <div class="errorMsg">${err_msg}</div>
			<button type="submit" class="btn">Login</button>
		</form:form>
	</div>
</body>
</html>