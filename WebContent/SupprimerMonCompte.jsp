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
<title>suppression compte</title>
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
		<br>
	</div>
	
	<div align="center">
		<h6 style="color: red">${messageErreur}</h6>	
	</div>
	
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<h5 style="color: crimson; text-align:center; ">Entrer votre mot de passe et confirmer la suppression</h5>
		</div>
		<div class="col"></div>
	</div>
	<br>
	<div>
		<form action=SupprimerMonCompte method="post">
			<div class="row">
				<div class="col"></div>
				<div>
					<div class="form-group">
						<label for="email">email</label> 
						<input type="email" class="form-control" id="email" placeholder="exemple@domaine.fr" name="email"  value="${email}" required>
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Ce champs est obligatoire. </div>
					</div>
					<div class="form-group">
						<label for="mot_de_passe">Mot de passe</label> <input
							type="password" class="form-control" id="mot_de_passe"
							placeholder="Entrer mot de passe" name="mot_de_passe" required>
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Ce champs est obligatoire.</div>

					</div>
				</div>
				<div class="col"></div>

			</div>




			<div class="row">
				<div class="col"></div>
				<div>
					<button type="submit" style="" class="btn btn-danger">Confirmer Suppression</button>
				</div>
				<div class="col"></div>
			</div>
		</form>
	</div>
</body>
</html>