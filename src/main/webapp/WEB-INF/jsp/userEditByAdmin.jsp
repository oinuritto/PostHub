<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- user's username and password editing for admin is not available--%>

<t:mainLayout title="Admin Panel">
    <div class="row justify-content-center align-items-center">
        <div class="col-12 col-lg-9 col-xl-7">
            <div class="card" style="border-radius: 15px;">
                <div class="card-body p-2 p-md-3">
                    <c:if test="${not empty message}">
                        <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
                    </c:if>
                    <div class="text-center">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">${editingUser.username} Profile Edit</h3>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">First Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <form method="post">
                                                <div class="input-group mb-3">
                                                    <input id="firstName" class="form-control form-control-sm mr-sm-2"
                                                           type="text"
                                                           name="firstName" placeholder="New first name..." required/>
                                                    <input class="btn btn-primary btn-sm" type="submit" value="Edit"/>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Last Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <form method="post">
                                                <div class="input-group mb-3">
                                                    <input id="lastName" class="form-control form-control-sm mr-sm-2"
                                                           type="text"
                                                           name="lastName" placeholder="New last name..." required/>
                                                    <input class="btn btn-primary btn-sm" type="submit" value="Edit"/>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="mt-4 pt-2">
                                <a href="${pageContext.request.contextPath}/adminPanel">
                                    <button class="btn btn-primary btn-lg">Back</button>
                                </a>
                            </div>
                        </div>

                        <div class="col-md-4 mb-3">
                            <div class="mt-4 pt-2">
                                <button class="btn btn-primary btn-lg" data-bs-toggle="modal"
                                        data-bs-target="#deleteUserAccountModal">Delete account
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <!-- Modal delete account -->
    <div class="modal fade" id="deleteUserAccountModal" tabindex="-1" aria-labelledby="deleteUserAccountModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteUserAccountModalLabel">Account deleting</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure want to delete this user's account?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                    <form action="${pageContext.request.contextPath}/adminPanel/deleteUser" method="post">
                        <input type="hidden" name="isDeleteAccepted" value="true" />
                        <input type="hidden" name="deletingUserUsername" value="${editingUser.username}" />
                        <input type="submit" class="btn btn-primary" value="Yes"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:mainLayout>