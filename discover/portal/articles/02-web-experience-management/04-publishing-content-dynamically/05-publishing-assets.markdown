# Publishing Assets [](id=publishing-assets)

As you create web content, it's important to keep in mind that to @product@, the
pieces of content are assets, just like message board entries and blog posts.
This allows you to publish different kinds of content using @product@'s Asset
Publisher. You can use the Asset Publisher to publish a mixed group of various
kinds of assets such as images, documents, blogs, and of course, web content.
This helps in creating a more dynamic web site: you can place user-created wiki
entries, blog posts, or message board messages in context with your content.
You'll examine some of the Asset Publisher's features next.

## Querying for Content [](id=querying-for-content)

The Asset Publisher is a highly configurable application that lets you query for
mixed types of content on the fly. By giving you the ability to control what and
how content is displayed from one location, the Asset Publisher helps you to
"bubble up" the most relevant content to your users.

To get to all the application's options, click the *Options* icon
(![Options](../../../images/icon-options.png)) in the application's menu. If you
click the *Configuration* option and then *Setup* (if necessary), you can
configure the Asset Publisher's settings from the following three areas:

- Asset Selection
- Display Settings
- Subscriptions

Asset Selection allows you to configure which assets are displayed. You can set
asset selection to either *Dynamic* or *Manual*. With dynamic asset selection,
assets are automatically displayed based on certain rules or filters. For
example, you can set the Asset Publisher to display only assets of a certain
type or assets to which certain tags or categories have been applied. With
manual asset selection, the Asset Publisher only displays assets that have been
explicitly selected by an administrator.

The Asset Publisher supports a scope that restricts both dynamic and manual
asset selection. The Asset Publisher can only display assets from its configured
scope. By default, the Asset Publisher app is scoped to the site of the page to
which it was added. You can, however, customize the scope from the Asset
Selection section of the Asset Publisher configuration window. To extend your
Asset Publisher's scope, click *Select* under Scope and choose either *Global*
to add the global scope, *Pages...* to add the scope to specific pages, or
*Other Site...* to add the scope of anther site.

The Display Settings section of the Asset Publisher configuration window lets
administrators customize many details that determine how content is displayed.
The Subscription section allows administrators to enable, disable, or configure
email subscriptions and RSS subscriptions. In the following sections, you'll
explore the available configurations for the Asset Selection, Display Settings,
and Subscriptions sections of the Asset Publisher's configuration window. You'll
start by learning how select content manually. You'll see that it's very similar
to using the Web Content Display application except that you can select assets
of any type, not just web content articles.

### Selecting Assets Manually [](id=selecting-assets-manually)

By selecting *Manual* from the select box beneath *Asset Selection*, you're
telling the Asset Publisher that you want to select content manually. You can
configure multiple scopes, including the global scope, from which to select
assets.

![Figure 1: Selecting assets in the Asset Publisher manually is similar to selecting assets in the Web Content Display application except that you can select assets of any type, not just web content. You can also add scopes to expand the list of assets that are available to be displayed in the Asset Publisher.](../../../images/web-content-asset-publisher-manual.png)

When selecting assets manually, you'll see a list of configured scopes under the
Scope heading. Click the "X" button at the right to remove a scope from the
list. Click the *Select* button to add additional scopes to the Asset
Publisher's configuration. After you've added a scope, a new Select button
appears under the Asset Entries heading. A list of assets selected for display
appears in the Asset Entries section. You can select assets to be displayed by
clicking on the appropriate *Select* button. One button appears for each
configured scope. By default, the available asset types include the following:

- Blogs Entry
- Bookmarks Entry
- Bookmarks Folder
- Calendar Event
- Basic Document
- Google Docs
- Contract
- Marketing Banner
- Online Training
- Sales Presentation
- Documents Folder 
- Dynamic Data Lists Record
- Message Boards Message
- Basic Web Content
- Web Content Folder
- Wiki Page

You can select any number of assets to be displayed. Note, however, that there's
a display setting called *Number of Items to Display* that determines the
maximum number of items to display (or, if pagination is enabled, the maximum
number of items to display per page). The Asset Publisher enables you to mix and
match different asset types in the same interface. When you're done selecting
items to display, click *Save*. Any selected assets are added to the list of
assets that are displayed by the application. Once you have your content
selected, you can configure the display types to configure how the content
appears. We'll discuss the display settings in more detail after we finish
discussing how to select assets for display. 

While manual asset selection allows you to select assets of various types from
different scopes, it can be time-consuming to periodically update the assets
that should be displayed. It's often more convenient to use the Asset Publisher
to select content dynamically.

### Selecting Assets Dynamically [](id=selecting-assets-dynamically)

The Asset Publisher's default behavior is to select assets dynamically according
a set of customizable rules. These rules can be stacked on top of each other so
that they compliment each other to create a nice, refined query for your
content. You can define complicated rules for selecting assets for display and
@product@ automatically takes permissions into account. Liferay's Asset Publisher
performs well in these situations since it queries by search index instead of
querying the database directly. You have the following options for creating
rules for selecting content:

**Scope:** Choose the sites from which the content should be selected. This
works the same way as with manual asset selection: assets can only be displayed
if they belong to a configured scope. The following scope options are available
for an Asset Publisher:

- *Current Site*
- *Global*
- *Other Site*

The Other Site scope option is unavailable for Asset Publisher applications
configured on a page template (e.g., Content Display Page).

**Asset Type:** Choose whether you'll display any assets or only assets of a
specific type, such as only web content, only wiki entries, or any combination
of multiple types.

**Filter:** Add as many filters on tags or categories as you like. You can
choose whether the content must contain or must not contain any or all of the
tags or categories that you enter.

![Figure 2: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../../images/web-content-asset-publisher-filter.png)

Once you've set up your filter rules for dynamically selecting content, you can
decide how the content will be displayed.

You can configure the Asset Publisher to display assets that only match the
custom user profile attributes. This setting retrieves assets that have matching
categorization. These categories must be from the global context. For example,
suppose a user had a custom field called *Location* with the type *Text*. Also
assume the user's location attribute is set to *Moon*. You could create a
vocabulary called *Location* and a category for the Location vocabulary called
*Moon*. Then you could categorize content with *Moon* in the *Location*
vocabulary. With this organizational setup, adding an Asset Publisher and
specifying *Location* as the Asset Publisher's custom user attribute would only
display content that had been categorized as *Moon*. Pretty cool, right?

The *Ordering and Grouping* section of the Asset Publisher lets you
precisely control how content is ordered and grouped when displayed. You can
order the assets displayed by Asset Publisher in ascending or descending order
by the following attributes:

- Title
- Create Date
- Modified Date
- Publish Date
- Expiration Date
- Priority

For instance, suppose you have a series of "How To" articles that you want
displayed in descending order based on whether the article was tagged with the
*hammer* tag. Or, suppose you want a series of video captures to display in
ascending order based on a category called *birds*. For these use cases, you can
configure the ordering and grouping settings.

You can also configure a second ordering. The second ordering would be applied
to any assets for which the first ordering wasn't sufficient. For example,
suppose you chose to order assets by title and there are multiple assets with
the same title. Then the second ordering would take effect. For example, you
could order all the assets that had the same title by their publication dates.

You can establish grouping rules as well as ordering rules. You can group assets
by type or by vocabulary. For example, suppose there's a vocabulary called
*Membership Type* that belongs to your site. Suppose this vocabulary has two
categories: *Premium* and *Regular*. If you group assets by Membership Type, all
assets with the Premium category will be displayed in one group and all assets
with the Regular category will be displayed in another group. Grouping rules are
applied before any ordering rules: they're a way to divide up the displayed
assets into separate lists. The ordering rules are applied separately to each
group of assets.

Note that grouping and ordering rules are only one mechanism to control how your
content will be displayed. You can refine the display through many other display
settings which you'll examine next.

## Configuring Display Settings [](id=configuring-display-settings)

Open the *Display Settings* subtab of the Setup tab of the Asset Publisher's
Configuration window. Here, you can configure many more settings that control
the Asset Publisher's behavior and that determine how the Asset Publisher
displays content. The Display Settings section gives you precise control over
the display of your assets. There are many options available to configure how
you want your content to appear. Many of these, such as printing, flags,
ratings, comments, comment ratings, and social bookmarks work the same way they
do in the Web Content Display application.

**Show Add Content Button**: When selected, this selector adds an *Add New*
button that lets users add new assets directly from the Asset Publisher
application. This is checked by default.

**Display Template**: This selector lets you choose an application display
template to customize how the Asset Publisher displays assets. @product@ creates
the following display templates for each newly created site, including the
default site:

- *Abstracts:* This display template shows the first 200-500 characters of the
  content, defined by the **Abstract Length** field. This is the default display
  template of the Asset Publisher.
- *Table:* This display template displays the content in an HTML table which can
  be styled by a theme developer.
- *Title List:* This display template displays the content's title as defined by
  the user who entered it.
- *Full Content:* This display template displays the entire content of the entry.

    There's also the *Rich Summary* and *Map* display templates that belong to
    the global scope. The Rich Summary template provides a summary view of each
    asset along with a *Read More* link to the article's full content. The Map
    template displays [geolocalized assets](/discover/portal/-/knowledge_base/7-0/geolocating-assets) 
    in either a Google Map or an Open Street Map provider. The map provider can
    be configured in Instance Settings, and Site Settings in the Advanced 
    section.

**Abstract Length**: This selector lets you select the number of characters to
display for abstracts. The default is `200`.

**Asset Link Behavior:** The default value is *Show Full Content*. With this
value selected, when the link to an asset is clicked, the full asset is
displayed in the current Asset Publisher. (There's also a *View in Context* link
that shows the article in the Wiki page's Wiki application.) If the value *View
in a Context* is selected, clicking on an asset causes that asset to be
displayed in the application to which the asset belongs. For example, a blog
entry would be displayed in the Blogs application where it was created.
Likewise, a forum post would be displayed in the Message Boards application
where it was created. Similarly, a generic web content article would be
displayed in the Asset Publisher of its configured display page. See the section
below on display pages for more information.

+$$$

**Tip:** When the Asset Publisher displays web content articles that have an
associated small image, the small image becomes a link to the full article. To
use this feature, add or edit a web content article that the Asset Publisher
should display. Before clicking *Publish*, click on *Abstracts*, flag *Small
Image*, and upload an image. Then click *Publish*. Once your web content
article appears in the Asset Publisher's list, clicking the small image takes
you to the full article.

$$$

**Number of Items to Display**: This selector lets you select the maximum number
of assets that can be displayed by the Asset Publisher. If pagination, however,
is enabled, there's no limit to the number of of assets that the Asset Publisher
can display. So with pagination enabled, this number represents the maximum
number of assets that can be displayed per page.

**Pagination Type**: This can be set to *None*, *Simple*, or *Regular*. With
pagination set to *None*, the Asset Publisher displays at most the number of
assets specified in the **Number of Items to Display** property. Setting the
pagination type to *Simple* adds *Previous* and *Next* buttons that enable the
user to browse through pages of assets in the Asset Publisher. Setting the
pagination type to *Regular* adds more options and information including *First*
and *Last* buttons, a dropdown selector for pages, the number of items per page,
and the total number of results (assets being displayed).

**Show Metadata Descriptions:** This enables Metadata descriptions such as
*Content Related to...* or *Content with tag...* to be displayed with the
published assets.

**Show Available Locales:** Since content can be localized, you can have
different versions of it based on locale. Enabling this option shows the locales
available, enabling users to view the content in their language of choice. 

**Set as the Default Asset Publisher for This Page**: The Asset Publisher app is
an instanceable app; multiple Asset Publishers can be added to a page and each
has an independent configuration. The default Asset Publisher for a page is the
one used to display any web content associated with the page.

**Enable Conversion To:** If you have enabled Liferay's OpenOffice/LibreOffice
integration, you can allow your users to convert the content to one of several
formats:

- DOC
- ODT
- PDF
- RTF
- SXW
- TXT

    Please refer to the section on
    [Liferay Server Administration](/discover/portal/-/knowledge_base/6-2/server-administration)
    for information on setting up Liferay's OpenOffice/LibreOffice document
    conversion functionality.

    <!-- Update link above when available for 7.0. -Cody -->

**Enable ...**: The Asset Publisher's Display Settings allow you to
enable/disable the following options for displayed assets:

- Print
- Flags
- Related Assets
- Ratings
- Comments
- Comment Ratings
- Social Bookmarks

Enabling the Print option adds a *Print* link to the full view of an asset
displayed in the Asset Publisher. Clicking *Print* opens a new browser window
with a print view of the asset. Enabling flags, related assets, ratings,
comments, comment ratings, or social bookmarks add links to the corresponding
social features to the view full of the asset in the Asset Publisher.

+$$$

**Tip:** An alternate way to add flags, comments, and ratings to a page is
through the *Page Flags*, *Page Comments*, and *Page Ratings* applications. Just
add the applications in the appropriate location near the asset you'd like to
have feedback for.
     
$$$

When enabling social bookmarks, you're given sub-options to edit the display
style of your social bookmarks and whether they are listed at the top or bottom
of the Asset Publisher.

**Show Metadata:** Lets you select various metadata types to be displayed (see
below). For example, you can select tags and categories for display. Upon saving
your configuration, the Asset Publisher displays tags and categories for each
displayed asset. Then users can click on the tags and categories to manually
filter the displayed assets.

![Figure 3: You can configure the Asset Publisher to display various kinds of metadata about the displayed assets.](../../../images/available-metadata-fields.png)

The Display Settings section of the Asset Publisher has numerous options to help
you configure how your content selections are displayed to your users. Even
though there are many choices, it's easy to go through the options and quickly
adjust the ones that apply to your situation. You'll want to use the Asset
Publisher to query for different kinds of assets in @product@ that contain
relevant information for your users.

## Configuring Asset Publisher Subscriptions [](id=configuring-asset-publisher-subscriptions)

The Asset Publisher application supports two kinds of subscriptions: RSS
subscriptions and email subscriptions. To enable subscriptions, click the Asset
Publisher's Options icon and select *Configuration*. In the configuration
window, open the Subscriptions tab of the Setup tab. There are two options:
*Enable RSS Subscription* and *Enable Email Subscription*.

Enabling RSS Subscription creates an RSS feed containing links to all of the
assets that the Asset Publisher is configured to display. A link to this RSS
feed appears at the bottom of the Asset Publisher application. This option is
only available when the *Dynamic* Asset Selection is configured.

![Figure 4: When RSS subscriptions have been enabled for an Asset Publisher application, a link to the Asset Publisher's RSS feed appears. Users can subscribe to the Asset Publisher's RSS feed using their preferred RSS reader.](../../../images/asset-publisher-rss.png)

Enabling Email Subscription adds a *Subscribe* link to the Asset Publisher.
Users wishing to be notified of newly published assets can click on this link to
be added to the subscription list. @product@ periodically checks for new assets
and sends emails to subscribed users informing them about the new assets. By
default, Liferay performs this check every twenty-four hours.

<!-- but this can be
customized by adding the following property to your `portal-ext.properties` file
and changing the number:

    asset.publisher.check.interval=24

Also by default, Liferay limits the number of assets that are retrieved from the
database during this check to twenty. If you're interested in optimizing
Liferay's performance, you can lower this limit. If you're not concerned about
performance, you can remove this limit entirely. To customize the number of
assets about which Liferay notifies subscribed users, add the following property
to your `portal-ext.properties` file and change the number:

    asset.publisher.dynamic.subscription.limit=20

-----

This info needs to be updated once available. These properties are no longer in
portal.properties, but they are not available in System Settings either. Once
they're available, we'll need to document how to change these settings. -Cody

-->

Next, you'll look at Display Pages.

## Content Display Pages [](id=content-display-pages)

If you've been using @product@ for a while, you might have noticed something about
how @product@ handles web content--content is never tied directly to a page. While
this can be useful (because it means that you don't have to recreate content if
you want to display the same thing on multiple pages), it also means that you
don't have a static URL for any web content, which is bad for search engine
optimization.

As an improvement, Liferay introduced the concept of *display pages* and
*canonical URLs*. Each web content entry in Liferay has a canonical URL,
which is the official location of the content that is referenced any time the
content is displayed. A display page can be any page with an asset publisher
configured to display any content associated with the page. When adding or
editing web content, you can select a display page, but only pages with
a configured asset publisher are available for selection.

To create a display page, you can create a page yourself, add an Asset Publisher
app, and configure it yourself. Alternatively, you can use the *Content Display
Page* page template included with @product@. If you're creating a Display Page
manually, once you've added an Asset Publisher application to the page, open its
configuration window. Then navigate to the Display Settings tab and check the
*Set as the Default Asset Publisher for This Page* box. Also, for its display
settings, set the Display Style to *Simple* and the Asset Link Behavior to *View
in Context*.

+$$$

**Note:** Web content linked in the Asset Publisher can be viewed by clicking
their asset links. With the *View in Context* behavior checked, the link
displays the web content in its configured display page. If the web content does
not have a configured display page, it is displayed in the Web Content Display
application to which the asset belongs.

$$$

You may now be thinking, "Wait, you just told me that each web content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" That's right. Just watch--create a display
page called *My Web Content Display Page* somewhere in Liferay using the
*Content Display Page* template. Make sure the *Inherit Changes* selector is not
selected. Now, on a different page, add a Web Content Display application. Click
the *Add* button, enter a title and some content, click on *Display Page* at the
right, and select the Display Page you just created. Then click *Publish*.

![Figure 5: You can select a display page for a web content article when creating or editing one.](../../../images/web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, click the *Read
More* link to display the full content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional applications that you place on the page are
displayed along with the content when you access it via the canonical URL. If
you used the *Content Display Page* page template for your display page, it not
only features a configured Asset Publisher application but also a Tags
Navigation, a Categories Navigation, and a Search application. These tools help
users to quickly identify relevant content.

![Figure 6: A canonical URL for a *Lunar Resort Information* page would look similar to this screenshot.](../../../images/web-content-canonical-url.png)

Next, you'll learn about publishing RSS feeds.
