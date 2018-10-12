<%@include file="../init.jsp"%>

<%
  String keywords = ParamUtil.getString(request, "keywords");
  long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
%>

<liferay-portlet:renderURL varImpl="searchURL">
        <portlet:param name="mvcPath" 
        value="/guestbookwebportlet/view_search.jsp" />
</liferay-portlet:renderURL>

<portlet:renderURL var="viewURL">
    <portlet:param 
        name="mvcPath" 
        value="/guestbookwebportlet/view.jsp" 
    />
</portlet:renderURL>


<aui:form action="${searchURL}" name="fm">

<!-- 	<div class="row"> -->
<!-- 		<div class="col-md-2"> -->
<%-- 			<aui:select name="fieldName" value="${fieldName}"> --%>
<%-- 				<aui:option label="Modified" value="modified" /> --%>
<%-- 				<aui:option label="Event Date" value="guestbookEventDate" /> --%>
<%-- 				<aui:option label="Created" value="created" /> --%>
<%-- 			</aui:select> --%>
<!-- 		</div> -->

<!-- 		<div class="col-md-5"> -->
<%-- 			<c:set value="MM/dd/yyyy or Date Math Expression" var="ph" /> --%>

<%-- 			<aui:input autocomplete="off" cssClass="aui-datepicker" name="from" placeholder="${ph}" value="${from}" /> --%>
<!-- 		</div> -->

<!-- 		<div class="col-md-5"> -->
<%-- 			<aui:input autocomplete="off" cssClass="aui-datepicker" name="to" placeholder="${ph}" value="${to}" /> --%>
<!-- 		</div> -->
<!-- 	</div> -->

	<div class="row">
		<div class="col-md-8">
			<aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
		</div>

		<div class="col-md-4">
			<aui:button type="submit" value="search" />
		</div>
	</div>
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
    
    List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

        Map<String, String> guestbookMap = new HashMap<String, String>();

        for (Guestbook guestbook : guestbooks) {
                guestbookMap.put(Long.toString(guestbook.getGuestbookId()), guestbook.getName());
        }
%>

<liferay-ui:search-container delta="10" 
    emptyResultsMessage="no-entries-were-found" 
    total="<%= entries.size() %>">
        <liferay-ui:search-container-results
                results="<%= entries %>"
/>

<liferay-ui:search-container-row
        className="com.liferay.docs.guestbook.model.Entry"
        keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">
        
        <liferay-ui:search-container-column-text name="guestbook"
        value="<%=guestbookMap.get(Long.toString(entry.getGuestbookId()))%>" />

    <liferay-ui:search-container-column-text property="message" />

    <liferay-ui:search-container-column-text property="name" />

    <liferay-ui:search-container-column-jsp
        path="/guestbookwebportlet/entry_actions.jsp"
        align="right" />
</liferay-ui:search-container-row>

<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
        private static Log _log = LogFactoryUtil.getLog("html.guestbookwebportlet.view_search_jsp");
%>