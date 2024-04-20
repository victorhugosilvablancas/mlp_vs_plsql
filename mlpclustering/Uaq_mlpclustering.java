package dcc.uaq_mlpclustering;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Uaq_mlpclustering {

    public static void main(String[] args) {
        //ejecución de Kmeans
        System.out.println("Kmeans");
        Kmeans.Imprime=false;
        Kmeans kme=new Kmeans();
        kme.Hipotesis_Todas();
        
        //ejecución de Mlp con los resultados obtenidos de Kmeans:
        //mlp.Datasets.X,
        //mlp.Datasets.Y y 
        //mlp.Datasets.input
        System.out.println("Mlp");
        new dcc.mlp.Driver();
    }
}
