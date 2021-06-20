package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;

import java.sql.*;

public class MaterialMapper {
    private final Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public Material getMaterial(int id, int length) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String name = rs.getString("name");
                int thickness = rs.getInt("thickness");
                int width = rs.getInt("width");
                int price = rs.getInt("price");
                return new Material(id, name, thickness, width, length, price);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Material getCarportMaterial(int id, int length) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport_material WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                Material material = getMaterial(rs.getInt("material_id"), length);
                material.setDescription(rs.getString("description"));
                return material;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}
