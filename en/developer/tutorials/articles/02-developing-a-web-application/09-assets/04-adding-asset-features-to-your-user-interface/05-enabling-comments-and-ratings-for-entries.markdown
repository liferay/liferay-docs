---
header-id: enabling-comments-and-ratings-for-guestbook-entries
---

# Enabling Comments and Ratings for Guestbook Entries

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Adding Asset Features to Your UI</p><p>Step 5 of 5</p>
</div>

The asset framework lets users comment on and rate assets. As with tags,
categories, and related assets, you must update the user interface to expose
these features. Good application design requires that you have a View page where
users can rate and comment on assets. Follow these steps to enable comments and
ratings on guestbook entries: 

1.  Create a new file called `view_entry.jsp` in your `guestbook-web` module 
    project's `src/main/resources/META-INF/resources/guestbook` folder. 

2.  Add a Java scriptlet to the file you just created. In this scriptlet, use an 
    `entryId` request attribute to get a `GuestbookEntry` object. For security
    reasons, convert this object to an escaped model as discussed in the earlier
    step 
    [Creating JSPs for Displaying Custom Assets in the Asset Publisher](/docs/7.2/tutorials/-/knowledge_base/t/creating-jsps-for-displaying-custom-assets-in-the-asset-publisher):

    ```markup
    <%@ include file="../init.jsp"%>

    <%
      long entryId = ParamUtil.getLong(renderRequest, "entryId");

      long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

      GuestbookEntry entry = null;

      entry = GuestbookEntryLocalServiceUtil.getGuestbookEntry(entryId);

      entryId = entry.getEntryId();

      entry = entry.toEscapedModel();

      AssetEntry assetEntry = 
      AssetEntryLocalServiceUtil.getEntry(GuestbookEntry.class.getName(), 
      entry.getEntryId());
    ```

3.  Next, update the breadcrumb entry with the current entry's name: 

    ```markup
    String currentURL = PortalUtil.getCurrentURL(request);
    PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
    currentURL);
    ```

4.  End the scriptlet by adding the names of the current entry's existing 
    asset tags as keywords to the portal page. These tag names appear in a 
    `<meta content="[tag names here]" lang="en-US" name="keywords" />` element 
    in your portal page's `<head>` section. These keywords can help search 
    engines find and index your page: 

    ```markup
        PortalUtil.setPageSubtitle(entry.getMessage(), request);
        PortalUtil.setPageDescription(entry.getMessage(), request);

        List<AssetTag> assetTags = 
        AssetTagLocalServiceUtil.getTags(GuestbookEntry.class.getName(), 
        entry.getEntryId());
        PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"), 
        request);
    %>
    ```

5.  After the scriptlet, specify the URLs for the page and back link: 

    ```markup
    <liferay-portlet:renderURL varImpl="viewEntryURL">
      <portlet:param name="mvcPath"
        value="/guestbook/view_entry.jsp" />
      <portlet:param name="entryId" value="<%=String.valueOf(entryId)%>" />
    </liferay-portlet:renderURL>

    <liferay-portlet:renderURL varImpl="viewURL">
      <portlet:param name="mvcPath"
        value="/guestbook/view.jsp" />
    </liferay-portlet:renderURL>

    <liferay-ui:header backURL="<%=viewURL.toString()%>"
      title="<%=entry.getName()%>" 
    />
    ```

6.  Next, define the page's main content. Display the guestbook's name and the 
    entry's name and message with the `<dl>`, `<dt>`, and `<dd>` tags: 

    ```markup
    <dl>
      <dt>Guestbook</dt>
      <dd><%=GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName()%></dd>
      <dt>Name</dt>
      <dd><%=entry.getName()%></dd>
      <dt>Message</dt>
      <dd><%=entry.getMessage()%></dd>
    </dl>
    ```

    This is the same way you defined the page's main content in 
    `/asset/full_content.jsp`. 

7.  Next, use a `<liferay-ui:panel-container>` tag to create a panel container. 
    Inside this tag, use a `<liferay-ui:panel>` tag to create a panel to display
    the comments and ratings components: 

    ```markup
    <liferay-ui:panel-container extended="<%=false%>"
      id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
      <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
        id="guestbookCollaborationPanel" persistState="<%=true%>"
        title="Collaboration">
    ```

8.  Add the ratings component with the `<liferay-ui:ratings>` tag:

    ```markup
    <liferay-ui:ratings className="<%=GuestbookEntry.class.getName()%>"
      classPK="<%=entry.getEntryId()%>" type="stars" />

    <br />
    ```

9.  Next, add a scriptlet to retrieve the comments discussion object:

    ```markup
    <% 
        Discussion discussion = 
        CommentManagerUtil.getDiscussion(user.getUserId(), 
        scopeGroupId, GuestbookEntry.class.getName(), 
        entry.getEntryId(), new ServiceContextFunction(request));
    %>
    ```

10.  Below that add the tag for tracking the number of comments:

    ```markup
    <c:if test="<%= discussion != null %>">
      <h2>
        <strong><liferay-ui:message arguments="<%= discussion.getDiscussionCommentsCount() %>" key='<%= (discussion.getDiscussionCommentsCount() == 1) ? "x-comment" : "x-comments" %>' /></strong>
    ```

11. Create the `liferay-comment:discussion` tag, which creates the comments
    form, *Reply* button, and retrieves the discussion content. It also
    handles the form action of posting the comment without requiring
    you to create a portlet action URL.

    ```markup
      <liferay-comment:discussion
        className="<%= GuestbookEntry.class.getName() %>"
        classPK="<%= entry.getEntryId() %>"
        discussion="<%= discussion %>"
        formName="fm2"
        ratingsEnabled="true"
        redirect="<%= currentURL %>"
        userId="<%= entry.getUserId() %>"
        />
    </c:if>

  </liferay-ui:panel>
</liferay-ui:panel-container>
    ```

12. To restrict comments and ratings access to logged-in users, wrap the whole 
    panel container in a `<c:if>` tag that tests the expression 
    `themeDisplay.isSignedIn()`:

    ```markup
    <c:if test="<%= themeDisplay.isSignedIn() %>">
        ... your panel container ...
    </c:if>
    ```

    Make sure you add the closing `</c:if>` tag after the closing 
    `</liferay-ui:panel-container>` tag.

| **Note:** Discussions (comments) are implemented as message board messages. In
| the `MBMessage` table, there's a `classPK` column. This `classPK` represents
| the guestbook entry's `entryId`, linking the comment to the guestbook. Ratings
| are stored in the `RatingsEntry` table. Similarly, the `RatingsEntry` table
| contains a `classPK` column that links the guestbook entry to the rating. Using
| a `classPK` foreign key in one table to represent the primary key of another
| table is a common pattern throughout @product@.

Next, you'll update the guestbook actions to use the new view. 

## Updating the Entry Actions JSP

Your `view_entry.jsp` page is currently orphaned. Fix this by adding the *View*
option to the Actions Menu. Open the `/guestbook/entry_actions.jsp`
and find the following line:

```markup
<liferay-ui:icon-menu>
```

Add the following lines below it:

```markup
<portlet:renderURL var="viewEntryURL">
  <portlet:param name="entryId"
    value="<%= String.valueOf(entry.getEntryId()) %>" />
  <portlet:param name="mvcPath"
    value="/guestbook/view_entry.jsp" />
</portlet:renderURL>

<liferay-ui:icon message="View" url="<%= viewEntryURL.toString() %>" />
```

Here, you create a URL that points to `view_entry.jsp`. Test this link by
selecting the *View* option in a guestbook entry's Actions Menu. Then test your
comments and ratings. 

Excellent! You've asset-enabled the guestbook and guestbook entry entities and 
enabled tags, categories, and related assets for both entities. You've also 
enabled comments and ratings for guestbook entry entities! Great job! 

![Figure 1: Now you can see comments, rating, and the full range of asset features.](../../../../images/asset-publisher-full-content-finished.png)

Your next task is to add Workflow, so you can approve or deny guestbook entries,
thereby preventing people from spamming your guestbook. 

