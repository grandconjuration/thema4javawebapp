<%-- 
    Document   : index
    Created on : May 10, 2014, 5:32:17 PM
    Author     : Jelle
--%>
<%@page import="java.util.ArrayList;" %>
<%@page import="com.oncloud6.atd.rights.GroupsList;" %>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Groep overzicht</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Naam</th>
                    <th><a href="groupsadd" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>
                </tr>
            </thead>
            <tbody>
                <%  
                // retrieve your list from the request, with casting 
                ArrayList<GroupsList> list = (ArrayList<GroupsList>) request.getAttribute("grouplist");

                // print the information about every category of the list
                for(GroupsList group : list) {
                    %>
                    <tr>
                        <td><%=group.naam %></td>
                        <td><a href="groupsedit?id=<%=group.id %>" class="btn btn-succes"><i class="glyphicon glyphicon-edit"></i></a> <a href="groupsdelete?id=<%=group.id %>" class="btn btn-succes"><i class="glyphicon glyphicon-trash"></i><a/></td>
                    </tr>
                    <%
                }
                %>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />