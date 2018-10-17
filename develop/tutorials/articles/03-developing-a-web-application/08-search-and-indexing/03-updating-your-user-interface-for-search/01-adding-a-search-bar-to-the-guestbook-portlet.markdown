# Adding a Search Bar to the Guestbook Portlet [](id=adding-a-search-bar-to-the-guestbook-portlet)

<div class="learn-path-step">
    <p>Updating Your UI for Search<br>Step 1 of 2</p>
</div>

Create the search bar UI for the Guestbook portlet:

1.  In `guestbook-web`, open the file 
    `src/main/resources/META-INF/resources/guestbookwebportlet/view.jsp`. Add a 
    render URL near the top of the file, just after the scriptlet that gets the 
    `guestbookId` from the request:

        <liferay-portlet:renderURL varImpl="searchURL">
            <portlet:param name="mvcPath" 
            value="/guestbookwebportlet/view_search.jsp" />
        </liferay-portlet:renderURL>

    The render URL points to `/guestbookwebportlet/view_search.jsp` (created in
    the next Learning Path step). You construct the URL first to specify what
    happens when the user submits a search query. 

2.  Right after the render URL, create an AUI form that adds an input field for
    search keywords and a *Submit* button that executes the form action, which
    is mapped to the `searchURL`.

        <aui:form action="${searchURL}" name="fm">

            <div class="row">
                <div class="col-md-8">
                    <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
                </div>

                <div class="col-md-4">
                    <aui:button type="submit" value="search" />
                </div>
            </div>
        </aui:form>

        <aui:form action="<%= searchURL %>" method="get" name="fm">
            <liferay-portlet:renderURLParams varImpl="searchURL" />

            <div class="search-form">
                <span class="aui-search-bar">
                    <aui:input inlineField="<%= true %>" label="" 
                    name="keywords" size="30" title="search-entries" type="text"
                    />

                    <aui:button type="submit" value="search" />
                </span>
            </div>
        </aui:form>

    The body of the search form consists of a `<div>` with one row containing
    two fields: an input field, named `keywords` and a _Submit_ button. Its
    `name="keywords"` attribute specifies the name of the URL parameter that
    contains the search query. The `<aui:button>` tag defines the search button.
    The `type="submit"` attribute specifies that when the button is clicked (or
    the *Enter* key is pressed), the AUI form is submitted. The `value="search"`
    attribute specifies the name that appears on the button. 

That's all there is to the search form! When the form is submitted, the
`mvcPath` parameter pointing to the `view_search.jsp` is included in the URL
along with the `keywords` parameter containing the search query. Next create the
`view_search.jsp` file to display the search results. 
