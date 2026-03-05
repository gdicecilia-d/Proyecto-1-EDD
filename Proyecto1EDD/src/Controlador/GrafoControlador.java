package Controlador;
import Modelo.Grafo;
import Modelo.NodoGrafo;
import Servicios.Archivo;
import Servicios.Recorridos;
import Servicios.Dijkstra;
import Servicios.Análisis;
import java.io.File; // Trabajar con los archivos
import java.io.IOException;

/**
 * Propósito: Esta clase funciona como la union entre la interfaz gráfica (MainVentana)
 * y la lógica del programa 
 * 
 * @author Grazia
 * @author Elohym
 */

public class GrafoControlador {
    /**
    * Referencia al grafo de proteínas
    */
    private Grafo grafo; // grafo de proteínas
    private Archivo archivo;   // manejo de archivos CSV
    private Recorridos recorridos;// BFS 
    private Dijkstra dijkstra;  // ruta más corta
    private Análisis analisis; // cálculo de hubs

    /**
    * Constructor del controlador
    * Comienza el grafo y todos los servicios
    */
    public GrafoControlador() {
        // Creación del grafo para máx 1000 proteínas 
        this.grafo = new Grafo(1000);
        this.archivo = new Archivo();
        this.recorridos = new Recorridos();
        this.dijkstra = new Dijkstra();
        this.analisis = new Análisis();
    }
    
    // Métodos para conectar con Modelo
    
    /**
     * Cargar archivo: Leer un archivo CSV con datos de proteínas
     * @param archivo Archivo seleccionado por el usuario
     * @throws Exception Si hay error al leer
     */
    public void cargarArchivo(File archivo) throws Exception {
        try {
            // Cargar los datos
            Grafo nuevoGrafo = this.archivo.cargarArchivo(archivo);
            
            // Reemplazar el grafo actual con el nuevo
            this.grafo = nuevoGrafo;
          
        } catch (IOException e) {
            throw new Exception("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new Exception("Error en el formato del archivo: " + e.getMessage());
        }
    }
    
    /**
     * Guardar archivo: Escribir los cambios en un archivo CSV
     * @param archivo Archivo donde guardar
     * @throws Exception Si hay error al escribir
     */
    public void guardarArchivo(File archivo) throws Exception {
        try {
            // Verificar que hay datos para guardar
            if (grafo.numVertices() == 0) {
                throw new Exception("No hay proteínas para guardar");
            }   
            // guardar los datos
            this.archivo.guardarArchivo(grafo, archivo);
            
        } catch (IOException e) {
            throw new Exception("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Agregar proteína: Añadir una nueva proteína al grafo
     * @param id Identificar de la proteína 
     * @throws Exception Si ya existe o no hay espacio
     */
    public void agregarProteina(String id) throws Exception {
        // Verificar que el ID no esté vacío
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("El ID de la proteína no puede estar vacío");
        }
        
        boolean resultado = grafo.insertarVertice(id);
        
        if (!resultado) {
            throw new Exception("No se pudo agregar la proteína (O existe o está lleno el grafo)");
        }
    }
    
    /**
     * Eliminar proteína: Quitar una proteína del grafo
     * @param id Identificar la proteína a eliminar
     * @throws Exception Si no existe
     */
    public void eliminarProteina(String id) throws Exception {
        // Verificar que el ID no esté vacío
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("El ID de la proteína no puede estar vacío");
        }
        
        boolean resultado = grafo.eliminarVertice(id);
        
        if (!resultado) {
            throw new Exception("La proteína " + id + " no existe");
        }
    }
    
    /**
     * Detectar los complejos: Encontrar grupos de proteínas conectadas
     * @return Matriz con los complejos 
     */
    public String[][] detectarComplejos() {
         // Verificar que hay proteínas
        if (grafo.numVertices() == 0) {
            return new String[0][0];
        }
        
        String[][] complejos = recorridos.detectarComplejosBFS(grafo);
        return complejos;
    }
    
    /**
     * Ruta más corta: Encontrar el camino de menor costo entre dos proteínas
     * @param origen Proteína de inicio
     * @param destino Proteína de llegada
     * @return Arreglo con [costo, proteína1, proteína2, ...] o null si no hay ruta
     */
    public String[] rutaMasCorta(String origen, String destino) {
        // Verificar entradas
        if (origen == null || destino == null || origen.trim().isEmpty() || destino.trim().isEmpty()) {
            return null;
        }
        
        // Verificar que existan las proteínas
        if (grafo.buscarVertice(origen) == null) {
            return null;
        }
        
        if (grafo.buscarVertice(destino) == null) {
            return null;
        }
        
        String[] resultado = dijkstra.rutaMasCorta(grafo, origen, destino);     
        return resultado;
    }
    
    /**
     * Identificar hubs: Encontrar las proteínas más importantes 
     * @return Arreglo con IDs ordenados de mayor a menor importancia
     */
    public String[] identificarHubs() {
        // Verificar que hay proteínas
        if (grafo.numVertices() == 0) {
            return new String[0];
        }
        
        String[] hubs = analisis.identificarHubs(grafo);
        return hubs;
    }
    
    /**
     * Proteína más escencial: Obtener la de mayor grado
     * @return ID de la proteína más conectada, o null si no hay
     */
    public String proteinaMasEsencial() {
        if (grafo.numVertices() == 0) {
            return null;
        }
        return analisis.proteinaMasEsencial(grafo);
    }
    
    /**
     * Número de proteínas: Obtener el total
     * @return Cantidad de proteínas en el grafo
     */
    public int numProteínas() {
        return grafo.numVertices();
    }
    
    /**
     * Lista de proteínas: Obtener todos los IDs
     * @return Arreglo con los IDs de todas las proteínas
     */
    public String[] getTodasLasProteínas() {
        return grafo.getVertices();
    }
    
    /**
     * Verificar conexión: Comprobar si existe una arista entre dos proteínas
     * @param origen ID origen
     * @param destino ID destino
     * @return true si existe la conexión
     */
    public boolean existeConexion(String origen, String destino) {
        return grafo.existeArista(origen, destino);
    }
    
    /**
     * Obtener peso: Devolver el peso de una conexión
     * @param origen ID origen
     * @param destino ID destino
     * @return El peso o -1 si no existe
     */
    public int getPesoConexion(String origen, String destino) {
        return grafo.getPeso(origen, destino);
    }
    
    /**
     * Obtener estadísticas: Información básica del grafo
     * @return Arreglo con [cantidadProteínas, hubPrincipal]
     */
    public String[] obtenerEstadisticas() {
        String[] estadisticas = new String[2];
        estadisticas[0] = String.valueOf(grafo.numVertices());
        estadisticas[1] = proteinaMasEsencial();
        return estadisticas;
    }
    
    /**
     * Limpiar grafo: Eliminar todas las proteínas
     */
    public void limpiarGrafo() {
        grafo.limpiar();
    }
    
    /**
    * Obtener el grafo actual 
    * @return El grafo de proteínas
    */
    public Grafo getGrafo() {
        return grafo;
    }
    
    /**
    * Obtener número total de conexiones
    * @return Cantidad de aristas
    */
    public int getNumConexiones() {
        return grafo.getTodasAristas().length;
    }
}
