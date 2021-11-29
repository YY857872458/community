create table comment
(
    id          bigint auto_increment primary key,
    parent_id   bigint        not null,
    type        int           not null,
    author_id   bigint        not null,
    content     varchar(1024) not null,
    create_at   datetime      not null,
    modified_at datetime      not null,
    like_count  bigint default 0
);

