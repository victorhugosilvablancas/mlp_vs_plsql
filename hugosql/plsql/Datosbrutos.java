package hugosql.plsql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Victor Hugo Silva
 */
public class Datosbrutos {
    public Datosbrutosdata data;
    
    private Cluster l=new Cluster();
    
    public Datosbrutos() {
        data=new Datosbrutosdata();
    }
    
    
    /**
     * 1.extraer datos brutos a: mlp_datosbrutos.csv
     */
    public void Extraer_datos_brutos() {
        //code
    }
    /**
     * Instrucción para crear el esquema para los datos en la instancia de SQL.
     * 
     * @return la instrucción
     */
    public String CreaEsquema() {
        return Clusterdata.CREA_PROFILE+"\n"
                + Clusterdata.CREA_TABLESPACE+"\n"
                + Clusterdata.CREA_USER
                ;
    }
    /**
     * 3.crear tabla recolectora
     */
    public boolean Crear_tabla_recolectora() {
        l.ComandoSQL("drop table "+Datosbrutosdata.TABLA);
        return l.ComandoSQL(Datosbrutosdata.CREA_TABLA);
    }
    /**
     * 4.leer datos brutos de csv
     * dejando las dos primeras líneas (campo y tipo)
     */
    public boolean Leer_datos_brutos() {
        boolean hay=false;
        Datosbrutosdata.listadatos=new ArrayList<>();
        Datosbrutosdata midato=new Datosbrutosdata();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Datosbrutosdata.ARCHIVO_CSV));
            String line = br.readLine();
            int idx=-1;
            while (line != null) {
                if (idx>0) {
                    //System.out.println(line);
                    midato=new Datosbrutosdata(line);
                    midato.indice=idx;
                    Datosbrutosdata.listadatos.add(midato);
                }
                line = br.readLine();
                idx++;
            }
            br.close();
            hay=Datosbrutosdata.listadatos.size()>0;
        } catch (Exception e) {
            System.out.println("Leer_datos_brutos: "+e.getLocalizedMessage());
        }
        return hay;
    }
    /**
     * 5.llenar tabla recolectora
     */
    public boolean Llenar_tabla_recolectora() {
        boolean hay=false;
        Datosbrutosdata midato=new Datosbrutosdata();
        try {
            Class.forName(Clusterdata.Driver);
            Connection conn = DriverManager.getConnection(Clusterdata.Url,Clusterdata.Usuario,Clusterdata.Clave);
            Statement stmt = conn.createStatement();
            for (int i=0;i<Datosbrutosdata.listadatos.size();i++) {
                midato=Datosbrutosdata.listadatos.get(i);
                System.out.println(midato.inserta());
                stmt.executeUpdate(midato.inserta());
            }
            stmt.close();conn.close();
            hay=true;
        } catch (Exception e) {
            System.out.println("ComandoSQL: "+e.getLocalizedMessage());
        }
        return hay;
    }
    public void Consultar_con_PLSQL() {
        //code
    }
    public void Metodologia_1_2() {
        Extraer_datos_brutos();
        //preparación
        System.out.println(CreaEsquema());
        //tabla
        Crear_tabla_recolectora();
        //llenado
        if (Leer_datos_brutos()) {
            Llenar_tabla_recolectora();
            System.out.println("Los datos ya están en el esquema.");
            //Consultar_con_PLSQL();
        } else System.out.println("No hay datos brutos.");
    }

}
