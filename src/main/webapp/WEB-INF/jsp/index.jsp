<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:mainLayout title="Post Hub" jsFiles="likesScript.js">
    <c:forEach var="post" items="${posts}">

        <%--    one card--%>
        <div class="card m-auto" style="width: 30rem;">
            <c:if test="${not empty post.imgId && post.imgId != 0}">
                <img src="${pageContext.request.contextPath}/images?id=${post.imgId}"
                     class="card-img-top" alt="${post.title}">
            </c:if>
            <div class="card-body">
                <h5 class="card-title">${post.title}</h5>
                <a href="${pageContext.request.contextPath}/post?id=${post.id}" class="btn btn-primary">See more</a>
                <p
                        id="liked-post-${post.id}"
                        class="btn btn-link btn-floating btn-lg"
                        role="button"
                        data-mdb-ripple-color="dark" onclick="workWithLike(${post.id})">
                    <i class="bi bi-hand-thumbs-up" id="likedCount-post-${post.id}">${fn:length(post.likes)}</i>
                    <input type="hidden" value="nonLiked" id="ifLiked-post-${post.id}">
                </p>
            </div>
        </div>
        <%--        <c:if test="${not empty user}">--%>
        <%--            <input type="hidden" onload="updateIcon(${post.id}, ${user.id})">--%>
        <%--        </c:if>--%>
        <br>
    </c:forEach>

    <c:if test="${not empty user && not empty idOfPosts}">
        <script>window.onload = function () {
            updateIcons(${idOfPosts}, ${user.id});
        }
        </script>
    </c:if>

    <c:if test="${not empty page}">
        <%-- pages choose   --%>
        <nav aria-label="..." class="d-flex justify-content-center align-self-end">
            <ul class="pagination">
                <li class="page-item <c:if test="${page == 1}">disabled</c:if>">
                    <a class="page-link" href="<c:url value="/?page=${page - 1}"/>" tabindex="-1">
                        Previous</a>
                </li>
                <c:if test="${page != 1}">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/?page=1"/>">1</a>
                    </li>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">
                            ...</a>
                    </li>
                </c:if>
                <li class="page-item active">
                    <a class="page-link" href="<c:url value="/?page=${page}"/>">${page}</a>
                </li>
                <c:if test="${not empty idOfPosts}">
                    <li class="page-item" aria-current="page">
                        <a class="page-link" href="<c:url value="/?page=${page+1}"/>">${page+1}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="<c:url value="/?page=${page+2}"/>">${page+2}</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/?page=${page+1}"/>">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</t:mainLayout>
