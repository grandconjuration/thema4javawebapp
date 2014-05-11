<%-- 
    Document   : rightsrestore
    Created on : May 10, 2014, 5:32:41 PM
    Author     : Jelle
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.oncloud6.atd.rights.DropdownValues" %>
<%@page import="com.oncloud6.atd.rights.RightsList" %>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Groep aanpassen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% 
        Object msg = request.getAttribute("msg");
        RightsList list = (RightsList) request.getAttribute("rightlist");
        if(msg != null) {
            out.println("<div class=\"alert alert-success\">" + msg + "</div>");
        }%>
        <form action="" method="post">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <input type="text" class="form-control" placeholder="Recht naam" name="groupname" readonly="true" value="<%=list.naam %>">
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">#</span>
            <select name="RightValue" class="form-control">
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<DropdownValues> values = (ArrayList<DropdownValues>) request.getAttribute("dropdown");
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
