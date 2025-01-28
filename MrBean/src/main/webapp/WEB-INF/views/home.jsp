<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<h1>
	Hello world!
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>
