package com.liferay.docs.guestbook.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(immediate = true, service = Indexer.class)
public class EntryIndexer extends BaseIndexer<Entry> {

	public static final String CLASS_NAME = Entry.class.getName();

	public EntryIndexer() {

		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID, Field.SCOPE_GROUP_ID, Field.GROUP_ID);
		setDefaultSelectedLocalizedFieldNames(Field.TITLE, Field.CONTENT);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {

		return CLASS_NAME;
	}

	@Override
	public boolean hasPermission(
		PermissionChecker permissionChecker, String entryClassName,
		long entryClassPK, String actionId)
		throws Exception {

		return EntryPermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		addStatus(contextBooleanFilter, searchContext);
	}

	@Override
	public void postProcessSearchQuery(
		BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
		SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, "guestbookName", false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.CONTENT, false);
	}

	@Override
	protected void doDelete(Entry entry)
		throws Exception {

		deleteDocument(entry.getCompanyId(), entry.getEntryId());
	}

	@Override
            protected Document doGetDocument(Entry entry)
                throws Exception {

                Document document = getBaseModelDocument(CLASS_NAME, entry);
                document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
                document.addText("email", entry.getEmail());

                Locale defaultLocale =
                    PortalUtil.getSiteDefaultLocale(entry.getGroupId());
                String localizedTitle = LocalizationUtil.getLocalizedName(
                    Field.TITLE, defaultLocale.toString());
                String localizedMessage = LocalizationUtil.getLocalizedName(
                    Field.CONTENT, defaultLocale.toString());

                document.addText(localizedTitle, entry.getName());
                document.addText(localizedMessage, entry.getMessage());

                long guestbookId = entry.getGuestbookId();
                Guestbook guestbook = _guestbookLocalService.getGuestbook(guestbookId);
                String guestbookName= guestbook.getName();
                String localizedGbName = LocalizationUtil.getLocalizedName(
                    "guestbookName", defaultLocale.toString());

                document.addText(localizedGbName, guestbookName);

                return document;
            }

	@Override
    protected Summary doGetSummary(
        Document document, Locale locale, String snippet,
        PortletRequest portletRequest, PortletResponse portletResponse)
        throws Exception {

        Summary summary = createSummary(document);

        summary.setMaxContentLength(200);

        return summary;
    }

    @Override
    protected void doReindex(Entry entry)
        throws Exception {

        Document document = getDocument(entry);
        indexWriterHelper.updateDocument(
            getSearchEngineId(), entry.getCompanyId(), document,
            isCommitImmediately());
    }

    @Override
    protected void doReindex(String className, long classPK)
        throws Exception {

        Entry entry = _entryLocalService.getEntry(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids)
        throws Exception {

        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    protected void reindexEntries(long companyId)
        throws PortalException {

        final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
            _entryLocalService.getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setCompanyId(companyId);

        indexableActionableDynamicQuery.setPerformActionMethod(
            new ActionableDynamicQuery.PerformActionMethod<Entry>() {

                @Override
                public void performAction(Entry entry) {

                    try {
                        Document document = getDocument(entry);
                        indexableActionableDynamicQuery.addDocuments(document);
                    }
                    catch (PortalException pe) {
                        if (_log.isWarnEnabled()) {
                            _log.warn(
                                "Unable to index entry " + entry.getEntryId(),
                                pe);
                        }
                    }
                }
            });
        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    private static final Log _log = LogFactoryUtil.getLog(EntryIndexer.class);

    @Reference
    protected IndexWriterHelper indexWriterHelper;

    @Reference
    private EntryLocalService _entryLocalService;
    
    @Reference
    private GuestbookLocalService _guestbookLocalService;

}

