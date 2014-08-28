create index IX_B435DFAF on INSULT_Insult (uuid_);
create index IX_E7A2D119 on INSULT_Insult (uuid_, companyId);
create unique index IX_D1AEC9DB on INSULT_Insult (uuid_, groupId);