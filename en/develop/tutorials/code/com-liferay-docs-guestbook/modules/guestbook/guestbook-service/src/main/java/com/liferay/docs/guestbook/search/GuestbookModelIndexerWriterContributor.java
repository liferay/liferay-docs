package com.liferay.docs.guestbook.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
		service = ModelIndexerWriterContributor.class
)
public class GuestbookModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<Guestbook> {

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod((Guestbook guestbook) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(
	guestbook);

			batchIndexingActionable.addDocuments(document);
		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
	guestbookLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Guestbook guestbook) {
		return guestbook.getCompanyId();
	}

	@Override
	public void modelIndexed(Guestbook guestbook) {
		entryBatchReindexer.reindex(
	guestbook.getGuestbookId(), guestbook.getCompanyId());
	}

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory
	dynamicQueryBatchIndexingActionableFactory;

	@Reference
	protected EntryBatchReindexer entryBatchReindexer;

	@Reference
	protected GuestbookLocalService guestbookLocalService;

}