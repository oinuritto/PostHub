<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>

<%--    one card--%>
<div class="card m-auto">
    <c:if test="${not empty post.imgId}">
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

<%@include file="/WEB-INF/jsp/_footer.jsp" %>
