create table `user` (
                        id bigint not null primary key auto_increment ,
                        account_id varchar(100),
                        name varchar(50),
                        token varchar(36),
                        create_at datetime,
                        modified_at datetime
);