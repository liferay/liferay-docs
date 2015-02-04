package com.liferay.docs.insult.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.docs.insult.service.permission.InsultPermission;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.docs.insult.model.Insult;
import com.liferay.docs.insult.service.InsultLocalServiceUtil;

public class InsultIndexer extends BaseIndexer {

	public InsultIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
			String entryClassName, long entryClassPK, String actionId)
			throws Exception {

		return InsultPermission.contains(permissionChecker,
				entryClassPK, ActionKeys.VIEW);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {

		Insult insult = (Insult) obj;
		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, insult.getPrimaryKey());

		String searchEngineId = SearchEngineUtil.getDefaultSearchEngineId();
		SearchEngineUtil.deleteDocument(searchEngineId,
				insult.getCompanyId(), document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {

		Insult insult = (Insult) obj;
		long groupId = getSiteGroupId(insult.getGroupId());
		long scopeGroupId = insult.getGroupId();
		String insultString = insult.getInsultString();

		Document document = getBaseModelDocument(PORTLET_ID, insult);

		document.addKeyword(Field.GROUP_ID, groupId);
		document.addKeyword(Field.SCOPE_GROUP_ID, scopeGroupId);
		document.addText(Field.DESCRIPTION, insultString);

		return document;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {

		Insult insult = (Insult) obj;
		
			String searchEngineId = SearchEngineUtil.getDefaultSearchEngineId();
			SearchEngineUtil.updateDocument(searchEngineId,
				insult.getCompanyId(), getDocument(insult));
		
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {

		Insult insult = InsultLocalServiceUtil
				.getInsult(classPK);

		doReindex(insult);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {

		long companyId = GetterUtil.getLong(ids[0]);

		doReindexAll(companyId);
	}

	private void doReindexAll(long companyId) throws Exception {

		int count = InsultLocalServiceUtil.getInsultsCount();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexInsults(companyId, start, end);
		}
	}

	protected void reindexInsults(long companyId, int start, int end)
			throws Exception {

		//List<Insult> insults = InsultLocalServiceUtil.getInsultsByStatus(WorkflowConstants.STATUS_APPROVED);
		List<Insult> insults = InsultLocalServiceUtil.getInsults(start, end);

		if (insults.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (Insult insult : insults) {
			Document document = getDocument(insult);

			documents.add(document);
		}

		String searchEngineId = SearchEngineUtil.getDefaultSearchEngineId();
		SearchEngineUtil.updateDocuments(searchEngineId, companyId, documents);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {

		return PORTLET_ID;
	}

	public Summary doGetSummary(Document document, Locale locale,
			String snippet, PortletURL portletURL) {

		String title = document.get(Field.TITLE);

		String content = snippet;

		if (Validator.isNull(snippet)) {
			content = document.get(Field.DESCRIPTION);

			if (Validator.isNull(content)) {
				content = StringUtil.shorten(document.get(Field.CONTENT), 200);
			}
		}

		String insultId = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("mvcPath", "/html/insult/view_insult.jsp");
		portletURL.setParameter("insultId", insultId);

		return new Summary(title, content, portletURL);
	}

	@Override
	public String getPortletId() {
		
		return PORTLET_ID;
	}

	public String[] getClassNames() {

		return CLASS_NAMES;
	}

	public static final String[] CLASS_NAMES = { Insult.class.getName() };

	public static final String PORTLET_ID = "insults";

}
