
package com.liferay.docs.guestbook.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
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
public class GuestbookIndexer extends BaseIndexer<Guestbook> {

	public static final String CLASS_NAME = Guestbook.class.getName();

	public GuestbookIndexer() {
        setDefaultSelectedFieldNames(
            Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
            Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
            Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);
        setPermissionAware(true);
        setFilterSearch(true);
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

		return GuestbookPermission.contains(
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

		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
	}

	@Override
	protected void doDelete(Guestbook guestbook)
		throws Exception {

		deleteDocument(guestbook.getCompanyId(), guestbook.getGuestbookId());
	}

	@Override
	protected Document doGetDocument(Guestbook guestbook)
		throws Exception {

		Document document = getBaseModelDocument(CLASS_NAME, guestbook);

		document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

		Locale defaultLocale =
			PortalUtil.getSiteDefaultLocale(guestbook.getGroupId());
		String localizedField = LocalizationUtil.getLocalizedName(
			Field.TITLE, defaultLocale.toString());

		document.addText(localizedField, guestbook.getName());
		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(document);
		summary.setMaxContentLength(200);
		return summary;
	}

	@Override
	protected void doReindex(Guestbook guestbook)
		throws Exception {

		Document document = getDocument(guestbook);
		indexWriterHelper.updateDocument(
			getSearchEngineId(), guestbook.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK)
		throws Exception {

		Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
		doReindex(guestbook);
	}

	@Override
	protected void doReindex(String[] ids)
		throws Exception {

		long companyId = GetterUtil.getLong(ids[0]);
		reindexGuestbooks(companyId);
	}

	protected void reindexGuestbooks(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_guestbookLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);

		indexableActionableDynamicQuery.setPerformActionMethod(

			new ActionableDynamicQuery.PerformActionMethod<Guestbook>() {

				@Override
				public void performAction(Guestbook guestbook) {

					try {
						Document document = getDocument(guestbook);
						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index guestbook " +
									guestbook.getGuestbookId(),
								pe);
						}
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log =
		LogFactoryUtil.getLog(GuestbookIndexer.class);

	@Reference
	protected IndexWriterHelper indexWriterHelper;

	@Reference
	private GuestbookLocalService _guestbookLocalService;

}
