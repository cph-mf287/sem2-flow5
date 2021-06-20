package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.exceptions.ValueNotAssignedException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageCarportOrdersCommand extends CommandProtectedPage {
    public String pageToShow;
    private final OrderFacade orderFacade;

    public ManageCarportOrdersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.pageToShow = pageToShow;
        orderFacade = new OrderFacade(database);
    }

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws UserException, ValueNotAssignedException {
        String approved = request.getParameter("approved");
        if (approved != null) {
            int price = Integer.parseInt(request.getParameter("price"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            orderFacade.setCarportOrderPrice(price, orderId);
            request.setAttribute("onload", "window.onload = function goBack() {\n" +
                    "\twindow.history.go(-2);\n" +
                    "}");
        }
        String view = request.getParameter("view");
        if (view != null) {
            int id = Integer.parseInt(view);
            Order order = orderFacade.getCarportOrder(id);
            Carport carport = order.getCarport();
            carport.loadMaterials(database);

            CarportMaterialList materialList = new CarportMaterialList(carport);
            materialList.setPillarIndent(35*10);
            materialList.setApproximateGapBetweenRafters(55*10);
            materialList.load();
            request.setAttribute("list", materialList.toString());
            request.setAttribute("price", materialList.getTotalCost());
            request.setAttribute("orderId", order.getId());

            CarportDrawing carportDrawing = new CarportDrawing(carport);
            carportDrawing.setPillarIndent(35*10);
            carportDrawing.setApproximateGapBetweenRafters(55*10);
            carportDrawing.draw();
            if (carport.hasShed()) {
                CarportShed shed = carport.getShed();

                // TODO: CarportShedDrawing shedDrawing = new CarportShedDrawing(shed)
                // add shedDrawing to carportDrawing
            }
            carportDrawing.setViewport(carport.getLength() + 10, carport.getWidth() + 10);
            carportDrawing.setBackgroundColor("#fff");
            carportDrawing.setDefaultFillColor("#fff");
            carportDrawing.setDefaultStrokeColor("#000");
            carportDrawing.setDefaultStrokeWidth(4);
            request.setAttribute("svg", carportDrawing.toString());
            return "carportorder";
        }
        List<Order> orders = orderFacade.getAllCarportOrders();
        request.setAttribute("orders", orders);
        return pageToShow;
    }
}


