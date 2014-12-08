# Managing the Social Office Welcome Theme [](id=managing-the-social-office-welcome-theme)

The Social Office welcome theme is a CE-only feature. The welcome theme and
resource importer are included in the CE Social Office 2.0 package available
from Marketplace. The Social Office welcome theme provides a clean login screen
for Social Office when Social Office 2.0 is installed on a fresh portal
instance.

![Figure 1.7: The Social Office 2.0 welcome theme provides a clean and
attractive login screen.](../../images/so-welcome-theme1.png)

A fresh portal instance is an installation of Liferay Portal whose guest site
(default liferay.com site) has not yet been modified. If the guest site has not
been modified, the default theme of the guest site is replaced by the Social
Office welcome theme. The Social Office welcome theme requires the so-hook and
the resource-importer, which is why the resource-importer is also included in
the Social Office package. 

Note that the Social Office welcome theme is not intended for customization.
It's simply intended to provide a simple login screen.  If you try to customize
a page it's been applied to, you'll find that it hides the edit controls from
administrators. If you'd like to customize your portal's login page, you should
first remove the Social Office welcome theme. To do so, follow these steps:

1. Undeploy the Social Office welcome theme (so-welcome-theme).
2. Navigate to the Control Panel &rarr; Sites and select Actions &rarr; Edit
   Settings on the guest site.
3. Set the Application Adapter field to none.

![Figure 1.8: Editing the default site's
settings](../../images/so-welcome-theme2.png)

4. Set the theme of the site back to the classic theme or to your theme of
   choice.

![Figure 1.9: Editing the default site's
settings](../../images/so-welcome-theme3.png)

If you'd like to install the SO welcome theme when the guest site has already
been modified, you can follow these instructions:

First, you have to revert the guest site back to its original state when you
first installed Liferay Portal. This means that the guest site should only
contain one page and the page should only have the Sign In portlet and the Hello
World portlet. For Liferay Portal 6.1.1, a regular portal welcome theme is
deployed by default. That means that you'll need to take the following steps: 

1. Undeploy the portal welcome theme.
2. Delete all other pages ,
3. Make sure the first page of the guest site only has the Sign In and Hello
   World portlets. 
4. Deploy the Social Office welcome theme (so-welcome-theme).

This way, the Social Office welcome theme will be applied to the portal home
page.

+$$$

**Warning:** Installing the Social Office welcome theme overwrites any pages and 
customizations that have already been made to the guest site.

$$$

Next, let's discuss how Social Office facilitates team collaboration.
