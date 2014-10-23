# Setting up Display Pages [](id=setting-up-display-pages)

The Display Settings section gives you precise control over the display of your
assets. There are a multitude of options available to configure how you want
your content to appear. You can configure the style, length of abstracts,
behavior of the asset link, maximum items to display, pagination type and file
conversions. Additionally, you can enable printing, flags, ratings, comments and
comment ratings, and these work the same way they do in the Web Content Display
portlet.

## Display Style [](id=display-style)

**Abstracts:** Shows the first 200-500 characters of the content, defined by the
**Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme
developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

## Other Settings [](id=other-settings)

**Asset Link Behavior:** The default value is *Show Full Content*. With this
value selected, when the link to an asset is clicked, the full asset is
displayed in the current Asset Publisher. If the value *View in a Specific
Portlet* is selected, clicking on an asset causes that asset to be displayed in
the portlet to which the asset belongs. For example, a blog entry would be
displayed in the Blogs portlet where it was created. Likewise, a forum post
would be displayed in the Message Boards porlet where it was created. Similarly,
a generic Web Content instance would be displayed in the Asset Publisher of its
configurated Display Page. See the secton below on Display Pages for more
information.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next
navigation; regular includes a way of selecting the page to which you'd like to
navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it
from the list.

**Show Available Locales:** Since content can be localized, you can have
different versions of it based on locale. This will show the locales available,
enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org
integration, you can allow your users to convert the content to one of several
formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable
print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see
below).

![Figure 5.14: Available metadata
types](../../images/available-metadata-fields.png)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS
Feeds.

The Display Settings section of the Asset Publisher has numerous options to help
you configure how your content selections are displayed to your users. Even
though there are many choices, it's easy to go through the options and quickly
adjust the ones that apply to you. You'll want to use the Asset Publisher to
query for mixed assets in the portal that have relevant information for your
users.

Next, we'll look at Display Pages, an addition to the asset framework introduced
by Liferay 6.1.

## Display Page [](id=display-page)

If you've been using Liferay for a while, or you've just spent a little bit of
time with this guide, you might have noticed something about how Liferay handles
web content--content is never tied directly to a page. While this can be useful
(because it means that you don't have to recreate content if you want to display
the same thing on multiple pages), it also means that you don't have a static
URL for any web content, which is bad for search engine optimization.

As an improvement, Liferay has introduced the concept of Display Pages and
Canonical URLs. Each web content entry on the portal has a canonical URL, which
is the official location of the content that is referenced any time the content
is displayed. A Display Page can be any page with an asset publisher configured
to display any content associated with the page. When adding or editing web
content instances, you can select a Display Page, but only pages with a
configured asset publisher are available for selection.

To create a Display Page, you can create a page yourself, add an Asset Publisher
portlet and configure it yourself. Alternatively, you can use the *Content
Display Page* page template included with Liferay. If you're creating a Display
Page manually, once you've added an Asset Publisher portlet to the page, open
its configuration window. Then check the *Set as the Default Asset Publisher for
This Page* box. Also, for its display settings, set the Display Style to
*Abstracts* and the Asset Link Behavior to *View in a specific portlet*.

You may now be thinking, "Wait, you just told me that each Web Content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" Yes. That's exactly what I said. Just
watch--create a display page called *My Web Content Display Page* somewhere on
your portal, using the *Content Display Page* template. Now, on a different
page, add a Web Content Display portlet. Click the *Add Web Content* button,
enter a title and some content, click on *Display Page* at the right, and select
the Display Page you just created. Then click *Publish*.

![Figure 5.15: Selecting a Display
Page](../../images/04-web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, you can now click
the *Read More* link to display the content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional portlets that you place on the page are displayed
along with the content when you access it via the canonical URL. If you used the
*Content Display Page* page template for your Display page, it not only features
a configured Asset Publisher portlet but also a Tags Navigation, a Categories
Navigation, and a Search portlet. These tools help users to quickly identify
relevant content.

![Figure 5.16: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to another new featured introduced by Liferay 6.1.
