# Using the Web Content Editor [](id=using-the-wysiwyg-editor)

You can create simple articles using Liferay's Web Content editor, but when you
scrolled past all the options, you might get the notion that the Web
Content editor is capable of creating more than a plain text article.

## Basic Editor Functions

To explore these options, go back into the article you just created and make it 
better:

1.  On the *Web Content* page in *Site Administration*, click on the title of
    the article.

2.  Add some text that will serve as the heading in your article.

3.  Highlight the text, controls appear. 

    These controls let you style the text or provide a link.
    
4.  Click on the arrows where it says *Normal* to open the *Styles* dropdown 
    and select the *Heading 1* style.

Next, you'll add an image to the article. Whenever you place your cursor in the content area, the *Add* icon (![WYSIWYG Add](../../../../images/icon-wysiwyg-add.png)) appears. If you click on
it, controls for inserting an image, table, or horizontal line
(![Controls](../../../../images/icon-content-insert-controls.png)) appear.

1.  Click *Add* (![WYSIWYG Add](../../../../images/icon-wysiwyg-add.png)).

2.  Select the icon that depicts a mountain silhouette to insert the image.

3.  In the image file selector select an image to add to the article.

     Select an image from your computer or from the Site's 
     Documents and Media repository. If you select an from your 
     Documents and Media  repository, you can access the [image 
     editor](/discover/portal/-/knowledge_base/7-1/editing-images) to make
     changes specifically for your article.
     
     
![Figure 3: You can access the image editor through the item selector window.](../../../../images/image-editor-preview-window.png)

After adding an image to the web content article, click it to bring up controls
([![Image Controls](../../../../images/icon-wysiwyg-image-controls.png)) for
formatting it. You can also make it a link and define the `alt` HTML attribute.

You can also insert a table into your article. Click the table to access edit controls. They let you designate the first row and/or column as table headers, and also enable you to add rows, columns, and cells. You can also insert a horizontal line as a separator between between sections.

## Editing the Article Source

If you need to work directly with the HTML, you can switch to source view. 

1.  Select the *Source* icon (![WYSIWYG Source](../../../../images/icon-wysiwyg-source.png)) to switch. 

2.  Click the regular mode icon (![Regular Mode](../../../../images/icon-paint-roller.png)) to go back once you're done
    editing HTML.
    
The HTML editor is syntax highlighting, and you can switch between a dark and 
light theme by choosing the moon and sun icons.

In HTML mode, click on the *Enlarge* icon
(![Enlarge](../../../../images/icon-enlarge.png)) to access a dual pane view that 
shows your HTML code on the left and a preview pane on the right. You can 
arrange the HTML and preview panes horizonally or vertically.

![Figure 4: You can view how your HTML would render by using the preview pane.](../../../../images/web-content-editor-html.png)

Every 25 seconds, the entry you're creating is automatically saved as a draft.
Because of this, you never have to worry about losing your work if something
unforeseen happens in your browser or on your computer. You can exit the
enlarged editor by clicking the *Done* button at the bottom of the screen.

Add a few short sentences announcing the grand opening of the Lunar Resort. The
content can be localized in whatever language you want. You'll learn more about
localizing your content later on.

## Web Content Options

The bottom menu of the New Web Content form provides options for customizing
your web content.

![Figure 7: New web content can be customized in various ways using the menu located below the editor.](../../../../images/wcm-menu.png)

**Structure and Template:** lets you customize the web content article's
structure and template. To learn more about web content structures and
templates, visit the [Designing Uniform Content](/discover/portal/-/knowledge_base/7-1/designing-uniform-content)
section.

**Small Image:** sets the image that is used for the web content article's
previews.

**Metadata:** helps you organize your web content articles by selecting tags, categories, and priority. To learn more about tags and categories, visit the
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories)
section.

**Friendly URL** sets the friendly URL were the article can be viewed by itself.
If a Display Page is set, it will display with the display page at this 
location.

**Schedule:** customizes the date and time your content publishes and/or
expires. To learn more about scheduling content, visit the
[Scheduling Web Content Publication](/discover/portal/-/knowledge_base/7-1/scheduling-web-content-publication)
section.

**Display Page:** lets you select a Display Page to enhance the styling and formatting of your web content.

For example, if you had a news site with different sections like Sport and 
Technology, you could create a Display Page with specific banners, formatting, 
and embedded widgets that were different for articles published for each 
section. In this section, you would define the appropriate display page so that 
the articles appeared with the correct formatting. You'll work through an 
example of creating a display page in the
[Display Pages for Web Content](/discover/portal/-/knowledge_base/7-1/display-pages-for-web-content).

**Related Assets:** lets you determine content relationships between the web
content article and other assets in your Liferay instance, even if they don't
share any tags and aren't in the same category. You can connect your content to
any other Liferay asset or custom assets that implement this feature. To learn 
more about defining content relationships and publishing links to those related 
assets, visit the [Defining Content Relationships](/discover/portal/-/knowledge_base/7-1/defining-content-relationships)
section.

![Figure 8: This blog entry has links to three Related Assets: an article and a 
message board thread.](../../../../images/related-assets-link.png)

**Permissions:** customizes who has access to the content. By default, content
is viewable by Anyone (Guest Role). You can limit viewable permissions by
selecting any Role from the drop-down or in the list. You can customize 
permissions in more detail by selecting the *More Options* link below the drop 
down button. 

While you can set permissions here, they are ignored unless you activate Web
Content Article permissions in your System Configuration: 

1. Go to the *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

2. Search or browse for *Web Content (Default Settings for All Instances)*.

3. Check the box labeled *Article view permissions check enabled*.

4. Click *Save*.

Once it is activated, any permissions you set in the article's configuration are
checked before displaying the article.

