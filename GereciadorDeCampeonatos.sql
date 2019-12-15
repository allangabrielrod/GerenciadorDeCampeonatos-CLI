drop database if exists campeonatos;
create database campeonatos;

use campeonatos;

set sql_safe_updates = 0;

create table competidor (
	id int primary key unique auto_increment,
    nome varchar(200) not null,
    data_cadastro date not null
);

create table campeonato (
	id int primary key unique auto_increment,
    nome varchar(200),
    data_cadastro date not null
);

create table partida (
	id int primary key unique auto_increment,
    data_registro date not null,
    time_a int not null,
    score_a int,
    score_b int,	
    time_b int not null,
    campeonato_id int not null,
    foreign key (campeonato_id) references campeonato(id)
		on delete restrict
        on update cascade,
    foreign key (time_a) references competidor(id)
		on delete restrict
        on update cascade,
	foreign key (time_b) references competidor(id)
		on delete restrict
        on update cascade
);