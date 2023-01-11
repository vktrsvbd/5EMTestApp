<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>  
<head><title> DB server testing app 1.0 </title> 
<style>
body	{
	background:silver;
	}
#wrapper{
	margin: auto;
	margin-top:100px;
	width: 400px;
	height: 800px;
	border: 5px outset red;	
	}
#logger{
	margin-top:0px;
	margin-left:0px;
	}
#logs	{
	resize: none;
	margin-left: 2px;
	margin-top: 2px;	
	}
#form	{
	width: 400px;
	padding-top: 15px;
	background: yellow;
	}
</style>
</head>

<body>
<div id="wrapper">  

	<div id="form">		
 		<form action="" method="POST">	
			<input type="radio" name="db_type" value="MS">Microsoft
			<input type="radio" name="db_type" value="POSTGREE">Postgree
			<input type="radio" name="db_type" value="ORACLE">Oracle
			<input type="radio" name="db_type" value="ORACLE">MySQL
			<br/></br>
			<input type="button"  value="Run Test" 
 	        		onclick="this.disabled=true;window.location.href='controller.jsp'; return false;"  
		 			class="add-student-button" />
		</form>	  
	</div>

	<div id="logger">
		<textarea id="logs" name="logs" cols="51" rows="48"  >	
   * select type of  DB 
   * START button to proceed the tests *    
	
		</textarea>

	</div> 
</div>

</body>
</html>