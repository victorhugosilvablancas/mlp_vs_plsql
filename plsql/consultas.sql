select COUNT(*) from DATOSBRUTOS;
select COUNT(*) from DATOSBRUTOS_TEST;

select line,idproduct,amount from DATOSBRUTOS;

select idproduct,sum(amount) from DATOSBRUTOS group by idproduct order by idproduct;

select count(line) from DATOSBRUTOS;


