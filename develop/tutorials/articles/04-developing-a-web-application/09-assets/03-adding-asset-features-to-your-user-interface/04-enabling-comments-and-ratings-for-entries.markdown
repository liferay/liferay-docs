# Enabling Comments and Ratings for Guestbook Entries [](id=enabling-comments-and-ratings-for-guestbook-entries)

<div class="learn-path-step">
    <p>Adding Asset Features to Your UI<br>Step 5 of 5</p>
</div>

@product@'s asset framework lets users comment on and rate assets. As with tags, 
categories, and related assets, you must update the user interface to expose 
these features. It's best to separate the page where users comment on and rate 
assets from the page where users edit assets. You shouldn't have to edit an 
entry to comment on it; that not only makes no sense, it's a security problem. 
Comments and ratings should be added in a view mode only. 

Follow these steps to enable comments and ratings on guestbook entries: 

1.  Create a new file called `view_entry.jsp` in your `guestbook-web` module 
    project's `/guestbookwebportlet` folder. 

2.  Add a Java scriptlet to the file you just created. In this scriptlet, use an 
    `entryId` request attribute to get an entry object. For security reasons, 
    convert this object to an escaped model as discussed in the earlier step 
    [Creating JSPs for Displaying Customs Assets in the Asset Publisher](/develop/tutorials/-/knowledge_base/7.1/creating-jsps-for-displaying-custom-assets-in-the-asset-publisher):

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

3.  Next, update the breadcrumb entry with the the current entry's name: 

        String currentURL = PortalUtil.getCurrentURL(request);
        PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
        currentURL);

4.  At the end of the scriptlet, add the names of the current entry's existing 
    asset tags as keywords to the portal page. These tag names appear in a 
    `<meta content="[tag names here]" lang="en-US" name="keywords" />` element 
    in your portal page's `<head>` section. These keywords can help search 
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

6.  Next, define the page's main content. Display the guestbook's name, and the 
    entry's name and message with the `<dl>`, `<dt>`, and `<dd>` tags: 

        <dl>
          <dt>Guestbook</dt>
          <dd><%=GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName()%></dd>
          <dt>Name</dt>
          <dd><%=entry.getName()%></dd>
          <dt>Message</dt>
          <dd><%=entry.getMessage()%></dd>
        </dl>

    This is the same way you defined the page's main content in 
    `/guestbookwebportlet/full_content.jsp`. 

7.  Next, use a `<liferay-ui:panel-container>` tag to create a panel container. 
    Inside this tag, use a `<liferay-ui:panel>` tag to create a panel to display
    the comments and ratings components: 

        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

8.  Add the ratings and comments components via the `<liferay-ui:ratings>` and 
    `<liferay-ui:discussion>` tags, respectively. The latter tag needs an action 
    URL (in this case, `invokeTaglibDiscussion`) for its `formAction` attribute. 
    The action URL adds the comment after the user enters a comment and clicks 
    *Reply*: 

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

9.  To restrict comments and ratings access to logged-in users, wrap the whole 
    panel container in a `<c:if>` tag that tests the expression 
    `themeDisplay.isSignedIn()`:
    
        <c:if test="<%= themeDisplay.isSignedIn() %>">
            ... your panel container ...
        </c:if>

    Make sure you add the closing `</c:if>` tag after the closing 
    `</liferay-ui:panel-container>` tag.
    
    +$$$

    **Note:** Discussions (comments) are implemented as message board messages 
    in @product@. In the `MBMessage` table, there's a `classPK` column. This
    `classPK` represents the `entryId` of the guestbook entry the comment 
    belongs to. Ratings are stored in the `RatingsEntry` table. Similarly, the 
    `RatingsEntry` table contains a `classPK` column. This `classPK` represents 
    the `entryId` of the guestbook entry the rating belongs to. Using a 
    `classPK` foreign key in one table to represent the primary key of another 
    table is a common pattern that's used throughout @product@. 

    $$$

Next, you'll update the guestbook actions to use the new view. 

## Updating the Entry Actions JSP [](id=updating-the-entry-actions-jsp)

Nothing links to your `view_entry.jsp` page--it's currently orphaned. Fix this 
by adding the *View* option to the Actions Menu. Open the 
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
selecting the *View* option in a guestbook entry's Actions Menu. Then test that 
comments and ratings work as expected. 

Excellent! You've asset-enabled the guestbook and guestbook entry entities, and 
enabled tags, categories, and related assets for both entities. You've also 
enabled comments and ratings for guestbook entry entities! Great job! 

![Figure 1: Now you can see comments, rating, and the full range of asset features.](../../../../images/asset-publisher-full-content-finished.png)

Your next task is to generate web services. This makes it possible to write 
other clients (such as mobile applications) for the Guestbook application. 
