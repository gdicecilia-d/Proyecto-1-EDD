package Modelo;

/**
 * Proposito: Representar TODA la red de proteinas.
 * Es la estructura principal del proyecto que contiene todos los nodos
 * y gestiona las operaciones sobre la red PPI.
 * 
 * @author Elohym
 * @author Grazia
 */

public class Grafo {
    private NodoGrafo[] nodos;
    private boolean esDirigido;
    private int maxNodos;
    private int numVertices;
    
    private static final int CAPACIDAD_INICIAL = 1000;
    
    /**
     * Constructor por defecto.
     * Crea un grafo no dirigido con capacidad inicial de 1000 proteinas.
     */
    
    public Grafo() {
        this(CAPACIDAD_INICIAL);
    }
    
    /**
     * Constructor con capacidad personalizada.
     * @param maxNodos Capacidad maxima del grafo
     */
    
    public Grafo(int maxNodos) {
        this.nodos = new NodoGrafo[maxNodos];
        this.esDirigido = false;
        this.maxNodos = maxNodos;
        this.numVertices = 0;
    }
    
    /**
     * Inserta un nuevo vertice (proteina) en el grafo.
     * @param id Identificador de la proteina
     * @return true si se inserto correctamente, false si ya existe o no hay espacio
     */
    public boolean insertarVertice(String id) {
        if (numVertices >= maxNodos) {
            return false;
        }
        
        if (buscarVertice(id) != null) {
            return false;
        }
        
        nodos[numVertices] = new NodoGrafo(id);
        numVertices++;
        return true;
    }
    
    /**
     * Busca un vértice por su ID.
     * @param id Identificador de la proteina a buscar
     * @return El NodoGrafo si existe, null si no
     */
    public NodoGrafo buscarVertice(String id) {
        for (int i = 0; i < numVertices; i++) {
            if (nodos[i].getId().equals(id)) {
                return nodos[i];
            }
        }
        return null;
    }
    
    /**
     * Inserta una nueva arista (interaccion) entre dos proteinas.
     * @param origen ID de la proteina origen
     * @param destino ID de la proteina destino
     * @param peso Peso/costo de la interaccion
     * @return true si se inserto correctamente, false si no existen las proteinas
     */
    public boolean insertarArista(String origen, String destino, int peso) {
        NodoGrafo nodoOrigen = buscarVertice(origen);
        NodoGrafo nodoDestino = buscarVertice(destino);
        
        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }
        
        if (origen.equals(destino)) {
            return false;
        }
        
        boolean insertadoOrigen = nodoOrigen.agregarConexion(destino, peso);
        boolean insertadoDestino = nodoDestino.agregarConexion(origen, peso);
        
        return insertadoOrigen && insertadoDestino;
    }
    
    /**
     * Elimina un vertice y todas sus aristas.
     * @param id ID de la proteina a eliminar
     * @return true si se elimino correctamente, false si no existe
     */
    public boolean eliminarVertice(String id) {
        int indice = buscarIndiceVertice(id);
        if (indice == -1) {
            return false;
        }
        
        for (int i = 0; i < numVertices; i++) {
            if (nodos[i] != null && !nodos[i].getId().equals(id)) {
                nodos[i].quitarConexion(id);
            }
        }
        
        for (int i = indice; i < numVertices - 1; i++) {
            nodos[i] = nodos[i + 1];
        }
        nodos[numVertices - 1] = null;
        numVertices--;
        
        return true;
    }
    
    /**
     * Elimina una arista especifica entre dos proteinas.
     * @param origen ID de la proteina origen
     * @param destino ID de la proteina destino
     * @return true si se elimino correctamente, false si no existe
     */
    public boolean eliminarArista(String origen, String destino) {
        NodoGrafo nodoOrigen = buscarVertice(origen);
        NodoGrafo nodoDestino = buscarVertice(destino);
        
        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }
        
        boolean eliminadoOrigen = nodoOrigen.quitarConexion(destino);
        boolean eliminadoDestino = nodoDestino.quitarConexion(origen);
        
        return eliminadoOrigen && eliminadoDestino;
    }
    
    /**
     * Obtiene los IDs de los nodos adyacentes a un vertice.
     * @param id ID de la proteina
     * @return Arreglo con los IDs de los vecinos
     */
    public String[] getAdyacentes(String id) {
        NodoGrafo nodo = buscarVertice(id);
        if (nodo == null) {
            return new String[0];
        }
        
        Arista[] aristas = nodo.getAristas();
        String[] adyacentes = new String[aristas.length];
        
        for (int i = 0; i < aristas.length; i++) {
            adyacentes[i] = aristas[i].getDestino();
        }
        
        return adyacentes;
    }
    
    /**
     * Obtiene el peso de una arista especifica.
     * @param origen ID de la proteina origen
     * @param destino ID de la proteina destino
     * @return El peso si existe, -1 si no existe
     */
    public int getPeso(String origen, String destino) {
        NodoGrafo nodoOrigen = buscarVertice(origen);
        if (nodoOrigen == null) {
            return -1;
        }
        
        return nodoOrigen.getPesoConexion(destino);
    }
    
    /**
     * Obtiene el grado (numero de conexiones) de un vertice.
     * @param id ID de la proteina
     * @return Numero de conexiones, -1 si no existe
     */
    public int gradoVertice(String id) {
        NodoGrafo nodo = buscarVertice(id);
        if (nodo == null) {
            return -1;
        }
        
        return nodo.getNumConexiones();
    }
    
    /**
     * Obtiene todas las aristas del grafo en formato para guardar.
     * @return Matriz con [origen, destino, peso]
     */
    public String[][] getTodasAristas() {
        int contadorAristas = 0;
        for (int i = 0; i < numVertices; i++) {
            Arista[] aristas = nodos[i].getAristas();
            for (int j = 0; j < aristas.length; j++) {
                if (nodos[i].getId().compareTo(aristas[j].getDestino()) < 0) {
                    contadorAristas++;
                }
            }
        }
        
        String[][] todasAristas = new String[contadorAristas][3];
        int indice = 0;
        
        for (int i = 0; i < numVertices; i++) {
            Arista[] aristas = nodos[i].getAristas();
            for (int j = 0; j < aristas.length; j++) {
                if (nodos[i].getId().compareTo(aristas[j].getDestino()) < 0) {
                    todasAristas[indice][0] = nodos[i].getId();
                    todasAristas[indice][1] = aristas[j].getDestino();
                    todasAristas[indice][2] = String.valueOf(aristas[j].getPeso());
                    indice++;
                }
            }
        }
        
        return todasAristas;
    }
    
    /**
     * Obtiene todos los vertices del grafo.
     * @return Arreglo con los IDs de todas las proteinas
     */
    public String[] getVertices() {
        String[] vertices = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = nodos[i].getId();
        }
        return vertices;
    }
    
    /**
     * Obtiene el numero de vertices en el grafo.
     * @return Cantidad de proteinas
     */
    public int numVertices() {
        return numVertices;
    }
    
    /**
     * Busca el indice de un vertice por su ID.
     * @param id ID de la proteina
     * @return Indice en el arreglo, -1 si no existe
     */
    private int buscarIndiceVertice(String id) {
        for (int i = 0; i < numVertices; i++) {
            if (nodos[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Verifica si el grafo está vacio.
     * @return true si no hay proteínas
     */
    public boolean estaVacio() {
        return numVertices == 0;
    }
    
    /**
     * Verifica si el grafo esta lleno.
     * @return true si alcanzo el maximo de nodos
     */
    public boolean estalleno() {
        return numVertices >= maxNodos;
    }
    
    /**
     * Obtiene el objeto NodoGrafo completo (para acceder a metodos de visualizacion).
     * @param id ID de la proteina
     * @return El objeto NodoGrafo o null
     */
    public NodoGrafo getNodo(String id) {
        return buscarVertice(id);
    }
    
    /**
     * Obtiene todos los objetos NodoGrafo.
     * @return Arreglo con todos los nodos
     */
    public NodoGrafo[] getTodosNodos() {
        NodoGrafo[] copia = new NodoGrafo[numVertices];
        for (int i = 0; i < numVertices; i++) {
            copia[i] = nodos[i];
        }
        return copia;
    }
    
    /**
     * Verifica si existe una arista entre dos proteinas.
     * @param origen ID origen
     * @param destino ID destino
     * @return true si existe la conexion
     */
    public boolean existeArista(String origen, String destino) {
        NodoGrafo nodo = buscarVertice(origen);
        if (nodo == null) return false;
        
        return nodo.existeConexion(destino);
    }
    
    /**
     * Limpia el grafo (elimina todos los vértices).
     */
    public void limpiar() {
        nodos = new NodoGrafo[maxNodos];
        numVertices = 0;
    }
    
    /**
     * Obtiene el maximo de nodos permitidos.
     * @return Capacidad maxima
     */
    public int getMaxNodos() {
        return maxNodos;
    }
    
    /**
     * Obtiene el valor de esDirigido.
     * @return Siempre false para este proyecto
     */
    public boolean getEsDirigido() {
        return esDirigido;
    }
    
    /**
     * Representacion en String del grafo.
     * @return Descripcion del grafo
     */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== GRAFO DE INTERACCIONES PROTEICAS ===\n");
        sb.append("Vértices: ").append(numVertices).append("/").append(maxNodos).append("\n");
        sb.append("Tipo: ").append(esDirigido ? "Dirigido" : "No Dirigido").append("\n\n");
        
        for (int i = 0; i < numVertices; i++) {
            sb.append(nodos[i].toDetailedString());
        }
        
        return sb.toString();
    }
}

