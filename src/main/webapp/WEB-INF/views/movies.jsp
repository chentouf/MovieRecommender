<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			$('#neo4j').DataTable();
			$('#mongodb').DataTable();
		} );
	</script>
	<style>
		.scrollit {
			overflow:scroll;
			height:90%;
		}
	</style>
<title>Projet NoSQL - CHENTOUF - TORNOD</title>

</head>
<body>
<center><h1>
	<c:choose>
	    <c:when test="${userId==null}">
	        Tous les films
	    </c:when>    
	    <c:otherwise>
	        Films de l'utilisateur ${userId}
	    </c:otherwise>
	</c:choose>
</h1></center>
<div class="col-md-12 container">
	<div class="col-md-6 scrollit">
		<table id="neo4j" class="table table-striped table-bordered " cellspacing="0" width="100%">
			<thead>
			<tr>
				<th>${moviesNeo4JSize}</th>
				<th>Noe4j</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${moviesNeo4j}" var="movie">
				<tr>
					<td>${movie.id}</td>
					<td>${movie.title}</td>
				</tr>
					<%--<ul>
                        <c:forEach items="${movie.genres}" var="genre">
                            <li>
                                ${genre.name}
                            </li>
                        </c:forEach>
                    </ul>--%>
			</c:forEach>

				<%--<ul>
                    <c:forEach items="${movie.genres}" var="genre">
                        <li>
                                ${genre.name}
                        </li>
                    </c:forEach>
                </ul>--%>
			</tbody>
		</table>
	</div>
	<div class="col-md-6 scrollit">
		<table id="mongodb" class="table table-striped table-bordered " cellspacing="0" width="100%">
			<thead>
			<tr>
				<th>${moviesMongoDBSize}</th>
				<th>MongoDB</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${moviesMongoDB}" var="movie">
				<tr>
					<td>${movie.id}</td>
					<td>${movie.title}</td>
				</tr>
			</c:forEach>
				<%--<ul>
                    <c:forEach items="${movie.genres}" var="genre">
                        <li>
                                ${genre.name}
                        </li>
                    </c:forEach>
                </ul>--%>
			</tbody>
		</table>
	</div>
</div>


</div>
</body>
</html>