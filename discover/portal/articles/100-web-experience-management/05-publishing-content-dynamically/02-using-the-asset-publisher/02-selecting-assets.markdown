# Selecting Assets [](id=selecting-assets)

You can configure Asset Publisher to select assets manually or dynamically 
through various criteria. Within those options there is flexibility in what
assets are displayed and how they are displayed.

## Selecting Assets Manually [](id=selecting-assets-manually)

To enable manual asset selection,

1.  Click the click the *Options* icon
    (![Options](../../../../images/icon-options.png)) in the application's
    menu.

2.  Select *Configuration* from menu.

3.  In the Asset Publisher configuration, select *Manual* from the select box
    beneath *Asset Selection* sets.

Now you must select a *Scope* and specific *Asset Entries* from that scope to
display. You can configure multiple scopes, including the global scope, from
which to select assets.

![Figure 1: Selecting assets in the Asset Publisher manually is similar to selecting assets in the Web Content Display application except that you can select assets of any type, not just web content. You can also add scopes to expand the list of assets that are available to be displayed in the Asset Publisher.](../../../../images/web-content-asset-publisher-manual.png)

When selecting assets manually, a list of configured scopes appears under the
Scope heading. You can configure scope like this:

1.  Click the *X* button at the right to remove a scope from the
    list.

2.  Click the *Select* button to add additional scopes to the Asset
    Publisher's configuration.
 
3.  After you've added a scope, a new Select button appears under the Asset
    Entries heading. A list of assets selected for display appears in the Asset
    Entries section. You can select assets to be displayed by clicking the
    appropriate *Select* button. One button appears for each configured scope.
    By default, these are the available asset types: 

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
    number of items to display per page). The Asset Publisher can mix and match
    different asset types in the same interface. 

4.  When you're done selecting items to display, click *Save*. Any selected
    assets are added to the list of assets that are displayed by the
    application. 
 
Once you have your content selected, you can configure the display types to
configure how the content appears. We'll discuss the display settings in more
detail after we finish discussing how to select assets for display.

Manual asset selection lets you select assets of various types from different
scopes, but it can be time-consuming to update the assets that should be
displayed. It's often more convenient to use the Asset Publisher to select
content dynamically.

## Selecting Assets Dynamically [](id=selecting-assets-dynamically)

The Asset Publisher's default behavior is to select assets dynamically according
a set of customizable rules. These rules can combined so that they compliment
each other to create a nice, refined query for your content. Assets are
filtered by permissions automatically, no matter how complicated your asset
selection rules are. You have the following rule types:

**Scope:** Choose the sites containing the content that should be selected. This
works the same way as with manual asset selection: assets can only be displayed
if they belong to a configured scope. The following scope options are available:

- *Current Site*
- *Global*
- *Other Site*

The Other Site scope option is unavailable for Asset Publisher applications
configured on a page template (e.g., Content Display Page).

**Asset Type:** Choose the asset types you want, from all assets, to only one,
or any combination in between. For example, you could choose only web content,
only wiki entries, or any combination of multiple types.

**Filter:** Add as many filters on tags or categories as you like. You can
choose whether the content must contain or must not contain any or all of the
tags or categories that you enter.

![Figure 2: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../../../images/web-content-asset-publisher-filter.png)

Once you've set up your filter rules for dynamically selecting content, you can
decide how the content is displayed.

If you've added custom User profile attributes, you can configure the Asset
Publisher to display assets that match them. This setting retrieves assets that
have matching categorization. These categories must be from the global context.
For example, suppose a User has a custom field called *Location* with the type
*Text*. If this attribute is set to *Moon*, you could create a vocabulary
called *Location* and a category for the Location vocabulary called *Moon*.
Then you could categorize content with *Moon* in the *Location* vocabulary.
With this organizational setup, adding an Asset Publisher and specifying
*Location* as the Asset Publisher's custom user attribute would only display
content that had been categorized as *Moon*. Pretty cool, right?

See 
[Defining Categories for Content](/discover/portal/-/knowledge_base/7.1/defining-categories-for-content)
for further information. 

In addition, you can use these advanced filters:

-   **Show only assets with** ***Welcome*** **as its display page** displays only assets
    specifically configured for the *Welcome* page.
-   **Include tags specified in the URL?** lets you specify tags in the URL for
    the Asset Publisher to display.

The *Ordering and Grouping* section of the Asset Publisher precisely controls
how content is ordered and grouped when displayed. You can order the assets
displayed by Asset Publisher in ascending or descending order by the following
attributes:

- Title
- Create Date
- Modified Date
- Publish Date
- Expiration Date
- Priority

Say you have a series of "How To" articles that you want displayed in descending
order based on whether the article was tagged with the *hammer* tag. Or suppose
you want a series of video captures to appear in ascending order based on
a category called *birds*. For these use cases, you can configure the ordering
and grouping settings.

You can also configure a second ordering. The second ordering is applied to any
assets for which the first ordering wasn't sufficient. For example, if you
ordered assets by title and there are multiple assets with the same title, the
second ordering takes effect, perhaps the publication date. 

You can establish grouping rules as well as ordering rules. You can group assets
by type or by vocabulary. For example, suppose you have a vocabulary called
*Membership Type* with two categories: *Premium* and *Regular*. If you group
assets by Membership Type, all assets with the Premium category appear in one
group and all assets with the Regular category appear in another group. Grouping
rules are applied before any ordering rules: they're a way to divide up the
displayed assets into separate lists. The ordering rules are applied separately
to each group of assets.

Note that grouping and ordering rules are only one way to control how your
content appears. You can refine the display through many other display settings
which you'll examine next.

**Note:** The following actions have immediate effects in your Asset 
Publisher:
- Change the value of the *Asset Selection* option.
- Change the value of the *Scope* option.
- Select, add, sort or delete asset entries (only when selecting assets manually).

Other changes happen after clicking *Save*. Next you'll learn about the Asset
Publisher's other configuration options.
