package dcc.uaq_mlpclustering;

import java.util.Random;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @institution Universidad Autónoma de Querétaro, México
 * @school Facultad de Informática
 * @course 2024 Doctorado en Ciencias de la Computación
 */
public class Kmeans {
    /**
     * Define el arreglo estático de clústers.
     */
    private static Datosdata[] Cluster;
    /**
     * Define el número de épocas para entrenamiento.
     */
    private static int Epocas=100;
    /**
     * Determina si (true) los datos serán tomados desde un archivo CSV
     * o (false) serán aleatorios.
     */
    public static boolean DesdeCsv=false;
    /**
     * Determina si (true) los datos serán exportados en formato LaTeX
     * o (false) en formato TAB para hoja de cálculo.
     */
    private static boolean ExportarLaTeX=false;
    /**
     * Determina si (true) los datos serán impresos a la consola.
     */
    public static Boolean Imprime=false;
    
    /**
     * Auxiliar en la generación de valores aleatorios.
     */
    private Random rn=new Random();
    
    /**
     * Clase que realiza el entrenamiento de centroides para la técnica K-means.
     */
    public Kmeans() {
    }
    
    /**
     * Ejecuta el análisis de las 3 hipótesis de la investigación.
     */
    public void Hipotesis_Todas() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTeX=false;
        
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
                
        //Tomando las matrices para MLP
        dcc.mlp.Datasets.X=new Double[Kmeans.Cluster.length][2];
        dcc.mlp.Datasets.Y=new Double[Kmeans.Cluster.length][1];
        dcc.mlp.Datasets.input=new Double[Kmeans.Cluster.length][2];
        for (int j=0;j<Kmeans.Cluster.length;j++) {
            dcc.mlp.Datasets.X[j][0]=Kmeans.Cluster[j].A.x;
            dcc.mlp.Datasets.X[j][1]=Kmeans.Cluster[j].B.x;
            //Datasets.X[j][2]=Kmeans.Cluster[j].C.x;
            dcc.mlp.Datasets.Y[j][0]=Kmeans.Cluster[j].getCentroide().x;
            System.out.println(Kmeans.Cluster[j].cadena());
            
            //predictores aleatorios
            
            dcc.mlp.Datasets.input[j][0]=rn.nextDouble(0, .99);
            dcc.mlp.Datasets.input[j][1]=rn.nextDouble(0, .99);
        }
        //Fin Tomando las matrices para MLP
        
        resultado.Inicia();
        k=1;
        DefinirClusters(k);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_0\t"+resultado.getTiempo());
    }
    /**
     * Ejecuta el análisis de la hipótesis k=m de la investigación.
     */
    public void Hipotesis_K_igual_M() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTeX=false;
        
        Resultados resultado=new Resultados();
        resultado.Inicia();
        IniciarDatos(100000);
        int k=Datosdata.Dataset.size();
        //se hace innecesario el entrenamiento
        //DefinirClusters(k);
        //IniciarClusters();
        //CalcularDistanciaEuclideanaYAsignarCluster();
        //EntrenarCentroides(10);
        resultado.Termina();
        System.out.println("Hipotesis_K_igual_M\t"+resultado.getTiempo());
    }
    /**
     * Ejecuta el análisis de la hipótesis k=i de la investigación.
     */
    public void Hipotesis_K_igual_I() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTeX=false;
        
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
    /**
     * Ejecuta el análisis de la hipótesis k=1 de la investigación.
     */
    public void Hipotesis_K_igual_1() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTeX=false;
        
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
     * Rutina de experimentación de la clase.
     */
    public void Experimentacion() {
        Kmeans.DesdeCsv=true;
        Kmeans.ExportarLaTeX=false;
        
        IniciarDatos(100000);
        DefinirClusters(3);
        IniciarClusters();
        CalcularDistanciaEuclideanaYAsignarCluster();
        EntrenarCentroides(10);
        ElbowMethod();
        
        //PREDECIR
        Datosdata Hy=new Datosdata();
        if (Kmeans.DesdeCsv) {
            Hy.A.x=3.0;
            Hy.B.x=500.0;
            Hy.C.x=200.0;
            Hy=Datosdata.Normaliza(Datosdata.MinimoDato, Datosdata.MaximoDato, Hy);
        } else {
            Hy.A.x=0.03;
            Hy.A.y=0.06;
            Hy.A.z=0.09;
            Hy.B.x=0.04;
            Hy.B.y=0.07;
            Hy.B.z=0.10;
            Hy.C.x=0.05;
            Hy.C.y=0.08;
            Hy.C.z=0.11;
        }
        AsignarCategoria(Hy);
    }
    
    /**
     * 1.Iniciar datos ya sea desde archivo csv o * de forma aleatoria.
     * 
     * @param max máximos registros en caso de ser aleatorios.
     */
    public void IniciarDatos(int max) {
        if (Kmeans.DesdeCsv) Datosdata.IniciaDatasetCsv();
        else Datosdata.IniciaDatasetAleatorio(max);
    }
    /**
     * 2.Definir el número de clústers K.
     * 
     * @param k número de clústers.
     */
    public void DefinirClusters(Integer k) {
        Cluster=new Datosdata[k];
        for (int i=0;i<k;i++) {
            Cluster[i]=new Datosdata();
        }
    }
    /**
     * 3.Inicializar los centroides con valores aleatorios del dataset.
     */
    public void IniciarClusters() {
        int max=Datosdata.Dataset.size();
        
        int idato=0;
        int ianterior=0;
        
        if (Kmeans.ExportarLaTeX) {
            System.out.println("\\begin{table}[ht!]");
            System.out.println("\\begin{spacing}{1.5}");
            System.out.println("\\caption{Clústeres definidos por selección aleatoria a partir del Dataset}\\label{table:mlp_kdefinidos}");
            System.out.println("\\centering");
            System.out.println("\\begin{tabular}{r r r r r r}");
            System.out.println("\\hline");
            System.out.println("\\textbf{k} & \\textbf{Dataset} & \\textbf{A} & \\textbf{B} & \\textbf{C} & \\textbf{Centroide}\\\\");
            System.out.println("\\hline");
            //System.out.println("***Clusters definidos:");
            for (int j=0;j<Cluster.length;j++) {
                //seleccionando un dato que no se repita
                while (idato==ianterior) {
                    idato=rn.nextInt(0, max-1);
                }
                ianterior=idato;

                Cluster[j]=Datosdata.Dataset.get(idato);
                Cluster[j].setK(j);
                System.out.println(j+" & "+idato+" & "+Cluster[j].cadenaLaTeX()+"\\\\");
                System.out.println("\\hline");
            }
            System.out.println("\\end{tabular}");
            System.out.println("\\end{spacing}");
            System.out.println("\\end{table}");
            System.out.println("");
        } else {
            if (Kmeans.Imprime) 
                System.out.println("Clústeres definidos por selección aleatoria a partir del Dataset");
            //System.out.println("***Clusters definidos:");
            for (int j=0;j<Cluster.length;j++) {
                //seleccionando un dato que no se repita
                while (idato==ianterior) {
                    idato=rn.nextInt(0, max-1);
                }
                ianterior=idato;

                Cluster[j]=Datosdata.Dataset.get(idato);
                Cluster[j].setK(j);
                if (Kmeans.Imprime) 
                    System.out.println(j+"\t"+idato+"\t"+Cluster[j].cadena());
            }
            if (Kmeans.Imprime) System.out.println("");
        }
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
            for (int i=0;i<Datosdata.Dataset.size();i++) {
                midato=Datosdata.Dataset.get(i);
                rdistancia=Euclidean.SpaceDistance(Cluster[j].getCentroide(), midato.getCentroide());
                if (midato.distancia>0) {
                    if (rdistancia<midato.distancia) {
                        midato.distancia=rdistancia;
                        midato.setK(j);
                    }
                } else {
                    midato.distancia=rdistancia;
                    midato.setK(j);
                }
                //actualizando
                Datosdata.Dataset.set(i, midato);
                //if (i % 100 == 0) System.out.println(String.format("%d\t%.5f\t%.5f", i,midato.distancia,midato.getDistanciaCuadrada()));
            } //for i
            //System.out.println(j+": "+Cluster[j].getK()+","+Cluster[j].cadena());
        } //for j
    }
    /**
     * 5.Entrenar los clústers al actualizar el valor del centroide,
     * tomando la media de todos los puntos de ese clúster.
     * @param iepocas número de épocas.
     */
    public void EntrenarCentroides(int iepocas) {
        Datosdata midato=new Datosdata();
        Coordenadasdata sumaA=new Coordenadasdata();
        Coordenadasdata sumaB=new Coordenadasdata();
        Coordenadasdata sumaC=new Coordenadasdata();
        
        Epocas=iepocas;
        for (int iepoca=0;iepoca<Epocas;iepoca++) {
            if (Kmeans.Imprime) 
                System.out.println(String.format("Época %d",iepoca));
            Double rdistancia=0.0;
            int m=0;
            //actualizamos centroides
            for (int icluster=0;icluster<Cluster.length;icluster++) {
                sumaA=new Coordenadasdata();
                sumaB=new Coordenadasdata();
                sumaC=new Coordenadasdata();
                m=0;
                for (int i=0;i<Datosdata.Dataset.size();i++) {
                    midato=Datosdata.Dataset.get(i);
                    if (midato.getK().equals(icluster)) {
                        sumaA.x+=midato.A.x;
                        sumaA.y+=midato.A.y;
                        sumaA.z+=midato.A.z;
                        sumaB.x+=midato.B.x;
                        sumaB.y+=midato.B.y;
                        sumaB.z+=midato.B.z;
                        sumaC.x+=midato.C.x;
                        sumaC.y+=midato.C.y;
                        sumaC.z+=midato.C.z;
                        m++;
                    }
                }
                if (m>0) {
                    Cluster[icluster].A.x=sumaA.x/m;
                    Cluster[icluster].A.y=sumaA.y/m;
                    Cluster[icluster].A.z=sumaA.z/m;
                    Cluster[icluster].B.x=sumaB.x/m;
                    Cluster[icluster].B.y=sumaB.y/m;
                    Cluster[icluster].B.z=sumaB.z/m;
                    Cluster[icluster].C.x=sumaC.x/m;
                    Cluster[icluster].C.y=sumaC.y/m;
                    Cluster[icluster].C.z=sumaC.z/m;
                    
                    //recalcular distancia euclidiana Paso5
                    for (int i=0;i<Datosdata.Dataset.size();i++) {
                        midato=Datosdata.Dataset.get(i);
                        if (midato.getK().equals(icluster)) {
                            rdistancia=Euclidean.SpaceDistance(Cluster[icluster].getCentroide(), midato.getCentroide());
                            midato.distancia=rdistancia;
                            //System.out.println("189: "+midato.distancia+","+midato.getDistanciaCuadrada());
                            Datosdata.Dataset.set(i, midato);
                        }
                    }
                }
                if (Kmeans.Imprime) 
                    System.out.println("\t"+icluster+":"+rdistancia);
            } //for icluster
        } //for iepocas
        
        if (Kmeans.ExportarLaTeX) {
            System.out.println("\\begin{table}[ht!]");
            System.out.println("\\begin{spacing}{1.5}");
            System.out.println("\\caption{Clústeres entrenados ("+Epocas+" épocas)}\\label{table:mlp_kentrenados}");
            System.out.println("\\centering");
            System.out.println("\\begin{tabular}{r r r r r}");
            System.out.println("\\hline");
            System.out.println("\\textbf{k} & \\textbf{A} & \\textbf{B} & \\textbf{C} & \\textbf{Centroide}\\\\");
            System.out.println("\\hline");
            for (int j=0;j<Kmeans.Cluster.length;j++) {
                System.out.println(j+" &"+Kmeans.Cluster[j].cadenaLaTeX()+"\\\\");
                System.out.println("\\hline");
            }
            System.out.println("\\end{tabular}");
            System.out.println("\\end{spacing}");
            System.out.println("\\end{table}");
            System.out.println("");
        } else {
            if (Kmeans.Imprime) {
                System.out.println("Clústeres entrenados ("+Epocas+" épocas)");
                for (int j=0;j<Kmeans.Cluster.length;j++) {
                    System.out.println(j+"\t"+Kmeans.Cluster[j].cadenaLaTeX());
                }
                System.out.println("");
            }
        }
    }
    
    /**
     * Prueba de eficiencia a través de método de Elbow.
     */
    public void ElbowMethod() {
        Datosdata midato=new Datosdata();
        Double sdistancia=0.0;
        System.out.println("K-value\tWCSS");
        //System.out.println("idx\tEuclideana\tDisCuadrada");
        //SUMANDO LA DISTANCIA CUADRADA
        sdistancia=0.0;
        for (int i=0;i<Datosdata.Dataset.size();i++) {
            midato=Datosdata.Dataset.get(i);
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
     * @param HipotesisYe hipótesis de y solicitada.
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
        if (Kmeans.ExportarLaTeX) {
            System.out.println("\\begin{table}[ht!]");
            System.out.println("\\begin{spacing}{1.5}");
            System.out.println("\\caption{Predicción de Hy: el dato}\\label{table:mlp_predecir}");
            System.out.println("\\centering");
            System.out.println("\\begin{tabular}{r r r r}");
            System.out.println("\\hline");
            System.out.println("\\textbf{A} & \\textbf{B} & \\textbf{C} & \\textbf{Centroide}\\\\");
            System.out.println("\\hline");
            System.out.println(HipotesisYe.cadenaLaTeX()+"\\\\");
            System.out.println("\\hline");
            System.out.println("\\multicolumn{3}{l}{Pertenece al Clúster} & "+micluster+"\\\\");
            System.out.println("\\hline");
            System.out.println("\\end{tabular}");
            System.out.println("\\end{spacing}");
            System.out.println("\\end{table}");
            System.out.println("");
        } else {
            System.out.println("Predicción de Hy: el dato:");
            System.out.println(HipotesisYe.cadena());
            System.out.println("Pertenece al Clúster: "+micluster);
            System.out.println("");
        }
    }

}

