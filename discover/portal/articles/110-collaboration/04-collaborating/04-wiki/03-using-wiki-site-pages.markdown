# Using the Wiki on Site Pages [](id=using-the-wiki-on-site-pages)

You can use the Wiki on Site pages via the Wiki widget. Follow these steps to 
add the Wiki widget to a page:

1.  Navigate to the page where you want to place a wiki. 

2.  From the *Add* 
    (![Add](../../../../images/icon-control-menu-add.png)) menu, open *Widgets* 
    &rarr; *Wiki* and add a *Wiki* to the page. 

Your Site's wiki nodes appear in tabs across the top of the widget. 

![Figure 1: Users can interact with your Wiki nodes when you add the Wiki widget to a page.](../../../../images/wiki-page-full.png)

To view the Wiki widget's configuration options, click its *Options* icon 
(![Options](../../../../images/icon-app-options.png)) and select *Configuration*. 
The Configuration screen appears with these tabs:

**Setup**: Lets you choose wikis to display and gives you several options for
displaying them. The *Show Related Assets*, *Enable Page Ratings*, *Enable
Comments*, *Enable Ratings for Comments*, and *Enable Highlighting* check boxes
enable or disable those features for the Wiki. You can set how you want users
to interact with wiki documents. The *Display Template* selector menu lets you
choose the Wiki's 
[Application Display Template](/discover/portal/-/knowledge_base/7-1/styling-apps-with-application-display-templates).
Below this, you can set which wiki nodes are visible. For example, you might
host two wikis on a given site, exposing one to the public and keeping the
other private for site members.

**Communication**: Configure communication across portlets, using predefined
public render parameters. From here you can modify six public render
parameters: `categoryId`, `nodeId`, `nodeName`, `resetCur`, `tag`, and `title`.
You can perform these actions on each parameter:

-   Ignore the values for this parameter that come from other portlets. For
    example, the wiki can be used along with the tags navigation app. When
    a user clicks on a tag in tags navigation, the wiki shows a list of
    pages with that tag. In some cases, an administrator may want the wiki
    to show the front page always, independently of any tag navigation done
    through other portlets. This can be achieved by selecting *Ignore*, so
    that the values of the parameter coming from those other portlets are
    ignored.

-   Read the value of a parameter from another app. This is an advanced but 
    very powerful option that lets portlets communicate without prior
    configuration. For example, imagine that the wiki is used to publish 
    information about certain countries, and a custom app that allows browsing
    countries for administrative reasons was written and placed on the same
    page. You could associate to this second app a public render parameter
    called *country* to designate the name of the country. Using this
    procedure, you can cause the wiki to show the information from the country
    being browsed in the other app. You can do this here for the wiki by
    setting the value for the title parameter to be read from the country
    parameter of the other app.

**Sharing**: Displays options you're likely to be familiar with such as the
sections for sharing the Wiki with websites, Facebook, and NetVibes. 

**Scope**: Set the wiki's scope. You can select the site-scoped or
global-scoped instance, or select/create an instance for the page. If the page
doesn't already have an instance scoped to it, you can click the *[page name]
(Create New)* menu option to create a page-scoped wiki instance.

Once you set the wiki's configuration options the way you want them, click 
*Save*. 

![Figure 2: Here the user has selected to create a new Wiki instance scoped to the current page named *Welcome*](../../../../images/wiki-app-configuration-scope.png)

The Wiki's Options menu also contains the usual widget options: 

**Look and Feel Configuration:** Set the widget's [look and
feel](/discover/portal/-/knowledge_base/7-1/look-and-feel-configuration).

**Export/Import:** [Export or import widget data](/discover/portal/-/knowledge_base/7-1/exporting-importing-app-data).

**Permissions:** Set the widget's permissions.

**Configuration Templates:** Use 
[configuration templates](/discover/portal/-/knowledge_base/7-1/configuration-templates) to
store the widget's current setup or apply an existing archived setup.

**Remove:** Remove the widget from the page. 

The Wiki displays links to all of the Wiki instance's nodes, and provides links 
for navigating around the wiki. Click on a wiki node's name to begin browsing 
that node's pages. The following navigation links are listed after the wiki 
nodes:

**FrontPage:** The wiki node's front page article. This is shown by default
when the node is initially selected. 

**Recent Changes**: Shows all of the recently updated pages.

**All Pages**: A flat, alphabetical list of all pages currently stored in the
wiki.

**Orphan Pages**: A list of pages that have no links to them. This can happen
if you remove a page link without realizing it's the only link to that page.
This area lets you review such orphaned wiki pages so that you can re-link or
delete them. 

**Draft Pages**: A list of unpublished pages. Users can edit pages and save
their changes as drafts. They can come back later to finish their changes and
publish them. 

The current wiki page's content shows in the wiki's main viewing area. Several 
features display above the wiki page content, depending on which wiki features 
are enabled and your permissions: 

-   **Add Child Page:** Add a wiki page as a child of the current wiki page. 
-   **Edit:** Edit the wiki page (if you have sufficient permissions). 
-   **Details:** View the wiki page's details (if you have sufficient 
    permissions). This is explained further in 
    [the documentation on page details](/discover/portal/-/knowledge_base/7-1/wiki-page-details). 
-   **Print:** Print the wiki page.

Additional features appear below the wiki page's content. A view counter 
displays the wiki page's view count. Ratings and comments also appear if they're 
enabled. 
