create table SAMPLE_InsultLog (
	insultLogId LONG not null primary key,
	insult VARCHAR(75) null,
	rating VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG
);

create table SAMPLE_InsultWriter (
	insultWriterId LONG not null primary key,
	insult VARCHAR(75) null,
	rating VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG
);

create table SAMPLE_Insulted (
	insultedId LONG not null primary key,
	insult VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG
);