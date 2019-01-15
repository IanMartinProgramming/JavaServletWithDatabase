<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	text-align: center;
}

th {
	font-family: "helvetica", sans-serif;
	font-size: 20px;
	color: white;
	background-color: black;
}

td {
	font-family: "helvetica", sans-serif;
	background-color: darkgrey;
}
h1{
	font-family: "helvetica", sans-serif;
}
</style>
<meta charset="ISO-8859-1">
<title>Rockstar Lookup</title>
</head>
<body>
	<h1>Rockstar Lookup Repository</h1>
	<form method="post" action="Controller">
	<table>
		<tr><th colspan="2">Select search parameters, then input your query</th></tr>
		<tr><td><input type="radio" name="query" value="idName"/>Find Name by ID</td>
		<td><input type="radio" name="query" value="idSalary"/>Find Salary by ID</td></tr>
		<tr><td><input type="radio" name="query" value="nameSalary"/>Find Salary by Name</td>
		<td><input type="radio" name="query" value="nameId"/>Find ID by Name</td></tr>
	</table>
	<input type="text" name="userInput">
	<input type="submit" value="Search">
	</form>
</body>
</html>