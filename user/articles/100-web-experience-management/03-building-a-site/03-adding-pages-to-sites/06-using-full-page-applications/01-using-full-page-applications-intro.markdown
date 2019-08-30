---
header-id: using-full-page-applications
---

# Using Full Page Applications

[TOC levels=1-4]

Full Page Applications are the ideal way to display a Message Board, Wiki, or
other application that demands a full page.

## Configuring the Page

Creating a Full Page Application starts just like creating any other type of 
page.

1.  Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click the (![Add Page](../../../../../images/icon-add.png)) icon.

3.  Select the *Full Page Application* page type.

4.  Give your page a *Name* and click *Save*.

5.  Set the *Full Page Application* to *Wiki* and click *Save*.

    ![Figure 1: The Full Page Application configuration page.](../../../../../images/full-page-app-configure.png)

6.  Finish the page configuration and click *Save*.

    Out of the box, you can set the Blogs, Wiki, Media Gallery, Message Boards,
    RSS, Hello Soy Portlet, Documents and Media, Form, or Application
    Authorization Request to be the sole application for the page. Developers
    can make their applications Full Page Applications.
 
7.  Click *Go to Site* in the Site Administration menu, and then click on your
    page.

Now the page is configured to display the Wiki and only the Wiki. No other 
widgets can be added to the page, and the Wiki app cannot be removed.

![Figure 2: The Wiki displayed as a Full Page Application.](../../../../../images/single-page-app-wiki.png)

Note that all of the applications that can be added to the page are
non-instanceable and the content of whichever application you select is based on
the instance for that site. So if you already had data in your Wiki it appears
on this page.

If you want to configure the application to be scoped to this specific page, you
can configure that through the application's settings.

1.  From the page, click the (![Options](../../../../../images/icon-options.png))
    button for the Wiki and select *Configuration*.
 
2.  From the *Wiki - Configuration* page, select the *Scope* tab.

3.  Open the *Scope* menu and select *Space Wiki*.

![Figure 3: Configuring the scope.](../../../../../images/configuring-scope.png)

Now the Wiki is scoped for this page, and doesn't share data with the Site or
globally scoped Wiki.
