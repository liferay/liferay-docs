# Knowledge Base Display [](id=knowledge-base-display)

You can use the Knowledge Base Display widget to display your published 
Knowledge Base articles. You can customize how this widget displays articles, 
and which ones it displays. To get started, add the widget to the Site page
you want to display articles on:

1.  Navigate to the page and open the *Add* menu 
    (![Add](../../../../images/icon-add-app.png)). 

2.  Open the *Widgets* &rarr; *Content Management* section, then add *Knowledge 
    Base Display* to the page. 

By default, the Knowledge Base Display widget displays articles from the
Knowledge Base's Home folder. To change the location of its articles, follow
these steps: 

1.  Click the widget's *Options* icon 
    (![Options](../../../../images/icon-app-options.png)) 
    and select *Configuration*. This opens the Configuration dialog. 

2.  In the *Setup* tab, select the *General* tab then click *Select* in the 
    *Article or Folder* field. This brings up the *Select Entry* form.

3.  Click *Choose* next to the article or folder of articles you want to 
    display. Alternatively, you can click the *Choose This Folder* button at the 
    top of the Select Entry form to select the current folder. 

4.  Click *Save* and close the Configuration dialog. 

![Figure 1: Select the article or folder of articles that the Knowledge Base Display widget displays.](../../../../images/kb-display-config-article.png)
 
The Knowledge Base Display widget's Options icon 
(![Options](../../../../images/icon-app-options.png)) 
also provides these common configuration options: 

-   Look and Feel Configuration 
-   Export/Import 
-   Permissions 
-   Configuration Templates 

For more information on these, see the section on configuring 
widgets in 
[Web Experience Management](/discover/portal/-/knowledge_base/7-1/web-experience-management). 

The Knowledge Base Display's navigation menu and display options make it the 
perfect candidate for a full page widget. If you display a folder of articles, 
the navigation on the left side of the widget displays links to all the folder's 
articles. The viewing area on the right side of the widget displays the folder's 
leading article (the *priority one* article). Click an article in the navigation 
to display it in the viewing area. The currently displayed article's link 
appears in bold in the navigation. You can also move between articles by 
clicking the links with arrows at the bottom of the widget. 

![Figure 2: Knowledge Base Display's navigation and viewing provide a great reading experience.](../../../../images/kb-display.png)

Knowledge Base Display can also show article hierarchies. Viewing an article 
that has child articles expands the navigation tree to show links to the child 
articles. Any expanded nodes collapse when you view a different top level 
article.

The links at the top of the widget allow users to perform the following actions 
on an article: 

-   Subscribe to an RSS feed of the Knowledge Base
-   Subscribe to the current article
-   View the current article's history
-   Print the current article 

Administrators have access to an additional set of links at the top of the 
widget that lets them perform the following actions:

-   Edit the article
-   Add a child article
-   Set the article's permissions
-   Move the article
-   Delete the article

Below the article's content is the rating interface, showing thumbs up/down 
icons. Users can also submit suggestions or comments below the article in the 
text box labeled *Do you have any suggestions?*. Administrators can 
[view the suggestions and mark progress on them](/discover/portal/-/knowledge_base/7-1/responding-to-knowledge-base-feedback). 

If the administrator enables the Knowledge Base app's source URL feature (more 
on this in a moment) and an article has an assigned source URL, an 
*Edit on GitHub* button 
(![GitHub](../../../../images/icon-edit-on-github.png)) 
appears to the right of the article's title. This button lets users access the 
article's source in GitHub. You can use this feature to encourage users to 
contribute fixes or improvements to articles. If you're interested in this 
feature, you can direct your administrator to follow the instructions in 
[Knowledge Base System Settings](/discover/portal/-/knowledge_base/7-1/knowledge-base-system-settings). 

## Displaying Different Article Sets [](id=displaying-different-article-sets)

As an administrator, say that you've used folders to aggregate similar articles,
and you want to provide an easy way for users to switch between these sets of
articles. The Knowledge Base Display's content folder feature adds a selector to 
the top of the navigation that lets users switch between article sets.
 
Follow these steps to set up content folders: 

1.  Add a folder in the Knowledge Base app in Site Administration. Then create 
    sub-folders in this folder. These sub-folders are the *content folders*. 

2.  Add articles to each content folder.

3.  Select *Configuration* from Knowledge Base Display's *Options* menu 
    (![Options](../../../../images/icon-app-options.png)). 
    In the *Setup* &rarr; *General* tab, select the content folders' parent 
    folder and click *Save*. 

The content selector's values reflect the names of your content folders. Select 
one to view its articles. 

![Figure 3: Knowledge Base Display's content folder feature lets users switch between different sets of articles.](../../../../images/kb-display-content-selector.png)

You can also add a common prefix to the names shown in the selector: 

1.  Select *Configuration* from Knowledge Base Display's *Options* menu 
    (![Options](../../../../images/icon-app-options.png)). 
    In the Configuration dialog, select the *Setup* &rarr; *Display Settings* 
    tab. 
 
2.  Enter the prefix into the *Content Root Prefix* field and click *Save*. 
