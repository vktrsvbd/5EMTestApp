<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="statementModule.DBInsertTemp" %>
    <%@ page import="statementModule.DBInsertRecords" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main controller page</title>
</head>
<body>
 <% DBInsertTemp.insertTempRecords(); %>
 <h1>Records were inserted into the temporary table</h1>
<br />
 <% DBInsertRecords.recordsInsertion(); %> 
 <h1> The records were inserted in to the DB</h1>
</body>
</html>