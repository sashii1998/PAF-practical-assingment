<%@ page import="model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/doctors.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>DOCTOR SERVICE</h1>
				<form id="formItem" name="formItem" method="post" action="Doctors.jsp">  
					Doctor_Code: 
					<input id="docCode" name="docCode" type="text" class="form-control form-control-sm">  
					<br> Doctor_Name:  
					<input id="docname" name="docname" type="text" class="form-control form-control-sm">  
					<br> Doctor_Specilisation:  
					<input id="specilisation" name="specilisation" type="text" class="form-control form-control-sm">  
					<br> Phone_Number:  
					<input id="doctell" name="doctell" type="text" class="form-control form-control-sm">
					<br> Doctor_Emaill:  
					<input id="docmaill" name="docmaill" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divItemsGrid">
					<%
						Doctor docObj = new Doctor();
						out.print(docObj.readDoctor());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
</body>
</html>