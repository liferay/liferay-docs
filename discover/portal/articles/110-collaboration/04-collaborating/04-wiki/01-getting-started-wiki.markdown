# Getting Started with Wikis [](id=getting-started-with-wikis)

The Menu (![Menu](../../../../images/icon-menu.png)) 
is the best place to start working with your wikis. Click the *Menu* 
(![Menu](../../../../images/icon-menu.png)), navigate to your site, and select 
the *Content* section. If you're updating an existing page-scoped wiki app 
instance, you can select that page scope from the scope menu the Gear icon
(![Gear](../../../../images/icon-control-menu-gear.png)) 
makes available. The site's wiki app instance is available in the Default scope. 
Once you're in the proper content scope, click *Wiki*. The Wiki administration 
screen lets you add, modify, and delete wiki nodes. A Wiki app instance can 
contain many wiki nodes. By default, it contains one node: *Main*. 

![Figure 1: The Wiki app instance has a wiki node named *Main* with a single front page. You can build on the Main node or click the Add icon to create a new node.](../../../../images/wiki-admin-empty.png)

## Configuring Wikis [](id=configuring-wikis)

Before adding to your wiki instance, you should configure it. The instance's 
interfaces for permissions, export and import, configuration, and application 
templates are accessible from the Options menu. Click the
*Options* icon 
(![Options](../../../../images/icon-options.png)) to open this menu.

The following options are available in this menu: 

**Wikis Permissions**: Specify which
[Roles](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) can create
wiki nodes and access the Wikis Permissions screen. For example, if you've
created a specific Role for creating wiki nodes and want to enable that Role to
create new wiki nodes in this wiki application instance, select the Role's
check box in the *Add Node* column and then click *Save*. 

**Export / Import**: Import existing wiki content into your wiki app instance,
or export wiki content to a file. For details, refer to 
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content).

**Configuration**: Configure email notifications and RSS feeds. The *Email
From*, *Page Added Email*, and *Page Updated Email* tabs are similar to other
apps' notification email settings tabs; they customize who wiki emails come
from and the format and text of the email sent when a page is added or updated.
The *RSS* tab lets you configure RSS feeds. 

**Permissions**: Specify which Roles can view the wiki app instance's Options
menu, access the menu's Configuration and Permissions options, and access any
custom preference options added to the wiki app. 

## Adding Wikis [](id=adding-wikis)

Follow these steps to create a new wiki node: 

1.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) to start creating a new wiki node. 
    The *New Wiki Node* form appears. 

2.  Add a name and description for the wiki node. 

3.  Open the form's *Permissions* section and define the wiki node's 
    permissions. You can select the following permissions in the *Viewable
    by* menu:

    -   Anyone (Guest Role)
    -   Site Members
    -   Owner

    You can also click the *More Options* link to assign permissions to specific 
    Roles. 

4.  Click *Save* when you're done creating the wiki node. 

![Figure 2: The New Wiki Node form lets you describe your new node, set view permissions, and set permissions for the Guest and Site Member roles.](../../../../images/wiki-new-wiki-node.png)

## Wiki Node Options [](id=wiki-node-options)

Next to each listed wiki node is an *Actions* menu 
(![Actions](../../../../images/icon-actions.png)). Here are the actions available 
in this menu: 

**Edit**: Edit the wiki's name and description.

**Permissions**: Specify which roles can add attachments to wiki pages, add
pages, delete pages, import pages, set permissions on the wiki node, subscribe
to modifications, update existing pages, and view the wiki node. 

**Import Pages**: Import data from other wikis. This lets you migrate from
another wiki application to the @product@ wiki. You might want to do this if
you're migrating your site from a set of disparate applications (i.e.,
a separate forum, a separate wiki, a separate content management system) to
@product@, which provides all of these features. Currently, MediaWiki is the
only supported wiki.

**RSS**: Subscribe to an RSS feed using Live Bookmarks, Yahoo, Microsoft
Outlook, or an application on your machine.

**Subscribe**: Subscribe to a wiki node. Any time a wiki page is added or
updated, the portal sends you an email notification. 

**View Removed Attachments**: Display attachments that have been removed from
the wiki node. 

**Move to Recycle Bin**: Moves the wiki node to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets). 

![Figure 3: Each wiki node's Actions menu lists actions you can perform.](../../../../images/wiki-options.png)

Before opening wiki nodes to contributors, you should consider whether to 
associate a workflow with them. For example, you could create a workflow that
requires an administrator's approval to publish a wiki page modification (add,
update, or delete). You can access your site's default *Wiki Page* workflow from
within the Site Administration Menu, by navigating to *Configuration* &rarr; 
*Workflow* for your site. To learn how to use workflow, see 
[Using Workflow](/discover/portal/-/knowledge_base/7-1/using-workflow). 
