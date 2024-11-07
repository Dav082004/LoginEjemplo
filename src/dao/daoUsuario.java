package dao;

import java.sql.ResultSet;
import conexion.Conexion;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.logging.Level;      // Importa los niveles de log para registros de mensajes (info, error, etc.)
import java.util.logging.Logger;     // Importa la clase Logger para imprimir mensajes de log en caso de error
import java.sql.PreparedStatement;
import java.sql.SQLException;        // Importa la clase para manejar excepciones de SQL

/**
 *
 * @author david
 */
public class daoUsuario {

    Conexion cx = new Conexion("login_ejem");

    public daoUsuario() {
    }

    public boolean create(Usuario user) {
        try {
            String sqlInsert = "INSERT INTO usuario (idusuario, user, password,passwordse) VALUES(null,?,?,?)";
            PreparedStatement ps = cx.conectar().prepareStatement(sqlInsert);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPasswordse());
            ps.execute();
            ps.close();
            ps = null;
            cx.desconectar();
            return true;
        } catch (SQLException ex) {  // Captura la excepción si ocurre un error al cerrar
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex); // Registro detallado del error en el cierre
            return false;
        }
    }

    public ArrayList<Usuario> read() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        try {
            PreparedStatement ps = cx.conectar().prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idusuario"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
                u.setPasswordse(rs.getString("passwordSE"));
                lista.add(u);
            }
            ps.close();
            ps = null;
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(daoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Usuario read(int idUsuario) {
        Usuario u = new Usuario();
        try {
            PreparedStatement ps = cx.conectar().prepareStatement("SELECT * FROM usuario WHERE idusuario=?");
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("idusuario"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
                u.setPasswordse(rs.getString("passwordse"));
            }
            ps.close();
            ps = null;
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(daoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean update(Usuario user) {
        try {
            String sqlUpdate = "UPDATE usuario SET user=?, password=? WHERE idusuario=?";
            PreparedStatement ps = cx.conectar().prepareStatement(sqlUpdate);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPasswordse()); // Actualiza la columna passwordse
            ps.setInt(4, user.getIdUsuario());
            ps.execute();
            ps.close();
            ps = null;
            cx.desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(daoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int idusuario) {
        try {
            String sqlDelete = "DELETE FROM usuario WHERE idusuario=?";
            PreparedStatement ps = cx.conectar().prepareStatement(sqlDelete);
            ps.setInt(1, idusuario);
            ps.execute();
            ps.close();
            ps = null;
            cx.desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(daoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
