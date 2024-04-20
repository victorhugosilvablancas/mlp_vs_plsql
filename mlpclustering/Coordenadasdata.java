package dcc.uaq_mlpclustering;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Coordenadasdata {
    /**
     * Coordenada x del plano cartesiano.
     */
    public Double x;
    /**
     * Coordenada y del plano cartesiano.
     */
    public Double y;
    /**
     * Coordenada z del plano cartesiano.
     */
    public Double z;
   
    /**
     * Clase que define las coordenas (x,y,z).
     * 
     */
    public Coordenadasdata() {
        x=0.0;
        y=0.0;
        z=0.0;
    }
    /**
     * Clase que define las coordenas (x,y,z).
     * 
     * Inicia las coordenadas cuando sólo existe el plano x.
     * 
     * @param aequis valor inicial de x.
     */
    public Coordenadasdata(String aequis) {
        x=Double.valueOf(aequis);
        y=0.0;
        z=0.0;
    }
    /**
     * Devuelve cadena formateada de los valores (x,y,z).
     * 
     * @return cadena con los valores (x,y,z).
     */
    public String cadena() {
        return String.format("(%.4f,%.4f,%.4f)", x,y,z);
    }
    
}
