# Liferay Marketplace 

The **Liferay Marketplace** is an exciting new hub for sharing, browsing and
downloading Liferay-compatible applications. As enterprises look for ways to
build and enhance their existing platforms, developers and software vendors are
searching for new avenues to reach this market. Marketplace leverages the entire
Liferay ecosystem to release and share apps in a user-friendly, one-stop site. 

In addition to providing application consumers with Marketplace, Liferay
provides a Plugin Security Manager to help protect a consumer's portal from bad
side-affects that a Marketplace app may produce. The Plugin Security Manager's
job is to only allow an app to use resources that the app has specified up-front
in its Portal Access Control List (PACL). As such, we'll explain how you create
a PACLs for the apps you develop. 

This chapter covers to following topics related to developing apps for the
Liferay Marketplace: 

- Marketplace Basics
- Requirements for Publishing to the Marketplace
- Developing and Testing Apps for the Marketplace
- Publishing Apps to the Marketplace
- Maintaining and Updating Apps
- Tracking App Performance
- Understanding Plugin Security Management
- Developing Plugins with Security in Mind
- Enabling the Security Manager
- Portal Access Control List (PACL) Properties

This chapter focuses on the topics of interest to a Liferay developer. It is
highly recommended that you first read the [Liferay
Marketplace](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/liferay-marketpla-1)
chapter of *Using Liferay Portal*, where you will find detailed information
about the Marketplace from an end user's perspective.
 
## Marketplace Basics 

Before diving into the details of developing for the Marketplace, it is
important to have a good grasp of new concepts introduced in the Marketplace.
The following list of questions will help in understanding the concepts that you
will use over and over again as a Marketplace developer.

### What is an App? 

As a Liferay Developer, you will undoubtedly already be familiar with the
concept of plugins (portlets, hooks, themes, etc). If not, review Chapter 1 of
this guide. A *Liferay App* (sometimes just called an *app*) is a collection of
one or more of these plugins, packaged together to represent the full
functionality of an application on the Liferay platform. In addition to the
plugins contained within an app, apps have metadata such as names, descriptions,
versions, and other ancillary information used to describe and track the app
throughout its lifecycle.

Much like standard Liferay plugins, Liferay apps are also *hot-deployable*. If
you were to download an app from the Marketplace, you would find that it is a
special file type with a `.lpkg` extension. This file can be dropped into
Liferay's hot-deploy folder like any other plugin, and it is deployed into the
running instance of Liferay Portal.

Developers are not required to create the actual Liferay app files. Instead,
your app's individual plugins (`.war` files) are uploaded as part of the publish
process, along with identifying information (name, description, version, icon,
etc). This is described in detail later on in this chapter.

### What is a Version? 

The concept of versioning is well known in software, and it is no different
here. A version of an app represents the functionality of the app at a given
point in time. When an app is first created, it is given an initial version
(often `1.0`). When updates are needed for the app, a new version is created
(e.g. `1.1`), and new files are uploaded representing that version. In some
cases, additional qualifiers can be found in the version specifier, to which
developers often give special meaning. For example, a developer may declare that
the version of their app is always in x.y.z format (where the significance of
each x, y, and z are defined). Liferay itself also does this.

In any case, as developer of your app, you have complete freedom in how you wish
to assign version designators. It is highly recommended that you stick to a well
known and easy to understand format, such as `1.0`, `1.1`, `1.2`, and so on. You
may also include alphabetical characters (e.g `1.0 Beta 2` or `6.3 Patch
123235-01`), but this is not recommended, as it makes it difficult to understand
how versions relate to one another.

Keep in mind that the version of your app is completely up to you to specify,
but the releases of Liferay with which your app works must be specified using
the Liferay versioning scheme. See the *Specify App Packaging Directives*
section below for details on how to specify the releases of Liferay for which
your app is designed.

### What is a Package? 

Apps can be written to work across many different versions of Liferay. For
example, suppose you wish to publish version 1.0 of your app, which you're
supporting on Liferay 6.1 and 6.2. It may not be possible to create a single
binary `.war` file that works across both Liferay versions, due to
incompatibilities between these Liferay versions. In this case, you need to
compile your app twice: once against Liferay 6.1 and once against 6.2, producing
2 different *packages* (also called variations) of your version 1.0 app. Each
package has the same functionality, but they're different files, and it is these
packages that you can upload in support of different versions of Liferay, as you
will see in a later section. In this guide, packages are sometimes referred to
as files that make up your app.

### How Do Apps Relate to Users and Companies? 

When publishing an app, it is possible to publish it *on behalf of* yourself (an
individual) or a *company* with which you are associated. The selection you make
determines who has access to the app, once published. To understand the concepts
of a Marketplace user, admin, and company, and the ramifications of choosing one
vs. the other, visit the *Liferay Marketplace* chapter in the User Guide.

### What Are the Requirements for Publishing Apps? 

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins that are detailed in the *Portlet Development* chapter of
this guide. In addition to those, there are some Marketplace-specific
requirements to keep in mind.

- *WAR (`.war`) files*:
    - WARs must contain a `WEB-INF/liferay-plugin-package.properties` file.
    - WARs must not contain any `WEB-INF/liferay-plugin-package.xml` file.
    - WAR file names must not contain any commas.
    - WAR file names must conform to the following naming convention:
    
      *context_name*`-` *plugin_type* `-`A`.`B`.`C`.`D`.war`

      Where:

    - *context_name* - Alpha-numeric (including `-` and `_`) short name of
      your app.  This name is used as the deployment context, and should not
      duplicate any other app's context (you will see a warning if you use a
      context name of any other app on the Marketplace).

    - *plugin_type* - one of the following: `ext`, `hook`, `layouttpl`,
      `portlet`, `theme`, or `web`.

    - `A`.`B`.`C`.`D` - The 4 digit version of your WAR file.  4 digits must
       be used.

      Example: `myapp-portlet-1.0.0.0.war`

- *liferay-plugin-package.properties file*:
    - Property `recommended.deployment.context` must not be set.
    - Property `security-manager-enabled` must be set to `true`.  This enables
      Liferay's Plugin Security Manager.  Read the section on Plugin Security
      Management later in this chapter for information on developing secure
      apps.  Every app you submit on the Marketplace must use this framework.
- *Deployment contexts*:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as another plugin in the Marketplace.
    - Liferay reserves the right to replace WAR files of app plugins that have
      the same deployment context as plugins built by Liferay.

### Things You Need Before You Can Publish 

You must first develop your app using your preferred development tool (for
example, using Liferay Developer Studio or the Plugins SDK). Your app will
consist of one or more Liferay plugins. Ensure your app is designed to work with
Liferay 6.1 or later. If you wish to target multiple versions of Liferay (for
example, you may wish to support both 6.1 CE GA2 and 6.1 EE GA1), ensure you
have built binary images of your app for each supported minor family release, if
necessary. If a single set of files will work across all supported Liferay
versions, you do not need to build multiple plugins. Liferay guarantees
compatibility within a given minor release family, so your users can rest
assured that your app will work with the minor release that you specify, along
with all future maintenance releases of that minor release.

Next, think of a good name and description of your app, along with a versioning
scheme you wish to use. Take some screenshots, design an icon, create web sites
for your app (if they do not already exist), and have a support plan in place.

### Image and Naming Requirements 

**Icons** for your app *must be* exactly 90 pixels in both height and width and
must be in PNG, JPG, or GIF format. The image size cannot exceed 512kb. Animated
images are prohibited.
**Screenshots** for your app *must not exceed* 1080 pixels in width x 678 pixels
in height and must be in the JPG format. The file size of each screenshot *must
not exceed* 384KB. Each screenshot should preferably be the same size (each will
be automatically scaled to match the aspect ratio of the above dimensions), and
it is preferable if they are named sequentially, for example
`fluffy-puppies-01.png`, `fluffy-puppies-02.png`, and so on.
**Titles of Apps**: In some views with Marketplace, titles of applications
longer than 18 characters will be shortened with ellipsis. In the Marketplace,
titles *must not be* longer than 50 characters.
**Description, Tags, Websites and Version Numbers**: Descriptions, web sites and
version numbers are to be as reflective to the product as possible. Please do
not use misleading names, information, or icons. A tags suggestion tool has been
provided to aid with tagging your asset. Descriptions should be as concise as
possible. Ensure your icons, images, descriptions, and tags are free of
profanity or other offensive material.

Above and beyond these basics of creating apps in the form of Liferay plugins,
there are additional considerations to take into account when designing and
publishing apps.

### What Kind of Validations Are Performed by Liferay? 

Liferay will ensure that apps meet a minimum set of requirements, such as:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Basic sanity checking of functionality (e.g. deployment testing, etc)

Liferay does not do source code reviews and will not ask for your source code.
Further, Liferay is not responsible for the behavior (or misbehavior) of apps on
the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.
 
### What Versions of Liferay Should I Target? 

Of course, targeting the widest possible range of versions will ensure you a
larger audience. However, there are certain features in specific versions of
Liferay that you may wish to take advantage of. When uploading apps, you can
specify which versions your app is compatible with and you can have multiple
files for your app designed for different versions of the Liferay Platform.

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 and
later. That's not to say that they will not work with prior versions. However,
only Liferay 6.1 has support for installing Apps directly from the Marketplace
and safeguards against malicious Apps that will be ineffective on earlier
versions of Liferay. If you wish to use an app for an earlier version, consult
the documentation for that app, as it may or may not be supported on earlier
versions of Liferay.

Read the *Specify App Compatibility* section below for details on how to specify
which versions of Liferay your app works with.

---

 ![note](../../images/tip-pen-paper.png) **Note:** If you haven't yet done so, be
 sure to read the [Marketplace](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/liferay-marketpla-1)
 chapter of *Using Liferay Portal*! 

---

Now that we have covered the basics, you're armed with enough knowledge to start
creating apps on the Marketplace, so let's see what that looks like in the next
section.

## Developing and Publishing Apps 

Let's jump right in with an example. In this section, we'll walk you through the
creation and publication steps (but we won't actually publish the app on the
Marketplace, since this example app isn't very useful!). After walking through
this, you should understand how Marketplace App development typically occurs.

### Develop a Sample App 

Before you can publish anything, you first have to create (develop) an app! Apps
are nothing more than collections of individual plugins, so the first step in
development an app for the Marketplace is to develop the functionality in the
form of one or more Liferay plugins. To create a sample app (which will consist
of a single portlet), follow the detailed instructions in the *Portlet
Development* chapter of this guide. After creating and deploying your sample
app, return here to continue.

In the real world, apps usually consist of multiple components (e.g. multiple
`.war` file plugins), are spread across multiple plugin types, and present
non-trivial functionality which in many cases requires some configuration. How
these advanced tasks are dealt with is out of scope for this section, but some
tips and considerations for Marketplace development can be found later in this
chapter.

### Specify App Packaging Directives 

When publishing your app, each plugin you upload is packaged into one or more
*packages* for each Liferay release you intend to support. When you upload your
plugins to the Liferay Marketplace, your app is scanned, and the embedded
packaging directives you have specified are extracted and used to create
different downloadable *packages* of your app for different Liferay releases.
You must insert this information into each plugin in your app before you can
publish it to the Marketplace.

The packaging directives are related to the Liferay releases with which your app
is compatible. In order to specify which release of Liferay your app is
compatible with (and therefore which packages should be created for eventual
download on the Marketplace), you first need to understand how Liferay releases
are named and how they relate to the underlying Liferay release version. Details
can be found on the [Versioning Policy
Wiki](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+Versioning+Policy).
Accordingly, Liferay 6.1 CE GA1 is designated as version `6.1.0`. CE GA2 is then
`6.1.1`, and so on. Liferay 6.1 EE GA1 is designated as `6.1.10`. EE versioning
follows a slightly different policy given then presence of fix packs and service
packs, so 6.1 EE GA2 is `6.1.20`.

For each plugin that makes up your app, packaging directives must be placed in
the `liferay-plugin-package.properties` file (located in the `WEB-INF/`
directory of your plugin's `.war` file). Within this file, you must specify a
comma-separated list of Liferay releases with which your app is compatible, and
for which packages should be generated, using the `liferay-versions` keyword.
Marketplace will create packages that contain your plugins based on these
packaging directives (and will intelligently group them together as each plugin
is uploaded).  You should specify CE versions first, followed by EE versions,
using this form: `liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE`
are replaced with the corresponding Liferay Releases with which your app is
compatible).

 ![note](../../images/tip-pen-paper.png) **Note:** If your app is compatible
 with both CE and EE, you must specify a set of versions for both CE and EE
 releases. If you only specify compatibility with CE, then your app will not be
 compatible with (and will fail to deploy to) any EE release.

For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA2 (and later), and 6.1 EE GA2 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.1+,6.1.20+

This means that the app works with any 6.1 CE release starting with CE GA2, and
and 6.1 EE release starting with EE GA2. Marketplace will create two packages,
one that is compatible with the 6.1 CE GA2 release and *later*, and another that
is compatible with 6.1 EE GA2 release and *later*.

 ![note](../../images/tip-pen-paper.png) **Note:** Any CE or EE versions you
 include in your packaging directives *must* be terminated with a version using
 the `+` symbol.  This ensures that your app will be deployable onto future
 versions of Liferay (but does not guarantee your app will work in future
 versions).  So, `liferay-versions=6.1.1,6.1.2` will not work, but
 `liferay-versions=6.1.1,6.1.2+` will work.  Similarly,
 `liferay-versions=6.1.1+,6.1.20,6.1.21` will not work (as the EE versions are
 not properly terminated), but `liferay=versions=6.1.1+,6.1.20,6.1.21+` will
 work.


Here are some additional examples:

    # works with Liferay 6.1 CE and EE GA2 and later (NOT compatible with 6.1
    # CE or EE GA1).  This is most likely what you want to use.
    liferay-versions=6.1.1+,6.1.20+

    # works with Liferay 6.1 CE GA2, GA3, and GA5 (but not GA4), and EE GA2
    # and later
    liferay-versions=6.1.1,6.1.2,6.1.4+,6.1.20+
    
    # works with Liferay 6.1 EE GA2 and later (NOT compatible with CE)
    liferay-versions=6.1.20+

If some plugins within your app must be built for multiple releases, ensure that
the respective plugins have appropriate versioning information in them. For
example: suppose your app consists of two plugins: a portlet and a hook. The
portlet is simple, and uses standard API calls that work on all Liferay 6.1
releases. However, the hook is different for CE vs. EE because it takes
advantage of some feature of EE.

In this case, your portlet plugin would have `liferay-versions=6.1.1+,6.1.20+`,
indicating that it is compatible with CE and EE. However, because the hook must
be built against a different API for Liferay EE, you'd built *two* hook plugins.
The first one would specify `liferay-versions=6.1.1+` (indicating it works with
CE GA2 and later, but not EE), and the second hook plugin would specify
`liferay-versions=6.1.20+` (indicating it works with EE GA2 and later, but not
CE GA2). As you upload these plugins later on in this example, you will notice
that they are grouped into separate packages of plugins for each supported
release. In some cases, if a plugin is compatible with multiple releases, it
will be automatically copied for use in all of the releases for which your app
supports. Also, in some cases, if Marketplace cannot determine the appropriate
package into which to place your plugin, it may reject it. For example, if you
specified `liferay-versions=1.0.0+`, because you are confident your plugin
works with any Liferay release, Marketplace likely will reject it as `1.0` is
not a known Liferay release.

Now that you have developed your app, it's time to get started with Marketplace!

### Establish a Marketplace Account 

Before you can publish anything to the Marketplace, you must first have an
account on [liferay.com](http://liferay.com). If you do not have an account,
visit [http://liferay.com](http://liferay.com) and click *Register* in the
upper-right corner of the screen. Once you have registered, you can visit the
Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).
The Marketplace home page is shown below:

![Figure 11.1: The Marketplace home page is where users go to find new and interesting apps. ](../../images/marketplace-homepage.png) 

This is the front page of the Marketplace and is where users go to find new and
interesting apps. You'll visit here often during the course of development, so
it might be a good idea to bookmark it now. To get started, the first thing you
want to do is visit your personal *Home* page. There is a thin strip at the top
of your browser window. This is known as the *Dockbar*. Many links are present
in the drop-down menus of the Dockbar, including a link titled *Go to My Home*.
This is a link to your personal home page. More details about what you can find
on your personal home and profile pages can be found in the Liferay Marketplace
chapter of the *User Guide*. For now, go to your personal home page by clicking
on the *Go to My Home* link.

![Figure 11.2: Use the My Home Page link from anywhere in Liferay to navigate to your personal pages. ](../../images/marketplace-my-homepage-link.png) 

Your home page contains links to often-used functionality of liferay.com,
including app creation and management. There are several links on the left of
your home page, including one titled *App Manager*. This link allows you to
manage the apps that you have either purchased (or have been purchased for use
in companies you are associated with) or apps that you or your company have
developed. You'll use this page heavily, so a bookmark would be useful here.
Click *App Manager* to visit this page.

![Figure 11.3: The App Manager lets you maintain everything about apps you've purchased or published.](../../images/marketplace-my-app-manager.png) 

You'll notice three tabs across the top:

**Purchased** - This shows apps you have personally purchased, and those apps
that have been purchased on behalf of companies you are associated with.
**Apps** - This shows apps you have personally *published*.
**Add an App** - This screen begins the process of publishing a new app.

Since you have not purchased or published any apps, the first two tabs are
likely empty. Let's get publishing!

### Upload (Publish) your App 

To begin the process of publishing your app, click *Add an App*. A form appears,
allowing you to fill in your app's details.

#### Initial App Details 

The first step is to enter the basic details about your app. 

![Figure 11.4: Add all the details about your app, including tags, categories, and links to your site.](../../images/marketplace-add-app-details.png) 

This screen allows you to enter basic details about the app you are publishing.

**Name:** The name of your application. Arguably the most important detail of
your app, the name of your app should be a good title that conveys the function
of the App but is not overly wordy or misleading. Choosing a good name for your
app is key to its success, so choose wisely! See the *Marketplace
Considerations* chapter for more guidance on how to pick good names. Do not
include versions in the title unless it is a vital part of the name, for example
*Angry Birds 2: More Anger*. Each app on the Marketplace must have a unique
name.

**Description:** Like the name says, this is a description of your app. You can
put anything you want here, but a good guideline is no more than 4-5 paragraphs.
This field does not allow any markup tags or other textual adornments--it's just
text.

**Icon:** The icon is a small image representation of the app. See the
*Marketplace Basics* section of this chapter for detailed requirements for
icons.

**Screen Captures:** You can supply multiple screenshots of your app in action.
The screenshots you upload here are displayed when your app is viewed on the
Marketplace, using a carousel of rotating images. See the *Marketplace Basics*
section of this chapter for detailed requirements for screen captures.

**Tags:** A set of descriptive words that categorize your app. These tags are
free-form and can help potential purchasers find your app through keyword
searches, tag clouds, and other search mechanisms. You can click on
*Suggestions* to let Marketplace suggest some tags for you based on the data
you've already entered. Click *Select* to select from existing tags, or you can
manually type in new tags. See the *Marketplace Basics* section of this chapter
for detailed requirements for tags.

**Category:** Choose the Marketplace category that most accurately describes
what your app does. Users looking for specific types of apps will often browse
categories by clicking on a specific category name in the main Marketplace home
page. Having your app listed under the appropriate category will help them find
your app.

**Developer Website:** This is a URL that should reference the web site
associated with the development of the app. For open source apps, this typically
points at the project site where the source is maintained. For others, links to
a home page of information about this app would be appropriate.

**Support Website:** This is a URL that should reference a location where
purchasers or users of the app can get support for the app.

**Demo Website:** What better way to showcase the amazing capabilities of your
app than to have a live, running version of it for potential buyers to see and
use? This field can house a URL pointing to exactly that.

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either
yourself (personal) or a company. If you wish to publish your app on behalf of
your company, but your company does not yet have a Marketplace profile, you can
enter the company name in the **Company Name** field. If you are a
representative of your company, you can register your company by clicking the
*Register My Company* link.

Publishing on behalf of yourself is the default. When you publish on behalf of
yourself, the app appears in your *App Manager* &rarr; *Apps* list, and your
name appears in the Marketplace as the Publisher/Author. You are the only one
who can manage this app (add new releases, new versions, edit details, etc).

Publishing on behalf of a company effectively hands the keys over to the admins
of the company. The app only appears on the company's *App Manager* &rarr;
*Apps* list. In addition to yourself, company admins can manage this app (add
new releases, new versions, edit details, etc). The app appears to be
authored/developed by the company, not you personally. It also appears on the
company's public profile page under its list of apps.

Make up some sample data to use during this example, and enter it into the form.
Once you have entered all your app's details, click *Next* to move on to the
next screen.

![Figure 11.5: Specify the version of your app here, following the guidelines. ](../../images/marketplace-add-app-version-initial.png) 

On this screen, you must specify the version of your app. Review the guidance in
the *What is a version* section in this chapter to choose a good version
specifier and enter it here. For our example, since this is the first version,
enter `1.0`. Click *Next*.

#### Upload Files (Plugins) for your App 

This screen allows you to upload different sets of plugin files (variations) to
support different Liferay versions. You must upload at least one plugin file
before advancing beyond this screen. The screen is shown here as it initially
appears:

![Figure 11.6: Specify a set of files for each version of Liferay Portal you wish to support.](../../images/marketplace-add-app-initial-files.png) 

Press the *Browse* button, and select the plugins that make up your app. Each
time you add plugins to the list, they will automatically begin uploading, and
their compatibility information will be scanned (read the *Specify App
Compatibility* section above to understand what compatibility information is
read from your plugins).

For this example, our app consists of a hook and a portlet. The portlet works
across all Liferay releases, but the hook is built separately for CE and EE.
Therefore, we will upload 3 plugins that make up our app. Once the files are
uploaded, a check mark appears next to each plugin, and the plugins are
displayed based on the compatibility information.

![Figure 11.7: Your app has uploaded successfully.](../../images/marketplace-add-app-uploaded-files.png) 

This indicates that the files were successfully uploaded. Notice that the
portlet plugin was automatically copied for use in both the EE and CE
variations, even though you only uploaded the portlet plugin once. Click *Next*
to advance to the final screen.

#### Preview and Submit Your App 

Whenever you make a change (app details, adding files, adding new versions), you
always wind up at a *Preview* screen. This allows you to preview your app as it
will appear in the Marketplace, so you can confirm your changes.

![Figure 11.8: Always preview your app before submitting it. You may see changes here that you want to make before you submit it.](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information. Is it as you expect? If not, click
*Edit* to go back and continue making changes until you are satisfied.

Once you are satisfied, click *Submit for Review*. If you're walking through
this example on Liferay's Marketplace, don't do it, since this is only an
example app. The next section describes what happens when you submit apps or app
changes.

### The Review Process 

When you submit apps to the Marketplace, they are reviewed by Liferay
Marketplace staff to ensure that your app meets the minimum standards described
in the above *Requirements* section of this chapter.

Each of the following changes require a review by Marketplace staff before the
change is published to the Marketplace:

- Submitting a new app
- Changing details of an app (for example, changing the description or the
  screenshots)
- Adding a new package (set of files) to an existing app, in order to support
  more Liferay releases
- Adding a new version of an existing app

While your submitted change is under review, you can view the status of your
change by visiting *Home* &rarr; *App Manager* &rarr; *Apps*. You can also
cancel your submission by clicking *Cancel Submission* on the *Preview* screen
for each app.

Once your app is approved by Marketplace staff, congratulations! You will
receive an email confirmation and at that moment, your app is in the
Marketplace. The app is also shown on your public Profile page, which lists all
apps that you have personally developed and published.

If your app is rejected, an email will be sent to the email address associated
with the app, along with a note explaining the reasons for rejection.  At that
point, you can make the requested changes, and re-submit the app for approval.

Now that you have successfully published your first app, you'll likely get all
kinds of feedback from users and yourself about what's right and wrong with it.
In the next section, we'll explore how to make changes once you have published
your app.

## Making Changes to Published Apps 

After your app is published and approved, you will undoubtedly need to make one
or more of these kinds of changes during the life of the app:

- Editing your app details (e.g. description, icon, etc)
- Adding support for a new version of Liferay Portal
- Releasing a new version of your app to fix bugs or offer new functionality
- Disabling your apps

Liferay Marketplace supports all of the above operations as described below.

### Editing Your App Details 

App details include the name, description, icon, screenshots, and other
information that you supplied on the first screen during the app creation
process. To make changes to this content for your app, navigate to *Home* &rarr;
*App Manager* &rarr; *Apps*, then click the *Action* button next to the app you
wish to edit, and select *Edit*.

![Figure 11.9: Editing an app is as simple as navigating to it and clicking *Edit*.](../../images/marketplace-edit-app-details.png) 

This screen shows you what the app looks like on the Marketplace. To edit the
detail information, click the *Edit* button at the bottom of the preview. This
allows you to edit details (as well as add new files to your existing version).
Note that the current values as they appear in your app are used to pre-fill the
form. Make any changes as needed on this screen, and click *Next*. If you do not
need to edit any more variations, you can continue clicking *Next* until you
reach the final preview screen. Click *Submit for Review* to submit your detail
changes for review. Once approved, the changes you request appear on the
Marketplace.

### Adding Support for New Versions of Liferay Portal 

If you need to add files in support of another Liferay release, the process is
similar. Navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the
*Action* button next to the app for which you wish to add new files, and click
*Edit*. Click *Next* to advance past the details screen (making any changes as
needed), and click *Next* to advance past the version edit screen (you can't
actually edit the version number of an already-approved version, but you can
edit the "What's New" information if needed).

Once you advance past the version edit screen, you'll be at the File Upload
screen. This screen should look familiar--it's the same workflow used when you
initially created your app! The difference is that you can't edit pre-approved
files for specific Liferay releases. You can only add *new* files for a
different Liferay release (if you actually need to update existing files, you
must create a new version of the app--see the later section on adding versions
for details on how to do this).

Upload your new files (ensuring that your new plugins have updated compatibility
information, see the section on *Specify App Compatibility* for details on
versioning), click *Next*, and observe the newly-added files listed at the
bottom of the preview screen. Click *Submit for Review* to submit your requested
change (adding of files). The files will be reviewed by Liferay, and once
approved, the new package is available for download in the Marketplace.

### Releasing a New Version of your App 

After time passes, you may wish to add new functionality to your app or fix a
batch of bugs. This can be accomplished by releasing a new version of your app.
New versions offer your users new functionality and bugfixes, and users are
generally encouraged to always use the latest version. In addition, when a new
version of your app becomes available, existing users are notified automatically
through Liferay's notification system.

New versions of your apps are created similarly to the way the initial version
was. To add a new version, navigate to *Home* &rarr; *App Manager* &rarr;
*Apps*. Click the *Action* button next to the app for which you wish to add a
new version, and then click *Edit*. You will be taken to the Details screen. At
the bottom of the Details screen, click the *Add New Version* button. This
button begins the process of adding a new version, starting with the App Details
screen. In this case, the screen is pre-filled with data from the current
version of the app, as shown below.

![Figure 11.10: Adding a version is similar to creating a new app, except that the fields are filled in for you.](../../images/marketplace-add-version-details.png) 

You can make any changes to the pre-filled data on this screen. Since this is a
new version of an existing app making major changes (such as completely changing
the name or description) might be unsettling to your existing users. It is
common that you'll want to upload new screenshots and refresh the icon. Note
that you cannot change the app owner (such as moving from a personally-developed
app to a company-developed app).

Clicking *Next* takes you through the same screens you've already seen. On the
*Add App Version* screen, you can specify a new version name for this version of
your app. Also, note that when adding new versions to an existing app, you have
the option to add *What's New* text. This is typically filled in with a list of
changes for this version, such as significant new features or bugfix
information. Clicking *Next* from here allows you to upload the files associated
with the new version of the app. For a new version of the app, you must upload
all files for all supported Liferay versions again, even if they have not
changed since the last version.

### Deactivating Your App 

When the time comes to retire your app, you can *Deactivate* it. Deactivating an
app causes the app to no longer be downloadable from the Marketplace for new
customers and it won't appear in any public Marketplace listings. Existing
customers that have already downloaded your app can continue downloading the
legacy versions of the app they have already acquired, but they can't download
any versions they've not already received. The app remains in your inventory,
with all of its history, in case you need to re-activate or reference it in the
future.

To deactivate your app, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*,
click on the *Actions* button next to the app for which you want deactivate, and
select the *Deactivate* action.

## Tracking App Performance 

One of the main reasons for developing and publishing apps into the Marketplace
is to drive downloads and adoption of your app. The Marketplace enables you, as
the developer of your app, to get detailed reports about the number of views,
downloads, and installations of your app(s). To access these metrics, navigate
to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next
to the app for which you want metrics, and select the *Metrics* action.

![Figure 11.11: App metrics let you see graphically how many views, downloads, and installations your app has in the Marketplace.](../../images/marketplace-app-metrics-views.png) 

The view shown above is the default metrics view for a single app. Across the
top is a list of data series options (*Views*, *Downloads*, or *Installations*).
Below that, a date range can be chosen. In the middle, a graph is shown for the
data within the date range. Finally, the same data that is graphed is also shown
in tabular format, in case you want to know the exact values making up the
graph. The different types of data available to view are described below.

### Views 

When someone searches or browses the Marketplace, they click on apps to see
detailed views of the apps they're interested in. When this occurs for your app,
a *View* is recorded for the app, and this data is what is shown on the App
Metrics screen when *Views* is selected at the top. *Views* is also the default
view, as shown above. The number of recorded views per day per user is
unlimited.

### Downloads 

A download is recorded for your app when someone downloads a specific package of
a specific version of your app. The number of recorded downloads per day per
user is unlimited.

### Installations 

The Marketplace does a best effort in recording installations of your app on
Liferay instances. When a Liferay Administrator installs your app via the
Liferay Administration console (Control Panel), an *Install* is recorded for
your app. 

Now that you understand how to publish your app and track its performance, let's
get a good understanding of Liferay Portal's Plugin Security Manager and the
security information you'll need to provide with your app. 

## Understanding Plugin Security Management 

We all wish cyberspace were free of malicious software and unwanted bugs. Since
it isn't, we need to guard ourselves and our portals from these evils. Enter
Liferay Portal's Plugin Security Manager! It's like a super-hero in a cape and
tights, except, well, it's not.

In its quest for peace within your portal, the Plugin Security Manager pledges
to:

- Protect your portal and host system from unwanted side affects and malicious
  software introduced by plugins.
- Control plugin access to your portal, host system, and network by requiring
  that plugins specify ahead of time the portal resources they intend to access.

Let's go over some scenarios that could apply to you with regard to trying new
plugins, and then maybe the importance of this will be clear.

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it
  a whirl. But naturally, you want to know the parts of your system it will
  access.
- A colleague finds an interesting plugin after scouring the web for something
  that can help streamline processes at your workplace. Of course, you don't
  know whether you can truly trust the plugin creator--this plugin was found
  outside the Liferay Marketplace. If the plugin isn't open source, you have no
  way of knowing if it does anything nefarious.
- Upper management requests your corporate branch and other branches use a
  standard set of plugins on your portal instances. This set of plugins,
  however, was written by an outside firm, and you need to know there will be no
  tampering with your proprietary files.

These are just a few scenarios that may ring true for you. When you're
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system and network.

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks with the security
manager logging information on the attempt to access unauthorized APIs or
resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security manager
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security manager properties appropriately.
Before we dive into the intricacies of these properties, let's consider a plugin
development approach that involves designing an app for the security manager
from the ground up. 

## Developing Plugins with Security in Mind

At the start of plugin developement, you may not have a clear picture of all the
aspects of the portal you'll need to access, and that's fine. In fact, we
suggest you go ahead and develop your plugin first and address plugin security
afterwards. 

Here is the suggested plugin development approach: 

- Develop your plugin. 
- Build your plugin's PACL using Liferay's PACL Policy Generation tool. 
- Test your plugin thoroughly, with the Plugin Security Manager enabled. 
- Add to your plugin's security policy, as needed.
- Convert your policy's absolute file paths into relative paths. 

Let's go over each part of this approach. 

### Develop Your Plugin

First, create your plugin the way you normally would. Design your application,
write code, unit test your code, have users beta test your code. In essence, do
everything you normally would do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file: 

	security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

### Build Your Plugin's PACL

Rather than tediously figuring out all of the resources your plugin accesses, on
your own, let Liferay's PACL Policy Generation tool to give you a head start.
The generation tool detects resources your plugin accesses and writes
corresponding PACL properties to a policy file. You can then merge the PACL
properties from this policy file into your plugin's
`liferay-plugin-package.properties` file. 

Here's how you generate a PACL policy for your plugin: 

1. Specify your Liferay home in your `portal-ext.properties` file, if you
haven't done so already. For example: 

        liferay.home=C:/liferay-portal-<version>/bundles

    You must restart Liferay for the property to take affect. 

2. Turn on the security manager's *generate* mode in your plugin by setting the
following property in your `liferay-plugin-package.properties` file: 

        security-manager-enabled=generate

3. Deploy your plugin. 

    The PACL Policy Generation tool writes a PACL policy file: 

        ${liferay.home}/pacl-policy/${servletContextName}.policy

    The security manager performs security checks on your plugin; but rather
    than throwing errors on failed checks, the generator tool writes suggested
    rules for satisfying the security manager. 

4. Lastly, merge the properties found in your newly generated PACL policy file
into your plugin's `liferay-plugin-package.properties` file. It's just a matter
of merging the properties that start with the "security-manager-" prefix. 

Now that your plugin has a thoroughly specified list of resources it accesses,
let's enable the security manager and do final testing of your PACL properties.
We cover enabling the security manager in the next section. 

### Test the Plugin with the Security Manager Enabled

If you want to distribute plugins, either through the Liferay Marketplace or
through your web site, you have to assume potential users will insist the
Security Manager is enabled in your plugin. For this reason, you should enable
it when testing your plugins. 

To enable the Security Manger set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Then, re-deploy your plugin and re-test it's functionality. The Security Manager
throws Java security exceptions, if your plugin accesses resources that are not
specified in your plugin's security policy. As you test, keep track of these
Java security exceptions, so you can authorize access to the respective
resources in the PACL properties of your `liferay-plugin-package.properties`
file. Save your changes to the file, re-deploy the plugin, and re-test. Make
sure everything works. If not, there are more rules you must declare for your
plugin. Refer to the Portal Access Control List Properties section of this
chapter to see the definitions of all the PACL properties and see example
property values. 

If you are not finding an adequate way to specify a security rule with PACL, you
can specify it in a Java Security Policy file. You see, it's almost impossible
for Liferay and PACL to be aware of every possible security implementation
check, because developers, libraries, and the Java Security API can always call
for new types of security checks. So, Liferay provides a fallback to PACL, that
lets you specify operations permissible within the context of your app's
plugins. 

In case you need it for your plugin, let's get familiar with the Java Security
Policy file. 

### Using a Java Security Policy File

If you cannot find a way to specify PACL permissions for an operation that your
plugin must access, you can specify the permission in a Java Security Policy
file. You can create policy file (`java.policy`) in your plugin's `WEB-INF`
folder. The policy file must follow Policy File syntax as described in detail at
[http://docs.oracle.com/javase/6/docs/technotes/guides/security/PolicyFiles.html#FileSyntax](http://docs.oracle.com/javase/6/docs/technotes/guides/security/PolicyFiles.html#FileSyntax).
Like the rules you define in your plugin's PACL, the additional rules you define
in your plugin's Java Policy File, `WEB-INF/java.policy`, only apply to that
plugin. Plugins aren't privy to each other's security policies. 

Importantly, the Java policy file should only be used to specify rules Liferay's
PACL property implementation does not already support. You should not specify
any rules in a Java policy file that you can specify in a PACL.

Here's a scenario that calls for using a Java Security Policy:

Java has a security implementation called
[http://docs.oracle.com/javase/6/docs/api/java/net/NetPermission.html](java.net.NetPermission).
It checks a whole bunch of networking operations, that Liferay's implementation
doesn't check. In case you want to perform one of these operations, like using a
custom Stream Handler, you can grant your plugin permission to do so in its
`WEB-INF/java.policy` Java Security Policy file. Here's one way to specify that
rule: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
        permission java.net.NetPermission "specifyStreamHandler";
    };

This `grant` entry defines permission for the plugin's code to access the
`specifyStreamHandler` target operation of the `java.net.NetPermission` class.
The `codebase` value, in this example, specifies the following:

- `file:` indicates the code resides on the server's file system. 
- `${my-supercool-portlet}` represents the context path of a plugin named "My
  Supercool Portlet". The context path is a system property Liferay generates
  for the plugin. It maps the context path name to the plugin's fully qualified
  deployment path. 
- `${/}` represents the system's path separator. 
- `-` matches files and folders, in this folder and below. 

On reading this plugin's `.jar` file, the JVM creates a codebase for it. The
codebase uses properties that Liferay sets for the plugin that say, in effect,
"If a file originates within the plugin, then this plugin can perform the
`specifyStreamHandler` operation on it". The codebase narrows the scope for the
permission. This plugin is permitted to perform the definited operation,
`specifyStreamHandler`, as long as it is done within the scope the plugin. 

How do you add more permissions to a codebase? Just define them on separate
lines in the grant entry: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
	    permission java.lang.RuntimePermission "loadLibrary.test_b";
	    permission java.net.NetPermission "specifyStreamHandler";
    };

In this example, we've granted the plugin permission to invoke native code
that's in some library (`test_b.so`). This is another type of operation which
Liferay's PACL does not support. So, it makes sense to specify permission for it
in the Java Security Policy file. 

With Liferay's PACL policy and Java Security Policy files, you can precisely
specify all of the resources your plugin needs to access! Next, let's revisit
the file path values that the PACL Policy Generation Tool wrote to your
`liferay-plugin-package.properties` file. 

### Convert PACL Absolute File Paths into Relative Paths

As mentioned earlier in this chapter, we recommend using the PACL generation
tool to give you a head start on specifying your plugin's security rules. But
The generator is only aware of file paths with respect to the current system,
and therefore generates them as absolute file paths. In order to use your
security policy in production, it must use only relative file paths. So, as a
final step after testing the generated PACL, you must massage the generated file
paths into appropriate relative file paths. For example, you can specify paths
relative to your Liferay web portal directory:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/WEB-INF/tld/-,\
        ${liferay.web.portal.dir}/html/themes/-

In this example, we used a dash (`-`) character at the end of the paths. We use
this as a wildcard character. Oracle defines wildcards for for use with Java
Security, and Liferay provides some too. Let's consider some helpful wildcards
you can use in PACL properties and Java Security policies. 

For files and file paths, you can leverage the following wildcard characters:

- Dash (`-`) matches everything in the current folder and below, like you might
  expect with the normal GLOB operation in UNIX. The current folder isn't
  included in the match.
- Star (`*`) matches every file (*not* folder) in the current folder. The
  current folder and subfolders are excluded from the match.

Let's say you want to match all of your theme files and folders, specify ...

this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/-

NOT this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/*

The star means "every file in this single directory." The dash, however, matches
everything in this folder and below. 

One more note. This: 
                                            
    ${liferay.web.portal.dir}/html/themes/-

does not include this: 

    ${liferay.web.portal.dir}/html/themes

The dash lets you read the *contents* of the folder, but not the folder itself.
Also, when defining the folder, do not include a trailing slash, otherwise
the folder itself will not be included. Below, we specify the `themes` folder
and all of the contents under it: 

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes,\
        ${liferay.web.portal.dir}/html/themes/-

For file path separators, you can use the `${/}` alias.

Example,

    grant codeBase "file:${my-supercool-portlet}${/}-" {
    	permission java.net.NetPermission "specifyStreamHandler";
    };

Congratulations! You now know how to specify your policy's file paths
appropriately for deployment on any server. Once you've completed testing your
plugin without getting any Java security exceptions, you can distribute it as an
app on Liferay Marketplace. You can do so with confidence, because you've
specified all of the resources it uses in the application's PACL, and possibly
its Java Security Policy, and your application satisfies Liferay Portal's
Security Manager. 

The sections that follow demonstrated how to enable the Security Manager (which
you've already done) and provide descriptions for each type of PACL property. 

## Enabling the Security Manager

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume users will insist the Security Manager is
enabled in your plugin. For this reason, you should enable it when testing your
plugins and on packaging it for distribution. 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Next, we'll look at exactly what APIs the Security Manager protects, and how you
can declare whether your application uses any of these properties. 

## Portal Access Control List (PACL) Properties 

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The sections that follow describe the PACL properties: explaining each
property's purpose, its possible values, and the syntax to use in specifying its
value.

### AWT Security

Specify the AWT operations the plugin is permitted to access. 

*Example:*

    security-manager-awt-operations=\
        accessClipboard,\
        accessEventQueue,\
        accessSystemTray,\
        createRobot,\
        fullScreenExclusive,\
        listenToAllAWTEvents,\
        readDisplayPixels,\
        replaceKeyboardFocusManager,\
        setAppletStub,\
        setWindowAlwaysOnTop,\
        showWindowWithoutWarningBanner,\
        toolkitModality,\
        watchMousePointer

### Class Loader Security 

Specify the reference IDs of plugins for this plugin to access.

*Example:*

	security-manager-class-loader-reference-ids=\
		1_WAR_flashportlet,\
		flash-portlet

### Environment Variable Security

Specify regular expression patterns used to match environment variables
that the plugin is permitted to access. 

*Example:*

    security-manager-environment-variables=\
        java.home,\
        java.vendor,\
        java.version,\
        java.vm.v.*

### Expando Bridge

Specify models having Expando Bridge attributes the plugin is permitted to
access. The plugin can also access Expando Bridge attributes via the
wrapper classes of the models. 

*Example:*

	security-manager-expando-bridge=\
        com.liferay.portal.model.User

### File Security 

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Specify files the plugin is permitted to delete.

*Example:*

	security-manager-files-delete=\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Specify files the plugin is permitted to execute.

*Example:*

	security-manager-files-execute=\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to read.

*Example:*

	security-manager-files-read=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/liferay-releng.properties,\
		../webapps/chat-portlet/WEB-INF/src/-,\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to overwrite.

*Example:*

	security-manager-files-write=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/*,\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

### Bean Security

Specify bean properties the plugin is permitted to acquire. 

*Example:*

    security-manager-get-bean-property=\
        com.liferay.portal.kernel.xml.SAXReaderUtil,\
        com.liferay.portal.util.PortalUtil

Specify bean properties the plugin is permitted to set. 

*Example:*

    security-manager-set-bean-property=\
        com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

### Hook Security

Set to `true` if the hook plugin is permitted use custom JSPs. By default, the
hook plugin is not permitted to use custom JSPs. 

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing.
Customizing the indexing can involve modifying the search, summary and/or
queries for these entities. 

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to override. 

*Example:*

This example grants the plugin permission to override the Great Britain locale
of English, the Spain locale of Spanish, and *all* Portuguese locales. 

	security-manager-hook-language-properties-locales=\
		en_GB,\
		es_ES,\
		pt

Specify which portal properties the plugin is permitted to override. 

*Example:*

	security-manager-hook-portal-properties-keys=\
		phone.number.format.impl

Specify which services the plugin is permitted to access. 

*Example:*

	security-manager-hook-services=\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService

Specify whether to allow the plugin's servlet filter hooks. Setting this to
`true` gives the plugin permission to pre-process requests going to the portal's
servlets and post-process requests coming from the portal's servlets. By
default, the plugin's servlet filter hooks are not allowed. 

	security-manager-hook-servlet-filters-enabled=false

Specify struts action paths that the hook plugin is permitted to add or
override. 

*Example:*

	security-manager-hook-struts-action-paths=\
		/portal/test/pacl/success

### JNDI Security

Specify which services the plugin can look up. You can use regular expressions
to make this dynamic. 

*Example:*

Using the sample values below, the plugin can look up objects for key names
`matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can
look-up objects for key names containing `john` with zero or more characters
preceding and/or trailing `john`. 

	security-manager-jndi-names=\
		test-pacl-matthew,\
		test-pacl-[mM]ark|test-pacl-[lL]uke,\
		test-pacl-.*john.*

### Message Bus Security 

Specify which services the plugin is permitted to listen on via the portal's
message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which message bus destinations the plugin is permitted to call (send
messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success

### Portlet Bag Pool Security

Specify regular expression patterns used to match any portlet IDs that the
plugin is permitted to access from the portlet bag pool. 

*Example:*

    security-manager-portlet-bag-pool-portlet-ids=\
        1_WAR_flashportlet,\
        .*_WAR_chatportlet

### Search Engine Security

Specify the IDs of search engines the plugin is permitted to access. 

*Example:*

	security-manager-search-engine-ids=\
		SYSTEM_ENGINE

### Portal Service Security

Specify portal service classes and/or methods the plugin is permitted to access.
Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[portal]=\
		com.liferay.portal.service.GroupLocalService,\
		com.liferay.portal.service.PortalService#getBuildNumber,\
		com.liferay.portal.service.persistence.CompanyPersistence,\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
		com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

### Portlet Service Security

For each portlet the plugin accesses, replicate this property substituting
`some-portlet` in the `[` square brackets `]` with the name of the accessible
portlet. 

Specify portlet service classes and/or methods the plugin is permitted to
access. Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[some-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses

### Socket Security 

Specify sockets permitted to accept connections in the plugin.

*Example:*

	security-manager-sockets-accept=\
		localhost:4320

Specify connections the plugin is permitted to make with the outside world.

*Example:*

	security-manager-sockets-connect=\
		www.cbs.com:80,\
		www.google.com:80,\
		www.google.com:443,\
		www.yahoo.com:443

Specify sockets the plugin can listen on.

*Example:*

This example specifies socket range `4316-4318`, socket `4320`, and socket
`4321`.

	security-manager-sockets-listen=\
		4316-4318,\
		4320,\
		4321

*The Star Socket Wildcard:*

The star (`*`) represent any hostname. Here are examples that demonstrate its
use: 

- `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. 

- `*:*` matches every socket and every port. 

### SQL Security 

Specify tables in the Liferay database on which the plugin is permitted to
perform the applicable operations. These property names use the following
convention:

	security-manager-sql-tables-[operation]    
 
Specify tables on which the plugin can do any and all operations.

*Example:*

	security-manager-sql-tables-all=\
		TestPACL_Foo

Specify tables the plugin can create.

*Example:*

	security-manager-sql-tables-create=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables from which the plugin can delete records.

*Example:*

	security-manager-sql-tables-delete=\
		ClassName_

Specify tables the plugin can drop.

*Example:*

	security-manager-sql-tables-drop=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables into which the plugin can insert records.

*Example:*

	security-manager-sql-tables-insert=\
		TestPACL_InsertSuccess

Specify tables in which the plugin can replace records.

*Example:*

	security-manager-sql-tables-replace=\
		TestPACL_ReplaceSuccess

Specify tables the plugin can query.

*Example:*

	security-manager-sql-tables-select=\
		Counter,\
		TestPACL_Bar

Specify tables from which the plugin can delete all records.

*Example:*

	security-manager-sql-tables-truncate=\
		TestPACL_TruncateSuccess

Specify tables the plugin can update.

*Example:*

	security-manager-sql-tables-update=\
		ListType

### Thread Security 

Specify regular expression patterns used to match names of the thread pool
executor for the plugin to access.

*Example:*

	security-manager-thread-pool-executor-names=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success

<!-- TODO insert section(s) explaining security with respect to core development
-->

<!--Right now summaries from both chapters (plugins security was its own
chapter previously) are left here. They should be combined and the chapter
rewqorked for flow. --> 

## Summary 

In this chapter we introduced concepts and instructions for developers to make
their apps available on the Liferay Marketplace. 

With regards to Marketetplace apps, we looked at how to create, publish,
maintain, and track apps. You do this through [liferay.com](http://liferay.com),
using your own personal credentials and its features for Marketplace. Then, we
covered the requirements for publishing apps, which did not differ significantly
from requirements for general Liferay development. We then showed how you can
publish a sample app on the Marketplace and how you can modify it as the app
evolves. Finally, we looked at how to track the adoption of apps using view,
download, and install metrics. 

As for plugin security management, we discussed why plugin security management
is necessary, how the Plugin Security Manager checks each plugin against its
portal access control list (PACL) and how to specify PACL properties for the
plugins you create and deploy. We also explained Liferay's support of the Java
Security Policy, in case you need to specify rules above and beyond what PACL
properties support. 

Now you have a better understanding of how plugin security works, and can use
Liferay Portal's Plugin Security Manager effectively to specify exactly what
services your plugin needs in order to function. Anyone running Liferay Portal
with Security Manager turned on will know you're a "law abiding" citizen,
because you've specified what services your applications need to access in order
to function. We hope this information helps you understand how to develop safe
powerful Liferay apps. 

Next, we'll talk about using Ext plugins to make customizations that you can't
make with any other Liferay plugin type. 
