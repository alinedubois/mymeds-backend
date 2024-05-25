create table medicaments_codes_barres (
    id uuid primary key,
    medicament_id varchar(50) not null,
    code_barre varchar(100) not null
);

create index idx_medicaments_codes_barres_medicament_id on medicaments_codes_barres(medicament_id);
create index idx_medicaments_codes_barres_code_barre on medicaments_codes_barres(code_barre);
