package hugosql.mlpclustering;

import hugosql.kevin.Kevin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Queretaro Autonomus University
 * @school Informatics Faculty
 * @course 2024 Computer Science Doctorate
 */
public class Datosdata {
    /**
     *  Punto A del tríangulo
     */
    public Coordenadasdata A;
    /**
     *  Punto B del tríangulo
     */
    public Coordenadasdata B;
    /**
     *  Punto C del tríangulo
     */
    public Coordenadasdata C;
    /**
     *  Distancia euclideana
     */
    public Double distancia;
    /**
     * Clúster más cercano
     */
    private Integer k;
    
    public static List<Datosdata> dataset=new ArrayList<>();
    
    public Datosdata() {
        
    }
    public void setK(Integer k) {
        this.k=k;
    }
    public Integer getK() {
        return k;
    }
    public Double getDistanciaCuadrada() {
        return distancia*distancia;
    }
    
    public Coordenadasdata getCentroide() {
        return Centroide.CentroideTriangular(A, B, C);
    }
    public String cadenadistancia() {
        return String.format("k,%d,distancia,%.4f", k,distancia);
    }
    
    public static String Cabeza() {
        return "A(x,y,x)\t"
                + "B(x,y,z)\t"
                + "C(x,y,z)\t"
                + "Centroide (x,y,z)";
    }
    public String cadena() {
        Coordenadasdata cen=getCentroide();
        return String.format("A(%.4f,%.4f,%.4f)\t"
                + "B(%.4f,%.4f,%.4f)\t"
                + "C(%.4f,%.4f,%.4f)\t"
                + "Centroide(%.4f,%.4f,%.4f)",
                A.x,A.y,A.z,
                B.x,B.y,B.z,
                C.x,C.y,C.z,
                cen.x,cen.y,cen.z
                );
    }
    public static void IniciaDatasetAleatorio(Integer puntos) {
        
    }
    public static void IniciaDatasetCsv() {
        
    }
    
    public static Datosdata MinimoDato=new Datosdata();
    public static Datosdata MaximoDato=new Datosdata();
    /**
     * x = (x-xmin)/(xmax-xmin)
     * y = (y-ymin)/(ymax-ymin)
     * z = (z-zmin)/(zmax-zmin)
     * @param aminimo el dato con los valores mínimos
     * @param amaximo el dato con los valores máximos
     * @param adato el dato que se actualizará
     * @return 
     */
    public static Datosdata Normaliza(Datosdata aminimo, Datosdata amaximo, Datosdata adato) {
        Datosdata normalizado=new Datosdata();
        return normalizado;
    }
}
