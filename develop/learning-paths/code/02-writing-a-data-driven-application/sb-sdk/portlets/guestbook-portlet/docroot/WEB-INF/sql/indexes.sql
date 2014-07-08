create index IX_F5591FB6 on GB_Entry (groupId, guestbookId);
create index IX_3BD8D300 on GB_Entry (uuid_);
create index IX_B5EF5128 on GB_Entry (uuid_, companyId);
create unique index IX_C1EA01AA on GB_Entry (uuid_, groupId);

create index IX_9294AD47 on GB_Guestbook (groupId);
create index IX_ABB2E591 on GB_Guestbook (uuid_);
create index IX_9314A9F7 on GB_Guestbook (uuid_, companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_, groupId);