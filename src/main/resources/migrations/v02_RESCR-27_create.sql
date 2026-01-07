create table scr.tb_users (
  id bigserial primary key,
  email varchar(255) not null unique,
  password varchar(255) not null,
  role varchar(50) not null,
  enabled boolean default true,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);

create index idx_users_email on scr.tb_users(email);
