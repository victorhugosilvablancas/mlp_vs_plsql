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
        ...
    /**
     * Clase que define los puntos (A,B,C) a partir de una cadena de texto,
     * la distancia al centroide k y el índice de k.
     * 
     * @param renglon cadena de texto que contiene las coordenadas x de cada punto.
     */
    public Datosdata(String renglon) {
        ...
    }
    /**
     * Establece el valor de k.
     * 
     * @param k valor de k.
     */
    public void setK(Integer k) {
        ...
    }
    /**
     * Devuelve el valor de k.
     * 
     * @return valor de k.
     */
    public Integer getK() {
        ...
    }
    /**
     * Devuelve la distancia cuadrada.
     * 
     * @return distancia al cuadrado.
     */
    public Double getDistanciaCuadrada() {
        ...
    }
    /**
     * Devuelve la posición del centroide para (A,B,C).
     * 
     * @return La posición del centroide.
     */
    public Coordenadasdata getCentroide() {
        ...
    }
    /**
     * Devuelve cadena formateada de los valores k y distancia.
     * 
     * @return cadena que indica el clúster k y su distancia.
     */
    public String cadenadistancia() {
        ...
    }
    /**
     * Cabeza de la function cadena() para diseñar la tabla.
     * 
     * @return cadena con las cabezas de tabla separada por tabuladores.
     */
    public static String Cabeza() {
        ...
    }
    /**
     * 
     * Cadena que representa los puntos y el centroide en tres dimensiones 
     * utilizada en el diseño de tablas.
     * 
     * @return cadena con el valor de cada punto y el centroide en sus tres dimensiones.
     */
    public String cadena() {
        ...
    }
    /**
     * 
     * Cadena que representa los puntos y el centroide en tres dimensiones 
     * utilizada en el diseño de tablas en LaTeX.
     * 
     * @return cadena con el valor de cada punto y el centroide sólo con la dimensión x.
     */
    public String cadenaLaTeX() {
        ...
    }
    /**
     * Inicia el conjunto de datos Datosdata.Dataset con n puntos aleatorios.
     * 
     * @param puntos número de registros del dataset.
     */
    public static void IniciaDatasetAleatorio(Integer puntos) {
        ...
    }
    /**
     * Inicia el conjunto de datos Datosdata.Dataset a partir 
     * del archivo CSV rawdata.csv.
     * 
     */
    public static void IniciaDatasetCsv() {
        ...
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
        ...
    }
}
