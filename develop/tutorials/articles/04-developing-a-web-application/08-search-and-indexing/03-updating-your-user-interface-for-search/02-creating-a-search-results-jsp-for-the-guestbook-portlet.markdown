# Creating a Search Results JSP for the Guestbook Portlet [](id=creating-a-search-results-jsp-for-the-guestbook-portlet)

<div class="learn-path-step">
    <p>Updating Your UI for Search<br>Step 2 of 2</p>
</div>

There are several design goals to implement in the search results JSP:

- Use a search container to display guestbook entries matching a search query. 
- Make the Actions button available for each guestbook entry in the results,
  like it is in the main view's search container.
- Include the search bar so that users can edit and resubmit their queries 
  without having to click the back link to go to the portlet's default view.

![Figure 1: The search results should appear in a search container, and the Actions button should appear for each entry. The search bar should also be displayed.](../../../../images/guestbook-portlet-search-results.png)

Follow these steps to create the search results JSP:

1.  Create a new file called `view_search.jsp` in your `guestbook-web` module's 
    `/guestbookwebportlet` folder. In this file, include the `init.jsp`: 

        <%@include file="../init.jsp"%>

2.  Extract the `keywords` and `guestbookId` parameters from the request. The 
    `keywords` parameter contains the search query, and the `guestbookId` 
    parameter contains the ID of the guestbook being searched: 

        <%
          String keywords = ParamUtil.getString(request, "keywords");
          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
        %>

3.  Define the `searchURL` and `viewURL` as `renderURL`s. Both use the `mvcPath` 
    parameter that's available to Liferay MVC Portlets:

        <portlet:renderURL var="searchURL">
                <portlet:param name="mvcPath" 
                value="/guestbookwebportlet/view_search.jsp" />
        </portlet:renderURL>

        <portlet:renderURL var="viewURL">
            <portlet:param 
                name="mvcPath" 
                value="/guestbookwebportlet/view.jsp" 
            />
        </portlet:renderURL>

    The `searchURL` points to the current JSP: `view_search.jsp`. The `viewURL` 
    points back to the Guestbook portlet's main view. These URLs are used in
    the AUI form that you'll create next. 

4.  Add this AUI form:

        <aui:form action="${searchURL}" name="fm">

            <liferay-ui:header backURL="${viewURL}" title="back" />

            <div class="row">
                <div class="col-md-8">
                    <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
                </div>

                <div class="col-md-4">
                    <aui:button type="submit" value="search" />
                </div>
            </div>
        </aui:form>

    This form is identical to the one that you added to the Guestbook portlet's
    `view.jsp`, except that this one contains a `<liferay-ui:header>` tag that
    displays the Back icon next to the word *Back*. The `backURL` attribute in
    the header uses the `viewURL` defined above. Submitting the form invokes the
    `searchURL` with the user's search query added to the URL in the `keywords` 
    parameter. 

5.  Start a scriptlet to get a search context and set some attributes in it: 

        <%
            SearchContext searchContext = SearchContextFactory.getInstance(request);

            searchContext.setKeywords(keywords);
            searchContext.setAttribute("paginationType", "more");
            searchContext.setStart(0);
            searchContext.setEnd(10);

    To execute a search, you need a `SearchContext` object. 
    `SearchContextFactory` creates a `SearchContext` from the request
    object. Add the user's search query to the `SearchContext` by passing the 
    `keywords` URL parameter to the `setKeywords` method. Then specify details 
    about pagination and how the search results should be displayed. 

6.  Still in the scriptlet, obtain an `Indexer` to run a search. Retrieve the 
    entry indexer from the map in @product@'s indexer registry by passing in the
    indexer's class or class name:
          
        Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

7.  In the same scriptlet, use the indexer and the search context to run a 
    search: 

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

    The search results return as `Hits` objects containing pointers to documents 
    that correspond to guestbook entries. You then loop through the hit 
    documents, retrieving the corresponding guestbook entries and adding them to
    a list. 

8.  Finish the scriptlet by retrieving a list of all the guestbooks that exist 
    in the current site. Create a map between the guestbook IDs and the 
    guestbook names. 

                List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

                Map<String, String> guestbookMap = new HashMap<String, String>();

                for (Guestbook guestbook : guestbooks) {
                        guestbookMap.put(Long.toString(guestbook.getGuestbookId()), guestbook.getName());
                }
        %>

    Making this single service call and creating a map is more efficient than 
    making separate service calls for each guestbook. 

9.  Display the search results in a search container:

        <liferay-ui:search-container delta="10" 
            emptyResultsMessage="no-entries-were-found" 
            total="<%= entries.size() %>">
                <liferay-ui:search-container-results
                        results="<%= entries %>"
        />

    This specifies three attributes for the `<liferay-ui:search-container>` tag: 

    - `delta="10"`: specifies that at most, 10 entries can appear per page. 
    - `emptyResultsMessage`: specifies the message indicating there are no 
      results. 
    - `total`: specifies the number of search results. 

    The `results` attribute of the tag `<liferay-ui:search-container-results>` 
    specifies the search results. This is easy since you stored the entries 
    resulting from the search in the `entries` list. 

10. Use the `<liferay-ui:search-container-row>` tag to set the name of the class 
    whose properties are displayed in each row:

        <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry"
                keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">

    This uses the `className` attribute for the class name and specifies the
    entity's primary key attribute in the `keyProperty` attribute. The 
    `modelVar` property specifies the name of the `Entry` variable that's
    available to each search container row. To ensure that each field of the 
    `Entry` variable is escaped (sanitized), the `escapedModel` is `true`. This 
    prevents potential hacks that could occur if users submitted malicious code 
    into the Add Guestbook form, for example. 

11. Inside the `<liferay-ui:search-container-row>` tag, specify the four columns 
    to display: the guestbook entry's guestbook name, message, entry name, and 
    the actions JSP. The guestbook name is retrieved from the map created in the 
    scriptlet: 

            <liferay-ui:search-container-column-text name="guestbook"
                value="<%=guestbookMap.get(Long.toString(entry.getGuestbookId()))%>" />

            <liferay-ui:search-container-column-text property="message" />

            <liferay-ui:search-container-column-text property="name" />
                    
            <liferay-ui:search-container-column-jsp
                path="/guestbookwebportlet/entry_actions.jsp"
                align="right" />
        </liferay-ui:search-container-row>

12. Use the `<liferay-ui:search-iterator>` tag to iterate through the search 
    results and handle pagination. Close the search container tag:

                <liferay-ui:search-iterator />
        </liferay-ui:search-container>

13. At the bottom of `view_search.jsp`, declare a `Log` object. You used this 
    log in the `catch` clauses of the `try` clause that calls the 
    `EntryLocalServiceUtil.getEntry` method to retrieve the guestbook entries. 
    If this service call throws an exception, it's best to log the error so a
    server administrator can determine what went wrong. @product@'s convention 
    is to declare custom logs for individual classes or JSPs at the bottom of 
    the file: 

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

Good work! The Guestbook portlet now supports search! Now your users can find
those Guestbook Entries they were looking for. 

![Figure x: The Guestbook Application now support searching for Guestbook Entries.](../../images/guestbook-portlet-search-conclusion.png)

Once indexing is in place, the asset framework can be added to the Guestbook
application. It provides functionality that's shared across different types of
content like blog posts, message board posts, wiki articles, and more. This is
the heart of integration with @product@'s development platform. 
