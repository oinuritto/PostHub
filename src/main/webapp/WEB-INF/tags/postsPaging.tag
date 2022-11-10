<%@tag description="Posts paging Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="page" %>
<%@attribute name="pagesCount" %>

<c:if test="${not empty page && pagesCount != 0}">
    <%-- pages choose   --%>
    <nav aria-label="..." class="d-flex justify-content-center align-self-end">
        <ul class="pagination">
            <li class="page-item <c:if test="${page == 1}">disabled</c:if>">
                <a class="page-link" href="<c:url value="/?page=${page - 1}"/>" tabindex="-1">
                    Previous</a>
            </li>
            <c:if test="${page > 2}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value="/?page=1"/>">1</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="<c:url value="/?page=2"/>">2</a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">
                        ...</a>
                </li>
            </c:if>
            <li class="page-item active">
                <a class="page-link" href="<c:url value="/?page=${page}"/>">${page}</a>
            </li>

            <c:if test="${pagesCount >= page + 1}">
                <li class="page-item" aria-current="page">
                    <a class="page-link" href="<c:url value="/?page=${page+1}"/>">${page+1}</a>
                </li>
                <c:if test="${pagesCount >= page + 2}">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/?page=${page+2}"/>">${page+2}</a>
                    </li>
                </c:if>
                <li class="page-item">
                    <a class="page-link" href="<c:url value="/?page=${page+1}"/>">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</c:if>