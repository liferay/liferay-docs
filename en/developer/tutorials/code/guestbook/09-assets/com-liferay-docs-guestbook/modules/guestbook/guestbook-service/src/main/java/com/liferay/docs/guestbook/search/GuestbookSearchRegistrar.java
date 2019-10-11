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

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

@Component(immediate=true)
public class GuestbookSearchRegistrar {

        @Activate
        protected void activate(BundleContext bundleContext) {

            _serviceRegistration = modelSearchRegistrarHelper.register(
                Guestbook.class, bundleContext, modelSearchDefinition -> {
                    modelSearchDefinition.setDefaultSelectedFieldNames(
                        Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                        Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                        Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                        Field.TITLE, Field.UID);

                    modelSearchDefinition.setModelIndexWriteContributor(
                        modelIndexWriterContributor);
                    modelSearchDefinition.setModelSummaryContributor(
                        modelSummaryContributor);
                });
        }

        @Deactivate
        protected void deactivate() {

            _serviceRegistration.unregister();
        }

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
        protected ModelIndexerWriterContributor<Guestbook> modelIndexWriterContributor;

        @Reference
        protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
        protected ModelSummaryContributor modelSummaryContributor;

        private ServiceRegistration<?> _serviceRegistration;
}
