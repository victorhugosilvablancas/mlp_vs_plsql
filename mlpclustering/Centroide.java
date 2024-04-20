package dcc.uaq_mlpclustering;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Centroide {
    
    /**
     * Clase que define el valor del centroide.
     */
    public Centroide() {
    }
    /**
     * 
     * Centroide de la matriz X que pertenece al clúster k.
     * 
     * @param X La matriz de datos X.
     * @param ik clúster k (i=type integer).
     * @return El valor del centroide.
     */
    public static Double CentroideDe(Double[][] X,Integer ik) {
        Double centroide=0.0;
        Double rsumaDeXenCk=0.0;
        Integer ipuntostotales=0;
        for (int i=0;i<X.length;i++) {
            if (X[i][1].equals(ik.doubleValue())) {
                rsumaDeXenCk+=X[i][0];
                ipuntostotales++;
            }
        }
        centroide=rsumaDeXenCk/ipuntostotales;
        return centroide;
    }
    
    /**
     * 
     * Suma de Errores Cuadrados de la matriz X que pertenece al clúster k.
     * 
     * @param X La matriz de datos X.
     * @param ik clúster k (i=type integer).
     * @return La suma de errores al cuadrado.
     */
    public static Double SumaDeErroresCuadrados(Double[][] X,Integer ik) {
        Double rsuma=0.0;
        Double error=0.0;
        for (int i=0;i<X.length;i++) {
            if (X[i][1].equals(ik.doubleValue())) {
                error=X[i][0]-CentroideDe(X,ik);
                rsuma+=Math.pow(error, 2);
            }
        }
        return rsuma;
    }
    /**
     * 
     * Centroide del triángulo (A,B,C) para puntos en 3 dimensiones.
     * 
     * @param A coordenada A.
     * @param B coordenada B.
     * @param C coordenada C.
     * @return La posición del centroide del triángulo (A,B,C).
     */
    public static Coordenadasdata CentroideTriangular(Coordenadasdata A,Coordenadasdata B,Coordenadasdata C) {
        Coordenadasdata midato=new Coordenadasdata();
        midato.x=(A.x+B.x+C.x)/3;
        midato.y=(A.y+B.y+C.y)/3;
        midato.z=(A.z+B.z+C.z)/3;
        return midato;
    }
    
    /**
     * 
     * Centroide del triángulo (A,B,C) para puntos en 2 dimensiones.
     * 
     * @param Ax coordenada x del punto A.
     * @param Ay coordenada y del punto A.
     * @param Bx coordenada x del punto B.
     * @param By coordenada y del punto B.
     * @param Cx coordenada x del punto C.
     * @param Cy coordenada y del punto C.
     * @return La posición del centroide del triángulo (A,B,C).
     */
    public static String CentroideTriangular(Double Ax,Double Ay,Double Bx,Double By,Double Cx,Double Cy) {
        Double equis=(Ax+Bx+Cx)/3;
        Double ye=(Ay+By+Cy)/3;
        return String.format("Centroide = (%.4f,%.4f)", equis, ye);
    }
    
}
