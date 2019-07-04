<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Hello HOME!</h2>

<%!
    public int getValue() {
        return 78;
    }
%>

<%
    String name = request.getParameter("name");
    out.println("name: " + name);
%>
<br/>
The method we can use in Expression also.
<%=getValue()%>
</body>
</html>
