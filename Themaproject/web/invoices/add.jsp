<%-- 
    Document   : add
    Created on : 22-Jun-2014, 12:42:08
    Author     : Simon Whiteley
--%>
<%@page import="com.oncloud6.atd.domain.FactuurItem"%>
<%@page import="com.oncloud6.atd.domain.Factuur"%>
<%@page import="com.oncloud6.atd.domain.GebruiktOnderdeel"%>
<%@page import="com.oncloud6.atd.domain.Onderhoud"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.oncloud6.atd.domain.Klant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../theme/header.jsp" />
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h2>Factuur opstellen</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <% Object msg = request.getAttribute("message");
            if (msg != null) {
                out.println("<div class=\"alert alert-success\">" + msg + "</div>");
            }%>

        <% if ((Boolean) request.getAttribute("idSet") == false) { %>
        <form action="" method="get">
            <div class="input-group input-group-lg">
                <span class="input-group-addon">Kies een klant</span>
                <select class="form-control" name="id">
                    <%
                        List<Klant> klantList = (List<Klant>) request.getAttribute("klantList");
                        for (Klant klant : klantList) {
                    %>
                    <option value="<%= klant.getId()%>"><%= klant.getId()%> - <%= klant.getKlantNaam()%></option>
                    <% } %>
                </select>
            </div>

            <br/> <input type="submit"  class="btn btn-default" value="Doorgaan" />
        </form>                
        <% } else {
            Klant gekozenKlant = (Klant) request.getAttribute("gekozenKlant");%>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">volledige naam</span>
            <input type="text" class="form-control" placeholder="volledige naam" name="name" value="<%= gekozenKlant.getKlantNaam()%>" readonly="readonly">
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">adres</span>              
            <input type="text" class="form-control" placeholder="adres" name="adres" value="<%= gekozenKlant.getKlantAdres()%>" readonly="readonly">                
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">woonplaats</span>  
            <input type="text" class="form-control" placeholder="woonplaats" name="woonplaats" value="<%= gekozenKlant.getWoonplaats()%>" readonly="readonly">
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">postcode</span>  
            <input type="text" class="form-control" placeholder="postcode" name="postcode" value="<%= gekozenKlant.getPostcode()%>" readonly="readonly">
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">geboortedatum</span>  
            <input type="text" class="form-control" placeholder="geboortedatum" name="geboortedatum" value="<% String date = new SimpleDateFormat("dd-MM-yyyy").format(gekozenKlant.getGeboorteDatum());
                out.print(date); %>" readonly="readonly">                        
        </div>
        <% if ((Boolean) request.getAttribute("onderhoudIdSet") == false) {%>        
        <form action="" method="get">
            <div class="input-group input-group-lg">
                <input type="hidden" name="id" value="<%= gekozenKlant.getId()%>">
                <span class="input-group-addon">kies het onderhoud</span>
                <select class="form-control" name="onderhoudId">
                    <%
                        List<Onderhoud> onderhoudList = (List<Onderhoud>) request.getAttribute("onderhoudList");
                        for (Onderhoud onderhoud : onderhoudList) {
                    %>
                    <option value="<%= onderhoud.getId()%>"><%= onderhoud.getId()%> - <%= onderhoud.getBeschrijving()%></option>
                    <% }%>
                </select>
            </div>
            <br/> <input type="submit"  class="btn btn-default" value="Doorgaan" />
        </form>   
        <% } else {
            Onderhoud gekozenOnderhoud = (Onderhoud) request.getAttribute("gekozenOnderhoud");%>
        <br>
            <h2>Onderhoud van <% String date2 = new SimpleDateFormat("dd-MM-yyyy").format(gekozenOnderhoud.getDatum());
                out.print(date2);%></h2>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">merk auto</span>  
            <input type="text" class="form-control" placeholder="automerk" name="automerk" value="<%= gekozenOnderhoud.getAuto().getMerk()%>" readonly="readonly">
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">type auto</span>  
            <input type="text" class="form-control" placeholder="automerk" name="automerk" value="<%= gekozenOnderhoud.getAuto().getType()%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">auto kenteken</span>  
            <input type="text" class="form-control" placeholder="autokenteken" name="autokenteken" value="<%= gekozenOnderhoud.getAuto().getKenteken()%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">auto chassisnr</span>  
            <input type="text" class="form-control" placeholder="autochassisnr" name="autochassisnr" value="<%= gekozenOnderhoud.getAuto().getChassisNummer()%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">datum onderhoud</span>  
            <input type="text" class="form-control" placeholder="datumonderhoud" name="datumonderhoud" value="<%= date2%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">gebruikte onderdelen</span>  
            <div class="form-control">
                <% for (GebruiktOnderdeel onderdeel : gekozenOnderhoud.getGebruikteOnderdelen()) {%> 
                Onderdeel: <%= onderdeel.getOnderdeel().getNaam()%> - 
                Hoeveelheid: <%= onderdeel.getHoeveelheid()%>
                <% } %>               
            </div>
        </div>
        <br>
        <h2>Factuur gegevens</h2>
        <% Factuur factuur = (Factuur) request.getAttribute("factuur"); %>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">factuur datum</span>  
                   <input type="text" class="form-control" placeholder="factuurdatum" name="factuurdatum" value="<% String date3 = new SimpleDateFormat("dd-MM-yyyy").format(factuur.getFactuurDatum());
                out.print(date3);%>" readonly="readonly">
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">factuur klant naam</span>  
            <input type="text" class="form-control" placeholder="factuurklantnaam" name="factuurklantnaam" value="<%= factuur.getKlantNaam()%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">factuur klant adres</span>  
            <input type="text" class="form-control" placeholder="factuurklantadres" name="factuurklantadres" value="<%= factuur.getKlantAdres()%>" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">factuur klant korting</span>  
            <input type="text" class="form-control" placeholder="factuurklantkorting" name="factuurklantkorting" value="<%= Math.round((factuur.getFactuurKorting()))%>%" readonly="readonly">
        </div>   
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">factuur items:</span>  
            <div class="form-control">           
            </div>
        </div>   
        <% int i = 1;
            for (FactuurItem fi : factuur.getDeFactuurItems()) {%> 
        <div class="input-group input-group-lg">        
            <span class="input-group-addon"><%= i%></span>  
            <div class="form-control"> 
                Factuur Item naam: <%= fi.getFactuurItemNaam()%>,
                Factuur Item hoeveelheid: <%= fi.getFactuurItemHoeveelheid()%>            
            </div>
        </div>          
        <% i++;
            }%>    
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">subtotaal</span>  
            <div class="form-control">€<%= factuur.getSubTotaalBedrag()%></div>
        </div>
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">btw </span>  
            <div class="form-control"><%= Math.round((factuur.getBtwBedrag() - 1) * 100)%>%</div>
        </div>         
        <div class="input-group input-group-lg">        
            <span class="input-group-addon">totaal</span>  
            <div class="form-control">€<%= factuur.getTotaalBedrag()%></div>
        </div>
        <form action="" method="get">
            <div class="input-group input-group-lg">
                <input type="hidden" name="id" value="<%= gekozenKlant.getId()%>">
                <input type="hidden" name="onderhoudId" value="<%= gekozenOnderhoud.getId()%>" > 
                <input type="hidden" name="saveInvoice" value="yes" >                
            </div>
            <br/> <input type="submit"  class="btn btn-default" value="Factuur opslaan" />
        </form>           
        <% }
            }%>
    </div>
    <jsp:include page="../theme/footer.jsp" />
