package Modelo;

/**
 * Proposito: Representar una conexion de proteinas.
 * las proteinas se conectan con un peso/costo que representa
 * la resistencia en la transmision de señales
 * 
 * @author Elohym
 * @author Grazia
 * 
 */

public class Arista {
    private String destino;
    private int peso;
    
    private boolean activa;
    
    /**
     * Constructor de la Arista.
     * @param destino Proteina destino a la que se conecta.
     * @param peso Costo/peso de la conexion.
     */
    
    public Arista(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        this.activa = true;
    }
    
    /**
     * Obtiene la proteína destino de esta arista.
     * @return destino de la conexión
     */
    
    public String getDestino() {
        return destino;
    }
    
    /**
     * Obtiene el peso/costo de esta arista.
     * @return peso de la conexión
     */
    
    public int getPeso() {
        return peso;
    }
    
    /**
     * Cambia el destino de la arista.
     * Útil para operaciones de edición en la GUI.
     * @param destino Nuevo destino
     */
    
    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    /**
     * Cambia el peso de la arista.
     * Útil para operaciones de edición en la GUI.
     * @param peso Nuevo peso
     */
    
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    /**
     * Activa o desactiva esta arista.
     * Simula la inhibición de una interacción específica.
     * @param activa true para activa, false para inactiva
     */
    
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    /**
     * Verifica si esta arista está activa.
     * @return true si está activa
     */
    
    public boolean isActiva() {
        return activa;
    }
    
    /**
     * Compara si dos aristas son iguales (mismo destino).
     * @param otra Arista a comparar
     * @return true si tienen el mismo destino
     */
    
    public boolean esIgual(Arista otra) {
        if (otra == null) return false;
        return this.destino.equals(otra.destino);
    }
    
    /**
     * Compara si esta arista apunta a un destino específico.
     * @param destinoDestino Nombre de la proteína a comparar
     * @return true si el destino coincide
     */
    
    public boolean tieneDestino(String destinoDestino) {
        return this.destino.equals(destinoDestino);
    }
    
    /**
     * Representación en String de la arista.
     * @return formato "-> destino (peso)"
     */
    
    public String toString() {
        return "-> " + destino + " (" + peso + ")" + (activa ? "" : " [INACTIVA]");
    }
    
    /**
     * Obtiene una representación detallada de la arista.
     * @return String con toda la información
     */
    
    public String toDetailedString() {
        return "Arista hacia " + destino + " | Peso: " + peso + 
               " | Estado: " + (activa ? "Activa" : "Inactiva");
    }
}

