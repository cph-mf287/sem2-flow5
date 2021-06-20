package business.persistence;

import business.entities.Carport;
import business.entities.CarportShed;
import business.entities.Order;
import business.exceptions.UserException;

import java.sql.*;

public class CarportMapper {
    private final Database database;

    public CarportMapper(Database database) {
        this.database = database;
    }

    public void createCarportOrder(Carport carport, Order order) throws UserException {
        try (Connection connection = database.connect()){
            String sql = "INSERT INTO order_carport (order_id, carport_width, carport_length, carport_roof, shed_width, shed_length) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1, order.getId());
                ps.setInt(2, carport.getWidth());
                ps.setInt(3, carport.getLength());
                ps.setInt(4, carport.getRoofType());
                if (carport.hasShed()) {
                    CarportShed shed = carport.getShed();
                    ps.setInt(5, shed.getWidth());
                    ps.setInt(6, shed.getLength());
                } else {
                    ps.setNull(5, Types.INTEGER);
                    ps.setNull(6, Types.INTEGER);
                }
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Carport getOrderedCarport(int id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM order_carport WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int carportWidth = rs.getInt("carport_width");
                int carportLength = rs.getInt("carport_length");
                int carportRoof = rs.getInt("carport_roof");
                int shedWidth = rs.getInt("carport_width");
                int shedLength = rs.getInt("carport_length");
                Carport carport = new Carport(carportWidth, carportLength, 300, carportRoof);
                carport.loadMaterials(database);
                if (shedWidth > 0 || shedLength > 0) {
                    CarportShed shed = new CarportShed(shedWidth, shedLength);
                    shed.loadMaterials(database);
                    carport.setShed(shed);
                }
                return carport;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void setCarportPrice(int price, int orderId) throws UserException {
        try (Connection connection = database.connect()){
            String sql = "UPDATE `order` SET price = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, price);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}