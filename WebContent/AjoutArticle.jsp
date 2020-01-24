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

<title>Nouveau Article</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomePageConnecte">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MonProfil">Mon Profil</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Deconnexion">Déconnexion</a>
			</li>
		</ul>
	</nav>
	
	<div align="center">
		<h6 style="color: red">${messageErreur2}</h6>
		<h6 style="color: red">${messageErreur}</h6>	
		<h6 style="color: red">${incorrectDate}</h6>
	</div>
	
	<form method="post" action="AjoutArticle">
	<div align="center">
	<p style="color: red">${allRiquredFieldsMessage }</p>
	</div>
		<div class="row ">
			<br> <br>
		</div>

		<div class="container-fluid">
			<div class="row">
				<div class="col"></div>
				<div>
					<div class="form-group">
						<div>
							<label for="nom_article">Nom article:</label> 
							<input type="text" class="form-control" id="nom_article" placeholder="Entrer nom article" name="nom_article" value="${nom_article }" required="required">
						</div>
						<div class="form-group">
							<label for="description">Description:</label>
							<textarea class="form-control" maxlength="300" rows="4" id="description" name="description" value="${description}" required="required"></textarea>
						</div>
						<div class="form-group">
							<label for="sel1">Sélectionner la Catégorie:</label> 
							<select class="form-control" style="max-height: 65px" id="categorie" name="categorie" value="${categorie }">
								<option>--Catégories--</option>
								<option>Ameublement</option>
								<option>Informatique</option>
								<option>Sports&Loisirs</option>
								<option>Vêtements</option>
							</select>
						</div>
						<div class="container">
							<div class="row">
								<div class="col">
									<p>Photo de l'article:</p>
									<div class="custom-file">

										<input type="file" class="custom-file-input" id="customFile" name="filename" accept="image/png, image/jpeg" > 
											<label class="custom-file-label" for="customFile" data-browse="Parcourir">Choisissez une photo</label>
									</div>
									<script>
									<!-- pour faire apparaitre le nom du fichier dans la barre-->
										$(".custom-file-input")
												.on(
														"change",
														function() {
															var fileName = $(
																	this)
																	.val()
																	.split("\\")
																	.pop();
															$(this)
																	.siblings(
																			".custom-file-label")
																	.addClass(
																			"selected")
																	.html(
																			fileName);
														});
									</script>
								</div>
								<div style="width: 150%">
									<div>
										<label for="prix_initial">Prix initial:</label> 
										<input type="number" min="0" class="form-control" id="prix_initial" placeholder="XXXXX" name="prix_initial"  value="${prix_initial }" required="required">
									</div>
								</div>
							</div>
							<div>
								<div class="row">
									<div class="col">
										<label for="date_debut_encheres">Date début des enchères :</label> 
										<input type="date" class="form-control datepicker" id="date_debut_encheres" placeholder="jj/mm/aaaa" name="date_debut_encheres" value="${date_debut_encheres }" required="required">

									</div>
									<div class="col">
										<label for="date_fin_encheres">Date fin des enchères :</label>
										<input type="date" class="form-control datepicker" id="date_fin_encheres" placeholder="jj/mm/aaaa" name="date_fin_encheres"  value="${date_fin_encheres }" required="required">
										<div class="input-group date" data-provide="datepicker"></div>
									</div>
								</div>
								<br>
							<!-- 	BLOC ADRESS DE RETRAIT EN CAS DE VENTE -->
						
							<div class="card">
							<h5 align="center">Adresse de retrait </h5>
							<h6 align="center">(Par défaut l'adresse est celle de votre compte utilisateur) </h6>
									<div class="card-body">
										<table style="width: 450px">
											<tr>
												<th>Rue</th>
												<td>
												<input type="text" class="form-control" id="rue" placeholder="Rue" name="rue"  value="${rue}" required="required">
												</td>
											</tr>
											<tr>
												<th>Code Postal</th>
												<td>
												<input type="text" class="form-control" id="code_postal" placeholder="Code postal" name="code_postal"  value="${codePostal}" required="required">
												</td>
											</tr>
										
											<tr>
												<th>Ville</th>
												<td>
												<input type="text" class="form-control" id="ville" placeholder="Ville" name="ville"   value="${ville}" required="required" >
												</td>
											</tr>
										</table>
									</div>
								</div> 
									<!-- 	FIN DU BLOC ADRESS DE RETRAIT EN CAS DE VENTE -->

								<br>
							</div>
						</div>
					</div>
				</div>
				<div class="col"></div>
			</div>
			<div class="row">
				<div class="col"></div>
				<div class="col">
					<button type="submit" class="btn btn-secondary">Enregistrer</button>
					<button type="submit" class="btn btn-secondary">Annuler</button>
				</div>
				<div class="col"></div>
			</div>


		</div>
	</form>
</body>
</html>
