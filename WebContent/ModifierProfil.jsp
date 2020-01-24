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
<title>Modifier Profil</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomePageConnecte">Accueil</a></li>
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
	</div>
	
	<div align="center">
		<h6 style="color: red">${allFieldsRequiredMessage}</h6>
		<h6 style="color: red">${notMatchingPassword}</h6>
		<h6 style="color: red">${incorrectPassword}</h6>
		<h6 style="color: red">${messageErreur}</h6>	
		<h6 style="color: red">${nonRespectAlphNumMessage}</h6>	
	</div>
	
	<form method="post"  action="ModifierProfil">
		
		<div class="row">
			<div class="col"></div>
			<div class="col">

				<div>
					<label for="pseudo">Pseudo :</label> 
					<input type="text" class="form-control" id="pseudo" placeholder="Entrer pseudo" name="pseudo" value="${pseudo}">
					 <label for="prenom">Prénom :</label> 
					 <input type="text" class="form-control" id="prenom" placeholder="Entrer prenom" name="prenom" value="${prenom}"> 
					 <label for="telephone">Téléphone :</label> 
					 <input type="text" class="form-control" id="telephone" placeholder="Enter n°tel" name="telephone" value="${telephone}"> 
					 <label for="code_postal">Code postal :</label> 
					 <input type="text" class="form-control" id="code_postal" placeholder="Enter code poste" name="code_postal" value="${codePostal}"> 
					 <label for="mot_de_passe">Mot de passe actuel :</label> 
					 <input type="password" class="form-control" id="mot_de_passe" placeholder="mot de passe" name="mot_de_passe">
				</div>
			</div>
			<div class="col">
				<div>
					<label for="nom">Nom :</label> 
					<input type="text" class="form-control" id="nom" placeholder="Entrer nom" name="nom"  value="${nom}">
					<label for="email">Email :</label> 
					<input type="email" class="form-control" id="email" placeholder="Enter email" name="email"  value="${email}"> 
					<label for="rue">Rue :</label> 
					<input type="text" class="form-control" id="rue" placeholder="Enter Rue" name="rue"  value="${rue}"> 
					<label for="ville">Ville :</label> <input type="text" class="form-control" id="ville" placeholder="Enter Ville" name="ville"  value="${ville}"> 
					<label for="mot_de_passe">Nouveau mot de passe :</label> 
					<input type="password" class="form-control" id="mot_de_passe" placeholder="mot de passe" name="new_mot_de_passe"> 
					<label for="mot_de_passe">Confirmer le mot de passe :</label> 
					<input type="password" class="form-control" id="mot_de_passe" placeholder="mot de passe" name="conf_mot_de_passe">
				</div>
			</div>
			<div class="col"></div>

		</div>


		<div class="row">
			<br> <br>
		</div>
		<div align="center" class="row">
			<div class="col"></div>
			<div class="col">
				<button type="submit" class="btn btn-secondary">Enregistrer</button>
				<a href="${pageContext.request.contextPath}/MonProfil" class="btn btn-secondary" role="button">Annuler</a>
			</div>

			<div class="col"></div>
		</div>
		<div class="form-check"></div>
	</form>
	<br>
	
	<div align="center">
	<a href="${pageContext.request.contextPath}/SupprimerMonCompte"><button class="btn btn-outline-danger">Supprimer mon compte</button></a>
	</div>
</body>
</html>