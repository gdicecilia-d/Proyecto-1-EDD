package Servicios;
import Modelo.Grafo;
import Modelo.NodoGrafo;

/**
 * Propósito: se implementa el algoritmo de recorrido
 * de grafos para detectar complejos protéicos
 * @author grazia
 * @author elohym
 */
public class Recorridos {
    
    /**
     * Detectar complejos: Encontrar todos los componentes conexos del grafo
     * 
     * Se utiliza BFS para encontrar grupos de proteínas
     * que están conectadas entre sí
     * 
     * @param grafo El grafo de proteínas
     * @return Una matriz donde cada fila es un complejo (lista de IDs)
     */
     public String[][] detectarComplejosBFS(Grafo grafo) {
        
        // Obtener todas las proteínas del grafo
        String[] todasLasProteinas = grafo.getVertices();
        int cantidadProteinas = todasLasProteinas.length;
        
        // Arreglo para ver qué proteínas ya fueron vistas
        boolean[] visitado = new boolean[cantidadProteinas];
        
        // Arreglo temporal para guardar los complejos
        String[][] complejosTemp = new String[cantidadProteinas][];
        int contadorComplejos = 0;
        
        // Recorrer todas las proteínas
        for (int i = 0; i < cantidadProteinas; i++) {
            // Si la proteína no ha sido visitada se encuentra un nuevo complejo
            if (!visitado[i]) { 
                // Realizar BFS desde esta proteína
                String[] complejo = bfs(grafo, todasLasProteinas[i], visitado);
                // Guardar el complejo encontrado
                complejosTemp[contadorComplejos] = complejo;
                contadorComplejos++;
            }
        }
        // Crear la matriz final 
        String[][] complejos = new String[contadorComplejos][];
        for (int i = 0; i < contadorComplejos; i++) {
            complejos[i] = complejosTemp[i];
        }
        
        return complejos;
    }
    
    /**
     * BFS: Es la búsqueda en anchura desde una proteína de inicio
     * 
     * @param grafo El grafo de proteínas
     * @param inicio ID de la proteína donde empezar
     * @param visitado Arreglo de booleanos para marcar visitados
     * @return Un arreglo con los IDs del complejo encontrado
     */
    private String[] bfs(Grafo grafo, String inicio, boolean[] visitado) {
        
        // Obtener todas las proteínas para convertir IDs a índices
        String[] todasLasProteinas = grafo.getVertices();
        
        // Cola para el BFS 
        String[] cola = new String[todasLasProteinas.length];
        int frente = 0;  // Para sacar elementos
        int fin = 0;     // Para agregar elementos
        
        // Arreglo temporal para guardar el complejo
        String[] complejoTemp = new String[todasLasProteinas.length];
        int indiceComplejo = 0;
        
        // Marcar el inicio como visitado y agregarlo a la cola
        int indiceInicio = buscarIndice(todasLasProteinas, inicio);
        visitado[indiceInicio] = true;
        cola[fin] = inicio;
        fin++;
        
        // Mientras la cola no esté vacía
        while (frente < fin) {
            
            // Sacar una proteína de la cola
            String actual = cola[frente];
            frente++;
            
            // Agregarla al complejo
            complejoTemp[indiceComplejo] = actual;
            indiceComplejo++;
            
            // Obtener sus vecinos
            String[] vecinos = grafo.getAdyacentes(actual);
            
            // Recorrer los vecinos
            for (int i = 0; i < vecinos.length; i++) {
                String vecino = vecinos[i];
                int indiceVecino = buscarIndice(todasLasProteinas, vecino);
                // Si no ha sido visitado, agregarlo a la cola
                if (!visitado[indiceVecino]) {
                    visitado[indiceVecino] = true;
                    cola[fin] = vecino;
                    fin++;
                }
            }
        }
        
        // Crear el arreglo final 
        String[] complejo = new String[indiceComplejo];
        for (int i = 0; i < indiceComplejo; i++) {
            complejo[i] = complejoTemp[i];
        }
        
        return complejo;
    }
    
    /**
     * Buscar el índice de una proteína en el arreglo de IDs
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
