<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:mainLayout title="${post.title}" jsFiles="likesScript.js">
    <%--    one card--%>
    <c:if test="${not empty message}">
        <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
    </c:if>
    <div class="card m-auto">
        <c:if test="${not empty post.imgId && post.imgId != 0}">
            <img src="${pageContext.request.contextPath}/images?id=${post.imgId}"
                 class="card-img-top" alt="${post.title}">
        </c:if>
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
            <h6 class="card-subtitle mb-2 text-muted">Author: ${authorUsername}</h6>
            <p class="card-text">${post.text}</p>
            <a href="${pageContext.request.contextPath}" class="btn btn-primary">Back</a>

            <c:if test="${post.userId == user.id || user.role == 'admin'}">
                <button class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#deletePostModal">Delete
                </button>
            </c:if>

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

    <%-- run js fuction there, because need to give arguments from jsp --%>
    <c:if test="${not empty user}">
        <script>window.onload = function () {
            updateIcons(${[post.id]}, ${user.id});
        }
        </script>
    </c:if>

    <!-- Modal delete post -->
    <div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="deletePostModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deletePostModalLabel">Post deleting</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure want to delete this post?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                    <form action="${pageContext.request.contextPath}/post/delete" method="get">
                        <input type="hidden" name="id" value="${post.id}" />
                        <input type="submit" class="btn btn-primary" value="Yes"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

</t:mainLayout>
