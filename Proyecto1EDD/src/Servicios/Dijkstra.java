package Servicios;
import Modelo.Grafo;

/**
 * Propósito: usar el algoritmo Dijkstra para encontrar
 * la ruta más corta (de menor costo) entre dos proteínas
 * 
 * @author grazia
 * @author elohym
 */
public class Dijkstra {
    
    /**
     * Ruta más corta: encontrar el camino de menor costo entre dos proteínas
     * 
     * @param grafo El grafo de proteínas
     * @param origen ID de la proteína de inicio
     * @param destino ID de la proteína de llegada
     * @return Un arreglo:
     *         La primera posición es el costo total (como String)
     *         Las siguientes posiciones son los IDs en orden del camino
     *         Retorna null si no hay ruta
     */
    public String[] rutaMasCorta(Grafo grafo, String origen, String destino) {
        
        // Obtener todas las proteínas
        String[] todasLasProteinas = grafo.getVertices();
        int cantidadProteinas = todasLasProteinas.length;
        
        // Buscar índices de origen y de destino
        int indiceOrigen = buscarIndice(todasLasProteinas, origen);
        int indiceDestino = buscarIndice(todasLasProteinas, destino);
        
        // Si alguna no existe, retornar null
        if (indiceOrigen == -1 || indiceDestino == -1) {
            return null;
        }
        
        // Arreglo de distancias (infinito al inicio)
        int[] distancias = new int[cantidadProteinas];
        for (int i = 0; i < cantidadProteinas; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[indiceOrigen] = 0;
        
        // Arreglo de visitados
        boolean[] visitado = new boolean[cantidadProteinas];
        
        // Arreglo de predecesores 
        String[] predecesor = new String[cantidadProteinas];
        for (int i = 0; i < cantidadProteinas; i++) {
            predecesor[i] = null;
        }
        
        // Algoritmo de Dijkstra
        for (int i = 0; i < cantidadProteinas - 1; i++) {
            
            // Encontrar el vértice no visitado con distancia mínima
            int u = -1;
            int minDistancia = Integer.MAX_VALUE;
            
            for (int j = 0; j < cantidadProteinas; j++) {
                if (!visitado[j] && distancias[j] < minDistancia) {
                    minDistancia = distancias[j];
                    u = j;
                }
            }
            
            // Si no se encuentra o no se llega al destino, terminar
            if (u == -1 || u == indiceDestino) {
                break;
            }
            
            // Colocar como visitado
            visitado[u] = true;
            
            // Obtener vecinos del nodo actual
            String[] vecinos = grafo.getAdyacentes(todasLasProteinas[u]);
            
            // Actualizar distancias de los vecinos
            for (int j = 0; j < vecinos.length; j++) {
                String vecino = vecinos[j];
                int indiceVecino = buscarIndice(todasLasProteinas, vecino);
                
                if (!visitado[indiceVecino]) {
                    int peso = grafo.getPeso(todasLasProteinas[u], vecino);
                    int nuevaDistancia = distancias[u] + peso;
                    
                    if (nuevaDistancia < distancias[indiceVecino]) {
                        distancias[indiceVecino] = nuevaDistancia;
                        predecesor[indiceVecino] = todasLasProteinas[u];
                    }
                }
            }
        }
        
        // Si no hay ruta al destino
        if (distancias[indiceDestino] == Integer.MAX_VALUE) {
            return null;
        }
        
        // Reconstruir el camino desde destino hasta origen
        String[] caminoTemp = new String[cantidadProteinas];
        int contador = 0;
        
        String paso = destino;
        while (paso != null) {
            caminoTemp[contador] = paso;
            contador++;
            
            int indicePaso = buscarIndice(todasLasProteinas, paso);
            paso = predecesor[indicePaso];
        }
        
        // Invertir el camino para que quede de origen a destino
        String[] camino = new String[contador];
        for (int i = 0; i < contador; i++) {
            camino[i] = caminoTemp[contador - 1 - i];
        }
        
        // Crear el resultado
        String[] resultado = new String[camino.length + 1];
        resultado[0] = String.valueOf(distancias[indiceDestino]);
        
        for (int i = 0; i < camino.length; i++) {
            resultado[i + 1] = camino[i];
        }
        
        return resultado;
    }
    
    /**
     * Buscar el índice de una proteína en el arreglo de IDs.
     * 
     * @param ids Arreglo con todos los IDs
     * @param id ID a buscar
     * @return El índice o -1 si no existe
     */
    private int buscarIndice(String[] ids, String id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
