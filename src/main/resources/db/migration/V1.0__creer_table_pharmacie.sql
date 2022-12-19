create table pharmacie
(
    id serial not null,
    medicament_id      varchar(50)  not null,
    date_de_peremption DATE         not null,
    utilisateur_id     varchar(200) not null,
    primary key (id)
);
create INDEX index_email_utilisateur ON pharmacie (utilisateur_id);

create table notification
(
    id                     serial       not null,
    boite_de_medicament_id serial       not null,
    utilisateur_id         varchar(255) not null,
    lue                    boolean      not null default false,
    date_de_creation       date         not null,
    primary key (id),
    CONSTRAINT fk_notification_pharmacie
        FOREIGN KEY (boite_de_medicament_id)
            REFERENCES pharmacie (id)
);
create index index_notification_utilisateur on notification (utilisateur_id);

create table preference
(
    id                        serial not null,
    utilisateur_id            varchar(255) not null,
    notification_mail         varchar(255),
    notification_heure        int,
    type_affichage_medicaments varchar(10),
    primary key (id)
);
create index index_preference_utilisateur on preference (utilisateur_id);