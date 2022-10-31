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
                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Auth Form</h3>
                <form action="<c:url value="/auth"/>" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-4">

                            <div class="form-outline">
                                <input id="username-label" class="form-control form-control-md" type="text"
                                       name="username"/>
                                <label class="form-label" for="username-label">Username</label>
                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-4">

                            <div class="form-outline">
                                <input id="password-label" class="form-control form-control-md" type="password"
                                       name="password"/>
                                <label class="form-label" for="password-label">Password</label>
                            </div>

                        </div>
                    </div>

                    <div class="mt-4 pt-2">
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
                <h5 class="modal-title" id="registerModalLabel">Log-in form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

            </div>
        </div>
    </div>
</div>
<!-- Modal registration-->
