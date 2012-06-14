#### Tags and Categories [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags are keywords that are attached to web content in order to help users find content. Categories are hierarchical structures of content that are defined by administrators. Tags and categories make it easier for your users to find content through search or navigation.

By assigning a tag to web content, you define metadata about that content. This is then used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by creators of content, and it's important to tag content whenever it is created. If you don't tag content, all the search engine has to go on is the full text of the content (if you've made your content indexable). This might not produce the best results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

Tags can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

Categories are a little bit different, and new users generally ask a valid question when presented with tags and categories: what's the difference? Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they're never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the author. Categories do the same thing as the table of contents. 

By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 3.18: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used to manage all your tags and categories in one place. We'll look at this interface in the next chapter. 

Next, we'll look at Liferay's asset framework and how to use the Asset Publisher portlet.

### Using the Asset Publisher Portlet [](id=lp-6-1-ugen03-using-the-asset-publisher-portlet-0)

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your content. Let's look at some of its features.

#### Querying for Content [](id=lp-6-1-ugen03-querying-for-content-0)

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet, or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

##### Selecting assets manually [](id=lp-6-1-ugen03-selecting-assets-manually-0)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell the Asset Publisher that you want to select content manually. You can select what you want to be published within the portlet, or you can create new content  from within the Asset Publisher.

![Figure 3.22: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here is added to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

##### Selecting assets dynamically [](id=lp-6-1-ugen03-selecting-assets-dynamically-0)

The Asset Publisher's default behavior is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you'll display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 3.23: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping [](id=lp-6-1-ugen03-ordering-and-grouping-0)

You can display the content returned by the filters above in order by title, create date, modified date, view count, and more in ascending or descending order. For instance, you may have a series of "How To" articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type*, or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. Again, we'll see more about categories in chapter 4.

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings [](id=lp-6-1-ugen03-display-settings-0)

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type, and file conversions. Additionally, you can enable printing, flags, ratings, comments, and comment ratings, and these work the same way they do in the Web Content Display portlet.

##### Display Style [](id=lp-6-1-ugen03-display-style-0)

**Abstracts:** Shows the first 200-500 characters of the content, defined by the **Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

##### Other Settings [](id=lp-6-1-ugen03-other-settings-0)

**Asset Link Behavior:** The default value is *Show Full Content*. With this value selected, when the link to an asset is clicked, the full asset is displayed in the current Asset Publisher. If the value *View in a Specific Portlet* is selected, clicking on an asset causes that asset to be displayed in the portlet to which the asset belongs. For example, a blog entry would be displayed in the Blogs portlet where it was created. Likewise, a forum post would be displayed in the Message Boards porlet where it was created. Similarly, a generic Web Content article would be displayed in the Asset Publisher of its configurated Display Page. See the secton below on Display Pages for more information.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next navigation; regular includes a way of selecting the page to which you'd like to navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it from the list.

**Show Available Locales:** Since content can be localized, you can have different versions of it based on locale. This will show the locales available, enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org integration, you can allow your users to convert the content to one of several formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see below).

![Figure 3.24: Show Metadata](../../images/portal-admin-ch4_html_m409b2939.jpg)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, it's easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

Next, we'll look at Display Pages, an addition to the asset framework introduced by Liferay 6.1. 

#### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of time with this guide, you might have noticed something about how Liferay handles web content--content is never tied directly to a page. While this can be useful (because it means that you don't have to recreate content if you want to display the same thing on multiple pages), it also means that you don't have a static URL for any web content, which is bad for search engine optimization.

As an improvement, Liferay has introduced the concept of Display Pages and Canonical URLs. Each web content entry on the portal has a canonical URL, which is the official location of the content that is referenced any time the content is displayed. A Display Page can be any page with an asset publisher configured to display any content associated with the page. When adding or editing web content articles, you can select a Display Page. However, only pages with a configured asset publisher are available for selection. 

To create a Display Page, you can create a page yourself, add an Asset Publisher portlet, and configure it yourself. Alternatively, you can use the *Content Display Page* page template included with Liferay. If you're creating a Display Page manually, once you've added an Asset Publisher portlet to the page, open its configuration window. Then check the *Set as the Default Asset Publisher for This Page* box.

You may now be thinking, "Wait, you just told me that each Web Content item has its own URL, and that this is somehow related to pages where we display a whole bunch of content on the same page?" Yes. That's exactly what I said. Just watch--create a display page called *My Web Content Display Page* somewhere on your portal, using the *Content Display Page* template. Now, on a different page, add a Web Content Display portlet. Click the *Add Web Content* button, enter a title and some content, click on *Display Page* at the right, and select the Display Page you just created. Then click *Publish*.

![Figure 3.16: Selecting a Display Page](../../images/04-web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, you can now click the *Read More* link to display the content. Notice that the canonical URL for content appears in your browser's address bar. If you create your own custom display page, any additional portlets that you place on the page are displayed along with the content when you access it via the canonical URL. If you used the *Content Display Page* page template for your Display page, it not only features a configured Asset Publisher portlet but also a Tags Navigation, a Categories Navigation, and a Search portlet. These tools help users to quickly identify relevant content.

![Figure 3.17: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to another new featured introduced by Liferay 6.1. 

## Related Assets [](id=related-assets)

Related Assets is a new feature in Liferay 6.1 that enables you to connect any number of assets within a site or across the portal, even if they don't share any tags and aren't in the same category. We've already seen that you can show related assets within the display for a specific asset, and with the Related Assets portlet you can show links to any assets which are related to content displayed on that page.

The Related Assets portlet is based on the Asset Publisher, and possseses essentially the same interface with one key difference. The Asset publisher displays any content that meets the criteria selected in the portlet configuration. The Related Assets portlet only displays content that meets the criteria, and also is listed as a related asset for a piece of content that is currently published on the page where it is placed.

Let's move on, now, to the Calendar portlet.
