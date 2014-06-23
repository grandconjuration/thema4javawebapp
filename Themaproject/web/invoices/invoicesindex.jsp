<%-- 
    Document  t
    Created on n-2014, 9:50:38
    Author     : Niels
--%>
<%@page import="com.oncloud6.atd.domain.FactuurItem"%>
<%@page import="com.oncloud6.atd.domain.Factuur"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Facturen beheren</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <span class="input-group-addon">Kies een factuur</span>
        <%
        if(request.getAttribute("factuurList") != null) {
        
            List<Factuur> factuurList = (List<Factuur>) request.getAttribute("factuurList");
            for (Factuur f : factuurList) {
                out.println(f.getFactuurNummer() + "\n");
            }
        }%>
    </div>               
</div>
<jsp:include page="../theme/footer.jsp" />

