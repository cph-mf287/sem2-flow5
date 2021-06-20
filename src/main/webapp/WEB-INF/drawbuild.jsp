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

            <div class="col-md-3">

            </div>
            <div class="col-md-9">
                ${requestScope.svg}
                <hr>
                <form method="post">
                    <input type="hidden" name="carportWidth" value="${requestScope.carportWidth}">
                    <input type="hidden" name="carportLength" value="${requestScope.carportLength}">
                    <input type="hidden" name="carportRoof" value="${requestScope.carportRoof}">
                    <input type="hidden" name="shedWidth" value="${requestScope.shedWidth}">
                    <input type="hidden" name="shedLength" value="${requestScope.shedLength}">
                    <div class="form-group">
                        <label for="name">Navn</label>
                        <input id="name" name="name" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="address">Adresse</label>
                        <input id="address" name="address" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="city">Postnummer og by</label>
                        <input id="city" name="city" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="phone">Telefon</label>
                        <input id="phone" name="phone" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="email">E-mail adresse</label>
                        <input id="email" name="email" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="remarks">Evt. bemærkninger</label>
                        <textarea id="remarks" name="remarks" class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <input name="submit" value="Send forspørgsel" type="submit" class="btn btn-secondary">
                    </div>
                </form>
            </div>

        </div>

    </jsp:body>
</t:genericpage>