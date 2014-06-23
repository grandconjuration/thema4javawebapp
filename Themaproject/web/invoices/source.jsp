<%-- 
    Document   : source
    Created on : Jun 23, 2014, 11:27:59 AM
    Author     : Jelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link href="assets/invoices/Site.css" rel="stylesheet" type="text/css" />
    <link href="assets/invoices/Site.print.css" rel="stylesheet" type="text/css" />
    <link href="assets/invoices/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/invoices/bootstrap.print.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
    
    <div class="container-fluid">

<table class="invoiceHeader">
<tr>
<td>
 <img src="assets/invoices/images/logo_merenita.png" height="55" style="float:left;" />
</td>
<td style="text-align:right">
 <h2><asp:Label ID="lblInvoiceNumberTop" runat="server" /></h2> 
 <asp:Label ID="lblLongDateString" runat="server" />
</td>
</tr>
</table>

<div class="from">
    <blockquote>
    <p>    <strong>
    Merenita
    </strong>
    </p>

    <p>
    Moerasmos 15<br/> 3904 BV  Veenendaal<br/>the Netherlands
    </p>
    <p>
    Telefoon: +31 (0)6 52488458
    </p>

    <p>
    info@merenita.com
    </p>

    </blockquote>
</div>

 <table>
 <tr>
    <td width="65%">
            <h3>Klant :</h3>
            <blockquote>
            <p>
                <strong>
                    <asp:Label ID="lblCustomerName" runat="server" />
                </strong>
            </p>

            <p id="pCompanyNumber" runat="server">
                KVK nummer:
                <strong>
                    <asp:Label ID="lblCompanyNumber" runat="server" />
                </strong>
            </p>
             <p>
                <asp:Label ID="lblAddress" runat="server" />
            </p>

             <p>
                <asp:Label ID="lblPostCode" runat="server" />,
                <asp:Label ID="lblCity" runat="server" />
            </p>	      
            </blockquote>
    </td>
    <td>
        <h3>Dienst :</h3>
         <asp:Label ID="lblNotes" runat="server" />
    </td>
 </tr>
 </table>
   


 <asp:Label ID="lblProdPlaceholder" runat="server" />
 

<table>
    <tr>
        <td colspan="5" class="invoiceFooterTitle">SubTotaal:</td>
        <td width="200" class="invoiceFooterValue"><asp:Label ID="lblInvoiceNetTotal" runat="server" style="float: right" /></td>
    </tr>
    <tr>
        <td colspan="5" class="invoiceFooterTitle">BTW:</td>
        <td width="200" class="invoiceFooterValue"><asp:Label ID="lblInvoiceVATAmount" runat="server" style="float: right" /></td>
    </tr>
    <tr>
        <td colspan="5" class="invoiceFooterTitle">Totaal te betalen: </td>
        <td class="invoiceFooterValueTotal"><asp:Label ID="lblInvoiceTotalToPay" runat="server" style="float: right" /></td>
    </tr>
</table>

<div class="invoiceFooter">
    <p>
    Gelieve binnen 30 dagen na dagtekening te voldoen op onze bankrekening,<br />
    Betalings referentie: <asp:Label ID="lblInvoiceNumberBottom" runat="server" /><br />
    IBAN/SEPA Nummer: NL93RABO0166939455<br />
    BIC Nummer: RABONL2U<br />
    Ten name van: Merenita<br />
    Plaats: Veenendaal<br />
    Bank: Rabobank<br />
    KVK nummer: 53763238<br />
    BTW Nummer: NL206883766B02
    </p>
    

</div>

    </div>
</body>
</html>
