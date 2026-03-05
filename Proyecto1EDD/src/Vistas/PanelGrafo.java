package Vistas;
import Modelo.Grafo;
import Modelo.NodoGrafo;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

/**
 * Propósito: Panel para visualizar el grafo de proteínas usando GraphStream
 * 
 * @author grazia
 * @author Elohym
 */
public class PanelGrafo extends JPanel {
    
    private Graph graphStream;        // Grafo de GraphStream
    private ViewPanel viewPanel;      // Panel de visualización
    private Viewer viewer;            // Visor de GraphStream
    
    /**
     * Constructor del panel
     */
    public PanelGrafo() {
        // Configuración de GraphStream para usar Swing
        System.setProperty("org.graphstream.ui", "swing");
        
        // Crear un grafo vacío de GraphStream
        graphStream = new SingleGraph("BioGraph");
        
        // Configuración del estilo 
        String estilo = 
            "node { " +
            "   fill-color: #6A3FB5;" +        
            "   size: 40px;" +                  
            "   text-size: 18;" +                
            "   text-color: white;" +            
            "   text-style: bold;" +
            "   text-alignment: center;" +       
            "}" +
            "node.hub { " +                      
            "   fill-color: #E83F6F;" +         
            "   size: 45px;" +                   
            "}" +
            "edge { " +
            "   fill-color: #666;" +            
            "   size: 3px;" +                     
            "   text-size: 20;" +                 
            "   text-color: black;" +
            "   text-background-mode: plain;" + 
            "   text-background-color: white;" +
            "   text-background-padding: 4px;" +  
            "   text-alignment: along;" +       
            "}";
        
        graphStream.setAttribute("ui.stylesheet", estilo);
        
        // Crear el visor
        viewer = new SwingViewer(graphStream, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout(); 
        graphStream.setAttribute("layout.force", 0.5);     // Fuerza de separación
        graphStream.setAttribute("layout.quality", 1.0);   
        
        // Obtener el panel de visualización
        viewPanel = (ViewPanel) viewer.addDefaultView(false);
        
        // Configuración de este JPanel
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        
        // Dar tamaño al panel
        setPreferredSize(new java.awt.Dimension(600, 350));
        setMinimumSize(new java.awt.Dimension(300, 200));
    }
    
    /**
     * Actualización de la visualización con los datos del modelo
     * @param grafoModelo El grafo de proteínas 
     */
    public void actualizarGrafo(Grafo grafoModelo) {
        // Limpiar el grafo actual
        graphStream.clear();
        
        // Si no hay datos, salir
        if (grafoModelo == null || grafoModelo.numVertices() == 0) {
            return;
        }
        
        // Agregar los nodos
        NodoGrafo[] todosNodos = grafoModelo.getTodosNodos();
        for (int i = 0; i < todosNodos.length; i++) {
            NodoGrafo nodo = todosNodos[i];
            String id = nodo.getId();
            
            // Crear nodo en GraphStream
            Node node = graphStream.addNode(id);
            node.setAttribute("ui.label", id);
        }
        
        // Agregar las arístas
        String[][] todasAristas = grafoModelo.getTodasAristas();
        for (int i = 0; i < todasAristas.length; i++) {
            String origen = todasAristas[i][0];
            String destino = todasAristas[i][1];
            String peso = todasAristas[i][2];
            
            String idArista = origen + "-" + destino;
            
            // Evitar duplicados
            if (graphStream.getEdge(idArista) == null) {
                org.graphstream.graph.Edge edge = graphStream.addEdge(idArista, origen, destino, false);
                edge.setAttribute("ui.label", peso); // Mostrar el peso
            }
        }
        
        // Aplicar estilo de nuevo
        String estilo = 
            "node { " +
            "   fill-color: #6A3FB5;" +        
            "   size: 40px;" +                  
            "   text-size: 18;" +                
            "   text-color: white;" +            
            "   text-style: bold;" +
            "   text-alignment: center;" +       
            "}" +
            "node.hub { " +                      
            "   fill-color: #E83F6F;" +         
            "   size: 45px;" +                   
            "}" +
            "edge { " +
            "   fill-color: #666;" +            
            "   size: 3px;" +                     
            "   text-size: 20;" +                 
            "   text-color: black;" +
            "   text-background-mode: plain;" + 
            "   text-background-color: white;" +
            "   text-background-padding: 4px;" +  
            "   text-alignment: along;" +       
            "}";
    
        graphStream.setAttribute("ui.stylesheet", estilo);
    }
    
    /**
     * Resaltar los hubs en el grafo
     * @param hubs Arreglo con los IDs de los hubs en orden de importancia
     */
    public void resaltarHubs(String[] hubs) {
        if (hubs == null || hubs.length == 0) {
            return;
        }
        
        // Restaurar todos los nodos a su estilo normal
        for (Node node : graphStream) {
            node.removeAttribute("ui.class");
        }
        
        // Resaltar los hubs
        int numHubs = Math.min(hubs.length, 5); // Mostrar máx 5 hubs
        for (int i = 0; i < numHubs; i++) {
            Node node = graphStream.getNode(hubs[i]);
            if (node != null) {
                node.setAttribute("ui.class", "hub");
                
                // El hub principal se detalla con una estrella
                if (i == 0) {
                    node.setAttribute("ui.label", hubs[i] + "⭐");
                }
            }
        }
    }
    
    /**
    * Resaltar un camino específico (para lo del Dijkstra)
    * @param camino Arreglo con [costo, proteina1, proteina2, ...]
    */
    public void resaltarCamino(String[] camino) {
        if (camino == null || camino.length < 2) {
            return;
        }

        // Restaurar todos los nodos a su estilo normal
        for (Node node : graphStream) {
            node.removeAttribute("ui.class");
        }

        // Resaltar nodos del camino
        for (int i = 1; i < camino.length; i++) {
            Node node = graphStream.getNode(camino[i]);
            if (node != null) {
                node.setAttribute("ui.class", "hub");
            }
        }

        // Resaltar aristas del camino
        for (int i = 1; i < camino.length - 1; i++) {
            String origen = camino[i];
            String destino = camino[i + 1];

            String idArista1 = origen + "-" + destino;
            String idArista2 = destino + "-" + origen;

            org.graphstream.graph.Edge edge = graphStream.getEdge(idArista1);
            if (edge == null) {
                edge = graphStream.getEdge(idArista2);
            }

            if (edge != null) {
                edge.setAttribute("ui.style", "fill-color: #4A2B6B; size: 3px;");
            }
        }
    }
}