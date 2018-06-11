# Creating a Site Template

Suppose you need to create the following three sites for the Lunar Resort's
internal use: Engineering, Marketing, and Legal. These should be private sites
that are only accessible to members of these respective departments. You could
design each site separately but you can save yourself some work if you create a
site template to use instead.

1.  Go to the Control Panel and click *Sites* &rarr; *Site Templates*. 

2.  Click the *Add* icon (![Add Site Template](../../../../images/icon-add.png)) 
    and enter the name "Department" for your template.

3.  Leave the *Active* and *Allow site administrators to modify pages
    associated with this site template...* boxes checked. 

4.  Click *Save* to create your site template.

The *Active* box must be checked for your template to be usable. If your 
template is still a work in progress, you would uncheck it to ensure that no 
one uses it until it's ready. Checking *Allow site administrators to modify 
pages associated with this site template...* allows site administrators to 
modify or remove the pages and apps that the template introduces to their 
sites---if you want the templates to be completely static, you should uncheck 
this. 

Now it's time to edit your site template. For this example, you want your site template to include four pages.

!$$$

This section assumes knowledge of Liferay 7.1 page management. For more 
information on how to create and manage pages in Liferay 7.1, see the [Adding Pages to Sites tutorial](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

$$$

1.  Click the *Options* icon (![Options](../../../../images/icon-options.png)) 
    and select *Manage*.
    
    This will bring you to the *Pages* page for the Site Template. You already 
    have a home page. Create three more pages.

2.  Create a *Full Page Application* page named *Documents*.

3.  Click *Options* (![Actions](../../../../images/icon-actions.png))  
    &rarr; *Configure* and set the *Full Page Application* to *Documents and 
    Media*

4.  Create a page using the Global Page Template *Wiki* and name it *Wiki*.

5.  Create a widget page named *Message Boards*.

6.  Click *Go to Site* in the menu to the left, to go to the pages you just 
    created.

7.  On the *Home* page add the Activities, Announcements, and Calendar apps.

8.  On the *Message Boards* page add the Message Boards and Tag Cloud apps.

The changes you made to your site template above are completed in real time, so 
there's no *Save* button that you need hit to continue.

![Figure 2: You can see the name of the site template you're currently editing.](../../../../images/editing-site-template.png)

Next, you'll use your site template to create the Engineering, Marketing and
Legal sites.

1.  Go to the Control Panel and click on *Sites* &rarr; *Sites*.

2.  Click the *Add* icon (![Add Site](../../../../images/icon-add.png)) &rarr;
    *Department*.
    
3.  Enter *Engineering* for the site name.

4.  Check the *Create default pages as private (available only to members). If 
    unchecked, they will be public (available to anyone).* option since the 
    Engineering site is intended for internal use only.

5.  Click *Save*.
    
6.  In the next section, set the Membership Type to *Private*.
    
    Recall that private sites don't appear in the My Sites 
    application so that regular users won't even know that the Engineering site 
    exists. Also, the only way users can be added to a private site is via an 
    invitation from a site administrator.
    
7.  Leave the *Active* selector enabled so that your site can be used 
    immediately.
    
8.  Check the *Create default pages as private (available only to members). If 
    unchecked, they will be public (available to anyone).* option since the 
    Engineering site is intended for internal use only.

9.  Leave the *Enable propagation of changes from the site template* box 
    enabled so that the Engineering site receives updates if the Department 
    site template is modified.

10.  Click *Save* to create your Engineering site.

11.  Repeat these steps to create the Marketing and Legal sites.

The new sites have all the pages and apps you created in the site template. To 
view the pages of the new sites, click on *Sites* &rarr; *Sites* in the Control 
Panel and then click on *Actions* &rarr; *Go to Private Pages* next to one of 
your new sites. Using site templates streamlines the site creation process for 
administrators, making it easy to create sites quickly. Now each department of 
the Lunar Resort has its own calendar, documents and media library, wiki, and 
message boards application. Although the pages and apps of each department's 
site are the same, each site will quickly be filled with department-specific 
information as users add and share content within the sites. Also, site 
administrators can add new pages, apps, and content to their sites, further 
differentiating each department's site from the others.
