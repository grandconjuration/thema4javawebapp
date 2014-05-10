<%-- 
    Document   : Klant toevoegen
    Created on : 10-mei-2014, 16:51:28
    Author     : Simon Whiteley <simonwhiteley@hotmail.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Klant toevoegen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("msg");
        if(msg != null) {
            out.println("<div class=\"alert alert-success\">" + msg + "</div>");
        }%>
        <form action="" method="post">
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
            
    </div>
    <jsp:include page="../theme/footer.jsp" />
