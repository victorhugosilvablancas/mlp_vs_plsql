CREATE USER DCCUAQ_USR 
IDENTIFIED BY u84uvTrlnBM18 
DEFAULT TABLESPACE DCCUAQ_TABLES  
QUOTA UNLIMITED ON DCCUAQ_TABLES  
TEMPORARY TABLESPACE TEMP 
QUOTA 5M ON SYSTEM 
PROFILE DCCUAQ_PROFILE 
ACCOUNT UNLOCK;
GRANT CREATE SESSION TO DCCUAQ_USR;
GRANT CREATE ANY INDEX,
ALTER ANY INDEX,
DROP ANY INDEX TO DCCUAQ_USR;
GRANT CREATE SEQUENCE,
CREATE ANY SEQUENCE,
ALTER ANY SEQUENCE,
DROP ANY SEQUENCE,
SELECT ANY SEQUENCE TO DCCUAQ_USR;
GRANT CREATE ANY TABLE,
ALTER ANY TABLE,
DELETE ANY TABLE,
DROP ANY TABLE,
INSERT ANY TABLE,
SELECT ANY TABLE,
FLASHBACK ANY TABLE,
UPDATE ANY TABLE TO DCCUAQ_USR;
GRANT CREATE ANY VIEW,DROP ANY VIEW,UNDER ANY VIEW TO DCCUAQ_USR;
GRANT CREATE ANY PROCEDURE,DROP ANY PROCEDURE,UNDER ANY VIEW TO DCCUAQ_USR;
grant create any trigger,drop any trigger to DCCUAQ_USR;