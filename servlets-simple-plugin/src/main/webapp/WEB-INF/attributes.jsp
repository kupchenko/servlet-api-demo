<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<h2>Hello, ${username}!</h2>
<c:forEach items="${parameters}" var="parameter">
    ${parameter}<br/>
</c:forEach>
</body>
</html>
