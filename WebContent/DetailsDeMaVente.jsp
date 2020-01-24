<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>Détails de ma Vente</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark ">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Enchéres</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/AjoutArticle">Vendre un
					article</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MonProfil">Mon Profil</a>
			</li>
			<li class="nav-item"><a class="nav-link"href="${pageContext.request.contextPath}/Deconnexion">Déconnexion</a>
			</li>
		</ul>
	</nav>
	<div class="row">
		<br>
	</div>
	
	<div align="center">
		<h6 style="color: red">${messageErreur}</h6>
	</div>
	
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<h3>Détail Vente</h3>
		</div>
		<div class="col"></div>
	</div>
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<p>${nom_article}</p>
			
			<p>	Description:</p>
				<p> ${description}
			</p>
			
			<p>Meilleur offre:</p><p> ${prix_vente}</p>
			
			<p>Mise a prix:</p><p> ${prix_initial}</p>
			
			<p>Fin de l'enchère:</p><p> ${date_fin_encheres}</p>
			
			
			 <div class="card">
    <div class="card-body">
    <div class="row">
				<div class="col">
					<p>Retrait</p>
				</div>
				<div class="col">${rue }<br>${code_postal }<br>${ville }</div>
				</div>
  </div>
			</div>
			<br>
			<p>Vendeur: ${pseudo}</p>
			
			
			<div>
				
				<a href="${pageContext.request.contextPath}/DeleteArticle"><button style="width: 200px" type="submit" class="btn btn-outline-danger">Supprimer ma vente</button></a>
			</div>
		</div>
		<div class="col"></div>
	</div>

</body>
</html>