/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
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
        guestbookEntryBatchReindexer.reindex(
    guestbook.getGuestbookId(), guestbook.getCompanyId());
    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
    dynamicQueryBatchIndexingActionableFactory;

    @Reference
    protected GuestbookEntryBatchReindexer guestbookEntryBatchReindexer;

    @Reference
    protected GuestbookLocalService guestbookLocalService;

}