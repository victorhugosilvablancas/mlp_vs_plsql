package dcc.uaq_mlpclustering;

import java.util.Random;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Kmeans {
    /**
     * Define el arreglo estático de clústers.
     */
    private static Datosdata[] Cluster;
    /**
     * Define el número de épocas para entrenamiento.
     */
    private static int Epocas=100;
    /**
     * Determina si (true) los datos serán tomados desde un archivo CSV
     * o (false) serán aleatorios.
     */
    public static boolean DesdeCsv=false;
    /**
     * Determina si (true) los datos serán exportados en formato LaTeX
     * o (false) en formato TAB para hoja de cálculo.
     */
    private static boolean ExportarLaTeX=false;
    /**
     * Determina si (true) los datos serán impresos a la consola.
     */
    public static Boolean Imprime=false;
    
    /**
     * Auxiliar en la generación de valores aleatorios.
     */
    private Random rn=new Random();
    
    /**
     * Clase que realiza el entrenamiento de centroides para la técnica K-means.
     */
    public Kmeans() {
    }
    
    /**
     * Ejecuta el análisis de las 3 hipótesis de la investigación.
     */
    public void Hipotesis_Todas() {
        ...
    }
    /**
     * Ejecuta el análisis de la hipótesis k=m de la investigación.
     */
    public void Hipotesis_K_igual_M() {
        ...
    }
    /**
     * Ejecuta el análisis de la hipótesis k=i de la investigación.
     */
    public void Hipotesis_K_igual_I() {
        ...
    }
    /**
     * Ejecuta el análisis de la hipótesis k=1 de la investigación.
     */
    public void Hipotesis_K_igual_1() {
        ...
    }
    /**
     * Rutina de experimentación de la clase.
     */
    public void Experimentacion() {
        ...
    }
    
    /**
     * 1.Iniciar datos ya sea desde archivo csv o * de forma aleatoria.
     * 
     * @param max máximos registros en caso de ser aleatorios.
     */
    public void IniciarDatos(int max) {
        ...
    }
    /**
     * 2.Definir el número de clústers K.
     * 
     * @param k número de clústers.
     */
    public void DefinirClusters(Integer k) {
        ...
    }
    /**
     * 3.Inicializar los centroides con valores aleatorios del dataset.
     */
    public void IniciarClusters() {
        ...
    }
    /**
     * 4.Calcular la distancia euclideana de cada punto al centroide de clústers.
     * Asignar cada punto al cluster que le es más cercano.
     */
    public void CalcularDistanciaEuclideanaYAsignarCluster() {
        ...
    }
    /**
     * 5.Entrenar los clústers al actualizar el valor del centroide,
     * tomando la media de todos los puntos de ese clúster.
     * @param iepocas número de épocas.
     */
    public void EntrenarCentroides(int iepocas) {
        ...
    }
    
    /**
     * Prueba de eficiencia a través de método de Elbow.
     */
    public void ElbowMethod() {
        ...
    }
    
    /**
     * Asigna la categoría de la hipótesis H(y) en el clúster.
     * 
     * @param HipotesisYe hipótesis de y solicitada.
     */
    public void AsignarCategoria(Datosdata HipotesisYe) {
        ...
    }

}

