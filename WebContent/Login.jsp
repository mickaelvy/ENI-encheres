<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Login</title>


</head>

<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="navbar-brand" href="${pageContext.request.contextPath}/HomePageConnecte"> <img
					src="${pageContext.request.contextPath}/logo.jpg" class="rounded"
					style="width: 300px; height: 60px;">
			</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Sinscrire.jsp">Pas de compte? S'inscrire</a>
			</li>
		
		</ul>
	</nav>
	<div class="row" align="center">
		<br>
		<br>
	</div>
	
	<div align="center">
		<p style="color: red">${erreurIdentifiant}</p>
		<p style="color: red">${messagePourSeReconnecter}</p>
		<h6 style="color: red">${messageErreur}</h6>
	</div>
<%-- 	<h4 style="color: green">${succesRegister}</h4> --%>
		<form action="SeConnecter" method="post">
			<div class="row">
				<div class="col"></div>
				<div>
					<div class="form-group">
						<label for="pseudo">pseudo:</label> 
						<input type="text" class="form-control" id="pseudo" placeholder="Entrer pseudo" name="pseudo" value="${pseudo}" required>
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<div class="form-group">
						<label for="mot_de_passe">Mot de passe:</label> <input
							type="password" class="form-control" id="mot_de_passe"
							placeholder="Entrer mot de passe" name="mot_de_passe" required>
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Ce champ est obligatoire.</div>

					</div>
						<div class="form-group">
						<input type="checkbox" id="souvenir" name="souvenir" value="checked"/>
						<label for="souvenir">Se souvenir de moi</label>
					</div>
				</div>
				<div class="col"></div>

			</div>




			<div class="row">
				<div class="col"></div>
				<div>
					<button type="submit" style="" class="btn btn-secondary">Se connecter</button>
				</div>
				<div class="col"></div>
			</div>
		</form>
	</div>
</body>
</html>
