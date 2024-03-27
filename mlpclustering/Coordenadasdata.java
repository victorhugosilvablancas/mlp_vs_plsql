package hugosql.mlpclustering;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Queretaro Autonomus University
 * @school Informatics Faculty
 * @course 2024 Computer Science Doctorate
 */
public class Coordenadasdata {
    public Double x;
    public Double y;
    public Double z;
    
    public Coordenadasdata() {
        x=0.0;
        y=0.0;
        z=0.0;
    }
    /**
     * Inicializa las coordenadas sólo en el plano X
     * @param aequis 
     */
    public Coordenadasdata(String aequis) {
        x=Double.valueOf(aequis);
        y=0.0;
        z=0.0;
    }
    public String cadena() {
        return String.format("(%.4f,%.4f,%.4f)", x,y,z);
    }
    
}
