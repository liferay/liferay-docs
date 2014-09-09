<%@include file="/html/init.jsp"%>

<%
    String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
    <liferay-portlet:renderURLParams varImpl="searchURL" />

    <liferay-ui:header
        backURL="<%= viewURL.toString() %>"
        title="search"
    />
</aui:form>

<%
	SearchContext searchContext = SearchContextFactory
	.getInstance(request);

	searchContext.setKeywords(keywords);
	searchContext.setAttribute("paginationType", "more");
	searchContext.setStart(0);
	searchContext.setEnd(10);
	
	Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

	Hits hits = indexer.search(searchContext);
	
	List<Entry> entries = new ArrayList<Entry>();

	for (int i = 0; i < hits.getDocs().length; i++) {
		Document doc = hits.doc(i);

		long entryId = GetterUtil
		.getLong(doc.get(Field.ENTRY_CLASS_PK));

		Entry entry = null;
		
		try {
			entry = EntryLocalServiceUtil.getEntry(entryId);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		entries.add(entry);
	}
%>

<liferay-ui:search-container delta="10" emptyResultsMessage="no-entries-were-found">
	<liferay-ui:search-container-results
		results="<%= entries %>"
		total="<%= entries.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Entry"
		keyProperty="entryId"
		modelVar="searchEntry"
	>
		<liferay-ui:search-container-column-text
			name="message"
			value="<%= searchEntry.getMessage() %>"
		/>

		<liferay-ui:search-container-column-text
			name="name"
			value="<%= searchEntry.getName() %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
private static Log _log = LogFactoryUtil.getLog("docroot.html.guestbook.view_search_jsp");
%>
