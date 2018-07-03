# Creating a Site Template [](id=creating-a-site-template)

Suppose you need to create the following three private Sites for the Lunar
Resort's internal use: Engineering, Marketing, and Legal. These should be
accessible only to members of these respective departments. You could design
each Site separately, but you can save yourself time if you create a Site
template instead.

1.  Go to the Control Panel and click *Sites* &rarr; *Site Templates*. 

2.  Click the *Add* icon (![Add Site Template](../../../../images/icon-add.png)) 
    and enter the name *Department* for your template.

3.  Leave the *Active* and *Allow Site administrators to modify pages
    associated with this Site template...* boxes checked. 

4.  Click *Save* to create your Site template.

The *Active* box must be checked for your template to be usable. If your 
template is still a work in progress, uncheck it to ensure that no one uses it
until it's ready. Checking *Allow Site administrators to modify pages associated
with this Site template...* allows Site administrators to modify or remove the
pages and apps that the template introduces to their Sites---if you want the
templates to be completely static, you should uncheck this. 

Now it's time to edit your Site template. This example, includes four pages.

+$$$

This section assumes knowledge of Liferay 7.1 page management. For more 
information on how to create and manage pages in Liferay 7.1, see the 
[Adding Pages to Sites tutorial](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

$$$

1.  Click the *Options* icon (![Options](../../../../images/icon-options.png)) 
    and select *Manage*.
 
    This brings you to the *Pages* page for the Site Template. You already have
    a home page. Create three more pages.

2.  Create a *Full Page Application* page named *Documents*.

3.  Click *Options* (![Actions](../../../../images/icon-actions.png)) 
    &rarr; *Configure* and set the *Full Page Application* to *Documents and
    Media*. 

4.  Create a page using the Global Page Template *Wiki* and name it *Wiki*.

5.  Create a widget page named *Message Boards*.

6.  Click *Go to Site* in the menu to the left to go to the pages you just 
    created.

7.  On the *Home* page add the Activities, Announcements, and Calendar apps.

8.  On the *Message Boards* page add the Message Boards and Tag Cloud apps.

The changes you made to your Site template above are completed in real time, so 
there's no *Save* button.

![Figure 1: You can see the name of the Site template you're currently editing.](../../../../images/editing-site-template.png)

Next, you'll use your Site template to create the Engineering, Marketing and
Legal Sites.

1.  Go to the Control Panel and click on *Sites* &rarr; *Sites*.

2.  Click the *Add* icon (![Add Site](../../../../images/icon-add.png)) &rarr;
    *Department*.
 
3.  Enter *Engineering* for the Site name.

4.  Check the *Create default pages as private (available only to members).
    If unchecked, they will be public (available to anyone)* option since the
    Engineering Site is intended for internal use only.

5.  Click *Save*.
 
6.  In the next section, set the Membership Type to *Private*.
 
    Recall that private Sites don't appear in the My Sites 
    application so that regular users won't even know that the Engineering Site 
    exists. Also, the only way users can be added to a private Site is via an 
    invitation from a Site administrator.
 
7.  Leave the *Active* selector enabled so that your Site can be used 
    immediately.
 
8.  Check the *Create default pages as private (available only to members). If 
    unchecked, they will be public (available to anyone).* option since the 
    Engineering Site is intended for internal use only.

9.  Leave the *Enable propagation of changes from the Site template* box 
    enabled so that the Engineering Site receives updates if the Department 
    Site template is modified.

10.  Click *Save* to create your Engineering Site.

11.  Repeat these steps to create the Marketing and Legal Sites.

The new Sites have all the pages and apps you created in the Site template. To 
view the pages of the new Sites, click *Sites* &rarr; *Sites* in the Control 
Panel and then click *Actions* &rarr; *Go to Private Pages* next to one of 
your new Sites. 

Using Site templates streamlines the Site creation process for administrators,
making it easy to create Sites quickly. Now each department of the Lunar Resort
has its own Calendar, Documents and Media Library, Wiki, and Message Boards on
their Sites. Although the pages and apps of each department's Site are the same,
each Site will quickly be filled with department-specific information as users
add and share content within the Sites. Also, Site administrators can add new
pages, apps, and content to their Sites, further differentiating each
department's Site from the others.
