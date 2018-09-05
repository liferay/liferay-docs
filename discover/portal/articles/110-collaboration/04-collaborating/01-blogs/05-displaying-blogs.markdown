# Displaying Blogs [](id=displaying-blogs)

The Blogs app in Site Administration lets you 
[add](https://www.liferay.com/), 
[manage](https://www.liferay.com/), 
and 
[configure](https://www.liferay.com/) 
your site's blogs. You can then display those blogs by adding a separate Blogs 
widget to a page. Adding the Blogs widget to a site page creates a shared blog 
for site members. Adding the widget to a user's personal site (dashboard) 
creates a blog just for that user. The widget works the same way in both cases. 
And of course, you can scope a blog to a page to produce a blog instance for 
just that page. 

To add a Blogs widget to a page:

1.  Navigate to the page. 

2.  From the *Add* menu 
    (![Add](../../../../images/icon-add-app.png)), open *Widgets* &rarr; 
    *Collaboration*. 

3.  Drag a *Blogs* widget onto the page.

By default, the Blogs widget lists abstracts of the site's recent blog entries. 
The listing shows each entry's cover image prominently. Each abstract in the 
listing also shows the number of comments, thumbs up/down ratings, and links to 
share the entry on Twitter, Facebook, LinkedIn, and other social networking 
sites. Clicking a blog entry lets you view its full content, where you can also 
comment on the entry. 

![Figure 1: Fancy a lunar spelunking trip? This blog entry's abstract lets you know what you're getting into.](../../../../images/blog-entry-abstract.png)

There are several display options that let you configure the listing to look the 
way you want. To configure the widget, click the *Options* icon 
(![Options](../../../../images/icon-app-options.png)) in its title bar and 
select *Configuration*. The display settings are in the *Setup* tab:  

-   **Enable Ratings:** Whether readers can rate blog entries. 

-   **Enable Comments:** Whether readers can comment on blog entries. 

-   **Show View Count:** Whether to show the number views for each entry. 

-   **Social Bookmarks:** The social networking sites that users can share blog 
    entries with. Only those in the *Current* column are displayed via the share 
    buttons on each blog entry. To move social networking sites between the 
    *Current* and *Available* columns, select the sites and use the arrows 
    between those columns. Similarly, use the up/down arrows beneath the 
    *Current* column to reorder the sites as they appear on each blog entry. 

-   **Display Style:** The display style for social bookmarks. *Inline* is the 
    default and displays the social bookmark icons in a row. *Menu* hides them 
    inside a single share menu. 

-   **Maximum Items to Display:** The total number of blog entries to display
    on the initial page. You can select up to 75 to display at once. 

-   **Display Template:** The overall appearance of blog entries in the listing. 
    *Abstract* is the default, and is shown in the above screenshot. You can 
    also choose the following: 

    -   **Full Content:** Displays each blog entry's full content. 
    -   **Title:** Displays only the blog entry's title.
    -   **Basic:** A stripped-down version of the Abstract, with less text and 
        no cover image.
    -   **Card:** Displays each blog entry in a card-like rectangle that shows 
        the cover image, title, author, post date, and a few lines of text. 

    ![Figure 2: The *Card* display template makes your blog posts look like fun little trading cards.](../../../../images/blogs-cards.png)

    To select a different application display template (ADT) or create your own, 
    click *Manage Templates*. For more information, see the documentation on 
    [application display templates](/discover/portal/-/knowledge_base/7-1/using-page-fragments). 

-   **Enable Report Inappropriate Content:** Whether to let users flag content 
    as inappropriate, which sends an email to administrators. 

-   **Enable Ratings for Comments:** Whether to let readers rate blog entry 
    comments.

-   **Show Related Assets:** Whether to display related content from other 
    apps/widgets in blog entries. 

There are also other tabs in *Configuration*: 

-   **Communication:** Lists public render parameters the widget publishes to 
    other widgets on the page. Other apps/widgets can read and take actions on 
    these. For each shared parameter, you can specify whether to allow 
    communication using the parameter and select which incoming parameter can 
    populate it. 

-   **Sharing:** Embed the widget instance as a widget on any website, 
    Facebook, Netvibes, or as an OpenSocial Gadget. 

-   **Scope:** Specify the blog instance the widget displays: the current site's 
    blog (default), the global blog, or the page's blog. If the page doesn't
    already have a blog instance, you can select scope option *\[Page Name\]
    \(Create New\)* to create a page-scoped blog instance. 

When you finish setting the options, click *Save* and then close the dialog box. 
