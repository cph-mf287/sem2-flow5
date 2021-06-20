<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div class="row">

            <div class="col-md-12">
                ${requestScope.list}
                <hr>
                ${requestScope.svg}
                <hr>
            </div>

        </div>
        <div class="row">

            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form method="post">
                    <label for="price">Pris</label>
                    <input name="orderId" type="hidden" value="${requestScope.orderId}">
                    <input id="price" name="price" type="text" value="${requestScope.price}">
                    <button name="approved" data-inline="true" data-rel="back">
                        Godkend Pris
                    </button>
                </form>
            </div>
            <div class="col-md-4"></div>

        </div>

        <script>
            ${requestScope.onload}
        </script>

    </jsp:body>
</t:genericpage>