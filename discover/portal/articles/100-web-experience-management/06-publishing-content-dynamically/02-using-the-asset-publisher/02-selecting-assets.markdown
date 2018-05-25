# Selecting Assets

The Asset Publisher can be configured to select assets manually or dynamically 
by selecting various criteria. Within those options there is a great deal of
flexibility in what assets are displayed and how they are displayed.

## Selecting Assets Manually [](id=selecting-assets-manually)

To enable manual asset selection:

1.  Click the click the *Options* icon (![Options](../../../../images/icon-options.png)) in the application's menu.

2.  Select *Configuration* from menu.

3.  In the Asset Publisher configuration, select *Manual* from the select box beneath *Asset Selection* sets.

Now you need to select a *Scope* and specific *Asset Entries* from that scope 
to display. You can configure multiple scopes, including the global scope, from which to select assets.

![Figure 1: Selecting assets in the Asset Publisher manually is similar to selecting assets in the Web Content Display application except that you can select assets of any type, not just web content. You can also add scopes to expand the list of assets that are available to be displayed in the Asset Publisher.](../../../../images/web-content-asset-publisher-manual.png)

When selecting assets manually, you'll see a list of configured scopes under the
Scope heading. You can configure scope as follows:

1.  Click the "X" button at the right to remove a scope from the
    list.

2.  Click the *Select* button to add additional scopes to the Asset
    Publisher's configuration.
    
After you've added a scope, a new Select button appears under the Asset Entries 
heading. A list of assets selected for display appears in the Asset Entries 
section. You can select assets to be displayed by clicking on the appropriate 
*Select* button. One button appears for each configured scope. By default, the 
available asset types include the following:

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

## Selecting Assets Dynamically [](id=selecting-assets-dynamically)

The Asset Publisher's default behavior is to select assets dynamically according
a set of customizable rules. These rules can be stacked on top of each other so
that they compliment each other to create a nice, refined query for your
content. You can define complicated rules for selecting assets for display and
@product@ automatically takes permissions into account. Liferay's Asset 
Publisher performs well in these situations since it queries by search index 
instead of querying the database directly. You have the following options for 
creating rules for selecting content:

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

![Figure 2: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../../../images/web-content-asset-publisher-filter.png)

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

In addition, you can use these advanced filters:

-   **Show only assets with *Welcome* as its display page** displays only assets
    specifically configured for the *Welcome* page.
-   **Include tags specified in the URL?** lets you specify tags in the URL for
    the Asset Publisher to display.

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

**Note:** The following actions will have immediate effects in your Asset 
Publisher:
- Change the value of the *Asset Selection* option.
- Change the value of the *Scope* option.
- Select, add, sort or delete asset entries (only when selecting assets manually).

Other changes in the rest of the options will come into effect after clicking 
*Save*. Next you'll learn about the Asset Publisher's other configuration 
options.
