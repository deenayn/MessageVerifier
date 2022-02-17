create table if not exists message_info
(
    id           bigserial not null
        constraint message_info_pkey
            primary key,
    ts           integer   not null,
    sender       text      not null,
    message      jsonb     not null,
    sent_from_ip text,
    priority     integer
);
