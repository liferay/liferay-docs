# Using Full Page Applications

While Content Pages provide a way to focus on a specific design and create 
content in that context and Widget Pages provide a high level of customization 
and flexibility, Full Page Applications provide a way to configure 
and optimize a page for one specific purpose. Full Page Applications are the 
ideal way to display a Message Board, Wiki, or other application that demands a 
full page.

## Configuring the Page

Creating a Full Page Application starts just like creating any other type of 
page.

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Site Pages*.

2.  Click the (![Add Page](../../../../../images/icon-add.png)) icon.

3.  Give your page a *Name* and click *Submit*.

At this point, you created the page, but it will contain no content, and if you 
visit the page, there is no way to add any content or widgets to the page. You 
need to configure the page for it to function.

1.  From *Site Pages* click the (![Options](../../../../../images/icon-options.png)) button and select 
    *Configure*.
    
    On the next screen, you can change the page's *Name*, *Friendly URL*, and 
    set the *Full Page Application* as well as access other page configuration
    options in the other tabs.
    
![Figure X: The Full Page Application configuration page.](../../../../../images/full-page-app-configure.png)
    
2.  Set the *Full Page Application* to *Wiki* and click *Save*.

    Out of the box, you can set the Blogs, Wiki, Media Gallery, Message Boards,
    RSS, Hello Soy Portlet, Documents and Media, or Dynamic Data Mapping Form 
    to be the sole application for the page. You can also configure your own
    custom applications to be available for Full Page Applications.
    
3.  Click *Go to Site* in the Site Administration menu, and then click on your
    page.

Now the page is configured to display the Wiki and only the Wiki. No other 
widgets can be added to the page, and the Wiki app cannot be removed.

![Figure X: The Wiki displayed as a Full Page Application.](../../../../../images/single-page-app-wiki.png)

Note that all of the applications that can be added to the page are 
"noninstanceable" and the content of whichever application you select will be 
based on the instance for that site. So if you already had data in your Wiki it 
will display on this page.

If you want to configure the application to be scoped to this specific page, 
you can configure that through the application on the page.

1.  From the page, click the (![Options](../../../../../images/icon-options.png)) button for the Wiki and
    select *Configuration*.
    
2.  From the *Wiki - Configuration* page, select the *Scope* tab.

3.  Open the *Scope* menu and select *Space Wiki*.

![Figure X: Configuring the scope.](../../../../../images/configuring-scope.png)

Now the Wiki will be configured to have it's own data solely for this page, and 
not share data with the Site or Global scoped Wiki.
