create table question
(
    id bigint auto_increment,
    title varchar(50),
    description text,
    create_at datetime,
    modified_at datetime,
    creator bigint,
    comment_count bigint default 0,
    view_count bigint default 0,
    like_count bigint default 0,
    tag varchar(256),
    constraint QUESTION_PK
        primary key (id)
);