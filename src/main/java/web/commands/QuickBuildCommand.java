package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.exceptions.ValueNotAssignedException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuickBuildCommand extends CommandUnprotectedPage
{
    private String pageToShow;
    private OrderFacade orderFacade;

    public QuickBuildCommand(String pageToShow) {
        super(pageToShow);
        this.pageToShow = pageToShow;
        orderFacade = new OrderFacade(database);
    }

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws UserException, ValueNotAssignedException {
        if (request.getParameter("draw") != null || request.getParameter("submit") != null) {
            String carportWidthString = request.getParameter("carportWidth");
            String carportLengthString = request.getParameter("carportLength");
            String carportRoofString = request.getParameter("carportRoof");
            String shedWidthString = request.getParameter("shedWidth");
            String shedLengthString = request.getParameter("shedLength");

            if (!carportWidthString.isEmpty() && !carportLengthString.isEmpty() && !carportRoofString.isEmpty()) {
                int carportWidth, carportLength, carportRoof;
                request.setAttribute("carportWidth", carportWidth = Integer.parseInt(carportWidthString));
                request.setAttribute("carportLength", carportLength = Integer.parseInt(carportLengthString));
                request.setAttribute("carportRoof", carportRoof = Integer.parseInt(carportRoofString));
                Carport carport = new Carport(carportWidth, carportLength, 300, carportRoof);
                carport.loadMaterials(database);

                CarportDrawing carportDrawing = new CarportDrawing(carport);
                carportDrawing.setPillarIndent(35*10);
                carportDrawing.setApproximateGapBetweenRafters(55*10);
                carportDrawing.draw();
                if (!shedWidthString.isEmpty() && !shedLengthString.isEmpty()) {
                    int shedWidth, shedLength;
                    request.setAttribute("shedWidth", shedWidth = Integer.parseInt(shedWidthString));
                    request.setAttribute("shedLength", shedLength = Integer.parseInt(shedLengthString));
                    CarportShed shed = new CarportShed(shedWidth, shedLength);
                    carport.setShed(shed);

                    // TODO: CarportShedDrawing shedDrawing = new CarportShedDrawing(shed)
                    // add shedDrawing to carportDrawing
                }
                carportDrawing.setViewport(carportLength + 10, carportWidth + 10);
                carportDrawing.setBackgroundColor("#fff");
                carportDrawing.setDefaultFillColor("#fff");
                carportDrawing.setDefaultStrokeColor("#000");
                carportDrawing.setDefaultStrokeWidth(4);
                request.setAttribute("svg", carportDrawing.toString());

                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String remarks = request.getParameter("remarks");

                if (request.getParameter("submit") != null && !name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    Recipient recipient = new Recipient(name, address, city, phone, email);
                    orderFacade.orderCarport(carport, recipient, remarks);
                    return "thankyou";
                }

                return "drawbuild";
            }
        }

        return pageToShow;
    }
}


