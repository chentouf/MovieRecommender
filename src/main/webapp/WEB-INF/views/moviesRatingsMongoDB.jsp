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
                $('#RatedMoviesMongoDB').DataTable();
                $('#AllMoviesMongoDB').DataTable();
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
            <table id="RatedMoviesMongoDB" class="table table-striped table-bordered " cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>${moviesRatingsMongoDBJSize}</th>
                    <th>Titre de filme</th>
                    <th>Note</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${moviesRatingsMongoDB}" var="rating">
                    <tr>
                        <td>${rating.getMovie().id}</td>
                        <td>${rating.getMovie().title}</td>
                        <td><span class="badge">${rating.score}</span><br/>
                            <c:forEach var="i" begin="1" end="5">
                                <c:if test="${i==rating.score}">
                                    <a href="/saveOrUpdateRatingMongoDB/${userId}/${rating.getMovie().id}/${i}"><button class="btn btn-success disabled">${i}</button></a>
                                </c:if>
                                <c:if test="${i != rating.score}">
                                    <a href="/saveOrUpdateRatingMongoDB/${userId}/${rating.getMovie().id}/${i}"><button class="btn btn-default">${i}</button></a>
                                </c:if>
                            </c:forEach></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 scrollit">
            <centre><h1>
                Des films non notés
            </h1></centre>
            <table id="AlleMoviesMongoDB" class="table table-striped table-bordered " cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>${otherMoviesSize}</th>
                    <th>Titre de filme</th>
                    <th>Ajouter une note</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${otherMovies}" var="movie">
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.title}</td>
                        <td>
                            <c:forEach var="i" begin="1" end="5">
                                <a href="/createRatingMongoDB/${userId}/${movie.id}/${i}"><button class="btn btn-default">${i}</button></a>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
</div>
</body>
</html>
