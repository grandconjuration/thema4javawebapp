<%-- 
    Document   : add
    Created on : 22-Jun-2014, 12:42:08
    Author     : Simon Whiteley
--%>
<%@page import="java.util.List"%>
<%@page import="com.oncloud6.atd.domain.Klant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Factuur opstellen</h2>
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
                <span class="input-group-addon">Kies een klant</span>
                <select class="form-control" name="klant">
                    <%
                        List<Klant> klantList = (List<Klant>) request.getAttribute("klantList");
                        for (Klant klant : klantList) {
                    %>
                    <option value="<%= klant.getId()%>"><%= klant.getId()%> - <%= klant.getKlantNaam() %></option>
                    <% }%>
			 </select>
            </div>
			 <% if((Boolean) request.getAttribute("idSet") == true) { %>
			 <% } %>
            <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
	   </form>

    </div>
    <jsp:include page="../theme/footer.jsp" />
