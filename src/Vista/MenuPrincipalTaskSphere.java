package Vista;

import Modelo.Usuario;
import Controlador.CtrlMenuPrincipal;
import javax.swing.JOptionPane;
import Controlador.CtrlRegistroUsuario;
import Modelo.ConsultasUsuario;

public class MenuPrincipalTaskSphere extends javax.swing.JFrame {

    private Usuario usuarioActivo;

    public MenuPrincipalTaskSphere(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setName("MenuPrincipalTaskSphere");
    }

    public MenuPrincipalTaskSphere(Usuario usuarioActivo, boolean mostrarSaludo) {
        this.usuarioActivo = usuarioActivo;

        initComponents();
        
        btnGProyectos.setName("btnGProyectos");
        btnGTareas.setName("btnGTareas");
        btnUsuarios.setName("btnUsuarios");
        btnSalir.setName("btnSalir");

        setName("MenuPrincipalTaskSphere");
        
        setLocationRelativeTo(null);
        setResizable(false);
        

        if (mostrarSaludo) {
            JOptionPane.showMessageDialog(this,
                "Bienvenido " + usuarioActivo.getNombre() + " - Rol: " + usuarioActivo.getRol());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRegistro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGProyectos = new javax.swing.JButton();
        btnGTareas = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/main menu (3).png"))); // NOI18N

        btnRegistro.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnRegistro.setText("REGISTRO / USUARIOS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Diseño sin título (2).gif"))); // NOI18N

        btnGProyectos.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnGProyectos.setText("GESTIÓN PROYECTOS");
        btnGProyectos.setName("btnGProyectos");

        btnGTareas.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnGTareas.setText("GESTIÓN TAREAS");

        btnUsuarios.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnUsuarios.setText("VISTA USUARIOS");

        btnSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnSalir.setText("SALIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGTareas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0)
                .addComponent(jLabel2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnGProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnGTareas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGProyectos;
    public javax.swing.JButton btnGTareas;
    public javax.swing.JButton btnRegistro;
    public javax.swing.JButton btnSalir;
    public javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}

