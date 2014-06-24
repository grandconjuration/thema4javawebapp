<%-- 
    Document   : index
    Created on : 24-jun-2014, 12:39:38
    Author     : Simon Whiteley <simonwhiteley@hotmail.com>
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oncloud6.atd.domain.Planning"%>
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
                    <th>Datum start planning</th>
                    <th>Onderhoud beschrijving</th>
                    <th>Monteur</th>  
                    <th><a href="schedulesadd" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>                    
                </tr>
            </thead>
            <tbody>
                <%  
                List<Planning> planningList = (List<Planning>) request.getAttribute("planningList");
                // print the information about every category of the list
                for(Planning pl : planningList) {
                    %>
                    <tr>
                        <td><% String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(pl.getDatumStart());
                out.print(date);%> uur</td>
                        <td><%= pl.getOnderhoud().getBeschrijving() %></td>
                        <td><%= pl.getMonteur().getNaam() %></td>                
                        <td></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />
