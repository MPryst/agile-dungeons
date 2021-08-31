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
<p>Recordá que <strong>un gran poder conlleva una gran responsabilidad</strong></p>

<h4 class="display-4">Peticiones</h4>
<div class="container">
  <div class="row">
    <div class="col-md-2">
      <span style="color: green; font-weight:bold">Player 1 →</span -><span style="color: orange; font-weight:bold"> Player 2 </span>
    </div>
    <div class="col-md-9">
      Cuidado a la derecha! Hay bandidos!!!
    </div>
    <div class="col-md-1">
      Mensaje
    </div>
  </div> 
  
<div class="container">
  <div class="row">        
    <div class="col-md-2">
        <g:form controller="gameMaster" action="accept">
            <label>&nbsp;</label><br>
            <g:actionSubmit type="submit" class="form-control btn btn-success" value="accept"/>
            <g:textField class="form-control" name="idMessage" id="message" value="999Accept" hidden="true"/>
        </g:form>
    </div>      
    <div class="col-md-2">
        <g:form controller="gameMaster" action="cancel">
            <label>&nbsp;</label><br>
            <g:actionSubmit type="submit" class="form-control btn btn-danger" value="cancel"/>
            <g:textField class="form-control" name="idMessage" id="message" value="999Cancel" hidden="true"/>
        </g:form>
    </div>      
 </div>     
</div>
<hr>
<br>

<h4 class="display-4">Enviar mensaje</h4>
<g:form controller="gameMaster" action="message">
<div class="container">
  <div class="row">
    <div class="col-md-6">
        <label for="username">Mensaje</label>    
        <g:textField class="form-control" name="message" id="message" placeholder="Qué quiere decir?"/>
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

<h4 class="display-4">Enviar a un grupo</h4>
<g:form controller="gameMaster" action="groupMessage">
<div class="container">
  <div class="row">
    <div class="col-md-6">
        <label for="username">Mensaje</label>    
        <g:textField class="form-control" name="message" id="message" placeholder="Qué quiere decir?"/>
    </div>
    <div class="col-md-4">
        <label for="username">Destinatario</label>
        <g:select class="form-control" name='id' value="${id}"    
            from='${characters}'
            optionKey="id" optionValue="name"></g:select>
    </div>
    <div class="col-md-2">
        <label>&nbsp;</label><br>
        <g:actionSubmit class="form-control" type="submit" class="btn btn-primary" value="groupMessage"/>
    </div>
  </div> 
</div>  
</g:form>
<hr>
<br>
<hr>
</body>
</div>
</html>