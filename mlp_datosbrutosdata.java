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
    private static final String adriver="oracle.jdbc.OracleDriver";
    private static final String aurl="jdbc:oracle:thin:@DATAWAREHOUSE:1521:XE";
    private static final String ausuario="MLP_USR";
    private static final String aclave="aBc1D23";

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
        System.out.println("1.Extraer datos brutos");
        try {
            File f=new File(ARCHIVO);
            FileWriter fw=new FileWriter(f);
            PrintWriter pw=new PrintWriter(fw);
            pw.println(mlp_datosbrutosdata.CABEZA);
            pw.println(mlp_datosbrutosdata.TIPOS);
            
            Class.forName(adriver);
            Connection conn = DriverManager.getConnection(aurl,UNKNOWN,UNKNOWN); 
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String atabla;
            String instruccion;
            int contador=0;
            mlp_datosbrutosdata midato=new mlp_datosbrutosdata();
            for (int iperiodo=2012;iperiodo<2023;iperiodo++) {
                for (int itabla=0;itabla<Tablas.length;itabla++) {
                    atabla=Tablas[itabla]+iperiodo;
                    instruccion="select table_name from user_tables where table_name='"+atabla+"' ";
                    Boolean haytabla=false;
                    rs=stmt.executeQuery(instruccion);
                    while (rs.next()) {
                        haytabla=rs.getString(1).length()>0;
                    }
                    if (haytabla) {
                        instruccion="select idmov,idproducto,cantidad from "+atabla+" ";
                        rs=stmt.executeQuery(instruccion);
                        while (rs.next()) {
                            midato=new mlp_datosbrutosdata();
                            midato.periodo=iperiodo;
                            midato.tabla=atabla;
                            midato.idmov=rs.getInt(1);
                            midato.idproducto=rs.getInt(2);
                            midato.cantidad=rs.getDouble(3);

                            pw.println(midato.cadena());

                            contador++;
                            System.out.println(iperiodo+":"+contador);
                        }
                    }
                }
            }
            pw.close();
            fw.close();
            stmt.close();conn.close();
        } catch (Exception e) {
            System.out.println("1.Extraer_datos_brutos: "+e.getLocalizedMessage());
        }
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
        try {
            Class.forName(adriver);
            Connection conn = DriverManager.getConnection(aurl,ausuario,aclave); 
            Statement stmt = conn.createStatement();
            
            System.out.println("3.Crear tabla recolectora");
            String instruccion;
            ResultSet rs;
            
            instruccion="select table_name from user_tables where table_name='"+mlp_datosbrutosdata.TABLA+"' ";
            Boolean haytabla=false;
            rs=stmt.executeQuery(instruccion);
            while (rs.next()) {
                haytabla=rs.getString(1).length()>0;
            }
            if (!haytabla) {
                stmt.executeUpdate(mlp_datosbrutosdata.CREA_TABLA);
                System.out.println("tabla creada");
            } else {
                stmt.executeUpdate(mlp_datosbrutosdata.LIMPIA_TABLA);
                System.out.println("tabla limpiada");
            }
            stmt.close();conn.close();
        } catch (Exception e) {
            System.out.println("3.Crear_tabla_recolectora: "+e.getLocalizedMessage());
        }
    }
    /**
     * 4.leer datos brutos de csv
     */
    public void Leer_datos_brutos() {
        Timestamp fini=new Timestamp(System.currentTimeMillis());
        listadatos=new ArrayList<>();
        try {
            System.out.println("3. Leer datos brutos");
            
            BufferedReader br = new BufferedReader(new FileReader(ARCHIVO));
            String line = br.readLine();
            int irenglon=0;
            
            mlp_datosbrutosdata midato=new mlp_datosbrutosdata();
            while (line != null) {
                if (irenglon>1) {
                    midato=new mlp_datosbrutosdata(line);
                    listadatos.add(midato);
                }
                line = br.readLine();
                irenglon++;
                System.out.print(".");
                if (irenglon % 100 == 0) System.out.println();
            }
            br.close();
            System.out.println("FIN leer datos brutos "+listadatos.size());
        } catch (Exception e) {
            System.out.println("4.Leer_datos_brutos: "+e.getLocalizedMessage());
        }
        Timestamp ffin=new Timestamp(System.currentTimeMillis());

        Long tiempoini=fini.getTime();
        Long tiempofin=ffin.getTime();
        Integer tiempo=tiempofin.intValue()-tiempoini.intValue();
        System.out.println("Resultado: "+tiempo/1000+" segundos");
    }
    /**
     * 5.llenar tabla recolectora
     */
    public void Llenar_tabla_recolectora() {
        Timestamp fini=new Timestamp(System.currentTimeMillis());
        try {
            System.out.println("5.Llenar tabla recolectora");
            
            Class.forName(adriver);
            Connection conn = DriverManager.getConnection(aurl,ausuario,aclave); 
            Statement stmt = conn.createStatement();
            
            mlp_datosbrutosdata midato=new mlp_datosbrutosdata();
            for (int i=0;i<listadatos.size();i++) {
                midato=listadatos.get(i);
                stmt.executeUpdate(midato.inserta());
                System.out.print(".");
                if (i % 100 == 0) System.out.println();
            }
            System.out.println("FIN llenar tabla recolectora");
            stmt.close();conn.close();
        } catch (Exception e) {
            System.out.println("5.Llenar tabla recolectora: "+e.getLocalizedMessage());
        }
        Timestamp ffin=new Timestamp(System.currentTimeMillis());

        Long tiempoini=fini.getTime();
        Long tiempofin=ffin.getTime();
        Integer tiempo=tiempofin.intValue()-tiempoini.intValue();
        System.out.println("Resultado: "+tiempo/1000/60+" minutos");
    }
    public void Consultar_con_PLSQL() {
        try {
            Class.forName(adriver);
            Connection conn = DriverManager.getConnection(aurl,ausuario,aclave); 
            Statement stmt = conn.createStatement();

            System.out.println("6.Consultar con pl/sql (medir tiempo)");
            
            mlp_datosbrutosdata midato=new mlp_datosbrutosdata();
            
            Timestamp fini=new Timestamp(System.currentTimeMillis());
            System.out.println("Inicio: "+fini.toString());
            //1. Consulta a punto de línea
            String instruccion="select * from "+mlp_datosbrutosdata.TABLA+" ";
            ResultSet rs=stmt.executeQuery(instruccion);
            while (rs.next()) {
                midato=new mlp_datosbrutosdata();
                midato.periodo=rs.getInt(1);
                midato.tabla=rs.getString(2);
                midato.idmov=rs.getInt(3);
                midato.idproducto=rs.getInt(4);
                midato.cantidad=rs.getDouble(5);
            }
            Timestamp ffin=new Timestamp(System.currentTimeMillis());
            
            Long tiempoini=fini.getTime();
            Long tiempofin=ffin.getTime();
            Integer tiempo=tiempofin.intValue()-tiempoini.intValue();
            System.out.println("Resultado a punto: "+tiempo+" milésimas de segundo");
            
            //2. Consulta a grupo
            fini=new Timestamp(System.currentTimeMillis());
            instruccion="select periodo,count(*) from "+mlp_datosbrutosdata.TABLA+" group by periodo ";
            rs=stmt.executeQuery(instruccion);
            while (rs.next()) {
                midato=new mlp_datosbrutosdata();
                midato.periodo=rs.getInt(1);
                midato.cuantos=rs.getInt(2);
            }
            ffin=new Timestamp(System.currentTimeMillis());
            
            tiempoini=fini.getTime();
            tiempofin=ffin.getTime();
            tiempo=tiempofin.intValue()-tiempoini.intValue();
            System.out.println("Resultado a grupo: "+tiempo+" milésimas de segundo");
            
            //3. Consulta total
            fini=new Timestamp(System.currentTimeMillis());
            instruccion="select count(*) from "+mlp_datosbrutosdata.TABLA+" ";
            rs=stmt.executeQuery(instruccion);
            while (rs.next()) {
                midato=new mlp_datosbrutosdata();
                midato.cuantos=rs.getInt(1);
                System.out.println(midato.cuantos);
            }
            ffin=new Timestamp(System.currentTimeMillis());
            
            tiempoini=fini.getTime();
            tiempofin=ffin.getTime();
            tiempo=tiempofin.intValue()-tiempoini.intValue();
            System.out.println("Resultado total: "+tiempo+" milésimas de segundo");
            
            stmt.close();conn.close();
        } catch (Exception e) {
            System.out.println("2,3,4 y 5. Leer datos brutos y crear tabla recolectora: "+e.getLocalizedMessage());
        }
    }
    public void Todos() {
        /*
        Extraer_datos_brutos();
        System.out.println(CreaEsquema());
        Crear_tabla_recolectora();
        Leer_datos_brutos();
        Llenar_tabla_recolectora();
        */
        
        Consultar_con_PLSQL();
    }
    
}
