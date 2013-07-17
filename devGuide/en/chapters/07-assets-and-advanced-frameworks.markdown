
# Assets and advanced frameworks

<!-- We'll rename this chapter eventually. Most of this content was from the
former "Liferay APIs" and "Liferay Frameworks" chapters - Jim --> 

Picture a hot, summer day. You're on vacation, and you're just coming back from
the beach after a day of frolicking on the sand and in the water. After all that
activity, you're hungry. Time to grill up some burgers and dogs. 

To grill hamburgers and hot dogs, you have to have a proper procedure and
apparatus for accomplishing the task. The *procedure* would be called an
algorithm in computer science terms. The *apparatus* is the framework. 

You first need a grill. That grill should be equipped with a heating mechanism;
in the case of most grills, that's either charcoal or propane gas. Obviously,
it also has a metal frame, or grill, placed near the heat. You also need tools,
such as a spatula, a plate to hold the meat, and if you're making chicken in
addition to those burgers, a brush and some barbecue sauce. 

All of these together form a *framework* for making grilled food on a hot
summer day. All the tools you need are at your disposal; you just need to pick
them up and get grilling! If the framework is already in place, it's obviously
a lot easier (and more timely) to cook food than it would be if the framework
weren't there. Just ask the cave men. 

Liferay contains several frameworks that give you all the tools you need to
perform various common tasks, such has handling permissions, letting users
enter comments, categories, and tags, and other common tasks that Liferay
doesn't make you have to write yourself. 

Here's what we've got in store for you: 

- Asset Framework 

<!--- File Storage Framework -->

- Other Frameworks 

- Device Detection

- Using Message Bus

Let's get started with a framework that nobody relishes having to write
themselves: security. 

<!-- I took out the Service Builder section as it is superceded by the work
Jesse is doing. -Rich -->
## Asset Framework 

Liferay's asset framework is a system that allow you to add common functionality
to your application. For example, you might build an event management
application that shows a list of upcoming events. It might be nice to be able to
tag or categorize those events to provide users with metadata describing more
about them. Or you might want to let users comment on events. 

This common functionality is what Liferay's asset framework gives you. Using the
power of Liferay's built-in message boards, tags, and categories, Liferay lets
you infuse your application with these features in no time. 
 
The term *asset* is a generic term referring to any type of content,
including text, an external file, a URL, an image, or a record in an online
book library. Consequently, when we use the term *asset* here, we're referring
to some type of Liferay content, like documents, blog entries, bookmarks, wiki
pages, or anything you create in your applications. 

Here are the features you can reuse thanks to the asset framework: 

-   Associate tags to custom content types. New tags are created automatically
    when the author assigns them to the content. 

-   Associate categories to custom content types. Authors are only allowed to
    select from predefined categories within several predefined vocabularies. 

-   Manage tags from the control panel, including merging tags. 

-   Manage categories from the control panel, including creating complex
    hierarchies. 

-   Associate comments with assets. 

-   Rate assets using a five star rating system. 

-   Assign social bookmarks to assets, including via tweet, Facebook like, or +1
    (Google Plus). 

-   Add custom fields to assets. 

-   Relate assets to one another. 

-   Flag asset content as inappropriate. 

-   Keep track of the number of visualizations of an asset. 

-   Integrate workflow with assets. 

-   Publish your content using the Asset Publisher portlet. Asset Publisher can
    publish dynamic asset lists or manually selected asset lists. It can also
    show an asset summary view with a link to the full view. This saves you time,
    since it likely won't be necessary to develop custom portlets for your custom
    content types. 

At this point you might be saying, "Asset Framework sounds great; but how do I
leverage all these awesome functions?" Excellent question, young padawan, and
perfect timing; we couldn't have said it better ourselves. 

We'll describe the first two briefly here before we dive in head first: 

-   The first step is mandatory; you must let the framework know whenever one of
    your custom content entries is added, updated or deleted. 

-   The second step enables the asset framework in the UI: you can use a
    set of taglibs to provide widgets that allow authors to enter comments, tags
    and categories, as well as how to show the entered tags and categories along
    with the content. 

<!--I don't understand the second half of the second step, after the comma.
-Russ -->

<!-- It was confusingly written; hopefully I fixed it. -Rich -->

<!--It's still confusing to me, as it says taglibs provide widgets that allow
authors to enter comments, tags and categories, as well as how to...I wasn't
expecting a how to here and it doesn't work, does it? since it's originally
saying taglibs provide widgets that allow...-->

Next let's dive head first into the first step; informing the Asset Framework
when you add, update, or delete assets. 

#### Adding, updating and deleting assets 

Whenever you create a new entity, you need to let the Asset Framework know. In
this sense, it's similar to permission resources. It's a simple procedure: you
invoke a method of the Asset Framework that adds an `AssetEntry` so that Liferay
can keep track of the asset. 

Specifically, you should access these methods using either the static methods
of `AssetLocalServiceUtil` or an instance of the `AssetEntryLocalService`
injected by Spring. To simplify this section, we'll be using the static methods
of `AssetLocalServiceUtil`, since it doesn't require any special setup in your
application. 

<!-- This is wrong. We should show them the right way to do it, rather than the
way that's easier to document. Remember that developers will be taking direction
from our text and writing actual code based on it. The right way is to add a
<reference> tag to the service.xml and inject the service with Spring. In fact,
the example below does just that (since it's Liferay's best practice), so the
example code here doesn't even match what we just stated. We need to fix this.
-Rich--> 

The method to invoke when one of your custom content entries is added or
updated is the same, and is called `updateEntry`. Here's the full signature: 

    AssetEntry updateEntry(
            long userId, long groupId, String className, long classPK,
            String classUuid, long classTypeId, long[] categoryIds,
            String[] tagNames, boolean visible, Date startDate, Date endDate,
            Date publishDate, Date expirationDate, String mimeType,
            String title, String description, String summary, String url,
            String layoutUuid, int height, int width, Integer priority,
            boolean sync)
        throws PortalException, SystemException

Here's an example of this method's invocation extracted from the built in
blogs portlet: 

    assetEntryLocalService.updateEntry(
            userId, entry.getGroupId(), BlogsEntry.class.getName(),
            entry.getEntryId(), entry.getUuid(), 0, assetCategoryIds,
            assetTagNames, visible, null, null, entry.getDisplayDate(), null,
            ContentTypes.TEXT_HTML, entry.getTitle(), null, summary, null, null,
            0, 0, null, false);

Here's a quick summary of the most important parameters of this method: 

-   `userId` is the identifier of the user who created the content. 

-   `groupId` identifies the scope of the created content. If your content
    doesn't support scopes (extremely rare), just pass `0` as the value. 

-   `className` identifies the type of asset. The recommended convention is to
    use the name of the Java class that represents your content type, but you can
    actually use any String you want as long as you are sure that it is unique. 

-   `classPK` identifies the specific content being created among others of the
    same type. It's usually the primary key of the table where the custom content
    is stored. If you want, you can use the *classUuid* parameter to specify a
    secondary identifier; it's guaranteed to be universally unique. It's especially
    useful if your content will be exported and imported across separate portals. 

-   `assetCategoryIds` and `assetTagNames` represent the categories and tags
    selected by the author of the content. The Asset Framework will store them
    for you. 

-   `visible` specifies whether the content should be shown at all by Asset
    Publisher. 

-   `title,` `description` and `summary` are descriptive fields used by the Asset
    Publisher when displaying entries of your content type. 

-   `publishDate` and `expirationDate`, when specified, tell Asset Publisher it
    shouldn't show the content before a given publication date or after a given
    expiration date, respectively. 

-   All other fields are optional; it won't always make sense to include them.
    The `sync` parameter should always be *false* unless you're doing something
    very advanced (feel free to look at the code if you're really curious). 

<!-- We should fully describe the sync parameter, as well as any others.
Otherwise, it looks like we're holding back information for no apparent reason.
-Rich -->

When one of your custom content entries is deleted, you should once again let
Asset Framework know. That way it can clean up stored information and make sure
that the Asset Publisher doesn't show any information for the content that has
been deleted. The signature of method to delete an asset entry is: 

    void deleteEntry(String className, long classPK)

Here's an example invocation extracted again from the blogs portlet: 

    assetEntryLocalService.deleteEntry(
        BlogsEntry.class.getName(), entry.getEntryId());

Now that you can create and modify assets, consider tagging and categorizing
them. Let's tackle that next. 

#### Entering and displaying tags and categories 

In the last section we let Asset Framework know about the tags and categories
that we associated with a given asset; but how does a content author specify
the tags and categories? 

Liferay provides a set of JSP tags you can use to make this task very easy. You
can put the following Liferay UI tags in your forms to create content that can
be associated with new or existing tags or predefined categories: 

    <label>Tags</label>
    <liferay-ui:asset-tags-selector
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
        <liferay-ui:asset-categories-selector
            className="<%= entry.getClass().getName() %>"
            classPK="<%= entry.getPrimaryKey() %>"
    />

These two taglibs create appropriate form controls that allow the user to
search for a tag or create a new one or select an existing category. 

---

![tip](../../images/tip-pen-paper.png) **Tip:** If you're using Liferay's Alloy
UI Form taglibs, creating fields to enter tags and categories is even simpler.
You just use `<aui:input name="tags" type="assetTags" />` and `<aui:input
name="categories" type="assetCategories" />`, respectively. 

---

Once the tags and categories have been entered, you'll want to show them along
with the content of the asset. Here's how to display the tags and categories: 

    <label>Tags</label>
    <liferay-ui:asset-tags-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
    <liferay-ui:asset-categories-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

In both JSP tags, you can also specify a `portletURL` parameter; each tag that
uses it will be a link containing the `portletURL` *and* `tag` or `categoryId`
parameter value, respectively. This supports tags navigation and categories
navigation within your portlet. You'll need to implement the look-up
functionality in your portlet code; do this by reading the values of those two
parameters and using the `AssetEntryService` to query the database for entries
based on the specified tag or category. 

<!-- An example of this would be really nice. -Rich -->

Great job! You'll have no problem associating tags and categories with your
assets. Before we go further with our example, let's take a look at more JSP
tags you can use to leverage Asset Framework's features. 

#### More JSP tags for assets 

In addition to tags and categories, there are more features that Asset Framework
provides. These features allow users to do the following with your assets: 

-   Add comments

-   Rate comments of other users

-   Rate assets

-   Apply social bookmarks (e.g. via tweet, Facebook like, or +1 (Google Plus))

-   Relate assets to one another

-   Flag content as inappropriate and notify the portal administrator

There are JSP tags, called *Liferay UI* tags, associated with each feature. You
can find these tags used in the JSPs for Liferay's built-in portlets (e.g. the
`edit_entry.jsp` of the Blogs portlet).  Here are some examples of the JSP tags
from the Blogs portlet: 

-   *Comments and comment ratings:* 

        <portlet:actionURL var="discussionURL">
            <portlet:param name="struts_action" value="/blogs/edit_entry_discussion" />
        </portlet:actionURL>

        <liferay-ui:discussion
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            formAction="<%= discussionURL %>"
            formName="fm2"
            ratingsEnabled="<%= enableCommentRatings %>"
            redirect="<%= currentURL %>"
            subject="<%= entry.getTitle() %>"
            userId="<%= entry.getUserId() %>"
        />

-   *Rate assets:* 

        <liferay-ui:ratings
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
        />

-   *Social Bookmarks:* 

        <liferay-ui:social-bookmarks
            displayStyle="<%= socialBookmarksDisplayStyle %>"
            target="_blank"
            title="<%= entry.getTitle() %>"
            url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay) %>"
        />

-   *Related assets:* 

        <liferay-ui:input-asset-links
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entryId %>"
        />

-   *Flag as inappropriate:* 

        <liferay-ui:flags
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            contentTitle="<%= entry.getTitle() %>"
            reportedUserId="<%= entry.getUserId() %>"
        />

<!-- The above features need more explanation and sections of their own. We
don't need to do this now, but we should do it later, for the 6.2 release. -Rich
-->

With Liferay's taglib tags, you can easily apply these features to your assets.
No problemo, right? So let's get the assets published in your portal. 

#### Publishing assets with Asset Publisher 

A huge benefit of using the asset framework is that you can leverage the Asset
Publisher portlet to publish lists of your custom asset types. You can choose
to have users specify lists dynamically (e.g., based on the asset tags or
categories) or have administrators do it statically. 

To display your assets, the Asset Publisher needs to know how to access their
metadata. You also need to provide the Asset Publisher templates for the types
of views (e.g. *full* view and *abstract* view) available to display your
assets. You can provide all this to the Asset Publisher by implementing these
two interfaces: 

-   `AssetRendererFactory`: A class that knows how to retrieve specific assets
    from persistent storage using the `classPK`. The `classPK` is typically the
    asset's primary key, but can be anything you specified to the `updateAsset`
    method, which you use to add or update the asset. Your factory implementation
    can grab the asset from a `groupId` (identifies a scope of data) and a
    `urlTitle` (a title that can be used in friendly URLs to refer uniquely to the
    asset within a given scope). Finally, the asset renderer factory can provide a
    URL for the Asset Publisher to use when a user wants to add a new asset of your
    custom type. This URL should point to your own portlet.  There are other less
    important methods of the interface, but you can avoid implementing them by
    extending `BaseAssetRendererFactory`. By extending this base class instead of
    implementing the interface directly, your code will be more robust to possible
    interface changes in future versions of Liferay, since the base implementation
    will be updated to accommodate the interface changes. 

-   `AssetRenderer`: This is an interface that provides metadata information
    about one specific asset. It checks whether the current user has permission
    to edit or view the asset and renders the asset for the different templates
    (e.g. abstract and full content view) by forwarding to a specific JSP. We
    recommend that you extend the `BaseAssetRenderer` class rather than directly
    implementing the interface. The base class provides helpful defaults and 
    contains methods that get added to the interface in the future. 

Let's look at an example of these two classes. We'll use Liferay's Blogs
portlet again, and we'll start by implementing `AssetRendererFactory`: 

    public class BlogsEntryAssetRendererFactory extends BaseAssetRendererFactory {

        public static final String CLASS_NAME = BlogsEntry.class.getName();

        public static final String TYPE = "blog";

        public AssetRenderer getAssetRenderer(long classPK, int type)
            throws PortalException, SystemException {

            BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(classPK);

            return new BlogsEntryAssetRenderer(entry);
        }

        @Override
        public AssetRenderer getAssetRenderer(long groupId, String urlTitle)
            throws PortalException, SystemException {

            BlogsEntry entry = BlogsEntryServiceUtil.getEntry(groupId, urlTitle);

            return new BlogsEntryAssetRenderer(entry);
        }

        public String getClassName() {
            return CLASS_NAME;
        }

        public String getType() {
            return TYPE;
        }

        @Override
        public PortletURL getURLAdd(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws PortalException, SystemException {

            HttpServletRequest request =
                liferayPortletRequest.getHttpServletRequest();

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);

            if (!BlogsPermission.contains(
                themeDisplay.getPermissionChecker(),
                themeDisplay.getScopeGroupId(), ActionKeys.ADD_ENTRY)) {

                return null;
            }

        PortletURL portletURL = PortletURLFactoryUtil.create(
            request, PortletKeys.BLOGS, getControlPanelPlid(themeDisplay),
            PortletRequest.RENDER_PHASE);

            portletURL.setParameter("struts_action", "/blogs/edit_entry");

            return portletURL;
        }

        @Override
        public boolean hasPermission(
                PermissionChecker permissionChecker, long classPK, String actionId)
            throws Exception {

            return BlogsEntryPermission.contains(
                permissionChecker, classPK, actionId);
        }

        @Override
        public boolean isLinkable() {
            return _LINKABLE;
        }

        @Override
        protected String getIconPath(ThemeDisplay themeDisplay) {
            return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";
        }

        private static final boolean _LINKABLE = true;
    }

Here's the `AssetRenderer` implementation: 

    public class BlogsEntryAssetRenderer extends BaseAssetRenderer {

        public BlogsEntryAssetRenderer(BlogsEntry entry) {
            _entry = entry;
        }

        public long getClassPK() {
            return _entry.getEntryId();
        }

        @Override
        public String getDiscussionPath() {
            if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
                return "edit_entry_discussion";
            }
            else {
                return null;
            }
        }

        public long getGroupId() {
            return _entry.getGroupId();
        }

        public String getSummary(Locale locale) {
            return HtmlUtil.stripHtml(_entry.getDescription());
        }

        public String getTitle(Locale locale) {
            return _entry.getTitle();
        }

        @Override
        public PortletURL getURLEdit(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws Exception {

            PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
                getControlPanelPlid(liferayPortletRequest), PortletKeys.BLOGS,
                PortletRequest.RENDER_PHASE);

            portletURL.setParameter("struts_action", "/blogs/edit_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

            return portletURL;
        }

        @Override
        public String getUrlTitle() {
            return _entry.getUrlTitle();
        }

        @Override
        public PortletURL getURLView(
                LiferayPortletResponse liferayPortletResponse,
                WindowState windowState)
            throws Exception {

            PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
                PortletKeys.BLOGS, PortletRequest.RENDER_PHASE);

            portletURL.setWindowState(windowState);

            portletURL.setParameter("struts_action", "/blogs/view_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

            return portletURL;
        }

        @Override
        public String getURLViewInContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            String noSuchEntryRedirect) {

            ThemeDisplay themeDisplay =
                (ThemeDisplay)liferayPortletRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

            return themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
                "/blogs/find_entry?noSuchEntryRedirect=" +
                    HttpUtil.encodeURL(noSuchEntryRedirect) + "&entryId=" +
                        _entry.getEntryId();
        }

        public long getUserId() {
            return _entry.getUserId();
        }

        public String getUuid() {
            return _entry.getUuid();
        }

        @Override
        public boolean hasEditPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.UPDATE);
        }

        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.VIEW);
        }

        @Override
        public boolean isPrintable() {
            return true;
        }

        public String render(
                RenderRequest renderRequest, RenderResponse renderResponse,
                String template)
            throws Exception {

            if (template.equals(TEMPLATE_ABSTRACT) ||
                template.equals(TEMPLATE_FULL_CONTENT)) {

                renderRequest.setAttribute(WebKeys.BLOGS_ENTRY, _entry);

                return "/html/portlet/blogs/asset/" + template + ".jsp";
            }
            else {
                return null;
            }
        }

        @Override
        protected String getIconPath(ThemeDisplay themeDisplay) {
            return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";
        }

        private BlogsEntry _entry;

    }

<!--Some long code blocks in here. Not sure if they're too long? -->

<!-- These are too long. Can we abstract out just the functionality we need?
-Rich -->

In the render method, there's a forward to a JSP in the case of the abstract
and the full content templates. The abstract isn't mandatory and if it isn't
provided, the Asset Publisher shows the title and the summary from the
appropriate methods of the renderer. The full content template should always be
provided. Here's how it looks for blogs entries: 

    <%@ include file="/html/portlet/blogs/init.jsp" %>

    <%
    BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);
    %>

    <%= entry.getContent() %>

    <liferay-ui:custom-attributes-available className="<%= BlogsEntry.class.getName() %>">
        <liferay-ui:custom-attribute-list
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
            editable="<%= false %>"
            label="<%= true %>"
        />
    </liferay-ui:custom-attributes-available>

That's about it. It wasn't that hard, right? Now it's time to get really fancy;
put on your dancing shoes. If you need to extend the capabilities of the
`AssetRendererFactory` for one of Liferay's core portlets, check out the
article [Extending an
AssetRendererFactory](http://www.liferay.com/web/juan.fernandez/blog/-/blogs/extending-an-assetrendererfactory)
by Juan Fern&agrave;ndez; he talks about doing just that. 

<!-- Let's not do this. Blog entries get out of date quickly. Instead, we should
take his blog entry and massage the text to include it right here in the
documentation. -Rich -->

Now get out there and start enjoying the benefits of the Asset Framework in
your custom portlets. 

Let's learn how to leverage Liferay Portal's File Storage Framework. 

<!--## File Storage Framework--> 

<!--The File Storage Framework lets you store files using the back end of the
Document Library. If you use this framework you won't have to worry about
clustering or backups, since they'll already be taken care of for the Document
Library itself. To see examples of this framework in action, check out the
source code for the built in *wiki* and *message boards* portlets of Liferay;
the File Storage Framework stores their attached files in pages and posts,
respectively. -->

<!-- This section should be removed, as it amounts to no documentation at all.
We need to cover this properly. -Rich --> 

## Other frameworks 

Liferay has a variety of frameworks that simplify development of complex
functionalities for your own applications. Liferay's frameworks have evolved
from its built in applications, so they're proven to work in the real world,
even in high performance portals. 

This section is a placeholder that provides a brief description of the main
frameworks provided with Liferay 6.1. The following list is a work in progress,
since we'll add more sections  to this chapter over time, and some of the
current sections might evolve into their own chapters as we add more
information and detailed instructions on how to use them. 

- *Workflow Framework*: Lets you add Workflow functionality to your own
  portlets. One benefit of using this framework is that you can reuse all the
workflow management UIs Liferay provides. You can also abstract your code from
the specific workflow engine that will be used (e.g., JBPM or Liferay Kaleo).
Many Liferay portlets use this framework; to see a simple example and learn how
you can use it, the blogs portlet is a good start. 

- *Custom Fields*: A portlet that uses custom fields lets the end user extend
  the fields of its data entries with custom fields defined by the end user.
To see a list of data types that support this functionality go to Liferay's
Control Panel &rarr; Custom Fields. 

- *Inline Permissions Framework*: Lets you enhance your SQL queries so the
  database checks for view permissions. This is particularly useful when doing
queries for data entries that might result in many of items, making the
checking of permissions afterward inefficient. It's also useful when you want
to implement pagination (which wouldn't work if permissions are checked
afterward and an item is removed). Liferay's *Document Library* and *Message
Boards* portlets both use this functionality. 

- *Faceted Search*: A new API that simplifies the creation new facet
  configurations and searches. It uses a JSON based configuration to define the
details of facets used for the search. To find out more, see the [Faceted
Search](http://www.liferay.com/community/wiki/-/wiki/1071674/Faceted+Search)
wiki by Ray Aug&#233;.

Check back in the near future; new editions of the Developer's Guide will have
extended information on each of these frameworks. 

<!-- The above section should also be removed. All of these are implemented and
documented in *Liferay in Action*. -Rich -->

## Device Detection

As you know, Internet traffic has risen exponentially over the past decade and
shows no sign of stopping. With the latest and greatest devices, mobile Internet
access has become the norm and is predicted to pass PC based Internet access
soon. Because of the mobile boom, new obstacles and challenges are presented for
content management. How will content adapt to all devices with different
capabilities? How can your grandma's gnarly tablet and cousin's awesome new
mobile phone request the same information from your portal?

The Device Detection API detects the capabilities of any device making a
request to your portal. It can also determine what mobile device or operating
system was used to make a request, and then follows rules to make Liferay render
pages based on the device. To use this feature, you first need to install the
*Device Recognition Provider* app from Liferay Marketplace. Find more
information on the app by following one of these links, depending on whether you
use Liferay CE or EE: [Device Recognition
CE](http://www.liferay.com/marketplace/-/mp/application/15193341) or [Device
Recognition EE](http://www.liferay.com/marketplace/-/mp/application/15186132). 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app; it uses a device database called *WURFL* to determine the
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net/](http://wurfl.sourceforge.net/).

You can create your own plugin to use your device's database. Let's look at
some simple uses of the Device Detection API and talk about its
capabilities. 

### Using the Device API 

Let's look at a couple of code snippets to get you started. You can obtain the
object `Device` from the `themeDisplay` object like this:

    Device device = themeDisplay.getDevice();

You can view the API in the [Device
Javadocs](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
Using some of the methods from the Javadocs, here's an example that obtains a
device's dimensions:

    Dimensions dimensions = device.getScreenSize();
    float height = dimensions.getHeight();
    float width = dimensions.getWidth();

Now your code can get the `Device` object and the dimensions of a device.
Of course this is just a simple example; you can acquire many other device
attributes that help you take care of the pesky problems that arise when sending
content to different devices. You can refer to the Device Javadocs mentioned
above for assistance.  Let's look at some device capabilities next.

### Device capabilities

Most of the capabilities of a device can be detected, depending on the device
detection implementation you're using. The Device Recognition plugin's device
database (WURFL) has a list of capabilities
[here](http://www.scientiamobile.com/wurflCapability/tree). For example, you
can obtain the capability of a brand name with this code:

    String brand = device.getCapability("brand_name");

You can grab many other device capabilities, including `model_name`,
`marketing_name`, and `release_date`. You can also get boolean values like
`is_wireless_device`, `is_tablet`, etc. 

With the Device Detection API, you can detect the capabilities of a device
making request to your portal and render content accordingly; so your grandma's
gnarly tablet and your cousin's awesome new mobile phone can make requests to
your portal and receive identical content. This will make everyone happy! 

You're really getting the hang of Liferay's APIs. Way to go! 

## Using Message Bus 

The *Message Bus* is a service level API used to exchange messages within
Liferay. The Message Bus is a mechanism for sending message payloads to
different components in Liferay, providing loose coupling between message
producers and consumers to prevent class loading issues. It's located in
the global class loader, making it accessible to every deployed web application.
Remote messaging isn't supported, but messages are sent across a cluster
when ClusterLink is enabled. 

Message Bus has several common uses, including sending search index write
events, sending subscription emails, handling messages at scheduler endpoints,
and running asynchronous processes. 

You can leverage Message Bus to send messages between and within your plugins. 

As we show you Message Bus we'll talk about things like *synchronous* and
*asynchronous* messaging , *serial* vs. *in-parallel* message dispatching, and
Java and JSON style messages formats. 

Before we get into those topics, let's first try to understand Message Bus
System's architecture. 

<!--Is a subtopic list okay? -->

<!-- No; I changed it to a paragraph-based list. -Rich -->

<!--It looks like Rich changed the "common uses" list instead of the
"subtopics" list, so I left his change and also changed the subtopics list into
a paragraph. Russ--> 

### The Message Bus System 

The Message Bus system contains the following components: 

- **Message Bus**: Manages transfer of messages from message *senders* to message
*listeners*. 

- **Destinations**: Addresses or endpoints to which *listeners* register to
receive messages. 

- **Listeners**: Consume messages received at destinations. They receive all
messages sent to their registered destinations. 

- **Senders**: Invoke the Message Bus to send messages to destinations. 

Your services can send messages to one or more destinations, and can listen
to one or more destinations. The figure below depicts this. An individual
service can be both a message sender and a message listener. For example, in the
figure below both *Plugin 2 - Service 3* and *Plugin 5 - Service 7* send and
listen for messages. 

![Figure 7.1: Example, Message Bus system](../../images/msg-bus-system.png)

The Message Bus supports *synchronous* and *asynchronous* messaging: 

- **Synchronous messaging**: After it sends a message, the sender blocks waiting
for a response from a recipient. 

- **Asynchronous messaging**: After it sends a message, the sender is free to
continue processing. The sender can be configured to receive a call-back or can
simply send and forget. We'll show you how to implement both synchronous and
asynchronous messaging in this section. 

    - **Call-back**: The sender can include a call-back destination key as the
    response destination for the message. The recipient (listener) can then
    send a response message back to the sender via this response
    destination. 

    - **Send-and-Forget**: The sender includes no call-back information in the
    message sent and continues with processing. 

Configuration of Message Bus is done using the following files:

- `WEB-INF/src/META-INF/messaging-spring.xml`: Specifies your destinations,
listeners, and their mappings to each other. 

- `WEB-INF/web.xml`: Holds a listing of deployment descriptors for your plugin.
Make sure you add `messaging-spring.xml` to your list of Spring configurations
in this file. 

---

![note](../../images/tip-pen-paper.png) **Note:** The internal file
`META-INF/messaging-core-spring.xml` of `portal-impl.jar` specifies the default
Message Bus class, default asynchronous message sender class, and default
synchronous message sender class for Liferay. 

---

You can control your *Message Types* by using either the `Message` or
`JSONObject` class. Liferay core services are typically serialized and
deserialized in JSON. In our examples we'll demonstrate both types of message
classes. 

So far we've introduced the Message Bus System, including message types,
destinations, senders, listeners, and approaches to sending messages. Next we'll
show you how easy it is to create your destinations, register listeners, and
send your messages. To demonstrate, we'll implement a business use case.

### Example Use Case--Procurement process 

Our use case will consider Jungle Gyms R-Us and its distribution of playground
equipment, buying the equipment from manufacturers and selling the equipment to
retailers. We'll focus on the company's process for procuring new jungle gym
equipment. Let's lay out this process now.

Jungle Gyms R-Us employs the following departments in their procurement process: 

- *Procurement Department*: Scouts out the latest equipment deals of
manufacturers.

- *Finance Department*: Determines whether the equipment can be purchased based
on budget.

- *Legal Department*: Determines whether the equipment's safety ratings are
acceptable.

- *Warehouse Department*: Recieves the equipment, stores it, and prepares it for
shipping.

- *Sales Department*: Builds relationships with prospective customers to sell
them products.

The departments currently use email to exchange comments about new equipment
purchases, but someone always seems to be left out of the loop. One time, Sales
was gung-ho about getting their hands on the latest and greatest spring rider
animals from Boingo-Boingo Industries, but they didn't consider the failing
safety reviews discovered by the Legal department, because the Legal department
forgot to copy the Sales department in their email to Procurement.  Tempers flew,
feelings were hurt, and everybody avoided hanging out in the company breakroom
for the next couple of weeks. 

<!-- Now that I see that this example is not a functional or even a potential
real-world example, I think we need to scrap the whole thing and come up with
another example, one that is integrated into an actual application. We could
conceivablly salvage it by making it into a purchase request application:
Departments have to enter purchase requests, and the message bus automatically
emails certain departments when a purchase request is entered. -Rich -->

Jungle Gyms R-Us could use Liferay's [Workflow with
Kaleo](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/workflow-with-kal-4)
to resolve the communication breakdown, but we'll resolve the Jungle Gym's
communication woes using Message Bus, to show you how it works. 
Here are the inter-department message exchanges we'll accommodate:

 Message | Sender | Listener | Response | Response Listeners |
-------- | ------ | -------- | -------- | ------------------ |
 Request permission to proceed with purchase | Procurement | Finance | required | Procurement |
 Request permission to proceed with purchase | Procurement | Legal | required | Procurement |
 Notify and solicit feedback on new purchase | Procurement | Warehouse | optional | Procurement, Sales |
 Notify and solicit feedback on new purchase | Procurement | Sales | optional | Procurement, Warehouse |
 Broadcast equipment news | Procurement | Employees | none | none |

Let's implement Procurement's request to Finance first. 

### Synchronous messaging 

In our example, equipment purchases can't proceed without approval from Finance
and Legal departments. Since special offers from the manufacturers often only
last for a couple hours, Procurement makes it their top priority to get approval
as soon as possible. Implementing their exchange using *synchronous* messaging
makes the most sense. 

![Figure 7.2: Synchronous messaging](../../images/msg-bus-sync-msg.png)

The following table describes how we'll set things up: 

 Destination Key | Type  | Sender | Receivers                                 |
---------------- | ----- | ------ | ----------------------------------------- |
 `jungle/finance/purchase`          | synchronous | Procurement | Finance |
 `jungle/finance/purchase/response` | synchronous | Finance | Procurement |
 `jungle/legal/purchase`            | synchronous | Procurement | Legal   |
 `jungle/legal/purchase/response`   | synchronous | Legal | Procurement   |

We've set it up so Finance sends its response messages to a destination on which
Procurement will listen. That way a full-bodied response message is sent back to
Procurement in addition to the response object returned from sending the
message. 

*The Procurement Department sends a purchase approval request:* 

<!--Where does this code go? messaging-spring.xml? If so is the code here
simply added to the file? And the code in subsequent descriptions too?-->

<!-- That's a very good question. This would generally be part of a portlet
application. This doesn't look like an actual, working demo, and I think we need
to talk to Jim about it. -Rich --> 

    Message message = new Message();
    message.put("department", "Procurement");
    message.put("partName", part.getName(Locale.US));

    message.setResponseId("1111");
    message.setResponseDestinationName("jungle/finance/purchase/response");

    try {
        String financeResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/finance/purchase", message, 10000);

        System.out.println(
            "Procurement received Finance sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + financeResponse);

        message.setResponseId("2222");
        message.setResponseDestinationName("jungle/legal/purchase/response");

        String legalResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/legal/purchase", message, 10000);

        System.out.println(
            "Procurement received Legal sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + legalResponse);

        if (financeResponse.contains("yes") && legalResponse.contains("yes")) {
            sendPurchaseNotification(part, userId);
        }
    }
    catch (MessageBusException e) {
        e.printStackTrace();
    }

This sender takes the following steps: 

1. Creates the message using Liferay's `Message` class.

2. Stuffs the message with key/value pairs.

3. Sets a response ID and response destination for listeners to use in replying
back.

4. Sends the message to the destination with a timeout value of 10,000
milliseconds.

5. Blocks waiting for the response.

*Finance Department listens for purchase approval requests and replies back:* 

    public class FinanceMessagingImpl implements MessageListener {

        public void receive(Message message) {
            try {
                doReceive(message);
            }
            catch (Exception e) {
                _log.error("Unable to process message " + message, e);
            }
        }

        protected void doReceive(Message message)
            throws Exception {

            String department = (String) message.get("department");
            String partName = (String) message.get("partName");

            System.out.println("Finance received purchase request for " +
                partName + " from " + department);

            Message responseMessage = MessageBusUtil.createResponseMessage(
                message);

            responseMessage.put("department", "Finance");
            responseMessage.put("partName", partName);
            responseMessage.setPayload("yes");

            MessageBusUtil.sendMessage(
                responseMessage.getDestinationName(), responseMessage);
        }

        private static Log _log =
            LogFactoryUtil.getLog(FinanceMessagingImpl.class);
    }

This listener executes the following steps: 

1. Implements the `receive(Message message)` method of the
`com.liferay.portal.kernel.messaging.MessageListener` interface. 

2. Extracts values from the `Message` parameter by getting values associated
with known keys. 

3. Creates a `Message` based on the message received via the
`MessageBusUtil.createResponseMessage(message)` method, which accesses the
response destination name from the `message` variable and sets the destination
of the response message. 

4. Sets the response message's payload. 

5. Sends the response `Message` to the response destination.

You can implement the listener for the Legal Department similarly. Next we'll
account for Legal Department-related classes in our configuration.

*Message Bus Configuration for the purchase approval request process:*

For Message Bus to direct messages from destinations to listeners, we must
register the listeners by configuring the appropriate mappings in our plugin's
`WEB-INF/src/META-INF/messaging-spring.xml` file (create this file if it's not
already in your plugin). Here is the configuration: 

<!--If the messaging-spring file potentially hasn't been created yet where has
the reader been putting the code snippets from above? Should this statement go
before the first code snippet?--> 

<!-- The above snippets go in the service layer. If you follow the message bus
section in Liferay in Action, you'll see that the Java stuff goes in the service
layer's -Impl class. It's generally a best practice to write the Java classes
and then write the configuration file, because tools like Liferay IDE and
Liferay Developer Studio try to deploy the plugin right away, and if you declare
classes in the configuration file that don't exist yet, the whole plugin blows
up. -Rich -->

    <?xml version="1.0"?>

    <beans
        default-destroy-method="destroy"
        default-init-method="afterPropertiesSet"
        xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    >

        <!-- Listeners -->

        <bean id="messageListener.finance_listener" class="com.liferay.training.parts.messaging.impl.FinanceMessagingImpl" />
        <bean id="messageListener.legal_listener" class="com.liferay.training.parts.messaging.impl.LegalMessagingImpl" />
        <bean id="messageListener.procurement_listener" class="com.liferay.training.parts.messaging.impl.ProcurementMessagingImpl" />

        <!-- Destinations -->

        <bean id="destination.finance.purchase" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/finance/purchase" />
        </bean>

        <bean id="destination.finance.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/finance/purchase/response" />
        </bean>

        <bean id="destination.legal.purchase" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/legal/purchase" />
        </bean>

        <bean id="destination.legal.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/legal/purchase/response" />
        </bean>

        <!-- Configurator -->

        <bean id="messagingConfigurator" class="com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator">
            <property name="messageListeners">
                <map key-type="java.lang.String" value-type="java.util.List">
                    <entry key="jungle/finance/purchase">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.finance_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/finance/purchase/response">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.legal_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase/response">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                </map>
            </property>
            <property name="destinations">
                <list>
                    <ref bean="destination.finance.purchase"/>
                    <ref bean="destination.finance.purchase.response"/>
                    <ref bean="destination.legal.purchase"/>
                    <ref bean="destination.legal.purchase.response"/>
                </list>
            </property>
        </bean>
    </beans>

The configuration above specifies the following beans: 

- *Listener beans*: Specify classes to handle messages.
- *Destination beans*: Specify the class *type* and *key* names of the
destinations.
- *Configurator bean*: Maps listeners to their destinations.

When Finance sends its purchase approval request message for a new three-story
spiral slide, the console reports Finance's receipt of the message,
Procurement's receipt of the callback response from Finance, and Procurement's
receipt of the synchronous response returned from sending the message. Here's
what the console message looks like: 

    Finance received purchase request for three-story spiral slide from Procurement
    Procurement received Finance callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Finance sync response to purchase approval for three-story 
    spiral slide: yes
    Legal received purchase request for three-story spiral slide from Procurement
    Procurement received Legal callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Legal sync response to purchase approval for three-story 
    spiral slide: yes

Do you know what all those *yes* messages mean? Success! Jungle Gyms R-Us has
the cash to purchase this cool new slide, and the Legal Department has no gripes
about the slide's safety ratings! 

Next let's have Procurement notify the Sales and Warehouse departments and
solicit their feedback.

### Asynchronous messaging with callbacks  

Asynchronous messaging consists of sending a message and then continuing with
processing without blocking waiting for an immediate response. This allows the
sender to continue with other tasks. It's often necessary, however, that the
listener can respond to the sender. This can be done using a call-back.

Jungle Gyms R-Us's Procurement Department must notify the Sales and Warehouse
departments of incoming equipment while simultaneously soliciting their
feedback. To assure all three departments are up to speed, any responses from
the Sales or Warehouse departments are posted to a shared destination. 

The following table describes how we'll set things up: 

| Destination Key            | Type  | Sender | Receivers                     |
---------------------------- | ----- | ------ | ----------------------------- |
  `jungle/purchase`          | async serial | Procurement |  Sales, Warehouse |
  `jungle/purchase/response` | synchronous  | Sales, Warehouse | Procurement  |

The following image shows asynchronous messaging, with serial dispatching of
messages: 

![Figure 7.3: Asynchronous messaging with *serial* dispatching](../../images/msg-bus-async-serial-msg.png)

Let's package the message as a `JSONObject` and send it to the destination: 

    JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

    jsonObject.put("department", "Procurement");
    jsonObject.put("partName", part.getName(Locale.US));
    jsonObject.put("responseDestinationName", "jungle/purchase/response");

    MessageBusUtil.sendMessage("jungle/purchase", jsonObject.toString());

Here's how the Warehouse Department listens for and handles messages: 

    public void receive(Message message) {

        try {
            doReceive(message);
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceive(Message message)
        throws Exception {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");
        String partName = jsonObject.getString("partName");
        String responseDestinationName = jsonObject.getString(
                        "responseDestinationName");

        System.out.println("Warehouse received purchase notification for " +
            partName + " from " + department);

        jsonObject = JSONFactoryUtil.createJSONObject();

        jsonObject.put("department", "Warehouse");
        jsonObject.put("partName", partName);
        jsonObject.put("comment", "Ugh! We're running out of space!!");

        MessageBusUtil.sendMessage(
            responseDestinationName, jsonObject.toString());
    }

Here's how this listener deserializes the `JSONObject` from the message: 

1. Gets the message payload and casts it to a `String`. 
2. Creates a `JSONObject` from the payload string. 
3. Gets values from the `JSONObject` using its getter methods. 

The class also demonstrates how the Warehouse Department packages a response
message and sends it back to the Procurement Department, using these steps: 

1. Create a `JSONObject`.  
2. Stuff it with name-value pairs. 
3. Send the response message to the original message's response destination. 

The Sales department listener can be implemented the same way, substituting
*Sales* as the department value; the comment would likely be different, too. 

You just used the `JSONObject` message type to send an asynchronous response
message using a call-back. 

Remember, we want the Procurement, Sales, and Warehouse departments to be
aware of any message regarding the new playground equipment purchasing process.
Let's leverage our destination keys and department names in handling shared
responses. 

Here's how the Warehouse might handle messages it receives:

    public void receive(Message message) {

        try {
            if (message.getDestinationName().equals(
                    "jungle/purchase"))
            {
                doReceive(message);
            }
            else if (message.getDestinationName().equals(
                    "jungle/purchase/response"))
            {
                doReceiveResponse(message);
            }
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceiveResponse(Message message)
        throws JSONException {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");

        if (!department.equals("Warehouse")) {
            System.out.println(
                "Warehouse is in the loop on response from " + department);
        }
    }

Let's look at `receive(Message)` for a minute. We've set it up to handle
messages differently depending on their destinations: messages to
`jungle/purchase` are handled as Procurement's purchase notifications, while
messages to `jungle/purchase/response` are treated as departmental responses to
Procurement's purchase notifications.  The `doReceiveResponse(Message)` method
performs an important task, checking that the response comes from a department
other than itself, and printing an error if it doesn't. 

Here are the configuration elements we added to the `messaging-spring.xml` from
the previous section:

*Listener beans*: 

        <bean id="messageListener.warehouse_listener" class="com.liferay.training.parts.messaging.impl.WarehouseMessagingImpl" />
        <bean id="messageListener.sales_listener" class="com.liferay.training.parts.messaging.impl.SalesMessagingImpl" />

*Destination beans*: The purchase notifications will be sent to a *serial*
  destination and the responses will be sent to a *synchronous* destination. 

        <bean id="destination.purchase" class="com.liferay.portal.kernel.messaging.SerialDestination">
            <property name="name" value="jungle/purchase" />
        </bean>

        <bean id="destination.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/purchase/response" />
        </bean>

*Configuration bean listener map entry*: Warehouse and Sales are registered
  to listen for the notifications from Procurement. All three departments are
registered to listen for inter-departmental responses.

        <entry key="jungle/purchase">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.warehouse_listener" />
                <ref bean="messageListener.sales_listener" />
            </list>
        </entry>
        <entry key="jungle/purchase/response">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.procurement_listener" />
                <ref bean="messageListener.warehouse_listener" />
                <ref bean="messageListener.sales_listener" />
            </list>
        </entry>

*Configuration bean destination list references*:

        <ref bean="destination.purchase"/>
        <ref bean="destination.purchase.response"/>

Don't forget to send news of these new products to *all* Jungle Gyms R-Us
employees.

<!-- Missing transition. --> 

### Asynchronous "Send and Forget" 

In the *send and forget* model, the sender sends messages and continues
processing. We'll apply this behavior to Jungle Gym's company-wide new product
notification.

Procurement isn't expecting response messages from individual employees, so
there's no need for the company-wide listener to package up responses. We do,
however, want everyone to get product news at the *same time*, so instead of
dispatching news to employees *serially* we'll dispatch *in parallel*.

![Figure 7.4: Asynchronous messaging with *parallel* dispatching](../../images/msg-bus-async-parallel-msg.png)

We'll specify a parallel destination type in our `messaging-spring.xml`:

*Destination bean*:

        <bean id="destination.employee.news" class="com.liferay.portal.kernel.messaging.ParallelDestination">
                <property name="name" value="jungle/employee/news" />
        </bean>

*Listener bean*:

        <bean id="messageListener.employee_listener" class="com.liferay.training.parts.messaging.impl.EmployeeMessagingImpl" />

*Configuration bean listener map entry*: 

        <entry key="jungle/employee/news">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.employee_listener" />
            </list>
        </entry>

*Configuration bean destination list reference*: 

        <ref bean="destination.employee.news"/>

Congratulations! You implemented inter-departmental communications for the
procurement process at Jungle Gyms R-Us.

Along the way you used Message Bus to implement the following:

- Sender, listener, and destination components. 
- Synchronous and Asynchronous messaging schemes. 
- *Serial* and *parallel* message dispatching. 
- Java and JSON message types. 

<!--Next we'll show you the Device Detection API and its capabilities. -->



## Summary 

With Liferay's frameworks, implementing complex functionality in your custom
portlets becomes easy. We covered security and permissions and the 
Asset Framework. Make sure  you check back regularly to find more detailed
descriptions of current frameworks and discover brand new frameworks that'll
knock your socks off, or at least simplify your custom portlet development. 
