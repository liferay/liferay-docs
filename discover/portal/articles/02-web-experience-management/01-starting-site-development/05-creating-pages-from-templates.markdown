# Creating Pages from Templates [](id=creating-pages-from-templates)

Page templates function similarly to site templates but at the page level. Each
page template provides a pre-configured page to reuse. Within a page template,
it's possible to select a theme or layout template, and add applications to the
page and configure app preferences. Both sites and site templates can utilize
page templates for creating new pages. Click on *Sites* &rarr; *Page Templates*
in the Control Panel to see a list of page templates.

You can edit or delete existing page templates, configure their permissions, or
add new page templates. By default, three sample page templates are provided:.

- **Content Display Page:** provides a page preconfigured to display content. It
  has three auxiliary applications (Tags Navigation, Categories Navigation, and
  Search) and an Asset Publisher. The most significant aspect of this page is
  that the Asset Publisher is preconfigured to display any web content
  associated with this page. This means that you can select any page created
  from this page template as a *Display Page* for a web content article. You can
  choose a display page for a web content article when creating a new web
  content article or when editing an existing one. When you create a new web
  content article, a unique (canonical) URL for the web content pointing to this
  page will be assigned to it.

- **Wiki:** provides a page with three applications related to authoring a wiki.
  It also has two columns, the main left column with the Wiki application and
  two right side appss to allow navigating through pages by tags and categories.

- **Blog:** provides a page with three applications related to blogging. It has
  two columns, the main left column contains the Blogs application and the small
  right column provides two side apps, Tag Cloud and Recent Bloggers. The Tag
  Cloud application shows the tags used within the site and allows navigating
  through the blog entries shown in the main Blogs app.

![Figure 1: The Blog page template is already available for use along with the Content Display Page and Wiki page templates.](../../../images/default-page-templates.png)

To add a new page template, click the *Add* icon
(![Add Page Template](../../../images/icon-add.png)). Then enter a name and
description for your template. Leave the *Active* button enabled. Click *Save*
and then identify your page template in the list. Use the *Actions* icon
(![Actions](../../../images/icon-actions.png)) to edit the page template.
Clicking the page template's name opens a new browser window which you can use
to configure your new page. Any changes you make are automatically saved so you
can close the new browser window once you're done.

Note that after a new page template has been created, the default permissions
are to only allow the creator to use the page template. To give other users
access to it, use the Actions menu in the list of templates and choose
*Permissions*. Once you see the matrix of roles and permissions, check the
*View* permission for the role or roles needed to see the page template in the
list of available page templates when creating a new page. If you want any user
who can create a page to be able to use the page template, just check the *View*
permission for the *User* role.

![Figure 2: When creating a new site page, you're given options for the page template and page type.](../../../images/selecting-page-template.png)

To use your template to create a new page, just navigate to your site's Site
Administration dropdown menu and select the *Navigation* dropdown option. Select
the *Actions* button (![Actions](../../../images/icon-actions.png)) for the page
or page set you'd like to add a page to and then click the Add Page button.
ou'll be able to select a page template and type a name for the new page.

![Figure 3: You can choose whether or not to inherit changes made to the page template.](../../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates pages based on a page
template, any future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by disabling the
*Inherit Changes* selector. Occasionally, propagation for page templates fails
due to unintended errors. To learn how to manage a failed page template
propagation, visit the
[Propagating Changes from Site Templates to Sites](/discover/portal/-/knowledge_base/7-0/building-sites-from-templates#propagating-changes-from-site-templates-to-sites)
section of this chapter.

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes still need to be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the *Inherit
Changes* selector does not appear.

You'll learn about staging later in the User's Guide. For now you'll look at
importing and exporting templates.

## Sharing Page Templates [](id=sharing-page-templates)

If you want to export a page that uses a page template to a different
environment (through a LAR file or remote publication), the template must be
exported and imported manually in advance or the import will fail.

To export a page using a page template, use the following process:

1.  Go to *Control Panel* &rarr; *Sites* &rarr; *Page Templates*.
2.  Next to the page template you would like to export, click *Actions* &rarr;
    *Export*. This produces a LAR file you can import later.
3.  On the target environment, go to *Control Panel* &rarr; *Sites* &rarr; *Page
    Templates* and create a new page template.
4.  Next to the new template, click *Actions*
    (![Add Page](../../../images/icon-actions.png)) &rarr; *Import*.
5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to your new environment. For more
information on exporting/importing content, visit the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
article.

Next, you'll examine the tools @product@ provides for exporting/importing content.
