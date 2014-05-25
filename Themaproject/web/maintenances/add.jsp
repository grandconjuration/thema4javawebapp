<%-- 
    Document   : add
    Created on : 25-mei-2014, 23:45:07
    Author     : Simon Whiteley <simonwhiteley@hotmail.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Onderhoud toevoegen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("msg");
            if (msg != null) {
                out.println("<div class=\"alert alert-success\">" + msg + "</div>");
            }%>
        <form action="" method="post">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Kies auto</span>
                <select class="form-control" name="auto">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Datum onderhoud</span>
                <input type="date" class="form-control" placeholder="Datum onderhoud" name="datum">
            </div>
<div class="input-group input-group-lg">
                <span class="input-group-addon">Beschrijving</span>
                <input type="text" class="form-control" placeholder="beschrijving..." name="beschrijving">
            </div>
            <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />

    </div>
    <jsp:include page="../theme/footer.jsp" />
