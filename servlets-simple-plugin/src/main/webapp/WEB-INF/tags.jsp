<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://kupchenko.me/tags/funcs" prefix="func" %>
<%@ taglib uri="http://kupchenko.me/tags/ctags" prefix="ct" %>
<html>
<body>
<h2>Hello ${func:sum(1,1)}!</h2>
<ct:testTag arg1="1" arg2="2"/>
</body>
</html>
