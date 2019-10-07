package com.liferay.docs.guestbook.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
		service = ModelIndexerWriterContributor.class
)
public class EntryModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<Entry> {

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(
	entry);
			
			batchIndexingActionable.addDocuments(document);
			
		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
	entryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Entry entry) {
		return entry.getCompanyId();
	}

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory
	dynamicQueryBatchIndexingActionableFactory;

	@Reference
	protected EntryLocalService entryLocalService;

}