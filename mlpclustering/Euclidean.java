package dcc.uaq_mlpclustering;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Euclidean {
    
    /**
     * Clase que determina la distancia Euclidiana entre dos puntos y 3 dimensiones.
     */
    public Euclidean() {
    }
    
    /**
     * Calcula la distancia euclideana en tres dimensiones para dos puntos.
     * 
     * @param A Puntosdata a.
     * @param B Puntosdata dataset.
     * @return Distancia euclideana para 3 dimensiones.
     */
    public static Double SpaceDistance(Coordenadasdata A,Coordenadasdata B) {
        Double Equis=(B.x-A.x)*(B.x-A.x);
        Double Ye=(B.y-A.y)*(B.y-A.y);
        Double Zeta=(B.z-A.z)*(B.z-A.z);
        Double AB = Equis+Ye+Zeta;
        Double Dab = Math.sqrt(AB);
        return Dab;
    }
    
    
}
