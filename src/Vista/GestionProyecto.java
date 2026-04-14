package Vista;

import Modelo.Usuario;

public class GestionProyecto extends javax.swing.JFrame {

    private Usuario usuarioActivo;
    private MenuPrincipalTaskSphere menuPrincipal;

    public GestionProyecto(MenuPrincipalTaskSphere menuPrincipal, Usuario usuarioActivo) {
        this.menuPrincipal = menuPrincipal;
        this.usuarioActivo = usuarioActivo;

        initComponents();
        btnReportes.setName("btnReportes");
        setLocationRelativeTo(null);
        setResizable(false);
        setName("GestionProyecto");
    }

    public GestionProyecto(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setName("GestionProyecto");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	

        jPanel2 = new javax.swing.JPanel();
        btnRegistrarProyecto1 = new javax.swing.JButton();
        btnCronograma1 = new javax.swing.JButton();
        btnTareas1 = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnRiesgos1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnCostos1 = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión Proyectos");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(82, 82, 82));

        btnRegistrarProyecto1.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrarProyecto1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnRegistrarProyecto1.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarProyecto1.setText("Registrar proyecto");
        btnRegistrarProyecto1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnRegistrarProyecto1.setFocusable(false);
        btnRegistrarProyecto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarProyecto1.setMaximumSize(new java.awt.Dimension(90, 53));
        btnRegistrarProyecto1.setPreferredSize(new java.awt.Dimension(62, 53));
        btnRegistrarProyecto1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrarProyecto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProyecto1ActionPerformed(evt);
            }
        });

        btnCronograma1.setBackground(new java.awt.Color(0, 0, 0));
        btnCronograma1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnCronograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnCronograma1.setText("Cronograma");
        btnCronograma1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnCronograma1.setFocusable(false);
        btnCronograma1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCronograma1.setMaximumSize(new java.awt.Dimension(90, 53));
        btnCronograma1.setPreferredSize(new java.awt.Dimension(62, 53));
        btnCronograma1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCronograma1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCronograma1ActionPerformed(evt);
            }
        });

        btnTareas1.setBackground(new java.awt.Color(0, 0, 0));
        btnTareas1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnTareas1.setForeground(new java.awt.Color(255, 255, 255));
        btnTareas1.setText("Tareas");
        btnTareas1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnTareas1.setFocusable(false);
        btnTareas1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTareas1.setMaximumSize(new java.awt.Dimension(90, 53));
        btnTareas1.setPreferredSize(new java.awt.Dimension(62, 53));
        btnTareas1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTareas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTareas1ActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnRegresar.setFocusable(false);
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setMaximumSize(new java.awt.Dimension(90, 53));
        btnRegresar.setPreferredSize(new java.awt.Dimension(62, 53));
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnRiesgos1.setBackground(new java.awt.Color(0, 0, 0));
        btnRiesgos1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnRiesgos1.setForeground(new java.awt.Color(255, 255, 255));
        btnRiesgos1.setText("Riesgos");
        btnRiesgos1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnRiesgos1.setFocusable(false);
        btnRiesgos1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRiesgos1.setMaximumSize(new java.awt.Dimension(90, 53));
        btnRiesgos1.setPreferredSize(new java.awt.Dimension(62, 53));
        btnRiesgos1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRiesgos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiesgos1ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imagen_2025-10-30_164618888.png"))); // NOI18N

        btnCostos1.setBackground(new java.awt.Color(0, 0, 0));
        btnCostos1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnCostos1.setForeground(new java.awt.Color(255, 255, 255));
        btnCostos1.setText("Costos");
        btnCostos1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnCostos1.setFocusable(false);
        btnCostos1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCostos1.setMaximumSize(new java.awt.Dimension(90, 53));
        btnCostos1.setPreferredSize(new java.awt.Dimension(62, 53));
        btnCostos1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCostos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostos1ActionPerformed(evt);
            }
        });

        btnReportes.setBackground(new java.awt.Color(0, 0, 0));
        btnReportes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black, java.awt.Color.darkGray));
        btnReportes.setFocusable(false);
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportes.setMaximumSize(new java.awt.Dimension(90, 53));
        btnReportes.setPreferredSize(new java.awt.Dimension(62, 53));
        btnReportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnRegistrarProyecto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTareas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRiesgos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCronograma1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCostos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnRegistrarProyecto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCronograma1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTareas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRiesgos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCostos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setMenuPrincipal(MenuPrincipalTaskSphere menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public MenuPrincipalTaskSphere getMenuPrincipal() {
        return menuPrincipal;
    }
    
    private void btnRiesgos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiesgos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRiesgos1ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnTareas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTareas1ActionPerformed

    }//GEN-LAST:event_btnTareas1ActionPerformed

    private void btnCronograma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCronograma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCronograma1ActionPerformed

    private void btnRegistrarProyecto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProyecto1ActionPerformed

    }//GEN-LAST:event_btnRegistrarProyecto1ActionPerformed

    private void btnCostos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCostos1ActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    public javax.swing.JButton getBtnRegistrarProyecto() {
        return btnRegistrarProyecto1;
    }

    public javax.swing.JButton getBtnRegresar() {
        return btnRegresar;
    }

    public javax.swing.JButton getBtnTareas() {
        return btnTareas1;
    }

    public javax.swing.JButton getBtnCronograma() {
        return btnCronograma1;
    }

    public javax.swing.JButton getBtnRiesgos() {
        return btnRiesgos1;
    }

    public javax.swing.JButton getBtnCostos() {
        return btnCostos1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCostos1;
    public javax.swing.JButton btnCronograma1;
    public javax.swing.JButton btnRegistrarProyecto1;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JButton btnReportes;
    public javax.swing.JButton btnRiesgos1;
    public javax.swing.JButton btnTareas1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
