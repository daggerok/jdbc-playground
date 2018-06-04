drop table if exists messages;
drop table if exists users;

create table users(
  id   varchar(32) not null primary key,
  name varchar(32) not null
);

create table messages(
  id      bigint(10) auto_increment not null primary key,
  body    varchar(4096)             null,
  user_id varchar(32)               not null references users(id)
);
