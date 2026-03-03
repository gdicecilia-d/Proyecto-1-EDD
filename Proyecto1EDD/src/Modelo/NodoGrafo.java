package Modelo;

/**
 * Proposito: Representar UNA proteina en la red de Interaccion Proteina-Proteina (PPI).
 * Cada proteina es un vertice del grafo.
 * 
 * @author Elohym
 * @author Grazia
 */

public class NodoGrafo {
    private String id;
    private Arista[] aristas;
    private int numAristas;
    private static final int CAPACIDAD_INICIAL = 10;
    
    private boolean activa;
    private String color;
    private int posX, posY;
    
    /**
     * Constructor de la clase NodoGrafo
     * @param id Identificador unico de la proteina (ej: "P1", "P2")
     */
    
    public NodoGrafo(String id){
        this.id = id;
        this.aristas = new Arista[CAPACIDAD_INICIAL];
        this.numAristas = 0;
        this.activa = true;
        this.color = "purple";
        this.posX = 0;
        this.posY = 0;
    }
    
    /**
     * Obtiene el ID de la proteina.
     * @return id de la proteina
     */
    public String getId() {
        return id;
    }
    
    /**
     * Obtiene todas las aristas (conexiones) de esta proteina.
     * @return arreglo con las aristas
     */
    public Arista[] getAristas() {
        Arista[] copia = new Arista[numAristas];
        for (int i = 0; i < numAristas; i++) {
            copia[i] = aristas[i];
        }
        return copia;
    }
    
     /**
     * Metodo para agregar una nueva conexion.
     * @param destino ID de la proteina destino
     * @param peso Peso de la interaccion (como int para facilitar almacenamiento)
     * @return true si se agrego correctamente
     */
    
    public boolean agregarConexion(String destino, int peso){
        if (existeConexion(destino)){
            return false;
        }
        if (numAristas >= aristas.length){
            expandirArreglo();
        }
        
        aristas[numAristas] = new Arista(destino, peso);
        numAristas++;
        
        return true;
    }
    
    /**
     * Metodo para quitar una conexion.
     * @param destino ID de la proteina destino a eliminar
     * @return true si se elimino
     */
    public boolean quitarConexion(String destino) {
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].tieneDestino(destino)) {
                for (int j = i; j < numAristas - 1; j++) {
                    aristas[j] = aristas[j + 1];
                }
                aristas[numAristas - 1] = null;
                numAristas--;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si existe una conexion con un destino especifico.
     * @param destino ID de la proteina destino
     * @return true si existe la conexion
     */
    public boolean existeConexion(String destino) {
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].tieneDestino(destino)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene el peso de una conexion especifica.
     * @param destino ID de la proteina destino
     * @return peso, o -1 si no existe
     */
    public int getPesoConexion(String destino) {
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].tieneDestino(destino)) {
                return aristas[i].getPeso();
            }
        }
        return -1;
    }
    
    /**
     * Obtiene una arista especifica por destino.
     * @param destino ID de la proteina destino
     * @return objeto Arista o null si no existe
     */
    public Arista getArista(String destino) {
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].tieneDestino(destino)) {
                return aristas[i];
            }
        }
        return null;
    }
    
    /**
     * Actualiza el peso de una conexion existente.
     * @param destino ID de la proteina destino
     * @param nuevoPeso Nuevo peso
     * @return true si se actualizo
     */
    public boolean actualizarPeso(String destino, int nuevoPeso) {
        Arista arista = getArista(destino);
        if (arista != null) {
            arista.setPeso(nuevoPeso);
            return true;
        }
        return false;
    }
    
    /**
     * Activa o desactiva una conexion especifica.
     * @param destino ID de la proteina destino
     * @param activa true para activar, false para desactivar
     * @return true si se encontro la conexion
     */
    public boolean setEstadoConexion(String destino, boolean activa) {
        Arista arista = getArista(destino);
        if (arista != null) {
            arista.setActiva(activa);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene el numero de conexiones activas.
     * @return cantidad de aristas activas
     */
    public int getNumConexionesActivas() {
        int count = 0;
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].isActiva()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Obtiene el numero total de conexiones.
     * @return cantidad de aristas
     */
    public int getNumConexiones() {
        return numAristas;
    }
    
    /**
     * Expande la capacidad del arreglo de aristas.
     */
    private void expandirArreglo() {
        int nuevaCapacidad = aristas.length * 2;
        Arista[] nuevoArreglo = new Arista[nuevaCapacidad];
        
        for (int i = 0; i < aristas.length; i++) {
            nuevoArreglo[i] = aristas[i];
        }
        
        aristas = nuevoArreglo;
    }
    
    /**
     * Activa o desactiva la proteina.
     * @param activa true para activa, false para inactiva
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
        this.color = activa ? "purple" : "pink";
    }
    
    /**
     * Verifica si la proteina está activa.
     * @return true si está activa
     */
    public boolean isActiva() {
        return activa;
    }
    
    /**
     * Asigna un color para la visualización.
     * @param color Color en formato GraphStream
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Obtiene el color para visualización.
     * @return color actual
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Asigna posicion para la visualizacion.
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void setPosicion(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    
    /**
     * Obtiene coordenada X.
     * @return posicion X
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * Obtiene coordenada Y.
     * @return posicion Y
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Compara si dos nodos son iguales por su ID.
     * @param otro Nodo a comparar
     * @return true si tienen el mismo ID
     */
    public boolean esIgual(NodoGrafo otro) {
        if (otro == null) return false;
        return this.id.equals(otro.id);
    }
    
    /**
     * Representación en String del nodo.
     * @return ID de la proteína
     */
    
    @Override
    public String toString() {
        return id + (activa ? "" : " [INACTIVA]");
    }
    
    /**
     * Obtiene una representación detallada con todas sus conexiones.
     * @return String con ID y todas sus aristas
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proteína ").append(id).append(" (").append(numAristas).append(" conexiones):\n");
        
        if (numAristas == 0) {
            sb.append("  Sin conexiones\n");
        } else {
            for (int i = 0; i < numAristas; i++) {
                sb.append("  ").append(aristas[i].toString()).append("\n");
            }
        }
        
        return sb.toString();
    }

}
