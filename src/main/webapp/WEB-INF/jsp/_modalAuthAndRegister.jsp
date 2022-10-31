<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal auth-->
<div class="modal fade" id="authModal" tabindex="-1" aria-labelledby="authModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Log-in form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <c:if test="${not empty message}">
                    <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
                </c:if>
                <form action="<c:url value="/auth"/>" method="post">
                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="username-label-auth" class="form-control form-control-md" type="text"
                                       name="username"/>
                                <label class="form-label" for="username-label-auth">Username</label>
                            </div>

                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="password-label-auth" class="form-control form-control-md" type="password"
                                       name="password"/>
                                <label class="form-label" for="password-label-auth">Password</label>
                            </div>

                        </div>
                    </div>

                    <div class="mt-3 pt-2 d-flex justify-content-center">
                        <input class="btn btn-primary btn-md" type="submit" value="Submit"/>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal auth-->

<!-- Modal registration-->
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Registration form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <c:if test="${not empty message}">
                    <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
                </c:if>
                <form action="<c:url value="/register"/>" method="post">
                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="firstName-label" class="form-control form-control-md" type="text"
                                       name="firstName"/>
                                <label class="form-label" for="firstName-label">First Name</label>
                            </div>

                        </div>

                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="lastName-label" class="form-control form-control-md" type="text"
                                       name="lastName"/>
                                <label class="form-label" for="lastName-label">Last Name</label>
                            </div>

                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="username-label" class="form-control form-control-md" type="text"
                                       name="username"/>
                                <label class="form-label" for="username-label">Username</label>
                            </div>

                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6 mb-3">

                            <div class="form-outline">
                                <input id="password-label" class="form-control form-control-md" type="password"
                                       name="password"/>
                                <label class="form-label" for="password-label">Password</label>
                            </div>

                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="form-check d-flex justify-content-center">

                            <input id="policy-label" class="form-check-input" type="checkbox" name="policy"
                                   value="agreed">
                            <label class="form-check-label" for="policy-label">
                                I do accept the Policy Agreement
                            </label>

                        </div>
                    </div>

                    <div class="mt-4 pt-2 d-flex justify-content-center">
                        <input class="btn btn-primary btn-md" type="submit" value="Submit"/>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal registration-->
