create index IX_9294AD47 on GB_Guestbook (groupId);
create index IX_9314A9F7 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E84D72FD on GB_GuestbookEntry (groupId, guestbookId);
create index IX_CC265FEF on GB_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4A541631 on GB_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], groupId);