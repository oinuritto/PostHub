<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post Hub</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
            crossorigin="anonymous"></script>
</head>
<body class="bg-light">
<nav class="navbar sticky-top navbar-expand-md navbar-light p-3" style="background-color: #f1f1f1;">
    <div class="container-fluid">
        <a class="navbar-brand" href="#home" id="home">PostHub</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <form class="d-flex">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

            <ul class="navbar-nav ms-auto ">
                <li class="nav-item">
                    <a class="nav-link mx-2 active" aria-current="page" href="#home">Home</a>
                </li>

                <%-- Если авторизован               --%>
                <c:if test="${not empty user}">
                    <li class="nav-item dropdown">
                        <a class="nav-link mx-2 dropdown-toggle" href="#" id="navbarDropdownMenuLinkAuthorized"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-people-fill"></i>
                            Account
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkAuthorized">
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li><a class="dropdown-item" href="#">New post</a></li>
                            <li><a class="dropdown-item" href="#">Log out</a></li>
                        </ul>
                    </li>
                </c:if>
                <%-- Если авторизован               --%>

                <%-- Если не авторизован               --%>
                <c:if test="${empty user}">
                    <li class="nav-item dropdown">
                        <a class="nav-link mx-2 dropdown-toggle" href="#" id="navbarDropdownMenuLinkNotAuthorized"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-people-fill"></i>
                            Account
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkNotAuthorized">
                            <li><a class="dropdown-item" href="#">Log in</a></li>
                            <li><a class="dropdown-item" href="#">Registration</a></li>
                        </ul>
                    </li>
                </c:if>
                <%-- Если не авторизован               --%>
            </ul>
        </div>
    </div>
</nav>
