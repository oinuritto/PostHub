<nav class="navbar sticky-top navbar-expand-md navbar-light p-3" style="background-color: #f1f1f1;">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value="/"/>">PostHub</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <%--            DONE: сделать поиск --%>
      <form class="d-flex" method="get" action="${pageContext.request.contextPath}">
        <input id="search-input" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="query">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>

      <ul class="navbar-nav ms-auto ">
        <li class="nav-item">
          <a class="nav-link mx-2 active" aria-current="page" href="<c:url value="/"/>">Home</a>
        </li>

        <%-- Если авторизован               --%>
        <c:if test="${not empty user}">
          <li class="nav-item dropdown">
            <a class="nav-link mx-2 dropdown-toggle" href="#" id="navbarDropdownMenuLinkAuthorized"
               role="button"
               data-bs-toggle="dropdown" aria-expanded="false">
              <i class="bi bi-people-fill"></i>
                ${user.username}
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkAuthorized">
              <li><a class="dropdown-item" href="<c:url value="/profile"/>">Profile</a></li>
              <li><a class="dropdown-item" href="<c:url value="/newpost"/>">New post</a></li>
              <li><a class="dropdown-item" href="<c:url value="/logout"/>">Log out</a></li>
            </ul>
          </li>
        </c:if>
        <%-- Если авторизован               --%>

        <%-- Если не авторизован               --%>
        <c:if test="${empty user}">
          <li class="nav-item dropdown">
            <a class="nav-link mx-2 dropdown-toggle" href="#" id="navbarDropdownMenuLinkNotAuthorized"
               role="button"
               data-bs-toggle="dropdown" aria-expanded="false">
              <i class="bi bi-people-fill"></i>
              Account
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkNotAuthorized">
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#authModal">Log in</a></li>
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#registerModal">Registration</a></li>
            </ul>
          </li>
        </c:if>
        <%-- Если не авторизован               --%>
      </ul>
    </div>
  </div>
</nav>
