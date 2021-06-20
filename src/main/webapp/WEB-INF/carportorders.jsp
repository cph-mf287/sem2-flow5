<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div class="row">

            <div class="col-md-3">

            </div>
            <div class="col-md-9">
                <form>
                    <table>
                        <thead>
                        <tr>
                            <th>Produkt</th><th>Pris</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${requestScope.orders}">
                        <tr>
                            <td>Carport ${order.carport.width}x${order.carport.length} cm</td>
                            <td style="text-align: right"><f:formatNumber type="number" minIntegerDigits="1" minFractionDigits="2" maxFractionDigits="2" value="${order.price / 100}"/> kr.</td>
                            <td><button name="view" value="${order.id}" class="btn btn-secondary">Tilgå</button></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>

        </div>

    </jsp:body>
</t:genericpage>