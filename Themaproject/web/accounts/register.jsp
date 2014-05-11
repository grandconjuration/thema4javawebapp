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
        <%
        Object msgs = request.getAttribute("msgs");
        if(msgs != null) {
            out.println(msgs);
        }
        %>
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
                <input type="text" class="form-control" placeholder="Volledige naam" name="naam">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Adres" name="adres">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Korting" name="korting">
            </div>   
            <div class="input-group input-group-lg">
                <span class="input-group-addon">#</span>
                <input type="text" class="form-control" placeholder="Geboortedatum" name="geboortedatum">
            </div>              
               <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
        </form>
    </div>
    <jsp:include page="../theme/footer.jsp" />
    
