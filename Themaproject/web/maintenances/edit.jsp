<%-- 
    Document   : edit
    Created on : May 24, 2014, 2:54:05 PM
    Author     : Jelle
--%>

<%@page import="com.oncloud6.atd.maintenances.DropdownValues"%>
<%@page import="com.oncloud6.atd.maintenances.PartList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Onderhoud uitvoeren</h2>
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
            <input type="text" class="form-control" placeholder="Beschrijving" name="description" value="<%
            Object description = request.getAttribute("description");
            if(description != null) {
                out.println(description);
            }
            %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Man uren" name="manhour" value="<%
            Object manhour = request.getAttribute("manhour");
            if(manhour != null) {
                out.println(manhour);
            }
            %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <select name="status" class="form-control">
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<DropdownValues> values = (ArrayList<DropdownValues>) request.getAttribute("status");
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
        <%
            String id = (String) request.getAttribute("id");
        %>
        <table class="table">
            <thead>
                <tr>
                    <th>Naam</th>
                    <th>Hoeveelheid</th>
                    <th>Prijs per stuk</th>
                    <th><a href="maintenancesaddpart?id=<%=id %>" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>
                </tr>
            </thead>   
            <tbody>
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<PartList> list = (ArrayList<PartList>) request.getAttribute("partlist");

                // print the information about every category of the list
                for(PartList part : list) {
                    %>
                    <tr>
                        <td><%=part.naam %></td>
                        <td><%=part.hoeveelheid %></td>
                        <td>€ <%=part.prijs %></td>
                        <td><a href="maintenancesdeletepart?id=<%=part.id %>&mid=<%=id %>" class="btn btn-succes"><i class="glyphicon glyphicon-trash"></i></a></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />
