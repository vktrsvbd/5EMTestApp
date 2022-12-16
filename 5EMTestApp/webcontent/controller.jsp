<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="statementModule.DBInsertManualCommit" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main controller page</title>
</head>
<body>

 <% DBInsertManualCommit.insertDBRecords(); %> 
 <h1> The records were inserted in to the DB</h1>
 
</body>
</html>