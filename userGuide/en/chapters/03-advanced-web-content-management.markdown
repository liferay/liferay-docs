# Advanced Web Content Management  [](id=lp-6-1-ugen03-advanced-web-content-management--0)

<!--   My first attempt at writing an intro. for Ch. 03   -->
In the previous chapter we looked at some basic ways you can use Liferay to handle your web content. In this chapter we’ll delve deeper into slightly more complex web content management techniques. But don’t be alarmed, it’s not too intense. We'll cover the following topics:

- Web content structures and templates
- Leveraging Liferay's multi-site capabilities
- Using page templates and site templates
- Allowing users to customize site pages
- Staging
- Creating teams to allow for flexible management of site permissions
- Mobile device rules

We’ll examine how web content structures and templates provide additional power and flexibility to the web content management system we saw in chapter 2. You'll also learn how easy it is to set up and administer multiple sites in Liferay. Next, we'll learn how you can empower your users to create personal customizations of site pages. We'll also examine how you can use staging to manage the publication of pages and content on your site. We’ll conclude with sections on creating teams and rules for presenting site pages to mobile devices. Once finished with this chapter, you’ll be the envy of your peers as they’ll think you really know what you’re doing. 

## Advanced content with structures and templates [](id=lp-6-1-ugen03-advanced-content-creation-0)

If you've ever launched a web site, you know that as it grows, you can experience growing pains. This is the case especially if you've given lots of people access to the site to make whatever changes they need to make. Without preset limitations, users can display content in any order and in any manner they desire (think huge, flashing letters in a font nobody can read). Content can get stale, especially if those responsible for it don't maintain it like they should. And sometimes, content is published that should never have seen the light of day. 

Thankfully, Liferay WCM helps you handle all of those situations. You can use *Structures* to define which fields are available to users when they create content. These can be coupled with *Templates* that define how to display that content. Content won't get stale, because you can take advantage of the *Scheduling* feature to determine when content is displayed and when it's removed. Additionally, you can configure Liferay's built-in *Workflow* system to set up a review and publishing process so only what you want winds up on the live site. Liferay Portal gives you the management tools you need to run everything from a simple, one-page web site to an enormous, content-rich site.

All of this starts with structures. 

#### Using structures [](id=lp-6-1-ugen03-using-structures-0)

Structures are the foundation for web content. They determine which fields are available to users as they create new items for display. Structures not only improve manageability for the administrator, they also make it much easier for users to quickly add content.

For example, say you're managing an online news magazine. All your articles need to contain the same types of information: a title, a subtitle, an author and one or more pages of text and images that comprise the body of the article. If Liferay only supported simple content as has been described above, you'd have no way to make sure your users entered a title, subtitle, and author. You might also get articles that don't match the look and feel of your site. If titles are supposed to be navy blue but they come in from your writers manually set to light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so your users know what needs to be entered to have a complete article. Using structures, you can provide a form for your users which spells out exactly what is required and can be formatted automatically using a template.

You create a structure by adding form controls such as text fields, text boxes, text areas (HTML), check boxes, select boxes and multi-selection lists. Also you can add specialized, Liferay-specific application fields such as Image Uploader and Documents and Media right onto the structure. Furthermore, you can move the elements around by dragging them where you want them. This makes it easy for you to prototype different orders for your input fields. Additionally, elements can be grouped together into blocks which can then be repeatable. Template writers can then write a template which loops through these blocks and presents your content in innovative ways, such as in sliding navigation bars, content which scrolls with the user and more.

Let's look at how we edit a structure.

##### Editing a Structure [](id=lp-6-1-ugen03-editing-a-structure-0)

Go back to the control panel and select *Web Content* from the Site section. Click *Add* from the Web Content page to add another piece of content to your portal. Instead of going right for the content, this time we'll create a structure. To edit a structure, simply click on the *Edit* icon next to the *Structure* heading near the top of the page.

It's very easy to edit structures: all you have to do is drag elements into the structure and then give them names. For instance, select the *Checkbox* element under the *Form Controls* tab and drag it onto the structure. You can do the same with any of the elements. To remove it from the structure, simply select the *Delete* icon (black circle with X) in the upper right corner of the element. Take a moment to add, delete and rearrange different elements.

![Figure 3.1: Structure Elements](../../images/04-web-content-structure-editor.png)

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

These form elements provide all you need to model any information type you would want to use as web content. Liferay customers have used structures to model everything from articles, to video metadata, to databases of wildlife. You're limited only by your imagination. To fire that imagination, let's look more closely at the form elements. 

##### Editing form elements [](id=lp-6-1-ugen03-editing-form-elements-0)

When creating a new structure it is essential you set variable names template writers can use to refer to elements on your form. If you don't do this, Liferay generates random variable names and these can be difficult for a template writer to follow. For example, consider a field called *Author*. You might create this field in your form but the underlying variable name in the structure might look something like `TextField4882`. The template writer needs to create markup for your structure and place the Author field in a certain spot in the markup. How will he or she know which field is Author when they're all named randomly? 

To solve this problem, all you need to do is set a variable name for each field as you add it to your structure. Let's do this now. In your structure, add an element *Text Area (HTML)* which has the Field Label *Instructions*. If we wanted to give it the variable name `Steps`, we can do it very easily: at the bottom of every form element is a **Variable Name** field. Replace the generated name with the name you want to use. Now your template writer has a variable by which he or she can refer to this field. 

Below each field is a button labeled *Edit Options*. This contains several other ways to configure your fields: 

**Field Type:** changes the field type, in case you dragged the wrong field type to this location in the form.
 
**Field Label:** changes the displayed label for the field. 

**Index Type:** Choose how you want Liferay to index your field for search. You can have it indexed by keyword, which filters out common words such as *and*, *but*, *the*, and so on, or you can have it index the full text of the field. By default, indexing is turned off. 

**Predefined Value:** If there's a common default value for this field, type it here. 

**Instructions for the User:** You can type instructions in the field and display them as a tooltip by checking the box. 

**Repeatable:** If you want this field to be a repeatable element, check this box. Your users will then be able to add as many copies of this field as they like. For example, if you're creating a structure for articles, you might want a repeatable Author field in case you have multiple authors for a particular article. 

**Required:** Check the box to mark the field required. If a field is required, users cannot submit content using this structure without filling out this field. 

For the Nose-ster structure, type something in the *Instructions for the User* field that helps users know what to put into the Body element (example: *this is an HTML Text area for the body of your content*). Also enable the *Display as Tooltip* box. Now, when users hover over the Help icon near your title, your instructions are displayed. 

As with everything else in Liferay, you can set permissions on structures. Let's see how you'd do that. 

##### Assigning Permissions [](id=lp-6-1-ugen03-assigning-permissions-1)

Setting permissions on structures is done using the same procedure as permissions everywhere else in Liferay. Most users should not have the ability to edit structures. Structures are coupled with templates, which require some web development knowledge to create. This is why only trusted developers should be able to create structures and templates. Users, of course, should be able to view structures. The View permission enables them to make use of the structures to create content.

![Figure 3.2: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

You can grant or deny permissions based on Roles and this is the recommended way to handle permissions for structures.  

Now that you understand what structures are used for, you need to understand the other half of Liferay's web content management system: templates. 

#### Using templates [](id=lp-6-1-ugen03-using-templates-0)

Developers create templates to display the elements of the structure in the markup they want. Content can then be styled properly using CSS, because markup is generated consistently by the template when structured content is displayed. In essence, templates are scripts that tell Liferay how to display content in the structure. Any changes to the structure require corresponding changes to the template, because new or deleted fields produce errors on the page. If users enter content into a structure, it *must* have a matching template. Without a template, the portal has no idea how to display content which has been created using a custom structure. 

Let's look more closely at the types of templates Liferay supports. 

##### Template Types (VM, XSL, FTL and CSS) [](id=lp-6-1-ugen03-template-types-vm-xsl-ftl-and-css-0)

Liferay supports templates written in four different templating languages, to support the skill sets of the largest number of developers. This increases the chances you can jump right in and use whichever one you've already used before. If you haven't yet been exposed to any of them, your best bet is Velocity or Freemarker, as they are less "chatty" than XSL and extremely simple to understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix logic with HTML. This is similar to other scripting languages, such as PHP, though Velocity is much simpler. Because it's been in the product the longest, it is probably the most widely used language for templates in Liferay WCM. If you haven't used any of the template languages before, we recommend using Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to transform the underlying XML of a structure into markup suitable for the browser. While it may not be as clean and compact as Velocity or FTL, it's widely used for transforming XML into other formats and it's very likely your developers have already been exposed to it. 

**FTL** (FreeMarker Template Language): Freemarker is a templating language which could be considered a successor to Velocity, though it is not yet as popular. It has some advantages over Velocity for which it sacrifices some simplicity, yet it is still easy to use. 

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very straightforward and modifications are simple (colors, fonts, layouts, etc). If your structure is more complex, however, you'll need to use one of the other options.

##### Adding a Template [](id=lp-6-1-ugen03-adding-a-template-0)

Liferay WCM makes it easy to create structures, templates and content from the same interface. Let's go through the entire flow of how you'd create a structure, link it to a template and then create content using them both. We'll use Velocity for our template and we'll lay out the structure fields systematically to go along with the format we've defined for our content.

![Figure 3.3: Adding Template Interface](../../images/04-web-content-templates-create.png)

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

This template is pretty small but it actually does quite a bit. First, a portlet URL which maximizes the portlet is created. Once this is done, the template gets the namespace of the portlet. This is important to avoid URL collisions with other URLs that might be on the page.

After this, the template attempts to get a request parameter called `read_more`. Whether or not this was successful is the key to the rest of the script:

-   If the template got the `read_more` parameter, it displays the abstract and the body below the title (which is always displayed).

-   If the template didn't get the `read_more` parameter, it displays the image, the abstract and the link created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 3.4: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 3.5: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

<!-- another attempt at a transition   -->
Now that you've created a handsome template, it's time to decide who the lucky people are that get to use it.

#### Assigning template permissions [](id=lp-6-1-ugen03-assigning-template-permissions-0)

Permissions for templates are similar to permissions for structures. As with structures, you only want specific developers editing and creating templates. You may, however, want to make the templates viewable to some content creators who understand the template scripting language but are not directly writing the scripts. You can determine who views the template by selecting from the *Viewable By* select box beneath the *Permissions* tab. By default the *Anyone (Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can do this by selecting the *More* link.

From the *More* link, you can grant or deny permissions based on Roles. For instance, you may create a role with the ability to update the template and create a second role that can both update and delete. Liferay Portal makes it possible to assign permissions based on the roles and responsibilities within your organization.

<!--   another attempt at a transition  -->
Now that you understand the role structures and templates play in creating web content, let's look at how you can use Liferay to manage multiple sites.

## Leveraging Liferay’s multi-site capabilities [](id=lp-6-1-ugen12-sites-0)

As stated in chapter 1, a site is a set of pages that can be used to publish content or applications. Sites can be independent or they can be associated to an organization and serve as the website for that organization.

Liferay's sites can be used for a variety of purposes, from corporate websites to company intranets including small sites to collaborate among members of a team. To support all types of collaboration and social scenarios, Liferay's sites support three types of membership types:

- Private: Users are not allowed to become members of the site. Site administrators can still manually select users and make them members of the site.
- Restricted: Users are allowed to request to become members of the site and site administrators have to aprove the request. The request can be done from the *My Sites* application.
- Open: Users are allowed to become members of the site at any time. This can be done from the *My Sites* portlet.

In addition to these memberships, when a site is associated to an organization, all the users of that organization are automatically considered members of the site.

Members of a site can be given additional privileges within the site by using Liferay's permission settings. It is also possible to assign different roles within the site to different members. This can be done through *site roles* which are defined equally for all sites or *teams* which are unique for each site.

Liferay's Sites can have two hierarchies of pages: public pages and private pages. A site can have only public pages, only private pages or both. The main difference between the two hierarchies is private pages can only be accessed by members of the site. It is possible to restrict access to both of them in finer detail for each page through the permission system. Public pages and private pages are accessed through a different URL and can have a different look and feel but they share the same content.

An example of using sites could be a corporate Intranet which might have sites for all the organizations in the company: Sales, Marketing, Information Technology, Human Resources and so on. But what about the corporate health and fitness center? That's something everybody in the company, regardless of organization, may want to join. This makes it a good candidate for an open and independent site. Similarly, the home page for a corporate intranet should probably be placed in an open independent site so any member of the portal can access it.

For other kinds of web sites, you may want to use independent sites to bring people together who share a common interest. If you were building a photo sharing web site, you might have independent sites based on the types of photos people want to share. For example, those who enjoy taking pictures of landscapes could join a Landscapes site and those who enjoy taking pictures of sunsets could join a Sunsets site. 

Liferay always provides one default site, which is also known as the main site of the portal. This site does not have its own name but rather takes the name of the portal. By default the portal name is *liferay.com* but this value can be changed through the simple configuration of the setup wizard. The portal name can also be changed at any time through the control panel within *Portal Settings*.

---

![tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, there were two ways of creating sites: organizations and communities. This has been simplified to provide more ease of use and allow for more flexibility. The main role of organizations is still to organize the users of the portal in a hierarchy but they can also have associated sites. Communities can still be created through independent sites but the new name reflects the fact that sites can be used for many different purposes besides communities.

---

Sites can be created through the control panel by a portal administrator. To add a site, click the *Sites* link on the left side of the control panel in the Portal section and then click *Add* in the toolbar. If there is at least one site template available, a dropdown menu will be shown allowing you to select a *Blank Site*. Other site templates will appear in the menu as they become available. *Site templates* provide a preconfigured set of pages, applications and content that can be used as the basis of the site.

The following figure shows the form that needs to be filled when creating a *Blank Site*.

![Figure 3.6: Adding a Site](../../images/01-add-site-screen.png)

**Name:** is the name of the site you wish to create.

**Description:** describes the site's intended function.

**Membership Type:** can be open, restricted or private. An open site appears in the My Sites portlet and users can join and leave the site whenever they want. A restricted site is the same except users must request membership. A site administrator must then explicitly grant or deny users' requests to join. A private site does not appear in the My Sites portlet and users must be added to it manually by a site administrator.

**Active:** determines whether a site is active or inactive. Inactive sites are inaccessible but can be activated whenever a site administrator wishes.

Once you've created a site, it appears in the Sites page of the control panel. Once the site has been created you can specify more details about the site using three categories: Basic Information, Search Engine Optimization and Advanced.

![Figure 3.7: Editing a Site](../../images/01-site-editor.png)

**Details:** lets you edit the information you entered when you created the site and allows you to choose a site template for the public or private pages of your site. If you select a site template, leave the *Enable propagation of changes from the site template* box checked to automatically update your site if the associated site template changes. The update will only be done to pages which have not been changed within the specific site. If you uncheck this box but recheck it later, the template pages are then reapplied to your site, overwriting any changes that may have been made. Only users who have the permission "Unlink Site Template" will be able to disable the propagation of changes. When the propagation is enabled, the site template might prevent modification of some or all pages to ensure the propagation occurs.

**Categorization:** allows you to apply categories and tags to the site.

**Site URL:** lets you set friendly URLs and virtual hosts for your web site.

**Site Template:** provides additional information about the site template associated to the pages of the site (if any).

**Sitemap:** lets you use the sitemap protocol to notify search engines your web site is available for crawling. 

**Robots:** lets you use a `robots.txt` file to specify certain pages and links you don't want to be indexed by search engines. You need to set a virtual host before you set a `robots.txt` file.

**Staging:** lets you turn on either Local Live staging or Remote Live staging. To enable staging, the *Enable propagation of changes from the site template* box on the Details tab must be unchecked. With staging enabled, changes to the site template are automatically propagated to the staged site, not to the live site. The changes still must be approved before the site is published to live.

**Analytics:** lets you set a Google Analytics ID that is used for your site. 

When creating a site from a site template, the initial form provides a new option that lets you decide if you want to copy the pages from the template as public pages or as private pages. By default, the site is linked to the site template and changes to the site template propagate to any site based on it. A checkbox appears that allows users to unlink the site template if the user has permission to do so.

![Figure 3.8: When creating a site from a site template, you need to choose whether the site template should be copied into the site's public pages or private pages.](../../images/creating-site-from-site-template.png)

Site templates are very powerful for managing many similiar sites. Let's look further at how they work. 

## Using site templates [](id=lp-6-1-ugen12-site-templates-0)

Site Templates can be administered in the control panel within the portal section of the left menu.

Creating or modifying a site template is done using the same tools used to manage a site. You can use these tools to add a hierarchy of pages. Each page can have any configuration and any number of applications, just like a regular site. When you create a site using a site template, the configuration of pages and applications are copied from the template to the site. By default, all changes made to the site template are automatically copied to sites based on that template.

Site templates can also contain content just like actual sites. This allows you to use a site template to create sample content that appears in your site when it is first created. Changes to a site template's content, however, are not propagated to existing sites that are linked to the site template.

---

![tip](../../images/01-tip.png) **Tip:** If you want to publish a piece of web content to many sites and ensure modifications are applied to all, don't use site template content for that purpose. Instead, place the content in the global scope and then reference it from a *Web Content Display* application in each site.

---

By default, the following site templates are provided:

- **Community Site:** Provides a preconfigured site for building online communities. The home of a *community site* provides message boards, search, a display of a poll and statistics of the activity of community members. The site will also be created with a page for a community calendar and a page for a wiki.

- **Intranet Site:** Provides a preconfigured site for an intranet. The Home page displays the activities of the members of the site, search, a language chooser and a list of the recent content created in the intranet. It also provides 3 additional pages for *Documents and Media*, *Calendar* and external *News* obtained through public feeds.

The following figure displays the form shown when editing the *Community Site* template:

![Figure 3.9: Site Templates](../../images/01-site-templates.png)

To view and manage the pages of a site template, click the *Open site template* link. This opens the template in a new browser window (or tab) and it can be navigated or managed like a regular site..

<!-- | TODO: I would change the example below to not use organizations to simplify things | -->
For example, let's suppose we need to create sites for three suborganizations of the Nosester organization: Engineering, Marketing and Legal. These are to be private sites designed for each organization's internal use. We could design each site separately but we can save ourselves some work if we create a site template to use instead.

To create a site template, navigate to the control panel and click *Site Templates*. Then click *Add* and enter a name for your template: we'll use *Organization Site Template* for our example. Leave the *Active* and *Allow Site Administrators to Modify the Pages Associated with This Site Template* boxes checked. The *Active* box must be checked for your template to be usable. If your template is still a work in progress, you can uncheck it so no one uses it until it's ready. Checking *Allow Site Administrators to Modify the Pages Associated with This Site Template* allows Site Administrators to modify or remove the pages and portlets the template introduces to their sites--if you want the templates to be completely static, you should uncheck this.

Click on the *Open site template* link to begin adding pages and portlets and configuring the layouts. For our example, we would like our template to include four pages: a Home page with the Activities,  Announcements and Calendar portlets, a Documents and Media page with the Documents and Media portlet, a Wiki page with the Wiki and Tag Cloud portlets and a Message Boards page with the Message Boards and Tag Cloud portlets. The changes are automatically saved as you make them, so once you're finished, return to the Site Templates page of the control panel and select *Save*.

![Figure 3.10: You can see the name of the site template you're currently editing](../../images/editing-site-template.png)

Now let's create the Engineering, Marketing and Legal organizations whose sites we want to create with our template. Go to the control panel and click *Users and Organizations*. Then click the *Add* button and select *Regular Organization*. Enter a name for your organization, select the *Organization site* tab and check the *Create Site* box. When you check this box, two drop-down lists appear: one for the site's Public Pages and one for its Private Pages. To use your template to create the site, select the name of your template, *Organization Site*, from the Private Pages drop-down list. Click *Save* to create your site.  You can view the new site by clicking the *Open private pages* link from the newly created organization page. The new site will have all the pages and portlets you created in the template. This feature streamlines the site creation process for administrators, making it easy to create sites quickly. Next, let's discuss how to create and apply page templates.

## Using page templates [](id=lp-6-1-ugen12-page-templates-0)

Page templates function similarly to site templates but at the page level. Page templates provide a pre-configured page to reuse. Within a page template it is possible to set up a theme, a layout and specific applications and their configuration. Both sites and site templates can utilize page templates for creating new pages.

![Figure 3.11: Page Templates](../../images/server-configuration-page-templates.png)

The Page Templates page of the control panel shows a list of templates and lets you create new ones. It also allows you to edit existing templates and configure their permissions. By default three sample page templates are provided:

- Blog: provides a page with three applications related to blogging. It has two columns, the main left column contains the blogs portlet and the small right column provides two side portlets, Tag Cloud and Recent Bloggers. The tag cloud application will show the tags used within the site and will allow navigating through the blog entries shown in the main blogs portlet.
- Wiki: provides a page with three applications related to authoring a wiki. It also has two columns, the main left column with the wiki application and two right side portlets to allow navigating through pages by tags and categories.
- Content Display Page: provides a page preconfigured to display content. It has three auxiliary applications (Tags Navigation, Categories Navigation, and Search) and an Asset Publisher. The most significant aspect of this page is that the Asset Publisher is preconfigured to be display any web content associated with this page. This means that you can select any page created from this page template as a *Display Page* for a web content article. You can choose a display page for a web content article when creating a new web content article or when editing an existing one. When you create a new web content article, a unique (canonical) URL for the web content pointing to this page will be assigned to it.

To add a new page template, click the *Add* button. Then enter a name and description for your template. Leave the *Active* button checked. Click *Save* and then identify your page template in the list. Click its name or use the Actions button to edit the page template. The *Open Page Template* link opens a new browser window which you can use to configure your new page. Any changes you make are automatically saved so you can close the new browser window once you're done.

Note that after a new page template has been created the default permissions are to only allow the creator to use the page template. To give other users access to it, use the actions menu in the list of templates and choose *Permissions*. Once you see the matrix of roles and permissions, check the *View* permission for the role or roles needed to see the page template in the list of available page templates when creating a new page. If you want any user who can create a page to be able to use the page template, just check the *View* permission for the *User* role.

![Figure 3.12: Selecting a Page Template](../../images/control-panel-selecting-page-template.png)

To use your template to create a new page, just navigate to a page over which you have site administrator privileges and select *Add* &rarr; *Page* from the Dockbar. You'll be able to select a page template and type a name for the new page. Alternatively, you can use the control panel. First, in the context selector menu, select the site to which you'd like to add a page and then click on the *Site Pages* link. Then click the *Add Page* button, type a name, select your template from the drop down menu and click *Add Page* to finish.

![Figure 3.13: Choosing whether or not to automatically apply page template changes to live pages](../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates pages based on a page template, any future changes to the template are automatically propagated to those pages. Site administrators can disable this behavior by unchecking the *Automatically apply changes done to the page template* box.

<!-- | COMMENT FOR AUTHOR: IMHO, the following paragraph does not fit here because it is of interest in the context of managing a site, not in the context of managing a site template | -->
If staging has been enabled, changes to the page template are automatically propagated to the staged page. These changes still need to be approved before the page is published to live. For this reason, the automatic propagation of page template changes to the staged page cannot be turned off and the *Automatically apply changes done to the page template* checkbox does not appear.

<!--  another attempt at a transition     -->
We'll discuss staging in more detail later in this chapter. For now let's look at importing and exporting templates.

![EE Only Feature]
### Exporting and Importing Site Templates and Page Templates [](id=lp-6-1-ugen03-exporting-and-importing-site-templates-and-page-templates-0)

If you want to export a site that uses site or page Templates to a different environment (trough a LAR file or remote publication), the Templates must be exported and imported manually in advance or the import will fail.

To export a Site using a Site Template, use the following process:
1.  Go to Control Panel &rarr; Site Templates and click Actions &rarr; Manage Pages for the Site Template your site is using. 
2.  Click *Export* to obtain a LAR file with the content of the Site Template. Be sure to choose the applications and data you want exported. 
3.  In your target environment, go to Control Panel &rarr; Site Templates and create a new Site Template.
4.  Click Actions &rarr; Manage Pages for that Site Template and then click *Import*.
5.  Upload the LAR file containing your site template's content. 

Now the site can be exported and imported normally to this new environment.

For page templates, the process very similar: 
1.  Go to Control Panel &rarr; Page Templates. 
2.  Next to the page template you would like to export, click Actions &rarr; Export. This produces a LAR file you can import later. 
3.  On the target environment, go to Control Panel &rarr; Page Templates and create a new Page Template.
4.  Next to the new template, click Actions &rarr; Import. 
5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to this new environment.

<!-- another attempt at a transition -->
Next, let's examine the tools Liferay provides for handling translations.

##### Localization [](id=lp-6-1-ugen03-localization-0)

Previous versions of Liferay had the ability to create and manage different translations of your web content but with Liferay 6.1 we've added several improvements. 

When you create a new piece of Web Content, you have the ability to choose a default language. If you click *Change*, you can select your default language from a large number of languages Liferay supports. Before you can create a translation, you must finish creating the content in your default language and save it. Once you've done that, editing the content provides you with the option to *Add Translation*.

![Figure 3.14: Adding a translation](../../images/04-web-content-content-translation.png)

After you click *Add Translation*, you can select a language by scrolling through the list or by entering the language you want to use in the search box. When you select a language, a lightbox opens within your browser window enabling you to easily compare the original with the new translation. Once you are done with the translation, click *Save* and the translation is added to the list of *Available Translations*. 

![Figure 3.15: Adding a translation](../../images/04-web-content-content-translation-2.png)

The ability to completely delete a translation in one step has also been added. Instead of simply disabling a translation or having to go through a multistep process to remove it, you can now simply open the translation you don't want and click *Remove Translation*.

When you create a new web content structure, each field you create has a *Localizable* checkbox displayed next to it. This enables you to control what can and can't be changed in the translation process. For example, if you don't want images or content titles to be changed when the content is translated, you can make sure those fields aren't listed as localizable. When you follow the steps above to localize content, only fields within the structure that had the *Localizable* box checked appear within the translation window.

<!-- another attempt at a transition  -->
## Allowing users to customize site pages [](id=lp-6-1-ugen03-advanced-publishing-options-0)

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've gone over Liferay management tools that help you create content quickly and in an orderly fashion. You created a simple announcement with Liferay's structure editor that allows you to quickly design a structure and prepare it for the template designers. Then you applied a template to the structure. You know how to display content using the Web Content Display portlet. Now, you're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become stale and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and possibly inaccurate) web content.

You also want your content to be found easily by your users. This is done through tags and categories. 

Additionally, you may want to create content and send it through an approval and reviewal process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* and *Workflow* features.  

#### Scheduling Web Content [](id=lp-6-1-ugen03-scheduling-web-content-0)

Liferay's WCM lets you define when your content goes live. You can determine when the content is displayed, expired and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form your users access to add web content, in the same column as the structure and template selectors.

![Figure 3.16: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Sets (within a minute) when content will be displayed.

**Expiration Date:** Sets a date to expire the content. The default is one year.

**Never Auto Expire:** Sets your content to never expire.

**Review Date:** Sets a content review date. 

**Never Review:** Sets the content to never be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content. 

<!-- another attempt at a transition -->

Similar to scheduling, Liferay's staging feature also allows you to manipulate time, in a manner of speaking.

## Staging page publication [](id=lp-6-1-ugen03-staging-and-workflow-0)

Staging is an important feature of Liferay WCM. The concept of staging is a simple one: you can modify your site behind the scenes and then publish it all in one shot. You don't want your users seeing your web site change before their eyes as you're modifying it, do you? Liferay's staging environment allows you to make changes to your site in a specialized *staging area*, and when you're finished, publish the whole site to your users.

You can use staging in multiple ways. You can have a staging server—-a separate instance of Liferay Portal which is used just for staging. Content creators can then use this server to make their changes while the live server handles the incoming user traffic. When changes to the site are ready to be published, they are pushed over the network to the live server.

You can also use staging in the same instance of your Liferay Portal. In this configuration, you have a *local* staging environment: you host both your staging environment and your live environment on the same server. Either way the interface is the same, once set up; the only difference comes when it's actually time to publish your content.

In addition, Liferay 6.1 adds the capability to create multiple variations of staged pages, so you can manage several future versions of a site simultaneously. Variations can be merged and published through an intuitive UI. Let's jump in to see how to use staging.

##### Enabling the staging environment [](id=lp-6-1-ugen03-enabling-the-staging-environment-0)

Staging configuration can be found in the Site Settings UI. The Staging tab allows us to make changes in a staging environment and preview our work before publishing it to the live site. Let's create a staging environment for Nose-ster's home page.

First, you'll add a new page. Click *Add &rarr; Page* from the toolbar in the default site and name the new page *News and Events*. Next, click the *View Pages* button and add the Alerts and Announcements portlets to it.

Now we're ready to activate the staging feature for this site. Go to the control panel then to *Site Settings* and select *Staging* from under the *Advanced* heading.

![Figure 3.17: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

We'll assume we don't have a separate staging server so we'll select the staging type *Local Live*. If you want to set up a remote staging environment, it's easy. First select *Remote Live*, then supply the name or IP of the remote server where staged content should be published, the port (80 if Liferay is sitting behind a web server or the port your application server is listening on if not) and the remote site or organization ID. You can find this ID by selecting *Actions &rarr; Edit* on any site in the control panel. Either way, once you make a selection (*Local Live* or *Remote Live*), more options become available.

We'll cover many of the collaboration portlets listed here when we come to chapter 6. For now you just need to be aware the option is available to enable or disable staging for any of them and you need to decide if you want to stage content for these portlets. In the case of the collaborative portlets, the answer is usually "no." Why? Because portlets such as the Message Boards are designed for user interaction. If their content were staged, you'd have to manually publish your site whenever somebody posted a message on the message boards to make that message appear on the live site.

Generally, you'll want web content to be staged because end users aren't creating that kind of content-—web content is the stuff you publish to your site. But portlets like the message boards or the wiki would likely benefit from *not* being staged.

Enabling *Page Versioning* makes it so you can work in parallel with other users on multiple versions of the same pages and it gives you the flexibility to revert easily to a previous version if you encounter any issues. Check *Enabled On Public Pages* so we can look at versioning.

##### Using the staging environment [](id=lp-6-1-ugen03-using-the-staging-environment-0)

If you navigate back to the News and Events page you'll now notice some new items along the top of the screen. These will help us manage staged pages. You'll also notice most of your page management options have been removed, because now you can't directly edit live pages--you'll now use the staging environment to do that. Click on *Staging* to view the staged area. Your management options are restored and you have some new options related to staging.

![Figure 3.18: You can see the new bar staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Calendar portlet and then click on *Live* from the Dockbar. Notice that the Calendar portlet isn't there. That's because you've staged a change to the page but haven't published that change yet to the live site. Go back to the staged page and look at the options you have available. From here you can *Undo* changes, view a *History* of changes, *Mark as Ready for Publication* and *Manage Page Variations*.

**Undo/Redo:** allows you to step back/forward through recent changes to a page, which can save you the time of manually adding or removing portlets if you make a mistake.

**History:** shows you the list of revisions of the page, based on publication dates. You can go to any change in the revision history and see how the pages looked at that point.

**Manage Page Variations:** allows you to work in parallel on multiple versions of a staged page. We will explain this later.

After you're done making changes to the staged page, click the *Mark as Ready for Publication* button. The status of the page changes from *Draft* to *Ready for Publication* and any changes you've made can be published to the Live Site. When you publish a page to live, only the version which was *Marked as Ready for Publication* is published.

The dropdown next to the Staging link at the top gives you the option to *Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** immedatiately pushes any changes to the Live Site.

**Schedule Publication to Live:** lets you set a specific date to publish or to setup recurring publishing. You could use this, for example, to publish all changes made during the week every Monday morning without any further intervention.

Click on *Mark as Ready for Publication* and then *Publish to Live Now* to publish your Calendar portlet to the live site.

Content publication can be also controlled using staging. Calendar events are staged by default (this can be changed in Staging Configuration). If you create an event in the staged site, it isn't visible in the live site until you publish it to the live site following the same steps you just performed (you can select which types of content are published when you publish to the live site). If workflow is enabled for Calendar Events, the event needs to go through the workflow process before it can be published to the live site.

![Figure 3.19: Ready to publish to the live site.  ](../../images/04-web-content-staging-publish.png)

One of the most powerful features of staging is page variations. Let's see how to use them to create multiple different variations of your site's pages for different purposes.

#### Site Pages Variations [](id=lp-6-1-ugen03-site-pages-variations-0)

Let's say you're working on a product-oriented site where you'll have several major changes to a page or a set of pages over a short period of time. Also you need to be working on multiple versions of the site at the same time to ensure everything has been properly reviewed before it goes live. With staging in Liferay 6.1 you can do this using **Page Variations**.

For example, you can create several page variations, enabling the marketing team to give your site a completely different look and feel for Christmas. At the same time, the product management team can work on a different version that will be published the day after Christmas for the launching of a new product. Additionally, the product management team is considering two different ideas for the home page of the site, so they can create several page variations of the home page inside their product launch site.

Variations only affect pages and not the content, which means all the existing content in your staging site is shared by all your variations. In different site page variations you can have different logos, different look and feel for your pages, different applications on these pages, different configuration of these applications and even different pages. One page can exist in just one site page variation or in several of them.

By default, we only have one site page variation which is called **Main Variation**. To create a new one, use the dropdown next to the *Staging* link and click on *Manage Site Pages Variations*. This brings you to a list of the existing site page variations for your site. Click *Add Site Pages Variation* to create a new one. From the *Add Site Pages Variation* screen, you can set a Name, Description and also set your new variation to copy the content from an existing variation. There are several options to choose in this selector. 

**Any existing Site Pages Variation:** creates a new site page variation that contains only the last version of all the pages that exist in this variation. The current variation must be marked as ready for publication. 

**All Site Pages Variation:** creates a new variation that contains the last version marked as ready for publication from any single page existing in any other variation.

**None:** creates a new, empty variation.

You are also able to rename any variation. For example, edit the Main Variation and change its name to something that makes more sense in your site, such as *Basic*, *Master*, *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging menu bar. It's also possible to set permissions on each variation, so certain users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo, apply a new theme, move portlets around, change the order of the pages and configure different portlets. The other variations won't be affected. You can even delete existing pages or add new ones (remember to *Mark as Ready for Publication* when you are finished with your changes). 

When you delete a page, it is deleted only in the current variation. The same happens when you add a new page. If you try to access a page which was deleted in the current variation, Liferay informs you this page is not *enabled* in this variation and you must enable it. To enable it, navigate to the *Manage* &rarr; *Site Pages* screen. Here all the existing pages for all the variations are shown in a tree. Pages not enabled for the current variation are shown in a lighter color.

To publish a variation to the live site, click on *Publish to Live now* in the dropdown next to the variation name. Publications can also be scheduled independently for different variations. For example, you could have a variation called *Mondays* which is published to the live site every Monday and another one called *Day 1* which is published to the live site every first day of each month.

You can also have variations for a single page inside a site page variation, which allows you to work in parallel in different versions of a page. For example, you might work on two different proposals for the design of the home page for the Christmas variation. These page variations only exist inside a site Page variation.

To create a new page variation, click *Manage Page Variations* on the staging toolbar. This brings you to a list of existing page variations for the current page (by default, there is only one called *Main Variation*). You can create more or rename the existing one. You can switch between different page variations using the toolbar containing the page variations below the site pages variations toolbar. When you decide which page variation should be published, mark it as *Ready for Publication*. Only one page variation can be marked as ready for publication and that is the one that gets published to the live site.

![Figure 3.20: Creating a new Page Variation](../../images/04-web-content-add-site-pages-variation.png)

For example, we could create a page variation called Thanksgiving for the News and Events page inside of the Christmas variation and another one called Christmas Day to display different content on those particular days.

![Figure 3.21: The Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations. To merge two Site Pages Variations, you need to go to the Manage Site Variations screen. From there, click on *Merge* on the Site Pages Variation you want to use as the base. You will be asked to choose the Site Pages Variation to merge on top of it. Merging works in the following way:

* New pages that don't exist in the base Variation, will be added.
* If a page exists in both Site Pages variations, and at least one version of the page was marked as ready for publication, then the latest version marked as ready will be added as a new Page Variation in the target page of the base Variation. (Note that older versions or page variations not marked as ready for publication won't be copied. However, merge can be executed as many times as needed and will create the needed pages variations in the appropriate page of the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base Variation, it will just add more versions, pages and page variations as needed.

Let's finish our discussion of staging by outlining a few more features. 

#### Wrapping up staging [](id=lp-6-1-ugen03-wrapping-up-staging-0)

You can enable staging on an individual site basis, depending on your needs. This makes it easy to put strict controls in place for your public web site, while opening things up for individual sites that don't need such strict controls. Liferay's staging environment is extremely easy to use and makes maintaining a content-rich web site a snap. 

Liferay 6.0 introduced a new feature to the permissions system called teams. Let's examine them next.

## Creating teams for advanced site membership management [](id=lp-6-1-ugen12-teams-0)

Teams don't appear as a link in the control panel because they exist *within* sites. Teams allow site administrators a greater degree of flexibility than was possible using just user groups and roles. They allow site administrators to create various sets of users and permissions for site-specific functions. Teams are the preferred method for collecting permissions within a single site. 

If you create a team for one site, the permissions defined for it are not available to any other sites. In contrast, if you assigned a custom role to a user group, the role would be available portal-wide even though the specific permissions defined by it would only apply within the scope of a designated site. Furthermore, team members, unlike user group members, are guaranteed to be members of the desired site.

To create a team within a site, first naviagte to the *Control Panel &rarr; Sites* page then and then select *Actions &rarr; Manage Memberships* for the site within which you want to create a team. Finally, click *View &rarr; Teams* and click the Add Team button.

![Figure 3.22: Creating a Team within a Site](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description, click *Save*. Your new team will appear in the list. To add members, simply click on *Actions &rarr; Assign Members*.

Permission management for teams is handled at the individual portlet level, using the *Options &rarr; Configuration &rarr; Permissions* tab of the portlet itself. Remember the portlet options link is the wrench symbol at the top of a portlet. This enables users who wouldn't have access to all of the necessary options in the control panel to manage permissions through teams.

![Figure 3.23: Assigning Portlet Permissions to a Team](../../images/01-assigning-portlet-permissions-to-teams.png)

To give a team access to a particular portlet function, access the *Permissions* tab of a portlet residing on a page, check the boxes corresponding to permissions you want to assign to the teams, then click *Save*. That's it! Now your team is ready to perform their functions. Next, let's look at how to configure Liferay for mobile devices.
  
## Displaying site pages to mobile devices [](id=lp-6-1-ugen03-mobile-device-rules-0)

Mobile device rules allow you to configure sets of rules to alter the behavior of the portal based on the device being used to access Liferay. The proportion of mobile devices users browsing the web has been steadily increasing so it's important to be able to handle different kinds of devices appropriately. For instance, you can configure the look and feel of Liferay pages accessed by smartphone or tablet users differently than those accessed by PC users. 

Both sites and individual pages can be configured with any number of rule groups. A rule group is designed to describe a group of devices. It can contain one or more rules which describe a category of devices, such as all android devices or all iOS tablets. Rule groups can be prioritized to determine which one will be applied to a given page request. You can define as many rules as you need to classify all the devices you want to take actions on.

To configure mobile device rules, you must install the wurlf-web plugin. This plugin enables Liferay to detect which mobile device or operating system is being used for any given request. To install the plugin, navigate to the Plugins Installation section of the control panel, located under the Server heading. Then search for the wurlf-web plugin and click *Install*. 

Alternatively, if you have a working copy of Liferay's plugin repository, you can install the plugin manually. The plugin is available from Liferay's public SVN repository at [http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/](http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/) or from Liferay's public Github repository at [https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web](https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web). Once you have checked out or cloned the repository, navigate to the plugins root directory and create a `build.username.properties` file. Add the line `app.server.dir = <path-to-app-server>`, replacing `<path-to-app-server>` with the path to the application server running Liferay on your machine. Then navigate to the wurlf-web directory and execute the ant deploy target.

You can access the Mobile Device Rules administrative page from the Content section of the control panel. Select the appropriate scope using the context menu selector so your rule groups are available where you expect them to be. The Mobile Device Rules administrative page displays a list of defined rule groups and allows you to add more. To add rules to a rule group, select *Actions* &rarr; *Manage Rules* or just click on a rule group to edit it and then click the *Manage Rules* link.

![Figure 3.24: You can manage device rules from the Mobile Device Rules administrative page.](../../images/mobile-device-rules.png)

The rules defined for a rule group, along with the priorities of the rule groups selected for a particular site or page, determine which rule will be applied to a given request. From the Manage Rules page for a specific rule set, you can add a rule by specifying a rule type. Note that, by default, only the Simple Rule type is available. However, the rules are designed to be extensible and additional rule types can be added by your developers. Once added, you can edit the rule to specify a device type and operating system.

![Figure 3.25: You need to install the wurlf-web plugin to populate the OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device rule groups and added some rules to them, you'll be ready to set up some actions. The actions defined for a rule group determine what happens to a particular request when the request's device has been detected and the rule group has been found to apply. 

You can add actions to a rule group from the Site Pages page of the control panel. Select either the public or private pages and then look for the *Mobile Rule Groups* link in the right-hand menu. Use the *Select Rule Group* button to select rule groups to be applied either to a site or to a single page. If you select the page group itself from the left-hand menu, the selected rule group will apply to all the pages of the site by default. However, if you select an individual page and then click the the *Select Rule Group* button, the rule groups will apply to only that page. You can select multiple rule groups for a particular site or page and order them by prority. The rule groups are checked in decreasing order of priority: the actions defined by the first rule group that applies are executed.

![Figure 3.26: You can select a mobile device rule group to apply for a site or page from the Site Pages section of the control panel.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage Actions* button and then click *Add Action*. By default, there are four kinds of actions that can be configured for mobile rule groups: layout template modifications, theme modifications, simple redirects and site redirects. Layout template modifications let you change the way portlets are arranged on pages delivered to mobile devices and themes modifcations let you select a specifc look and feel. If it makes more sense for you to create separate mobile versions of certain sites or pages, you can use a redirect to make sure mobile devices users get to the right page. To define a simple redirect, you need to specify a URL. To define a site redirect, you only need to specify the site name and page name of the page you're redirecting to. Like mobile device rules, mobile device actions are designed to be extensible. Your developers can define custom actions in addition to the four actions provided by default. 

## Summary [](id=lp-6-1-ugen03-summary-0)

This chapter has been your guide to Liferay site management and advanced Web Content Management. We've seen how you can use Liferay to manage both simple content and advanced content with structures and templates. We've learned how you can use Liferay to create multiple sites with different membership types. We've also learned how to use page and site templates to simplify the site creation process.

Liferay WCM also includes a powerful staging environment, allowing you to stage content locally on the same server or remotely to another server. You can publish your site when you want it, on the schedule you choose. You can even create different variations of your site that can be worked on simultaneously. 

You saw how to allow users to create personal customizations of site pages. We discussed how site administrators can create teams as a flexible means of delegating site permissions. We also saw how to configure mobile device rules so that site pages are presented differently depending on the device making a page request.

Whether your site is small and static or large and dynamic, Liferay's WCM enables you to plan and manage it. With tools such as the WYSIWYG editor, structures and templates, you can quickly add and edit content. With the Web Content Display and Asset Publisher, you can rapidly select and configure what content to display and how to display it. By using Liferay's integrated workflow, you can set up custom publishing rules to fit your organization. And by using Liferay's staging and scheduling mechanisms, you can manage various branches of pages and content and control when they are published to your live portal instance. You will find that managing your site becomes far easier when using Liferay's Web Content Management system.
