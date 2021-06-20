package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private final Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public Order createOrder(Carport carport, Recipient recipient, String remarks) throws UserException {
        try (Connection connection = database.connect()){
            String sql = "INSERT INTO `order` (remarks, date_created) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime now = LocalDateTime.now();
                ps.setString(1, remarks);
                ps.setLong(2, Long.parseLong(datetime.format(now)));
                ps.executeUpdate();
                /////////////////////////////////////
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                return new Order(id, carport, recipient, remarks, 0, datetime.format(now));
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    // TODO: make a getMaterialOrder to get orders of separate materials

    public Order getCarportOrder(int id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order` WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                Carport carport = new CarportMapper(database).getOrderedCarport(id);
                Recipient recipient = new RecipientMapper(database).getRecipient(id);
                String remarks = rs.getString("remarks");
                int price = rs.getInt("price");
                String created = rs.getString("date_created");
                return new Order(id, carport, recipient, remarks, price, created);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    // TODO: make a getAllMaterialOrders to get all material lists of orders of separate materials

    public List<Order> getAllCarportOrders() throws UserException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM order_carport";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("order_id");
                    Order order = getCarportOrder(id);
                    orders.add(order);
                }
                return orders;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}
