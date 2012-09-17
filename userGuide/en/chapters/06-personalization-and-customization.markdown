# Personalization and Customization [](id=drools)

In this chapter, we discuss several ways Liferay users can customize pages,
applications, and the way they use your portal. We'll cover the following
topics:

- Personal Sites
- Customizable Pages and Applications
- Using a Rules Engine

Personal sites allow each portal user to manage and customize a set of public
and/or private pages and any associated content or applications. Public pages
provide a means of making content publicly available while private pages provide
a means of hiding information from other users. Liferay 6.1 introduced
customizable pages and applications. Administrators can designate certain pages
or applications as "customizable," which allows each user to make and save their
own customizations. Liferay Enterprise Edition provides a rules engine which
allows administrators to create custom portal rules and simplify complex blocks
of code containing lots of `if-else` statements. Let's start by discussing
personal sites.

## User Personal Sites [](id=lp-6-1-ugen06-user-personal-sites-0)

By default, newly created users in Liferay are each granted a personal site.
Each user functions as the site administrator of his or her personal site.
Personal sites are fully customizable but cannot have more than one member. The
public pages of personal sites provide a space for users to publish content that
they'd like to make accessible to anyone, including guests. User blogs are often
placed on public personal site pages. Content and applications that users would
like to reserve for personal use are often placed on the private pages of
personal sites. For example, each user can add a Documents and Media portlet to
his or her private pages and use it as an online private file repository.

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
chapter 20 for details.

---

![Tip](../../images/tip.png) Prior to Liferay 6.1, administrators could disallow
users from being able to modify the pages and portlets of their personal sites
by setting the following properties:

    layout.user.public.layouts.modifiable=true
    layout.user.private.layouts.modifiable=true
    
As of Liferay 6.1, this property is obsolete. However, you can customize the
modifiable portions of personal sites through Liferay's permissions system by
removing permissions from roles. To disallow all portal users from modifying
something, remove the permission from the User role.

---

Historically (prior to Liferay 5.1), only power users received personal sites.
Back then, they were called personal communities. If you'd like only power users
to receive personal sites, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.power.user.required=true
    layout.user.private.layouts.power.user.required=true
    
Personal sites are a dynamic feature of Liferay Portal. They allow users to
manage and customize their own pages and content on your portal. Next, let's
look at how users can customize applicatons.

## Page Customizations [](id=lp-6-1-ugen06-page-customizations-0)

Liferay 6.1 introduced the concept of page customizations. Administrators can
designate public pages or sections of public pages to be customizable. When a
user visits such a page, a notification will appear stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, site members can make page customizations but
non-site members and guests can't.

To enable page customizations as an administrator, first navigate to the page
you'd like to let site members modify. Then select *Manage* &rarr; *Page
Customizations* from the Dockbar.

![Figure 6.1: To enable page customizations, select *Manage* &rarr; *Page
Customizations* from the Dockbar.](../../images/page-customizations.png)

Once you've selected *Manage* &rarr; *Page Customizations*, you'll see one or
more red regions, depending on the layout template of your page. Check one or
more of the *Customizable* boxes to allow site members to customize certain
sections of the page. Regions that you've designated as customizable are colored
green.

![Figure 6.2: Check one or more of the *Customizable* boxes to allow site
members to customize certain sections of the
page.](../../images/customizable-regions.png)

When site members visit your customizable page, they'll see a notification
saying, "You can customize this page." Site members can toggle between viewing
their customized page and viewing the default page by clicking the *View Default
Page* or *View My Customized Page* links just below the Dockbar. There's also a
*Reset My Customizations* link that restores a user's customized page to the
match the default page. This allows users to discard one set of customizations
and start a new set without having to manually undo each customization that
they'd previously made.

Note that non-administrator site members can access the Add menu from the
Dockbar when viewing their customizable page even if they don't ordinarily have
permission to view this menu. This allows them to add portlets to the sections
of the page that they're allowed to customize. If they click *View Default
Page*, the Add menu will disappear from the Dockbar since they're not allowed to
modify the default page.

![Figure 6.3: Non-administrator site members can customize their own versions of
customizble pages but can't modify the default
page.](../../images/default-customizable-page.png)

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page*. Changes made to the *default
page* affect all users, whereas changes made to the *customized page* affect
only the administrator who made the changes.  Changes made by administrators to
non-customizable sections in the *default view* are immediately applied for all
users. However, changes made by administrators to customizable sections do *not*
overwrite users' customizations.

Users can make two kinds of customizations to customizable regions. First, they
can configure any portlet applications within the customizable regions. Second,
they can add portlets to or remove portlets from the customizable regions. As a
simple example, suppose that you, as an administrator, selected the right column
of the default portal homepage to be customizable. A member of the default site
could take the following steps to make a personal customization of the portal
homepage:

1. Navigate to the portal homepage by clicking *Go To* &rarr; *Liferay* from the
   Dockbar. (The portal homepage belongs to an automatically created site called
*Liferay*, by default.)
2. Remove the Hello World portlet remove from the right column of the page.
3. Add the Language portlet to the right column by clicking *Add* &rarr; *More*
   in the Dockbar, expanding the *Tools* category, and clicking *Add* next to
*Language*.
4. Configure the Language portlet by clicking on the wrench icon and selecting
   *Configuration* and then opening the *Display Style* dropdown menu and
choosing *Select Box*.

The Language portlet is useful to have on your portal homepage if you expect
users who speak different languages to access your portal. Users can select
their language in the Language portlet to view a translation of the portal into
their native language. After closing the Configuration dialog box of the
Language portlet, the customized portal homepage looks like this:

![Figure 6.4: In this example, Joe Bloggs removed the Hello World portlet, added
the Language portlet, and changed the display style from icons to a select
box.](../../images/customized-portal-homepage.png)

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
Liferay's rules engine.

## Using Liferay's rules engine [](id=lp-6-1-ugen06-using-liferays-rules-engine-0)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal Enterprise Edition provides an implementation of a JSR-94
compliant rules engine. This rules engine is provided as a Web Plugin and is
based on the popular open source Drools project. 

### Why use a rules engine? [](id=why-use-a-rules-engine-)

If you are not familiar with rules engines, you may be wondering why you would
want to use one. In most applications, complex rule processing often takes the
form of nested `if-else` blocks of code which can be very difficult to decipher
and to maintain. If rules change, a developer must work with a business user to
define the new rules. The developer must then read through the existing logic to
understand what is happening and make the necessary modifications. The changes
must then be recompiled, tested, and redeployed. A rules engine provides a means
to separate the rules or logic of an application from the remaining code.
Separating these rules provides several distinct advantages. 

* A rule engine allows for a more declarative style of programming where the
  rules define what is happening, without describing how it is happening. This
makes it much easier to read than nested 'if-else' blocks of code. It's also
easier to make changes without introducing bugs in your code. 

* The rules are written in a language that is easier for non-developers to
  understand. This makes it easier for business users to validate and even
modify the rules without having to involve developers.  

* A rule engine allows for changes to be made to the rules without requiring
  that you recompile your application. If your code must pass through a strict
deployment workflow, this can be a huge time saver and can also save a
significant amount of money. 

After all this, you may be interested in using Liferay's rules engine, so let's
get started with it. 

### Installation [](id=installati-4)

The Drools Web Plugin is available to Liferay Enterprise Edition customers
through Liferay Marketplace. Its name is `Drools EE`, and you'll find it
categorized as a Utility app.

The Drools Web Plugin provides a rules engine implementation, but by itself it
doesn't provide any observable changes to the portal user interface or any
additional functionality. To see the rules engine in action, you can download
and install a Sample Drools Portlet that contains two rule definitions that
illustrate how to leverage the rules engine in your custom code. The Sample
Drools Portlet is available through the Customer Portal.

Let's examine the sample portlet to see how it works. 

#### Configuring the sample Drools portlet [](id=lp-6-1-ugen06-configuring-the-sample-drools-portlet-0)

<!-- | TODO: We need to Nose-ster-ize this. | --> 

Begin by downloading and installing the Sample Drools Portlet. The Sample Drools
Portlet is available to Liferay Enterprise Edition customers through the
customer portal. The name is `sample-drools-portlet`, and you'll find it in the
list of web plugins.

After installation is complete, add the portlet to a page. Initially, the
portlet indicates the name of the currently logged in user and a message that
says there are no results. To see results in the portlet we'll need to create
and tag assets in the site to which you added the portlet. 

Log in as an administrative user and navigate to the Control Panel. Once in the
Control Panel, add a new Web Content entry to your site. Before publishing the
Web Content entry, tag the article with *west coast symposium*. While still in
the control panel, navigate to *My Account* and select the Address link on the
right side of the screen. Enter a Canadian, Mexican, or US based address and
save the record. Now, navigate back to the liferay.com site and the Web Content
should be displayed in the Sample Drools Portlet.

The default rule that's being evaluated displays a list of assets based on the
current user's address. For example, if the current user's country is set to
Canada, Mexico, or the United States, the Sample Drools Portlet displays a list
of assets that have been tagged with the *west coast symposium* tag.

<!-- | TODO: Need screen shots here. | --> <!-- | TODO: We need to point to what
we're about to do before we do it; otherwise this is hard to follow. | --> The
Sample Drools Portlet plugin also contains a second rule that returns
personalized content based on the user's net worth set in the My Account &rarr;
Custom Fields section of the Control Panel. To see this rule in action, add a
second instance of the Sample Drools Portlet to a page. Once added to the page,
select the *Options* icon (*the wrench*) and then select *Configuration*. You
need to replace the rules defined in the *Rules* section of the Configuration
screen with contents of the *rules_user_custom_attribute_content.drl* file. The
rule file can be found in the deployed portlet at
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl`.
In the same Configuration screen, add `networth` to the
user-custom-attribute-names field. Save your changes and close the pop-up
window. Navigate to the Control Panel and add a Custom Field on the User object
with the Key `networth`. Navigate to *My Account* and select the Custom Fields
link on the right side of the screen. Enter a net worth of 150000 and save the
record. While still in the Control Panel, add a new Web Content entry to the
default liferay.com site. Before publishing the Web Content entry, tag the
article with *high net worth* and then save the entry. Now, navigate back to the
liferay.com site and the Web Content should be displayed in the second Sample
Drools Portlet added to the page.

Now that you can see how it works in practice, let's look closer at the rules
themselves. 

#### Rules Definitions [](id=lp-6-1-ugen07-rules-definitions-0)

Rule definitions can be written using Drools' declarative language. Rule files
are text files that often have a .drl extension. A rule file can contain
multiple rules. In addition to the standard Drools' declarative language, a
domain specific language (DSL) can be created for your specific problem domain.
<!-- | TODO: Explain. | --> Creating a DSL can make your rules even easier for
business users to create and maintain your applications rules but does require
some additional work up front. For additional information on creating a DSL for
your problem domain please refer to the Domain Specific Languages section of the
official Drools Documentation at
[http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217](http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217). 

To see examples of a rules definition file, access the following directory in
the Sample Drools Portlet
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies`. To
see how rules work in action we'll look at the rule defined in
`rules_user_address_content.drl`. 

At first glance, this .drl file looks a lot like a Java class file.  This
example starts with a comment describing the rule. Single line comments can
begin with either a *#* or *//* and multi-line comments begin with _/*_ and end
with _*/_. 

	## ## Rules ## ## This sample program will return personalized content
based on the user's ## addresses set in the My Account section of the Control
Panel.  ## ## For example, suppose the current user has an address in the United
States and ## is a member of the Liferay site. All assets within the Liferay
site ## that are tagged with "West Coast Symposium" will be returned.  ##

Following the comments is a `package` declaration. The package declaration is
optional in a Drools, but if it appears, it must be at the beginning of the
file. The package denotes a collection of rules. Unlike Java, the package name
does not represent a folder structure; it's only a unique namespace. The `;` at
the end of the package declaration and all other statements is optional. 

    package com.liferay.sampledrools.dependencies;

After the package declaration are a series of `import` statements. Import
statements in the rule file are used the same as the import statements in Java
classes. Classes that are imported can be used in the rules. 

	import com.liferay.portal.kernel.util.KeyValuePair; import
    com.liferay.portal.kernel.util.StringUtil; import
    com.liferay.portal.model.Address; import com.liferay.portal.model.Contact;
    import com.liferay.portal.model.User; import
    com.liferay.portal.service.AddressLocalServiceUtil; import
    com.liferay.portlet.asset.model.AssetEntry; import
    com.liferay.portlet.asset.service.persistence.AssetEntryQuery; import
    com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil; import
    com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

	import java.util.ArrayList; import java.util.List;
	
The next line declares the `dialect` for the package. In this case, we will be
using Java for any of the code expressions that we'll encounter. The other
possible value for dialect is `MVEL`. <!-- | TODO: And what is that? | --> If
necessary, the dialect can also be specified at the rule level to override the
package level dialect.  

	dialect "java"
	
In this rule file, we have only a single function, which is listed next.
Functions allow you to insert Java code that can be evaluated at run time into
your rule file. Functions are commonly used as a as part of the consequence
clause of the rule statement. Functions are similar to Java methods, but to
define a function you use the *function* keyword. The function's parameters and
the return type are declared as they would be in a Java method. In this example,
the `getAssetEntries` function returns a `java.util.List` object that contains
`AssetEntry` objects based on the `groupId`s, `classNameId`s, and `name`s
provided in the function call. 

	function List getAssetEntries( long[] groupIds, long[] classNameIds,
    String[] names) {
	 
		long[] assetTagIds =
    AssetTagLocalServiceUtil.getTagIds(groupIds, names);
	 
		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
	 
		if (assetTagIds.length > 0) { AssetEntryQuery assetEntryQuery =
    new AssetEntryQuery();
	 		
			assetEntryQuery.setAnyTagIds(assetTagIds);
    assetEntryQuery.setClassNameIds(classNameIds);
    assetEntryQuery.setGroupIds(groupIds); assetEntryQuery.setVisible(true);
	 
			assetEntries.addAll(AssetEntryLocalServiceUtil.getEntries(assetEntryQuery));
    }
	
		return assetEntries; }

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
defaults to the value of 0. Rules with a higher salience value are given a
higher priority. It is considered a best practice to write attributes one to a
line. In our example, the first rule is one that should be evaluated before any
other so it is given a high salience value of 1000. None of our other rules have
a salience attribute set, so they all default to a value of 0.  

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
called *Get European Symposium Content*. This rule includes the following
pattern which ensures that a user's address contains the country name France,
Germany, or Spain. 

	userAddress : Address(country.name in ("France", "Germany", "Spain"));

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

## Summary [](id=summ-29)

In this chapter, we discussed personal sites for portal users. We showed how to
enable or disable them, how to set whether or not pages should be automatically
created, and how to customize automatically created pages. We also examined
general customizable pages that don't belong to personal sites. Administrators
can designate certain pages or portions of pages to be customizable and site
members can configure these portions of the pages, add or remove portlet
applications, and save their configurations.

We also discussed how you can use Liferay's rules engine with Liferay EE. As you
can see from the Sample Rules Portlet, using a rules engine can be a powerful
way to decouple the rules of your application from the front-end and back-end
code. These rules are written in a declarative language that business users can
read and verify. Additionally, rule definitions can be modified without
modifying the underlying Java code, re-compiling, or redeploying your
applications. 
