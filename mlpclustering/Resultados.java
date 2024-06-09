package dcc.uaq_mlpclustering;

import java.sql.Timestamp;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Queretaro Autonomus University
 * @school Informatics Faculty
 * @course 2024 Computer Science Doctorate
 */
public class Resultados {
    /**
     * timestamp inicial.
     */
    private Timestamp fini;
    /**
     * timestamp final.
     */
    private Timestamp ffin;
    
    /**
     * Clase que calcula el tiempo consumido por n operación.
     */
    public Resultados() {
    }
    
    /**
     * Inicia el conteo del tiempo.
     */
    public void Inicia() {
        ...
    }
    
    /**
     * Termina el conteo del tiempo.
     */
    public void Termina() {
        ...
    }
    
    /**
     * Imprime el tiempo consumido.
     * 
     * @return tiempo consumido en segundos.
     */
    public String getTiempo() {
        ...
    }
    
}
