<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld Index Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<body>
 
	<center>
		<h2>Projet NoSQL - CHENTOUF & TOROND</h2>
	</center>

	<div class="container">
		<h2>List of realised tasks</h2>
		<div class="list-group">
			<a href="hello?name=Eric" class="list-group-item">Say Hello</a>
			<a href="moviesNeo4j" class="list-group-item">Show Movies From Neo4j</a>
			<a href="moviesMongoDB" class="list-group-item">Show Movies From MongoDB</a>
			<a href="moviesNeo4j?user_id=4" class="list-group-item">Show Movies With user_id=4 From Neo4j</a>
			<a href="moviesMongoDB?user_id=6003" class="list-group-item">Show Movies With user_id=6003 From MongoDB</a>
			<a href="moviesRatingsNeo4j?user_id=6" class="list-group-item">Show Movies Reted by user_id=6 From Neo4j</a>
			<a href="moviesRatingsMongoDB?user_id=6012" class="list-group-item">Show Movies Reted by user_id=6012 From MongoDB</a>
		</div>
	</div>

</body>
</html>