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
<title>Fin De Vente</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
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
		<div class="col">
			<!-- vu de l'Acheteur -->
			<h4>Vous aves remporté la vente</h4>
			<!--vue pour le vendeur -->
			<h4>a remporté la vente</h4>
		</div>
		<div class="col"></div>
	</div>
	<div class="row">
		<br>
		<br>
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<p>${nom_article}</p>
			<br>
			<p>
				Description: <br> ${description}
			</p>
			<br>
			<p>Meilleur offre: ${prix_vente}
			<!-- partie vendeur -->
			par ${pseudo}
			</p>
			<br>
			<p>Mise a prix: ${prix_initial}</p>
			<br>
			<div class="row">
				<div class="col">
					<p>Retrait</p>
				</div>
				<div class="col">${rue }<br>${code_postal }<br>${ville }</div>
			</div>
			<br>
			<!-- pour acheteur -->
			<p>Vendeur: ${pseudo}</p>
			<br>
			<!-- pour acheteur -->
			<p>${telephone }</p>
			<!-- pour vendeur -->
			<button type="submit" class="btn btn-secondary">Retrait éffectué</button>
		</div>
		<div class="col"></div>
	</div>
</body>
</html>