# Managing Site Templates

To get started, click on *Site Templates* in the Sites section of the Control
Panel. Here, you can add, manage, or delete site templates. You can also
configure the permissions of site templates. As long as a site is linked to the
site template it was created from, changes to the site template's pages, apps,
and app configurations are propagated to the site. Changes to a site template's
content, however, are not propagated to existing sites that are linked to the
site template. You'll learn about the propagation of changes between site
templates and sites in more detail in the section on site template use cases
below.

To manage the pages of a site template:

1.  Click on *Site Templates* in the Control Panel

2.  Select the *Actions* icon (![Actions](../../../../images/icon-actions.png)) 
    and then *Manage* for an existing template.
    
If you open the main Menu on the left side of your screen (if necessary), 
the site template is selected in the Site Administration dropdown menu. You're provided some similar options as a regular site which include
*Build*, *Content*, *Configuration*, and *Publishing*. By default, the
Manage Interface opens *Build* &rarr; *Pages*. From here, you can add
or remove pages from a site template or select themes and layout templates to
apply to the site template. You can also configure each page to have any theme, 
any layout template, and any number of applications, just like a page of a 
regular site. As with site pages, you can organize the pages of a site template 
into hierarchies. When you create a site using a site template, the 
configuration of pages and apps is copied from the template to the site. By
default, all changes made to the site template are automatically copied to sites
based on that template.

+$$$

**Tip:** If you want to publish a piece of web content to many sites and ensure
modifications are applied to all, don't use site template content for that
purpose. Instead, place the content in the global scope and then reference it
from a *Web Content Display* application in each site.

$$$

The Content section offers separate repositories for content related apps
based on your site template. For instance, by clicking *Polls* from the Content
section, you can create a poll question that is only available for that specific
site template. Assets created within your template's Content section can
only be accessed by sites using the template.

The Configuration section includes Application Display Templates and
Mobile Device configuration options for your site template. Also, nested in the
Configuration section is the *Site Template Settings*. This option allows you to
edit the template's name and description while also offering boolean options for
activating your site template and allowing site administrators to modify pages
associated with your template.

The following figure displays the form shown when editing the *Department*
template's settings:

![Figure 1: Site templates have several configurable options including the option to allow site administrators to modify pages associated with the site template.](../../../../images/site-template-settings.png)

By default, the following site templates are provided:

- **Intranet Site:** Provides a preconfigured site for an intranet. The Home
  page displays the activities of the members of the site, search, a language
  selector, and a list of the recent content created in the intranet. It also
  provides two additional pages for Documents and Media and external News
  obtained through public feeds.

- **Community Site:** Provides a preconfigured site for building online
  communities. The Home page of a *community site* provides message boards,
  search, a display of a poll and statistics of the activity of community
  members. The site will also be created with a page for a wiki.

Now that you know the basics for creating and managing your site templates,
you can learn about propagating changes next.
