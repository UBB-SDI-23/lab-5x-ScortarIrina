-- vets // id, fName, lName, age, salary, specialty, years of experience
-- takes data from csv, puts it in a temp table, then it transfers it into the table of vets
DO
$$
    BEGIN
        DROP FOREIGN TABLE tmp_tablename_fdw;
        CREATE FOREIGN TABLE tmp_tablename_fdw(
            id bigint,
            first_name varchar(255),
            last_name varchar(255),
            age integer,
            salary integer,
            specialty varchar(255),
            years_of_experience integer
            ) SERVER import OPTIONS ( filename '/tmp/vets.csv', format 'csv');

        INSERT INTO vet (id, first_name, last_name, age, salary, specialty, years_of_experience)
        SELECT id, first_name, last_name, age, salary, specialty, years_of_experience
        FROM tmp_tablename_fdw;
    END
$$;

select * from vet;