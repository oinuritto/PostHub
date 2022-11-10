<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Users">
  <t:sortingChoose isSortedVariable="${isSorted}"/>

  <div class="row justify-content-center align-items-center">
    <div class="col-12 col-lg-9 col-xl-7">
      <div class="card" style="border-radius: 15px;">
        <div class="card-body p-2 p-md-3">
          <div class="text-center">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Users</h3>
          </div>

          <table class="table table-hover">
            <thead class="table-primary">
            <tr>
              <th>Username</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Rating</th>
              <th>Action</th>
            </tr>
            </thead>
            <tbody id="usersTbody">
            <c:forEach items="${usersList}" var="usr">
              <tr>
                <td>${usr.username}</td>
                <td>${usr.firstName}</td>
                <td>${usr.lastName}</td>
                <td>${usr.rating}</td>
                <td>
                  <a href="<c:url value="/users/posts?id=${usr.id}"/>">
                    <button class="btn btn-primary btn-sm">View Posts
                    </button>
                  </a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>


        </div>
      </div>
    </div>
  </div>
</t:mainLayout>