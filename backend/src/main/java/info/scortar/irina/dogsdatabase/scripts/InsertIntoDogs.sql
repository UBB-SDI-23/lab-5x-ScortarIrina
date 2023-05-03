-- dogs // id, name, breed, age, weight
-- takes data from csv, puts it in a temp table, then it transfers it into the table of dogs
DO
$$
BEGIN
        DROP FOREIGN TABLE tmp_tablename_fdw;
        CREATE FOREIGN TABLE tmp_tablename_fdw(
            id bigint,
            name varchar(255),
            breed varchar(255),
            age integer,
            weight integer,
            owner_id bigint
            ) SERVER import OPTIONS ( filename '/tmp/dogs.csv', format 'csv');

INSERT INTO dog (id, age, breed, name, weight, owner_id)
SELECT id, age, breed, name, weight, owner_id
FROM tmp_tablename_fdw;
END
$$;

select * from dog;