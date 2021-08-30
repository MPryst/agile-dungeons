<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="container">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item active" aria-current="page">Inicio</li>
  </ol>
</nav>

<g:form controller="Login" action="login">
  <div class="form-group">
    <label for="username">Usuario</label>    
    <g:textField class="form-control" name="username" placeholder="Ingrese su nombre de usuario..."/><br/>
    <small id="usernameHelp" class="form-text text-muted">¡Logueate con tu nombre de usuario, no de personaje!</small>
  </div>
  <div class="form-group">
    <label for="password">Contraseña</label>
    <g:passwordField type="password" class="form-control" name="password" placeholder="Ingrese su contraseña..." /><br/>                
  </div>    
  <g:actionSubmit type="submit" class="btn btn-primary" value="login"/>
  </g:form>

<hr>

	</body>

</div>
</html>