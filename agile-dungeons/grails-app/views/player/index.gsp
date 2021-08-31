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
<g:if test="${awake == true}">
  <g:each in="${messages}">
  <div class="row">
      <div class="col-md-2">
        <span style="color: green; font-weight:bold">${it.emisor} →</span -><span style="color: blue; font-weight:bold"> ${it.receptor} </span>
      </div>
      <div class="col-md-9">
        ${it.content}      
      </div>
      <div class="col-md-1">
      <span style="color: ${it.color}; font-weight:bold">${it.approved}</span>
      </div>
    </div>   
  </g:each>

  </div>
  <hr>
  <br>

  <h4 class="display-4">Enviar mensaje</h4>
  <g:form controller="Player" action="message">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
          <label for="username">Enviar mensaje</label>    
          <g:textField class="form-control" name="message" id="message" placeholder="¿Qué quiere decirle?"/>
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
</g:if>

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