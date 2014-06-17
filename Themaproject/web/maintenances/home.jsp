<%-- 
    Document   : ListmaintenancePage
    Created on : 19-mei-2014, 16:17:56
    Author     : Laura
--%>

<%@page import="com.oncloud6.atd.maintenances.MaintenanceList"%>
<%@page import="com.oncloud6.atd.maintenances.DropdownValues"%>
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
        <form action="" method="get">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <select name="cid" class="form-control">
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<DropdownValues> values = (ArrayList<DropdownValues>) request.getAttribute("klantlist");
                // print the information about every category of the list
                for(DropdownValues dlist : values) {
                    %>
                    <option value="<%=dlist.key %>"<% if(dlist.selected) { out.println(" selected=\"true\"");} %>><%=dlist.value %></option>
                    <%
                }
                %>
            </select>
        </div>
           <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
        </form>
    </div>
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Merk</th>
                    <th>Type</th>
                    <th>Kenteken</th>
                    <th><a href="maintenancesadd" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>
                </tr>
            </thead>
            <tbody>
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<MaintenanceList> list = (ArrayList<MaintenanceList>) request.getAttribute("list");

                // print the information about every category of the list
                for(MaintenanceList maintenance : list) {
                    %>
                    <tr>
                        <td><%=maintenance.merk %></td>
                        <td><%=maintenance.type %></td>
                        <td><%=maintenance.kenteken %></td>
                        <td><a href="maintenancesedit?id=<%=maintenance.onderhoudId %>" class="btn btn-succes"><i class="glyphicon glyphicon-edit"></i></a></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />
