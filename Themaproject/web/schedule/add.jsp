<%-- 
    Document   : add
    Created on : 26-mei-2014, 19:27:05
    Author     : Simon Whiteley <simonwhiteley@hotmail.com>
--%>
<%@page import="com.oncloud6.atd.domain.Monteur"%>
<%@page import="com.oncloud6.atd.domain.Onderhoud"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Onderhoud inplannen</h2>
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
                <span class="input-group-addon">Kies onderhoud</span>
                <select class="form-control" name="onderhoud">
                    <%
                        List<Onderhoud> onderhoudList = (List<Onderhoud>) request.getAttribute("onderhoudList");
                        for (Onderhoud onderhoud : onderhoudList) {
                    %>
                    <option value="<%= onderhoud.getId()%>"><%= onderhoud.getId()%> - <%= onderhoud.getBeschrijving()%></option>
                    <% } %>
                </select>
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Datum start</span>
                <input type="date" class="form-control" placeholder="Start datum" name="datum_start">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Datum einde</span>
                <input type="date" class="form-control" placeholder="Eind datum" name="datum_einde">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Kies monteur</span>
                <select class="form-control" name="monteur">
                    <%
                        List<Monteur> monteurList = (List<Monteur>) request.getAttribute("monteurList");
                        for (Monteur monteur : monteurList) {
                    %>
                    <option value="<%= monteur.getId()%>"><%= monteur.getId()%> - <%= monteur.getNaam() %></option>
                    <% }%>
                </select>
            </div>
            <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />

    </div>
    <jsp:include page="../theme/footer.jsp" />
