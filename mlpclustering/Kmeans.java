package hugosql.mlpclustering;

import hugosql.kevin.Kevin;
import hugosql.monitor.Resultados;
import java.util.Random;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Queretaro Autonomus University
 * @school Informatics Faculty
 * @course 2024 Computer Science Doctorate
 */
public class Kmeans {
    public static Datosdata[] Cluster;
    public static int Epocas=100;
    public static boolean DesdeCsv=false;
    public static boolean ExportarLaTex=false;
        
    private Random rn=new Random();
    
    public Kmeans() {
        
    }
    public void Hipotesis_Todas() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTex=false;
        
        Resultados resultado=new Resultados();
        IniciarDatos(100000);
        resultado.Inicia();
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_M\t"+resultado.getTiempo());
        
        resultado.Inicia();
        int k=8;
        DefinirClusters(k);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_I\t"+resultado.getTiempo());
        
        resultado.Inicia();
        k=1;
        DefinirClusters(k);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_0\t"+resultado.getTiempo());
    }
    public void Hipotesis_K_igual_M() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTex=false;
        
        Resultados resultado=new Resultados();
        resultado.Inicia();
        IniciarDatos(100000);
        int k=Datosdata.dataset.size();
        //se hace innecesario el entrenamiento
        //DefinirClusters(k);
        //IniciarClusters();
        //CalcularDistanciaEuclideanaYAsignarCluster();
        //EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_M\t"+resultado.getTiempo());
    }
    public void Hipotesis_K_igual_I() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTex=false;
        
        Resultados resultado=new Resultados();
        resultado.Inicia();
        IniciarDatos(100000);
        int k=8;
        DefinirClusters(k);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_I\t"+resultado.getTiempo());
    }
    public void Hipotesis_K_igual_1() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTex=false;
        
        Resultados resultado=new Resultados();
        resultado.Inicia();
        IniciarDatos(100000);
        int k=1;
        DefinirClusters(k);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_0\t"+resultado.getTiempo());
    }
    
    /**
     * 1.Iniciar datos ya sea desde archivo csv o
     * de forma aleatoria
     * 
     * @param max máximos registros en caso de ser aleatorios
     */
    public void IniciarDatos(int max) {
        if (Kmeans.DesdeCsv) Datosdata.IniciaDatasetCsv();
        else Datosdata.IniciaDatasetAleatorio(max);
    }
    /**
     * 2.Definir el número de clústers K
     * @param k número de clústers
     */
    public void DefinirClusters(Integer k) {
        Cluster=new Datosdata[k];
        for (int i=0;i<k;i++) {
            Cluster[i]=new Datosdata();
        }
    }
    /**
     * 3.Inicializar los centroides con valores aleatorios del dataset
     */
    public void IniciarClusters() {
        int max=Datosdata.dataset.size();
        
        int idato=0;
        int ianterior=0;
        
            //System.out.println("***Clusters definidos:");
            for (int j=0;j<Cluster.length;j++) {
                //seleccionando un dato que no se repita
                while (idato==ianterior) {
                    idato=rn.nextInt(0, max-1);
                }
                ianterior=idato;

                Cluster[j]=Datosdata.dataset.get(idato);
                Cluster[j].setK(j);
                if (Kevin.Imprime) 
                    System.out.println(j+"\t"+idato+"\t"+Cluster[j].cadena());
            }
	System.out.println("");
        
    }
    /**
     * 4.Calcular la distancia euclideana de cada punto al centroide de clústers.
     * Asignar cada punto al cluster que le es más cercano.
     */
    public void CalcularDistanciaEuclideanaYAsignarCluster() {
        Datosdata midato=new Datosdata();
        Double rdistancia=0.0;
        //System.out.println("idx\tEuclideana\tDisCuadrada");
        for (int j=0;j<Cluster.length;j++) {
            for (int i=0;i<Datosdata.dataset.size();i++) {
                midato=Datosdata.dataset.get(i);
                rdistancia=Euclidean.SpaceDistance(Cluster[j].getCentroide(), midato.getCentroide());
                //actualizando
                Datosdata.dataset.set(i, midato);
            } //for i
        } //for j
    }
    /**
     * 5.Entrenar los clústers al actualizar el valor del centroide,
     * tomando la media de todos los puntos de ese clúster
     * @param iepocas número de épocas
     */
    public void EntrenarCentroides(int iepocas) {
        Datosdata midato=new Datosdata();
        Coordenadasdata sumaA=new Coordenadasdata();
        Coordenadasdata sumaB=new Coordenadasdata();
        Coordenadasdata sumaC=new Coordenadasdata();
        
        Epocas=iepocas;
        for (int iepoca=0;iepoca<Epocas;iepoca++) {
            if (Kevin.Imprime) 
                System.out.println(String.format("Época %d",iepoca));
            Double rdistancia=0.0;
            int m=0;
            //actualizamos centroides
            for (int icluster=0;icluster<Cluster.length;icluster++) {
                sumaA=new Coordenadasdata();
                sumaB=new Coordenadasdata();
                sumaC=new Coordenadasdata();
                m=0;
                for (int i=0;i<Datosdata.dataset.size();i++) {
                    midato=Datosdata.dataset.get(i);
                    if (midato.getK().equals(icluster)) {
                        m++;
                    }
                }
                if (m>0) {
                    
                    //recalcular distancia euclidiana Paso5
                    for (int i=0;i<Datosdata.dataset.size();i++) {
                        midato=Datosdata.dataset.get(i);
                        if (midato.getK().equals(icluster)) {
                            rdistancia=Euclidean.SpaceDistance(Cluster[icluster].getCentroide(), midato.getCentroide());
                            midato.distancia=rdistancia;
                            //System.out.println("189: "+midato.distancia+","+midato.getDistanciaCuadrada());
                            Datosdata.dataset.set(i, midato);
                        }
                    }
                }
                if (Kevin.Imprime) 
                    System.out.println("\t"+icluster+":"+rdistancia);
            } //for icluster
        } //for iepocas
        
    }
    
    /**
     * Prueba de eficiencia a través de método de Elbow
     * https://builtin.com/data-science/elbow-method 
     */
    public void ElbowMethod() {
        Datosdata midato=new Datosdata();
        Double sdistancia=0.0;
        System.out.println("K-value\tWCSS");
        //System.out.println("idx\tEuclideana\tDisCuadrada");
        //SUMANDO LA DISTANCIA CUADRADA
        sdistancia=0.0;
        for (int i=0;i<Datosdata.dataset.size();i++) {
            midato=Datosdata.dataset.get(i);
            sdistancia+=midato.getDistanciaCuadrada();
            //System.out.println(String.format("%d\t%.4f\t%.4f\t%.4f",i+1,midato.distancia,midato.getDistanciaCuadrada(),sdistancia));
        } //for i
        Double wcss=0.0;
        for (int mika=0;mika<Cluster.length;mika++) {
            wcss=sdistancia/(mika+1);
            System.out.println(String.format("%d\t%.6f", mika,wcss));
        } //for j
    }
    
    /**
     * Asigna la categoría de la hipótesis H(y) en el clúster.
     * 
     * @param HipotesisYe hipótesis de y solicitada
     */
    public void AsignarCategoria(Datosdata HipotesisYe) {
        Double maxdistancia=99999.9;
        Double mindistancia=0.0;
        Integer micluster=-1;
        for (int j=0;j<Cluster.length;j++) {
            mindistancia=Euclidean.SpaceDistance(Cluster[j].getCentroide(), HipotesisYe.getCentroide());
            if (mindistancia<maxdistancia) {
                micluster=j;
                maxdistancia=mindistancia; 
            }
        }
    }

}

    