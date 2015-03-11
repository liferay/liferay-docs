create index IX_864197E5 on INSULT_Insult (groupId);
create index IX_B435DFAF on INSULT_Insult (uuid_);
create index IX_E7A2D119 on INSULT_Insult (uuid_, companyId);
create unique index IX_D1AEC9DB on INSULT_Insult (uuid_, groupId);

create index IX_D9D1C805 on Insult_Insult (groupId);
create index IX_985397CF on Insult_Insult (uuid_);
create index IX_E476C4F9 on Insult_Insult (uuid_, companyId);
create unique index IX_90AE35BB on Insult_Insult (uuid_, groupId);