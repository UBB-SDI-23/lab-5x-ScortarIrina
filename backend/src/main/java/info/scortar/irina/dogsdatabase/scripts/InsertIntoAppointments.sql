-- appointments // id, date, price, dId, vId, description
-- takes data from csv, puts it in a temp table, then it transfers it into the table of appointments
DO
$$
    BEGIN
       DROP FOREIGN TABLE tmp_tablename_fdw;
        CREATE FOREIGN TABLE tmp_tablename_fdw(
            id bigint,
            date date,
            price real,
            dog_id bigint,
            vet_id bigint
            ) SERVER import OPTIONS ( filename '/tmp/appointments.csv', format 'csv');

        INSERT INTO appointment (id, date, price, dog_id, vet_id)
        SELECT id, date, price, dog_id, vet_id
        FROM tmp_tablename_fdw;
    END
$$;
