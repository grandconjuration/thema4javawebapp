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
            <input type="text" class="form-control" placeholder="Groep naam" name="groupname">
        </div>
            
           <br/> <input type="submit"  class="btn btn-default" value="Voltooien" />
        </form>
    </div>
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Naam</th>
                    <th>Waarde</th>
                    <th><a href="groupsadd" class="btn btn-succes"><i class="glyphicon glyphicon-plus"></i></a></th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>df</td>
                        <td>true</td>
                        <td><a href="groupsrightsedit?id=1" class="btn btn-succes"><i class="glyphicon glyphicon-edit"></i> <a href="groupsrightsrestore?id=1" class="btn btn-succes"><i class="glyphicon glyphicon-trash"></i></td>
                    </tr>
            </tbody>
        </table>
    </div>
    <jsp:include page="../theme/footer.jsp" />