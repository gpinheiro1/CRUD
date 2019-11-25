create table Aluno(
ra varchar(6) primary key,
nome varchar(50) not null,
email varchar(30) not null
)
select * from Aluno

create table Materia(
codMateria varchar(10) primary key,
nomeMateria varchar(40) not null
)
select * from Materia

create table Fez(
ra varchar(6) not null,
codMateria varchar(10) not null,
nota int not null,
frequencia int not null,
constraint fkAluno foreign key(ra)
references Aluno(ra),
constraint fkMateria foreign key(codMateria)
references Materia(codMateria)
)
select * from Fez

--alunos que tiveram 0% de frequencia
 select
	a.nome
from
	Aluno a,
	Fez f
where
	f.ra = a.ra
	and f.frequencia = 0;

--nome das materias sem reprovacao
 select
	m.nomeMateria
from
	Materia m,
	Fez f
where
	m.codMateria = f.codMateria
group by
	m.nomemateria
having
	avg(f.nota) >= 5;

--nome das materias ordenado de forma crescente pela media dos alunos
 select
	m.nomeMateria
from
	Materia m,
	Fez f
where
	m.codMateria = f.codMateria
group by
	m.nomemateria
order by
	avg(f.nota);

--nome dos alunos ordenados crescente pela média de suas matérias
 select
	a.nome
from
	Aluno a,
	Fez f,
	Materia m
where
	a.ra = f.ra
	and m.codMateria = f.codMateria
group by
	a.nome
order by
	avg(f.nota);
