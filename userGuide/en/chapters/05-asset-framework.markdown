# Leveraging the Asset Framework [](id=lp-6-1-ugen05-leveraging-the-asset-framework-0)

Any type of content in Liferay is considered an asset. In chapters 2 and 3, we already examined Liferay's most common type of asset: web content. Other types of assets include blog posts, wiki articles, message board posts, bookmarks, and documents. It's possible for developers to define custom asset types that utilize Liferay's asset framework. Originally, the asset framework was created to provide a mechanism for adding tags to blog entries, wiki articles, and web content without reimplement the same functionality multiple times. The asset framework has been greatly extended since then and it now supports tags, categories, comments, ratings, and asset relationships.

This chapter covers the following topics:

- Tagging and categorizing content
- Using the Asset Publisher
- Setting up display pages
- Adding relationships between assets

The Asset Publisher portlet is designed to display multiple assets. It has quite a few configuration options which we'll cover in this chapter. By default, abstracts (previews) of recently published assets are displayed by the Asset Publisher portlet and links to their full views are provided. You can configure the Asset Publisher portlet to display a table of assets, a list of asset titles, or the full content of assets. You can also configure the Asset Publisher to display only certain kinds of assets and you choose how many items to display in a list. The Asset Publisher portlet is very useful for displaying chosen types of content, for displaying recent content, and for allowing users to browse content by tags and categories. The Asset Publisher is designed to integrate with the Tags Navigation and Categories Navigation portlets to allow this.

## Tagging and Categorizing Content [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags and categories are two important tools you can use to help organize information on your portal and make it easier for your users to find the content they're looking for through search or navigation. Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 5.1: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used to manage all your tags and categories in one place. It is important that you both tag and categorize your content when you enter it. Let's take a closer look at tags and categories.

### Tags [](id=ta-3)

Tags are an important tool that you can use to help organize information on your portal and make it easier for your users to find content that they're looking for. Tags are words or phrases that you can attach to any content on the website. Tagging content will make your search results more accurate, and enable you to use tools like the Asset Publisher to display content in an organized fashion on a web page. There are two ways to create tags: you can do it through the administrative console in the control panel, or on the fly as content is created.

![Figure 5.2: The Add Tag Dialog](../../images/05-add-tag.png)

To create tags in the control panel, select the site that you want to create tags for, and select *Tags*. From this screen, you will be able to view any existing tags and make new ones. To create a new tag, simply click *Add Tag*. You'll then be asked for the name of the tag, and you'll have the ability to set permissions for viewing or managing the tag. You can also add properties to a tag. Properties basically act like tags for your tags. Structurally, properties are key-value pairs associated with specific tags that provide information about your tags. You can edit existing tags from the *Tags* window of on the control panel. You can change the tag name, change the tag's permissions, delete the tag, or add properties.

Tags are not the only portal-wide mechanism for describing content: you can also use categories.

### Categories [](id=categori-3)

Categories are similar in concept to tags, but are designed for use by administrators, not regular users. Hierarchies of categories can be created, and categories can be grouped together in *vocabularies*. While tags represent an ad hoc method for users to group content together, categories exist to allow administrators to organize content in a more official, hierarchical structure. You can think of tags like the index of a book and categories like its table of contents. Both serve the same purpose: to help the user find the information he or she seeks.

Adding vocabularies and categories is similar to adding tags. Once you've selected the site you want to work on, select *Categories* from the content section of the control panel, and you will be presented with the categories administration page.

![Figure 5.3: Categories Administration Page](../../images/05-categories.png)

Clicking on a vocabulary on the left will display any categories that have been created under that vocabulary. You can create new vocabularies simply by clicking *Add Vocabulary* and providing a name for it. You can create categories in a similar fashion by choosing a vocabulary on the left, and then selecting *Add Category*. Like tags, you can also provide properties for categories. Once you have created some vocabularies and categories, you can take advantage of the full capabilities of categories by creating a nested hierarchy of categories. To nest categories, select what you want to be the parent category, then drag any category that you want to become a child category onto it. You will see a plus sign appear next to the name of the category you are dragging if you can add it to the selected parent category; if you see a red *x* that means that you cannot add that category as a subcategory of parent category that you have selected.

Once you have created a hierarchy of categories, your content creators will have them available to apply to content that they create. Navigate to the Web Content page of the control panel and click *Add Content*. Click the Categorization link from the right-side menu and click *Select* on the vocabulary use would like to use. A dialog box will appear with your categories. Select any relevant categories by checking the box next to them, and they will be applied to the content.

Next, we'll look at Liferay's asset framework and how to use the Asset Publisher portlet.

## Using the Asset Publisher [](id=lp-6-1-ugen03-using-the-asset-publisher-portlet-0)

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts or message board messages in context with your content. Let's look at some of its features.

#### Querying for Content [](id=lp-6-1-ugen03-querying-for-content-0)

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

##### Selecting assets manually [](id=lp-6-1-ugen03-selecting-assets-manually-0)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell the Asset Publisher that you want to select content manually. You can select what you want to be published within the portlet, or you can create new content  from within the Asset Publisher.

![Figure 5.4: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here is added to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

##### Selecting assets dynamically [](id=lp-6-1-ugen03-selecting-assets-dynamically-0)

The Asset Publisher's default behavior is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you'll display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 5.5: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping [](id=lp-6-1-ugen03-ordering-and-grouping-0)

You can display the content returned by the filters above in order by title, create date, modified date, view count and more in ascending or descending order. For instance, you may have a series of "How To" articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type* or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. 

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings [](id=lp-6-1-ugen03-display-settings-0)

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type and file conversions. Additionally, you can enable printing, flags, ratings, comments and comment ratings, and these work the same way they do in the Web Content Display portlet.

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

![Figure 5.6: Available metadata types](../../images/available-metadata-fields.png)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, it's easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

Next, we'll look at Display Pages, an addition to the asset framework introduced by Liferay 6.1. 

#### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of time with this guide, you might have noticed something about how Liferay handles web content--content is never tied directly to a page. While this can be useful (because it means that you don't have to recreate content if you want to display the same thing on multiple pages), it also means that you don't have a static URL for any web content, which is bad for search engine optimization.

As an improvement, Liferay has introduced the concept of Display Pages and Canonical URLs. Each web content entry on the portal has a canonical URL, which is the official location of the content that is referenced any time the content is displayed. A Display Page can be any page with an asset publisher configured to display any content associated with the page. When adding or editing web content articles, you can select a Display Page, but only pages with a configured asset publisher are available for selection. 

To create a Display Page, you can create a page yourself, add an Asset Publisher portlet and configure it yourself. Alternatively, you can use the *Content Display Page* page template included with Liferay. If you're creating a Display Page manually, once you've added an Asset Publisher portlet to the page, open its configuration window. Then check the *Set as the Default Asset Publisher for This Page* box.

You may now be thinking, "Wait, you just told me that each Web Content item has its own URL, and that this is somehow related to pages where we display a whole bunch of content on the same page?" Yes. That's exactly what I said. Just watch--create a display page called *My Web Content Display Page* somewhere on your portal, using the *Content Display Page* template. Now, on a different page, add a Web Content Display portlet. Click the *Add Web Content* button, enter a title and some content, click on *Display Page* at the right, and select the Display Page you just created. Then click *Publish*.

![Figure 5.7: Selecting a Display Page](../../images/04-web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, you can now click the *Read More* link to display the content. Notice that the canonical URL for content appears in your browser's address bar. If you create your own custom display page, any additional portlets that you place on the page are displayed along with the content when you access it via the canonical URL. If you used the *Content Display Page* page template for your Display page, it not only features a configured Asset Publisher portlet but also a Tags Navigation, a Categories Navigation, and a Search portlet. These tools help users to quickly identify relevant content.

![Figure 5.8: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to another new featured introduced by Liferay 6.1. 

## Defining content relationships [](id=related-assets)

Related Assets is a new feature in Liferay 6.1 that enables you to connect any number of assets within a site or across the portal, even if they don't share any tags and aren't in the same category. We've already seen that you can show related assets within the display for a specific asset, and with the Related Assets portlet you can show links to any assets which are related to content displayed on that page.

The Related Assets portlet is based on the Asset Publisher and possseses essentially the same interface with one key difference. The Asset publisher displays any content that meets the criteria selected in the portlet configuration. The Related Assets portlet only displays content that meets the criteria, and also is listed as a related asset for a piece of content that is currently published on the page where it is placed.

## Summary [](id=lp-6-1-ugen05-summary-0)

In this chapter, we explored Liferay's asset framework. Any type of content in Liferay is considered an asset and can utilize the features provided by the asset framework: tags, categories, comments, ratings, and relationships. We examined the Asset Publisher portlet and looked at the many configuration options for choosing what kinds of assets to display and how to display them. We saw that the Asset Publisher portlet is designed to integrate with the Tags Navigation and Categories navigation portlets to allow users to browse content more easily. We also learned about the Display Page attribute of web content, the Content Display Page page template, and canonical URLs for assets. Assets can have display page associated with them so that the full view of the asset is displayed on the display page. The display page of an asset is used in the asset's canonical URL.
