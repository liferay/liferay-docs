# Enabling Comments and Ratings for Guestbook Entries [](id=enabling-comments-and-ratings-for-guestbook-entries)

Liferay's asset framework includes comments and ratings features. As with tags,
categories, and related assets, all you must do is update the user interface to
expose these features. It's best to separate the page where users comment on and
rate assets from the page where users edit the assets. You shouldn't have to
edit an entry just to comment on it; that not only makes no sense, but also is a
security problem. Comments and ratings should be added in a view mode only. 

Follow these steps to enable comments and ratings on guestbook entries:

1.  Create a new file called `view_entry.jsp` in your `guestbook-web` module 
    project's `/guestbookwebportlet` folder. 

2.  Add a Java scriptlet to the file you just created. Use an `entryId` 
    request attribute to get an entry object and convert it to an escaped 
    model for security reasons, as discussed in the 
    [Creating JSPs for Displaying Customs Assets in the Asset Publisher](/develop/tutorials/-/knowledge_base/7.0/creating-jsps-for-displaying-custom-assets-in-the-asset-publisher) 
    section earlier:
    
        <%@ include file="../init.jsp"%>

        <%
          long entryId = ParamUtil.getLong(renderRequest, "entryId");

          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

          Entry entry = null;

          if (entryId > 0) {
            entry = EntryLocalServiceUtil.getEntry(entryId);

            entryId = entry.getEntryId();
          }

          entry = EntryLocalServiceUtil.getEntry(entryId);
          entry = entry.toEscapedModel();

          AssetEntry assetEntry = 
          AssetEntryLocalServiceUtil.getEntry(Entry.class.getName(), 
          entry.getEntryId());
 
3.  Next, update the breadcrumb entry with the name of the current entry:

        String currentURL = PortalUtil.getCurrentURL(request);
        PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
        currentURL);
 
    ![Figure 1: The Breadcrumb portlet appears on regular portal pages, by default. It appears just beneath the main page navigation menu and displays the path to the current page or portlet.](../../../../images/portlet-breadcrumb.png)

4.  At the end of the scriptlet, add the names of the existing asset tags for
    the current entry as keywords to the portal page. These tag names appear in
    a `<meta content="[tag names here]" lang="en-US" name="keywords" />` element 
    in the `<head>` section of your portal page. These keywords can help search 
    engines find and index your page:

          PortalUtil.setPageSubtitle(entry.getMessage(), request);
          PortalUtil.setPageDescription(entry.getMessage(), request);

          List<AssetTag> assetTags = 
          AssetTagLocalServiceUtil.getTags(Entry.class.getName(), 
          entry.getEntryId());
          PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"), 
          request);
        %>

5.  After the scriptlet, specify the URLs for the page and back link:

        <liferay-portlet:renderURL varImpl="viewEntryURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view_entry.jsp" />
          <portlet:param name="entryId" value="<%=String.valueOf(entryId)%>" />
        </liferay-portlet:renderURL>

        <liferay-portlet:renderURL varImpl="viewURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view.jsp" />
        </liferay-portlet:renderURL>

        <liferay-ui:header backURL="<%=viewURL.toString()%>"
          title="<%=entry.getName()%>" 
        />
 
6.  Next, define the page's main content. Display the guestbook's name, 
    the entry's name, and message  with `<dl>`, `<dt>`, and `<dd>` tags the same 
    way you did in `/guestbookwebportlet/full_content.jsp`:

        <dl>
          <dt>Guestbook</dt>
          <dd><%=GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName()%></dd>
          <dt>Name</dt>
          <dd><%=entry.getName()%></dd>
          <dt>Message</dt>
          <dd><%=entry.getMessage()%></dd>
        </dl>

7.  Next, use a `<liferay-ui:panel-container>` tag to create a panel container. 
    Inside this tag, use a `<liferay-ui:panel>` tag to create a panel to display
    the comments and ratings components:

        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

8.  Next, add the ratings and comments components. The ratings component is 
    implemented via the `<liferay-ui:ratings>` tag. The comments component is 
    implemented via the `<liferay-ui:discussion>` tag. The 
    `<liferay-ui:discussion>` tag requires an action URL for its `formAction` 
    attribute, `invokeTaglibDiscussion` action URL in this case. The action URL 
    adds the comment after the user clicks enters a comment and clicks *Reply*:
    
            <liferay-ui:ratings className="<%=Entry.class.getName()%>"
              classPK="<%=entry.getEntryId()%>" type="stars" />

            <br />

            <portlet:actionURL name="invokeTaglibDiscussion" 
            var="discussionURL" />

            <liferay-ui:discussion className="<%=Entry.class.getName()%>"
              classPK="<%=entry.getEntryId()%>"
              formAction="<%=discussionURL%>" formName="fm2"
              ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
              userId="<%=entry.getUserId()%>" />

          </liferay-ui:panel>
        </liferay-ui:panel-container>
 
9.  Wrap the whole panel container in a `<c:if>` tag that test if the user is 
    logged in. This restricts access to comments and ratings to users who have 
    signed in with a portal account. Add the following expression to check for 
    this:
    
        <c:if test="<%= themeDisplay.isSignedIn() %>">
 
    Make sure you add the closing `</c:if>` tag after the closing 
    `</liferay-ui:panel-container>` tag.
    
    +$$$

    **Note:** Discussions (comments) are implemented as message board messages 
    in @product@. In the `MBMessage` table, there's a `classPK` column. This
    `classPK` represents the `entryId` of the guestbook entry to which the 
    comment was added. Ratings are stored in the `RatingsEntry` table. The 
    `RatingsEntry` table contains a `classPK` column where the `classPK` 
    represents the `entryId` of the guestbook entry to which the rating was 
    added. Using a `classPK` foreign key in one table to represent the primary 
    key of another table is a common pattern that's used throughout @product@.

    $$$

10. Finally, display the current entry's related assets at the end of the page.
    Note that you're using the `<liferay-ui:asset-links>` tag to display related
    assets. This tag is distinct from the `<liferay-ui:input-asset-links>` that
    you used in `edit_guestbook.jsp` for selecting related assets:
    
        <liferay-ui:asset-links
          assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
          className="<%=Entry.class.getName()%>"
          classPK="<%=entry.getEntryId()%>" />

Next, you'll update the guestbook actions to use the new view. 

## Updating the Entry Actions JSP

Your `view_entry.jsp` page is currently orphaned, because nothing links to it.
Fix this by adding the *View* option to the Actions Menu. Open the
`/guestbookwebportlet/entry_actions.jsp` and find the following line:

    <liferay-ui:icon-menu>

Add the following lines below it:

    <portlet:renderURL var="viewEntryURL">
      <portlet:param name="entryId"
        value="<%= String.valueOf(entry.getEntryId()) %>" />
      <portlet:param name="mvcPath"
        value="/guestbookwebportlet/view_entry.jsp" />
    </portlet:renderURL>

    <liferay-ui:icon message="View" url="<%= viewEntryURL.toString() %>" />

Here, you create a URL that points to `view_entry.jsp`. Test this link by
selecting the *View* option in the Actions Menu for a guestbook entry.  Then
test that comments and ratings work as expected. 

Excellent! You've asset-enabled the guestbook and guestbook entry entities, and 
you've enabled tags, categories, related assets, for both entities, and comments
and ratings for guestbook entry entities! Great job! 

Your next task is to generate web services. This makes it possible to write
other clients (such as mobile applications) for the Guestbook. 
