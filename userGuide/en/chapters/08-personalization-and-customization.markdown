# Personalization and Customization [](id=personalization-and-customization-liferay-portal-6-2-user-guide-07-en)

In this chapter, we discuss several ways Liferay users can customize pages,
applications, and the way they use your portal. We'll cover the following
topics:

- Personal Sites
- Customizable Pages and Applications
- Application Display Templates
- Using a Rules Engine

Personal sites allow each portal user to manage and customize a set of public
and/or private pages and any associated content or applications. Public pages
provide a means of making content publicly available. Private pages provide a
means for users to create content and use applications that should be hidden
from other users. Liferay 6.1 introduced the concepts of customizable pages and
applications. Administrators can designate certain pages or applications as
"customizable," which allows each user to make and save their own
customizations. Portlet layouts can also be customized with the use of
application display templates. Application display templates allow a portlet's
look and feel to be completely customized. Liferay Enterprise Edition provides a
rules engine which allows administrators to create custom portal rules and
simplify complex blocks of code containing lots of `if-else` statements. Let's
start by discussing personal sites.

## User Personal Sites [](id=user-personal-sites-liferay-portal-6-2-user-guide-07-en)

By default, newly created users in Liferay are each granted a personal site.
Each user functions as the site administrator of his or her personal site.
Personal sites are fully customizable but cannot have more than one member. The
public pages of personal sites provide a space for users to add content and
applications that they'd like to make accessible to anyone, including guests.
User blogs are often placed on public personal site pages. Content and
applications that users would like to reserve for personal use are often placed
on the private pages of personal sites. For example, each user can add a
Documents and Media portlet to his or her private pages and use it as an online
private file repository.

If you'd like to disable personal sites for your portal, just add the following
properties to your `portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false
    
---

 ![Tip](../../images/tip.png) Note that the public and private page sets of
 personal sites are handled separately. You can leave one page set enabled while
 disabling the other.

---

What if you initially had user personal sites enabled for your portal but then
disabled them? Each existing user's personal site remains on your portal until
the next time they log in, at which point it's removed.

You can allow users to create personal sites but not have them automatically
created for new users. To do this, first make sure that
`layout.user.public.layouts.enabled` and `layout.user.private.layouts.enabled`
are not set to `false`. You don't need to explicitly set them to `true`--`true`
is the default. Then add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false
    
If the properties `layout.user.public.layouts.enabled`,
`layout.user.private.layouts.enabled`, `layout.user.public.layouts.auto.create`,
and `layout.user.private.layouts.auto.create` are all set to `true`, which is
the default, then users will have personal sites and public and private pages
will be automatically created for new users. There are number of portal
properties you can use to customize the automatically created pages. You can
customize the names of the default pages, the portlets that appear on the pages,
the themes and layout templates of the default pages, and more. Please refer to
the Default User Public Layouts and Default User Private Layouts sections of
the `portal.properties` file for details. You can find an HTML version of the
this file here: [http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).

---

 ![Tip](../../images/tip.png) Prior to Liferay 6.1, administrators could
 disallow users from being able to modify the pages and portlets of their
 personal sites by setting the following properties:

    layout.user.public.layouts.modifiable=true
    layout.user.private.layouts.modifiable=true
    
As of Liferay 6.1, this property is obsolete. However, you can customize the
modifiable portions of personal sites through Liferay's permissions system by
removing permissions from roles. To disallow all portal users from modifying
something, remove the relevant permission from the User role.

---

Historically (prior to Liferay 5.1), only power users received personal sites.
Back then, they were called personal communities. If you'd like only power users
to receive personal sites, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.power.user.required=true
    layout.user.private.layouts.power.user.required=true
    
Personal sites are a dynamic feature of Liferay Portal. They allow users to
manage and customize their own pages and content on your portal. Next, let's
look at how users can customize applications.

## Page Customizations [](id=page-customizations-liferay-portal-6-2-user-guide-07-en)

Liferay 6.1 introduced the concept of page customizations. Administrators can
designate public pages or sections of public pages to be customizable. When a
user visits such a page, a notification appears stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, site members can make page customizations but
non-site members and guests can't.

To enable page customizations as an administrator, first navigate to the page
you'd like to let site members modify. Then click on the *Edit* button at the
left side of the page, expand the *Customization Settings* area, and click on
the *Show Customizable Sections* button. 

![Figure 7.1: To enable page customizations, click on the *Edit* button at the left side of the page, expand the *Customization Settings* area, and click on the *Show Customizable Sections* button.](../../images/page-customizations.png)

Once you've clicked the *Show Customizable Sections* button, you'll see one or
more red regions, depending on the layout template of your page. Check one or
more of the *Customizable* boxes to allow site members to customize certain
sections of the page. Regions that you've designated as customizable are colored
green.

![Figure 7.2: Check one or more of the *Customizable* boxes to allow site members to customize certain sections of the page.](../../images/customizable-regions.png)

When site members visit your customizable page, they'll see a notification
saying, "You can customize this page." Site members can toggle between viewing
their customized page and viewing the default page by clicking the *View Default
Page* or *View My Customized Page* links just below the Dockbar. There's also a
*Reset My Customizations* link that restores a user's customized page to the
match the default page. This allows users to discard one set of customizations
and start a new set without having to manually undo each customization that
they'd previously made.

Note that non-administrator site members can access the Add menu from the left
side of the screen when viewing their customizable page even if they don't
ordinarily have permission to view this menu. This allows them to add portlets
to the sections of the page that they're allowed to customize. If they click
*View Default Page*, the Add menu will disappear from the Dockbar since they're
not allowed to modify the default page.

![Figure 7.3: Non-administrator site members can customize their own versions of customizable pages but can't modify the default page.](../../images/default-customizable-page.png)

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page* view. Changes made to the
*default page* affect all users, whereas changes made to the *customized page*
affect only the administrator who made the changes. Changes made by
administrators to non-customizable sections in the *default view* are
immediately applied for all users. However, changes made by administrators to
customizable sections do *not* overwrite users' customizations.

Users can make two kinds of customizations to customizable regions. First, they
can configure portlet applications within the customizable regions. Second, they
can add portlets to or remove portlets from the customizable regions.

The portal doesn't allow users to change a non-instanceable portlet's
configuration inside a customizable region since those kinds of portlets are
tied to the site to which they've been added. If this were allowed, the
customization would affect all users, not just the one who customized the
region. Therefore, changes to the portlet configuration in a customizable region
are only possible for instanceable portlets, whose portlet configuration only
affects that one user.

For example, suppose that you, as an administrator, selected the right column of
the Welcome page of the Lunar Resort site to be customizable. A member of the
Lunar Resort site could take the following steps to make a personal
customization of the Welcome page:

1. Navigate to the Welcome homepage by clicking *Go To* &rarr; *Lunar Resort*
   from the Dockbar.
2. Remove the Hello World portlet from the right column of the page.
3. Add the Language portlet to the right column of the page by clicking *Add*,
   clicking on *Applications*, searching for *Language*, and clicking *Add* next
   to its name.
4. Configure the Language portlet by clicking on the gear icon and selecting
   *Configuration* and then opening the *Display Style* dropdown menu and
   choosing *Select Box*.

The Language portlet is useful to have on your portal's homepage if you expect
users who speak different languages to access your portal. Users can select
their language in the Language portlet to view a translation of the portal into
their native language. After closing the Configuration dialog box of the
Language portlet, the customized Welcome page looks like this:

![Figure 7.4: In this example, the user removed the Hello World portlet, added the Language portlet, and changed the display style from icons to a select box.](../../images/customized-portal-homepage.png)

<!-- This section needs updating for 6.2. The following two paragraphs'
statements about the required permissions and roles seem to be obsolete. -->

To allow users to customize a page, administrators must grant users permission
to *Customize* pages under the Site section. This can be achieved by assigning
permission to a role, then assigning this role to the appropriate users. For
example, if we want any logged user to be able to customize our customizable
pages, we could assign the *Customize* permission to the role *User*. If we want
site members to be able to customize the customizable pages of their sites, we
would accept the default setting. By default, the *Customize* permission is
assigned to the role *Site Member*.

In addition to granting the ability to customize portlet configurations, the
*Customize* permission allows users to customize the look and feel of portlets
and to import or export portlet settings. Next, let's look at how to use
application display templates.

## Using Application Display Templates [](id=using-application-display-templates-liferay-portal-6-2-user-guide-07-en)

The application display template (ADT) framework allows portal administrators to
override the default display templates, removing limitations to the way your
site's content is displayed. With ADTs, you can define custom display templates
used to render asset-centric applications. For example, you may want to show
blog entries horizontally instead of vertically, or list your assets in the
asset publisher portlet in different sizes.

Let's go through a simple use case to illustrate how creating a custom ADT can
improve your site. Suppose you're customizing the Lunar Resort site and want to
allow users to communicate with other interested travelers. Specifically, you
want to configure the Wiki portlet for collaboration with Facebook or Twitter.
With ADTs, you can launch a template editor, create a custom template, and
configure your portlet host that template. Custom templates let you re-skin your
portlet and give you ultimate control over its appearance and functionality in
your portal.

Before attempting to change the ADT for your application, you'll need to select
a site for your custom template to reside in. Choosing the *Global* context
makes your template available across all sites. To choose a site to house your
ADT, navigate to the Control Panel &rarr; *Sites* and click on a site from the
list. If you select the Global context, the *Application Display Templates* page
of the Control Panel's Configuration Menu shows you a list of sample templates
available for your portlets. These sample templates differ from the default
templates already configured in the portlets. If you choose a site to host your
template, you must create a custom template for that site's portlets.

![Figure 7.5: In the Control Panel, you can choose the context in which your application display template resides.](../../images/context-selector.png)

If you'd like to add an ADT, select the portlet you'd like to customize. The
list below specifies the portlets that can be customized using ADTs. It also
provides sample template descriptions:

- *Asset Publisher*: displays abstracts, icons, related assets, and print/edit
  actions for assets. Optionally includes asset bookmarks and ratings.
- *Blogs*: displays titles, authors, and abstracts compactly for blog entries.
- *Categories Navigation*: displays a column for each vocabulary. Each column
  includes the name of a vocabulary with the vocabulary's top level categories
  listed underneath.
- *Media Gallery*: displays images in a carousel.
- *Site Map*: displays a column for each top level page. Each column includes
  the name of a top level page with the page's immediate children listed
  underneath.
- *Tags Navigation*: displays asset tags colored by popularity: red (high),
  yellow (medium), and green (low).
- *Wiki*: displays social bookmarks and ratings for wiki pages and their child
  pages.

To create a new ADT, click *Add* and select the template you'd like to create,
based on portlet type. Then enter the name and, optionally, a description and a
small image to use. You can select the language type for your template (FTL or
VM). Lastly, the *Script* option lets you browse your file system for a template
on your file system, or you can launch the editor and create one directly. On
the left side of the template editor, you'll notice a palette of common
variables used for making templates. This is a great reference when creating
your template. To place one of the variables into the template editor, simply
position your cursor where you want it placed, and click the variable name.

Think it can't get any better? Guess again! If the variable name doesn't give
you enough information on the variable's functionality, you can hover your
pointer over it for a more detailed description. Because there are multiple
kinds of ADTs, there are also different variables for each ADT. Thus, each
template has a different set of variables only applicable for that specific
template. 

![Figure 7.6: Liferay offers a versatile script editor to customize your ADT.](../../images/adt-script-editor.png)

You can also use the autocomplete feature to add variables to your template. It
can be invoked by typing *${* which opens a drop-down menu of available
variables. By clicking one of the variables, the editor inserts the variable
into the editor.

You also have the ability to embed same-type templates into other templates. For
example, suppose you have an existing Wiki ADT and would like to create another
similar Wiki ADT. Instead of starting from scratch, you can import the existing
Wiki ADT into your new one and build off of it. In other words, you can utilize
ADTs as generic templates which allow for reusable code to be imported by
Velocity or FreeMarker templates in the system. For more information on how to
create a custom template, visit the [Liferay Developer's Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development).

Another cool feature is the *Export/Import* functionality. You can take
advantage of this feature by clicking the gear icon at the top right of the
screen and selecting *Export/Import*. For more information on using this
feature, visit the
[Export/Import](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/export-import-liferay-portal-6-2-user-guide-04-en)
section of this guide.

After you've completed the initial set up and saved your ADT, you can manage
your ADT through its *Actions* button. This provides several options:

- *Edit*: lets you modify the ADT's setup properties.
- *Permissions*: lets you manage the permissions *Delete*, *Permissions*,
  *Update*, and *View* for the ADT.
- *Copy*: creates a copy of the ADT.
- *Delete*: deletes the ADT.

Additionally, your ADT generates a static URL and a WebDAV URL. These values
access the XML source of your template. You can find these URLs by clicking the
ADT from the menu and expanding the *Details* section. With the WebDAV URL, site
administrators are capable of adding, browsing, editing, and deleting ADTs on a
remote server. If you'd like to learn more about what the WebDAV URL can do,
visit the *Document Management* chapter's *WebDAV access* chapter.

To enable your ADT for a portlet, navigate to the portlet you want to modify and
open its *Configuration* menu. In the *Display Settings* sub-tab located within
the *Setup* tab, select your ADT from the *Display Template* drop-down menu.
You'll notice they're separated by context type. Also, you can manage
site-specific display templates for your portlet: do this by clicking the
*Manage Display Templates for [SPECIFIC_SITE]* link next to the *Display
Template* drop-down menu. A window will display with a list of your configured
templates only available for your site with options to add new templates or edit
existing templates.

![Figure 7.7: In the *Configuration* menu of a portlet, you can edit and manage available ADTs.](../../images/adt-configuration.png)

Now that you know the general functions of ADTs, let's create our own. This
brief demonstration will show you just how easy, yet powerful, ADTs can be for
your portal.

1. Add the Media Gallery portlet to a page by navigating to *Add* &rarr;
   *Content and Applications* &rarr; *Applications* &rarr; *Content Management*
   &rarr; *Media Gallery*.

2. Select the *Options* gear from the top right corner, then click
   *Configuration*.

3. Enable the *Show Actions* and *Show Folder Menu* display settings. Then click
   *Save* and close the popup.

4. Click the *Multiple Media* link and select two custom photos to display. Then
   click *Save*, and navigate back to the main portlet screen.

5. Notice the default format of the pictures. To change the display template for
   this portlet, navigate back to the *Options* gear and click *Configuration*.

6. From the *Display Template* drop-down menu, select *Carousel*. Then click
   *Save*.

	![Figure 7.8: After applying the Carousel ADT, your pictures are displayed as a carousel slideshow.](../../images/adt-carousel.png)

	The Media Gallery portlet is transformed into a carousel slideshow. At this
	time, it's perfectly natural to be experiencing "I can conquer the world"
	feelings, just as Liferay's mascot, Ray, exudes in the image above. ADTs
	have that kind of power to transform your site into an enjoyable and
	convenient home for users.

Customizing the user interface of Liferay's bundled portlets provides the
ultimate customization experience for Liferay users. Next, let's look at how to
use Liferay's rules engine.

## Using Liferay's rules engine [](id=using-liferays-rules-engine-liferay-portal-6-2-user-guide-07-en)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal Enterprise Edition provides an implementation of a JSR-94
compliant rules engine. This rules engine is provided as a Web Plugin and is
based on the popular open source Drools project. 

### Why use a rules engine? [](id=why-use-a-rules-engine-liferay-portal-6-2-user-guide-07-en)

If you are not familiar with rules engines, you may be wondering why you would
want to use one. In most applications, complex rule processing often takes the
form of nested `if-else` blocks of code which can be very difficult to decipher
and to maintain. If rules change, a developer must work with a business user to
define the new rules. The developer must then read through the existing logic to
understand what is happening and make the necessary modifications. The changes
must then be recompiled, tested, and redeployed. A rules engine provides a means
to separate the rules or logic of an application from the remaining code.
Separating these rules provides several distinct advantages. 

- A rules engine allows for a more declarative style of programming where the
  rules define what is happening, without describing how it is happening. This
  makes it much easier to read than nested 'if-else' blocks of code. It's also
  easier to make changes without introducing bugs in your code. 

- The rules are written in a language that is easier for non-developers to
  understand. This makes it easier for business users to validate and even
  modify the rules without having to involve developers.  

- A rules engine allows for changes to be made to the rules without requiring
  that you recompile your application. If your code must pass through a strict
  deployment workflow, this can be a huge time saver and can also save a
  significant amount of money. 

After all this, you may be interested in using Liferay's rules engine, so let's
get started with it. 

### Installation [](id=installation-liferay-portal-6-2-user-guide-07-en)

The Drools web plugin is available to Liferay Enterprise Edition customers
through Liferay Marketplace. It's called the Drools EE app and you'll find it
categorized as a Utility app.

The Drools web plugin provides a rules engine implementation. By itself, it
doesn't provide any observable changes to the portal user interface or any
additional functionality. To see the rules engine in action, you can download
and install the Sample Drools portlet that contains two rule definitions that
illustrate how to leverage the rules engine in your custom code. The Sample
Drools portlet is available through the Customer Portal.

Let's examine the sample portlet to see how it works. 

#### Configuring the Sample Drools Portlet [](id=configuring-the-sample-drools-portlet-liferay-portal-6-2-user-guide-07-en)

Begin by downloading and installing the Sample Drools portlet. The Sample Drools
portlet is available to Liferay Enterprise Edition customers through the
customer portal. The name is `sample-drools-portlet`, and you'll find it in the
list of web plugins.

After installation is complete, log in as an administrator and add the portlet
to a page. Initially, the portlet indicates the name of the currently logged in
user and a message stating that there are no results. To see results in the
portlet, we need to create and tag assets in the site to which you added the
portlet. 

From the Dockbar, click *Admin* &rarr; *Content* and create a new web content
instance in your site. Before publishing the web content instance, tag the
article with *americas*. Then, navigate to *My Account* from the
Dockbar and click on the *Addresses* link on the right side of the screen. Enter
a Brazilian, Canadian, Mexican, or US based address and click *Save*. Navigate
back to the page with the Sample Drools portlet. The Sample Drools portlet
should now be displaying the web content instance that you created.

The default rule that's being evaluated displays a list of assets based on the
current user's address. For example, if the current user's country is set to
Brazil, Canada, Mexico, or the United States, the Sample Drools portlet displays
a list of assets that have been tagged with the *americas* tag.

![Figure 7.9: By default, the Sample Drools portlet returns personalized content based on the addresses set on the form for editing a user account. This form is accessible from the Dockbar via *\[User Name\]* &rarr; *My Account*. Users with addresses in Brazil, Canada, Mexico, or the United States will see a list of assets tagged with the "americas" tag in the Sample Drools portlet.](../../images/sample-drools-portlet.png)

<!-- | TODO: We need to point to what we're about to do before we do it;
otherwise this is hard to follow. | -->

<!--
TODO: The rules in the `sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl` file need to be updated to be compatible with API changes. See the fix for LPS-40820 for an example of how this should be done. -Jesse, 10/1/2013
-->

<!--
The Sample Drools portlet plugin also contains a second rule that returns
personalized content based on the user's net worth set in the *My Account*
&rarr; *Custom Fields* section of the Control Panel. To see this rule in action,
add a second instance of the Sample Drools portlet to a page. Once added to the
page, select the *Options* icon (the gear) and then select *Configuration*. You
need to replace the rules defined in the *Rules* section of the Configuration
screen with contents of the *rules_user_custom_attribute_content.drl* file. The
rule file can be found in the deployed portlet at
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl`.
In the same Configuration screen, add `networth` to the
`user-custom-attribute-names` field. Save your changes and close the pop-up
window. Navigate to the Control Panel and add a custom field to the User object
with the key `networth`. Navigate to *My Account* and click on the *Custom
Fields* link on the right side of the screen. Enter a net worth of 150000 and
save the record. Next, add a new web content instance to the default
site of your portal. Before publishing the web content, tag the instance
with *high net worth* and then save the entry. Now, navigate back to the
default site and the web content should be displayed in the second Sample Drools
portlet added to the page.
-->

Now that you've seen how the Sample Drools portlet works, let's take a look
closer at the rules themselves. 

#### Rules Definitions [](id=rules-definitions-liferay-portal-6-2-user-guide-07-en)

Rule definitions can be written using Drools' declarative language. Rule files
are text files that often have a `.drl` extension. A rule file can contain
multiple rules. In addition to the standard Drools' declarative language, a
domain specific language (DSL) can be created for your specific problem domain.
<!-- | TODO: Explain. | --> Creating a DSL can make your rules even easier for
business users to create and maintain your applications rules but does require
some additional work up front. For additional information on creating a DSL for
your problem domain please refer to the Domain Specific Languages section of the
official Drools Documentation at
[http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217](http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217). 

To see examples of a rules definition file, access the following directory in
the Sample Drools portlet
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies`. To
see how rules work in action, we'll look at the rule defined in
`rules_user_address_content.drl`. 

At first glance, this `.drl` file looks a lot like a Java class file.  This
example starts with a comment describing the rule. Single line comments can
begin with either a `*#*` or `*//*` and multi-line comments begin with `_/*_`
and end with `_*/_`. 

    ##
    ## Rules
    ##
    ## This sample program will return personalized content based on the user's
    ## addresses set in the My Account section of the Control Panel.
    ##
    ## For example, suppose the current user has an address in the United States and
    ## is a member of the Liferay site. All assets within the Liferay site
    ## that are tagged with "West Coast Symposium" will be returned.
    ##

Following the comments is a `package` declaration. The package declaration is
optional in a Drools, but if it appears, it must be at the beginning of the
file. The package denotes a collection of rules. Unlike Java, the package name
does not represent a folder structure; it's only a unique namespace. The `;` at
the end of the package declaration and all other statements is optional. 

    package com.liferay.sampledrools.dependencies;

After the package declaration are a series of `import` statements. Import
statements in the rule file are used just like the import statements in Java
classes. Classes that are imported can be used in the rules. 

    import com.liferay.portal.kernel.search.BooleanClause;
    import com.liferay.portal.kernel.search.BooleanClauseOccur;
    import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
    import com.liferay.portal.kernel.search.BooleanQuery;
    import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
    import com.liferay.portal.kernel.search.Document;
    import com.liferay.portal.kernel.search.FacetedSearcher;
    import com.liferay.portal.kernel.search.Field;
    import com.liferay.portal.kernel.search.Hits;
    import com.liferay.portal.kernel.search.Indexer;
    import com.liferay.portal.kernel.search.SearchContext;
    import com.liferay.portal.kernel.search.facet.AssetEntriesFacet;
    import com.liferay.portal.kernel.search.facet.Facet;
    import com.liferay.portal.kernel.search.facet.ScopeFacet;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.util.KeyValuePair;
    import com.liferay.portal.kernel.util.LocaleUtil;
    import com.liferay.portal.kernel.util.StringUtil;
    import com.liferay.portal.kernel.util.Validator;
    import com.liferay.portal.model.Address;
    import com.liferay.portal.model.Group;
    import com.liferay.portal.model.Contact;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.AddressLocalServiceUtil;
    import com.liferay.portal.util.PortalUtil;
    import com.liferay.portlet.asset.model.AssetEntry;
    import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
	
The next line declares the `dialect` for the package. In this case, we will be
using Java for any of the code expressions that we'll encounter. The other
possible value for the dialect is `MVEL`, which is a powerful expression
language for Java-based applications. See
[http://mvel.codehaus.org/](http://mvel.codehaus.org/) for details. If
necessary, the dialect can also be specified at the rule level to override the
package level dialect.

        dialect "java"
	
In this rule file, we have only a single function, which is listed next.
Functions allow you to insert Java code that can be evaluated at run time into
your rule file. Functions are commonly used as a as part of the consequence
clause of the rule statement. Functions are similar to Java methods but you use
the *function* keyword to define a function. The function's parameters and the
return type are declared as they would be in a Java method. In this example, the
`getAssetEntries` function returns a `java.util.List` object that contains
`AssetEntry` objects based on the `user`, `classNameId`s, and `assetTagName`
provided in the function call. 

    function List getAssetEntries(
        User user, long[] classNameIds, String assetTagName) {

        List<Group> groups = user.getMySites();

        long[] groupIds = new long[groups.size()];

        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);

            groupIds[i] = group.getGroupId();
        }

        if ((classNameIds == null) || (classNameIds.length == 0) ||
            (groupIds.length == 0) || Validator.isNull(assetTagName)) {

            return Collections.emptyList();
        }

        SearchContext searchContext = new SearchContext();

        Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);

        assetEntriesFacet.setStatic(true);

        searchContext.addFacet(assetEntriesFacet);

        Facet scopeFacet = new ScopeFacet(searchContext);

        scopeFacet.setStatic(true);

        searchContext.addFacet(scopeFacet);

        searchContext.setAttribute("paginationType", "regular");
        searchContext.setCompanyId(user.getCompanyId());
        searchContext.setGroupIds(groupIds);
        searchContext.setIncludeStagingGroups(false);
        searchContext.setScopeStrict(false);
        searchContext.setUserId(user.getUserId());

        BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(searchContext);

        booleanQuery.addExactTerm(Field.ASSET_TAG_NAMES, assetTagName);

        BooleanClause booleanClause = BooleanClauseFactoryUtil.create(
            searchContext, booleanQuery, BooleanClauseOccur.MUST.getName());

        searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});

        List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

        Indexer indexer = FacetedSearcher.getInstance();

        Hits hits = indexer.search(searchContext);

        for (int i = 0; i < hits.getDocs().length; i++) {
            Document document = hits.doc(i);

            String entryClassName = document.get(Field.ENTRY_CLASS_NAME);

            long entryClassPK = GetterUtil.getLong(
                document.get(Field.ENTRY_CLASS_PK));
            long rootEntryClassPK = GetterUtil.getLong(
                document.get(Field.ROOT_ENTRY_CLASS_PK));

            if (rootEntryClassPK > 0) {
                entryClassPK = rootEntryClassPK;
            }

            AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
                entryClassName, entryClassPK);

            if (assetEntry != null) {
                assetEntries.add(assetEntry);
            }
        }

        return assetEntries;
    }

Alternatively, this function could've been written in a helper class and then
imported using a *function import*. So if we had created a helper class called
`AddressContentHelper` the import would look like this: 

        import function
        com.liferay.sampledrools.dependencies.AddressContentHelper.getAsetEntries;

The last section of the rules file contains the actual rules. The syntax of a
rule is very straightforward. 

        rule "name" attribute when condition then consequence end

The rule name is required and it must be unique within the package as declared
above. Names with spaces must be enclosed in double quotes. It is considered a
best practice to always enclose your rule names in double quotes. 

        rule "Initialize Rules"
 
The attributes section is optional. In our first rule, we have a single
attribute called `salience`. The salience attribute is an integer value that
acts as a priority weighting. The number can be positive or negative and
defaults to the value of `0`. Rules with a higher salience value are given a
higher priority. It is considered a best practice to write attributes one to a
line. In our example, the first rule is one that should be evaluated before any
other so it is given a high salience value of 1000. None of our other rules have
a salience attribute set, so they all default to a value of `0`.

        salience 1000

The `when` keyword marks the conditional section of the rule. It is also
referred to as the Left Hand Side (LHS). The `when` keyword is followed by zero
or more condition elements that must be met before the consequence will be
called. If there are no condition elements, then the condition is always
evaluated as true. 

The most common type of conditional element is a *pattern*. Patterns match
against *facts*. Facts are statements that are known to be true. Facts can be
inserted by the Java code that invokes the rules engine or they can be declared
in the rule file itself. 

In the first rule of our rule file (`Initialize Rules`), the only condition is
that the rule must operate on a User object. 
		
	when user : User();

In more complex rules, the pattern may include constraints or may evaluate the
properties of Java objects.  For example, the second rule of this rule file is
called *Get European Content*. This rule includes the following pattern which
ensures that a user's address contains the country name France, Germany, or
Spain. 

	userAddress : Address(country.getName(Locale.US) in ("France", "Germany", "Spain"));

The consequence section of the rule follows the conditional section. It's also
known as the Right Hand Side (RHS) or action part of the rule. The consequence
section begins with the keyword `then` and it is intended to modify the working
memory data. Drools provides some convenience methods that make it easier to
modify the working memory. In this rule, we use the `insertLogical` method which
places a new object into the working memory and retracts it when there are no
more facts supporting the truth of the current rule. After the consequence
section of the rule, the rule is terminated with the keyword `end`. 

	then List<Address> userAddresses = AddressLocalServiceUtil.getAddresses(
            user.getCompanyId(), Contact.class.getName(), user.getContactId());

        for (Address userAddress : userAddresses) {
            insertLogical(userAddress); } end

Following the initial rule in our example, there are three additional rules that
will be evaluated. Each of these rules evaluates the `userAddress` that was
inserted into the working memory to determine what type of content should be
displayed to the end user. 

For additional documentation on the Drools rules language, please see the
official Drools documentation at
[http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/](http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/). 

## Summary [](id=summary-liferay-portal-6-2-user-guide-08-en-0)

In this chapter, we discussed personal sites for portal users. We showed how to
enable or disable them, how to set whether or not pages should be automatically
created, and how to customize automatically created pages. We also examined
general customizable pages that don't belong to personal sites. Administrators
can designate certain pages or portions of pages to be customizable and site
members can configure these portions of the pages, add or remove portlet
applications, and save their configurations.

We also discussed how you can use Liferay's rules engine with Liferay EE. As you
can see from the Sample Rules portlet, using a rules engine can be a powerful
way to decouple the rules of your application from the front-end and back-end
code. These rules are written in a declarative language that business users can
read and verify. Additionally, rule definitions can be modified without
modifying the underlying Java code, re-compiling, or redeploying your
applications. 
