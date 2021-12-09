<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registered Users</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <c:if test="${error}">
            <div>Unable to Fetch Users. Please try later</div>
        </c:if>
        
        <c:if test="${nousers}">
            <div>No users Registered. Please register 
             <a href="<c:url value="/register/registerUser"/>"> here </a></div>
        </c:if>
    
    <c:if test="${success}">
        <table>
            <thead>
                <tr>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userDetails}" var="userDetail">
                    <tr>
                        <td>${userDetail.firstName}</td>
                        <td>${userDetail.lastName}</td>
                        <td>${userDetail.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
    </body>
</html>