<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--<script>
  $(document).ready(function(){
    setTimeout(function(){ location.reload(); }, 5000);
  });
</script>
-->
<div class="container">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item active" aria-current="page">Inicio</li>
  </ol>
</nav>
<p>Hola ${username}!</p>
<p>Estás controlando a <strong>${characterName}</strong></p>

<h4 class="display-4">Mensajes</h4>
<div class="container">
  <div class="row">
    <div class="col-md-2">
      <span style="color: green; font-weight:bold">DM →</span -><span style="color: orange; font-weight:bold"> Neisseria </span>
    </div>
    <div class="col-md-9">
      Sentis hambre... (Fatiga LVL 1)
    </div>
    <div class="col-md-1">
      Estado
    </div>
  </div> 
  <div class="row">
    <div class="col-md-2">
      DM -> Neisseria
    </div>
    <div class="col-md-9">
      Sentis hambre...
    </div>
    <div class="col-md-1">
      Estado
    </div>
  </div>   
</div>
<hr>
<br>

<h4 class="display-4">Mensajes recibidos</h4>
<g:form controller="Player" action="message">
<div class="container">
  <div class="row">
    <div class="col-md-6">
        <label for="username">Enviar mensaje</label>    
        <g:textField class="form-control" name="message" id="message" placeholder="Ingrese su nombre de usuario..."/>
    </div>
    <div class="col-md-4">
        <label for="username">Destinatario</label>
        <g:select class="form-control" name='id' value="${id}"    
            from='${characters}'
            optionKey="id" optionValue="name"></g:select>
    </div>
    <div class="col-md-2">
        <label>&nbsp;</label><br>
        <g:actionSubmit class="form-control" type="submit" class="btn btn-primary" value="message"/>
    </div>
  </div> 
</div>  
</g:form>
<hr>
<br>

<h4 class="display-4">Pedir acción</h4>
<g:form controller="Player" action="action">
<div class="container">
  <div class="row">
    <div class="col-md-6">
        <label for="username">Accion</label>    
        <g:textField class="form-control" name="actionMessage" id="actionMessage" placeholder="¿Qué quieres decirle al GM?"/>
    </div>
    <div class="col-md-4">
        <label for="username">Destinatario</label>
        <g:textField class="form-control" name="message" id="message" disabled="true" placeholder="Dungeon Master"/>
    </div>
    <div class="col-md-2">
        <label>&nbsp;</label><br>
        <g:actionSubmit class="form-control" type="submit" class="btn btn-primary" value="action"/>
    </div>
  </div> 
</div>  
</g:form>
<hr>
</body>
</div>
</html>