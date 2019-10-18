create index IX_A4D0B52D on GB_Guestbook (groupId, status);
create index IX_B946FEA9 on GB_Guestbook (status);
create index IX_9314A9F7 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D49B64E3 on GB_GuestbookEntry (groupId, guestbookId, status);
create index IX_D8346035 on GB_GuestbookEntry (groupId, status);
create index IX_4BED4AA1 on GB_GuestbookEntry (status);
create index IX_CC265FEF on GB_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4A541631 on GB_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], groupId);