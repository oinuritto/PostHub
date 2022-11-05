<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Admin Panel" jsFiles="usersSearch.js">
    <div class="row justify-content-center align-items-center">
        <div class="col-12 col-lg-9 col-xl-7">
            <div class="card" style="border-radius: 15px;">
                <div class="card-body p-2 p-md-3">
                    <c:if test="${not empty message}">
                        <h4 class="mb-4 pb-2 pb-md-0 mb-md-5">${message}</h4>
                    </c:if>
                    <div class="text-center">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Admin Panel</h3>
                    </div>

                    <input type="text" id="usersSearch" class="form-control form-control-md mb-2"
                           placeholder="Search user" onkeyup="searchUsers(document.getElementById('usersSearch').value)">
                    <table class="table table-hover">
                        <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody id="usersTbody">
                        <c:forEach items="${usersList}" var="usr">
                            <tr>
                                <td>${usr.id}</td>
                                <td>${usr.username}</td>
                                <td>${usr.firstName}</td>
                                <td>${usr.lastName}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/adminPanel/userEdit?username=${usr.username}">
                                        <button class="btn btn-primary btn-sm">Edit
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>


                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="mt-4 pt-2">
                                <a href="${pageContext.request.contextPath}/profile">
                                    <button class="btn btn-primary btn-lg">Profile</button>
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</t:mainLayout>
