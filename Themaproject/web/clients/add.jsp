<%-- 
    Document   : GebruikerToevoegen
    Created on : 10-mei-2014, 13:41:39
    Author     : Simon Whiteley <simonwhiteley@hotmail.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Gebruiker toevoegen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("msg");
        if(msg != null) {
            out.println("<div class=\"alert alert-success\">" + msg + "</div>");
        }%>
        <form action="UserAdd" method="post">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Gebruikersnaam" name="username">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="password" class="form-control" placeholder="Wachtwoord" name="password">
        </div>
            
           <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
            
    </div>
    <jsp:include page="../theme/footer.jsp" />
