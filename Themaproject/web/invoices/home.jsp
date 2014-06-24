<%-- 
    Document  t
    Created on n-2014, 9:50:38
    Author     : Niels, simon
--%>

<%@page import="com.oncloud6.atd.domain.Factuur"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Overzicht onderhouden</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Factuur datum</th>
                    <th>Klant</th>
                    <th>Totaal bedrag</th>                    
                    <th><a href="maintenancesinvoice" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>                    
                </tr>
            </thead>
            <tbody>
                <%  
                List<Factuur> factuurList = (List<Factuur>) request.getAttribute("factuurList");
               
                for(Factuur fac : factuurList) {
                    %>
                    <tr>
                        <td><% String date = new SimpleDateFormat("dd-MM-yyyy").format(fac.getFactuurDatum());
                out.print(date);%> uur</td>
                        <td><%= fac.getKlantNaam() %></td> 
                        <td>€<%= fac.getTotaalBedrag() %></td>           
                        <td></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />
