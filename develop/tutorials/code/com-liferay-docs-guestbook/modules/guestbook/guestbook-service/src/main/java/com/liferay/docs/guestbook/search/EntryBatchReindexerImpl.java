package com.liferay.docs.guestbook.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;

@Component(immediate = true, service = EntryBatchReindexer.class)
public class EntryBatchReindexerImpl implements EntryBatchReindexer {

	@Override
	public void reindex(long guestbookId, long companyId) {
		BatchIndexingActionable batchIndexingActionable =
	indexerWriter.getBatchIndexingActionable();

		batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
			Property guestbookIdPropery = PropertyFactoryUtil.forName(
	"guestbookId");

			dynamicQuery.add(guestbookIdPropery.eq(guestbookId));
		});

		batchIndexingActionable.setCompanyId(companyId);

		batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
			Document document = indexerDocumentBuilder.getDocument(entry);

			batchIndexingActionable.addDocuments(document);
		});

		batchIndexingActionable.performActions();
		
	}

	@Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
	protected IndexerDocumentBuilder indexerDocumentBuilder;

	@Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
	protected IndexerWriter<Entry> indexerWriter;

}