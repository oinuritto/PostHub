<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:mainLayout title="User posts" jsFiles="likesScript.js">
  <div class="text-center">
    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Posts of ${username}</h3>
  </div>

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
    <br>
  </c:forEach>


  <%-- run js function there, because need to give arguments from jsp --%>
  <c:if test="${not empty user && not empty idOfPosts}">
    <script>window.onload = function () {
      updateIcons(${idOfPosts}, ${user.id});
    }
    </script>
  </c:if>

</t:mainLayout>