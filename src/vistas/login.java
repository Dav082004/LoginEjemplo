package vistas;

import conexion.Conexion;
import java.awt.Image; // Importar clase para manejar imágenes
import java.awt.Toolkit; // Importar clase para acceder a recursos de sistema
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException; // Importar clase para manejar excepciones de SQL
import java.util.logging.Level; // Importar clase para establecer niveles de logging
import java.util.logging.Logger; // Importar clase para registrar información de log
import javax.swing.ImageIcon; // Importar clase para manejar iconos de imagen
import javax.swing.JOptionPane; // Importar clase para mostrar diálogos de opciones
import java.sql.ResultSet; // Importar clase para manejar resultados de consultas SQL
import java.sql.Statement; // Importar clase para ejecutar sentencias SQL

/**
 *
 * @author david
 */
public class login extends javax.swing.JFrame {

    // Objeto para la conexión a la base de datos
    Conexion cx;

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public login() {
        initComponents(); // Inicializa los componentes de la interfaz gráfica
        this.setTitle("LOGIN"); // Establece el título de la ventana
        this.setSize(550, 450); // Establece el tamaño de la ventana

        // Carga y establece el logo en la interfaz
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/logo.png"));
        lblLogo.setIcon(new ImageIcon(img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
        this.setIconImage(img); // Establece la imagen de la ventana
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Inicializa la conexión a la base de datos con el nombre "login_ejem"
        cx = new Conexion("login_ejem");
        try {
            cx.conectar(); // Intenta conectar a la base de datos
        } catch (SQLException ex) {
            // Registra un error si no se puede conectar
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 320, 41));

        jLabel1.setText("Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout_16dp_E8EAED_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 170, 60));

        btnIniciar.setBackground(new java.awt.Color(0, 102, 102));
        btnIniciar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login_16dp_E8EAED_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 160, 60));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N
        lblLogo.setText("jLabel3");
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 190, 130));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 320, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        Statement st = null; // Declaración de la variable para la sentencia SQL
        ResultSet rs = null; // Declaración de la variable para el resultado de la consulta SQL

        try {
            String user = txtUsuario.getText(); // Obtiene el texto del campo de usuario
            String password = String.valueOf(txtPassword.getPassword()); // Obtiene la contraseña del campo de contraseña
            password = convertirSHA256(password);
            // Consulta SQL para verificar el usuario y la contraseña
            String query = "SELECT * FROM usuario WHERE user='" + user + "' and password='" + password + "'";
            System.out.println(query); // Muestra la consulta en la consola

            // Crea una sentencia SQL usando la conexión existente
            st = (Statement) cx.conectar().createStatement();
            rs = st.executeQuery(query); // Ejecuta la consulta y obtiene el resultado

            // Verifica si se encontró un resultado
            if (rs.next()) {
                // Si el usuario existe, muestra un mensaje de éxito
                JOptionPane.showMessageDialog(this, "EL USUARIO ESTA EN LA BASE DE DATOS");
                proceso p = new proceso();
                p.setVisible(true);
                this.setVisible(false);

            } else {
                // Si no existe, muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "EL NO USUARIO EXISTE EN LA BASE DE DATOS");
            }
        } catch (SQLException ex) {
            // Registra un error si hay problemas con la consulta
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cierra ResultSet y Statement para liberar recursos
            try {
                if (rs != null) {
                    rs.close(); // Cierra el resultado si no es nulo
                }
                if (st != null) {
                    st.close(); // Cierra la sentencia si no es nula
                }
            } catch (SQLException ex) {
                // Registra un error si hay problemas al cerrar
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
