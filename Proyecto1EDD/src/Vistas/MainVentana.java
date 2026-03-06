package Vistas;
import Controlador.GrafoControlador;

public class MainVentana extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainVentana.class.getName());
    
    private GrafoControlador controlador;
    private PanelGrafo panelGrafo;
    
    public MainVentana() {
        initComponents();
        
        // Abrir en pantalla completa
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        // Inicializar controlador
        controlador = new GrafoControlador();
        
        // Crear el panel del grafo
        panelGrafo = new PanelGrafo();
    
        // Limpiar el panelCentral existente y agregar panelGrafo
        panelCentral.removeAll();  
        panelCentral.setLayout(new java.awt.BorderLayout()); 
        panelCentral.add(panelGrafo, java.awt.BorderLayout.CENTER);
        panelCentral.setBackground(new java.awt.Color(243, 241, 247));  
        panelCentral.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(185, 180, 199), null));
        panelCentral.revalidate(); 
        panelCentral.repaint();
        
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
        
        // Agregar Conexion
        java.net.URL urlAgregarConexion = getClass().getResource("/Imagenes/broken-link.png");
        java.awt.Image imgAgregarConexion = new javax.swing.ImageIcon(urlAgregarConexion).getImage();
        java.awt.Image imgAgregarConexionEscalada = imgAgregarConexion.getScaledInstance(tamañoBoton, tamañoBoton, java.awt.Image.SCALE_SMOOTH);
        btnAgregarConexion.setIcon(new javax.swing.ImageIcon(imgAgregarConexionEscalada));
        
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
        
        // Carga automática del archivo
        try {
            java.io.File archivoInicial = new java.io.File("maestro.csv");
            if (archivoInicial.exists()) {
                controlador.cargarArchivo(archivoInicial);
                String[] estadisticas = controlador.obtenerEstadisticas();
                int conexiones = controlador.getNumConexiones();
                lblInfo.setText("Proteínas: " + estadisticas[0] + 
                               " | Hub principal: " + (estadisticas[1] != null ? estadisticas[1] : "---") + 
                               " | Conexiones: " + conexiones);
                panelGrafo.actualizarGrafo(controlador.getGrafo());
            }
        } catch (Exception e) {
            //No hacer nada
        }
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
        btnAgregarConexion = new javax.swing.JButton();
        btnComplejos = new javax.swing.JButton();
        btnRuta = new javax.swing.JButton();
        btnHubs = new javax.swing.JButton();
        panelCentral = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BioGraph");
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
        btnCargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargar.setFocusPainted(false);
        btnCargar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnCargar.addActionListener(this::btnCargarActionPerformed);
        panelBotones.add(btnCargar);

        btnGuardar.setBackground(new java.awt.Color(75, 58, 109));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panelBotones.add(btnGuardar);

        btnAgregar.setBackground(new java.awt.Color(209, 107, 165));
        btnAgregar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Proteína");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);
        panelBotones.add(btnAgregar);

        btnEliminar.setBackground(new java.awt.Color(201, 79, 144));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Proteína");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new java.awt.Dimension(170, 35));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        panelBotones.add(btnEliminar);

        btnAgregarConexion.setBackground(new java.awt.Color(204, 255, 255));
        btnAgregarConexion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregarConexion.setForeground(new java.awt.Color(0, 179, 179));
        btnAgregarConexion.setText("Agregar Conexion");
        btnAgregarConexion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarConexion.setFocusPainted(false);
        btnAgregarConexion.setPreferredSize(new java.awt.Dimension(170, 35));
        btnAgregarConexion.addActionListener(this::btnAgregarConexionActionPerformed);
        panelBotones.add(btnAgregarConexion);

        btnComplejos.setBackground(new java.awt.Color(214, 205, 234));
        btnComplejos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnComplejos.setForeground(new java.awt.Color(75, 58, 109));
        btnComplejos.setText("Detectar Complejos");
        btnComplejos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComplejos.setFocusPainted(false);
        btnComplejos.setPreferredSize(new java.awt.Dimension(170, 35));
        btnComplejos.addActionListener(this::btnComplejosActionPerformed);
        panelBotones.add(btnComplejos);

        btnRuta.setBackground(new java.awt.Color(239, 234, 248));
        btnRuta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRuta.setForeground(new java.awt.Color(110, 106, 120));
        btnRuta.setText("Ruta más Corta");
        btnRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRuta.setFocusPainted(false);
        btnRuta.setPreferredSize(new java.awt.Dimension(170, 35));
        btnRuta.addActionListener(this::btnRutaActionPerformed);
        panelBotones.add(btnRuta);

        btnHubs.setBackground(new java.awt.Color(255, 221, 255));
        btnHubs.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHubs.setForeground(new java.awt.Color(201, 79, 144));
        btnHubs.setText("Identificar Hubs");
        btnHubs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHubs.setFocusPainted(false);
        btnHubs.setPreferredSize(new java.awt.Dimension(170, 35));
        btnHubs.addActionListener(this::btnHubsActionPerformed);
        panelBotones.add(btnHubs);

        panelCentral.setBackground(new java.awt.Color(243, 241, 247));
        panelCentral.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(185, 180, 199), null));
        panelCentral.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelCentral.setPreferredSize(new java.awt.Dimension(4, 235));

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        panelInferior.setBackground(new java.awt.Color(230, 225, 240));
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
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setPreferredSize(new java.awt.Dimension(72, 15));
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        panelInferior.add(btnSalir, java.awt.BorderLayout.EAST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
            .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
            .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Verificar que hay datos
        if (controlador.numProteínas() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No hay datos para guardar. Carga un archivo primero.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear para seleccionar archivos
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo CSV");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));

        int resultado = fileChooser.showSaveDialog(this);

        if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File archivo = fileChooser.getSelectedFile();

                // Agregar extensión .csv si no la tiene
                if (!archivo.getName().toLowerCase().endsWith(".csv")) {
                    archivo = new java.io.File(archivo.getAbsolutePath() + ".csv");
                }

                // Llamar al controlador
                controlador.guardarArchivo(archivo);

                javax.swing.JOptionPane.showMessageDialog(this,
                    "Archivo guardado: " + archivo.getName(),
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No se pudo guardar " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        
        // Verificar si hay datos para guardar
        if (controlador.numProteínas() > 0) {
            int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                "Hay datos cargados actualmente.\n¿Deseas guardarlos antes de cargar un nuevo archivo?",
                "Datos sin guardar",
                javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);

            if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                btnGuardarActionPerformed(evt);
            } else if (respuesta == javax.swing.JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        // Crear para seleccionar archivos
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File archivo = fileChooser.getSelectedFile();

                // Llamar al controlador
                controlador.cargarArchivo(archivo);

                // Actualizar información
                String[] estadisticas = controlador.obtenerEstadisticas();
                int conexiones = controlador.getNumConexiones();
                lblInfo.setText("Proteínas: " + estadisticas[0] + 
                                " | Hub principal: " + (estadisticas[1] != null ? estadisticas[1] : "---") + 
                                " | Conexiones: " + conexiones);
                
                panelGrafo.actualizarGrafo(controlador.getGrafo()); // Actualizar el grafo visual

                javax.swing.JOptionPane.showMessageDialog(this,
                    "Archivo cargado exitosamente.\nProteínas: " + controlador.numProteínas(),
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No se pudo cargar " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String id = javax.swing.JOptionPane.showInputDialog(this,
        "Ingresa el ID de la nueva proteína:",
        "Agregar Proteína",
        javax.swing.JOptionPane.QUESTION_MESSAGE);
    
        // Verificar que el usuario no haya cancelado y que el ID no esté vacío
        if (id == null || id.trim().isEmpty()) {
            return; // Salir si no hay ID válido
        }

        // Preguntar si se quieren agregar conexiones
        int conectar = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Quiere conectar " + id + " con alguna proteína existente?",
            "Agregar Conexión",
            javax.swing.JOptionPane.YES_NO_OPTION);

        // Variables para la conexión 
        String destino = null;
        int peso = 0;
        boolean agregarConexion = false;

        // Si quiere conexiones:
        if (conectar == javax.swing.JOptionPane.YES_OPTION) {

            // Datos de conexión
            javax.swing.JPanel panelConexion = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 10, 10));

            // Campo para la proteína destino
            panelConexion.add(new javax.swing.JLabel("Conectar con:"));
            javax.swing.JTextField txtDestino = new javax.swing.JTextField();
            panelConexion.add(txtDestino);

            // Campo para el peso de la conexión (valor por defecto 10)
            panelConexion.add(new javax.swing.JLabel("Peso de la conexión:"));
            javax.swing.JTextField txtPeso = new javax.swing.JTextField("10");
            panelConexion.add(txtPeso);

            int resultadoConexion = javax.swing.JOptionPane.showConfirmDialog(this,
                panelConexion,
                "Datos de la conexión",
                javax.swing.JOptionPane.OK_CANCEL_OPTION);

            // Si cancela, salir sin agregar nada
            if (resultadoConexion != javax.swing.JOptionPane.OK_OPTION) {
                return; 
            }

            // Obtener los valores ingresados y limpiar espacios
            destino = txtDestino.getText().trim();
            String pesoTexto = txtPeso.getText().trim();

            // Verificar que ambos campos tengan datos
            if (destino.isEmpty() || pesoTexto.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Debes completar todos los campos",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir el peso a número entero
                peso = Integer.parseInt(pesoTexto);
                
                // Verificar si es mayor a 0
                if (peso <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "El peso debe ser un número positivo mayor que cero",
                        "Error de validación",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }catch (NumberFormatException e) {
                // Si el peso no es un número válido
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El peso debe ser un número entero",
                    "Error de formato",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que la proteína destino exista
            if (controlador.getGrafo().buscarVertice(destino) == null) {
                // Mostrar error si no existe
                javax.swing.JOptionPane.showMessageDialog(this,
                    "La proteína " + destino + " no existe",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            agregarConexion = true;
        }

        try {
            // Agregar la proteína
            controlador.agregarProteina(id);

            // Si hay conexión, agregarla
            if (agregarConexion) {
                controlador.getGrafo().insertarArista(id, destino, peso);
            }

            // Actualizar la interfaz
            String[] estadisticas = controlador.obtenerEstadisticas();
            int conexiones = controlador.getNumConexiones(); // Obtener total de conexiones

            // Actualizar el panel inferior con la nueva información
            lblInfo.setText("Proteínas: " + estadisticas[0] + 
                           " | Hub principal: " + (estadisticas[1] != null ? estadisticas[1] : "---") + 
                           " | Conexiones: " + conexiones); 

            // Actualizar el panel del grafo para mostrar los cambios
            panelGrafo.actualizarGrafo(controlador.getGrafo());

            // Mensaje según si se agregaron conexiones o no
            String mensajeExito = "Proteína " + id + " agregada";
            if (agregarConexion) {
                mensajeExito += " con conexión a " + destino + " (peso " + peso + ")";
            }

            javax.swing.JOptionPane.showMessageDialog(this,
                mensajeExito,
                "Éxito",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No se pudo agregar: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String id = javax.swing.JOptionPane.showInputDialog(this,
        "Ingresa el ID de la proteína a eliminar:",
        "Eliminar Proteína",
        javax.swing.JOptionPane.WARNING_MESSAGE);
    
        if (id != null && !id.trim().isEmpty()) {
            int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar la proteína " + id + "?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    controlador.eliminarProteina(id);

                    String[] estadisticas = controlador.obtenerEstadisticas();
                    lblInfo.setText("Proteínas: " + estadisticas[0] + 
                                   " | Hub principal: " + (estadisticas[1] != null ? estadisticas[1] : "---") + 
                                   " | Eliminada: " + id);
                    
                    panelGrafo.actualizarGrafo(controlador.getGrafo()); // Actualizar el grafo visual

                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Proteína " + id + " eliminada",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar " + e.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaActionPerformed
        // Verificar que hay datos
        if (controlador.numProteínas() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No hay proteínas cargadas. Carga un archivo primero.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear panel para origen y destino
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 10, 10));

        panel.add(new javax.swing.JLabel("Proteína ORIGEN:"));
        javax.swing.JTextField txtOrigen = new javax.swing.JTextField();
        panel.add(txtOrigen);

        panel.add(new javax.swing.JLabel("Proteína DESTINO:"));
        javax.swing.JTextField txtDestino = new javax.swing.JTextField();
        panel.add(txtDestino);

        int resultado = javax.swing.JOptionPane.showConfirmDialog(this, panel,
            "Buscar ruta más corta",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (resultado == javax.swing.JOptionPane.OK_OPTION) {
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();

            if (origen.isEmpty() || destino.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Debes ingresar origen y destino",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String[] ruta = controlador.rutaMasCorta(origen, destino);

                if (ruta == null) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "No existe una ruta entre " + origen + " y " + destino,
                        "Sin ruta",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                panelGrafo.resaltarCamino(ruta); // Resaltar el camino

                // Construir mensaje
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("Ruta encontrada \n\n");
                mensaje.append("Costo total: ").append(ruta[0]).append("\n\n");
                mensaje.append("Camino: ");

                for (int i = 1; i < ruta.length; i++) {
                    mensaje.append(ruta[i]);
                    if (i < ruta.length - 1) {
                        mensaje.append(" → ");
                    }
                }

                javax.swing.JOptionPane.showMessageDialog(this,
                    mensaje.toString(),
                    "Ruta más Corta",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRutaActionPerformed

    private void btnHubsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHubsActionPerformed
        // Verificar que hay datos
        if (controlador.numProteínas() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No hay proteínas cargadas. Carga un archivo primero.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String[] hubs = controlador.identificarHubs();
            String masEsencial = controlador.proteinaMasEsencial();

            if (hubs.length == 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontraron hubs.",
                    "Resultado",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            panelGrafo.resaltarHubs(hubs); // Resaltar los hubs

            // Construir mensaje
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("Proteínas más importantes \n\n");

            for (int i = 0; i < Math.min(hubs.length, 10); i++) {
                mensaje.append(i+1).append(". ").append(hubs[i]);

                if (hubs[i].equals(masEsencial)) {
                    mensaje.append("  (Más importante)");
                }
                mensaje.append("\n");
            }

            // Mostrar en ventana con scroll
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(mensaje.toString());
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));

            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

            javax.swing.JOptionPane.showMessageDialog(this,
                scrollPane,
                "Hubs - Proteínas más importantes",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // Actualizar etiqueta inferior
            lblInfo.setText("Proteínas: " + controlador.numProteínas() + 
                           " | Hub principal: " + masEsencial);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHubsActionPerformed

    private void btnComplejosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComplejosActionPerformed
        // Verificar que hay datos
        if (controlador.numProteínas() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No hay proteínas cargadas. Carga un archivo primero.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String[][] complejos = controlador.detectarComplejos();

            if (complejos.length == 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontraron complejos proteicos.",
                    "Resultado",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Construir mensaje
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("COMPLEJOS PROTEICOS ENCONTRADOS:\n\n");

            for (int i = 0; i < complejos.length; i++) {
                mensaje.append("Complejo ").append(i+1).append(": ");
                for (int j = 0; j < complejos[i].length; j++) {
                    mensaje.append(complejos[i][j]);
                    if (j < complejos[i].length - 1) {
                        mensaje.append(" → ");
                    }
                }
                mensaje.append(" (").append(complejos[i].length).append(" proteínas)\n");
            }

            // Mostrar en ventana
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(mensaje.toString());
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));

            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));

            javax.swing.JOptionPane.showMessageDialog(this,
                scrollPane,
                "Complejos Proteicos",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            panelGrafo.resaltarComplejos(complejos);
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnComplejosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
        this,                                  
        "¿Estás seguro que deseas salir?",      
        "Confirmar salida",                      
        javax.swing.JOptionPane.YES_NO_OPTION,  
        javax.swing.JOptionPane.QUESTION_MESSAGE 
        );
        // Si el usuario dice "Sí"
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra el programa completamente
        }
        // Sino, no hace nada
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarConexionActionPerformed
        // Verificar que hay proteínas cargadas
        if (controlador.numProteínas() < 2) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Se necesitan al menos 2 proteínas para crear una conexión.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear panel para los datos de la conexión
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(3, 2, 10, 10));

        panel.add(new javax.swing.JLabel("Proteína Origen:"));
        javax.swing.JTextField txtOrigen = new javax.swing.JTextField();
        panel.add(txtOrigen);

        panel.add(new javax.swing.JLabel("Proteína Destino:"));
        javax.swing.JTextField txtDestino = new javax.swing.JTextField();
        panel.add(txtDestino);

        panel.add(new javax.swing.JLabel("Peso de la conexión:"));
        javax.swing.JTextField txtPeso = new javax.swing.JTextField("10");
        panel.add(txtPeso);

        int resultado = javax.swing.JOptionPane.showConfirmDialog(this, panel,
            "Agregar nueva conexión",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (resultado == javax.swing.JOptionPane.OK_OPTION) {
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String pesoTexto = txtPeso.getText().trim();

            // Verificar que origen y destino no estén vacíos
            if (origen.isEmpty() || destino.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Debes ingresar origen y destino",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que no sean la misma proteína
            if (origen.equals(destino)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El origen y destino no pueden ser la misma proteína",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el peso sea un número entero
            int peso;
            try {
                peso = Integer.parseInt(pesoTexto);
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El peso debe ser un número entero",
                    "Error de formato",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el peso sea positivo
            if (peso <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El peso debe ser un número positivo mayor que cero",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que la proteína origen exista
            if (controlador.getGrafo().buscarVertice(origen) == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "La proteína " + origen + " no existe",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que la proteína destino exista
            if (controlador.getGrafo().buscarVertice(destino) == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "La proteína " + destino + " no existe",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si la conexión ya existe
            if (controlador.existeConexion(origen, destino)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Ya existe una conexión entre " + origen + " y " + destino,
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Agregar la conexión
            try {
                controlador.getGrafo().insertarArista(origen, destino, peso);

                // Actualizar interfaz
                String[] estadisticas = controlador.obtenerEstadisticas();
                int conexiones = controlador.getNumConexiones();
                lblInfo.setText("Proteínas: " + estadisticas[0] + 
                               " | Hub principal: " + (estadisticas[1] != null ? estadisticas[1] : "---") + 
                               " | Conexiones: " + conexiones);

                panelGrafo.actualizarGrafo(controlador.getGrafo());

                javax.swing.JOptionPane.showMessageDialog(this,
                    "Conexión agregada: " + origen + " ↔ " + destino + " (peso: " + peso + ")",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al agregar conexión: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarConexionActionPerformed

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
    private javax.swing.JButton btnAgregarConexion;
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
