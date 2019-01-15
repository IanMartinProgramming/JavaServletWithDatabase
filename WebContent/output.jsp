<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1, h3{
font-family: "helvetica", sans-serif;
}
</style>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
<h1>Search Results</h1>
<%String queryResult = (String)request.getAttribute("queryResult");  %>
<h3>${queryResult}</h3>
<a href="input.jsp">Back</a>
</body>
</html>