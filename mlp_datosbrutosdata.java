import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Hugo Silva
 */
public class mlp_datosbrutosdata {
    private static final String ARCHIVO="mlp_datosbrutos.csv";
    private static final String COMMA=",";
    private static String[] Tablas=new String[] {
        "NOTAMOV",
        "TICKETMOV",
        "FACTUMOVD",
    };
    
    Integer periodo;
    String tabla;
    Integer idmov;
    Integer idproducto;
    Double cantidad;
    //informativo
    Integer cuantos;
    
    public static final String CABEZA="periodo"+COMMA
            + "tabla"+COMMA
            + "idmov"+COMMA
            + "idproducto"+COMMA
            + "cantidad"
            ;
    public static final String TIPOS="number(4)"+COMMA
            + "varchar2(20)"+COMMA
            + "number(9)"+COMMA
            + "number(6)"+COMMA
            + "number(9,2)"
            ;
    public static final String TABLA="TABLA_RECOLECTORA";
    public static final String CREA_TABLA="create table "+TABLA+" ("
            + "periodo number(4) default 0,"
            + "tabla varchar2(20) not null,"
            + "idmov number(9) default 0,"
            + "idproducto number(6) default 0,"
            + "cantidad number(9,2) "
            + ")";
    public static final String LIMPIA_TABLA="delete from "+TABLA+" ";
    
    public mlp_datosbrutosdata() {
        periodo=0;
        tabla="";
        idmov=0;
        idproducto=0;
        cantidad=0.0;
        cuantos=0;
    }
    public mlp_datosbrutosdata(String linea) {
        String[] columnas=linea.split(COMMA);
        periodo=Integer.valueOf(columnas[0]);
        tabla=columnas[1];
        idmov=Integer.valueOf(columnas[2]);
        idproducto=Integer.valueOf(columnas[3]);
        cantidad=Double.valueOf(columnas[4]);
    }
    public String cadena() {
        return periodo+COMMA
                + tabla+COMMA
                + idmov+COMMA
                + idproducto+COMMA
                + cantidad
                ;
    }
    public String inserta() {
        return "insert into "+TABLA+" ("
                + "periodo"+COMMA
                + "tabla"+COMMA
                + "idmov"+COMMA
                + "idproducto"+COMMA
                + "cantidad "
                + ") values ( "
                + periodo+COMMA
                + "'"+tabla+"'"+COMMA
                + idmov+COMMA
                + idproducto+COMMA
                + cantidad+" "
                + ")"
                ;
    }

    private static List<mlp_datosbrutosdata> listadatos=new ArrayList<>();
    
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
        String instruccion="CREATE PROFILE DVE_DW_PERFIL LIMIT SESSIONS_PER_USER UNLIMITED "
                + "CPU_PER_SESSION UNLIMITED CPU_PER_CALL 3000 CONNECT_TIME 45 "
                + "LOGICAL_READS_PER_SESSION DEFAULT LOGICAL_READS_PER_CALL 1000 "
                + "PRIVATE_SGA 15K COMPOSITE_LIMIT 5000000 PASSWORD_LIFE_TIME UNLIMITED;\n"
                + "CREATE TABLESPACE MLP_DATOSBRUTOS DATAFILE 'C:\\DATAWAREHOUSE\\DATOSBRUTOS.DBF' "
                + "SIZE 600M EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;\n"
                + "CREATE USER MLP_USR IDENTIFIED BY aBc1D23 "
                + "DEFAULT TABLESPACE MLP_DATOSBRUTOS "
                + "QUOTA UNLIMITED ON MLP_DATOSBRUTOS "
                + "TEMPORARY TABLESPACE TEMP QUOTA 5M ON SYSTEM PROFILE DVE_DW_PERFIL ACCOUNT UNLOCK;\n"
                + "GRANT CREATE SESSION TO MLP_USR;\n"
                + "GRANT CREATE ANY INDEX, ALTER ANY INDEX, DROP ANY INDEX TO MLP_USR;\n"
                + "GRANT CREATE SEQUENCE, CREATE ANY SEQUENCE, ALTER ANY SEQUENCE, "
                + "DROP ANY SEQUENCE,SELECT ANY SEQUENCE TO MLP_USR;\n"
                + "GRANT CREATE ANY TABLE, ALTER ANY TABLE, DELETE ANY TABLE, "
                + "DROP ANY TABLE,INSERT ANY TABLE, SELECT ANY TABLE, FLASHBACK ANY TABLE, "
                + "UPDATE ANY TABLE TO MLP_USR;\n"
                + "GRANT CREATE ANY VIEW, DROP ANY VIEW, UNDER ANY VIEW TO MLP_USR;\n"
                + "GRANT CREATE ANY TRIGGER, DROP ANY TRIGGER, UNDER ANY VIEW TO MLP_USR;\n"
                + "GRANT CREATE ANY PROCEDURE, DROP ANY PROCEDURE TO MLP_USR;\n"
                + "GRANT CREATE ANY TYPE, DROP ANY TYPE, ALTER ANY TYPE TO MLP_USR;\n"
                ;
        return instruccion;
    }
    /**
     * 3.crear tabla recolectora
     */
    public void Crear_tabla_recolectora() {
        //code
    }
    /**
     * 4.leer datos brutos de csv
     */
    public void Leer_datos_brutos() {
        //code
    }
    /**
     * 5.llenar tabla recolectora
     */
    public void Llenar_tabla_recolectora() {
        //code
    }
    public void Consultar_con_PLSQL() {
        //code
    }
    public void Todos() {
        Extraer_datos_brutos();
        System.out.println(CreaEsquema());
        Crear_tabla_recolectora();
        Leer_datos_brutos();
        Llenar_tabla_recolectora();
        Consultar_con_PLSQL();
    }
    
}
