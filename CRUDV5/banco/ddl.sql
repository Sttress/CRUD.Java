create table tipo(
	pk serial not null,
	nome varchar(200) not null,
	ativo boolean null,
	primary key (pk)
);

create table viagem(
	pk serial not null,
	dataSolicitacao date not null,
	dataAprovacaoSolicitacao date null,
	dataPartida date null,
	dataRetorno date null,
	dataAcerto date null,
	dataAprovacaoAcerto date null,
	estatus int null,
	motivo varchar(200) null,
	dias int not null,
	tipo int null,
	primary key (pk),
	foreign key (tipo) references tipo (pk)
);

create table lugar(
	pk serial not null,
	nome varchar(200) not null,
	ativo boolean null,
	primary key (pk)
);