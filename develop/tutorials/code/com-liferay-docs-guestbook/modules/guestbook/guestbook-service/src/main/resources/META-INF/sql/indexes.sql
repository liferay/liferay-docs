create index IX_F5591FB6 on GB_Entry (groupId, guestbookId);
create index IX_535BE3DC on GB_Entry (groupId, status);
create index IX_2DDEBF1A on GB_Entry (status);
create index IX_B5EF5128 on GB_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C1EA01AA on GB_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A4D0B52D on GB_Guestbook (groupId, status);
create index IX_B946FEA9 on GB_Guestbook (status);
create index IX_9314A9F7 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);