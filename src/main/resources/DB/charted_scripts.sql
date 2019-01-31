create table song
(
  id int auto_increment
    primary key,
  title varchar(45) not null,
  artist varchar(45) not null,
  genre enum('JAZZ', 'BLUES', 'ROCK') not null,
  file blob not null,
  uploader_id int not null
);

create table song_mastery
(
  know_id int auto_increment
    primary key,
  song_id int not null,
  user_id int not null,
  mastery_level tinyint not null
);

create table user
(
  id int auto_increment
    primary key,
  email varchar(255) not null,
  pass varchar(64) not null
);

