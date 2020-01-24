<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>home</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="#"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link"  href="${pageContext.request.contextPath}/Sinscrire.jsp">S'inscrire</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/SeConnecter">Se
					connecter</a></li>
		</ul>	
	</nav>
	
	
	
	<div class="row">
		<br> <br>
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<h3>Liste des enchéres</h3>
		</div>
		<div class="col"></div>
	</div>

	<div class="row">
		<br> <br>
	</div>
	<div class="row" style="margin-left: 40px;">
		<div class="col">
		<font color="red">${aucunMotTapeMessage}</font>
		<form action="RechercherArticleParMotClef" method="post">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Rechercher un article" name="motCle" value="${motClef}">
				<div class="input-group-append">
				<button class="btn btn-secondary" type="submit">Rechercher</button>
				</div>
			</div>
		</form>
			<div class="form-group">
			<label for="sel1">Select Catégories:</label> 
			<form action="HomePageConnecte" method="post">
			    <select name="selectCategorie" id="selectCategorie" onchange="this.form.submit()">
			        <option value="0">--Catégories--</option>
			        <option value="1">Meublement</option>
			        <option value="2">Informatique</option>
			        <option value="3">Sports/Loisirs</option>
			        <option value="4">Vétements/Accessoires</option>
			    </select>
			</form>
			</div>
			
		

		</div>
		<div class="col"></div>
	</div><br><br>
	
	<div class="row">  		
 	<c:forEach items="${articles}" var="article">					
		 	<div class="col-md-4" align="center">
<%-- <img src="${product.getImage() }" class="img-responsive" ><br> --%>
                     <div class="bg-light">
                        <p>
	                        <form  style="margin-bottom: -15px" action="SonProfil" method="post">
							<input type="hidden" name="noUtilisateur" value="${article.noUtilisateurVendeur}">
							<label>Vendeur :</label>
							<input type="submit" value="${article.pseudoVendeur}" style="padding: 0em; border-style: none; color: green;">
							</form>
							
							
					    </p>
						<p style=" font-weight:bold; font-size:20px;margin-bottom: -5px "> <c:out value="${article.nomArticle}"></c:out></p>
						
 						<p style="margin: 0em;"> <c:out value="${ article.description}"></c:out></p>	
 						
 						<p style="margin: 0em;" > <font color="gray">Fin enchère le :</font> <c:out value="${ article.dateFinEncheres}"></c:out></p>
 						<p style="color: red;margin: 0em;">  <font color="gray">Prix initial :</font>   <c:out  value="${article.prixInitial}" ></c:out> Points</p>
 						<p style="color: red;margin: 0em;">  <font color="gray">Meilleure offre :</font>   <c:out  value="${article.meilleureOffreActuelle}" ></c:out> Points</p>
 						
		 				
		 				<form  style="margin-bottom: -15px" action="DetailsVente" method="post">
							<input type="hidden" name="noArticle" value="${article.noArticle}">
							<input type="submit" value="Enchérir" class="btn btn-success btn-sm">
						</form>
						
		 			</div> 	<br>
		 	</div>						
 	</c:forEach>							
</div>
	
	
	
	
	
</body>
</html>