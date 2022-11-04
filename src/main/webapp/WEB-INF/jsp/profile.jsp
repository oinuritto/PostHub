<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>

    <div class="row justify-content-center align-items-center">
        <div class="col-12 col-lg-9 col-xl-7">
            <div class="card" style="border-radius: 15px;">
                <div class="card-body p-2 p-md-3">
                    <div class="text-center">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Your profile</h3>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Username</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${user.username}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">First Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${user.firstName}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Last Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${user.lastName}</p>
                                        </div>
                                    </div>
                                    <%--                    <div class="row">--%>
                                    <%--                        <div class="col-sm-3">--%>
                                    <%--                            <p class="mb-0">Gender</p>--%>
                                    <%--                        </div>--%>
                                    <%--                        <div class="col-sm-9">--%>
                                    <%--                            <p class="text-muted mb-0">${user.gender}</p>--%>
                                    <%--                        </div>--%>
                                    <%--                    </div>--%>
                                    <%--                    <hr>--%>
                                    <%--                    <div class="row">--%>
                                    <%--                        <div class="col-sm-3">--%>
                                    <%--                            <p class="mb-0">Date of birth</p>--%>
                                    <%--                        </div>--%>
                                    <%--                        <div class="col-sm-9">--%>
                                    <%--                            <p class="text-muted mb-0">${user.birthDate}</p>--%>
                                    <%--                        </div>--%>
                                    <%--                    </div>--%>
                                    <%--                    <hr>--%>
                                    <%--                    <div class="row">--%>
                                    <%--                        <div class="col-sm-3">--%>
                                    <%--                            <p class="mb-0">Country</p>--%>
                                    <%--                        </div>--%>
                                    <%--                        <div class="col-sm-9">--%>
                                    <%--                            <p class="text-muted mb-0">${user.country}</p>--%>
                                    <%--                        </div>--%>
                                    <%--                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="mt-4 pt-2">
                                <a href="${pageContext.request.contextPath}/logout">
                                    <button class="btn btn-primary btn-lg">Log out</button>
                                </a>
                            </div>
                        </div>

                        <div class="col-md-4 mb-3">
                            <div class="mt-4 pt-2">
                                <a href="${pageContext.request.contextPath}/profile/editProfile">
                                    <button class="btn btn-primary btn-lg">Edit profile</button>
                                </a>
                            </div>
                        </div>

                        <c:if test="${user.role == \"admin\"}">
                            <div class="col-md-4 mb-3">
                                <div class="mt-4 pt-2">
                                    <a href="${pageContext.request.contextPath}/adminPanel">
                                        <button class="btn btn-primary btn-lg">Admin Panel</button>
                                    </a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="/WEB-INF/jsp/_footer.jsp" %>

