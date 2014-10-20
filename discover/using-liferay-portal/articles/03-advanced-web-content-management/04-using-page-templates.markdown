# Using Page Templates [](id=using-page-templates)

Click on *Page Templates* in the Control Panel to see a list of page templates.
Page templates function similarly to site templates but at the page level. Each
page template provides a pre-configured page to reuse. Within a page template,
it's possible to select a theme, a layout template, to add portlets to the page
and to configure portlet preferences. Both sites and site templates can utilize
page templates for creating new pages.

![Figure 3.14: The Blog page template is already available for use along with the Content Display Page and Wiki page templates.](../../images/server-configuration-page-templates.png)

You can edit or delete existing page templates, configure their permissions, or
add new page templates. By default, three sample page templates are provided:

- **Blog:** provides a page with three applications related to blogging. It has
  two columns, the main left column contains the blogs portlet and the small
  right column provides two side portlets, Tag Cloud and Recent Bloggers. The
  tag cloud application will show the tags used within the site and will allow
  navigating through the blog entries shown in the main blogs portlet.

- **Content Display Page:** provides a page preconfigured to display content. It
  has three auxiliary applications (Tags Navigation, Categories Navigation, and
  Search) and an Asset Publisher. The most significant aspect of this page is
  that the Asset Publisher is preconfigured to be display any web content
  associated with this page. This means that you can select any page created
  from this page template as a *Display Page* for a web content article. You can
  choose a display page for a web content article when creating a new web
  content article or when editing an existing one. When you create a new web
  content article, a unique (canonical) URL for the web content pointing to this
  page will be assigned to it.

- **Wiki:** provides a page with three applications related to authoring a wiki.
  It also has two columns, the main left column with the wiki application and
  two right side portlets to allow navigating through pages by tags and
  categories.

To add a new page template, click the *Add* button. Then enter a name and
description for your template. Leave the *Active* button checked. Click *Save*
and then identify your page template in the list. Click its name or use the
Actions button to edit the page template. The *Open Page Template* link opens a
new browser window which you can use to configure your new page. Any changes you
make are automatically saved so you can close the new browser window once you're
done.

Note that after a new page template has been created the default permissions are
to only allow the creator to use the page template. To give other users access
to it, use the actions menu in the list of templates and choose *Permissions*.
Once you see the matrix of roles and permissions, check the *View* permission
for the role or roles needed to see the page template in the list of available
page templates when creating a new page. If you want any user who can create a
page to be able to use the page template, just check the *View* permission for
the *User* role.

![Figure 3.15: When creating a new site page, you're given options for the page template and page type.](../../images/selecting-page-template.png)

To use your template to create a new page, just navigate to a page over which
you have site administrator privileges and select *Add* &rarr; *Page* from the
left menu. You'll be able to select a page template and type a name for the new
page. Alternatively, you can use the Site Administration page. First, make sure
you're on your desired site and navigate to *Admin* &rarr; *Site
Administration*. Then click on *Site Pages* &rarr; *Add Page*, type a name,
select your template from the drop down menu, and click *Add Page* to finish.

![Figure 3.16: You can choose whether or not to automatically apply page template changes to live pages.](../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates pages based on a page
template, any future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by unchecking the
*Automatically apply changes done to the page template* box. Occasionally,
propagation for page templates fails due to unintended errors. To learn how to
manage a failed page template propagation, visit the *Propagating Changes from
Site Templates to Sites* section of this chapter.

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes still need to be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the
*Automatically apply changes done to the page template* checkbox does not
appear.

We'll discuss staging in more detail later in this chapter. For now let's look
at importing and exporting templates.

## Exporting and Importing Site Templates and Page Templates [](id=exporting-and-importing-site-templates-and-page-templates)

If you want to export a site that uses site or page templates to a different
environment (through a LAR file or remote publication), the templates must be
exported and imported manually in advance or the import will fail.

To export a Site using a Site Template, use the following process:

1. Go to *Control Panel* &rarr; *Site Templates* and click *Actions* &rarr;
*Manage* for the Site Template your site is using.
2. Click *Export* to obtain a LAR file with the content of the Site Template. Be
sure to choose the applications and data you want exported.
3. In your target environment, go to *Control Panel* &rarr; *Site Templates* and
create a new Site Template.
4. Click *Actions* &rarr; *Manage Pages* for that Site Template and then click
*Import*.
5. Upload the LAR file containing your site template's content.

Now the site can be exported and imported normally to this new environment.

For page templates, the process very similar:

1. Go to *Control Panel* &rarr; *Page Templates*.
2. Next to the page template you would like to export, click *Actions* &rarr;
*Export*. This produces a LAR file you can import later.
3. On the target environment, go to *Control Panel* &rarr; *Page Templates* and
create a new Page Template.
4.  Next to the new template, click *Actions* &rarr; *Import*.
5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to this new environment.

Next, let's examine the tools Liferay provides for handling translations.
