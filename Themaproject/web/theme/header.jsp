<%-- 
    Document   : Theme Header
    Created on : 10-mei-2014, 12:48:53
    Author     : Simon Whiteley
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>AutoTotaalDiensten</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <style>
            body {
                padding-top: 50px;
                padding-bottom: 20px;
            }
        </style>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="bootstrap/css/main.css">

        <script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="./">AutoTotaalDiensten</a>
                </div>
                <div class="navbar-collapse collapse">
                    <% if (session.getAttribute("userID") != null) { %>
                    <p class="navbar-text">Ingelogd als <% out.println(session.getAttribute("userName")); %></p>
                    <div><a class='btn btn-danger navbar-btn navbar-right' href='accountslogout'>Uitloggen</a></div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary navbar-btn dropdown-toggle" data-toggle="dropdown">Opties<span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href='carsaddcar'>Auto Toevoegen</a></li>
                            <li><a href='invoicesindex'>Facturen</a></li>
                            <li><a href='customersedit'>Gegevens</a></li>
                        </ul>
                    </div>
                    <% } else { %>
                    <% Object message = request.getAttribute("msgs");
				    if (message != null) {
					   out.println("<div class=\"alert alert-success\">" + message + "</div>");
				    }%>
                    <form action="accountslogin" method="post" class="navbar-form navbar-right" role="form">
                        <div class="form-group">
                            <input type="text" name="username" placeholder="Gebruikersnaam" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="Wachtwoord" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Inloggen</button>
                        <a href="accountsregister" class="btn btn-success">Registreren</a>
                    </form>
                    <% }%>
                </div><!--/.navbar-collapse -->
            </div>
        </div>