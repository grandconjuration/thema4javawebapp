<%-- 
    Document   : register
    Created on : 11-mei-2014, 17:19:43
    Author     : Niels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Registreren</h2>
    </div>
</div>
<div class="container">
    <div class="row">
        <form action="accountsregister" method="post">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Gebruikersnaam" name="username">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="password" class="form-control" placeholder="Wachtwoord" name="password">
            </div> 
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="password" class="form-control" placeholder="Herhaal wachtwoord" name="chckpassword">
            </div> 
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Naam" name="naam">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Adres" name="address">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="zip" class="form-control" placeholder="Postcode" name="zipcode">
            </div>   
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="datetime" class="form-control" placeholder="Geboortedatum" name="birthdate">
            </div>              
               <br/> <input type="submit"  class="btn btn-default" value="Registreren" />
        
    </div>
    <jsp:include page="../theme/footer.jsp" />
    
