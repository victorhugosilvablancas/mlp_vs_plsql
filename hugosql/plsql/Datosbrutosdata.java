package hugosql.plsql;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Hugo Silva
 */
public class Datosbrutosdata {
    public static final String ARCHIVO_CSV="datosbrutos.csv";
    public static final String COMMA=",";
    public static String[] Tablas=new String[] {
        "NOTAMOV",
        "TICKETMOV",
        "FACTUMOVD",
    };
    
    Integer indice;
    Integer periodo;
    String tabla;
    Integer idmov;
    Integer idproducto;
    Double cantidad;
    //informativo
    Integer cuantos;
    
    public static List<Datosbrutosdata> listadatos=new ArrayList<>();
    
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
            + "indice number(9) primary key,"
            + "periodo number(4) default 0,"
            + "tabla varchar2(20) not null,"
            + "idmov number(9) default 0,"
            + "idproducto number(6) default 0,"
            + "cantidad number(9,2) "
            + ")";
    public static final String LIMPIA_TABLA="delete from "+TABLA+" ";
    
    
    public Datosbrutosdata() {
        indice=0;
        periodo=0;
        tabla="";
        idmov=0;
        idproducto=0;
        cantidad=0.0;
        cuantos=0;
    }
    public Datosbrutosdata(String linea) {
        indice=0;
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
                + "indice,"
                + "periodo"+COMMA
                + "tabla"+COMMA
                + "idmov"+COMMA
                + "idproducto"+COMMA
                + "cantidad "
                + ") values ( "
                + indice+COMMA
                + periodo+COMMA
                + "'"+tabla+"'"+COMMA
                + idmov+COMMA
                + idproducto+COMMA
                + cantidad+" "
                + ")"
                ;
    }

    
    
}