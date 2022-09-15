<%@ page import="model.Category" %>
<%@ page import="manager.CategoryManager" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Add Item</title>
</head>

<body>
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
            .topnav.responsive {
                position: relative;
            }

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
<body>
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

    <a href="/" class="active" style="float: right">ԳԼԽԱՎՈՐ ՄԵՆՅՈՒ</a>

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

    <h6>

    </h6>


</div>

<h4 style="color: darkblue"> ԱՎԵԼԱՑՆԵԼ ՀԱՅՏԱՐԱՐՈՒԹՅՈՒՆ </h4>
<% List<Category> categories = new CategoryManager().getAllCategories();
%>

<div
        style="text-align: center">

    <form action="/addItem" method="post" enctype="multipart/form-data">
        <label for="title">ԱՆՎԱՆՈՒՄ:</label><br>
        <input type="text" name="title" id="title" required><br>
        <label for="price">ԳԻՆ:</label><br>
        <input type="text" name="price" id="price"><br>
        <label for="description" name="description">ՆԿԱՐԱԳՐՈՒԹՅՈՒՆ:</label><br>
        <textarea name="description" id="description"></textarea><br>
        <label for="category"> ԿԱՏԵԳՈՐԻԱ</label><br>
        <select name="category" id="category"> <br>
            <% for (Category category : categories) {%>
            <option value="<%=category.getId()%>"><%=category.getName()%>
            </option>
            <% }%>
        </select> <br>
        <input type="file" name="image/jpeg"><br>

        <input type="submit" value="ԱՎԵԼԱՑՆԵԼ">
    </form>
</div>
</head>
</body>
</body>
</html>
