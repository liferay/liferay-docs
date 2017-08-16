# Creating a Search Results JSP for the Guestbook Portlet [](id=creating-a-search-results-jsp-for-the-guestbook-portlet)

There are several design goals to implement in the search results JSP:

- Use a search container to display guestbook entries matching a search query. 
- Make the Actions button available for each guestbook entry in the results,
just like in the main view's search container.
- Include the search bar so that users can edit their queries and resubmit them
without having to click the back link to go to the portlet's default view.

![Figure 1: The search results should appear in a Search Container and the Actions button should appear for each entry. The search bar should also be displayed.](../../../../images/guestbook-portlet-search-results.png)

Follow these steps to create the view search JSP:

1.  Create a new file called `view_search.jsp` in your `guestbook-web` module 
    project's `/guestbookwebportlet` folder and include the `init.jsp`:

        <%@include file="../init.jsp"%>

2.  Extract the `keywords` parameter from the request. This is a very 
    important parameter for `view_search.jsp` since it determines what the 
    search results will be:

        <%
          String keywords = ParamUtil.getString(request, "keywords");
          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
        %>

3.  Define the `searchURL` and `viewURL`, both of which use the `mvcPath`
    parameter that's available to Liferay MVC Portlets. 

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

    `searchURL` points to the current JSP: `view_search.jsp`. `viewURL` points
    back to the Guestbook portlet's main view. These URLs are used in
    the AUI form that you'll create next.

4.  Add an AUI form:

        <aui:form action="<%= searchURL %>" method="get" name="fm">
            <liferay-portlet:renderURLParams varImpl="searchURL" />

        <liferay-ui:header
            backURL="<%= viewURL.toString() %>"
            title="search"
        />
            
            <div class="search-form">
                <span class="aui-search-bar">
                    <aui:input inlineField="<%= true %>" label="" name="keywords" 
                    size="30" title="search-entries" type="text" />
                
                    <aui:button type="submit" value="search" />
                </span>
            </div>
        </aui:form>

    This form is identical to the one that you added to the Guestbook portlet's
    `view.jsp` except that this one contains a `<liferay-ui:header>` tag that
    displays the Back icon next to the word "Search." The `backURL` attribute in
    the header uses the `viewURL` defined above. Submitting the form invokes the
    `searchURL` with the search query entered by the user added to the URL in
    the `keywords` parameter.

5.  Add a scriptlet to get a search context and set some attributes in it:

        <%
            SearchContext searchContext = SearchContextFactory
            .getInstance(request);

            searchContext.setKeywords(keywords);
            searchContext.setAttribute("paginationType", "more");
            searchContext.setStart(0);
            searchContext.setEnd(10);

    To execute a search you need a `SearchContext` object.
    `SearchContextFactory` lets you create a `SearchContext` from the request
    object. Add the search query entered by the user to the `SearchContext` by
    passing the `keywords` URL parameter to the `setKeywords` method.  Then
    specify details about pagination and how the search results should be
    displayed.

 6. Still in the scriptlet, obtain an indexer to run a search. Retrieve the
    entry indexer from the map in @product@'s indexer registry by passing in the
    indexer's class or class name:
          
        Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

7.  In the same scriptlet, use the indexer, along with the search context, to
    run a search. 

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

    Return the search results as `Hits` objects containing pointers to
    documents that correspond to guestbook entries. Finally, loop through the
    hit documents, retrieve the corresponding guestbook entries, and add them to
    a list.

8.  At the very end of the scriptlet, retrieve a list of all the guestbooks that
    exist in the current site and create a map between the guestbook IDs and the
    guestbook names. 

                List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);
                
                Map<String, String> guestbookMap = new HashMap<String, String>();
                
                for (Guestbook guestbook : guestbooks) {
                        guestbookMap.put(Long.toString(guestbook.getGuestbookId()), guestbook.getName());
                }
        %>

    Making this single service call and creating a map is more efficient than
    making separate service calls for each guestbook entry to retrieve the
    corresponding guestbook name. Close the scriptlet.

9.  Display the search results in a search container:

        <liferay-ui:search-container delta="10" 
        emptyResultsMessage="no-entries-were-found" 
        total="<%= entries.size() %>">
                <liferay-ui:search-container-results
                        results="<%= entries %>"
                />

    Specify three attributes for the `<liferay-ui:search-container>` tag: 

    - `delta="10"` specifies that at
    most, 10 entries can appear per page. 
    - `emptyResultsMessage` specifies the message indicating there are no results
    - `total` specifies the number of search results. 

    Specify the search results in the `results` attribute of the
    `<liferay-ui:search-container-results>` tag. This is easy since you stored
    the entries resulting from the search in the `entries` list.

10. Use the `<liferay-ui:search-container-row>` tag to set the name of the class
    for which properties will displayed in each row:

                <liferay-ui:search-container-row
                        className="com.liferay.docs.guestbook.model.Entry"
                        keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">

    Use the `className` attribute for the class name, and also specify the
    `keyProperty` to indicate the primary key attribute of the entity. Use the
    `modelVar` property to specify the name of the `Entry` variable that's
    available to each search container row. Set `escapedModel` to `true` to
    ensure that each field of the `Entry` variable has been escaped (sanitized).
    This prevents potential hacks that could occur if users submitted malicious
    code into the Add Guestbook form, for example.

11. Inside the `<liferay-ui:search-container-row>` tag, specify the four
    columns to display: the guestbook entry's guestbook name, message, 
    entry name, and the actions JSP. The guestbook name is retrieved from the 
    map created in the scriptlet:

                        <liferay-ui:search-container-column-text name="guestbook"
                        value="
                        <%=guestbookMap.get(Long.toString(entry.getGuestbookId()))%>" />

                        <liferay-ui:search-container-column-text property="message" />

                        <liferay-ui:search-container-column-text property="name" />
                        
                        <liferay-ui:search-container-column-jsp
                    path="/guestbookwebportlet/entry_actions.jsp"
                    align="right" />
                </liferay-ui:search-container-row>

12.  Use the `<liferay-ui:search-iterator>` tag to iterate through the search 
    results and handle the pagination so the results are displayed properly:

                <liferay-ui:search-iterator />
        </liferay-ui:search-container>

13. At the bottom of `view_search.jsp`, declare a `Log` object for the JSP. You
    used this log in the `catch` clauses of the `try` clause that calls the
    `EntryLocalServiceUtil.getEntry` method to retrieve the guestbook entries.
    If this service call throws an exception, it's best to log the error so a
    server administrator can determine what went wrong. Liferay's convention is
    to declare custom logs for individual classes or JSPs at the bottom of the
    file:

        <%!
                private static Log _log = LogFactoryUtil.getLog("html.guestbookwebportlet.view_search_jsp");
        %>

14. Finally, your `view_search.jsp` requires some extra imports. Add the 
    following imports to `init.jsp`:

        <%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
        <%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
        <%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
        <%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
        <%@ page import="com.liferay.portal.kernel.log.Log" %>
        <%@ page import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
        <%@ page import="com.liferay.portal.kernel.search.Indexer" %>
        <%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %>
        <%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
        <%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %>
        <%@ page import="com.liferay.portal.kernel.search.Hits" %>
        <%@ page import="com.liferay.portal.kernel.search.Document" %>
        <%@ page import="com.liferay.portal.kernel.search.Field" %>
        <%@ page import="com.liferay.portal.kernel.util.StringPool" %>
        <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
        <%@ page import="com.liferay.portal.kernel.util.Validator" %>
        <%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>

        <%@ page import="java.util.ArrayList" %>
        <%@ page import="java.util.Map" %>
        <%@ page import="java.util.HashMap" %>

        <%@ page import="javax.portlet.PortletURL" %>

Good work! Your Guestbook portlet now supports search! In the next section,
you'll implement back-end search and indexing support for guestbook entities
themselves. You don't anticipate needing to search for guestbooks but you'll
create a guestbook indexer so they can take advantage of Liferay's asset
framework. Liferay's asset framework provides sets of functionality that are
common to different types of content such as blog posts, message board posts,
wiki articles, and more.
