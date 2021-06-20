package business.services;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.CarportMapper;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.RecipientMapper;

import java.util.List;

public class OrderFacade {
    private final OrderMapper orderMapper;
    private final CarportMapper carportMapper;
    private final RecipientMapper recipientMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
        carportMapper = new CarportMapper(database);
        recipientMapper = new RecipientMapper(database);
    }

    public Order orderCarport(Carport carport, Recipient recipient, String remarks) throws UserException {
        Order order = orderMapper.createOrder(carport, recipient, remarks);
        carportMapper.createCarportOrder(carport, order);
        recipientMapper.createRecipient(recipient, order);
        return order;
    }

    public void setCarportOrderPrice(int price, int orderId) throws UserException {
        carportMapper.setCarportPrice(price, orderId);
    }

    public Order getCarportOrder(int id) throws UserException {
        return orderMapper.getCarportOrder(id);
    }

    public List<Order> getAllCarportOrders() throws UserException {
        return orderMapper.getAllCarportOrders();
    }

}
