<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Auth">
    <c:if test="${not empty message}">
        <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
    </c:if>
    <div class="text-center">
        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Auth</h3>
    </div>
    <form action="<c:url value="/auth"/>" method="post">
        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">

                <div class="form-outline">
                    <input id="username-label-auth" class="form-control form-control-md" type="text"
                           name="username" required/>
                    <label class="form-label" for="username-label-auth">Username</label>
                </div>

            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-6 mb-3">

                <div class="form-outline">
                    <input id="password-label-auth" class="form-control form-control-md" type="password"
                           name="password" required/>
                    <label class="form-label" for="password-label-auth">Password</label>
                </div>

            </div>
        </div>

        <div class="mt-3 pt-2 d-flex justify-content-center">
            <input class="btn btn-primary btn-md" type="submit" value="Submit"/>
        </div>

    </form>
</t:mainLayout>
