# Marketplace Basics [](id=marketplace-basics)

Before diving into the details of developing for the Marketplace, it is
important that you have a good grasp of the concepts introduced in the
Marketplace. The following sections discuss these concepts.

## What is an App?

As a Liferay developer, you're undoubtedly already familiar with the concept
of plugins (portlets, hooks, themes, etc). If not, see the
[Tutorials Introduction](/develop/tutorials/-/knowledge_base/6-2/tutorials).
A *Liferay App* (sometimes just called an *app*) is a collection of one or more
of these plugins, packaged together to represent the full functionality of an
application on the Liferay platform. In addition to the plugins contained within
an app, apps have meta-data such as names, descriptions, versions, and other
ancillary information used to describe and track the app throughout its
life cycle.

Much like standard Liferay plugins, Liferay apps are also *hot-deployable*. On
downloading an app from the Marketplace, you'll find that it is a special file
type with a `.lpkg` extension. This file can be dropped into Liferay's
hot-deploy folder (`liferay-portal-[version]/deploy`) like any other plugin, to
deploy it into that running instance of Liferay Portal.

As an app developer, you're not required to create the `.lpkg` files.
Instead, your app's individual plugins (`.war` files) are uploaded as part of
the publication process, along with information (name, description, version,
icon, etc.) that identifies the app. The publication process is described in
detail later.

## What is a Version?

The concept of versions is well known in software, and it is no different
here. A version of an app represents the functionality of the app at a given
point in time. When you first create an app, you give it an initial version
(e.g., `1.0`). On updating the app, you increment its version (e.g., from `1.0`
to `1.1`), and you upload new files representing that version of the app. In some
cases, you may want to specify additional qualifiers in order to convey a
special meaning. For example, you may declare that the version of your app is
always in x.y.z format (where you've clearly defined the significance of each x,
y, and z). Liferay Portal versions and official Liferay app versions use this
format.

In any case, you have complete freedom in how you wish to assign version
designators to your app. It is highly recommended that you stick to a well known
and easily understandable format, such as `1.0`, `1.1`, `1.2`, and so on.
Although you may want to include alphabetical characters (e.g., `1.0 Beta 2` or
`6.3 Patch 123235-01`), we discourage it, as it can make it difficult for people
to understand how the app versions relate to one another.

Keep in mind that the version of your app is completely up to you to specify,
but the releases of Liferay with which your app works must be specified using
Liferay's versioning scheme, as explained in
[Understanding Liferay's Releases](/discover/portal/-/knowledge_base/6-2/understanding-liferays-releases).
See the later section *Specify App Packaging Directives* for details on
specifying the releases of Liferay for which your app is designed. 

## What is a Package?

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

## How Do Apps Relate to Users and Companies?

When publishing an app, it is possible to publish it on behalf of yourself (an
individual) or a *company* with which you are associated. The selection you make
determines who has access to the app, once published. To understand the concepts
of a Marketplace user, portal administrator, and company, and the ramifications
of publishing apps as an individual versus publishing apps as part of a company,
see the
[Leveraging the Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace)
chapter of Using Liferay Portal.

## What Are the Requirements for Publishing Apps?

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins, as explained in the tutorials on creating
[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/creating-a-liferay-mvc-portlet-project)
and creating
[JSF Portlets](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-and-deploying-jsf-portlets).

In addition to those requirements, there are some Marketplace-specific ones to
keep in mind:

- *Target the Java 6 JRE*: Your app's bytecode must be compatible with Java 6
  (i.e., Java 1.6). Liferay's Plugins SDK already targets Java 6 via the
  `build.properties` setting `ant.build.javac.target=1.6`; so don't override
  this setting. Your app will be rejected if its byte code is not compatible
  with Java 6. 
- *WAR (`.war`) files*:
    - WARs must contain a `WEB-INF/liferay-plugin-package.properties` file.
    - WARs must not contain any `WEB-INF/liferay-plugin-package.xml` file.
    - WAR file names must not contain any commas.
    - WAR file names must conform to the following naming convention:
    
      *context_name*`-`*plugin_type*`-A.B.C.D.war`

      Where:

    - *context_name*: Alpha-numeric (including `-` and `_`) short name of
      your app.  This name is used as the deployment context, and must not
      duplicate any other app's context (you'll be warned if you use a
      context name of any other app on the Marketplace).

    - *plugin_type*: one of the following: `hook`, `layouttpl`,
      `portlet`, `theme`, or `web`.

    - `A.B.C.D`: The 4 digit version of your WAR file.  4 digits must
       be used.

      Example: `myapp-portlet-1.0.0.0.war`

- `WEB-INF/`[`liferay-plugin-package.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
  file:
    - Property `recommended.deployment.context` must not be set.
    - Setting property `security-manager-enabled` to `true` is mandatory for all
      paid apps on 6.1 CE GA3, 6.1 EE GA3, and later; the setting is optional
      for free apps. Setting this property to `true` enables Liferay's Plugin
      Security Manager. If you're enabling the security manager, you'll also
      need to define your Portal Access Control List (PACL) in this file.  Read
      [Developing Plugins with Security in Mind](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-with-security-in-mind-liferay-portal-6-2-dev-guide-11-en)
      for information on developing secure apps. 
- *Deployment contexts*:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as a context of another plugin in the
      Marketplace. 
    - Liferay reserves the right to replace app plugin WAR files that have
      the same deployment context as plugins built by Liferay.

+$$$

**Important:** If you're developing a
 paid app or want your free app to satisfy Liferay's Plugin Security Manager,
 see the tutorial
 [Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl),
 for details. Give yourself adequate time to develop your app's PACL and time to
 test your app thoroughly with the security manager enabled. 

$$$

Now that you've learned the packaging and deployment requirements for your app,
let's consider the versions of Liferay you're targeting for your app and how to
prepare your app for them. 

## Things You Need Before You Can Publish

You must first develop your app using your preferred development tool. For
example, using
[Liferay IDE or Liferay Developer Studio](/develop/tutorials/-/knowledge_base/6-2/liferay-ide),
the [Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk),
or [Maven](/develop/tutorials/-/knowledge_base/6-2/maven).
Your app will consist of one or more Liferay plugins. Ensure your app is
designed to work with Liferay 6.1 or later. If you wish to target multiple
versions of Liferay (for example, you may wish to support 6.2 EE SP1, 6.2 CE
GA1, 6.1 EE GA3, and 6.1 CE GA3), ensure you have built binary images of your
app for each supported minor family release, if necessary. If a single set of
files will work across all supported Liferay versions, you do not need to build
multiple plugins. Liferay guarantees compatibility within a given minor release
family, so your users can rest assured that your app will work with the minor
release that you specify, along with all future maintenance releases of that
minor release. 

Next, think of a good name and description of your app, along with the version
scheme you wish to use. Take some screenshots, design an icon, create web sites
for your app (if they do not already exist), and have a support plan in place.

## Image and Naming Requirements

**Icons** for your app *must be* exactly 90 pixels in both height and width and
must be in PNG, JPG, or GIF format. The image size cannot exceed 512KB. Animated
images are prohibited.

**Screenshots** for your app *must not exceed* 1080 pixels in width x 678 pixels
in height and must be in the JPG format. The file size of each screenshot *must
not exceed* 384KB. Each screenshot should preferably be the same size (each will
be automatically scaled to match the aspect ratio of the above dimensions), and
it is preferable if they are named sequentially, for example
`fluffy-puppies-01.png`, `fluffy-puppies-02.png`, and so on.

**Titles of Apps**: In some views with Marketplace, titles of applications
longer than 18 characters are shortened using an ellipsis. In the Marketplace,
titles *must not be* longer than 50 characters.

**Description, Tags, Websites and Version Numbers**: Descriptions, web sites and
version numbers are to be as reflective of the product as possible. Please do
not use misleading names, information, or icons. A tags suggestion tool has been
provided to aid with tagging your asset. Descriptions should be as concise as
possible. Ensure your icons, images, descriptions, and tags are free of
profanity or other offensive material.

Above and beyond these basics of creating apps in the form of Liferay plugins,
there are additional considerations to take into account when designing and
publishing apps.

## What Kind of Validations Are Performed by Liferay?

Liferay ensures that apps meet a minimum set of requirements by performing the
following activities:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Doing basic sanity checking of functionality (e.g., deployment testing, etc.)

Liferay does not do source code reviews and will not ask for your source code.
Further, Liferay is not responsible for the behavior (or misbehavior) of apps on
the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.
 
## What Versions of Liferay Should I Target?

Of course, targeting the widest possible range of Liferay versions in an app
typically draws larger audiences to the app. And there are certain features in
specific versions of Liferay that you may wish to take advantage of. When
uploading apps, you can specify which versions your app is compatible with and
you can have multiple files for your app that are designed for different
versions of the Liferay Platform.

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 or
later. That's not to say that they can't work with prior versions. However, only
Liferay 6.1 and later versions provide support for installing apps directly from
the Marketplace and provide safeguards against malicious apps. If you wish to
use an app with an earlier version of Liferay, consult the documentation for
that app, as it may or may not be supported.

Read the section below for details on how to specify support for specific
versions of Liferay. 

+$$$

**Note:** If you haven't yet done so,
 make sure to read the chapter
 [Leveraging the Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace)
 from *Using Liferay Portal*! 

$$$

Now that we've covered the basics, you're armed with knowledge to start
creating apps on the Marketplace, so let's see what that looks like in the next
section.

