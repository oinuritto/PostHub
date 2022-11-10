<%@tag description="Sorting choose Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="isSortedVariable" %>
<%@attribute name="actionUrl" %>

<%-- sorting choose dropdown menu --%>
<div class="dropdown mb-2 d-flex justify-content-center">
    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
            data-bs-toggle="dropdown" aria-expanded="false">
        Сортировка
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li>
            <form <c:if test="${not empty actionUrl}">action="<c:url value="${actionUrl}"/>"</c:if>
                  method="post">
                <input type="hidden" name="isSorted" value="false">
                <input type="submit" class="dropdown-item" value="По умолчанию"
                       <c:if test="${empty isSortedVariable || !isSortedVariable}">disabled</c:if> />
            </form>
        </li>
        <li>
            <form <c:if test="${not empty actionUrl}">action="<c:url value="${actionUrl}"/>"</c:if>
                  method="post">
                <input type="hidden" name="isSorted" value="true">
                <input type="submit" class="dropdown-item" value="По популярности"
                       <c:if test="${not empty isSortedVariable && isSortedVariable}">disabled</c:if> />
            </form>
        </li>
    </ul>
</div>