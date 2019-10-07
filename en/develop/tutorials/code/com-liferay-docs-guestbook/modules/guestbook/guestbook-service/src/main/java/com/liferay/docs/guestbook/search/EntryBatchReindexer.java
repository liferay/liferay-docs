package com.liferay.docs.guestbook.search;

public interface EntryBatchReindexer {

	public void reindex(long guestbookId, long companyId);

}