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

import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;

@Component(immediate = true, service = GuestbookEntryBatchReindexer.class)
public class GuestbookEntryBatchReindexerImpl implements GuestbookEntryBatchReindexer {

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

        batchIndexingActionable.setPerformActionMethod((GuestbookEntry entry) -> {
            Document document = indexerDocumentBuilder.getDocument(entry);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();
        
    }

    @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry)")
    protected IndexerWriter<GuestbookEntry> indexerWriter;

}