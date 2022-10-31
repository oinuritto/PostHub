<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_header.jsp" %>
<div class="container" style="min-height: 71.5vh;">
    <%@include file="/WEB-INF/jsp/_modalAuthAndRegister.jsp"%>

    <%--    one card--%>
    <div class="card">
        <c:if test="${not empty post.img}">
            <img src="..." class="card-img-top" alt="...">
        </c:if>
        <div class="card-body">
            <h5 class="card-title">Заголовок карточки</h5>
            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже в качестве естественного
                перехода к дополнительному контенту. Этот контент немного длиннее.</p>
            <a href="#" class="btn btn-primary">See more</a>
        </div>
    </div>
    <br>
    <%--    one card--%>


    <%--    <div class="card d-flex justify-content-center">--%>
    <%--        <div class="card-body d-flex justify-content-center">--%>
    <nav aria-label="..." class="d-flex justify-content-center align-self-end">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item" aria-current="page">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
    <%--        </div>--%>
    <%--    </div>--%>
</div>
<%@include file="/WEB-INF/jsp/_footer.jsp" %>
