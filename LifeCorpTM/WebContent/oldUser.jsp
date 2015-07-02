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
	    window.location.assign("http://localhost:8080/LifeCorpTM/Products")
	}
	
</script>
</head>
<body>
	 <div class="form-group">
		     <label for="exampleInputEmail">Email address</label>
    			<input type="email" class="form-control" id="email" placeholder="email">
		  </div>
		  <div class="form-group">
    		<label for="password">Password</label>
    			<input type="password" class="form-control" id="password" placeholder="password">
  		  </div>
  		  <div class="checkbox">
		    <label>
		      <input type="checkbox"> Sign In
		    </label>
		  </div>
		    </div>
		  <button type="submit" class="btn btn-default"  onclick="login()">Submit</button>
		</form>		 	
</body>
</html>