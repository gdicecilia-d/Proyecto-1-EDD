package Vistas;
import Controlador.GrafoControlador;

public class MainVentana extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainVentana.class.getName());
    
    private GrafoControlador controlador;
    
    public MainVentana() {
        initComponents();
        
        // Inicializar controlador
        controlador = new GrafoControlador();
        
        // Imagenes para los botones y para el título
        // Tamaños
        int tamañoBoton = 20;      // Imagenes de botones
        int tamañoTitulo = 35;     // Imagen del título
        
        // Cargar Archivo 
        java.net.URL urlCargar = getClass().getResource("/Imagenes/folder.png");
        java.awt.Image imgCargar = new javax.swing.ImageIcon(urlCargar).getImage();
        java.awt.Image imgCargarEscalada = imgCargar.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnCargar.setIcon(new javax.swing.ImageIcon(imgCargarEscalada));
        
        // Guardar Cambios 
        java.net.URL urlGuardar = getClass().getResource("/Imagenes/floppy-disk.png");
        java.awt.Image imgGuardar = new javax.swing.ImageIcon(urlGuardar).getImage();
        java.awt.Image imgGuardarEscalada = imgGuardar.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnGuardar.setIcon(new javax.swing.ImageIcon(imgGuardarEscalada));
        
        // Agregar Proteína 
        java.net.URL urlAgregar = getClass().getResource("/Imagenes/plus.png");
        java.awt.Image imgAgregar = new javax.swing.ImageIcon(urlAgregar).getImage();
        java.awt.Image imgAgregarEscalada = imgAgregar.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnAgregar.setIcon(new javax.swing.ImageIcon(imgAgregarEscalada));
        
        // Eliminar Proteína 
        java.net.URL urlEliminar = getClass().getResource("/Imagenes/substract.png");
        java.awt.Image imgEliminar = new javax.swing.ImageIcon(urlEliminar).getImage();
        java.awt.Image imgEliminarEscalada = imgEliminar.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnEliminar.setIcon(new javax.swing.ImageIcon(imgEliminarEscalada));
        
        // Detectar Complejos 
        java.net.URL urlComplejos = getClass().getResource("/Imagenes/search.png");
        java.awt.Image imgComplejos = new javax.swing.ImageIcon(urlComplejos).getImage();
        java.awt.Image imgComplejosEscalada = imgComplejos.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnComplejos.setIcon(new javax.swing.ImageIcon(imgComplejosEscalada));
        
        // Ruta más Corta 
        java.net.URL urlRuta = getClass().getResource("/Imagenes/route-planning.png");
        java.awt.Image imgRuta = new javax.swing.ImageIcon(urlRuta).getImage();
        java.awt.Image imgRutaEscalada = imgRuta.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnRuta.setIcon(new javax.swing.ImageIcon(imgRutaEscalada));
        
        // Identificar Hubs 
        java.net.URL urlHubs = getClass().getResource("/Imagenes/star.png");
        java.awt.Image imgHubs = new javax.swing.ImageIcon(urlHubs).getImage();
        java.awt.Image imgHubsEscalada = imgHubs.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnHubs.setIcon(new javax.swing.ImageIcon(imgHubsEscalada));
        
        // TÍTULO 
        java.net.URL urlDna = getClass().getResource("/Imagenes/dna.png");
        java.awt.Image imgDna = new javax.swing.ImageIcon(urlDna).getImage();
        java.awt.Image imgDnaEscalada = imgDna.getScaledInstance(tamañoTitulo, tamañoTitulo, java.awt.Image.SCALE_SMOOTH);
        lblTitulo.setIcon(new javax.swing.ImageIcon(imgDnaEscalada));
        
        // Posición del texto y la imagen
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT); 
        lblTitulo.setIconTextGap(15);    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnComplejos = new javax.swing.JButton();
        btnRuta = new javax.swing.JButton();
        btnHubs = new javax.swing.JButton();
        panelCentral = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(237, 235, 255));

        panelTitulo.setBackground(new java.awt.Color(230, 225, 240));
        panelTitulo.setLayout(new java.awt.BorderLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(106, 63, 181));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("BioGraph - Análisis de Interacciones Proteicas");
        panelTitulo.add(lblTitulo, java.awt.BorderLayout.CENTER);

        panelBotones.setBackground(new java.awt.Color(243, 241, 247));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        btnCargar.setBackground(new java.awt.Color(109, 63, 175));
        btnCargar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setText("Cargar Archivo");
        btnCargar.setFocusPainted(false);
        btnCargar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnCargar.addActionListener(this::btnCargarActionPerformed);
        panelBotones.add(btnCargar);

        btnGuardar.setBackground(new java.awt.Color(75, 58, 109));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panelBotones.add(btnGuardar);

        btnAgregar.setBackground(new java.awt.Color(209, 107, 165));
        btnAgregar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Proteína");
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);
        panelBotones.add(btnAgregar);

        btnEliminar.setBackground(new java.awt.Color(201, 79, 144));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Proteína");
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        panelBotones.add(btnEliminar);

        btnComplejos.setBackground(new java.awt.Color(214, 205, 234));
        btnComplejos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnComplejos.setForeground(new java.awt.Color(75, 58, 109));
        btnComplejos.setText("Detectar Complejos");
        btnComplejos.setFocusPainted(false);
        btnComplejos.setPreferredSize(new java.awt.Dimension(170, 35));
        btnComplejos.addActionListener(this::btnComplejosActionPerformed);
        panelBotones.add(btnComplejos);

        btnRuta.setBackground(new java.awt.Color(239, 234, 248));
        btnRuta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRuta.setForeground(new java.awt.Color(110, 106, 120));
        btnRuta.setText("Ruta más Corta");
        btnRuta.setFocusPainted(false);
        btnRuta.setPreferredSize(new java.awt.Dimension(170, 35));
        btnRuta.addActionListener(this::btnRutaActionPerformed);
        panelBotones.add(btnRuta);

        btnHubs.setBackground(new java.awt.Color(255, 221, 255));
        btnHubs.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHubs.setForeground(new java.awt.Color(201, 79, 144));
        btnHubs.setText("Identificar Hubs");
        btnHubs.setFocusPainted(false);
        btnHubs.setPreferredSize(new java.awt.Dimension(170, 35));
        btnHubs.addActionListener(this::btnHubsActionPerformed);
        panelBotones.add(btnHubs);

        panelCentral.setBackground(new java.awt.Color(243, 241, 247));
        panelCentral.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(185, 180, 199), null));

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        panelInferior.setBackground(new java.awt.Color(255, 255, 255));
        panelInferior.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(165, 138, 217), null));
        panelInferior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelInferior.setLayout(new java.awt.BorderLayout());

        lblInfo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(110, 106, 120));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInfo.setText("Proteínas: 0 | Hub principal: --- | Conexiones: 0");
        lblInfo.setPreferredSize(new java.awt.Dimension(400, 30));
        panelInferior.add(lblInfo, java.awt.BorderLayout.WEST);

        btnSalir.setBackground(new java.awt.Color(75, 58, 109));
        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(72, 15));
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        panelInferior.add(btnSalir, java.awt.BorderLayout.EAST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
            .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRutaActionPerformed

    private void btnHubsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHubsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHubsActionPerformed

    private void btnComplejosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComplejosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComplejosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
        this,                                   // Ventana 
        "¿Estás seguro que deseas salir?",      // Mensaje
        "Confirmar salida",                      // Título
        javax.swing.JOptionPane.YES_NO_OPTION,  // Opciones (Sí/No)
        javax.swing.JOptionPane.QUESTION_MESSAGE // Icono de pregunta
        );
        // Si el usuario dice "Sí"
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra el programa completamente
        }
        // Sino, no hace nada
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String args[]) {
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> new MainVentana().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnComplejos;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHubs;
    private javax.swing.JButton btnRuta;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
