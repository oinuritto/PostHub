<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:mainLayout title="${post.title}" jsFiles="likesScript.js">
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
    <br>

    <c:if test="${not empty user}">
        <script>window.onload = function () {
            updateIcons(${[post.id]}, ${user.id});
        }
        </script>
    </c:if>

</t:mainLayout>
