<%-- 
    Document   : ListMaintainanceCustomer
    Created on : 20-mei-2014, 17:24:34
    Author     : Laura
--%>


<%@page import="com.oncloud6.atd.maintainance.MaintainanceList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Overzicht onderhouden klant</h2>
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
                ArrayList<MaintainanceList> list = (ArrayList<MaintainanceList>) request.getAttribute("list");

                // print the information about every category of the list
                for(MaintainanceList maintainances : list) {
                    %>
                    <tr>
                        <td><%=maintainances.onderhoudId %></td>
                        <td><%=maintainances.bedrijfsId %></td>
                        <td><%=maintainances.autoId %></td>
                        <td><%=maintainances.datum %></td>
                        <td><%=maintainances.beschrijving %></td>
                        <td><%=maintainances.status %></td>
                        <td><%=maintainances.manuur %></td>
                    </tr>
                    <%
                }
                %>
        
        </table>  
    </div>
    <jsp:include page="../theme/footer.jsp" />

