<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Registration Status</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
       
    </head>
    <body>
    
    <c:if test="${error}">
            <div>Unable to register Users. Please try later</div>
        </c:if>
        
        <c:if test="${success}">
            <div>User Successfully Registered</div>
        </c:if>
         <p><a href="<c:url value="/register/getUsers"/>"> View Users </a>
        <p><a href="<c:url value="/register/registerUser"/>"> Register Users </a>
    </body>
</html>