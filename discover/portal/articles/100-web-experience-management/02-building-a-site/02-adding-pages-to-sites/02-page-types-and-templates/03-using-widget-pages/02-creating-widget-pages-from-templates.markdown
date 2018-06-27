# Creating Widget Pages from Templates [](id=creating-widget-pages-from-templates)

Page templates provides a pre-configured page to reuse. Page Templates can take a couple different forms in @product-ver@. There are Widget Page templates, which consist of a portlet layout and configuration and Content 
Page templates, which are constructed from Fragments. You can read about [Content Page Templates in this tutorial](/develop/tutorials/-/knowledge_base/7-1/building-content-page-templates).

Three sample layout page templates are bundled with Liferay:

- **Search:** contains a search bar and configuration to display various facets.
  
- **Wiki:** provides a page with three applications related to authoring a wiki.

- **Blog:** provides a page with three applications related to blogging.

![Figure 1: The Blog page template is already available for use along with the Search and Wiki page templates.](../../../../../../images/default-page-templates.png)

To add a new widget page template:

1.  Go to *Build* &rarr; *Site Pages*.

2.  Select the *Page Templates* tab.

3.  Create a Collection named "Lunar Resort Templates".

4.  Click the *Add* icon (![Add Page Template](../../../../../../images/icon-add.png)).

5.  Enter a name and description for your template.

6.  Leave the *Active* button enabled.

7.  Click *Save*.

To edit the page template:

1.  Click the *Actions* icon (![Actions](../../../../../../images/icon-actions.png)).

2.  Click *Edit*.

3.  Make any changes to the page.

4.  The changes are instantly applied to the template and you don't need to
    save before quitting to keep them.

Note that after a new page template has been created, the default permissions
are to only allow the creator to use the page template. To give other users
access to it:

1.  Use the Actions menu for the template and select *Permissions*.

2.  In the matrix of roles and permissions, check the *View* permission for the 
    role or roles needed to see the page template in the list of available page 
    templates when creating a new page.

If you want any user who can create a page to be able to use the page template, 
just check the *View* permission for the *User* role.

To use your template to create a new page:

1.  Go to your Site Administration and select the *Pages* option from the 
    *Build* menu. dropdown option.
    
2.  Click the the *Add* icon (![Add Page](../../../../../../images/icon-add.png)).

3.  Inside of the Lunar Resorts collection, select the *Community* page.

4.  Enter the name of your page and click *Submit*.

Pages based on templates have one option that's not included in the  configuration for other pages - the ability to activate or deactivate inheriting
changes from the page template.

![Figure 2: You can choose whether or not to inherit changes made to the page template.](../../../../../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates pages based on a page
template, any future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by disabling the
*Inherit Changes* selector. Occasionally, propagation for page templates fails
due to unintended errors. To learn how to manage a failed page template
propagation, visit the
[Propagating Changes from Site Templates to Sites](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates#propagating-changes-from-site-templates-to-sites)
tutorial.

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes still need to be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the *Inherit
Changes* selector does not appear.

You can read more about staging in the[Staging Content for Publication tutorial](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication).

## Sharing Widget Page Templates [](id=sharing-widget-page-templates)

If you want to export a page that uses a page template to a different 
environment (through a LAR file or remote publication), the template must be 
exported and imported manually in advance or the import will fail.

To export a page using a page template, use the following process:

1.  Go to Control Panel → Sites → Page Templates.

2.  Next to the page template you would like to export, click Actions → Export. 
    This produces a LAR file you can import later.

3.  On the target environment, go to Control Panel → Sites → Page Templates and 
    create a new page template.

4.  Next to the new template, click Actions () → Import.

5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to your new environment. For 
more information on exporting/importing content, visit the Importing/Exporting 
Pages and Content article

