package Servicios;
import Modelo.Grafo;

/**
 * Propósito: se usa para analizar el grafo y
 * encontrar los hubs (proteínas importantes) en base
 * a los números de conexiones
 * 
 * @author grazia
 * @author elohym
 */
public class Análisis {
    
    /**
     * Identificar hubs: Encontrar las proteínas con más conexiones
     * 
     * Calcular el grado (número de conexiones) de cada proteína
     * y ordenar de mayor a menor
     * 
     * @param grafo El grafo de proteínas
     * @return Un arreglo con los IDs ordenados de mayor a menor importancia
     */
    public String[] identificarHubs(Grafo grafo) {
        
        // Obtener todas las proteínas
        String[] todasLasProteinas = grafo.getVertices();
        int cantidadProteinas = todasLasProteinas.length;
        
        // Arreglo para guardar 
        String[][] hubsTemp = new String[cantidadProteinas][2];
        
        // Calcular el grado de cada proteína
        for (int i = 0; i < cantidadProteinas; i++) {
            String id = todasLasProteinas[i];
            int grado = grafo.gradoVertice(id);
            
            hubsTemp[i][0] = id;
            hubsTemp[i][1] = String.valueOf(grado);
        }
        
        // Ordenar por grado de mayor a menor 
        for (int i = 0; i < cantidadProteinas - 1; i++) {
            for (int j = 0; j < cantidadProteinas - i - 1; j++) {
                
                int gradoActual = Integer.parseInt(hubsTemp[j][1]);
                int gradoSiguiente = Integer.parseInt(hubsTemp[j + 1][1]);
                
                if (gradoActual < gradoSiguiente) {
                    // Intercambiar
                    String[] temp = hubsTemp[j];
                    hubsTemp[j] = hubsTemp[j + 1];
                    hubsTemp[j + 1] = temp;
                }
            }
        }
        
        // Crear arreglo solo con los IDs ordenados
        String[] hubs = new String[cantidadProteinas];
        for (int i = 0; i < cantidadProteinas; i++) {
            hubs[i] = hubsTemp[i][0];
        }
        
        return hubs;
    }
    
    /**
     * Proteína más esencial: Encontrar la de mayor grado
     * 
     * @param grafo El grafo de proteínas
     * @return ID de la proteína con más conexiones, o null si no hay
     */
    public String proteinaMasEsencial(Grafo grafo) {
        
        String[] todasLasProteinas = grafo.getVertices();
        int cantidadProteinas = todasLasProteinas.length;
        
        if (cantidadProteinas == 0) {
            return null;
        }
        
        String masEsencial = todasLasProteinas[0];
        int maxGrado = grafo.gradoVertice(masEsencial);
        
        for (int i = 1; i < cantidadProteinas; i++) {
            String id = todasLasProteinas[i];
            int grado = grafo.gradoVertice(id);
            
            if (grado > maxGrado) {
                maxGrado = grado;
                masEsencial = id;
            }
        }
        
        return masEsencial;
    }
    
    /**
     * Estadísticas: Obtener información básica del grafo
     * 
     * @param grafo El grafo de proteínas
     * @return Un arreglo con [cantidadProteinas, hubPrincipal]
     */
    public String[] obtenerEstadisticas(Grafo grafo) {
        String[] estadisticas = new String[2];
        
        estadisticas[0] = String.valueOf(grafo.numVertices());
        estadisticas[1] = proteinaMasEsencial(grafo);
        
        return estadisticas;
    }
}
