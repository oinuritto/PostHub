<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>

    <c:if test="${not empty message}">
        <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
    </c:if>
    <div class="text-center">
        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration</h3>
    </div>
    <form method="post">
        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">


                <div class="form-outline">
                    <input id="firstName-label" class="form-control form-control-md" type="text"
                           name="firstName" required/>
                    <label class="form-label" for="firstName-label">First Name</label>
                </div>

            </div>

        </div>

        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">

                <div class="form-outline">
                    <input id="lastName-label" class="form-control form-control-md" type="text"
                           name="lastName" required/>
                    <label class="form-label" for="lastName-label">Last Name</label>
                </div>

            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">

                <div class="form-outline">
                    <input id="username-label" class="form-control form-control-md" type="text"
                           name="username" required/>
                    <label class="form-label" for="username-label">Username</label>
                </div>

            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">

                <div class="form-outline">
                    <input id="password-label" class="form-control form-control-md" type="password"
                           name="password" required/>
                    <label class="form-label" for="password-label">Password</label>
                </div>

            </div>
        </div>

        <div class="row justify-content-center">
            <div class="form-check d-flex justify-content-center">

                <input id="policy-label" class="form-check-input" type="checkbox" name="policy"
                       value="agreed" required>
                <label class="form-check-label" for="policy-label">
                    I do accept the Policy Agreement
                </label>

            </div>
        </div>

        <div class="mt-4 pt-2 d-flex justify-content-center">
            <input class="btn btn-primary btn-md" type="submit" value="Submit"/>
        </div>

    </form>

<%@include file="/WEB-INF/jsp/_footer.jsp" %>
