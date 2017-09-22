package com.liferay.docs.guestbook.search;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import java.util.Locale;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
@Component(
	immediate = true,
	service = Indexer.class)
public class GuestbookIndexer extends BaseIndexer<Guestbook> {
	public GuestbookIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID, Field.DESCRIPTION);
		setDefaultSelectedLocalizedFieldNames(Field.TITLE);
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
		Guestbook guestbook =
				_guestbookLocalService.getGuestbook(entryClassPK);
		return permissionChecker.hasPermission(guestbook.getGroupId(), Guestbook.class.getName(),
				guestbook.getGuestbookId(), ActionKeys.VIEW);
	}
	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter contextBooleanFilter, SearchContext searchContext)
			throws Exception {
		addStatus(contextBooleanFilter, searchContext);
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
        document.addText(Field.TITLE, guestbook.getName());
        document.addKeyword(Field.GROUP_ID, getSiteGroupId(guestbook.getGroupId()));
        document.addKeyword(Field.SCOPE_GROUP_ID, guestbook.getGroupId());
		
		
		return document;
	}
	
	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse)
			throws Exception {
		//String prefix = Field.SNIPPET + StringPool.UNDERLINE;
		//String title = document.get(prefix + Field.TITLE, Field.TITLE);
		//String content = HtmlUtil.stripHtml(
			//document.get(prefix + Field.DESCRIPTION, Field.DESCRIPTION));
		
        Summary summary = createSummary(document);

        summary.setMaxContentLength(200);

        return summary;
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
		reindexEntries(companyId);
	}
	@Override
	protected void doReindex(Guestbook guestbook)
		throws Exception {
		Document document = getDocument(guestbook);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			guestbook.getCompanyId(), document, isCommitImmediately());
	}
	protected void reindexEntries(long companyId)
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
	private GuestbookLocalService _guestbookLocalService;
	private static final String CLASS_NAME = Guestbook.class.getName();
}