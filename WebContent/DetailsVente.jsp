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
		<h6 style="color: red">${messageErreur2}</h6>	
		<h6 style="color: red">${creditPasSuffisantMessage}</h6>
	</div>
	
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<h3>Détails Vente</h3>
		</div>
		<div class="col"></div>
	</div>
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<p>${nomArticle}</p>
			<br>
			<p>
				<font style="color: grey; font-weight: bold; font-size: 20px ">Description</font> : ${description}
			</p>
			<p>
				<font style="color: grey; font-weight: bold; font-size: 20px ">Catégorie</font> : ${no_categorie}
			</p>
			<br>
			<p><font style="color: grey; font-weight: bold; font-size: 20px ">Meilleure offre</font> : ${meilleurOffre}</p>
			<br>
			<p><font style="color: grey; font-weight: bold; font-size: 20px ">Mise à prix</font> : ${PrixInitial}</p>
			<br>
			<p><font style="color: grey; font-weight: bold; font-size: 20px ">Fin enchère</font> : ${dateFinEnchere}</p>
			<br>
			<div class="row">
				<div class="col">
					<p><font style="color: grey; font-weight: bold; font-size: 20px ">Retrait</font></p>
				</div>
				<div class="col">${rueRetrait}<br>${codePostalRetrait } ${villeDeRetrait }</div>
			</div>
			<br>
			<p><font style="color: grey; font-weight: bold; font-size: 20px ">Vendeur</font> : ${pseudoVendeur}</p>
			<br>
			
			<p>Mon enchère :</p>
			
			
		<form  style="margin-bottom: -15px" action="Encherir" method="post">					
			<div>
				<div class="row">
					<div class="col">
						<input type="number" class="form-control" min="${PrixInitial}"   id="montantEnchere"  name="montantEnchere">
						<input type="hidden" name="noArticle" value="${numeroArticle}">
						<font color="red">${enchereInferieureMessage}</font>
					</div>
					<div class="col"  style="margin-bottom: 100px">
						<button type="submit" style="" class="btn btn-secondary">Enchérir</button>
					</div>
				</div>
			</div>
		</form>
			
		</div>
		<div class="col"></div>
	</div>

</body>
</html>