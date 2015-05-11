create table Task_Inventory (
	inventoryId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG
);

create table Task_Setup (
	setupId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG
);

create table Task_Task (
	taskId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	roadieResponse VARCHAR(75) null,
	inventoryResponse VARCHAR(75) null
);