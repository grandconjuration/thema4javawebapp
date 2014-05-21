<%-- 
    Document   : add
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
        <form action="addcarcustomer" method="post">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">merk</span>
                <input type="text" class="form-control" placeholder="volledige naam" name="surname">
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">type</span>
                <input type="text" class="form-control" placeholder="adres" name="address">
            </div>
            <div>
                <span class="input-group-addon">kenteken</span>
                <input type="zipcode" class="form-control" placeholder="1234AB" name="zip">
            </div>  
                <p>*Deze gegevens zijn verplicht</p>
               <br/> <input type="submit"  class="btn btn-default" value="Registreren" />    
        </form>
    </div>
    <jsp:include page="../theme/footer.jsp" />
