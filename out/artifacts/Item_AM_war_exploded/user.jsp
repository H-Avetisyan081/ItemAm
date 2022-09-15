<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<% User user = (User) session.getAttribute("user");

    List<Item> items = (List<Item>) request.getAttribute("items");
%>


<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #0470aa;
            color: white;
        }

        .topnav .icon {
            display: none;
        }

        @media screen and (max-width: 600px) {
            .topnav a:not(:first-child) {
                display: none;
            }

            .topnav a.icon {
                float: right;
                display: block;
            }
        }

        @media screen and (max-width: 600px) {

            .topnav.responsive .icon {
                position: absolute;
                right: 0;
                top: 0;
            }

            .topnav.responsive a {
                float: none;
                display: block;
                text-align: left;
            }
        }
    </style>
</head>
<style>
    .card {
        float: left;
        position: relative;
        left: 50px;
        align-content: space-between;
        box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.2);
        max-width: 250px;
        margin: auto;
        text-align: center;
        font-family: Calibri;
    }

    .price {
        color: #05050c;
        font-size: 20px;
    }


</style>

<div class="topnav" id="myTopnav">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            width: 40%;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .container {
            padding: 2px 16px;
        }
    </style>

    <a href="/" class="active">ԳԼԽԱՎՈՐ</a>


    <a href="/addItem.jsp" style="color: #4cbffd">ԱՎԵԼԱՑՆԵԼ ՀԱՅՏԱՐԱՐՈՒԹՅՈՒՆ
        <i class="fa fa-check-circle"></i></a>
    <a href="/logout" style="right: 150px;float: right"> Դուրս Գալ</a>

    <% if (user == null) {%>
    <a href="/" style="float:right"><i class="fa fa-fw fa-user"></i> </a>
    <%} else {%>
    <a href="/userHome" style="float:right">Օգտատեր: <%=user.getName()%>._<%=user.getSurname()%><i
            class="fa fa-fw fa-user"></i> </a>

    <%}%>
    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i></a>

</div>

<div style="padding-left:16px">
    <h5>
        <div style="color: dodgerblue;font-feature-settings: initial;font-family:'Gadugi';font-size: 45px;float: top;
">
            Item.am
        </div>
    </h5>
    <h4> Դուք ունեք <%=items.size() %> հայտարարություն</h4>

</div>
<div>
    <%
        if (items != null) {
            for (Item item : items) {
    %>
    <a href="/singleItem?id=<%=item.getId()%>">
        <div class="card">
            <% if (item.getPicUrl() != null && !item.getPicUrl().equals("")) {%>
            <img src="/image?path=<%=item.getPicUrl()%>" width="180/" height="120/">
            <%} else {%>
            <img src="${pageContext.request.contextPath}/image/img.jpg" width="180/" height="120/">
            <%}%>
            <h3><%=item.getTitle()%></h3>
            <p class="price"> Գին  <%=item.getPrice()%></p> </a>
<a href="/delete?id=<%=item.getId()%>">ՀԵՌԱՑՆԵԼ</a>
        </div>


    <%
            }
        }
    %>
</div>

<script>
    function myFunction() {
        var x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }
</script>
</body>
</html>


