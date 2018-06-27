# Displaying Web Content [](id=displaying-web-content)

In the previous sections you created and edited an article. Now it's time to 
display it. 

## Publishing Content

1.  Go to the *Welcome* page of the Lunar Resort Site.

2.  Select the *Add* button
    (![Add](../../../../images/icon-control-menu-add.png)) from the top Control 
    Menu and select the *Widget* tab.

3.  Find the *Web Content Display* and add it to the page.

![Figure 12: Add the Web Content Display app to a page to begin displaying your new web content article.](../../../../images/add-web-content-display.png)

You can drag a widget to the position on the page where you want your content 
to appear. You can have as many Web Content Display widgets on a page as you 
need, which gives you the power to lay out your content exactly the way you 
want it. 

Now, select the content to display:

1.  Click *Select Web Content to make it visible* in the bottom of the widget.

2.  Click *Select* under *Web Content*.

3.  Click on the article that you want to display

    If your content does not immediately appear in the list, you can search for 
    the content by title, description, user name, or site (click the dropdown 
    arrow to see all the options).

Once you select the web content article, you can to choose the User Tools and 
Content Metadata to be published in the Web Content Display app. These
two entities have the following options to choose from, by default:

- **User Tools**
    - *Translations*
    - *Print*
- **Content Metadata**
    - *Related Assets*
    - *Ratings*
    - *Comments*
    - *Comment Ratings*

**Translations** shows the available locales for your content. If you're working 
on the page for a particular language, you can select the translation of your 
content that goes with your locale.

![Figure 13: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../../../images/web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your Liferay instance, you
can also enable document conversion for your content. This gives your users the
ability to download your content in their format of choice. Conversion options 
are available under the *User Tools* list.

**Print** pops the content up in a separate browser window with
just the content, and no navigation or other widgets.

**Ratings** shows one of two ratings interfaces @product@ has: five stars or 
thumbs up and thumbs down. Rating types are configured in *Configuration* 
&rarr; *Site Settings*.

**Comments** creates a discussion forum attached to your content.

By default, guests are not allowed to leave comments on web content. 
If you'd like to allow guests to comment on your web content article:

1.  Navigate to the Control Panel &rarr; *Users* &rarr; *Roles*

2.  Select *Guest* &rarr; *Define Permissions*.

3.  From the left menu, select *Site Administration* &rarr; *Content* &rarr; 
    *Web Content*.

4.  The navigate down to the Web Content Article heading and select the *Add 
    Discussion* checkbox.

Guests are now able to post comments on your web content article!

You may decide you want one, some, or none of these features, which is why
they're all implemented as simple selector buttons to be enabled or disabled at
need. Once you've selected the features you want to include in your Web Content
Display spp, click the *Save* and close the configuration window.

## Editing Published Content

If you need to edit published content, you can do it directly from the Web 
Content Display app or from Site Administration. To edit it from
the Web Content Display app:

1.  Select the *Options* button 
    (![Options](../../../../images/icon-app-options.png)) from the app's top 
    panel.

2.  Select the appropriate edit action.
    
    For example, selecting *Edit Web Content* launches the editor. 
    *Edit Template* launches the template editor for the web content article's 
    template.

![Figure 14: You can select and edit an article, or edit its template directly from the Web Content Display app.](../../../../images/web-content-display-icons.png)

If you edit the article from Site Administration, you can also view the article's evolution.

1.  Go to *Content* &rarr; *Web Content* from the Menu.

2.  Next to the article, click *Actions* icon 
    (![Actions](../../../../images/icon-actions.png)) and select *View
    History*.
    
    From this menu, you can view all the article's versions and 
    modified/display dates. The Diff tool lets you compare versions of the 
    article and highlight the differences between the two.

1.  Click *Actions* next to a version of the article you'd like to compare. 

2.  Select *Compare to...*.

3.  Select the version with which to compare it.

    The tool provides color coded highlighting to emphasize additions and deletions between the two articles.

![Figure 15: Comparing web content articles is a great feature to use during the Workflow process.](../../../../images/web-content-diff-feature.png)

When you publish updates to a web content article that's already being displayed
the content is immediately updated unless you have a workflow enabled, which is discussed in greater detail in
[Using Workflow](/discover/portal/-/knowledge_base/7-1/using-workflow).
It makes no difference whether you edit it from a Web Content Display app, from
the Asset Publisher, or from the Site Administration interface.
