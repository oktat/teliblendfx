

create table users (
  id          integer not null primary key autoincrement,
  username    text not null
);

create table books (
  id          integer not null primary key autoincrement,
  title       text not null,
  author      text,
  user_id integer
);
