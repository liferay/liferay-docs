# Updating Your User Interface For Search

Updating the Guestbook portlet's user interface for search involves two steps:

1. Updating the Guestbook portlet's default view JSP to display a search bar
   which allows users to submit queries.

2. Creating a new JSP for the Guestbook portlet that reads the search query
   entered by the user and uses it to retrieve and display search results.

You'll start by updating the Guestbook portlet's default view JSP.

## Adding a Search Bar to the Guestbook Portlet

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

Add the following import to `docroot/html/init.jsp`:

    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

Your first step in creating a search bar is to create a render URL that points
to the `docroot/html/guestbook/view_search.jsp`. You haven't created this file
yet but you're constructing the URL that points to it now because you have to
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

## Creating a Search Results JSP for the Guestbook Portlet

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
    %>

    <liferay-ui:search-container delta="10" emptyResultsMessage="no-entries-were-found">
            <liferay-ui:search-container-results
                    results="<%= entries %>"
                    total="<%= entries.size() %>"
            />

            <liferay-ui:search-container-row
                    className="com.liferay.docs.guestbook.model.Entry"
                    modelVar="searchEntry"
            >
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
`view.jsp` except that this one contains a `liferay-ui:header>` tag. 
