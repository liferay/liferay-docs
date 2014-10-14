# Marketplace basics [](id=marketplace-basics)

Before diving into the details of developing for the Marketplace, it is
important to have a good grasp of new concepts introduced in the Marketplace.
The following list of questions will help in understanding the concepts that you
will use over and over again as a Marketplace developer.

## What is an app? [](id=what-is-an-app)

As a Liferay Developer, you will undoubtedly already be familiar with the
concept of plugins (portlets, hooks, themes, etc). If not, review chapter 1 of
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

## What is a version? [](id=what-is-a-version)

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

## What is a package? [](id=what-is-a-package)

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

## How do apps relate to users and companies? [](id=how-do-apps-relate-to-users-and-companies)

When publishing an app, it is possible to publish it *on behalf of* yourself (an
individual) or a *company* with which you are associated. The selection you make
determines who has access to the app, once published. To understand the concepts
of a Marketplace user, admin, and company, and the ramifications of choosing one
vs. the other, visit the *Liferay Marketplace* chapter in the User Guide.

## What are the requirements for publishing apps? [](id=what-are-the-requirements-for-publishing-apps)

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

    - *plugin_type* - one of the following: `hook`, `layouttpl`,
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
      define your Portal Access Control List (PACL) in this file. Read 
      Chapter 13 on Plugin Security Management for information on developing
      secure apps. 
- *Deployment contexts*:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as another plugin in the Marketplace.
    - Liferay reserves the right to replace WAR files of app plugins that have
      the same deployment context as plugins built by Liferay.

+$$$

**Important:** If you're developing a paid app or want your free app to satisfy
Liferay's Plugin Security Manager, Chapter 13 on Plugin Security Management for
details. Give yourself adequate time to develop your app's PACL and time to test
your app thoroughly with the security manager enabled. 

$$$

Now that you've learned the packaging and deployment requirements for your app,
let's consider the versions of Liferay you're targetting for your app and how to
prepare your app for them. 

## Things you need before you can publish [](id=things-you-need-before-you-can-publish)

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

## Image and Naming Requirements [](id=image-and-naming-requirements)

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

## What kind of validations are performed by Liferay? [](id=what-kind-of-validations-are-performed-by-liferay)

Liferay will ensure that apps meet a minimum set of requirements, such as:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Basic sanity checking of functionality (e.g. deployment testing, etc)

Liferay does not do source code reviews and will not ask for your source code.
Further, Liferay is not responsible for the behavior (or misbehavior) of apps on
the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.
 
## What versions of Liferay should I target? [](id=what-versions-of-liferay-should-i-target)

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

+$$$

**Note:** If you haven't yet done so, be sure to read the
[Marketplace](/discover/portal/-/knowledge_base/6-1/leveraging-the-liferay-marketplace)
chapter of *Using Liferay Portal*! 

$$$

Now that we have covered the basics, you're armed with enough knowledge to start
creating apps on the Marketplace, so let's see what that looks like in the next
section.
