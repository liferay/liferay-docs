# Using Liferay Web Content Management

Web Content Management is a system which allows non-technical users to publish content to the web without having advanced knowledge of web technology or programming of any sort. Liferay WCM empowers you to publish your content with a simple point and click interface, and it helps you to keep your site fresh. You'll find yourself easily creating, editing, and publishing content within just a few minutes of being exposed to its features. But Liferay WCM doesn't sacrifice power for simplicity. If need be, you can use your developer skills to create complex presentation layer templates that make your content "pop" with dynamic elements. Once these templates have been deployed into the portal, your non-technical users can manage content using these templates as easily as they would manage static content. All of this makes Liferay WCM an appropriate choice for sites with only a few pages or sites with gigabytes of content. 

In this chapter, we'll cover the following topics: 

- Features of Liferay WCM
- Simple content creation
- Publishing content
- Structures and Templates
- Permissions
- Tags and Categories
- Staging and Workflow

As you'll see, Liferay's WCM is a full-featured solution for managing your web site. We'll start with an overview of what it has to offer, and then we'll dive down into its features. 

## How Can Liferay's WCM Help You?

With Liferay's WCM you have the ability to create, edit, stage, publish, and approve content with easy to learn yet powerful tools. Liferay's WCM streamlines site changes for the end user versus doing a site in HTML. Some ways Liferay WCM makes this possible include:

-   Once set up, non-technical users can manage the site.
-   Liferay's fine-grained permissions system ensures your content gets to the right users.
-   To manage the site, no programming is required.
-   Content can be staged.
-   Content can be passed through a workflow.
-   Content can be published on a schedule.
-   WCM is integrated with Liferay's services, so advanced template developers can use them to query for data stored elsewhere in Liferay.

Once you get familiar with Liferay WCM, you'll wonder how you ever got along without it.  

### What Features Does Liferay WCM Have?

Liferay's WCM has a host of features the makes managing the content of your site easier.

-   **WYSIWYG Editor:** A complete HTML editor that allow you to modify fonts, add color, insert images and much more.

-   **Structure Editor:** Easily add and remove fields you want available to content creators and then dynamically move them around. This editor includes an entire suite of form controls you can drag and drop onto your structure.

-   **Template Editor:** Import template script files that inform the system how to display the content within the fields determined by the structure.

-   **Web Content Display:** A portlet that allows you place web content on a page in your portal.

-   **Asset Publisher:** A portlet which can aggregate different types of content together in one view.

-   **Scheduler:** Lets you schedule when content is reviewed, displayed, and removed.

-   **Workflow Integration:** Run your content through an approval or review process.

-   **Staging:** Use a separate staging server or stage your content locally so you can keep your changes separate from the live site. 

Liferay's Web Content Management is a powerful and robust tool for creating and organizing content on your web site. Now that you've seen the basics of what you can do with Liferay's WCM, let's apply some of these concepts and create some content.

## Building a Site with Liferay's WCM

You've just been assigned the task to build a web site for a an innovative new social networking site called Nose-ster. You've decided to take advantage of Liferay Portal and its rapid deployment features as well as its ability to get a fully functional, content-rich web site with integrated social features up and running in little time.

We'll walk through the creation of Nose-ster's web site, starting by creating and publishing some simple content using Liferay's built-in WYSIWYG editor. We'll then take advantage of Liferay's robust structure editor. We'll use templates to display the content and then explore some of the advanced publishing features such as the built-in workflow and Asset Publisher.

### Creating content the simple way

As we've stated above, content is the reason web sites exist. Liferay Portal has made it easier than ever to get content published to your site. Because Liferay Portal is so flexible, you can use basic authoring tools right away or take advantage of the more advanced features. It's adaptable to your needs.

We'll begin by creating simple content using Liferay's WYSIWYG Editor and then we'll publish it to the home page of Nose-ster's web site. This a fast and straightforward process that demonstrates how easy it is to create and publish content onto your Liferay Portal. So let's get familiar with the Web Content section of the control panel so we can create and publish our first pieces of content.

![Figure 4.8: Choosing a Site in the Content Section](../../images/04-web-content-context-dropdown.png)

When you manage web content from the control panel, you can select the location where the content resides. For instance, you can add content that's available to a specific site or globally across the portal. The Content section of the control panel displays as its heading the name of the site you're currently working on. This heading is clickable: you can change where you're working by using the drop down attached to the heading. We will add our first piece of content to the *Nose-ster* site, which we defined earlier in the chapter as the default site.

##### Rich, WYSIWYG Editing

Once you have the Nose-ster site selected, click on the *Web Content* link in the control panel. Next, click the *Add* button under the *Web Content* tab. This is a highly customizable form that by default has two fields: a title and a powerful WYSIWYG editor. We could customize this form to contain whatever fields our content needs, but let's keep things simple for now. We'll cover more advanced features such as structures, templates, and content scheduling later in this chapter.

For now, type the words *Welcome to Nose-ster* in the *Name* field. Notice that content can be localized in whatever language you want. If you click the *localize* check box, two select boxes appear which allow you to pick the language you're working in and the language that is the default. You can enter translations of your content for any language in the list. The screenshot below shows this interface but for now, we won't be using it, so you can leave it unchecked. In the content field, add a short sentence announcing that the web site is up and running.

![Figure 4.9: The Web Content Editor](../../images/04-web-content-wysiwyg.png)

Getting a new web site up and running is an exciting step for anyone, whether it is a large corporation or a small non-profit charity. To celebrate this momentous achievement at Nose-ster, let's give our announcement some of the pomp and circumstance we think it deserves!

Using the editor, select all the text and then change the style to Heading 1 and the color to dark blue.

You could insert an image here as well as more text with a different style, as demonstrated in the screenshot below. You can also add bullets, numbering, links to another site, or custom images. You can even add an emoticon. Let's add a smiley face at the end of our announcement.

![Figure 4.10: Customizing Content](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text, images, tables, links, and more. Additionally, you can modify the display to match the purpose of the content. Plus it's integrated with the rest of Liferay Portal: for example, when you upload an image to be added to a page, that image can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can click the *Source* button and write your own HTML if you wish.

##### Assigning Permissions

Permissions in Liferay WCM work the same way they do everywhere else in Liferay. By default, content is viewable by Anyone (Guest Role). You can limit viewable permissions by selecting any Role from the drop-down or in the list. Additionally, Liferay Portal provides the ability to customize permissions in more detail. Select the *More Options* link next to the drop down button, and you'll find the different activities you can grant or deny to your web content.

![Figure 4.11: Permissions for Web Content](../../images/04-web-content-content-permissions.png)

For this piece of web content, we don't need to change anything. After you're finished with permissions, click *Save*. This saves the content in draft form. Once you're satisfied with the your changes, select *Publish*. This makes the content available for display, but we still have some work to do to enable users to see it. In Liferay WCM, all content resides in a container, which is one of two portlets: Web Content Display or Web Content List. By far the most frequently used is the *Web Content Display* portlet. So let's go back to the page where we want the content displayed and add the Web Content Display portlet (in this case, the Welcome page).

#### Publishing Content with the Web Content Display Portlet

Now that we've created and published our first piece of web content for Nose-ster, it's time to display it. First, add the *Web Content Display* portlet to our Welcome page, by selecting *Add &rarr; Web Content Display* from the Dockbar.

![Figure 4.11: Adding the Web Content Display Portlet](../../images/portal-admin-ch4_html_m5a656d59.jpg)

Once the portlet appears, drag it to the position on the page where you want your content to appear. You can have as many Web Content Display portlets on a page as you need, which gives you the power to lay out your content exactly the way you want it. 

To add existing web content, select the *gear* icon on the lower left of the portlet. You will see the message *Please select a web content from the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If there is lots of published content available, you could search for the content by name, ID, type, version, content, and site (click the *Advanced* link to see all the options). You can also show the available locales for your content. If you're working on the page for a particular language, you can select the translation of your content that goes with your locale.

![Figure 4.12: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also enable document conversion for your content. This gives your users the ability to download your content in their format of choice. This is especially handy if you are running a research or academically oriented site; users can very quickly download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling ratings so that users can rate the content, enabling comments, and enabling ratings on comments.

The Print button pops the content up in a separate browser window that contains just the content, without any of the web site navigation. This is handy for printing the content. Enabling ratings shows one of two ratings interfaces Liferay has: five stars or thumbs up and thumbs down. This can be set globally in the `portal-ext.properties` file. See chapter 12 for further information about this.

Enabling comments creates a discussion forum attached to your content which users can use to discuss your content. Enabling ratings on comments gives your users the ability to rate the comments. You may decide you want one, some, or none of these features, which is why they're all implemented as simple check boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see that you can choose the type of client to which you want to expose content. This lets you target the large screens of users' computers for expansive graphics and lots of special effects, or target the small screens of mobile devices with pertinent information and a lightweight page. For now, leave both checked and click the *Save* button. You can now close the configuration window.

To publish new content, select the *page and green plus icon* on the lower left of the portlet. This launches the same full-featured editor you've already seen in the control panel, which lets you add and edit content in place as you are working on your page.

This is another example of the flexibility that Liferay Portal offers. At times, you may want to add content directly into the Web Content Display portlet of the page you're managing, especially if you are in the process of building the page. At other times, you may want to use the control panel to create content, because at that moment you're more concerned with the creation of the content and not where the content will later be displayed. Liferay WCM supports both processes. 

Editing content that's already been published is just as easy as creating new content is. You'll use the same exact tools. 

##### Editing Content

Once the content is displayed--whether you've selected content or created it in the Web Content Display portlet--you can edit the content directly from the Web Content Display portlet or from the control panel. To edit it from the Web Content Display portlet, select the *pencil* icon to the lower left of the portlet. This launches the WYSIWYG editor and from there you can make any necessary changes.

![Figure 4.12: Edit, Select, and Add Icons of Web Content Display Portlet](../../images/web-content-display-icons.png)

When you publish your content this way, it becomes available immediately (unless, of course, you have a workflow enabled, which we'll see below). This happens whether you edit it in place or in the control panel.

Note: if you want to view your page the way your users will see it (i.e., without all those portlet controls and icons), go up to the Dockbar and select *Toggle Edit Controls*. This makes all those extra controls you see as a portal administrator disappear. If you need to use those controls again, just select *Toggle Edit Controls* again. 

That's pretty much all there is to simple content creation. Whole sites have been created this way. But if you want to take advantage of the full power of Liferay's WCM, you'll want to use structures and templates. Next, we'll see how they work and how you can use them to make your site more dynamic. 

### Advanced Content Creation

If you've ever launched a web site, you know that as it grows, you can experience growing pains. This is the case especially if you've given lots of people access to the site to make whatever changes they need to make. Without preset limitations, users can display content in any order and in any manner they desire (think huge, flashing letters in a font nobody can read). Content can get stale, especially if those responsible for it don't maintain it like they should. And sometimes, content is published that should never have seen the light of day. 

Thankfully, Liferay WCM helps you handle all of those situations. You can use *Structures* to define which fields are available to users when they create content. These are coupled with *Templates* that define how to display that content. Content won't get stale, because you can take advantage of the *Scheduling* feature to determine when content is displayed and when it's removed. Additionally, you can configure Liferay's built-in *Workflow* system to set up a review and publishing process so that only what you want winds up on the live site. Liferay Portal gives you the management tools you need to run everything from a simple, one-page web site to an enormous, content-rich site.

All of this starts with structures. 

#### Using structures

Structures are the foundation for web content. They determine which fields are available to users as they create a new item for display. Structures not only improve manageability for you the administrator, but also make it much easier for the users to quickly add content.

For example, say you're managing an online news magazine. All of your articles need to contain the same types of information: a title, a subtitle, an author, and one or more pages of text and images that comprise the body of the article. If Liferay only supported simple content as has been described above, you would have no way to make sure that your users entered a title, subtitle, and author. You might also get articles that don't match the look and feel of your site. If titles are supposed to be navy blue and they come in from your writers as light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so that your users know what needs to be entered to have a complete article. Using structures, you can provide a form for your users which spells out exactly what is required and can be automatically formatted using a template.

To create a structure, you simply add form controls such as text fields, text boxes, text areas (HTML), check boxes, select boxes, and multi-selection lists, as well as specialized, Liferay-specific *Application Fields* such as Media Gallery and Documents and Media Library right onto the structure. Furthermore, you can move the elements around by dragging them where you want them. This allows you to quickly brainstorm different orders for your input fields. Additionally, elements can be grouped together into blocks which can then be repeatable. Template writers can then write a template which loops through these blocks and presents your content in innovative ways, such as in sliding navigation bars, content which scrolls with the user, and more.

Let's take a look at how we edit a structure.

##### Editing a Structure

Go back to the control panel and select the *Web Content*. Click *Add* from the Web Content page to add another piece of content to your portal. Instead of going right for the content this time, however, we're going to create a structure. To edit a structure, simply click on the *Edit* icon next to the *Structure* heading near the top of the page.

It is very easy to edit structures: all you have to do is drag elements into the structure and then give them names. For instance, select the *Checkbox* element under the *Form Controls* tab and drag it onto the structure. You can do the same with any of the elements. To remove it from the structure, simply select the *Delete* icon (black circle with X) in the upper right corner of the element. Take a moment to add, delete, and rearrange different elements.


Liferay supports the following elements ![Figure 4.13: Structure Elements](../../images/04-web-content-structure-editor.png)
in structures:

**FORM FIELDS**

**Text Field:** Used for items such a titles and headings.
**Text Box:** Used for the body of your content or long descriptions.
**Text Area (HTML):** An area that uses a WYSIWYG editor to enhance the content.
**Checkbox:** Allows you to add a checkbox onto your structure. Template developers can use this as a display rule.
**Selectbox:** Allows you to add a select box onto your structure.
**Multi-selection Lists:** Allows you to add a multi-selection list onto your structure.

**APPLICATION FIELDS**

**Image Gallery:** Allows you to add the Image Gallery application into your structure.
**Upload Image:** Allows you to add the upload image application into your structure.
**Document Library:** Allows you to add the Document Library application to your structure.

**MISCELLANEOUS**

**Link to Page:** Inserts a link to another page in the same site.
**Selection Break:** Inserts a break in the content.

##### Editing Elements

When creating a new structure it is essential that you set the variable names for the elements for the template writers. Otherwise, the generated variable names will be very difficult for a template writer to follow. In your structure, you can add the element *Text Area* (HTML) which has the Field Label *Instructions*. However, we want to give it the variable name `Steps`. This can be done very easily: at the bottom of every form element is a **Variable Name** field. Replace the generated name with the name you want to use. There are many other options for fields, including setting tooltips for users. To set these options, select the *Edit Options* button in the lower right corner of the element.

You can type instructions in the **Instructions for the User** field and even display them as a tooltip. For the Nose-ster structure, type in something that will help users know what to put into the Body element (example: this is an HTML Text area for the body of your content). Also, enable the *Display as Tooltip* box. Now, when users hover over the Help icon near your title, they will see the instructions you entered.

##### Assigning Permissions

Permissions to the structure are straightforward. Generally, you don't want most users editing structures as this often requires a developer to modify the template assigned to the structure. However, you do want to make your structure viewable to everyone that will be using it to add web content.

![Figure 4.14: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

You have the ability to grant or deny permissions based on Roles. For instance, you can give the Guest role the ability to *Add Discussion* or you may want to give a *Site Member* the ability to *Delete*. Liferay Portal makes it easy to configure the permissions based on your specific needs for the site.

#### Templates

Developers create templates to display the elements of the structure in the markup that they want. Content can then be styled properly using CSS, because markup is generated consistently when users enter content. In essence, templates are scripts that tell Liferay how to display the content within the fields determined by the structure. Any changes to the structure require corresponding changes to the template. Otherwise, new or deleted fields will produce errors on the page. Without a template, the portal has no idea how to display content which has been created using a custom structure.

##### Template Types (VM, XSL, FTL, and CSS)

Liferay supports templates written in four different templating languages. This is so that you can get started right away. If you have experience with one over another, you can use whichever one you've already used before. If you are just starting with any one of them, we recommend Velocity, as it is less “chatty” than XSL and extremely simple to understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix logic with HTML. This is similar to other scripting languages, such as PHP, that you may have seen before, though Velocity is much simpler. It's been in the product the longest, so it is probably the most widely used language for templates in Liferay WCM. If you haven't used any of the template languages before, we recommend using Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to transform the underlying XML of a structure into markup suitable for the browser. While it may not be as clean and compact as Velocity or FTL, it is widely used for transforming XML into other formats.

**FTL** (FreeMarker Template Language): Freemarker is a templating language which could be considered a successor to Velocity, though it is not yet as popular. It has some advantages over Velocity for which it sacrifices some simplicity, yet it is still easy to use and less “chatty” than XSL.

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very straightforward and modifications are simple (colors, fonts, layouts, etc). If your structure is more complex, however, you'll need to use one of the other options.

##### Adding a Template

Liferay WCM makes it easy to create structures, templates, and content from the same interface. Let's go through the entire flow of how we'd create a structure, link it to a template, and then create content based on what we've defined. We'll use Velocity for our template, and we'll lay out the structure fields systematically to go along with the format we've defined for our content.

![Figure 4.14: Adding Template Interface](../../images/04-web-content-templates-create.png)

1.  Go back to the Web Content section of the control panel and click *Add* under *Web Content*.
2.  Click the *Edit* icon for Structures.
3.  Remove the Content field and add the following fields:

**Field Type**, **Variable Name**

* Text, *title*

* Text Box, *abstract*

* Image, *image*

* Text Area, *body*

4.  Select *Save* and give the structure a name.
5.  Go back to the main web content page and select the *Templates* tab.
6.  Select *Add Template.*
7.  Type in a name and description.
8.  De-select the box labeled *Cacheable.*
9.  Select VM as the language.
10. Click *Select* and choose a Structure that goes with the Templates.
11. If you've written the script beforehand, you can select *Browse* to upload it from your machine. Otherwise, you can click *Launch Editor* to type the script directly into the small editor window that appears.
12. Select *Save.*
13. Return to the Web Content tab and open the Company News content. You'll see the new element labeled Abstract just below the Title.

Below is the template script for this structure. It is written in
Velocity:

    #set ($renderUrlMax = $request.get("render-url-maximized"))
    #set ($namespace = $request.get("portlet-namespace"))
    #set($readmore = $request.get("parameters").get("read_more"))
	<h1>$title.getData()</h1>
    #if ($readmore)
	<p>$abstract.getData()</p>
	<p>$body.getData()</p>
    #else
	<p>
	<img src="${image.getData()}" border="0" align="right">
	$abstract.getData()</p>
	<a href="${renderUrlMax}&${namespace}read_more=true">Read More</a>
    #end

This template is pretty small, but it actually does quite a bit. First, a portlet URL which maximizes the portlet is created. Once this is done, we get the namespace of the portlet. This is important because we don't want our URL to collide with another URL that might be on the page.

After this, we attempt to get a request parameter called `read_more`. Whether or not we were successful in getting this parameter is the key to the rest of the script:

-   If we were successful in getting the `read_more` parameter, we display the abstract and the body below the title (which is always displayed).

-   If we were not successful in getting the `read_more` parameter, we display the image, the abstract, and the link we created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 4.x: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 4.x: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

Of course, there is much, much more you can do with structures and templates. Check out the Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)) for further information and examples.

#### Assigning Template Permission

Permissions for templates are very similar to permissions for structures. Generally, you only want specific developers accessing the template. However, you may want to make the templates viewable to some content creators who understand the template scripting language, but are not directly writing the scripts. You can determine who views the template by selecting from the *Viewable By* select box beneath the *Permissions* tab. By default the *Anyone (Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can do this by selecting the *More* link.

From the *More* link, you have the ability to grant or deny permissions based on Roles. For instance, you may create a role with the ability to update the template and create a second role that can both update and delete. Liferay Portal makes it possible to assign permissions based on the roles and responsibilities within your organization.

### Advanced Publishing Options

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've discussed Liferay management tools that help you create content quickly and in an orderly fashion. We created a simple announcement and then covered Liferay's new structure editor that allows you to quickly design a structure and prepare it for the template designers. Then, we went through applying a template to the structure. We demonstrated how to display our content using the Web Content Display portlet. Now, we're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become out of date, and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and now inaccurate) web content.

Additionally, you may want the ability to create content and send it through a review and approve process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* feature in the Web Content portlet.

#### Scheduling Web Content

You can publish your content on a schedule. You can determine when the content will be displayed, expired, and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form that your users make use of to add web content, in the same column as the structure and template selectors.

![Figure 4.16: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Allows you to determine (within a minute) when content will be displayed.
**Expiration Date:** Allows you to set the date when the content will expire. The default is one year.
**Never Auto Expire:** Allows you set your content to never expire.
**Review Date:** Allows you to set a date when you want the content reviewed.
**Never Review:** Allows you to determine that your content will not be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content.

#### Tags and Categories

Tags are keywords that can be attached to web content in order to help users find content. Categories are a hierarchical organization of content whose structure can be defined by administrators. With tags and categories, you can make it easier for your users to find your content through search or navigation.

##### Why Tag?

Tags are keywords attached to a piece of web content in the portal. By assigning a tag to web content, you define metadata about that content. This can be used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by the creator of the content, and it is important to tag your content whenever it is created. If you don't tag your content, all the search engine has to go on is the full text of the content when a user does a search, and that might not produce the most optimal results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

##### Who Tags?

Tags in web content are added by the creator of the content. They can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

##### What is the Difference Between a Tag and a Category?

Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they are never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the creator of the book. Categories do the same thing. By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing your content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 4.17: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used administratively to manage your tags and categories. We'll take a look at this interface in the next chapter.

### Using the Asset Publisher Portlet

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your content. Let's take a look at some of its features.

#### Querying for Content

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to “bubble up” the most relevant content to your users.

To get to all of the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

##### Selecting Assets

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet, or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first take a look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

##### Selecting Assets Manually

By selecting *Manual* from the select box beneath *Asset Selection*, tell the Asset Publisher that you want to select your content manually. You can select what you want to be published within the portlet, or you can create new content right from within the Asset Publisher.

![Figure 4.18: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here will be added to the list below of assets that will be displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher gives you the ability to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

##### Selecting Assets Dynamically

The default behavior for the Asset Publisher is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you will display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 4.19: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)


**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping

You can display the content returned by the filters above in order by title, create date, modified date, view count, and more in ascending or descending order. For instance, you may have a series of “How To” articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type*, or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. Again, we'll see more about categories in chapter 4.

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type, and file conversions. Additionally, you can enable printing, flags, ratings, comments, and comment ratings, and these work the same way they do in the Web Content Display portlet.

##### Display Style

**Abstracts:** Shows the first 200-500 characters of the content, defined by the **Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

##### Other Settings

**Asset Link Behavior:** When the link to the asset is clicked, it can be displayed in the Asset Publisher or in the portlet to which the asset belongs, such as the Blogs or Message Boards.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next navigation; regular includes a way of selecting the page to which you'd like to navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it from the list.

**Show Available Locales:** Since content can be localized, you can have different versions of it based on locale. This will show the locales available, enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org integration, you can allow your users to convert the content to one of several formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see below).

![Figure 4.20: Show Metadata](../../images/portal-admin-ch4_html_m409b2939.jpg)

**Enable RSS Subscription:** This gives users the ability to subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, its easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

#### Staging and Workflow

Staging is a major feature of Liferay WCM. The concept of staging is a simple one: you don't want your users seeing your web site change before their eyes as you are modifying it, do you? Liferay's staging environment allows you to make changes to your site in a specialized *staging area*, and then when you are finished, publish the whole site to your users.

You can use staging in multiple ways. Larger organizations may consider having a staging server—a separate instance of Liferay Portal which is used just for staging. Content creators can then use this server to make their changes while the live server handles the incoming user traffic. When changes to the site are ready to be published, they can be pushed remotely to the live server.

Alternatively, you may want to host both your staging environment and your live environment on the same server—particularly if you are part of a smaller organization with less resources to purchase servers. Either way, once set up, the interface is the same; the only difference comes when it's actually time to publish your content.

In addition, Liferay 6.1 adds the capability to create multiple branches of staged content, so that you can manage several future versions of a page simultaneously. Branches can be merged and published through an intuitive UI.

##### Enabling the Staging Environment

The Staging tab allows us to make changes in a staging environment and preview our work before publishing it to the live site. Let's create a staging environment for Nose-ster's homepage.

First, let's add a new page. Click *Add &rarr; Page* from the somewhere in the default community and name the new page *News and Events.* Next, click the *View Pages* button and add the Alerts and Announcements portlets to it.

Now we're ready to activate the staging feature for this site. Go to the control panel then to *Site Settings* and select *Staging* from under the *Advanced* heading.

![Figure 4.21: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

We'll assume we don't have a separate staging server, and so we'll select a staging type of *Local Live*. If you were to select *Remote Live*, you would also have needed to supply the name or IP of the remote server where staged content should be published, the port (80 if Liferay is sitting behind a web server, or the port your application server is listening on if not), and the remote site or organization ID. You can find this ID by selecting *Actions &rarr; Edit* on any site in the control panel. Either way, once we make a selection (*Local Live* or *Remote Live*), more options will become available to us.

We'll cover many of the collaboration portlets listed here when we come to chapter 4. For now you just need to be aware that the option is available to enable or disable staging for any of them, and you need to decide if you would like to stage content for these portlets. In the case of the collaborative portlets, the answer would usually be “no.” Why? Because portlets such as the Message Boards are designed for user interaction. If their content were staged, you'd have to manually publish your site whenever somebody posted a message on the message boards in order to make that message appear on the live site.

Generally, you'll want web content to be staged, because end users aren't creating that kind of content—web content is the stuff you publish to your site. But portlets like the message boards or the wiki would likely benefit from *not* being staged.

Enabling *Page Versioning* gives you the ability to work in parallel on multiple versions of the same page, as well as the flexibility to easily revert to a previous version if you encounter any issues. Check *Enabled On Public Pages* so that we take a look at versioning.  

##### Using the Staging Environment

If we navigate back to the News and Events page we'll now notice some new items along the top of the screen which will help us manage staged pages, and you'll also notice that most of your page management options have been removed. Click on *Staging* to view the staged area. You will now have your management options restored, and you'll have some new options related to staging.

![Figure 4.22: You can see the new bar that staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Calendar portlet and then click on *Live* from the Dockbar. Notice that the Calendar portlet isn't there. As you can see, only the page changes are staged; web content itself can go through a whole different workflow process (which we'll get to later). Go back to the staged page, and take a look at the options you have available. From here you can *Undo* changes, view a *History* of changes, *Mark as Ready for Publication*, and *Manage Page Variations*.

**Undo:** allows you to step back through recent changes to a page, which can save you the time of manually adding or removing portlets if you make a mistake.

**History:** will show you the list of revisions of the page, based on the times when it was published.

**Manage Page Variations:** allows you to create multiple versions of a staged page that you can either merge together, or use to work on several major changes at the same time.

After you're done making changes to the staged page, click on *Mark as Ready for Publication*. Nothing will happen immediately, but now the page, with any changes that you've made, can be published to the Live Site. If you click on the dropdown next to the Staging link at the top, you will have the option to *Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** will allow you to immedatiately push any changes to the Live Site.

**Schedule Publication:** to Live will give you the option to set a specific date to publish, or to setup recurring publishing. You could use this, for example, to publish all changes made during the week every Monday morning without any further intervention.

![Figure 4.22: Ready to publish to Live ](../../images/04-web-content-staging-publish.png)

You may have noticed that something appears to be missing from this process, in particular, you might be asking the question, "How do I reject changes?" Starting with Liferay 6.1, Staging is integrated with Liferay's Workflow engine. In order to have a review process for staged pages, you will need to make sure that you have a workflow engine configured, and that you have Staging set up in the workflow. When using a worfklow, clicking *Mark as Ready for Publication* will submit the staged pages into the workflow. Once all necessary approvals have been completed, the *Publish to Live Now* and *Schedule for Publication* options will become available.

#### Site Pages Variations

Let's say that you're working in an environment where you'll have several major changes to a page or a set of pages over a short period of time, and need to be working on multiple variations on the same time to ensure that everything has been properly reviewed before it goes live. With staging in Liferay 6.1 you can do this with **Page Variations**.

To create a new page variation, click on *Manage Page Variations* on the staging menu bar. This will bring you to a list of existing versions of your page. Click *Add Page Variation* to create a new one. From the *Add Page Variation* screen, you can set a Name, Description, and set your new variation to copy the content from an existing variation.

![Figure 4.23: Creating a new variation](../../images/04-web-content-add-site-pages-variation.png)

Site Page Variations are the top level variations, and each one will become it's own **Main Variation**, and each Main Variation can in turn have different variations for each page under it. You can switch between different variations by simply clicking on them from the staging menu bar. It is also possible to set permissions on each variation, so that certain users wouldn't have access to manage some, but not all page variations. Page variations also have different branches, so you could, for example, have three main variations, and then have have three varitions of each of those, resulting in nine different versions of a page. While can be confusing at first, it can all come together when you add **Merging**.

Merging occurs automatically once you click *Merge*, and it will do it's best to preserve all changes made in different branches. For example, if you add two different portlets to a page in two different variations, and then merge them with the main variation, both portlets would be added to the page after the merge. You could then do some tweaking on the final merged product before publishing to live.

![Figure 4.24: Our branching example](../../images/04-web-content-staging-forking.png)

For example, suppose we want to manage a couple of variations of a page for Thanksgiving and Christmas. Initially we could create a Site Page Variation for general changes called *Current Design* and make any maintenance changes there. Whenever we have anything that needs to be published, we would marge those changes with the Main Variation and publish it.

In the mean time, we could duplicate the pages from the Current Design variation in a *Thanksgiving Page* variation, and make additional changes to the page. Once it gets close to Thanksgiving, we could merge Thanksgiving Break with the Current Design, and subsequently merge up to the Main Variation again. Once this is complete, we could make any necessary tweaks after the merge, and Publish the pages. We would then repeat the same steps at Christmas time.

![Figure 4.25: The Thanksgiving branch.](../../images/04-web-content-branch-thanksgiving.png)

#### Wrapping up Staging

You can enable staging on an individual site basis, depending on your needs. This makes it really easy to put strict controls in place for your public web site, but to open things up for individual sites that don't need such strict controls. Liferay's staging environment is extremely easy to use and makes maintaining a content-rich web site a snap.

### Using Liferay's Integrated Workflow with Content Management

Workflow is essentially a predetermined sequence of connected steps. In Liferay WCM, workflow is designed to manage the creation, modification, and publication of web content. You can set up a workflow so that content cannot be published without going through an approval process that you design. In this way, content goes up on your site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow, and it ships with Liferay CE. If you have uninstalled it or are using EE, it needs to be installed and configured separately. This was covered in chapter 2. For now, we'll assume it's installed and look at how you can take advantage of workflow in getting your content through any approval steps between creation and publication.

To enable workflow for Web Content, navigate to the control panel and select *Workflow Configuration*. From there, select a workflow that has been deployed to Liferay.

![Figure 4.26: Enabling Workflow for Content Management](../../images/04-web-content-workflow-config.png)

As you will discover in chapter 6, you can design workflows to suit your organization's approval process. For Nose-ster's implementation we will use the *Single Approver* workflow which ships with the product.

#### Defining Workflows for Web Content

Let's go ahead and set up Liferay's Workflow for the Nose-ster web site.

1.  Go to the control panel and select *Workflow Configuration* from the left panel.

2.  From the select box, choose *Single Approver* for Web Content. Click *Save.* Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now publishing content works a little bit differently. Let's go through the process of publishing details for new class offerings at Nose-ster. Return to the home page and click on the *Add Web Content *icon on the Web Content Display portlet. Call it *Course Offerings* and enter some content. Notice that the Publish button is now gone. In its place is a *Submit for Publication* button. Go ahead and click it.

![Figure 4.27: Pending Workflow](../../images/04-web-content-workflow-config.png)

Next, go to the *Workflow Tasks* in control panel and then select *My Workflow Tasks*. You will see the option to Review Content for Sales Goals. It shows because you are logged in as an Administrator. There is also a Content Approvers role which is defined by this workflow, and anyone in this role can approve content as well.

To approve the content, you must first take ownership of it. Click on the task. You should see the screen below.

Taking ownership of, reviewing, and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to someone else in the Content Approvers role or create / update a due date for the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve* or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created the content to be the one who approves it. Instead, you would have one or more roles designed for users who will be creating content, and you will have specific users assigned to one or more roles for approving content. Our example was of a very straightforward workflow, as it has only a single approver. Kaleo workflow allows you to design workflows that go through as many steps as you need to conform to your business processes. We look at Kaleo workflow in more detail in chapter 6.

## Summary

This chapter has been your guide to Liferay Web Content Management. We've seen how pages are created and managed in Liferay sites and organizations. It is easy to create whole page hierarchies without ever leaving your browser. You can import and export pages using LAR archives, and these can also be used to transfer a site from one Liferay Portal server to another.

Liferay WCM also includes a powerful staging environment, allowing you to stage content locally on the same server or remotely to another server. And when working on pages, you have the ability to use layouts and nested portlets to design every page to look exactly the way you want it to look.

Whether your site is small and static or large and dynamic, Liferay's WCM enables you to easily plan and manage it. With tools such as the WYSIWYG Editor, Structures, and Templates, you can quickly add and edit content. With the Web Content Display and Asset Publisher, you can rapidly select and configure what and how your content is displayed. And by using Liferay's integrated workflow, you can set up custom publishing rules to fit your organization. You will find that managing your site becomes far easier when using Liferay's Web Content Management system.
