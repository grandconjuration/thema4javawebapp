<%-- 
    Document   : add
    Created on : May 10, 2014, 5:32:31 PM
    Author     : Jelle
--%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Groep toevoegen</h2>
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
    <jsp:include page="../theme/footer.jsp" />
