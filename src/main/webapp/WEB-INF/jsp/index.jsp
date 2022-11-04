<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>

<c:forEach var="post" items="${posts}">

    <%--    one card--%>
    <div class="card m-auto" style="width: 30rem;">
        <c:if test="${not empty post.imgId}">
            <img src="${pageContext.request.contextPath}/images?id=${post.imgId}"
                 class="card-img-top" alt="${post.title}">
        </c:if>
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
<%--            <p class="card-text">${post.text}</p>--%>
            <a href="${pageContext.request.contextPath}/post?id=${post.id}" class="btn btn-primary">See more</a>
        </div>
    </div>

    <br>
</c:forEach>

<nav aria-label="..." class="d-flex justify-content-center align-self-end">
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item" aria-current="page">
            <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>

<%@include file="/WEB-INF/jsp/_footer.jsp" %>
