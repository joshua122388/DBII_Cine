package accesoDatos;

import entidades.ProductoSnack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoSnackDAO {

    // INSERT
    public void insertar(ProductoSnack producto) throws Exception {
        String sql = "INSERT INTO producto_snack (Codigo_Producto, Nombre, Precio) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, producto.getCodigoProducto());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());

            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void actualizar(ProductoSnack producto) throws Exception {
        String sql = "UPDATE producto_snack SET Nombre=?, Precio=? WHERE Codigo_Producto=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setString(3, producto.getCodigoProducto());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void eliminar(String codigoProducto) throws Exception {
        String sql = "DELETE FROM producto_snack WHERE Codigo_Producto=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoProducto);
            stmt.executeUpdate();
        }
    }

    // SELECT by ID
    public ProductoSnack buscarPorID(String codigoProducto) throws Exception {
        String sql = "SELECT * FROM producto_snack WHERE Codigo_Producto=?";
        ProductoSnack producto = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new ProductoSnack(
                    rs.getString("Codigo_Producto"),
                    rs.getString("Nombre"),
                    rs.getDouble("Precio")
                );
            }
        }
        return producto;
    }

    // SELECT ALL
    public List<ProductoSnack> listarTodos() throws Exception {
        List<ProductoSnack> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto_snack";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductoSnack producto = new ProductoSnack(
                    rs.getString("Codigo_Producto"),
                    rs.getString("Nombre"),
                    rs.getDouble("Precio")
                );
                lista.add(producto);
            }
        }
        return lista;
    }
}
