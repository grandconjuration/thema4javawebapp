<%-- 
    Document   : edit
    Created on : May 24, 2014, 2:54:05 PM
    Author     : Jelle
--%>

<%@page import="com.oncloud6.atd.maintenances.DropdownValues"%>
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
    <jsp:include page="../theme/footer.jsp" />
