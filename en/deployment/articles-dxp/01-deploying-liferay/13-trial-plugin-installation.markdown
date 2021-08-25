---
header-id: trial-plugin-installation
---

# Trial Plugin Installation

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.html) and is no longer maintained here.</span>
</aside>

For Liferay customers who are evaluating @product@ on a trial basis, **the
plugins can be accessed from within the *Apps* &rarr; *Store* (i.e.,
Marketplace) section of the Control Panel in your product installation**. 

## Installation Process

Follow the steps below to install a trial plugin:

1.  Register a `liferay.com` account (LRDC) account by visiting Liferay's home
    page (if necessary). Do this by clicking *Sign In/Create Account* button
    from the top right Profile button.

    ![Figure 1: Hover over the Profile button and click *Sign In/Create Account*.](../../images-dxp/liferay-com-sign-in.png)

2.  Start your @product@ instance (trial license is OK).

3.  After signing in as an Admin in your @product@ trial server, go to the
    Control Panel &rarr; *Apps* &rarr; *Store* and sign in to the Marketplace
    using your `liferay.com` (LRDC) account credentials. Authorize Marketplace
    to access your local account. 

    ![Figure 2: Click the *Store* link and authorize Marketplace to access your local account.](../../images-dxp/dxp-store-link.png)

4.  Once signed into the Store, click on the *Purchased* link, and then click on
    the *EE* tab.

    Here you can see a list of @product@ plugins that are installed, as well as
    options to update or install certain plugins. See [Using the Liferay
    Marketplace](/docs/7-2/user/-/knowledge_base/u/using-the-liferay-marketplace)
    for details. 

Next are answers to some common questions. 

## FAQ

**Q:** Where are the *@product@ Trial Plugins*?

**A:** There is no such thing. The @product@ plugins in Liferay Marketplace are
the same ones that you get to try out with your @product@ trial license for your
portal. The @product@ license (trial or official @product@ subscriber) gives you
access to the @product@ plugins. Also, there is no difference code-wise or
release-wise between a @product@ trial installation and a regular @product@
non-trial installation. The only difference is the license.

**Q:** Why can't I go to liferay.com/marketplace? Why can't I *purchase* from
the Marketplace site?

**A:** DXP trial users must use the Marketplace from within the product's
Control Panel (instructions above). You do not need to *purchase* any DXP
plugins because if you access Marketplace from within the Control Panel,
Marketplace sees that you have a DXP license installed and gives access to DXP
plugins. Official DXP subscription customers (i.e., non trial) can log into
`liferay.com` with their designated DXP subscriber login and access all DXP
plugins through the Marketplace website. 

**Q:** Why are the plugins under the Purchased tab? If I click on the *DXP 
Marketplace* link, it does not let me get the DXP plugins.

**A:** Once you're signed into the Store, click on the *Purchased* tab, then 
click on the *EE* tab.

**Q:** What happens when DXP trial customers become official Liferay Digital
Experience subscribers?

**A:** They can still complete the above process, or they can also visit the
[Liferay Marketplace website](https://www.liferay.com/marketplace).

**Q:** Do DXP trial customers get the DXP source code?

**A:** No, they can only install the plugin. The DXP source code becomes
available once they are official @product@ Enterprise subscribers.

**Q:** Can this process of installing DXP plugins be used from Liferay Portal CE
(Community Edition)?

**A:** No, the Marketplace must detect that you are running @product@.
