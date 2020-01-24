<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
}
th, td {
	padding: 5px;
}

th {
	text-align: left;
}
</style>
<title>profil utilisateur</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> 
			 <img src="${pageContext.request.contextPath}/logo.jpg" class="rounded" style="width:300px;height:60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomePageConnecte">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/AjoutArticle">Vendre un
					article</a></li>
			
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Deconnexion">Déconnexion</a>
			</li>
		</ul>
	</nav>
	<div class="row">
		<br>
		<br>
	</div>
	
	<div align="center">
		<h6 style="color: red">${messageErreur}</h6>
	</div>
	
	<div class="row">
		<div class="col"></div>
		<div>
			<h2>Profil</h2>
		</div>
		<div class="col"></div>
	</div>
	<div class="row"></div>
	<div class="row">
		<div class="col"></div>
		<table style="width: 33%">

			<tr>
				<th>Pseudo</th>
				<td>${pseudo}</td>
			</tr>
			<tr>
				<th>Nom</th>
				<td>${nom}</td>
			</tr>
			<tr>
				<th>Prénom</th>
				<td>${prenom}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${email}</td>
			</tr>
			<tr>
				<th>Télephone</th>
				<td>${telephone}</td>
			</tr>
			
			<tr>
				<th>Ville</th>
				<td>${ville}</td>
			</tr>
		</table>
		<div class="col"></div>
	</div>
	<div class="row">
		<br>
		<br>
	</div>
	<div class="row">
		<div class="col"></div>
	
		<div class="col"></div>
	</div>
</body>
</html>