<%-- 
    Document   : edit
    Created on : May 10, 2014, 5:32:25 PM
    Author     : Jelle
--%>
<%@page import="java.util.ArrayList;" %>
<%@page import="com.oncloud6.atd.rights.RightsList;" %>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Groep aanpassen</h2>
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
            <input type="text" class="form-control" placeholder="Groep naam" name="groupname" value="<%
            Object name = request.getAttribute("name");
            if(name != null) {
                out.println(name);
            }
            %>">
        </div>
            
           <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
        </form>
    </div>
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Naam</th>
                    <th>Inhoud</th>
                    <th></th>
                </tr>
            </thead>   
            <tbody>
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<RightsList> list = (ArrayList<RightsList>) request.getAttribute("rightlist");
                String id = (String) request.getAttribute("id");

                // print the information about every category of the list
                for(RightsList right : list) {
                    %>
                    <tr>
                        <td><%=right.naam %></td>
                        <td><% if(right.value == null) { %><%=right.defaultValue %><% }else{ %><%=right.value %>(<%=right.defaultValue %>)<% } %></td>
                        <td><a href="groupsrightsedit?id=<%=right.id %>&gid=<%=id %>" class="btn btn-succes"><i class="glyphicon glyphicon-edit"></i></a><% if(right.value != null) { %> <a href="groupsrightsrestore?id=<%=right.id %>&gid=<%=id %>" class="btn btn-succes"><i class="glyphicon glyphicon-trash"></i></a><% } %></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />