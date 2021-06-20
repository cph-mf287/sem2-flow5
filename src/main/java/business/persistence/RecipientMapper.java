package business.persistence;

import business.entities.Order;
import business.entities.Recipient;
import business.exceptions.UserException;

import java.sql.*;

public class RecipientMapper {
    private final Database database;

    public RecipientMapper(Database database)
    {
        this.database = database;
    }

    public void createRecipient(Recipient recipient, Order order) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO recipient (order_id, name, address, city, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getId());
                ps.setString(2, recipient.getFullName());
                ps.setString(3, recipient.getAddress());
                ps.setString(4, recipient.getCity());
                ps.setString(5, recipient.getPhone());
                ps.setString(6, recipient.getEmail());
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Recipient getRecipient(int id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM recipient WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String name = rs.getString("name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                return new Recipient(name, address, city, phone, email);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}
