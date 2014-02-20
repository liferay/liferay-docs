# Liferay Marketplace [](id=liferay-marketplace-liferay-portal-6-2-dev-guide-11-en)

The **Liferay Marketplace** is an exciting hub for sharing, browsing and
downloading Liferay-compatible applications. As enterprises look for ways to
build and enhance their existing platforms, developers and software vendors are
searching for new avenues to reach this market. Marketplace leverages the entire
Liferay ecosystem to release and share apps in a user-friendly, one-stop site. 

In addition to providing application consumers with Marketplace, Liferay
provides a Plugin Security Manager to help protect a consumer's portal from the
potentially negative side-affects of Marketplace apps. The Plugin Security
Manager's job is to only allow an app to use resources that the app has
specified up-front in its Portal Access Control List (PACL). As such, we'll
explain how to create PACLs for the apps you develop. 

This chapter covers to following topics related to developing apps for Liferay
Marketplace: 

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
highly recommended that you first read the [Liferay Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
chapter of *Using Liferay Portal*, where you will find detailed information
about the Marketplace from an end user's perspective.
 
## Marketplace Basics [](id=marketplace-basics-liferay-portal-6-2-dev-guide-11-en)

Before diving into the details of developing for the Marketplace, it is
important to have a good grasp of new concepts introduced in the Marketplace.
The following sections discuss concepts with which you'll need to be familiar as
a Marketplace developer.

### What is an App? [](id=what-is-an-app-liferay-portal-6-2-dev-guide-11-en)

As a Liferay developer, you will undoubtedly already be familiar with the
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
your app's individual plugins (`.war` files) are uploaded as part of the
publication process, along with identifying information (name, description,
version, icon, etc). This is described in detail later on in this chapter.

### What is a Version? [](id=what-is-an-app-version-liferay-portal-6-2-dev-guide-11-en)

The concept of versioning is well known in software, and it is no different
here. A version of an app represents the functionality of the app at a given
point in time. When an app is first created, it is given an initial version
(often `1.0`). When updates are needed for the app, a new version is created
(e.g. `1.1`), and new files are uploaded representing that version. In some
cases, additional qualifiers can be found in the version specifier, to which
developers often give special meaning. For example, a developer may declare that
the version of their app is always in x.y.z format (where the significance of
each x, y, and z are defined). Liferay Portal versions, as well as Liferay app
versions, follow this format.

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

### What is a Package? [](id=what-is-an-app-package-liferay-portal-6-2-dev-guide-11-en)

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

### How Do Apps Relate to Users and Companies? [](id=how-do-apps-relate-to-users-and-companies-liferay-portal-6-2-dev-guide-en)

When publishing an app, it is possible to publish it *on behalf of* yourself (an
individual) or a *company* with which you are associated. The selection you make
determines who has access to the app, once published. To understand the concepts
of a Marketplace user, admin, and company, and the ramifications of choosing one
vs. the other, visit the [Liferay Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
chapter in the User Guide.

### What Are the Requirements for Publishing Apps? [](id=app-publishing-requirements-liferay-portal-6-2-dev-guide-en)

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins that are detailed in the *Portlet Development* chapter of
this guide. In addition to those, there are some Marketplace-specific
requirements to keep in mind.

- *Target the Java 6 JRE*: Your app's byte code must be compatible with Java 6
  (i.e., Java 1.6). Liferay's Plugins SDK already targets Java 6 via the
  `build.properties` setting `ant.build.javac.target=1.6`; so don't override
  this setting. Your app will be rejected if its byte code is not compatible
  with Java 6. 
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
    - Setting property `security-manager-enabled` to `true` is mandatory for all
      paid apps on 6.1 CE GA3, 6.1 EE GA3, and later, but is optional for free
      apps. Setting this property to `true` enables Liferay's Plugin Security
      Manager. If you're enabling the security manager, you'll also need to
      define your Portal Access Control List (PACL) in this file.  Read the
      section on Understanding Plugin Security Management later in this chapter
      for information on developing secure apps. 
- *Deployment contexts*:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as another plugin in the Marketplace.
    - Liferay reserves the right to replace WAR files of app plugins that have
      the same deployment context as plugins built by Liferay.

---

 ![Note](../../images/tip-pen-paper.png) **Important:** If you're developing a
 paid app or want your free app to satisfy Liferay's Plugin Security Manager,
 see the section on Understanding Plugin Security Management for details. Give
 yourself adequate time to develop your app's PACL and time to test your app
 thoroughly with the security manager enabled. 

---

Now that you've learned the packaging and deployment requirements for your app,
let's consider the versions of Liferay you're targetting for your app and how to
prepare your app for them. 

### Things You Need Before You Can Publish [](id=things-you-need-before-publishing-an-app-liferay-portal-6-2-dev-guide-en)

You must first develop your app using your preferred development tool (for
example, using Liferay Developer Studio or the Plugins SDK). Your app will
consist of one or more Liferay plugins. Ensure your app is designed to work with
Liferay 6.1 or later. If you wish to target multiple versions of Liferay (for
example, you may wish to support both 6.1 CE GA3 and 6.1 EE GA3), ensure you
have built binary images of your app for each supported minor family release, if
necessary. If a single set of files will work across all supported Liferay
versions, you do not need to build multiple plugins. Liferay guarantees
compatibility within a given minor release family, so your users can rest
assured that your app will work with the minor release that you specify, along
with all future maintenance releases of that minor release.

Next, think of a good name and description of your app, along with a versioning
scheme you wish to use. Take some screenshots, design an icon, create web sites
for your app (if they do not already exist), and have a support plan in place.

### Image and Naming Requirements [](id=app-image-and-naming-requirements-liferay-portal-6-2-dev-guide-en)

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

### What Kind of Validations Are Performed by Liferay? [](id=liferay-app-validations-liferay-portal-6-2-dev-guide-en)

Liferay ensures that apps meet a minimum set of requirements, such as:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Basic sanity checking of functionality (e.g. deployment testing, etc)

Liferay does not do source code reviews and will not ask for your source code.
Further, Liferay is not responsible for the behavior (or misbehavior) of apps on
the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.
 
### What Versions of Liferay Should I Target? [](id=targeting-liferay-versions-for-apps-liferay-portal-6-2-dev-guide-en)

Of course, targeting the widest possible range of versions will ensure you a
larger audience. However, there are certain features in specific versions of
Liferay that you may wish to take advantage of. When uploading apps, you can
specify which versions your app is compatible with and you can have multiple
files for your app designed for different versions of the Liferay Platform.

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 and
later. That's not to say that they will not work with prior versions. However,
only Liferay 6.1 and later versions provide support for installing apps directly
from the Marketplace and provide safeguards against malicious apps. If you wish
to use an app for an earlier version, consult the documentation for that app, as
it may or may not be supported on earlier versions of Liferay.

Read the section below for details on how to specify the versions of Liferay
your app works with. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If you haven't yet done so, be
 sure to read the [Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
 chapter of *Using Liferay Portal*! 

---

Now that we have covered the basics, you're armed with enough knowledge to start
creating apps on the Marketplace, so let's see what that looks like in the next
section.

## Developing and Publishing Apps [](id=developing-and-publishing-apps-liferay-portal-6-2-dev-guide-11-en)

Let's jump right in with an example. In this section, we'll walk you through the
creation and publication steps (but we won't actually publish the app on the
Marketplace, since this example app isn't very useful!). After walking through
this, you should understand how Marketplace App development typically occurs.

### Develop a Sample App [](id=develop-a-sample-app-liferay-portal-6-2-dev-guide-11-en)

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

### Specify App Packaging Directives [](id=specify-app-packaging-directives-liferay-portal-6-2-dev-guide-11-en)

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
can be found in the [Liferay User Guide](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/editions-of-liferay-liferay-portal-6-2-user-guide-15-en).
Accordingly, Liferay 6.2 CE GA1 is designated as version `6.2.0`. CE GA2 is then
`6.2.1`, and so on. Liferay 6.2 EE GA1 is designated as `6.2.10`. EE versioning
follows a slightly different policy given then presence of fix packs and service
packs, so 6.2 EE GA2 will be `6.1.20`.

For each plugin that makes up your app, packaging directives must be placed in
the `liferay-plugin-package.properties` file (located in the `WEB-INF/`
directory of your plugin's `.war` file). Within this file, you must specify a
comma-separated list of Liferay releases with which your app is compatible and
for which packages should be generated using the `liferay-versions` keyword.
Marketplace will create packages that contain your plugins based on these
packaging directives (and will intelligently group them together as each plugin
is uploaded). You should specify CE versions first, followed by EE versions,
using this form: `liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE` are
replaced with the corresponding Liferay Releases with which your app is
compatible).

 ![Note](../../images/tip-pen-paper.png) **Note:** If your app is compatible
 with both CE and EE, you must specify a set of versions for both CE and EE
 releases. If you only specify compatibility with CE, then your app will not be
 compatible with (and will fail to deploy to) any EE release.

For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA3 (and later), and 6.1 EE GA3 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.2+,6.1.30+

This means that the app works with any 6.1 CE release starting with CE GA3, and
and 6.1 EE release starting with EE GA3. Marketplace will create two packages,
one that is compatible with the 6.1 CE GA3 release and *later*, and another that
is compatible with 6.1 EE GA3 release and *later*.

 ![Note](../../images/tip-pen-paper.png)**Note:** Any CE or EE versions you
 include in your packaging directives *must* be terminated with a version using
 the `+` symbol.  This ensures that your app will be deployable onto future
 versions of Liferay (but does not guarantee your app will work in future
 versions). So, `liferay-versions=6.1.1,6.1.2` will not work, but
 `liferay-versions=6.1.1,6.1.2+` will work.  Similarly,
 `liferay-versions=6.1.2+,6.1.30,6.1.31` will not work (as the EE versions are
 not properly terminated), but `liferay=versions=6.1.2+,6.1.30,6.1.31+` will
 work.


Here are some additional examples:

    # works with Liferay 6.1 CE and EE GA3 and later (NOT compatible with 6.1
    # CE or EE GA2).  This is most likely what you want to use.
    liferay-versions=6.1.2+,6.1.30+

    # works with Liferay 6.1 CE GA2, GA3, and GA5 (but not GA4), and EE GA2
    # and later
    liferay-versions=6.1.1,6.1.2,6.1.4+,6.1.20+
    
    # works with Liferay 6.1 EE GA3 and later (NOT compatible with CE)
    liferay-versions=6.1.30+

You may find it advantageous to implement one of your app's plugins in multiple
ways, customizing that plugin for different Liferay releases. We'll illustrate
this with an example. 

#### Example App: Using Different Versions of a Hook [](id=app-uses-hooks-with-different-versions-liferay-portal-6-2-dev-guide-en)

Suppose your app consists of two plugins: a portlet and a hook. The portlet uses
standard API calls that work on all Liferay 6.1 releases. Your hook, on the
other hand, needs to interact with EE GA3 differently than it does with CE GA3,
because you want the hook to take advantage of an exclusive EE feature. For your
app, how do you provide one version of your hook plugin for EE and another
version of it for CE, while applying your portlet plugin to both EE and CE? 

It's easy. In this case, you'd specify versions
`liferay-versions=6.1.2+,6.1.30+` for your portlet plugin, indicating that it is
compatible with CE GA3 and later, and EE GA3 and later. As for your hook plugin,
you'd create and build *two* versions of it, one version of the hook to use with
Liferay EE and the other version of the hook to use with Liferay CE. You'd
specify `liferay-versions=6.1.30+` for your EE hook and
`liferay-versions=6.1.2+` for your CE hook. The EE hook would work exclusively
with EE GA3 and later, while the CE hook would work exclusively with CE GA3 and
later. You might think that it's difficult to arrange the packaging for an app
that has plugins targeted to different Liferay releases, but it's easy.
Marketplace takes care of it based on the `liferay-versions` values you
specified for each plugin. We'll talk about that next.

#### Marketplace Packages Your App's Plugins [](id=marketplace-packages-your-apps-plugins-liferay-portal-6-2-dev-guide-11-en)

When you upload your app's plugins, as demonstrated later on in this chapter,
you'll notice that Marketplace groups them into separate packages based on the
respective releases each plugin supports. Marketplace copies a plugin into each
of the release packages corresponding to its list of `liferay-versions` values.
If Marketplace cannot verify the version of Liferay the plugin supports, it
rejects the plugin. For example, if you specify `liferay-versions=1.0.0+` for
your plugin--perhaps because you are confident it can work on any Liferay
release--Marketplace will likely reject it, because it doesn't know of a `1.0`
release of Liferay. So take care in specifying the Liferay version information
for each your app's plugins. 

Now that you've developed your app and specified its packaging directives, it's
time to get it to the Marketplace! 

### Establish a Marketplace Account [](id=establish-a-marketplace-account-liferay-portal-6-2-dev-guide-11-en)

Before you can publish anything to the Marketplace, you must first have an
account on [liferay.com](http://liferay.com). If you do not have an account,
visit [http://liferay.com](http://liferay.com) and click *Register* in the
upper-right corner of the screen. Once you have registered, you can visit the
Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).
The Marketplace home page is shown below:

![Figure 12.1: The Marketplace home page is where users go to find new and interesting apps. ](../../images/marketplace-homepage.png) 

This is the front page of the Marketplace and is where users go to find new and
interesting apps. You'll visit here often during the course of development, so
it might be a good idea to bookmark it now. 

You can publish Marketplace apps as an individual or as part of a company.
Before you can submit apps to the Marketplace, you must register yourself as an
app developer. It's easy. Simply click the link *Become a Developer* in the
*Marketplace* column on the left. You're now in the Marketplace registration
wizard. If you're registering with a company and the company is already
registered, you can search for it from these Marketplace registration screens
and request to *join* the company in publishing apps to the Marketplace. On
completing the Marketplace registration, Liferay sends you an email confirming
your acceptance as a Marketplace Developer--Congratulations!

Now that you're a Marketplace Developer, your liferay.com *App Manager* provides
options for adding new apps and viewing your published apps. Your App Manager is
accessible from your user profile page. Let's go there now. In the upper right
corner on liferay.com select your name &rarr; *User Profile*. 

![Figure 12.2: Select your name &rarr; *User Profile* on liferay.com to access your *App Manager*.](../../images/liferay-dot-com-user-profile.png) 

Your profile page contains links to often-used functionality of liferay.com,
including app creation and management. There are several links on the left of
your home page, including one called *App Manager*. This link allows you to
manage the apps that you have for either personal or company use and to manage
the apps that you or your company have developed. You'll use this page heavily,
so a bookmark would be useful here. Click *App Manager* to visit this page.

![Figure 12.3: The App Manager lets you maintain everything about apps you've purchased or published.](../../images/marketplace-my-app-manager.png) 

You'll notice three tabs across the top:

**Purchased**: This shows apps you have personally purchased, and those apps
that have been purchased on behalf of companies you are associated with. 

**Apps**: This shows apps you have personally *published*. 

**Add an App**: This screen begins the process of publishing a new app.

Since you have not purchased or published any apps, the first two tabs are
likely empty. Let's get publishing!

### Upload (Publish) your App [](id=upload-and-publish-your-app-liferay-portal-6-2-dev-guide-11-en)

To begin the process of publishing your app, click *Add an App*. A form appears,
allowing you to fill in your app's details.

#### Initial App Details [](id=initial-app-details-liferay-portal-6-2-dev-guide-11-en)

The first step is to enter the basic details about your app. 

![Figure 12.4: Add all the details about your app, including tags, categories, and links to your site.](../../images/marketplace-add-app-details.png) 

This screen allows you to enter basic details about the app you are publishing.

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

**Category:** Choose the Marketplace category that most accurately describes
what your app does. Users looking for specific types of apps will often browse
categories by clicking on a specific category name in the main Marketplace home
page. Having your app listed under the appropriate category will help them find
your app.

**Developer Website URL:** This is a URL that should reference the web site
associated with the development of the app. For open source apps, this typically
points at the project site where the source is maintained. For others, links to
a home page of information about this app would be appropriate.

**Support URL:** This is a URL that should reference a location where
purchasers or users of the app can get support for the app.

**Documentation URL:** What better way to showcase the amazing capabilities of
your app than to have a live, running version of it for potential buyers to see
and use? This field can house a URL pointing to exactly that.

**Source Code URL:** If you'd to provide a link to the source code of your app,
do so here.

**Labs:** You can denote an app as experimental by flagging the appropriate box.

**Security:** If your app does *not* use Liferay's PACL Security Manager, flag
the appropriate box. Otherwise, make sure to enable the security manager in your
app by including the setting `security-manager-enabled=true` in your
`liferay-plugin-package.properties` file. 

**Tags:** A set of descriptive words that categorize your app. These tags are
free-form and can help potential purchasers find your app through keyword
searches, tag clouds, and other search mechanisms. You can click on
*Suggestions* to let Marketplace suggest some tags for you based on the data
you've already entered. Click *Select* to select from existing tags, or you can
manually type in new tags. See the *Marketplace Basics* section of this chapter
for detailed requirements for tags.

**EULA:** You can either use the default end user license agreement or provide
your own. There's a link to the minimum terms which custom EULAs must satisfy.

**Liferay Only:** If you're publishing a CE-only or EE-only app, select *CE
Only* or *EE Only* in the *Product Type* dropdown selector. If you're providing
an app that runs on both CE and EE, just flag the *Liferay EE Plugin* checkbox.
If you're uploading a bug fix or would like to replace a previous version of
your app, specify the app entry IDs of the apps to be replaced by the new app in
the *Supercedes App Entry IDs* field.

Make up some sample data to use during this example, and enter it into the form.
Once you have entered all your app's details, click *Next* to move on to the
next screen.

#### Upload Files (Plugins) for your App [](id=upload-plugin-files-for-your-app-liferay-portal-6-2-dev-guide-11-en)

On this screen, you must specify the version of your app and upload its plugin
files. Review the guidance in the *What is a version* section in this chapter to
choose a good version specifier and enter it here. For our example, since this
is the first version, enter `1.0`. 

Then you must upload the different sets of plugin files (variations) to support
different Liferay versions. You must upload at least one plugin file before
advancing beyond this screen. So, click the *Browse* button, and select the
plugins that make up your app. Each time you add plugins to the list, they
automatically begin uploading and their compatibility information is scanned
(read the previous sections in this chapter to understand what compatibility
information is read from your plugins). 


![Figure 12.5: Specify a set of files for each version of Liferay Portal you wish to support.](../../images/marketplace-app-version-and-upload-files.png)

As a more complicated example, let's consider an app that consists of a hook and
a portlet. The portlet works across all Liferay releases, but the hook is built
separately for CE and EE. Therefore, you would upload 3 plugins that make up the
app: 1 portlet plugin for all releases, 1 hook plugin for CE, and 1 hook plugin
for EE. Once the files are uploaded, a check mark appears next to each plugin,
and the plugins are displayed based on their compatibility information. 

![Figure 12.6: Your app has uploaded successfully.](../../images/marketplace-add-app-uploaded-files.png) 

This indicates that the files were successfully uploaded. Notice that the
portlet plugin was automatically copied for use in both the EE and CE
variations, even though you only uploaded the portlet plugin once. Click *Next*
to advance to the final screen.

#### Preview and Submit Your App [](id=preview-and-submit-your-app-liferay-portal-6-2-dev-guide-11-en)

Whenever you make a change (app details, adding files, adding new versions), you
always wind up at a *Preview* screen. This allows you to preview your app as it
will appear in the Marketplace, so you can confirm your changes.

![Figure 12.7: Always preview your app before submitting it. You may see changes here that you want to make before you submit it.](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information. Is it as you expect? If not, click
*Edit* to go back and continue making changes until you are satisfied.

Once you are satisfied, click *Submit for Review*. If you're walking through
this example on Liferay's Marketplace, don't do it, since this is only an
example app. The next section describes what happens when you submit apps or app
changes.

### The Review Process [](id=the-app-review-process-liferay-portal-6-2-dev-guide-11-en)

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
receive an email confirmation and at that moment, your app is available on
Marketplace. The app is also shown on your public Profile page, which lists all
apps that you have personally developed and published.

If your app is rejected, an email will be sent to the email address associated
with the app, along with a note explaining the reasons for rejection. At that
point, you can make the requested changes, and re-submit the app for approval.

Now that you have successfully published your first app, you'll likely get all
kinds of feedback from users and yourself about what's right and wrong with it.
In the next section, we'll explore how to make changes once you have published
your app.

## Making Changes to Published Apps [](id=making-changes-to-published-apps-liferay-portal-6-2-dev-guide-11-en)

After your app is published and approved, you will undoubtedly need to make one
or more of these kinds of changes during the life of the app:

- Editing your app details (e.g., description, icon, etc)
- Adding support for a new version of Liferay Portal
- Releasing a new version of your app to fix bugs or offer new functionality
- Disabling your apps

Liferay Marketplace supports all of the above operations as described below.

### Editing Your App Details [](id=editing-your-app-details-liferay-portal-6-2-dev-guide-11-en)

App details include the name, description, icon, screenshots, and other
information that you supplied on the first screen during the app creation
process. To make changes to this content for your app, navigate to *Home* &rarr;
*App Manager* &rarr; *Apps*, then click the *Action* button next to the app you
wish to edit, and select *Edit*.

This screen shows you what the app looks like on the Marketplace. To edit the
detail information, click the *Edit* button at the bottom of the preview. This
allows you to edit details (as well as add new files to your existing version).
Note that the current values as they appear in your app are used to pre-fill the
form. Make any changes as needed on this screen, and click *Next*. If you do not
need to edit any more variations, you can continue clicking *Next* until you
reach the final preview screen. Click *Submit for Review* to submit your detail
changes for review. Once approved, the changes you request appear on the
Marketplace.

### Adding Support for New Versions of Liferay Portal [](id=adding-support-for-new-versions-of-liferay-portal-6-2-dev-guide-11-en)

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

### Releasing a New Version of your App [](id=releasing-a-new-version-of-your-app-liferay-portal-6-2-dev-guide-11-en)

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
version of the app. 

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

### Deactivating Your App [](id=deactivating-your-app-liferay-portal-6-2-dev-guide-11-en)

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

## Tracking App Performance [](id=tracking-app-performance-liferay-portal-6-2-dev-guide-11-en)

One of the main reasons for developing and publishing apps into the Marketplace
is to drive downloads and adoption of your app. The Marketplace enables you, as
the developer of your app, to get detailed reports about the number of views,
downloads, and installations of your app(s). To access these metrics, navigate
to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next
to the app for which you want metrics, and select the *Metrics* action.

![Figure 12.8: App metrics let you see graphically how many views, downloads, and installations your app has in the Marketplace.](../../images/marketplace-app-metrics-views.png) 

The view shown above is the default metrics view for a single app. Across the
top is a list of data series options (*Views*, *Downloads*, or *Installations*).
Below that, a date range can be chosen. In the middle, a graph is shown for the
data within the date range. Finally, the same data that is graphed is also shown
in tabular format, in case you want to know the exact values making up the
graph. The different types of data available to view are described below.

### Views [](id=views-of-your-app-liferay-portal-6-2-dev-guide-11-en)

When someone searches or browses the Marketplace, they click on apps to see
detailed views of the apps they're interested in. When this occurs for your app,
a *View* is recorded for the app, and this data is what is shown on the App
Metrics screen when *Views* is selected at the top. *Views* is also the default
view, as shown above. The number of recorded views per day per user is
unlimited.

### Downloads [](id=recorded-app-downloads-liferay-portal-6-2-dev-guide-11-en)

A download is recorded for your app when someone downloads a specific package of
a specific version of your app. The number of recorded downloads per day per
user is unlimited.

### Installations [](id=recorded-app-installations-liferay-portal-6-2-dev-guide-11-en)

The Marketplace makes an effort to count the number of installations of your app
to Liferay instances. When a Liferay Administrator installs your app via the
Liferay Administration console (Control Panel), an *Install* is recorded for
your app. 

Now that you understand how to publish your app and track its performance, let's
get a good understanding of Liferay Portal's Plugin Security Manager and the
security information you'll need to provide with your app. 

## Understanding Plugin Security Management [](id=understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)

We all wish cyberspace was free of malicious software and unwanted bugs. Since
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
  however, was written by an outside firm, and you must assure that the plugins
  will not tamper with your proprietary files. 

These are just a few scenarios that may ring true for you. When you're
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system, and network.

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks and the security
manager logs information on the plugin's attempt to access the unauthorized APIs
or resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security management
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security management properties
appropriately. Before we dive into the intricacies of these properties, let's
consider a plugin development approach that involves designing an app for the
security manager from the ground up. 

## Developing Plugins with Security in Mind [](id=developing-plugins-with-security-in-mind-liferay-portal-6-2-dev-guide-11-en)

At the start of plugin development, you may not have a clear picture of all the
aspects of the portal you'll need to access, and that's fine. In fact, we
suggest you go ahead and develop your plugin first and address your plugin's
Portal Access Control List (PACL) later. But, as you develop your plugin there
are some common security pitfalls, highlighted in the next section, that you'll
want to avoid. After you develop your plugin you'll dig whole-heartedly
into security management by generating and fine-tuning your plugin's PACL. Don't
worry, we'll guide you through the entire process. 

If you're developing a plugin as part of a free app, writing the plugin's PACL
and enabling the security manager for the plugin are optional, and you can skip
the remainder of this chapter. Otherwise, read on. 

Here is the suggested approach for developing secure plugins: 

- Consider common security pitfalls.
- Develop your plugin. 
- Build your plugin's PACL using Liferay's PACL Policy Generation tool. 
- Test your plugin thoroughly, with the security manager enabled. 
- Add to your plugin's security policy, as needed.
- Convert your policy's absolute file paths to relative paths. 

Let's go over each part of this approach. 

### Consider Common Security Pitfalls [](id=consider-common-security-pitfalls-liferay-portal-6-2-dev-guide-11-en)

As you develop your plugin, you need to anticipate your plugin's actions in
light of Liferay's secured environment. The security manager leverages the Java
SE Security Architecture. So understanding Java SE Security and learning the few
requirements that Liferay's security manager adds on top of it will benefit you
as you develop plugins. The extensive Java SE Security Architecture
documentation is available for you to read at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
But we'll highlight a couple common mistakes developers make that violate
Liferay's secured environment: 

- Invoking a method, directly or indirectly, without considering whether the
  method can throw a security exception.
- Using external libraries or external frameworks that access classloaders
  outside of your plugin.  

You wouldn't intentionally make these kinds of mistakes, but you'd be surprised
at how easily you can make them if you're not being careful enough. We'll
consider scenarios that illustrate both of these mistakes and explain how to
avoid making them in your plugin. Let's consider security exceptions first. 

When you're running on Liferay Portal with the security manager enabled for your
plugin, you must only access authorized resources. If you invoke a method
declared as throwing a security exception (i.e., `java.lang.SecurityException`)
and you're not authorized to access the resources the method uses, the method
throws the security exception and the Security Manger stops your plugin dead in
its tracks. Security exceptions are unchecked, meaning that the compiler doesn't
require your code to handle them. But since methods that throw security
exceptions are declared as throwing them, you should check their signatures
while you're designing your plugin. If the methods your plugin uses throw
security exceptions, handle them appropriately with try/catch blocks. Keep in
mind that you not only need to handle security exceptions of methods your plugin
invokes *directly*, but you also need to handle the security exceptions of the
underlying methods your plugin invokes *indirectly*. 

For example, you may be using a file utility that calls `java.io.File`'s
`canRead` method. Since the `canRead` method can throw a `SecurityException`,
your plugin will violate security if it invokes the utility on a file that
you're not authorized to access. So, be aware of all security exceptions thrown
by methods your plugin calls directly and indirectly.

Operations involving reflection, and similar activities, typically can throw
security exceptions. The Java SE Security documentation explains how to deal
with them. In many cases, you can declare your plugin's permissions to avoid
running into these exceptions. We'll go over your plugin's permissions and
security policies later in this chapter. 

The second common mistake you should avoid is allowing your plugin to bring up
classloaders unintentionally, via other frameworks or libraries. Consider the
following Spring configuration from a plugin:

    <bean id="userServiceBeanFactory"
        class="com.liferay.portal.service.UserLocalServiceUtil"
        factory-method="getService"
    />

It declares a factory bean that calls a method on a Liferay class. This seems
reasonable, right? Unfortunately, Spring tries to grab the classloader for the
factory class. Since the factory class does not belong to the plugin, the
security manager balks at the plugin's attempt to access the classloader for the
factory class. The security manager forbids applications from accessing arbitrary
classloaders because the classloaders can add, access, and modify classes that
your plugin is unauthorized to access. Using Spring in this manner violates the
secured environment.

How do you get around this issue? You could simply invoke the method directly
like this: 

    UserLocalServiceUtil.getService()

But if you insist on using a Spring factory bean, you can do the following:

1. Write a class *inside* your plugin to act as a factory. Your factory class
   should declare a class that wraps the type of instance your factory returns.
   Your factory should also implement a method that returns the instance,
   wrapped in the class you declared. 

2. Configure a Spring factory bean that uses your plugin's factory class. 
    
Here's what your plugin's new factory class could look like:

        package test;
    
        // Add imports here ...
    
        public class FactoryUtil {
    
            public static UserLocalService getUserLocalService() {
                TestUserLocalServiceWrapper localServiceWrapper =
                    new TestUserLocalServiceWrapper(
                        UserLocalServiceUtil.getService());
    
                return localServiceWrapper;
            }
    
            private static class TestUserLocalServiceWrapper
                extends UserLocalServiceWrapper {
     
                public TestUserLocalServiceWrapper(
                    UserLocalService userLocalService) {

                    super(userLocalService);
                }
    
            }
    
        }

The code above declares a factory class named `FactoryUtil` that resides in a
package named `test`. The factory declares an inner class named
`TestUserLocalServiceWrapper` that extends Liferay's `UserLocalServiceWrapper`
class. Note, `UserLocalServiceWrapper` in turn wraps `UserLocalService`--the
class you want the factory to return. Lastly, the `getUserLocalService()` method
uses the original factory method, `UserLocalServiceUtil.getService()`, to get
the `UserLocalService` instance. This instance is wrapped up in your factory's
`TestUserLocalServiceWrapper` class. In your plugin, you've implemented a
factory class to access the instances you want. That wasn't so difficult, was
it? 

Your new Spring factory bean would look like the following configuration:

    <bean id="userServiceBeanFactory" 
        class="test.FactoryUtil"
        factory-method="getUserLocalService"
    />

Great! Now you know a couple alternatives to using the troublesome Spring
factory bean configuration that was accessing a classloader that didn't belong
to your plugin. 

With regards to both of the use cases we've illustrated, the main point we're
emphasizing is that you must be aware of the how the libraries you use behave
with respect to your secured environment. The better you understand Java SE
Security and Liferay's Plugin Security Management, the easier it will be for you
to write security-aware plugins. Keeping this in mind, you can proceed
confidently creating your plugin. 

### Develop Your Plugin [](id=develop-your-plugin-for-security-liferay-portal-6-2-dev-guide-en)

Start creating your plugin the way you normally would. Design your application,
write code, unit test your code, and have users beta test your app. In essence,
do everything you would normally do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file:

    security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

### Build Your Plugin's PACL [](id=build-your-plugins-pacl-liferay-portal-6-2-dev-guide-11-en)

Rather than tediously figuring out all of the resources your plugin accesses, on
your own, let Liferay's PACL Policy Generation tool to give you a head start.
The generation tool detects resources your plugin accesses and writes
corresponding PACL properties to a policy file. You can then merge the PACL
properties from this policy file into your plugin's
`liferay-plugin-package.properties` file. 

Here's how you generate a PACL policy for your plugin: 

1.  Make sure your Liferay Portal instance has `liferay` set as its security
    manager strategy value and that the security manager was activated during
    application server startup. 

    In your `portal-ext.properties` file, make sure Liferay Portal's security
    manager strategy is specified as follows: 

        portal.security.manager.strategy=liferay

    Your app server may require that certain startup arguments be used for
    activiting the security manager. Check the PACL and security manager
    instructions for your app server in the [Installation and
    Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
    chapter of *Using Liferay Portal 6.2*. Some app servers, like Tomcat, output
    a terminal message, like "Using Security Manager", indicating that it's
    using the security manager. 

    Unless you already started Liferay with the security manager enabled and
    activated as described above, you must restart Liferay with these settings. 

2.  Enable the security manager to generate a security policy for your plugin by
    setting the following property in your plugin's
    `liferay-plugin-package.properties` file: 

        security-manager-enabled=generate

3.  Deploy your plugin.

    The PACL Policy Generation tool writes a PACL policy file with the following
    path: 

        [liferay.home]/pacl-policy/[servletContextName].policy

    On deploying your plugin and as you exercise your plugin's features, Liferay
    Portal's security manager performs security checks on your plugin; but
    rather than throwing errors on failed checks, the generator tool writes
    suggested rules that specify access to the resources your plugin accesses. 

    Unless you've turned off logging for the generator tool, messages like the
    ones below are logged, reporting the various authorization properties that
    the tool generated

        DEBUG [localhost-startStop-2][GeneratingPACLPolicy:230] my-pacl-portlet
        generated authorization property {key=security-manager-properties-read,
        value=log4j.configDebug}
        DEBUG [localhost-startStop-2][GeneratingPACLPolicy:230] my-pacl-portlet
        generated authorization property {key=security-manager-properties-read,
        value=line.separator}

4.  Lastly, merge the properties that the security manager wrote (i.e., your
    newly generated PACL policy file
    `[liferay.home]/pacl-policy/[servletContextName].policy`) into your plugin's
    `liferay-plugin-package.properties` file. It's just a matter of merging the
    properties that start with the "security-manager-" prefix. 

----

![Note](../../images/tip-pen-paper.png) **Note:** There is a known issue
 [LPS-41716](https://issues.liferay.com/browse/LPS-41716) in which Liferay may
 need to be restarted after deploying your plugin, in order for the security
 manager to detect and write out the complete set of policies for a plugin. If
 you are using your plugin with the "security-manager-" generated properties the
 first time and notice security violations, then you may need to turn on policy
 generation one more time and restart Liferay. This gives the security manager
 another opportunity to detect additional properties to satisfy your security
 policy. If you are still seeing security violations on deployment, you'll need
 to address them per instructions that follow in this chapter. Here are the
 work-around steps: Remove the previously generated
 `[servletContextName].policy` file, set `security-manager-enabled=generate` in
 your `liferay-plugin-package.properties` file, restart Liferay, redeploy your
 plugin, and merge any new properties from the newly generated
 `[servletContextName].policy` file into your
 `liferay-plugin-package.properties` file. 
 
----

Now that you've thoroughly specified the resources your plugin accesses, let's
enable the security manager and do final testing of your PACL properties. 

### Test the Plugin with the Security Manager Enabled [](id=test-the-plugin-with-security-manager-liferay-portal-6-2-dev-guide-en)

If you want to distribute plugins, either through the Liferay Marketplace or
through your web site, you have to assume potential users will insist the
Security Manager be enabled in your plugin. For this reason, you should enable
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
plugin. Refer to the online definition of the Portal Access Control List
Properties for the `liferay-plugin-package.properties` file at 
[http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
and in the PACL properties section of this chapter for additional details. 

If you are not finding an adequate way to specify a security rule with PACL, you
can specify it in a Java Security Policy file. It's almost impossible for
Liferay and PACL to be aware of every possible security implementation check,
because developers, libraries, and the Java Security API can always call for new
types of security checks. So, Liferay provides a fallback to PACL, that lets you
specify operations permissible within the context of your app's plugins. 

In case you need it for your plugin, let's get familiar with the Java Security
Policy file. 

### Using a Java Security Policy File [](id=using-a-java-security-policy-file-liferay-portal-6-2-dev-guide-11-en)

If you cannot find a way to specify PACL permissions for an operation that your
plugin must access, you can specify the permission in a Java Security Policy
file. You can create the policy file (`java.policy`) in your plugin's `WEB-INF`
folder. The policy file must follow Policy File syntax as described in detail at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html#FileSyntax](http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html#FileSyntax).
Like the rules you define in your plugin's PACL, the additional rules you define
in your plugin's Java Policy File, `WEB-INF/java.policy`, only apply to that
plugin. Plugins aren't privy to each other's security policies. 

Importantly, the Java policy file should only be used to specify rules Liferay's
PACL property implementation does not already support. You should not specify
any rules in a Java policy file that you can specify in a PACL.

Here's a scenario that calls for using a Java Security Policy:

Java has a security implementation called
[http://docs.oracle.com/javase/7/docs/api/java/net/NetPermission.html](java.net.NetPermission).
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

### Convert PACL Absolute File Paths into Relative Paths [](id=convert-pacl-file-paths-to-relative-paths-liferay-portal-6-2-dev-guide-en)

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

## Enabling the Security Manager [](id=enabling-the-security-manager-liferay-portal-6-2-dev-guide-11-en)

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume users will insist the Security Manager is
enabled in your plugin. For this reason, you should enable it when testing your
plugins and on packaging it for distribution. 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

    security-manager-enabled=true

Next, we'll explain the purpose of the PACL properties, show you some of the
wildcards you can use for particular property values, and refer you to the file
containing the PACL property definitions. 

## Portal Access Control List (PACL) Properties [](id=portal-access-control-list-pacl-properties-liferay-portal-6-2-dev-guide-en)

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The online definitions for the PACL properties can be found at
[http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html).
If you have the Liferay Portal source code, you can find the
`liferay-plugin-package_6_2_0.properties` file in the
`liferay-portal/definitions` folder. 

Some of the properties accept wildcard characters that have special meaning.
Let's investigate the wildcard characters you can use in your plugin's file
security properties. 

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Here's an example that uses the `-` character to specify that the plugin is
permitted to delete files in the
`../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp` directory and its
subdirectories. 

    security-manager-files-delete=\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Note, you can use a relative paths in the file security
properties. 

You can use a mix of UNIX/Linux style paths and Windows style paths as
demonstrated in the example below: 

    security-manager-files-execute=\
        /bin/bash,\
        C:\\WINDOWS\\system32\\ping.exe

And the following example uses the `*` character to specify that the plugin is
reads files in the `../webapps/chat-portlet/images` and
`../webapps/chat-portlet/WEB-INF/*` directories, but not their subdirectories:

    security-manager-files-write=\
        ../webapps/chat-portlet/images/*,\
        ../webapps/chat-portlet/WEB-INF/*,\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

For socket security properties the `*` character represents any hostname. For
example, `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. And `*:*` matches every socket and
every port. 

<!-- TODO insert section(s) explaining security with respect to core development
-->

## Summary [](id=summary-liferay-portal-6-2-dev-guide-11-en-0)

In this chapter, we introduced concepts and instructions for developers to make
their apps available on the Liferay Marketplace. 

We looked at how to create, publish, maintain, and track Liferay Marketplace
apps. You can do this through the App Manager that's available on your personal
[liferay.com](http://liferay.com) home page ([liferay.com](http://liferay.com)
account required!). Then, we covered the requirements for publishing apps, which
did not differ significantly from requirements for general Liferay development.
Next, we showed how you can publish a sample app on the Marketplace and how you
can modify it as the app evolves. Finally, we looked at how to track the
adoption of apps using view, download, and install metrics. 

Regarding plugin security management, we discussed why plugin security
management is necessary, how the Plugin Security Manager checks each plugin
against its portal access control list (PACL), and how to specify PACL
properties for the plugins you create and deploy. We also explained Liferay's
support of the Java Security Policy, in case you need to specify rules above and
beyond what PACL properties support.

Now you have a better understanding of how plugin security works and can use
Liferay Portal's Plugin Security Manager effectively to specify exactly what
services your plugin needs in order to function. Anyone running Liferay Portal
with Security Manager turned on will know you're a "law abiding" citizen,
because you've specified what services your applications need to access in order
to function. We hope this information helps you understand how to develop safe
and powerful Liferay apps. 

Next, we'll talk about using Ext plugins to make customizations that you can't
make with any other Liferay plugin type. 
