create table INSULT_Insult (
	uuid_ VARCHAR(75) null,
	insultId LONG not null primary key,
	insultString VARCHAR(75) null,
	userId LONG,
	groupId LONG,
	companyId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table Insult_Insult (
	uuid_ VARCHAR(75) null,
	insultId LONG not null primary key,
	insultString VARCHAR(75) null,
	userId LONG,
	groupId LONG,
	companyId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);