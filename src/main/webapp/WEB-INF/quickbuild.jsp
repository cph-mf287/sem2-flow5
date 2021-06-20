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
                <h2>Quick-byg tilbud - carport med fladt tag</h2>

                <p> Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.
                <p> Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                    Ved bestilling medfølger standardbyggevejledning.
                <p> Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"
                    Felter markeret * SKAL udfyldes!

                <form method="post">
                    <strong>Ønskede carport mål:</strong><br>
                    <div class="form-group">
                        <label for="carportWidth" title="Carport bredde">Carport bredde</label>
                        <select id="carportWidth" title="Carport bredde" name="carportWidth" class="form-control">
                            <option value selected>Vælg bredde</option>
                            <c:forEach var="width" begin="240" end="750" step="30">
                                <option value="${width}">${width} cm</option>
                            </c:forEach>>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="carportLength" title="Carport længde">Carport længde</label>
                        <select id="carportLength" title="Carport længde" name="carportLength" class="form-control">
                            <option value selected>Vælg længde</option>
                            <c:forEach var="length" begin="240" end="780" step="30">
                                <option value="${length}">${length} cm</option>
                            </c:forEach>>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="carportRoof" title="Tag">Tag</label>
                        <select id="carportRoof" title="Tag" name="carportRoof" class="form-control">
                            <option value="1" selected>Plasttrapezplader</option>
                        </select>
                    </div>
                    <strong>Redskabsrum:</strong><br>
                    NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*
                    <div class="form-group">
                        <label for="shedWidth" title="Redskabsrum bredde">Carport bredde</label>
                        <select id="shedWidth" title="Redskabsrum bredde" name="shedWidth" class="form-control">
                            <option value selected>Ønsker ikke redskabsrum</option>
                            <c:forEach var="width" begin="210" end="720" step="30">
                                <option value="${width}">${width} cm</option>
                            </c:forEach>>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="shedLength" title="Redskabsrum længde">Carport længde</label>
                        <select id="shedLength" title="Redskabsrum længde" name="shedLength" class="form-control">
                            <option value selected>Ønsker ikke redskabsrum</option>
                            <c:forEach var="length" begin="150" end="690" step="30">
                                <option value="${length}">${length} cm</option>
                            </c:forEach>>
                        </select>
                    </div>
                    <p> * Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330 cm.
                    <div class="form-group">
                        <input name="draw" value="Se tegning" type="submit" class="btn btn-secondary">
                    </div>
                </form>
            </div>

        </div>

    </jsp:body>
</t:genericpage>