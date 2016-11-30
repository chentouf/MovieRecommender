<%--
  Created by IntelliJ IDEA.
  User: a.chentouf
  Date: 30/11/2016
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
</script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
</script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
    $(document).ready(function() {
        $('#RatedMoviesNeo4j').DataTable();
        $('#AllMoviesNeo4j').DataTable();
    } );
</script>
<style>
    .scrollit {
        overflow:scroll;
        height:90%;
    }
</style>
<title>Films</title>
</head>
<body>


    <br/>
    <div class="col-md-12 container">
        <div class="col-md-6 scrollit">
            <center><h1>
                Vos films notés : ID utilisateur ${userId}
            </h1></center>
            <table id="RatedMoviesNeo4j" class="table table-striped table-bordered " cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>${moviesRatingsNeo4JSize}</th>
                    <th>Titre de filme</th>
                    <th>Note</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${moviesRatingsNeo4j}" var="rating">
                    <tr>
                        <td>${rating.getMovie().id}</td>
                        <td>${rating.getMovie().title}</td>
                        <td>${rating.score}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 scrollit">
            <centre><h1>
                Ajouter une note
            </h1></centre>
            <table id="AlleMoviesNeo4j" class="table table-striped table-bordered " cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>${allMoviesSize}</th>
                    <th>Titre de filme</th>
                    <th>Add Note</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allMovies}" var="movie">
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.title}</td>
                        <td></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>



<%--<ul>
	<c:forEach items="${allMovies}" var="movie">
    		<!-- get movie rating if any -->
    		<c:set var="score" value="0" />
    		<c:forEach items="${ratings}" var="rating">
    			<c:if test="${rating.getMovie().id eq movie.id}">
    				<c:set var="score" value="${rating.score}" />
    			</c:if>
    		</c:forEach>

    		<li>
    			${movie.title} - ${score} (user id: ${userId}, movie id: ${movie.id})
    			<form method="post" action="/MovieRecommender/movieratings">
    				<input type="number" name="userId" value="${userId}" hidden="hidden">
    				<input type="number" name="movieId" value="${movie.id}" hidden="hidden">
    				<input type="radio" name="score" onclick="this.form.submit();" value="1" <c:if test="${score eq 1}">checked</c:if>>
    				<input type="radio" name="score" onclick="this.form.submit();" value="2" <c:if test="${score eq 2}">checked</c:if>>
    				<input type="radio" name="score" onclick="this.form.submit();" value="3" <c:if test="${score eq 3}">checked</c:if>>
    				<input type="radio" name="score" onclick="this.form.submit();" value="4" <c:if test="${score eq 4}">checked</c:if>>
    				<input type="radio" name="score" onclick="this.form.submit();" value="5" <c:if test="${score eq 5}">checked</c:if>>
    			</form>
    			<ul>
    				<c:forEach items="${movie.genres}" var="genre">
    					<li>
    						${genre.name}
    					</li>
    				</c:forEach>
    			</ul>
    		</li>
    	</c:forEach>
</ul>--%>
</body>
</html>
