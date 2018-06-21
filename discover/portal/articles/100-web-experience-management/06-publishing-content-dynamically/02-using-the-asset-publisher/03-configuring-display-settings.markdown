# Configuring Display Settings [](id=configuring-display-settings)

From the Asset Publisher's configuration page, open the Setup tab's *Display
Settings* sub-tab. This section gives you precise control over the display of
your assets. There are many options available to configure how you want your
content to appear. Many of these, such as printing, flags, ratings, comments,
comment ratings, and social bookmarks work the same way they do in the Web
Content Display application.

**Show Add Content Button**: When selected, an *Add New* button appears that
lets users add new assets directly from the Asset Publisher application. This
is checked by default.

**Display Template**: This selector lets you choose an application display
template to customize how the Asset Publisher displays assets. These templates
are in every site by default:

- *Abstracts:* Shows the first 200-500 characters of the
  content, defined by the **Abstract Length** field. This is the default display
  template. 
- *Table:* Displays the content in an HTML table which can
  be styled by a theme developer.
- *Title List:* Displays the content's title as defined by
  the user who entered it.
- *Full Content:* This display template displays the entire content of the entry.

There's also the *Rich Summary* and *Map* display templates that belong to
the global scope. The Rich Summary template provides a summary view of each
asset along with a *Read More* link to the article's full content. The Map
template displays [geo-localized assets](/discover/portal/-/knowledge_base/7-1/geolocating-assets)
in either a Google Map or an Open Street Map provider. The map provider can
be configured in Instance Settings, and Site Settings in the Advanced
section.

**Abstract Length**: Select the number of characters to display for abstracts.
The default is `200`.

**Asset Link Behavior:** The default value is *Show Full Content*, which
displays the full asset in the current Asset Publisher. *View in a Context*
causes that asset to be displayed in the application where the asset belongs.
For example, a blog entry is displayed in Blogs where it was created. See the
section below on display pages for more information.

+$$$

**Tip:** When the Asset Publisher displays web content articles with an
associated small image, the small image becomes a link to the full article. To
use this feature, add or edit a web content article that the Asset Publisher
should display. Before clicking *Publish*, click on *Abstracts*, flag *Small
Image*, and upload an image. Then click *Publish*. Once your web content article
appears in the Asset Publisher's list, clicking the small image takes you to the
full article.

$$$

**Number of Items to Display**: Select the maximum number of assets that can be
displayed by the Asset Publisher. If pagination is enabled, this number
represents the maximum number of assets that can be displayed per page.

**Pagination Type**: This can be set to *None*, *Simple*, or *Regular*. *None*
displays at most the number of assets specified in the **Number of Items to
Display** property. *Simple* adds Previous and Next buttons for browsing through
pages of assets in the Asset Publisher. *Regular* adds more options and
information including First and Last buttons, a dropdown selector for pages,
the number of items per page, and the total number of results (assets being
displayed).

**Show Metadata Descriptions:** Enables Metadata descriptions such as
*Content Related to...* or *Content with tag...* to be displayed with the
published assets.

**Show Available Locales:** Since content can be localized, you can have
different versions of it based on locale. Enabling this option shows the locales
available, so users can view the content in their languages.

**Set as the Default Asset Publisher for This Page**: The Asset Publisher app is
an instanceable app: multiple Asset Publishers can be added to a page and each
has an independent configuration. The default Asset Publisher for a page is the
one used to display web content associated with the page.

**Enable ...**: Enable/disable the following options for displayed assets:

- Print
- Flags
- Related Assets
- Ratings
- Comments
- Comment Ratings

The Print option adds a *Print* link to the full view of an asset displayed in
the Asset Publisher. Clicking *Print* opens a new browser window with a print
view of the asset. Enabling flags, related assets, ratings, comments, comment
ratings, or social bookmarks add links to the corresponding social features to
the view full of the asset in the Asset Publisher.

+$$$

**Tip:** An alternate way to add flags, comments, and ratings to a page is
through the *Page Flags*, *Page Comments*, and *Page Ratings* applications. Just
add the applications in the appropriate location near the asset that should have
feedback.

$$$

**Metadata:** Select various metadata types to be displayed (see below). For
example, you can select tags and categories for display. Upon saving your
configuration, the Asset Publisher displays tags and categories for each
displayed asset. Then users can click on the tags and categories to filter the
displayed assets manually. 

![Figure 1: You can configure the Asset Publisher to display various kinds of metadata about the displayed assets.](../../../../images/available-metadata-fields.png)

Next you'll learn about configuring subscriptions for email and RSS through the 
Asset Publisher. 
