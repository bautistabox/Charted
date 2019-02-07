create table user
(
  id int auto_increment
    primary key,
  username varchar(255) not null,
  pass varchar(64) not null
);

create table song
(
  id int auto_increment
    primary key,
  title varchar(45) not null,
  artist varchar(45) not null,
  genre enum('JAZZ', 'BLUES', 'ROCK') not null,
  file longblob not null,
  uploader_id int not null,
  constraint song_ibfk_1
    foreign key (uploader_id) references user (id)
);

create index uploader_id
  on song (uploader_id);


