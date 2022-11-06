<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="${post.title}">
    <%--    one card--%>
    <div class="card m-auto">
        <c:if test="${not empty post.imgId && post.imgId != 0}">
            <img src="${pageContext.request.contextPath}/images?id=${post.imgId}"
                 class="card-img-top" alt="${post.title}">
        </c:if>
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
            <p class="card-text">${post.text}</p>
            <a href="${pageContext.request.contextPath}" class="btn btn-primary">Back</a>
        </div>
    </div>

    <br>
</t:mainLayout>
