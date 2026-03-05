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
        
        // Usando Merge Sort para ordenar para ordenar de mayor a menor
        
        mergeSort(hubsTemp, 0, cantidadProteinas - 1);
        
        // Crear arreglo solo con los IDs ordenados
        String[] hubs = new String[cantidadProteinas];
        for (int i = 0; i < cantidadProteinas; i++) {
            hubs[i] = hubsTemp[i][0];
        }
        
        return hubs;
    }
    
    /**
     * Merge Sort
     * 
     * @param arr Arreglo a ordenar
     * @param izquierda Índice inicial
     * @param derecha Índice final
     */
    private void mergeSort(String[][] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            
            // Dividir el arreglo en dos mitades
            mergeSort(arr, izquierda, medio);
            mergeSort(arr, medio + 1, derecha);
            
            // Combinar las mitades ordenadas
            merge(arr, izquierda, medio, derecha);
        }
    }
    
    /**
     * Combinar las dos mitades ordenadas (de mayor a menor)
     * 
     * @param arr Arreglo completo
     * @param izquierda Inicio de la primera mitad
     * @param medio Fin de la primera mitad
     * @param derecha Fin de la segunda mitad
     */
    private void merge(String[][] arr, int izquierda, int medio, int derecha) {
        // Tamaños de las sublistas
        int tamañoIzq = medio - izquierda + 1;
        int tamañoDer = derecha - medio;
        
        // Arreglos temporales
        String[][] izquierdaTemp = new String[tamañoIzq][2];
        String[][] derechaTemp = new String[tamañoDer][2];
        
        // Copiar datos a los arreglos temporales
        for (int i = 0; i < tamañoIzq; i++) {
            izquierdaTemp[i][0] = arr[izquierda + i][0];
            izquierdaTemp[i][1] = arr[izquierda + i][1];
        }
        for (int j = 0; j < tamañoDer; j++) {
            derechaTemp[j][0] = arr[medio + 1 + j][0];
            derechaTemp[j][1] = arr[medio + 1 + j][1];
        }
        
        // Mezclar 
        int i = 0, j = 0, k = izquierda;
        while (i < tamañoIzq && j < tamañoDer) {
            int gradoIzq = Integer.parseInt(izquierdaTemp[i][1]);
            int gradoDer = Integer.parseInt(derechaTemp[j][1]);
            
            // Orden descendente (mayor a menor)
            if (gradoIzq >= gradoDer) {
                arr[k][0] = izquierdaTemp[i][0];
                arr[k][1] = izquierdaTemp[i][1];
                i++;
            } else {
                arr[k][0] = derechaTemp[j][0];
                arr[k][1] = derechaTemp[j][1];
                j++;
            }
            k++;
        }
        
        // Copiar elementos restantes de la izquierda
        while (i < tamañoIzq) {
            arr[k][0] = izquierdaTemp[i][0];
            arr[k][1] = izquierdaTemp[i][1];
            i++;
            k++;
        }
        
        // Copiar elementos restantes de la derecha
        while (j < tamañoDer) {
            arr[k][0] = derechaTemp[j][0];
            arr[k][1] = derechaTemp[j][1];
            j++;
            k++;
        }
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
