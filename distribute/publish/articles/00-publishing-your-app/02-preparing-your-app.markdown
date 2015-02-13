# Preparing Your App [](id=preparing-your-app)

<!-- TODO intro -->

You must first develop your app using your preferred development tool. For
example, using
[Liferay IDE or Liferay Developer Studio](/develop/tutorials/-/knowledge_base/6-2/liferay-ide),
the [Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk),
or [Maven](/develop/tutorials/-/knowledge_base/6-2/maven).
An app will consists of one or more Liferay plugins. Ensure your app is designed
to work with Liferay 6.1 or later. If you wish to target multiple versions of
Liferay (for example, you may wish to support 6.2 EE SP1, 6.2 CE GA1, 6.1 EE
GA3, and 6.1 CE GA3), ensure you have built binary images of your app for each
supported minor family release, if necessary. If a single set of files works
across all supported Liferay versions, you don't need to build multiple versions
of the plugins. Liferay guarantees compatibility within a given minor release
family, so your users can rest assured that your app works with the minor
release that you specify, along with all future maintenance releases of that
minor release. 

## Image and Naming Requirements [](id=image-and-naming-requirements)

You'll want to think of a good name and description of your app, along with the version
scheme you wish to use. Take some screenshots, design an icon, create web sites
for your app (if they do not already exist), and have a support plan in place.

**Icons** for your app *must be* exactly 90 pixels in both height and width and
must be in GIF, JPEG/JPG, or PNG format. The image size cannot exceed 512KB.
Animated images are prohibited. 

**Screenshots** for your app *must not exceed* 1080 pixels in width x 678 pixels
in height and must be in  GIF, JPEG/JPG, or PNG format. The file size of each
screenshot *must not exceed* 384KB. Each screenshot should preferably be the
same size (each will be automatically scaled to match the aspect ratio of the
above dimensions), and it is preferable if they are named sequentially, for
example `fluffy-puppies-01.png`, `fluffy-puppies-02.png`, and so on. 

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

During the publication process, you upload your app's individual plugins (`.war`
files) along with its meta-data (name, description, version, icon,
etc.). The publication process is described in detail
later.

## Deployment Requirements [](id=deployment-requirements)

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins, as explained in the tutorials on creating
[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/creating-a-liferay-mvc-portlet-project)
and creating
[JSF Portlets](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-and-deploying-jsf-portlets).

In addition to those requirements, there are some Marketplace-specific ones to
keep in mind:

- *Target the Appropriate Java JRE*: Your app's bytecode must be compatible with
  the target Java JRE for your version of Liferay. For example, Liferay 6.2
  targets Java 1.7. The Liferay Plugins SDK for each version of Liferay targets
  a version of Java via the property `ant.build.javac.target` in the
  Plugins SDK's `build.properties` file; so don't override
  this setting. Your app will be rejected if its bytecode is not compatible
  with the Java JRE for the intended version of Liferay. 
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
      [Plugins Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
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

<!--
Now that you've learned the packaging and deployment requirements for your app,
let's ...
-->

<!-- Incorporate content from https://www.liferay.com/web/developer/marketplace/app-metadata-guidelines -->


In the real world, apps usually consist of multiple components (e.g., multiple
`.war` file plugins), are spread across multiple plugin types, and present
non-trivial functionality which in many cases requires some configuration. How
these advanced tasks are dealt with is out of scope for this section, but some
tips and considerations for Marketplace development can be found in the sections
that follow.

## Specify App Packaging Directives [](id=specify-app-packaging-directives)

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
can be found in 
[Understanding Liferay's Releases](/discover/portal/-/knowledge_base/6-2/understanding-liferays-releases). 
Accordingly, Liferay 6.2 CE GA1 is designated as version `6.2.0`. CE GA2 is then
`6.2.1`, and so on. Liferay 6.2 EE GA1 is designated as `6.2.10`. EE versions 
follow a slightly different policy given the presence of fix packs and service
packs, so 6.2 EE GA2 will be `6.2.20`.

For each plugin that makes up your app, packaging directives must be placed in
the `liferay-plugin-package.properties` file (in the `WEB-INF/` directory of
your plugin's `.war` file). In this file, you must specify a comma-separated
list of Liferay releases with which your app is compatible and for which
packages should be generated using the `liferay-versions` keyword. Marketplace
creates packages that contain your plugins based on these packaging
directives (and groups them together as each plugin is uploaded). You should
specify CE versions first, followed by EE versions, using this form:
`liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE` are replaced with
the corresponding Liferay Releases with which your app is compatible).

+$$$

**Note:** If your app is compatible
 with both CE and EE, you must specify a set of versions for both CE and EE
 releases. If you only specify compatibility with CE, then your app will not be
 compatible with (and will fail to deploy to) any EE release.

$$$
 
For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA3 (and later), and 6.1 EE GA3 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.2+,6.1.30+

This means that the app works with any 6.1 CE release starting with CE GA3
and 6.1 EE releases starting with EE GA3. Marketplace creates two packages:
one that is compatible with the 6.1 CE GA3 release and *later*, and another that
is compatible with 6.1 EE GA3 release and *later*.

+$$$

**Note:** Any CE or EE versions you
 include in your packaging directives *must* be terminated with a version using
 the `+` symbol.  This ensures that your app will be deployable onto future
 versions of Liferay (but does not guarantee your app will work in future
 versions). So, `liferay-versions=6.1.1,6.1.2` doesn't work, but
 `liferay-versions=6.1.1,6.1.2+` does. Similarly,
 `liferay-versions=6.1.2+,6.1.30,6.1.31` doesn't work (as the EE versions are
 not properly terminated), but `liferay=versions=6.1.2+,6.1.30,6.1.31+` does.

$$$

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

### Example App: Using Different Versions of a Hook [](id=example-app-using-different-versions-of-a-hook)

Suppose your app consists of two plugins: a portlet and a hook. The portlet uses
standard API calls that work on all Liferay 6.1 releases. Your hook, on the
other hand, needs to interact with EE GA3 differently than it does with CE GA3,
because you want the hook to take advantage of an exclusive EE feature. For your
app, how do you provide one version of your hook plugin for EE and another
version of it for CE, while applying your portlet plugin to both EE and CE? 

It's easy. In this case, you'd specify versions
`liferay-versions=6.1.2+,6.1.30+` for your portlet plugin, indicating that it is
compatible with CE GA3 and later, as well as EE GA3 and later. As for your hook
plugin, you'd create and build *two* versions of it, one version of the hook to
use with Liferay EE and the other version of the hook to use with Liferay CE.
You'd specify `liferay-versions=6.1.30+` for your EE hook and
`liferay-versions=6.1.2+` for your CE hook. The EE hook would work exclusively
with EE GA3 and later, while the CE hook would work exclusively with CE GA3 and
later. You might think that it's difficult to arrange the packaging for an app
that has plugins targeted to different Liferay releases, but it's easy.
Marketplace takes care of it based on the `liferay-versions` values you
specified for each plugin. We'll talk about that next.

### Marketplace Packages Your App's Plugins [](id=marketplace-packages-your-apps-plugins)

When you upload your app's plugins as demonstrated later, you'll notice that
Marketplace groups them into separate packages based on the respective releases
each plugin supports. Marketplace copies a plugin into each of the release
packages corresponding to its list of `liferay-versions` values.  If Marketplace
cannot verify the version of Liferay the plugin supports, it rejects the plugin.
For example, if you specify `liferay-versions=1.0.0+` for your plugin--perhaps
because you are confident it can work on any Liferay release--Marketplace
rejects it, because it doesn't know of a `1.0` release of Liferay. So take care
to specify the Liferay version information correctly for each your app's plugins. 

Now that you've developed your app and specified its packaging directives, it's
time to get it to the Marketplace! 

## What Kind of Validations Are Performed by Liferay? [](id=what-kind-of-validations-are-performed-by-liferay)

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
