-- licenses // id, city, country, serial number, university, v_id
-- takes data from csv, puts it in a temp table, then it transfers it into the table of medical licenses
DO
$$
    BEGIN
        DROP FOREIGN TABLE tmp_tablename_fdw;
        CREATE FOREIGN TABLE tmp_tablename_fdw(
            id bigint,
            city varchar(255),
            country varchar(255),
            serial_number varchar(255),
            university varchar(255),
            vet_id bigint
            ) SERVER import OPTIONS ( filename '/tmp/licenses.csv', format 'csv');

        INSERT INTO medical_license (id, city, country, serial_number, university, vet_id)
        SELECT id, city, country, serial_number, university, vet_id
        FROM tmp_tablename_fdw;
    END
$$;

select * from medical_license;