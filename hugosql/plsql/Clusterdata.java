package hugosql.plsql;

/**
 *
 * @author Victor Hugo Silva
 */
public class Clusterdata {
    public static final String Driver="oracle.jdbc.driver.OracleDriver";
    public static final String DriverOracle="oracle.jdbc.driver.OracleDriver";
    public static String Url ="jdbc:oracle:thin:@DATAWAREHOUSE:1521:XE";
    public static final String Usuario = "USUARIOMLP";
    public static final String Clave = "vTrlnB84uM18";
    
    public Clusterdata() {
    }
    
    public static final String CREA_PROFILE="""
                                           CREATE PROFILE PERFILMLP 
                                            LIMIT SESSIONS_PER_USER UNLIMITED 
                                            CPU_PER_SESSION UNLIMITED 
                                            CPU_PER_CALL 3000 
                                            CONNECT_TIME 45 
                                            LOGICAL_READS_PER_SESSION DEFAULT 
                                            LOGICAL_READS_PER_CALL 1000 
                                            PRIVATE_SGA 15K 
                                            COMPOSITE_LIMIT 5000000;
                                            alter profile PERFILMLP limit password_life_time unlimited;   
                                            """;
    public static final String CREA_TABLESPACE="""
                                            CREATE TABLESPACE ESQUEMAMLP 
                                            DATAFILE 'C:\\DATAWAREHOUSE\\DATOSMLP.DBF' SIZE 200M 
                                            EXTENT MANAGEMENT LOCAL 
                                            SEGMENT SPACE MANAGEMENT AUTO;
                                            """;
    public static final String CREA_USER="""
                                            CREATE USER USUARIOMLP 
                                            IDENTIFIED BY vTrlnB84uM18 
                                            DEFAULT TABLESPACE ESQUEMAMLP  
                                            QUOTA UNLIMITED ON ESQUEMAMLP  
                                            TEMPORARY TABLESPACE TEMP 
                                            QUOTA 5M ON SYSTEM 
                                            PROFILE USUARIOMLP 
                                            ACCOUNT UNLOCK;
                                            GRANT CREATE SESSION TO USUARIOMLP;
                                            GRANT CREATE ANY INDEX,
                                            ALTER ANY INDEX,
                                            DROP ANY INDEX TO USUARIOMLP;
                                            GRANT CREATE SEQUENCE,
                                            CREATE ANY SEQUENCE,
                                            ALTER ANY SEQUENCE,
                                            DROP ANY SEQUENCE,
                                            SELECT ANY SEQUENCE TO USUARIOMLP;
                                            GRANT CREATE ANY TABLE,
                                            ALTER ANY TABLE,
                                            DELETE ANY TABLE,
                                            DROP ANY TABLE,
                                            INSERT ANY TABLE,
                                            SELECT ANY TABLE,
                                            FLASHBACK ANY TABLE,
                                            UPDATE ANY TABLE TO USUARIOMLP;
                                            GRANT CREATE ANY VIEW,DROP ANY VIEW,UNDER ANY VIEW TO USUARIOMLP;
                                            GRANT CREATE ANY PROCEDURE,DROP ANY PROCEDURE,UNDER ANY VIEW TO USUARIOMLP;
                                            grant create any trigger,drop any trigger to USUARIOMLP;
                                            """;

}
