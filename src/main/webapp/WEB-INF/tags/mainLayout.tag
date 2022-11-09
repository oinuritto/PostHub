<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" %>
<%@attribute name="jsFiles" %>

<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta charset="UTF-8">
    <%--    icon    --%>
    <link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/img/icon/apple-touch-icon.png"/>">
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/img/icon/favicon-32x32.png"/>">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/img/icon/favicon-16x16.png"/>">
    <link rel="manifest" href="/img/icon/site.webmanifest">

    <%--    css     --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

    <%--    js     --%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
            crossorigin="anonymous"></script>
    <c:forTokens items="${jsFiles}" delims=", " var="file">
        <script defer src="<c:url value="/js/${file}"/>"></script>
    </c:forTokens>
</head>
<body class="bg-light">
<%@include file="/WEB-INF/jsp/parts/_navBar.jsp" %>

<%@include file="/WEB-INF/jsp/parts/_modalAuthAndRegister.jsp" %>
<br>
<div class="container" style="min-height: 71.5vh;">
    <jsp:doBody/>
</div>
<%@include file="/WEB-INF/jsp/parts/_footer.jsp" %>
</body>
</html>