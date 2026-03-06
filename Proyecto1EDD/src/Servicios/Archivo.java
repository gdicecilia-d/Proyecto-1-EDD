package Servicios;
import Modelo.Grafo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Propósito: leer y guardar archivos CSV con los datos
 * de las proteínas y sus interacciones.
 * 
 * @author grazi
 * @author elohym
 */
public class Archivo {
    
     /**
     * Cargar un archivo: Leer un archivo CSV y crear un grafo con los datos
     * 
     * @param archivo El archivo .csv a leer
     * @return Un objeto Grafo con todas las proteínas y conexiones
     * @throws IOException Si hay error al leer el archivo
     * @throws NumberFormatException Si el peso no es un número válido
     */
    public Grafo cargarArchivo(File archivo) throws IOException, NumberFormatException {
        
        // Crear un grafo vacío para máx 1000 proteínas 
        Grafo grafo = new Grafo(1000);
        
        // Abrir el archivo para leerlo
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        
        String linea;
        
        // Leer cada línea del archivo 
        while ((linea = lector.readLine()) != null) {
            
            // Separar la línea por comas
            String[] partes = linea.split(",");
            
            // Verificar que la línea tenga 3 partes
            if (partes.length == 3) {
                
                // Obtener los datos y eliminar los espacios en blanco
                String origen = partes[0].trim();
                String destino = partes[1].trim();
                int peso = Integer.parseInt(partes[2].trim());
                
                // Insertar las proteínas si no existen
                if (grafo.buscarVertice(origen) == null) {
                    grafo.insertarVertice(origen);
                }
                if (grafo.buscarVertice(destino) == null) {
                    grafo.insertarVertice(destino);
                }
                
                // Insertar la conexión
                grafo.insertarArista(origen, destino, peso);
            }
        }
        
        // Cerrar el archivo
        lector.close();
        return grafo;
    }
    
    /**
     * Guardar un archivo: Escribir el grafo en un archivo CSV.
     * 
     * @param grafo El grafo con las proteínas y conexiones
     * @param archivo El archivo donde guardar
     * @throws IOException Si hay error al escribir
     */
    public void guardarArchivo(Grafo grafo, File archivo) throws IOException {
        
        // Crear el archivo para escribir
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
        
        // Obtener todas las aristas del grafo 
        String[][] aristas = grafo.getTodasAristas();
        
        // Escribir cada arista en una línea
        for (int i = 0; i < aristas.length; i++) {
            String linea = aristas[i][0] + "," + aristas[i][1] + "," + aristas[i][2];
            escritor.write(linea);
            escritor.newLine();
        }
        
        // Cerrar el archivo
        escritor.close();
    }
}
