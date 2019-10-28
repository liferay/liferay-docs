---
header-id: using-the-wysiwyg-editor
---

#  Using the Web Content Editor

[TOC levels=1-4]

In the previous article, you created a simple web content article, but the Web
Content editor can do much more than make plain text articles.

## Basic Editor Functions

To explore these options, go back to the article you just created and make it 
better:

1.  On the *Web Content* page in *Site Administration*, click on the title of
    the article.

2.  Highlight the text you entered in the *Content* field.

    A number of controls appear. These let you style the text or provide a link.

3.  Click on the arrows where it says *Normal* to open the *Styles* dropdown 
    and select the *Heading 1* style.

Next, you'll add an image to the article. Whenever you place your cursor in the
*Content* area, the *Add* icon (
![WYSIWYG Add](../../../../../images/icon-wysiwyg-add.png)) appears. If you click
on it, controls for inserting an image, video, table, or horizontal line
(![Controls](../../../../../images/icon-content-insert-controls.png)) appear.

1.  Click *Add* (![WYSIWYG Add](../../../../../images/icon-wysiwyg-add.png)).

2.  Select the icon that depicts a mountain silhouette to insert the image.

3.  In the image file selector, select an image to add to the article.

    Select an image from your computer or from the Site's Documents and Media
    repository. If you select one from your Documents and Media repository, you
    can access the 
    [image editor](/docs/7-2/user/-/knowledge_base/u/editing-images) to make
    changes specifically for your article.

![Figure 1: You can access the image editor through the item selector window.](../../../../../images/image-editor-preview-window.png)

After adding an image to the web content article, click it to bring up controls
(![Image Controls](../../../../../images/icon-wysiwyg-image-controls.png)) for
formatting it. You can also make it a link.

The same way you inserted an image in to the article, you can also insert
a table. Click the table to access edit controls, which let you designate
the first row and/or column as table headers, and also enable you to add rows,
columns, and cells.

In addition to images and tables, you can insert a horizontal line as
a separator between between sections. You can also add video by providing a URL.

## Editing the Article Source

If you need to work directly with the HTML, you can switch to source view. 

1.  With your cursor in the *Content* field, select the *Source* icon (
    ![WYSIWYG Source](../../../../../images/icon-wysiwyg-source.png)) to switch. 

2.  Click the regular mode icon (
    ![Regular Mode](../../../../../images/icon-text.png)
    ) to go back once you're done editing HTML.

The HTML editor highlights syntax, and you can switch between a dark and light
theme by choosing the moon and sun icons.

In HTML mode, click on the *Fullscreen* icon
(![Fullscreen](../../../../../images/icon-enlarge.png)) to access a dual pane view
that shows your HTML code on the left and a preview pane on the right. You can
arrange the HTML and preview panes horizontally or vertically.

![Figure 2: You can view how your HTML would render by using the preview pane.](../../../../../images/web-content-editor-html.png)

You can exit the enlarged editor by clicking the *Done* button at the bottom of
the screen.

1.  Add a few short sentences announcing the grand opening of the Lunar Resort. 

2.  Click *Save as Draft*.

    Be sure to save your content frequently, because it is not auto-saved. 

The content can be localized in whatever language you want. You'll learn more 
about localizing your content later.

## Web Content Options

The right-side menu of the New Web Content form provides options for customizing
your web content. It's organized into two tabs: *Properties* (basic 
configuration properties) and *Usages* (Where the web content is used on the 
site). Note that the *Usages* tab is only visible if you're editing existing web 
content that's been added to a page. 

![Figure 3: New web content can be customized in various ways using the menu located to the right of the editor.](../../../../../images/wcm-menu.png)

The available properties are listed below:

**Basic Information:** Provide a summary for the web content article.

**Default Template:** Customize the web content article's template if it has 
one. To learn more about web content templates, see 
[Designing Uniform Content](/docs/7-2/user/-/knowledge_base/u/designing-uniform-content).

**Display Page Template:** Select a display page template to enhance the styling
and formatting of your web content.

For example, if you had a news site with different sections---Sports,
Technology, Culture---you could create a display page for each section with
unique banners, formatting, embedded widgets, or other features. By selecting
a display page, you would ensure that content appears on the page with the
appropriate features. You'll work through an example of creating a display page
in the
[Display Pages for Web Content](/docs/7-2/user/-/knowledge_base/u/display-pages-for-web-content)
tutorial.

If a display page template is configured, you can preview what it will look like 
with the *Preview* (![Preview Template](../../../../../images/icon-preview.png)) 
button located next to the selected display page template.

**Featured Image:** Set the image that is used for the web content article's
previews. You can set this image from a URL or your computer. If you don't want
a feature image, choose *No Image*.

**Metadata:** Organize web content articles by selecting tags, categories, and
priority. To learn more about tags and categories, see 
[Organizing Content with Tags and Categories](/docs/7-2/user/-/knowledge_base/u/organizing-content-with-tags-and-categories).

**Friendly URL:** Set the friendly URL where the article can be viewed alone. If
a specific display page is set, the URL links to it.

**Schedule:** Customize the date and time your content publishes and/or expires.
To learn more about scheduling content, see 
[Scheduling Web Content Publication](/docs/7-2/user/-/knowledge_base/u/scheduling-web-content-publication).

**Search:** Disabling search for your article removes it from end users' search
results. Administrators can still search for it from *Site Administration*
&rarr; *Content & Data* &rarr; *Web Content*, and the article can still be added
to pages.

**Related Assets:** Determine relationships between the web content article and
other assets, even if they don't share any tags and aren't in the same category.
You can connect your content to any asset that implements the Related Assets
feature. To learn more about defining content relationships and publishing links
to those related assets, see 
[Defining Content Relationships](/docs/7-2/user/-/knowledge_base/u/defining-content-relationships).

![Figure 4: This blog entry has links to two Related Assets: an article and a message board thread.](../../../../../images/related-assets-link.png)

**Permissions:** Customize who has access to the content. By default, content
is viewable by Anyone (Guest Role). You can limit viewable permissions by
selecting any Role from the drop-down or in the list. You can customize 
permissions in more detail by selecting the *More Options* link below the drop 
down button. 

If your permissions are ignored, you must activate the Web Content Article 
permissions in your System Configuration. This is enabled by default: 

1. Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

2. Search or browse for *Web Content*.

3. Check the box labeled *Article View Permissions Check Enabled* under the
    *Virtual Instance Scope* &rarr; *Web Content* tab.

4. Click *Save*.

Once it is activated, any permissions you set in the article's configuration are
checked before displaying the article.
