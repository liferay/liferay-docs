create index IX_F5591FB6 on GB_Entry (groupId, guestbookId);
create index IX_34CA7681 on GB_Entry (groupId, guestbookId, message);
create index IX_B00E4EF5 on GB_Entry (groupId, guestbookId, name);
create index IX_C6AF489C on GB_Entry (groupId, guestbookId, status);
create index IX_39A8BA35 on GB_Entry (groupId, name);
create index IX_65CAA0F7 on GB_Entry (guestbookId, name);
create index IX_B389CFC3 on GB_Entry (message);
create index IX_79054CF3 on GB_Entry (name);
create index IX_3BD8D300 on GB_Entry (uuid_);
create index IX_B5EF5128 on GB_Entry (uuid_, companyId);
create unique index IX_C1EA01AA on GB_Entry (uuid_, groupId);

create index IX_9294AD47 on GB_Guestbook (groupId);
create index IX_FA13ABC6 on GB_Guestbook (groupId, name);
create index IX_A4D0B52D on GB_Guestbook (groupId, status);
create index IX_11462442 on GB_Guestbook (name);
create index IX_ABB2E591 on GB_Guestbook (uuid_);
create index IX_9314A9F7 on GB_Guestbook (uuid_, companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_, groupId);