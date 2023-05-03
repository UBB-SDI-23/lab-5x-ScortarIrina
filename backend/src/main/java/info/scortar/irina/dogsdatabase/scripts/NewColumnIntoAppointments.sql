alter table appointment
    add column description varchar(50);

alter table appointment
    alter column description type varchar(100);

update appointment
set description = 'smth';


do $$
    declare
        conjunctions varchar[];
        nouns varchar[];
        numerals varchar[];
        adjectives varchar[];
        verbs varchar[];
        destinations varchar[];
        index_conj int;
        index_noun int;
        index_num int;
        index_adj int;
        index_verb int;
        index_dest int;
        new_description varchar(100);
    begin
        conjunctions := array ['and', 'or', 'but', 'because', 'to', 'for', 'from', 'against'::varchar];
        nouns := array ['Jane', 'Richard Nixon', 'Miss America', 'man', 'woman', 'fish', 'elephant', 'unicorn',
            'rabbit', 'bread', 'meat', 'knee', 'playground', 'backpack', 'paracetamol', 'JLo', 'Shakira'::varchar];
        numerals := array ['two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten'::varchar];
        adjectives := array ['big', 'tiny', 'pretty', 'bald', 'red', 'smart', 'fat', 'dull', 'fancy', 'gigantic',
            'nervous', 'petty', 'cruel'::varchar];
        verbs := array ['wants', 'runs', 'jumps', 'talks', 'sleeps', 'loves', 'hates', 'sees', 'knows', 'looks for',
            'finds', 'eats', 'drinks', 'cries', 'swims'::varchar];
        destinations := array ['trees', 'dogs', 'apples', 'mermaids', 'sharks', 'palm trees', 'computers'::varchar];

        for i in 0..9999999 loop
                SELECT floor(random() * array_length(adjectives, 1) + 1)::int into index_adj;
                SELECT floor(random() * array_length(nouns, 1) + 1)::int into index_noun;
                SELECT floor(random() * array_length(numerals, 1) + 1)::int into index_num;
                SELECT floor(random() * array_length(verbs, 1) + 1)::int into index_verb;
                SELECT floor(random() * array_length(conjunctions, 1) + 1)::int into index_conj;
                SELECT floor(random() * array_length(destinations, 1) + 1)::int into index_dest;
                new_description = 'the ' || adjectives[index_adj] || ' ' ||
                                  nouns[index_noun] || ' ' || verbs[index_verb] || ' ' || conjunctions[index_conj] || ' ' ||
                                  numerals[index_num] || ' ' || destinations[index_dest];
                update appointment
                set description = new_description
                where id = i;

            end loop;
    end;
$$;