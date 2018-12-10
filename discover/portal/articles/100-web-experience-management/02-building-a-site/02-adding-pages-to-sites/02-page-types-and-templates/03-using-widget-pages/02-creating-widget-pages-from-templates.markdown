# Creating Widget Pages from Templates [](id=creating-widget-pages-from-templates)

Page templates provide pre-configured pages to reuse. There are two types of
page templates in @product-ver@: Widget Page templates consist of a portlet
layout and configuration. Content Page templates are constructed from
Fragments. You can read about 
[Content Page Templates in this article](/discover/portal/-/knowledge_base/7-1/building-content-pages-from-fragments).

Three sample layout page templates are installed by default:

- **Search:** Contains a search bar and configuration to display various facets.
 
- **Wiki:** Provides a page with three applications related to authoring a wiki.

- **Blog:** Provides a page with three applications related to blogging.

![Figure 1: The Blog page template is already available for use along with the Search and Wiki page templates.](../../../../../../images/default-page-templates.png)

To add a new widget page template,

1.  Go to *Build* &rarr; *Pages*.

2.  Select the *Page Templates* tab.

3.  Create a Collection named *Lunar Resort Templates*.

4.  Click the *Add* icon (![Add Page Template](../../../../../../images/icon-add.png)) and select *Widget Page 
    Template.

5.  Enter a *Name*.

6.  Click *Save*.

The editing page for the template appears. You can add widgets to the page or
access page configuration now. The changes you make are instantly applied to
the template.

If you want to edit the template again, 

1.  Go back to the *Page Templates* tab.

2.  Click the *Actions* icon (![Actions](../../../../../../images/icon-actions.png)).

3.  Click *Edit*.

Note that after a new page template has been created, the default permissions
only allow the creator to use the page template. To give other users access to
it,

1.  Use the Actions menu for the template and select *Permissions*.

2.  In the matrix of Roles and permissions, check the *View* permission for the 
    Roles needed to see the page template in the list of available page
    templates when creating a new page.

If you want any user who can create a page to be able to use the page template, 
check the *View* permission for the *User* Role.

To use your template to create a new page,

1.  Go to Site Administration and select the *Pages* option from the 
    *Build* menu dropdown option.
 
2.  Click the *Add* icon (![Add Page](../../../../../../images/icon-add.png)).

3.  Inside the Lunar Resort collection, select the page template that you 
    created.

4.  Enter the name of your page and click *Submit*.

Pages based on templates can inherit changes from the page template:

![Figure 2: You can choose whether or not to inherit changes made to the page template.](../../../../../../images/automatic-application-page-template-changes.png)

By default, when a Site administrator creates pages based on a page
template, future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by disabling the
*Inherit Changes* selector. Occasionally, propagation for page templates fails
due to unintended errors. To learn how to manage a failed page template
propagation, visit the
[Propagating Changes from Site Templates to Sites](/discover/portal/-/knowledge_base/7-1/propagating-changes-from-site-templates-to-sites)
tutorial.

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes must still be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the *Inherit
Changes* selector does not appear.

You can read more about staging in the
[Staging Content for Publication tutorial](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication).

## Sharing Widget Page Templates [](id=sharing-widget-page-templates)

When importing pages to a new site or environment, you must also import
templates associated with those pages. Generally templates are
included automatically when an associated page is exported, but if not you 
can export the template collection separately so the page can be imported 
to the new environment. To export page templates, 

1.  Go to *Site Management* &rarr; *Build* &rarr; *Pages*.

2.  Select the *Page Templates* tab.

3.  At the top right of the page, click *Options* 
    (![Options](../../../../../../images/icon-options.png)) &rarr; 
    *Export/Import*.

4.  On the *Export/Import* page you can choose to export configuration data and
    change which Collections and templates are being exported.

5.  When you're done configuring the export, click *Export* and save the 
    exported LAR file.

6.  On the target environment, go to *Site Management* &rarr; *Build* &rarr; 
    *Pages* and select *Page Templates*.

7.  At the top right of the page, click *Options* 
    (![Options](../../../../../../images/icon-options.png)) &rarr; 
    *Export/Import*.

8.  Select the *Import* tab.

9.  Upload the LAR with your template data. If the LAR contains 
    additional content you don't want to import, you can deselect it.

Once the template has been imported, the page can be imported normally to your
new environment. For more information on exporting/importing content, visit the
[Importing/Exporting Sites and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content)
article. 

