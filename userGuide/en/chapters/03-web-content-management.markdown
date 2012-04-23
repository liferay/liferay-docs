# Using Liferay Web Content Management [](id=using-liferay-web-content-management)

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

## How Can Liferay's WCM Help You? [](id=how-can-liferay-s-wcm-help-you-)

With Liferay's WCM you have the ability to create, edit, stage, publish, and approve content with easy to learn yet powerful tools. Liferay's WCM streamlines site changes for the end user versus doing a site in HTML. Some ways Liferay WCM makes this possible include:

-   Once set up, non-technical users can manage the site.
-   Liferay's fine-grained permissions system ensures your content gets to the right users.
-   To manage the site, no programming is required.
-   Content can be staged.
-   Content can be passed through a workflow.
-   Content can be published on a schedule.
-   WCM is integrated with Liferay's services, so advanced template developers can use them to query for data stored elsewhere in Liferay.

Once you get familiar with Liferay WCM, you'll wonder how you ever got along without it.  

### What Features Does Liferay WCM Have? [](id=lp-6-1-ugen03-what-features-does-liferay-wcm-have-0)

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

## Building a Site with Liferay's WCM [](id=building-a-site-with-liferay-s-w-3)

You've just been assigned the task to build a web site for a an innovative new social networking site called Nose-ster. You've decided to take advantage of Liferay Portal and its rapid deployment features as well as its ability to get a fully functional, content-rich web site with integrated social features up and running in little time.

We'll walk through the creation of Nose-ster's web site, starting by creating and publishing some simple content using Liferay's built-in WYSIWYG editor. We'll then take advantage of Liferay's robust structure editor. We'll use templates to display the content and then explore some of the advanced publishing features such as the built-in workflow and Asset Publisher.

### Creating content the simple way [](id=lp-6-1-ugen03-creating-content-the-simple-way-0)

As we've stated above, content is the reason web sites exist. Liferay Portal has made it easier than ever to get content published to your site. Because Liferay Portal is so flexible, you can use basic authoring tools right away or take advantage of the more advanced features. It's adaptable to your needs.

We'll begin by creating simple content using Liferay's WYSIWYG Editor and then we'll publish it to the home page of Nose-ster's web site. This a fast and straightforward process that demonstrates how easy it is to create and publish content onto your Liferay Portal. So let's get familiar with the Web Content section of the control panel so we can create and publish our first pieces of content.

![Figure 3.1: Choosing a Site in the Content Section](../../images/04-web-content-context-dropdown.png)

When you manage web content from the control panel, you can select the location where the content resides. For instance, you can add content that's available to a specific site or globally across the portal. The Content section of the control panel displays as its heading the name of the site you're currently working on. This heading is clickable: you can change where you're working by using the drop down attached to the heading. We will add our first piece of content to the *Nose-ster* site, which we defined earlier in the chapter as the default site.

##### Rich, WYSIWYG Editing [](id=lp-6-1-ugen03-rich-wysiwyg-editing-0)

Once you have the Nose-ster site selected, click on the *Web Content* link in the control panel. Next, click the *Add* button under the *Web Content* tab. This is a highly customizable form that by default has two fields: a title and a powerful WYSIWYG editor. We could customize this form to contain whatever fields our content needs, but let's keep things simple for now. We'll cover more advanced features such as structures, templates, and content scheduling later in this chapter.

For now, type the words *Welcome to Nose-ster* in the *Name* field. Notice that content can be localized in whatever language you want. If you click the *localize* check box, two select boxes appear which allow you to pick the language you're working in and the language that is the default. You can enter translations of your content for any language in the list. The screenshot below shows this interface but for now, we won't be using it, so you can leave it unchecked. In the content field, add a short sentence announcing that the web site is up and running.

![Figure 3.2: The Web Content Editor](../../images/04-web-content-wysiwyg.png)

Getting a new web site up and running is an exciting step for anyone, whether it is a large corporation or a small non-profit charity. To celebrate this momentous achievement at Nose-ster, let's give our announcement some of the pomp and circumstance we think it deserves!

Using the editor, select all the text and then change the style to Heading 1 and the color to dark blue.

You could insert an image here as well as more text with a different style, as demonstrated in the screenshot below. You can also add bullets, numbering, links to another site, or custom images. You can even add an emoticon. Let's add a smiley face at the end of our announcement.

![Figure 3.3: Customizing Content](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text, images, tables, links, and more. Additionally, you can modify the display to match the purpose of the content. Plus it's integrated with the rest of Liferay Portal: for example, when you upload an image to be added to a page, that image can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can click the *Source* button and write your own HTML if you wish.

##### Assigning Permissions [](id=lp-6-1-ugen03-assigning-permissions-0)

Permissions in Liferay WCM work the same way they do everywhere else in Liferay. By default, content is viewable by Anyone (Guest Role). You can limit viewable permissions by selecting any Role from the drop-down or in the list. Additionally, Liferay Portal provides the ability to customize permissions in more detail. Select the *More Options* link next to the drop down button, and you'll find the different activities you can grant or deny to your web content.

![Figure 3.4: Permissions for Web Content](../../images/04-web-content-content-permissions.png)

For this piece of web content, we don't need to change anything. After you're finished with permissions, click *Save*. This saves the content in draft form. Once you're satisfied with your changes, select *Publish*. This makes the content available for display, but we still have some work to do to enable users to see it. In Liferay WCM, all content resides in a container, which is one of two portlets: Web Content Display or Web Content List. By far the most frequently used is the *Web Content Display* portlet. 

##### Localization [](id=lp-6-1-ugen03-localization-0)

Previous versions of Liferay had the ability to create and manage different translations of your web content, but with Liferay 6.1 we've added several improvements. 

When you create a new piece of Web Content, you have the ability to choose a default language. If you click *Change* you can select your default language from a large number of languages that Liferay supports. Before you can create a translation, you must finish creating the content in your default language and save it. Once you've done that, editing the content provides you with the option to *Add Translation*.

![Figure 3.5: Adding a translation](../../images/04-web-content-content-translation.png)

After you click *Add Translation*, you can select a language, either by scrolling through the list or by entering the language you want to use in the search box. When you select a language, a lightbox opens within your browser window, enabling you to easily compare the original with the new translation. Once you are done with the translation, click *Save*, and the translation is added to the list of *Available Translations*. 

![Figure 3.6: Adding a translation](../../images/04-web-content-content-translation-2.png)

The ability to completely delete a translation in one step has also been added. Instead of simply disabling a translation or having to go through a multistep process to remove it, you can now simply open the translation that you don't want and click *Remove Translation*.

When you create a new web content structure, each field you create has a *Localizable* checkbox displayed next to it. This enables you to control what can and can't be changed in the translation process. For example, if you don't want images or content titles to be changed when the content is translated, you can make sure that those fields aren't listed as localizable. When you follow the steps above to localize content, only fields within the structure that had the *Localizable* box checked appear within the translation window.

Let's go back to the page where we want the content displayed and add the Web Content Display portlet (in this case, the Welcome page).

#### Publishing Content with the Web Content Display Portlet [](id=lp-6-1-ugen03-publishing-content-with-the-web-content-display-portlet-0)

Now that we've created and published our first piece of web content for Nose-ster, it's time to display it. First, add the *Web Content Display* portlet to our Welcome page, by selecting *Add &rarr; Web Content Display* from the Dockbar.

![Figure 3.7: Adding the Web Content Display Portlet](../../images/portal-admin-ch4_html_m5a656d59.jpg)

Once the portlet appears, drag it to the position on the page where you want your content to appear. You can have as many Web Content Display portlets on a page as you need, which gives you the power to lay out your content exactly the way you want it. 

To add existing web content, select the *gear* icon on the lower left of the portlet. You will see the message *Please select a web content from the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If there is lots of published content available, you could search for the content by name, ID, type, version, content, and site (click the *Advanced* link to see all the options). You can also show the available locales for your content. If you're working on the page for a particular language, you can select the translation of your content that goes with your locale.

![Figure 3.8: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also enable document conversion for your content. This gives your users the ability to download your content in their format of choice. This is especially handy if you are running a research or academically oriented site; users can very quickly download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling ratings so that users can rate the content, enabling comments, and enabling ratings on comments.

The Print button pops the content up in a separate browser window that contains just the content, without any of the web site navigation. This is handy for printing the content. Enabling ratings shows one of two ratings interfaces Liferay has: five stars or thumbs up and thumbs down. This can be set globally in the `portal-ext.properties` file. See chapter 12 for further information about this.

Enabling comments creates a discussion forum attached to your content which users can use to discuss your content. Enabling ratings on comments gives your users the ability to rate the comments. You may decide you want one, some, or none of these features, which is why they're all implemented as simple check boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see that you can choose the type of client to which you want to expose content. This lets you target the large screens of users' computers for expansive graphics and lots of special effects, or target the small screens of mobile devices with pertinent information and a lightweight page. For now, leave both checked and click the *Save* button. You can now close the configuration window.

To publish new content, select the *page and green plus icon* on the lower left of the portlet. This launches the same full-featured editor you've already seen in the control panel, which lets you add and edit content in place as you are working on your page.

This is another example of the flexibility that Liferay Portal offers. At times, you may want to add content directly into the Web Content Display portlet of the page you're managing, especially if you are in the process of building the page. At other times, you may want to use the control panel to create content, because at that moment you're more concerned with the creation of the content and not where the content will later be displayed. Liferay WCM supports both processes. 

Editing content that's already been published is just as easy as creating new content is. You'll use the same exact tools. 

##### Editing Content [](id=lp-6-1-ugen03-editing-content-0)

Once the content is displayed--whether you've selected content or created it in the Web Content Display portlet--you can edit the content directly from the Web Content Display portlet or from the control panel. To edit it from the Web Content Display portlet, select the *pencil* icon to the lower left of the portlet. This launches the WYSIWYG editor and from there you can make any necessary changes.

![Figure 3.9: Edit, Select, and Add Icons of Web Content Display Portlet](../../images/web-content-display-icons.png)

When you publish your content this way, it becomes available immediately (unless, of course, you have a workflow enabled, which we'll see below). This happens whether you edit it in place or in the control panel.

Note: if you want to view your page the way your users will see it (i.e., without all those portlet controls and icons), go up to the Dockbar and select *Toggle Edit Controls*. This makes all those extra controls you see as a portal administrator disappear. If you need to use those controls again, just select *Toggle Edit Controls* again. 

That's pretty much all there is to simple content creation. Whole sites have been created this way. But if you want to take advantage of the full power of Liferay's WCM, you'll want to use structures and templates. Next, we'll see how they work and how you can use them to make your site more dynamic. 

### Advanced Content Creation [](id=lp-6-1-ugen03-advanced-content-creation-0)

If you've ever launched a web site, you know that as it grows, you can experience growing pains. This is the case especially if you've given lots of people access to the site to make whatever changes they need to make. Without preset limitations, users can display content in any order and in any manner they desire (think huge, flashing letters in a font nobody can read). Content can get stale, especially if those responsible for it don't maintain it like they should. And sometimes, content is published that should never have seen the light of day. 

Thankfully, Liferay WCM helps you handle all of those situations. You can use *Structures* to define which fields are available to users when they create content. These are coupled with *Templates* that define how to display that content. Content won't get stale, because you can take advantage of the *Scheduling* feature to determine when content is displayed and when it's removed. Additionally, you can configure Liferay's built-in *Workflow* system to set up a review and publishing process so that only what you want winds up on the live site. Liferay Portal gives you the management tools you need to run everything from a simple, one-page web site to an enormous, content-rich site.

All of this starts with structures. 

#### Using structures [](id=lp-6-1-ugen03-using-structures-0)

Structures are the foundation for web content. They determine which fields are available to users as they create new items for display. Structures not only improve manageability for you the administrator, but also make it much easier for users to quickly add content.

For example, say you're managing an online news magazine. All your articles need to contain the same types of information: a title, a subtitle, an author, and one or more pages of text and images that comprise the body of the article. If Liferay only supported simple content as has been described above, you'd have no way to make sure that your users entered a title, subtitle, and author. You might also get articles that don't match the look and feel of your site. If titles are supposed to be navy blue and they come in from your writers manually set to light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so that your users know what needs to be entered to have a complete article. Using structures, you can provide a form for your users which spells out exactly what is required and can be formatted automatically using a template.

To create a structure, you add form controls such as text fields, text boxes, text areas (HTML), check boxes, select boxes, and multi-selection lists, as well as specialized, Liferay-specific application fields such as Image Uploader and Documents and Media right onto the structure. Furthermore, you can move the elements around by dragging them where you want them. This makes it easy for you to prototype different orders for your input fields. Additionally, elements can be grouped together into blocks which can then be repeatable. Template writers can then write a template which loops through these blocks and presents your content in innovative ways, such as in sliding navigation bars, content which scrolls with the user, and more.

Let's look at how we edit a structure.

##### Editing a Structure [](id=lp-6-1-ugen03-editing-a-structure-0)

Go back to the control panel and select *Web Content* from the Site section. Click *Add* from the Web Content page to add another piece of content to your portal. Instead of going right for the content this time, however, we'll create a structure instead. To edit a structure, simply click on the *Edit* icon next to the *Structure* heading near the top of the page.

It's very easy to edit structures: all you have to do is drag elements into the structure and then give them names. For instance, select the *Checkbox* element under the *Form Controls* tab and drag it onto the structure. You can do the same with any of the elements. To remove it from the structure, simply select the *Delete* icon (black circle with X) in the upper right corner of the element. Take a moment to add, delete, and rearrange different elements.

![Figure 3.10: Structure Elements](../../images/04-web-content-structure-editor.png)

Liferay supports the following elements in structures:

**FORM FIELDS**

**Text Field:** Used for items such a titles and headings.
**Text Box:** Used for the body of your content or long descriptions.
**Text Area (HTML):** An area that uses a WYSIWYG editor to enhance the content.
**Checkbox:** Allows you to add a checkbox onto your structure. Template developers can use this as a display rule.
**Selection List:** Allows you to add a select box onto your structure.
**Multi-selection List:** Allows you to add a multi-selection list onto your structure.

**APPLICATION FIELDS**

**Image Uploader:** Allows you to add the upload image application into your structure.
**Documents and Media:** Allows you to add the Documents and Media folder hierarchy to your structure.

**MISCELLANEOUS**

**Link to Page:** Inserts a link to another page in the same site.
**Selection Break:** Inserts a break in the content.

These form elements provide all you need to model any information type that you would want to use as web content. Liferay customers have used structures to model everything from articles, to video metadata, to databases of wildlife. You're limited only by your imagination. To fire that imagination, let's look more closely at the form elements. 

##### Editing form elements [](id=lp-6-1-ugen03-editing-form-elements-0)

When creating a new structure it is essential that you set variable names that template writers can use to refer to elements on your form. If you don't do this, Liferay generates random variable names, and these can be difficult for a template writer to follow. For example, consider a field called *Author*. You might create this field in your form, but the underlying variable name in the structure might look something like `TextField4882`. The template writer needs to create markup for your structure and place the Author field in a certain spot in the markup. How will he or she know which field is Author, when they're all named randomly? 

To solve this problem, all you need to do is set a variable name for each field as you add it to your structure. Let's do this now. In your structure, add an element *Text Area (HTML)* which has the Field Label *Instructions*. If we wanted to give it the variable name `Steps`, we can do it very easily: at the bottom of every form element is a **Variable Name** field. Replace the generated name with the name you want to use. Now your template writer has a variable by which he or she can refer to this field. 

Below each field is a button labeled *Edit Options*. This contains several other ways to configure your fields: 

**Field Type:** changes the field type, in case you dragged the wrong field type to this location in the form. 
**Field Label:** changes the displayed label for the field. 
**Index Type:** Choose how you want Liferay to index your field for search. You can have it index by keyword, which filters out common words such as *and*, *but*, *the*, and so on, or you can have it index the full text of the field. By default, indexing is turned off. 
**Predefined Value:** If there's a common default value for this field, type it here. 
**Instructions for the User:** You can type instructions in the **Instructions for the User** field and display them as a tooltip by checking the box. 
**Repeatable:** If you want this field to be a repeatable element, check this box. Your users will then be able to add as many copies of this field as they like. For example, if you're creating a structure for articles, you might want a repeatable Author field in case you have multiple authors for a particular article. 
**Required:** Check the box to mark the field required. If a field is required, users cannot submit content using this structure without filling out this field. 

For the Nose-ster structure, type something in the *Instructions for the User* field that helps users know what to put into the Body element (example: *this is an HTML Text area for the body of your content*). Also enable the *Display as Tooltip* box. Now, when users hover over the Help icon near your title, your instructions are displayed. 

As with everything else in Liferay, you can set permissions on structures. Let's see how you'd do that. 

##### Assigning Permissions [](id=lp-6-1-ugen03-assigning-permissions-1)

Setting permissions on structures is done using the same procedure as permissions everywhere else in Liferay. Most users should not have the ability to edit structures. Structures are coupled with templates, which require some web development knowledge to create. For this reason, only trusted developers should be able to create structures and templates. Users, of course, should be able to view structures. The View permission enables them to make use of the structures to create content.

![Figure 3.11: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

You can grant or deny permissions based on Roles, and this is the recommended way to handle permissions for structures.  

Now that you understand what structures are used for, you need to understand the other half of Liferay's web content management system: templates. 

#### Using templates [](id=lp-6-1-ugen03-using-templates-0)

Developers create templates to display the elements of the structure in the markup that they want. Content can then be styled properly using CSS, because markup is generated consistently by the template when structured content is displayed. In essence, templates are scripts that tell Liferay how to display content in the structure. Any changes to the structure require corresponding changes to the template, because new or deleted fields produce errors on the page. If users enter content into a structure, it *must* have a matching template. Without a template, the portal has no idea how to display content which has been created using a custom structure. 

Let's look more closely at the types of templates Liferay supports. 

##### Template Types (VM, XSL, FTL, and CSS) [](id=lp-6-1-ugen03-template-types-vm-xsl-ftl-and-css-0)

Liferay supports templates written in four different templating languages, to support the skill sets of the largest number of developers. This increases the chances that you can jump right in and use whichever one you've already used before. If you haven't yet been exposed to any of them, your best bet is Velocity or Freemarker, as they are less "chatty" than XSL and extremely simple to understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix logic with HTML. This is similar to other scripting languages, such as PHP, that you may have seen before, though Velocity is much simpler. It's been in the product the longest, so it is probably the most widely used language for templates in Liferay WCM. If you haven't used any of the template languages before, we recommend using Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to transform the underlying XML of a structure into markup suitable for the browser. While it may not be as clean and compact as Velocity or FTL, it's widely used for transforming XML into other formats, and it's very likely your developers have already been exposed to it. 

**FTL** (FreeMarker Template Language): Freemarker is a templating language which could be considered a successor to Velocity, though it is not yet as popular. It has some advantages over Velocity for which it sacrifices some simplicity, yet it is still easy to use. 

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very straightforward and modifications are simple (colors, fonts, layouts, etc). If your structure is more complex, however, you'll need to use one of the other options.

##### Adding a Template [](id=lp-6-1-ugen03-adding-a-template-0)

Liferay WCM makes it easy to create structures, templates, and content from the same interface. Let's go through the entire flow of how you'd create a structure, link it to a template, and then create content using them both. We'll use Velocity for our template, and we'll lay out the structure fields systematically to go along with the format we've defined for our content.

![Figure 3.12: Adding Template Interface](../../images/04-web-content-templates-create.png)

1.  Go back to the Web Content section of the control panel and click *Add* under *Web Content*.
2.  Click the *Edit* icon for Structures.
3.  Remove the Content field and add the following fields:

Field Type		Variable Name
----------		-------------
Text				*title*
Text Box		 	*abstract*
Image			*image*
Text Area		*body*

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

Below is the template script for this structure. It is written in Velocity:

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

This template is pretty small, but it actually does quite a bit. First, a portlet URL which maximizes the portlet is created. Once this is done, the template gets the namespace of the portlet. This is important to avoid URL collisions with other URLs that might be on the page.

After this, the template attempts to get a request parameter called `read_more`. Whether or not this was successful is the key to the rest of the script:

-   If the template got the `read_more` parameter, it displays the abstract and the body below the title (which is always displayed).

-   If the template didn't get the `read_more` parameter, it displays the image, the abstract, and the link created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 3.13: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 3.14: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

Of course, there is much, much more you can do with structures and templates. Check out the Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)) for further information and examples.

#### Assigning template permissions [](id=lp-6-1-ugen03-assigning-template-permissions-0)

Permissions for templates are similar to permissions for structures. As with structures, you only want specific developers editing and creating templates. You may, however, want to make the templates viewable to some content creators who understand the template scripting language, but are not directly writing the scripts. You can determine who views the template by selecting from the *Viewable By* select box beneath the *Permissions* tab. By default the *Anyone (Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can do this by selecting the *More* link.

From the *More* link, you can grant or deny permissions based on Roles. For instance, you may create a role with the ability to update the template and create a second role that can both update and delete. Liferay Portal makes it possible to assign permissions based on the roles and responsibilities within your organization.

Now that you can create all kinds of advanced content with structures and templates, you'll want to know how Liferay makes it possible to manage how that content is published. We'll cover that next. 

### Advanced Publishing Options [](id=lp-6-1-ugen03-advanced-publishing-options-0)

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've gone over Liferay management tools that help you create content quickly and in an orderly fashion. You created a simple announcement with Liferay's structure editor that allows you to quickly design a structure and prepare it for the template designers. Then you applied a template to the structure. You know how to display content using the Web Content Display portlet. Now, you're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become out of date, and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and now inaccurate) web content.

You also want your content to be found easily by your users. This is done through tags and categories. 

Additionally, you may want to create content and send it through a review and approve process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* and *Workflow* features.  

#### Scheduling Web Content [](id=lp-6-1-ugen03-scheduling-web-content-0)

Liferay's WCM lets you define when your content goes live. You can determine when the content is displayed, expired, and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form that your users make use of to add web content, in the same column as the structure and template selectors.

![Figure 3.15: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Sets (within a minute) when content will be displayed.
**Expiration Date:** Sets a date to expire the content. The default is one year.
**Never Auto Expire:** Sets your content to never expire.
**Review Date:** Sets a content review date. 
**Never Review:** Sets the content to never be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content. 

In addition to controlling when content displays, you can use the Display Page feature to control where content displays.

#### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of time with this guide, you might have noticed something about how Liferay handles web content--content is never tied directly to a page. While this can be useful (because it means that you don't have to recreate content if you want to display the same thing on multiple pages), it also means that you don't have a static URL for any web content, which is bad for search engine optimization.

To fix this issue, Liferay has introduced the concept of Display Pages and Canonical URLs. Each web content entry on the portal has a canonical URL, which is the official location of the content that is referenced any time the content is displayed. A Display Page can be any page with an asset publisher set to its default settings. You can create the page yourself, or use the *Content Display Page* page template included with Liferay.

So right now you're thinking "Wait, you just told me that each Web Content item has its own URL, and that this is somehow related to pages where we display a whole bunch of content on the same page?" Yes. That's exactly what I said. Just watch--create a display page somewhere on your portal, using the Content Display Page template. Now click on *Display Page*, and select the page you just created as the display page for the content. 

![Figure 3.16: Selecting a Display Page](../../images/04-web-content-display-page.png)

You can now click the link to display the content, and the canonical URL for content shows in your browser's address bar. You can create your own custom display page, and any additional portlets that you place on the page are displayed along with the content when you access it via the canonical URL. 

![Figure 3.17: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to tags and categories. 

#### Tags and Categories [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags are keywords that are attached to web content in order to help users find content. Categories are hierarchical structures of content that are defined by administrators. Tags and categories make it easier for your users to find  content through search or navigation.

By assigning a tag to web content, you define metadata about that content. This is then used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by creators of content, and it's important to tag content whenever it is created. If you don't tag content, all the search engine has to go on is the full text of the content (if you've made your content indexable), and that might not produce the most optimal results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

Tags can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

Categories are a little bit different, and new users generally ask a valid question when presented with tags and categories: what's the difference? Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they're never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the author. Categories do the same thing as the table of contents. 

By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 3.18: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used to manage all your tags and categories in one place. We'll look at this interface in the next chapter. 

Next, we'll look at how to configure the way pages are accessed by mobile devices.

### Mobile Device Rules [](id=lp-6-1-ugen03-mobile-device-rules-0)

Mobile device rules allow you to configure sets of rules and use those rules to alter the behavior of the portal based on the device being used to access Liferay. The proportion of mobile devices users browsing the web has been steadily increasing so it's important to be able to handle different kinds of devices appropriately. For instance, you can configure the look and feel of Liferay pages accessed by smartphone or tablet users differently than those accessed by PC users. 

Both sites and individual pages can be configured with any number of rule groups. A rule group is designed to describe a group of devices. It can contain one or more rules which describe a category of devices, such as all android devices or all iOS tablets. Rule groups can be prioritized to determine which one will be applied to a given page request. You can define as many rules as you need to classify all the devices you want to take actions on.

In order to configure mobile device rules, you must install the wurlf-web plugin. This plugin enables Liferay to detect which mobile device or operating system is being used for any given request. To install the plugin, navigate to the Plugins Installation section of the control panel, located under the Server heading. Then search for the wurlf-web plugin and click *Install*. 

Alternatively, if you have a working copy of Liferay's plugin repository, you can install the plugin manually. The plugin is available from Liferay's public SVN repository at [http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/](http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/) or from Liferay's public Github repository at [https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web](https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web). Once you have checked out or cloned the repository, navigate to the plugins root directory and create a `build.username.properties` file. Add the line `app.server.dir = <path-to-app-server>`, replacing `<path-to-app-server>` with the path to the application server running Liferay on your machine. Then navigate to the wurlf-web directory and execute the ant deploy target.

You can access the Mobile Device Rules administrative page from the Content section of the control panel. Select the appropriate scope using the context menu selector so that your rule groups are available where you expect them to be. The Mobile Device Rules administrative page displays a list of defined rule groups and allows you to add more. To add rules to a rule group, select *Actions* &rarr; *Manage Rules* or just click on a rule group to edit it and then click the *Manage Rules* link.

![Figure 3.19: You can manage device rules from the Mobile Device Rules administrative page.](../../images/mobile-device-rules.png)

The rules defined for a rule group, along with the priorities of the rule groups selected for a particular site or page, determine which rule will be applied to a given request. From the Manage Rules page for a specific rule set, you can add a rule by specifying a rule type. Note that, by default, only the Simple Rule type is available. However, the rules are designed to be extensible and additional rule types can be added by your developers. Once added, you can edit the rule to specify a device type and operating system.

![Figure 3.20: You need to install the wurlf-web plugin to populate the OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device rule groups and added some rules to them, you'll be ready to set up some actions. The actions defined for a rule group determine what happens to a particular request when the request's device has been detected and the rule group has been found to apply. 

You can add actions to a rule group from the Site Pages page of the control panel. Select either the public or private pages and then look for the *Mobile Rule Groups* link in the right-hand menu. Use the *Select Rule Group* button to select rule groups to be applied either to a site or to a single page. If you select the page group itself from the left-hand menu, the selected rule group will apply to all the pages of the site by default. However, if you select an individual page and then click the the *Select Rule Group* button, the rule groups will apply to only that page. You can select multiple rule groups for a particular site or page and order them by prority. The rule groups are checked in decreasing order of priority: the actions defined by the first rule group that applies are executed.

![Figure 3.21: You can select a mobile device rule group to apply for a site or page from the Site Pages section of the control panel.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage Actions* button and then click *Add Action*. By default, there are four kinds of actions that be configured for mobile rule groups: layout template modifications, theme modifications, simple redirects, and site redirects. Layout template modifications let you change the way portlets are arranged on pages delivered to mobile devices and themes modifcations let you select a specifc look and feel. If it makes more sense for you to create separate mobile versions of certain sites or pages you can use a redirect to make sure that mobile devices users get to the right page. To define a simple redirect, you need to specify a URL. To define a site redirect, you only need to specify the site name and page name of the page you're redirecting to. Like mobile device rules, mobile device actions are designed to be extensible. Your developers can define custom actions in addition to the four actions provided by default. Next, let's discuss Liferay's asset publisher, which you can use to publish many different kinds of portal content.

### Using the Asset Publisher Portlet [](id=lp-6-1-ugen03-using-the-asset-publisher-portlet-0)

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your content. Let's look at some of its features.

#### Querying for Content [](id=lp-6-1-ugen03-querying-for-content-0)

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet, or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

##### Selecting assets manually [](id=lp-6-1-ugen03-selecting-assets-manually-0)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell the Asset Publisher that you want to select content manually. You can select what you want to be published within the portlet, or you can create new content  from within the Asset Publisher.

![Figure 3.22: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here is added to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

##### Selecting assets dynamically [](id=lp-6-1-ugen03-selecting-assets-dynamically-0)

Asset Publisher's default behavior is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you'll display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 3.23: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping [](id=lp-6-1-ugen03-ordering-and-grouping-0)

You can display the content returned by the filters above in order by title, create date, modified date, view count, and more in ascending or descending order. For instance, you may have a series of "How To" articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type*, or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. Again, we'll see more about categories in chapter 4.

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings [](id=lp-6-1-ugen03-display-settings-0)

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type, and file conversions. Additionally, you can enable printing, flags, ratings, comments, and comment ratings, and these work the same way they do in the Web Content Display portlet.

##### Display Style [](id=lp-6-1-ugen03-display-style-0)

**Abstracts:** Shows the first 200-500 characters of the content, defined by the **Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

##### Other Settings [](id=lp-6-1-ugen03-other-settings-0)

**Asset Link Behavior:** When the link to the asset is clicked, it can be displayed in the Asset Publisher or in the portlet to which the asset belongs, such as the Blogs or Message Boards.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next navigation; regular includes a way of selecting the page to which you'd like to navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it from the list.

**Show Available Locales:** Since content can be localized, you can have different versions of it based on locale. This will show the locales available, enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org integration, you can allow your users to convert the content to one of several formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see below).

![Figure 3.24: Show Metadata](../../images/portal-admin-ch4_html_m409b2939.jpg)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, it's easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

Next, we'll look at some of the most powerful features of Liferay WCM: staging and workflow. 

#### Staging and Workflow [](id=lp-6-1-ugen03-staging-and-workflow-0)

Staging is an important feature of Liferay WCM. The concept of staging is a simple one: you can modify your site behind the scenes, and then publish it all in one shot. You don't want your users seeing your web site change before their eyes as you're modifying it, do you? Liferay's staging environment allows you to make changes to your site in a specialized *staging area*, and then when you're finished, publish the whole site to your users.

You can use staging in multiple ways. You can have a staging server—-a separate instance of Liferay Portal which is used just for staging. Content creators can then use this server to make their changes while the live server handles the incoming user traffic. When changes to the site are ready to be published, they are pushed over the network to the live server.

You can also use staging in the same instance of your Liferay Portal. In this configuration, you have a *local* staging environment: you host both your staging environment and your live environment on the same server. Either way, once set up, the interface is the same; the only difference comes when it's actually time to publish your content.

In addition, Liferay 6.1 adds the capability to create multiple variations of staged pages, so that you can manage several future versions of a site simultaneously. Variations can be merged and published through an intuitive UI. Let's jump in to see how to use staging.

##### Enabling the staging environment [](id=lp-6-1-ugen03-enabling-the-staging-environment-0)

Staging configuration can be found in the Site Settings UI. The Staging tab allows us to make changes in a staging environment and preview our work before publishing it to the live site. Let's create a staging environment for Nose-ster's home page.

First, you'll add a new page. Click *Add &rarr; Page* from the toolbar in the default site and name the new page *News and Events*. Next, click the *View Pages* button and add the Alerts and Announcements portlets to it.

Now we're ready to activate the staging feature for this site. Go to the control panel then to *Site Settings* and select *Staging* from under the *Advanced* heading.

![Figure 3.25: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

We'll assume we don't have a separate staging server, and so we'll select a staging type of *Local Live*. If you want to set up a remote staging environment, it's easy. First, select *Remote Live*, and then supply the name or IP of the remote server where staged content should be published, the port (80 if Liferay is sitting behind a web server, or the port your application server is listening on if not), and the remote site or organization ID. You can find this ID by selecting *Actions &rarr; Edit* on any site in the control panel. Either way, once you make a selection (*Local Live* or *Remote Live*), more options become available.

We'll cover many of the collaboration portlets listed here when we come to chapter 4. For now you just need to be aware that the option is available to enable or disable staging for any of them, and you need to decide if you want to stage content for these portlets. In the case of the collaborative portlets, the answer is usually "no." Why? Because portlets such as the Message Boards are designed for user interaction. If their content were staged, you'd have to manually publish your site whenever somebody posted a message on the message boards in order to make that message appear on the live site.

Generally, you'll want web content to be staged, because end users aren't creating that kind of content-—web content is the stuff you publish to your site. But portlets like the message boards or the wiki would likely benefit from *not* being staged.

Enabling *Page Versioning* makes it so that you can work in parallel with other users on multiple versions of the same pages, and it gives you the flexibility to revert easily to a previous version if you encounter any issues. Check *Enabled On Public Pages* so that we can look at versioning.

##### Using the staging environment [](id=lp-6-1-ugen03-using-the-staging-environment-0)

If you navigate back to the News and Events page you'll now notice some new items along the top of the screen. These will help us manage staged pages. You'll also notice that most of your page management options have been removed, because you can now no longer directly edit live pages--you'll now use the staging environment to do that. Click on *Staging* to view the staged area. Your management options are restored, and you have some new options related to staging.

![Figure 3.26: You can see the new bar that staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Calendar portlet and then click on *Live* from the Dockbar. Notice that the Calendar portlet isn't there. That's because you've staged a change to the page, but haven't published that change yet to the live site. Go back to the staged page, and look at the options you have available. From here you can *Undo* changes, view a *History* of changes, *Mark as Ready for Publication*, and *Manage Page Variations*.

**Undo/Redo:** allows you to step back/forward through recent changes to a page, which can save you the time of manually adding or removing portlets if you make a mistake.

**History:** shows you the list of revisions of the page, based on publication dates. You can go to any change in the revision history and see how the pages looked at that point.

**Manage Page Variations:** allows you to work in parallel on multiple versions of a staged page. We will explain this later.

After you're done making changes to the staged page, click the *Mark as Ready for Publication* button. The status of the page changes from *Draft* to *Ready for Publication* and any changes you've made can be published to the Live Site. When you publish a page to live, only the version which was *Marked as Ready for Publication* is published.

The dropdown next to the Staging link at the top gives you the option to *Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** immedatiately pushes any changes to the Live Site.

**Schedule Publication to Live:** lets you set a specific date to publish or to setup recurring publishing. You could use this, for example, to publish all changes made during the week every Monday morning without any further intervention.

Click on *Mark as Ready for Publication* and then *Publish to Live Now* to publish your Calendar portlet to the live site.

Content publication can be also controlled using staging. Calendar events are staged by default (this can be changed in Staging Configuration). If you create an event in the staged site, it isn't visible in the live site until you publish it to the live site following the same steps you just performed (you can select which types of content are published when you publish to the live site). If workflow is enabled for Calendar Events, the event needs to go through the workflow process before it can be published to the live site.

![Figure 3.27: Ready to publish to the live site.  ](../../images/04-web-content-staging-publish.png)

One of the most powerful features of staging is page variations. Let's see how to use them to create multiple different variations of your site's pages for different purposes.

#### Site Pages Variations [](id=lp-6-1-ugen03-site-pages-variations-0)

Let's say that you're working on a product-oriented site where you'll have several major changes to a page or a set of pages over a short period of time, and you need to be working on multiple versions of the site at the same time to ensure that everything has been properly reviewed before it goes live. With staging in Liferay 6.1 you can do this with **Page Variations**.

For example, you can create several page variations, enabling the marketing team to give your site a completely different look and feel for Christmas. At the same time, the product management team can work on a different version that will be published the day after Christmas for launching of a new product. Additionally, the product management team is considering two different ideas for the home page of the site, so they can create several page variations of the home page inside their product launch site.

Variations only affect pages and not the content, which means that all the existing content in your staging site is shared by all your variations. In different site page variations you can have different logos, look and feel for your pages, different applications on these pages, different configuration of these applications, and even different pages. One page can exist in just one site page variation or in several of them.

By default, we only have one site page variation which is called **Main Variation**. In order to create a new one, use the dropdown next to the *Staging* link and click on *Manage Site Pages Variations*. This brings you to a list of the existing site page variations for your site. Click *Add Site Pages Variation* to create a new one. From the *Add Site Pages Variation* screen, you can set a Name, Description, and also set your new variation to copy the content from an existing variation. There are several options to choose in this selector. 

**Any existing Site Pages Variation:** creates a new site page variation that contains only the last version of all the pages that exist in this variation. The current variation must be marked as ready for publication. 

**All Site Pages Variation:** creates a new variation that contains the last version marked as ready for publication from any single page existing in any other variation.

**None:** creates a new, empty variation.

You are also able to rename any variation. For example, edit the Main Variation and change its name to something that makes more sense in your site, such as *Basic*, *Master*, *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging menu bar. It's also possible to set permissions on each variation, so that certain users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo, apply a new theme, move portlets around, change the order of the pages, and configure different portlets. The other variations won't be affected. You can even delete existing pages or add new ones (remember to *Mark as Ready for Publication* when you are finished with your changes). 

When you delete a page, it is deleted only in the current variation, and the same happens when you add a new page. If you try to access a page which was deleted in the current variation, Liferay informs you that this page is not *enabled* in this variation, and you can enable it. To enable it, navigate to the *Manage* &rarr; *Site Pages* screen. Here, all the existing pages for all the variations are shown in a tree. Pages that are not enabled for the current variation are shown in a lighter color.

To publish a variation to the live site, click on *Publish to Live now* in the dropdown next to the variation name. Publications can also be scheduled independently for different variations. For example, you could have a variation called *Mondays* which is published to the live site every Monday and another one called *Day 1* which is published to the live site every first day of each month.

You can also have variations for a single page inside a site page variation, which allows you to work in parallel in different versions of a page. For example, you might work on two different proposals for the design of the home page for the Christmas variation. These page variations only exist inside a site Page variation.

To create a new page variation, click *Manage Page Variations* on the staging toolbar. This brings you to a list of existing page variations for the current page (by default, there is only one called *Main Variation*). You can create more or rename the existing one. You can switch between different page variations using the toolbar containing the page variations below the site pages variations toolbar. When you decide which page variation should be published, mark it as *Ready for Publication*. Only one page variation can be marked as ready for publication and that is the one that gets published to the live site.

![Figure 3.28: Creating a new Page Variation](../../images/04-web-content-add-site-pages-variation.png)

For example, we could create a page variation called Thanksgiving for the News and Events page inside of the Christmas variation and another one called Christmas Day to display different content on those particular days.

![Figure 3.29: The Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations. In order to merge two Site Pages Variations, you need to go to the Manage Site Variations screen. From there, you should click on *Merge* on the Site Pages Variation that you want to use as the base, and you will be asked to choose the Site Pages Variation to merge on top of it. Merging works in the following way:

* New pages that don't exist in the base Variation, will be added.
* If a page exists in both Site Pages variations, and at least one version of the page was marked as ready for publication, then the latest version marked as ready will be added as a new Page Variation in the target page of the base Variation. (Note that older versions or page variations not marked as ready for publication won't be copied. However, merge can be executed as many times as needed and will create the needed pages variations in the appropriate page of the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base Variation, it will just add more versions, pages and page variations as needed.

Let's finish our discussion of staging by outlining a few more features. 

#### Wrapping up staging [](id=lp-6-1-ugen03-wrapping-up-staging-0)

You can enable staging on an individual site basis, depending on your needs. This makes it easy to put strict controls in place for your public web site, but to open things up for individual sites that don't need such strict controls. Liferay's staging environment is extremely easy to use and makes maintaining a content-rich web site a snap. 

Let's go one step further by introducing workflow into the mix.

### Using Liferay's Integrated Workflow with Content Management [](id=lp-6-1-ugen03-using-liferays-integrated-workflow-with-content-management-0)

Workflow is essentially a predetermined sequence of connected steps. In Liferay WCM, workflow is designed to manage the creation, modification, and publication of web content. You can set up a workflow so that content can't be published without going through an approval process that you design. In this way, content is published to the site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow, and it ships with Liferay CE. If you have uninstalled it or are using EE, it needs to be installed and configured separately. This is covered in chapter 6. Since we have somewhat of a "What came first--the chicken or the egg?" problem, for now, we'll assume it's installed and look at how you can take advantage of workflow in getting your content through any approval steps between creation and publication.

You may have noticed that something appears to be missing from the staging process discussed above. In particular, you might be asking the question, "How do I reject changes?" Starting with Liferay 6.1, Staging is integrated with Liferay's Workflow engine. In order to have a review process for staged pages, you need to make sure that you have a workflow engine configured, and that you have staging set up in the workflow. To do this, select the workflow definition desired for page revisions in the Workflow Configuration. 

When using a workflow, clicking *Submit for Publication* submits the staged pages into the workflow. Once all necessary approvals have been completed, the page status is marked as ready for publication. The *Publish to Live Now* and *Schedule for Publication* options  publish the last version of the selected pages marked as ready for publication.

To enable workflow for Web Content, navigate to the control panel and select *Workflow Configuration*. From there, select a workflow that has been deployed to Liferay.

![Figure 3.30: Enabling Workflow for Content Management](../../images/04-web-content-workflow-config.png)

As you'll discover in chapter 6, you can design workflows to suit your organization's approval process. For Nose-ster's implementation we'll use the *Single Approver* workflow which ships with the product.

#### Defining Workflows for Web Content [](id=lp-6-1-ugen03-defining-workflows-for-web-content-0)

Let's set up Liferay's Workflow for the Nose-ster web site.

1.  Go to the control panel and select *Workflow Configuration* from the left panel.

2.  From the select box, choose *Single Approver* for Web Content. Click *Save.* Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now that workflow is enabled, publishing content works a little bit differently. Let's go through the process of publishing details for new class offerings at Nose-ster. Return to the home page and click the *Add Web Content* icon on the Web Content Display portlet. Call the new content *Course Offerings* and enter some content. Notice that the Publish button is now gone. In its place is a *Submit for Publication* button. Go ahead and click it.

![Figure 3.31: Pending Workflow](../../images/04-web-content-workflow-config.png)

Next, go to the *Workflow Tasks* in control panel and then select *My Workflow Tasks*. You will see the option to Review Content for Sales Goals. It shows because you are logged in as an Administrator. There is also a Content Approvers role which is defined by this workflow, and anyone in this role can approve content as well.

To approve the content, you must first take ownership of it. Click on the task. You should see the screen below.

Taking ownership of, reviewing, and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to someone else in the Content Approvers role or create / update a due date for the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve* or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created the content to be the one who approves it. Instead, you would have one or more roles designed for users who will be creating content, and you would have specific users assigned to one or more roles for approving content. Our example was of a very straightforward workflow, as it has only a single approver. Kaleo workflow allows you to design workflows that go through as many steps as you need to conform to your business processes. We look at Kaleo workflow in more detail in chapter 6.

## Summary [](id=summ-25)

This chapter has been your guide to Liferay Web Content Management. We've seen how Liferay manages both simple content and advanced content with structures and templates. These powerful tools enable you to handle easily any type and amount of material that you'd like to publish on the web. 

Liferay WCM also includes a powerful staging environment, allowing you to stage content locally on the same server or remotely to another server. You can publish your site when you want it, on the schedule you choose. You can even create different variations of your site that can be worked on simultaneously. 

Whether your site is small and static or large and dynamic, Liferay's WCM enables you to plan and manage it. With tools such as the WYSIWYG editor, structures, and templates, you can quickly add and edit content. With the Web Content Display and Asset Publisher, you can rapidly select and configure what and how your content is displayed. And by using Liferay's integrated workflow, you can set up custom publishing rules to fit your organization. You will find that managing your site becomes far easier when using Liferay's Web Content Management system.
