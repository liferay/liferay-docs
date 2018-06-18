# Managing Site Templates [](id=managing-site-templates)

To get started, click on *Site Templates* in the Sites section of the Control
Panel. Here, you can add, manage, or delete Site templates. You can also
configure the permissions of Site templates. As long as a Site is linked to the
Site template it was created from, changes to the Site template's pages, apps,
and app configurations are propagated to the Site. Changes to a Site template's
content, however, are not propagated to existing Sites that are linked to the
Site template. You'll learn about the propagation of changes between Site
templates and Sites in more detail in the section on Site template use cases
below.

To manage a Site Template's pages,

1.  Click on *Site Templates* in the Control Panel. 

2.  Select the *Actions* icon (![Actions](../../../../images/icon-actions.png)) 
    and then *Manage* for an existing template.
 
If you open the main Menu on the left side of your screen (if necessary), the
Site Template is selected in the Site Administration dropdown menu. You're
provided similar options as a regular Site, including *Build*, *Content*,
*Configuration*, and *Publishing*. By default, the Manage Interface opens
*Build* &rarr; *Pages*. From here, you can add or remove pages from a Site
Template or select themes and layout templates to apply to the Site Template.
You can also configure each page to have any theme, any layout template, and any
number of applications, just like a page of a regular Site. As with Site pages,
you can organize a Site Template's pages into hierarchies. When you create
a Site using a Site template, the configuration of pages and apps is copied from
the template to the Site. By default, all changes made to the Site template are
automatically copied to Sites based on that template.

+$$$

**Tip:** If you want to publish a piece of web content to many Sites and ensure
modifications are applied to all, don't use Site template content for that
purpose. Instead, place the content in the global scope and then reference it
from a *Web Content Display* application in each Site.

$$$

The Content section offers separate repositories for content related apps
based on your Site Template. For instance, by clicking *Polls* from the Content
section, you can create a poll question that is only available for that specific
Site template. Assets created within your template's Content section can
only be accessed by Sites using the template.

The Configuration section includes Application Display Templates and
Mobile Device configuration options for your Site Template. Also, nested in the
Configuration section is the *Site Template Settings*. This edits the template's
name and description while also offering boolean options for activating your
Site template and allowing Site administrators to modify pages associated with
your template.

The following figure displays the form shown when editing the *Department*
template's settings:

![Figure 1: Site templates have several configurable options including the option to allow Site administrators to modify pages associated with the Site template.](../../../../images/site-template-settings.png)

By default, the following Site templates are provided:

- **Intranet Site:** Provides a preconfigured Site for an intranet. The Home
  page displays the activities of the members of the Site, search, a language
  selector, and a list of the recent content created in the intranet. It also
  provides two additional pages for Documents and Media and external News
  obtained through public feeds.

- **Community Site:** Provides a preconfigured Site for building online
  communities. The Home page of a *community Site* provides message boards,
  search, a display of a poll and statistics of the activity of community
  members. The Site will also be created with a page for a wiki.

Now that you know the basics for creating and managing your Site templates,
you can learn about propagating changes next.
