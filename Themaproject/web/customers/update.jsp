<%-- 
    Document   : update
    Created on : 10-mei-2014, 17:51:36
    Author     : Laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Klant aanpassen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("message");
        if(msg != null) {
            out.println("<div class=\"alert alert-fail\">" + msg + "</div>");
        }%>
        <form action="" method="post">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Volledige naam" name="customername" value="<% out.println(request.getAttribute("klant_naam")); %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Adres" name="customeraddress" value="<% out.println(request.getAttribute("klant_adres")); %>">
        </div>
              <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Postcode" name="customerpostcode" value="<% out.println(request.getAttribute("klant_postcode")); %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Woonplaats" name="customerplace" value="<% out.println(request.getAttribute("klant_woonplaats")); %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Korting" name="discount" value="<% out.println(request.getAttribute("klant_korting")); %>">
        </div>   
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="date" class="form-control" placeholder="Geboortedatum" name="dateofbirth" value="<% out.println(request.getAttribute("klant_geboortedatum")); %>">
        </div>  
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="password" class="form-control" placeholder="Wachtwoord" name="password" value="<% out.println(request.getAttribute("klant_wachtwoord")); %>">
        </div>
           <br/> <input type="submit"  class="btn btn-default" value="Aanpassen" />
            
    </div>
    <jsp:include page="../theme/footer.jsp" />
