# Enabling Comments and Ratings for Guestbook Entries [](id=enabling-comments-and-ratings-for-guestbook-entries)

Liferay's asset framework allows users to comment on and rate assets. As with
tags, categories, and related assets, since you already asset-enabled guestbook 
entries in the service layer, your only remaining task is to update your user 
interface to allow access to these features. It's best to separate the page 
where users comment on and rate assets from the page where users actually edit 
the assets themselves. If you added the commenting and rating functionality to 
the Guestbook portlet's `edit_entry.jsp` page, users might confuse the
collaboration fields with the content fields. It's easy to imagine scenarios
where users should be able to view, comment on, and rate assets without being
able to actually edit the assets.

Create a new file called `view_entry.jsp` in your guestbook-web module project's 
`/html/guestbookmvcportlet` folder. You'll edit the 
`/html/guestbookmvcportlet/guestbook_actions.jsp` to include a new *View* option 
in the Actions Menu that points to the new `view_entry.jsp` file. Add the 
following contents to `view_entry.jsp`:

<%@ include file="../init.jsp" %>

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

AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
Entry.class.getName(), entry.getEntryId());

PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
                currentURL);

PortalUtil.setPageSubtitle(entry.getMessage(), request);
PortalUtil.setPageDescription(entry.getMessage(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
                Entry.class.getName(), entry.getEntryId());
PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
                request);

%>

<liferay-portlet:renderURL varImpl="viewEntryURL">
	<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view_entry.jsp" />
	<portlet:param name="entryId" value="<%= String.valueOf(entryId) %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="viewURL">
    <portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:header
    backURL="<%= viewURL.toString() %>"
   	title="<%= entry.getName() %>"
/>

    <dl>
            <dt>Guestbook</dt>
            <dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName() %></dd>
            <dt>Name</dt>
            <dd><%= entry.getName() %></dd>
            <dt>Message</dt>
            <dd><%= entry.getMessage() %></dd>
    </dl>

    <c:if test="<%= themeDisplay.isSignedIn() %>">
            <liferay-ui:panel-container extended="<%= false %>"
                    id="guestbookCollaborationPanelContainer" persistState="<%= true %>">
                    <liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
                    id="guestbookCollaborationPanel" persistState="<%= true %>"
                    title="Collaboration">
                            <liferay-ui:ratings className="<%= Entry.class.getName() %>"
                                    classPK="<%= entry.getEntryId() %>" type="stars" />
                            
                            <br />

                            <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
            
                            <liferay-ui:discussion className="<%= Entry.class.getName() %>"
                        classPK="<%= entry.getEntryId() %>"
                        formAction="<%= discussionURL %>" formName="fm2"
                        ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
                        userId="<%= entry.getUserId() %>" />

                    </liferay-ui:panel>
            </liferay-ui:panel-container>
    </c:if>

    <liferay-ui:asset-links
            assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
            className="<%= Entry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>" />

In the scriptlet, you use the `entryId` request attribute to get a entry
object. You convert it to an escaped model for security reasons, as discussed
earlier. Next, you update your portlet's breadcrumb entry with the name of the
current entry. 

<!-- Need to check if the info below is true... -->

![Figure 3: The Breadcrumb portlet appears on regular portal pages, by default. It appears just beneath the main page navigation menu and displays the path to the current page or portlet.](../../../images/portlet-breadcrumb.png)

At the end of the scriptlet, you add the names of the existing asset tags for
the current entry as keywords to the portal page. These tag names appear in
a `<meta content="[tag names here]" lang="en-US" name="keywords" />` element in
the `<head>` section of your portal page. These keywords can help search engines
find and index your page.

After the scriptlet, you define the main content of your page. You're simply
displaying your guestbook's name, and entry's name and message  with `<dl>`, 
`<dt>`, and `<dd>` tags the same way you did in 
`/html/guestbookmvcportlet/full_content.jsp`.

Next, you use  `<liferay-ui:panel-container>` tag to create a panel container.
Inside this tag, you use a `<liferay-ui:panel>` tag to create a panel containing
the comments and ratings components. The ratings component is implemented via
the `<liferay-ui:ratings>` tag. The comments tag is implemented via the
`<liferay-ui:discussion>` tag. Note that the `<liferay-ui:discussion>` tag
requires an action URL to be supplied for its `formAction` attribute. The
`invokeTaglibDiscussion` action URL is responsible for actually adding the
comment after the user clicks *Add Comment*, enters a comment, and clicks
*Reply*.

+$$$

**Note:** Discussions (comments) are implemented as message board messages in
Liferay. In Liferay's `MBMessage` table, there's a `classPK` column. This
`classPK` represents the `entryId` of the guestbook entry to which the comment 
was added. Ratings are stored in Liferay's `RatingsEntry` table. The 
`RatingsEntry` table contains a `classPK` column where the `classPK` represents 
the `entryId` of the guestbook entry to which the rating was added. Using a 
`classPK` foreign key in one table to represent the primary key of another table 
is a common pattern that's used throughout Liferay.

$$$

Note that the whole panel container is wrapped in a `<c:if>` tag.  You're
restricting access to comments and ratings to users who have signed in with a
portal account. You're checking this with the following expression:

    <%= themeDisplay.isSignedIn() %>

At the end of the page, you're displaying the related assets of the current
entry. Note that you're using the `<liferay-ui:asset-links>` tag to
displayed related assets. This tag is distinct from the
`<liferay-ui:input-asset-links>` that you used in `edit_guestbook.jsp` that
allowed the user to select related assets.

Your `view_entry.jsp` page is currently orphaned. Fix this by adding the
View option to the Actions Menu. Open your 
`html/guestbookmvcportlet/guestbook_actions.jsp` and find the following line:

    <liferay-ui:icon-menu>

Add the following lines below:

    <portlet:renderURL var="viewEntryURL">
      <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
      <portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view_entry.jsp" />
    </portlet:renderURL>

    <liferay-ui:icon
      message="View"
      url="<%= viewEntryURL.toString() %>"
    />

Here, you're simply creating a URL that points to the `view_entry.jsp` that
you created and adding an `url` attribute that points to this URL. Test this
link by selecting the *View* option in the Actions Menu for a guestbook entry. 
Then test that comments and ratings work as expected. Excellent! You've
asset-enabled your guestbook and guestbook entry entities! And you've enabled
tags, categories, related assets, for both entities, and comments and ratings 
for guestbook entry entities!