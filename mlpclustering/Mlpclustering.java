package hugosql.mlpclustering;

import hugosql.kevin.Kevin;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Queretaro Autonomus University
 * @school Informatics Faculty
 * @course 2024 Computer Science Doctorate
 */
public class Mlpclustering {

    public static void main(String[] args) {
        Kmeans kme=new Kmeans();
        kme.Hipotesis_K_igual_M();
        kme.Hipotesis_K_igual_I();
        kme.Hipotesis_K_igual_1();
        
    }
}
