
# Liferay Frameworks 

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

- File Storage Framework 

- Other Frameworks 

- Device Detection

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

## File Storage Framework 

The File Storage Framework lets you store files using the back end of the
Document Library. If you use this framework you won't have to worry about
clustering or backups, since they'll already be taken care of for the Document
Library itself. To see examples of this framework in action, check out the
source code for the built in *wiki* and *message boards* portlets of Liferay;
the File Storage Framework stores their attached files in pages and posts,
respectively. 

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

## Summary 

With Liferay's frameworks, implementing complex functionality in your custom
portlets becomes easy. We covered security and permissions and the 
Asset Framework. Make sure  you check back regularly to find more detailed
descriptions of current frameworks and discover brand new frameworks that'll
knock your socks off, or at least simplify your custom portlet development. 
