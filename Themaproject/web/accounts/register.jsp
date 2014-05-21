<%-- 
    Document   : register
    Created on : 11-mei-2014, 17:19:43
    Author     : Niels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Registreren</h2>
    </div>
</div>
<div class="container">
    <div class="row">
        <% Object message = request.getAttribute("message");
        if(message != null) {
            out.println("<div class=\"alert alert-success\">" + message + "</div>");
        }%>
        <form action="accountsregister" method="post">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">volledige naam*</span>
                <input type="text" class="form-control" placeholder="volledige naam" name="surname">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">straat en huisnummer*</span>
                <input type="text" class="form-control" placeholder="adres" name="address">
                <span class="input-group-addon">postcode</span>
                <input type="zipcode" class="form-control" placeholder="1234AB" name="zip">
                <span class="input-group-addon">woonplaats</span>
                <input type="text" class="form-control" placeholder="bijv. Amsterdam" name="city">
            </div> 
            <div class="input-group input-group-lg">
                <span class="input-group-addon">geboortedatum*</span>
                <input type="date" class="form-control" placeholder="geboortedatum" name="birthdate">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">gebruikersnaam*</span>
                <input type="text" class="form-control" placeholder="gebruikersnaam" name="username">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">wachtwoord*</span>
                <input type="password" class="form-control" placeholder="wachtwoord" name="password">
            </div> 
            <div class="input-group input-group-lg">
                <span class="input-group-addon">herhaal wachtwoord*</span>
                <input type="password" class="form-control" placeholder="Herhaal wachtwoord" name="chckpassword">
            </div>  
                <p>*Deze gegevens zijn verplicht</p>
               <br/> <input type="submit"  class="btn btn-default" value="Registreren" />    
        </form>
    </div>
    <jsp:include page="../theme/footer.jsp" />
    
