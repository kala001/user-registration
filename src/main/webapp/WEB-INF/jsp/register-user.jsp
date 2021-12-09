<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<script type = "text/JavaScript">

	</script>
	
	
        <title>Register User</title>
    </head>
    <body>
        <c:if test="${validationError}">
            <div style="color:red">Unable to RegisterUser. All fields are mandatory and first name and last name must be of more than 3 characters long. email should be in pattern username@domain.com</div>
        </c:if>
        
         <c:if test="${emailValidationError}">
            <div style="color:red">Unable to RegisterUser. Email should be in pattern username@domain.com</div>
        </c:if>
    
        <c:url var="registerUserUrl" value="/register/registerUser"/>
        <form:form action="${registerUserUrl}" method="post" modelAttribute="userDetails">
            <form:label path="firstName">First Name: </form:label> <form:input type="text" path="firstName" id="fname" required="required" minlength="3"/>
            <form:label path="lastName">Last Name: </form:label> <form:input type="text" path="lastName" id ="lname" required="required" minlength="3"/>
            <form:label path="email">Email: </form:label> <form:input path="email" id="email" required="required" minlength="6"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>