<%-- 
    Document   : ListmaintenancePage
    Created on : 19-mei-2014, 16:17:56
    Author     : Laura
--%>

<%@page import="com.oncloud6.atd.maintenance.MaintenanceList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Overzicht onderhouden</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("msg");
        if(msg != null) {
            out.println("<div class=\"alert alert-success\">" + msg + "</div>");
        }%>
        <table border="1" style="width:750px">
            <tr>
                <td>Onderhouds id:</td>
                <td>Bedrijfs id:</td>
                <td>Auto id:</td>
                <td>Datum:</td>
                <td>Beschrijving:</td>
                <td>Status:</td>
                <td>Manuur:</td>
            </tr>
        
       <%  
                // retrieve your list from the request, with casting 
                ArrayList<MaintenanceList> list = (ArrayList<MaintenanceList>) request.getAttribute("list");

                // print the information about every category of the list
                for(MaintenanceList maintenanceList : list) {
                    %>
                    <tr>
                        <td><%=maintenanceList.onderhoudId %></td>
                        <td><%=maintenanceList.bedrijfsId %></td>
                        <td><%=maintenanceList.autoId %></td>
                        <td><%=maintenanceList.datum %></td>
                        <td><%=maintenanceList.beschrijving %></td>
                        <td><%=maintenanceList.status %></td>
                        <td><%=maintenanceList.manuur %></td>
                    </tr>
                    <%
                }
                %>
        
        </table>  
    </div>
    <jsp:include page="../theme/footer.jsp" />
