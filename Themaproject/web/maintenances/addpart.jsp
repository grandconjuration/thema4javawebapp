<%-- 
    Document   : addpart
    Created on : May 25, 2014, 2:09:30 PM
    Author     : Jelle
--%>

<%@page import="com.oncloud6.atd.maintenances.DropdownValues"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Onderdeel toevoegen</h2>
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
            <select name="part" class="form-control">
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<DropdownValues> values = (ArrayList<DropdownValues>) request.getAttribute("partlist");
                // print the information about every category of the list
                for(DropdownValues dlist : values) {
                    %>
                    <option value="<%=dlist.key %>"<% if(dlist.selected) { out.println(" selected=\"true\"");} %>><%=dlist.value %></option>
                    <%
                }
                %>
            </select>
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Hoeveelheid" name="amount">
        </div>
           <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
        </form>
    </div>
    <jsp:include page="../theme/footer.jsp" />

