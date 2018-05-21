# Configuring Display Settings [](id=configuring-display-settings)

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
