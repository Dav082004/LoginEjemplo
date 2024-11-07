package gestion;

import dao.daoUsuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;

/**
 *
 * @author david
 */
public class vUsuarios extends javax.swing.JInternalFrame {

    daoUsuario daoUser = new daoUsuario();
    DefaultTableModel model = new DefaultTableModel();
    Usuario user;
    int index = -1;
    ArrayList<Usuario> lista;

    public vUsuarios() {
        initComponents();
        this.setTitle("GESTION DE USUARIOS");
        model.addColumn("IDUSUARIO");
        model.addColumn("USUARIO");
        model.addColumn("PASSWORD");
        model.addColumn("PASSWORDSINENCRIP");
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBorrar.setEnabled(true);
        refrescarTabla();
    }

    public void borrar() {
        txtUser.setText("");
        txtPassword.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 31, -1, -1));

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 15, 248, 48));

        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 91, -1, -1));

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 80, 40));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 80, 40));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 80, 40));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 80, 40));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 80, 40));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 250, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 470, 240));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 470, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        btnGuardar.setEnabled(true);
        borrar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Usuario user = new Usuario();
        user.setUser(txtUser.getText());
        user.setPasswordse(txtPassword.getText());
        user.setPassword(convertirSHA256(txtPassword.getText()));
        System.out.println("" + convertirSHA256(txtPassword.getText()));
        if (daoUser.create(user)) {
            JOptionPane.showMessageDialog(this, "Se creo correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar usuario");
        }
        refrescarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        user.setUser(txtUser.getText());
        user.setPassword(convertirSHA256(txtPassword.getText()));
        if (daoUser.update(user)) {
            JOptionPane.showMessageDialog(this, "Se edito correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al editar Usuario");
        }
        refrescarTabla();
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBorrar.setEnabled(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (index > -1) {
            int res = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres eliminar?", "ELIMINAR", JOptionPane.YES_NO_OPTION);

            if (res == 0) {
                if (daoUser.delete(lista.get(index).getIdUsuario())) {
                    JOptionPane.showMessageDialog(this, "Se elimino correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar Usuario");
                }
                refrescarTabla();
                btnNuevo.setEnabled(true);
                btnGuardar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnBorrar.setEnabled(true);
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        index = tablaUsuarios.getSelectedRow();
        user = daoUser.read(lista.get(index).getIdUsuario());
        txtUser.setText(user.getUser());
        txtPassword.setText(user.getPassword());

        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    public void refrescarTabla() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        lista = daoUser.read();
        for (Usuario usuario : lista) {
            Object fila[] = new Object[3];
            fila[0] = usuario.getIdUsuario();
            fila[1] = usuario.getUser();
            fila[2] = usuario.getPassword();
            model.addRow(fila);
        }
        tablaUsuarios.setModel(model);
    }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

}
