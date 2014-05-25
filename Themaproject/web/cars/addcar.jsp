<%-- 
    Document   : addCarCustomer
    Created on : 21-mei-2014, 16:26:48
    Author     : Niels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<jsp:include page="../theme/header.jsp" />
<div class="jumbotron">
    <div class="container">
        <h2>Auto toevoegen</h2>
    </div>
</div>
<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("msg");
        if(msg != null) {
            out.println("<div class=\"alert alert-success\">" + msg + "</div>");
        }%>
        <form action="carsadd" method="post">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">merk</span>
                <input type="text" class="form-control" name="brand">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">type</span>
                <input type="text" class="form-control" name="type">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">kenteken</span>
                <input type="zipcode" class="form-control" name="licenseplate">
            </div>  
                <p>*Deze gegevens zijn verplicht</p>
               <br/> <input type="submit"  class="btn btn-default" value="Auto toevoegen" />    
        </form>
    </div>
    <jsp:include page="../theme/footer.jsp" />
