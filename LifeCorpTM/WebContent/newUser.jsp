<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Life Corporation TM - Online Market</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
	function login() 
	{
	    window.location.assign("http://localhost:8080/LifeCorpTM/UserInformation")
	}
	
</script>
	

</head>
<body>
	
		<form action="UserInformation" method="post">
		  <div class="form-group">
		    <label for="firstname">First Name</label>
		    <input type="text" class="form-control" id="firstname" placeholder="firstname" name="firstname" >
		  </div>
			
		   <div class="form-group">
		    <label for="lastname">Last Name</label>
		    <input type="text" class="form-control" id="lastname" placeholder="lastname" name="lastname">
		   </div>
		  	
		  	<div class="form-group">
		     <label for="Email">Email address</label>
    			<input type="email" class="form-control" id="email" placeholder="emailid" name="emailid">
		  	 </div>
		  
		   <div class="form-group">
    		<label for="password">Password</label>
    			<input type="password" class="form-control" id="password" placeholder="password" name="password" >
  		   </div>
  		  
    		<label for="retypepassword">ReType Password</label>
    			<input type="password" class="form-control" id="retypepassword" placeholder="retypepassword" name="retypepassword">
  		  </div>
		  <button type="submit" class="btn btn-default"  onclick="login()">Submit</button>
		</form>		 	
	</div>
</body>
</html>