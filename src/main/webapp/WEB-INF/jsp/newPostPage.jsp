<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>

    <div class="card">
        <c:if test="${not empty message}">
            <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
        </c:if>
        <form method="post" enctype="multipart/form-data" action="<c:url value="/upload"/>">
            <div class="row justify-content-center">
                <div class="col-md-6 mb-3 mt-3">

                    <div class="form-outline">
                        <input type="file" class="form-control form-control-md" id="inputFile"
                               aria-label="Upload" accept="image/*" name="file">
                        <label class="form-label" for="inputFile">Image</label>
                    </div>

                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-6 mb-3">

                    <div class="form-outline">
                        <input type="text" class="form-control form-control-md" id="postTitle" name="postTitle"
                               placeholder="Write the title..." required>
                        <label class="form-label" for="postTitle">Title</label>
                    </div>

                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-6 mb-3">

                    <div class="form-outline">
                        <textarea type="text" class="form-control form-control-md" id="postText" name="postText"
                                  placeholder="Write the text..." required></textarea>
                        <label class="form-label" for="postTitle">Text</label>
                    </div>

                </div>
            </div>

            <div class="mt-2 pt-2 d-flex justify-content-center">
                <input class="btn btn-primary btn-md" type="submit" value="Create"/>
            </div>
        </form>
    </div>

<%@include file="/WEB-INF/jsp/_footer.jsp" %>