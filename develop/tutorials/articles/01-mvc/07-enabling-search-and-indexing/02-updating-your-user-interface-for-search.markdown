# Updating Your User Interface For Search [](id=updating-your-user-interface-for-search)

Updating the Guestbook portlet's user interface for search involves two steps:

1. Updating the Guestbook portlet's default view JSP to display a search bar
   that allows users to submit queries.

2. Creating a new JSP for the Guestbook portlet that reads the search query
   entered by the user and uses it to retrieve and display search results.

You'll start by updating the Guestbook portlet's default view JSP.

## Adding a Search Bar to the Guestbook Portlet [](id=adding-a-search-bar-to-the-guestbook-portlet)

Open the guestbook-portlet project's `docroot/html/guestbook/view.jsp` file and
add the following code near the top of the file, just after the scriptlet that
grabs the `guestbookId` from the request:

    <liferay-portlet:renderURL varImpl="searchURL">
            <portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
    </liferay-portlet:renderURL>

    <aui:form action="<%= searchURL %>" method="get" name="fm">
        <liferay-portlet:renderURLParams varImpl="searchURL" />
        
        <div class="search-form">
            <span class="aui-search-bar">
                <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" />

                <aui:button type="submit" value="search" />
            </span>
        </div>
    </aui:form>

Add the following tag library declaration to `docroot/html/init.jsp`:

    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

Your first step in creating a search bar is to create a render URL that points
to the `docroot/html/guestbook/view_search.jsp`. You haven't created this file
yet, but you're constructing the URL that points to it now because you have to
specify what happens when the user submits a search query. Note that you're
using the `mvcPath` parameter since the Guestbook portlet is a Liferay MVC
portlet.

Next, you create an AUI form, which, when submitted, directs the user to the
`view_search.jsp` page which displays the search results. The
`<liferay-portlet:renderURLParams varImpl="searchURL" />` tag includes the URL
parameters of the `searchURL` as hidden input fields in the AUI form. This is
important since the parameters of the `searchURL` are overwritten when the
search query is submitted as a URL parameter.

The body of the search form consists of a `<div>` containing a `<span>` that
contains two elements: the search bar itself and the Search button. The search
bar is defined by the `<aui:input>` tag. Its `name="keywords"` attribute
specifies the name of the URL parameter that contains the search query. The
`<aui:button>` tag defines the Search button. The `type="submit"` attribute
specifies that when the button is clicked (or the *Enter* key is pressed), the
AUI form is submitted. The `value="search"` attribute specifies the name that
appears on the button.

That's all there is to the search form! When the form is submitted, the
`mvcPath` parameter pointing to the `view_search.jsp` will be included in the
URL along with the `keywords` parameter containing the search query. Now it's
time to create the `view_search.jsp` form so that the search results can be
displayed.

## Creating a Search Results JSP for the Guestbook Portlet [](id=creating-a-search-results-jsp-for-the-guestbook-portlet)

Your search results JSP should display the guestbook entries that match the
search query in a search container. The Actions button should be available for
each guestbook entry in the results. The Search bar should also appear on the
search results JSP so that users can edit their queries and resubmit them
without having to click the *Back* link to go to the portlet's default view.

![Figure 1: The search results should appear in a Search Container and the Actions button should appear for each entry. The search bar should also be displayed.](../../images/guestbook-portlet-search-results.png)

Create a new file called `view_search.jsp` in your guestbook-portlet project's
`docroot/html/guestbook` folder. Add the following contents to it:

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
        
        <div class="search-form">
            <span class="aui-search-bar">
                <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" />
            
                <aui:button type="submit" value="search" />
            </span>
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

    <liferay-ui:search-container delta="10" emptyResultsMessage="no-entries-were-found">
            <liferay-ui:search-container-results
                    results="<%= entries %>"
                    total="<%= entries.size() %>"
            />

            <liferay-ui:search-container-row
                    className="com.liferay.docs.guestbook.model.Entry"
                    keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">
                    <liferay-ui:search-container-column-text name="guestbook"
                            value="<%=guestbookMap.get(Long.toString(entry.getGuestbookId()))%>" />

                    <liferay-ui:search-container-column-text property="message" />

                    <liferay-ui:search-container-column-text property="name" />
                    
                    <liferay-ui:search-container-column-jsp
                path="/html/guestbook/guestbook_actions.jsp"
                align="right" />
            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator />
    </liferay-ui:search-container>

    <%
            if (Validator.isNotNull(keywords)) {
                    String currentURL = PortalUtil.getCurrentURL(request);
                    PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(
                                    pageContext, "search") + ": " + keywords, currentURL);
            }
    %>

    <%!
            private static Log _log = LogFactoryUtil.getLog("docroot.html.guestbook.view_search_jsp");
    %>

Your `view_search.jsp` requires some extra imports. Add the following imports to
`docroot/html/init.jsp`:

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
    <%@ page import="com.liferay.portal.util.PortalUtil" %>

    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.HashMap" %>

    <%@ page import="javax.portlet.PortletURL" %>

As with the rest of the guestbook-portlet project's JSPs, `init.jsp` is imported
at the top of `view_search.jsp`. After that, the `keywords` parameter is
extracted from the request. This is a very important parameter for
`view_search.jsp` since it determines what the search results will be!

Next, you define two URLs: `searchURL` and `viewURL`. Both of these URLs use the
`mvcPath` parameter that's available to you since the Guestbook portlet is a
Liferay MVC portlet. `searchURL` points to the current JSP: `view_search.jsp`.
`viewURL` points back to the Guestbook portlet's default view JSP. These two
URLs are used in the AUI form that follows.

This AUI form is identical to the one that you added to the Guestbook portlet's
`view.jsp` except that this one contains a `liferay-ui:header>` tag. The
`<liferay-ui:header>` tag displays the *Back* icon next to the word "Search".
Note how the `backURL` attribute uses the `viewURL` that you defined above.
Submitting the form invokes the `searchURL` with the search query entered by the
user added to the URL in the `keywords` parameter.

After the AUI form comes a scriplet. In this scriptlet, you use the `keywords`
URL parameter to actually run a search and retrieve the corresponding guestbook
entries. To execute a search in Liferay, you need a `SearchContext` object.
Liferay's `SearchContextFactory` allows you to create a `SearchContext` from the
request object. You need to add the search query entered by the user to the
`SearchContext`. This is done by the following line:

    searchContext.setKeywords(keywords);

The subsequent three lines specify details about pagination and how the search
results should be displayed.

Once your `SearchContext` has been prepared, you need to obtain an indexer to
run a search. This is done by the following line:

    Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

Indexers in Liferay's indexer registry are stored in a map and can be retrieved
by specifying the indexer's class or class name. After you've retrieved the
guestbook entry indexer, you use it, along with your search context, to run a
search. The search results are returned as `Hits` objects containing pointers to
documents that correspond to guestbook entries.

    Hits hits = indexer.search(searchContext);

Toward the end of the scriptlet, you loop through the hit documents, retrieve
the corresponding guestbook entries, and add them to a list. That's how easy it
is to obtain search results!

At the very end of the scriptlet, you retrieve a list of all the guestbooks that
exist in the current site and create a map between the guestbook IDs and the
guestbook names. Making this single service call and creating a map is more
efficient than making separate service calls for each guestbook entry to
retrieve the corresponding guestbook name.

Your next task is to display the search results. To display the search results,
you use the search container construct from Liferay's `<liferay-ui>` tag
library. You've used the search container twice already in earlier learning
paths: once in the Guestbook portlet's default view JSP and again in the
Guestbook Admin portlet's default view JSP. This is the third time.

You specify two attributes for the `<liferay-ui:search-container>` tag:
`delta="10"` specifies that at most 10 entries can appear per page. The
`emptyResultsMessage` attributes specifies the message that appears when there
are no results. The `<liferay-ui:search-container>` tag contains three subtags:
`<liferay-ui:search-container-results>`, `<liferay-ui:search-container-row>`,
and `<liferay-ui:search-iterator>`. You have to specify the search results and
the number of search results in the `results` and `total` attributes of the
`<liferay-ui:search-container-results>` tag. This is easy since you stored the
entries resulting from the search in the `entries` list.

For the `<liferay-ui:search-container-row>`, you have to specify the name of the
class for which properties will displayed in each row. You use the `className`
attribute for this. You also specify the `keyProperty` to indicate the primary
key attribute of the entity. The `modelVar` property specifies the name of the
`Entry` variable that's available to each search container row. Setting
`escapedModel` to `true` ensures that each field of the `Entry` variable has
been escaped (sanitized). This prevents potential hacks that could occur if
users submitted malicious code into the Add Guestbook form, for example.

Inside the `<liferay-ui:search-container-row>` tag, you specify the four
columns to display: the guestbook entry's guestbook name, message, entry name,
and the actions JSP. The guestbook name is retrieved from the map created in the
scriptlet. Finally, you use the `<liferay-ui:search-iterator>` tag to iterate
through the search results and handle the pagination so the results are
displayed properly.

The last two elements on the `view_search.jsp` page are a scriplet and a
declaration. The scriplet checks to see if the search query entered by the user
is not null. If it's not null, it calls the
`PortalUtil.addPortletBreadCrumbEntry` method to add the search query to the
portlet breadcrumb. The breadcrumb looks like this:

![Figure 2: You can customize the portlet breadcrumb so that it displays the search query entered by the user.](../../images/guestbook-portlet-search-breadcrumb.png)

The declaration at the bottom of `view_search.jsp` declares a `Log` object for
the JSP. You used this log in the `catch` clauses of the `try` clause that calls
the `EntryLocalServiceUtil.getEntry` method to retrieve the guestbook entries.
If this service call throws an exception, it's best to log the error so a server
administrator can review the information to try to determine what went wrong.
Liferay's convention is to declare custom logs for individual classes or JSPs at
the bottom of the file.

Good work! Your Guestbook portlet now supports search! In the next section,
you'll implement back-end search and indexing support for guestbook entities
themselves. You don't anticipate needing to search for guestbooks but you'll
create a indexer for guestbooks so that they can take advantage of Liferay's
asset framework. Liferay's asset framework provides sets of functionality that
are common to different types of content such as blog posts, message board
posts, wiki articles, and more.
