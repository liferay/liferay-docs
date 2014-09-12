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

The first thing you define here is a render URL that points to the
`docroot/html/guestbook/view_search.jsp`. You haven't created this file yet but
you're constructing the URL that points to it now because you have to specify
what happens when the user submits a search query. Note that you're using the
`mvcPath` parameter since the Guestbook portlet is a Liferay MVC portlet.

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


