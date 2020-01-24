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
<title>Créer Profil</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/SeConnecter">Déja un compte? Se
					connecter</a></li>
		</ul>
	</nav>
	<div class="row">
		<br>
	</div>
	
	<div align="center">
		<h6 style="color: red">${messageConflitMail}</h6>
		<h6 style="color: red">${messageConflitPhone}</h6>
		<h6 style="color: red">${messageConflitPseudo}</h6>
		<h6 style="color: red">${notMatchingPassword}</h6>
		<h6 style="color: red">${allFieldsRequiredMessage}</h6>	
		<h6 style="color: red">${nonRespectAlphNumMessage}</h6>	
		<h6 style="color: red">${messageErreur}</h6>	
		<h6 style="color: red">${messageErreur2}</h6>
	</div>
	
	<form  method="post" action="AjoutUtilisateur">
		
		<div class="row">
			<div class="col"></div>
			<div class="col">
				<div>
			
					<label for="pseudo">Pseudo:</label>
					<input type="text" class="form-control" id="pseudo" placeholder="Entrer pseudo" name="pseudo"  value="${pseudo}" equired="required"> 
					<label for="prenom">Prénom:</label> 
					<input type="text" class="form-control" id="prenom" placeholder="Entrer prenom" name="prenom" value="${prenom}" required="required"> 
					<label for="telephone">Telephone:</label> 
					<input type="text" class="form-control" id="telephone" placeholder="Enter n°tel" name="telephone" value="${telephone}" required="required"> 
					<label for="code_postal">Code postal:</label> 
					<input type="text" class="form-control" id="code_postal" placeholder="Enter code poste" name="code_postal" value="${code_postal}" required="required"> 
					<label for="mot_de_passe">Mot de passe:</label> 
					<input type="password" class="form-control" id="mot_de_passe" placeholder="mot de passe" name="mot_de_passe" required="required">
				</div>
			</div>
			<div class="col">
				<div>
					<label for="nom">Nom:</label> 
					<input type="text" class="form-control" id="nom" placeholder="Entrer nom" name="nom" value="${nom}" required="required">
					<label for="email">Email:</label> 
					<input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${email}" required="required"> 
					<label for="rue">Rue:</label> 
					<input type="text" class="form-control" id="rue" placeholder="Enter Rue" name="rue" value="${rue}" required="required"> 
					<label for="ville">Ville:</label> 
					<input type="text" class="form-control" id="ville" placeholder="Enter Ville" name="ville" value="${ville}" required="required"> 
					<label for="mot_de_passe">Confirmation du mot de passe:</label> 
					<input type="password" class="form-control" id="mot_de_passe" placeholder="mot de passe" name="conf_mot_de_passe" required="required">
				</div>
			</div>
			<div class="col"></div>
		</div>
		<div class="row">
			<br> <br>
		</div>
		<div class="row">
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">
				<div class="row">
					<div class="col">
						<button type="submit" class="btn btn-outline-secondary">Valider</button>
						<a href="${pageContext.request.contextPath}/HomePageConnecte" class="btn btn-outline-secondary" role="button">Abandonner</a>
					</div>
				</div>
			</div>
			<div class="col"></div>
		</div>
		<div class="form-check"></div>
	</form>

</body>
</html>
