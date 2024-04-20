package dcc.uaq_mlpclustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
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
    /**
     * Lista dinámica de datos compuestos de puntos Coordenadasdata(A,B,C).
     */
    public static List<Datosdata> Dataset=new ArrayList<>();
    
    /**
     * Clase que define los puntos (A,B,C), la distancia al centroide k y el índice de k.
     */
    public Datosdata() {
        A=new Coordenadasdata();
        B=new Coordenadasdata();
        C=new Coordenadasdata();
        distancia=0.0;
        k=0;
    }
    /**
     * Clase que define los puntos (A,B,C) a partir de una cadena de texto,
     * la distancia al centroide k y el índice de k.
     * 
     * @param renglon cadena de texto que contiene las coordenadas x de cada punto.
     */
    public Datosdata(String renglon) {
        String[] partes=renglon.split(",");
        A=new Coordenadasdata(partes[0]);
        B=new Coordenadasdata(partes[1]);
        C=new Coordenadasdata(partes[2]);
        distancia=0.0;
        k=0;
    }
    /**
     * Establece el valor de k.
     * 
     * @param k valor de k.
     */
    public void setK(Integer k) {
        this.k=k;
    }
    /**
     * Devuelve el valor de k.
     * 
     * @return valor de k.
     */
    public Integer getK() {
        return k;
    }
    /**
     * Devuelve la distancia cuadrada.
     * 
     * @return distancia al cuadrado.
     */
    public Double getDistanciaCuadrada() {
        return distancia*distancia;
    }
    /**
     * Devuelve la posición del centroide para (A,B,C).
     * 
     * @return La posición del centroide.
     */
    public Coordenadasdata getCentroide() {
        return Centroide.CentroideTriangular(A, B, C);
    }
    /**
     * Devuelve cadena formateada de los valores k y distancia.
     * 
     * @return cadena que indica el clúster k y su distancia.
     */
    public String cadenadistancia() {
        return String.format("k,%d,distancia,%.4f", k,distancia);
    }
    /**
     * Cabeza de la function cadena() para diseñar la tabla.
     * 
     * @return cadena con las cabezas de tabla separada por tabuladores.
     */
    public static String Cabeza() {
        return "A(x,y,x)\t"
                + "B(x,y,z)\t"
                + "C(x,y,z)\t"
                + "Centroide (x,y,z)";
    }
    /**
     * 
     * Cadena que representa los puntos y el centroide en tres dimensiones 
     * utilizada en el diseño de tablas.
     * 
     * @return cadena con el valor de cada punto y el centroide en sus tres dimensiones.
     */
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
    /**
     * 
     * Cadena que representa los puntos y el centroide en tres dimensiones 
     * utilizada en el diseño de tablas en LaTeX.
     * 
     * @return cadena con el valor de cada punto y el centroide sólo con la dimensión x.
     */
    public String cadenaLaTeX() {
        Coordenadasdata cen=getCentroide();
        return String.format("%.4f & "
                + "%.4f & "
                + "%.4f & "
                + "%.4f",
                A.x,
                B.x,
                C.x,
                cen.x
                );
    }
    /**
     * Inicia el conjunto de datos Datosdata.Dataset con n puntos aleatorios.
     * 
     * @param puntos número de registros del dataset.
     */
    public static void IniciaDatasetAleatorio(Integer puntos) {
        Datosdata.Dataset=new ArrayList<>();
        Datosdata midato=new Datosdata();
        Random rn=new Random();
        if (Kmeans.Imprime) 
                System.out.println(Datosdata.Cabeza());
        for (int i=0;i<puntos;i++) {
            midato=new Datosdata();
            midato.A.x=rn.nextDouble(0.001, 0.999);
            midato.A.y=rn.nextDouble(0.001, 0.999);
            midato.A.z=rn.nextDouble(0.001, 0.999);
            
            midato.B.x=rn.nextDouble(0.001, 0.999);
            midato.B.y=rn.nextDouble(0.001, 0.999);
            midato.B.z=rn.nextDouble(0.001, 0.999);
            
            midato.C.x=rn.nextDouble(0.001, 0.999);
            midato.C.y=rn.nextDouble(0.001, 0.999);
            midato.C.z=rn.nextDouble(0.001, 0.999);
            
            Datosdata.Dataset.add(midato);
            
            //System.out.println(i+": "+midato.cadena());
        }
        if (Kmeans.Imprime) 
                System.out.println("***Dataset de "+puntos+" registros");
    }
    /**
     * Inicia el conjunto de datos Datosdata.Dataset a partir 
     * del archivo CSV rawdata.csv.
     * 
     */
    public static void IniciaDatasetCsv() {
        Datosdata.Dataset=new ArrayList<>();
        Datosdata midato=new Datosdata();
        int Mostrar=5;
        try {
            File f=new File("rawdata.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));
            
            String line="";
            Boolean cabeza=true;
            int i=0;
            //String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (cabeza) cabeza=false;
                else {
                    midato=new Datosdata(line);
                    Datosdata.Dataset.add(midato);
                    if (Kmeans.Imprime) {
                        //if (i<Mostrar) System.out.println(i+": "+midato.cadena());
                    }
                    i++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("IniciaDatasetCsv: "+e.getLocalizedMessage());
        }
        //Normalizando
        if (Datosdata.Dataset.size()>0) {
            if (Kmeans.Imprime) 
                System.out.println("***Dataset de "+Datosdata.Dataset.size()+" registros (Normalizado)");
            //x = (x-xmin)/(xmax-xmin)
            //y = (y-ymin)/(ymax-ymin)
            //z = (z-zmin)/(zmax-zmin)
            //1.obteniendo mínimos y máximos
            Datosdata min=new Datosdata();
            min.A.x=999999.0;
            min.A.y=999999.0;
            min.A.z=999999.0;
            min.B.x=999999.0;
            min.B.y=999999.0;
            min.B.z=999999.0;
            min.C.x=999999.0;
            min.C.y=999999.0;
            min.C.z=999999.0;
            
            Datosdata max=new Datosdata();
            for (int i=0;i<Datosdata.Dataset.size();i++) {
                midato=Datosdata.Dataset.get(i);
                if (midato.A.x>max.A.x) max.A.x=midato.A.x;
                if (midato.A.y>max.A.y) max.A.y=midato.A.y;
                if (midato.A.z>max.A.z) max.A.z=midato.A.z;
                if (midato.B.x>max.B.x) max.B.x=midato.B.x;
                if (midato.B.y>max.B.y) max.B.y=midato.B.y;
                if (midato.B.z>max.B.z) max.B.z=midato.B.z;
                if (midato.C.x>max.C.x) max.C.x=midato.C.x;
                if (midato.C.y>max.C.y) max.C.y=midato.C.y;
                if (midato.C.z>max.C.z) max.C.z=midato.C.z;
                
                if (midato.A.x<min.A.x) min.A.x=midato.A.x;
                if (midato.A.y<min.A.y) min.A.y=midato.A.y;
                if (midato.A.z<min.A.z) min.A.z=midato.A.z;
                if (midato.B.x<min.B.x) min.B.x=midato.B.x;
                if (midato.B.y<min.B.y) min.B.y=midato.B.y;
                if (midato.B.z<min.B.z) min.B.z=midato.B.z;
                if (midato.C.x<min.C.x) min.C.x=midato.C.x;
                if (midato.C.y<min.C.y) min.C.y=midato.C.y;
                if (midato.C.z<min.C.z) min.C.z=midato.C.z;
            } //for i
            //2.normalizando de acuerdo a la fórmula
            for (int i=0;i<Datosdata.Dataset.size();i++) {
                midato=Datosdata.Dataset.get(i);
                midato=Normaliza(min,max,midato);
                Datosdata.Dataset.set(i, midato);
                
                if (i<Mostrar) {
                    if (Kmeans.Imprime) System.out.println(i+": "+midato.cadena());
                }
            }
            if (Kmeans.Imprime) 
                System.out.println("...");
        } else System.out.println("***Dataset vacío");
    }
    
    /**
     * Valor mínimo del la lista de datos utilizado durante la normalización.
     */
    public static Datosdata MinimoDato=new Datosdata();
    /**
     * Valor máximo del la lista de datos utilizado durante la normalización.
     */
    public static Datosdata MaximoDato=new Datosdata();
    /**
     * Devuelve el valor de Datosdata(x,y,z) normalizado de acuerdo a:
     * x = (x-xmin)/(xmax-xmin)
     * y = (y-ymin)/(ymax-ymin)
     * z = (z-zmin)/(zmax-zmin)
     * 
     * @param aminimo el dato con los valores mínimos
     * @param amaximo el dato con los valores máximos
     * @param adato el dato que se actualizará
     * @return 
     */
    public static Datosdata Normaliza(Datosdata aminimo, Datosdata amaximo, Datosdata adato) {
        Datosdata normalizado=new Datosdata();
        
        Double xmaxmin=amaximo.A.x-aminimo.A.x;
        Double ymaxmin=amaximo.A.y-aminimo.A.y;
        Double zmaxmin=amaximo.A.z-aminimo.A.z;
        if (xmaxmin!=0) 
            normalizado.A.x=(adato.A.x-aminimo.A.x)/xmaxmin;
        if (ymaxmin!=0) 
            normalizado.A.y=(adato.A.y-aminimo.A.y)/ymaxmin;
        if (zmaxmin!=0) 
            normalizado.A.z=(adato.A.z-aminimo.A.z)/zmaxmin;
        
        xmaxmin=amaximo.B.x-aminimo.B.x;
        ymaxmin=amaximo.B.y-aminimo.B.y;
        zmaxmin=amaximo.B.z-aminimo.B.z;
        if (xmaxmin!=0) 
            normalizado.B.x=(adato.B.x-aminimo.B.x)/xmaxmin;
        if (ymaxmin!=0) 
            normalizado.B.y=(adato.B.y-aminimo.B.y)/ymaxmin;
        if (zmaxmin!=0) 
            normalizado.B.z=(adato.B.z-aminimo.B.z)/zmaxmin;
        
        xmaxmin=amaximo.C.x-aminimo.C.x;
        ymaxmin=amaximo.C.y-aminimo.C.y;
        zmaxmin=amaximo.C.z-aminimo.C.z;
        if (xmaxmin!=0) 
            normalizado.C.x=(adato.C.x-aminimo.C.x)/xmaxmin;
        if (ymaxmin!=0) 
            normalizado.C.y=(adato.C.y-aminimo.C.y)/ymaxmin;
        if (zmaxmin!=0) 
            normalizado.C.z=(adato.C.z-aminimo.C.z)/zmaxmin;
        
        MinimoDato=aminimo;
        MaximoDato=amaximo;
        return normalizado;
    }
}
