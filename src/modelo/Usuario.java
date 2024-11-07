
package modelo;

/**
 *
 * @author david
 */
public class Usuario {
    int idUsuario;
    String user;
    String password;
    String passwordse;

    public String getPasswordse() {
        return passwordse;
    }

    public void setPasswordse(String passwordse) {
        this.passwordse = passwordse;
    }

    public Usuario(String passwordse) {
        this.passwordse = passwordse;
    }

    public Usuario() {
    }

    public Usuario(int idUsuario, String user, String password) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
