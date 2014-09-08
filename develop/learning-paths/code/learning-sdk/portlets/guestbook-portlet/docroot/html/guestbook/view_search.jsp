<%@include file="/html/init.jsp"%>

<%
    String redirect = ParamUtil.getString(request, "redirect");

    String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
    <liferay-portlet:renderURLParams varImpl="searchURL" />
    <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

    <liferay-ui:header
        backURL="<%= redirect %>"
        title="search"
    />

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/html/guestbook/view_search.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("keywords", keywords);

List<String> headerNames = new ArrayList<String>();

headerNames.add("#");
headerNames.add("entryName");

SearchContainer searchContainer = new SearchContainer(renderRequest,
		null, null, SearchContainer.DEFAULT_CUR_PARAM,
		SearchContainer.DEFAULT_DELTA, portletURL, headerNames,
		LanguageUtil.format(pageContext,
				"no-entries-were-found-that-matched-the-keywords-x",
				"<strong>" + HtmlUtil.escape(keywords) + "</strong>"));

try {
    Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

    SearchContext searchContext = SearchContextFactory.getInstance(request);

    searchContext.setEnd(searchContainer.getEnd());
    searchContext.setKeywords(keywords);
    searchContext.setStart(searchContainer.getStart());

    Hits results = indexer.search(searchContext);

    int total = results.getLength();

    searchContainer.setTotal(total);

    List resultRows = searchContainer.getResultRows();

    for (int i = 0; i < results.getDocs().length; i++) {
        Document doc = results.doc(i);

        ResultRow row = new ResultRow(doc, i, i);

        // Position

        row.addText(searchContainer.getStart() + i + 1 + StringPool.PERIOD);

        // Entry

        long entryId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

        Entry entry = null;

        try {
            entry = EntryLocalServiceUtil.getEntry(entryId);

            entry = entry.toEscapedModel();
        }
        catch (Exception e) {
            if (_log.isWarnEnabled()) {
                _log.warn("Entries search index is stale and contains entry " + entryId);
            }

            continue;
        }

        PortletURL rowURL = renderResponse.createRenderURL();
        
        String currentURL = PortalUtil.getCurrentURL(request);
        
        rowURL.setParameter("mvcPath", "/html/guestbook/edit_entry.jsp");
        rowURL.setParameter("redirect", currentURL);
        rowURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

        row.addText(entry.getName(), rowURL);

        // Add result row

        resultRows.add(row);
    }
%>

    <span class="aui-search-bar">
        <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" value="<%= keywords %>" />

        <aui:button type="submit" value="search" />
    </span>

    <br /><br />

    <liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

<%
}
catch (Exception e) {
    _log.error(e.getMessage());
}
%>
</aui:form>

<%
if (Validator.isNotNull(keywords)) {
    String currentURL = PortalUtil.getCurrentURL(request);
	
    PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "search") + ": " + keywords, currentURL);
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("docroot.html.guestbook.view_search_jsp");
%>
