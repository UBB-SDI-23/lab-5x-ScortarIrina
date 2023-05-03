-- owners // id, fName, lName, gender, job, age
-- takes data from csv, puts it in a temp table, then it transfers it into the table of owners
DO
$$
    BEGIN
        CREATE FOREIGN TABLE tmp_tablename_fdw(
            id bigint,
            first_name varchar(255),
            last_name varchar(255),
            gender varchar(255),
            job varchar(255),
            age integer
            ) SERVER import OPTIONS ( filename '/tmp/owners.csv', format 'csv');

        INSERT INTO owner (id, first_name, last_name, gender, job, age)
        SELECT id, first_name, last_name, gender, job, age
        FROM tmp_tablename_fdw;
    END
$$;

select * from owner;