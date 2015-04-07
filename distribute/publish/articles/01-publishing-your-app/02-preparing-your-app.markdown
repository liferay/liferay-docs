# Preparing Your App [](id=preparing-your-app)

As a Liferay developer, you're undoubtedly already familiar with the concept of
plugins (portlets, hooks, themes, etc). If you're not familiar with Liferay
plugins, see the [Tutorials Introduction](/develop/tutorials/-/knowledge_base/6-2/tutorials).
A *Liferay App* (sometimes just called an *app*) is a collection of one or more
of these plugins, packaged together to represent the full functionality of an
application on the Liferay platform. In addition to the plugins contained within
an app, apps have metadata such as names, descriptions, versions, and other
ancillary information used to describe and track the app throughout its life
cycle.

Much like standard Liferay plugins, Liferay apps are also *hot-deployable*. On
downloading an app from the Marketplace, you'll find that it is a special file
type with a `.lpkg` extension. This file can be dropped into Liferay's
hot-deploy folder (`liferay-portal-[version]/deploy`) like any other plugin, to
deploy it into that running instance of Liferay Portal.

As an app developer, you're not required to create the `.lpkg` files. Instead,
your app's individual plugins (`.war` files) are uploaded as part of the
publication process, along with information (name, description, version, icon,
etc.) that identifies the app. The publication process is described in detail
later.

At this point in preparing to publish your app, you've developed your app and,
if you're preparing a paid app, you've specified a portal access control list [\(PACL\)](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl),
so that your app can be deployed on Liferay instances that have their [Plugin Security Manager](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl#enabling-the-security-manager)
running. But before you start the formal publishing process, you must prepare
your app's files and app metadata. 

## Marketplace App Metadata Guidelines [](id=marketplace-app-metadata-guidelines)

The following app metadata guidelines are intended to ensure that apps are
submitted with important and necessary supporting information. The metadata that
you submit with your app will serve both as necessary information for your app's
buyers (eg., your contact info) and as promotional assets (eg., description,
screenshots, etc.) that can help drive traffic to and downloads of your app!

![Figure 1: Check out how good your app can look on the Markeplace.](../../images/dev-portal-app-metadata-guidelines.png)

You'll want to think of a good name and description of your app. if you haven't
already done so. take some screenshots, design an icon, and create a web site
for your app. The table below, can help guide you address the Marketplace app
metadata requirements and produce an appealing advertisement for your app.

**Marketplace App Metadata Guidelines:**

<style>
.lego-image {
	max-height: 100%;
	max-width: 100%;
}
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max
.max-960 {
	max-width: none;
}
.metadata-guidelines-table td {
	border-bottom: 1px solid;
	border-top: 1px solid;
	padding: 10px;
}
.table-header {
	font-weight: bold;
}
.table-header.second {
	width: 70%;
}
.left-header {
	border-right: 1px solid;
}
</style>
<div class="lego-article metadata-guidelines-table" id="article-33460946">
<div class="lego-article-content max-960">
<div class="aui-helper-clearfix lego-section section-1" >
<div class="aui-w100 block-1 content-column lego-block" >
<div class="content-column-content">
<table>
	<thead>
		<td class="table-header left-header">
			Required Metadata
		</td>
		<td class="table-header second">
			Description
		</td>
	</thead>
	<tbody>
		<tr>
			<td class="table-header left-header">
				App Name
			</td>
			<td class="">
				This is probably the most important branding element of your
				app, so be creative! Some important things to keep in mind:
				<ul>
					<li>
						In some views within the Marketplace, titles of
						applications longer than 18 characters will be shortened
						with ellipsis.
					</li>
					<li>Titles must not be longer than 50 characters.</li>
					<li>
						App title may contain the word "Liferay" to describe its
						use or intent as long as the name does not imply
						official certification or validation from Liferay, Inc.
						(For example, names such as "Exchange Connector for
						Liferay" or "Integration Connector Kit for Liferay"
						would be allowed, while "Liferay Mail Portlet," "Liferay
						Management Console," or "Liferay UI Kit" would not be
						permissible without explicit approval). Please refer to
						our <a href="https://www.liferay.com/trademark">
						trademark policy</a> for details.  
					</li>
					<li>
					    Please try to conform the app name, as closely as
					    possible, to the actual plugin (portlet, hook, theme,
					    etc.) name.
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Support Information </td>
			<td class="">
				<p>
					Please include an email address, contact information, and/or
					website URL for the "Support" field. If someone encounters
					an issue with your app, they will need a way to contact you.
				<p>
				    Even if you choose not to offer Support Services (by
				    unchecking "Offer Support" during the app submission
				    process), we require that you provide support contact
				    information so that buyers can reach you with general
				    questions about your app. 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Description </td>
			<td class="">
				Please provide a description for your app in English. At a
				minimum, the description should provide a concise overview of
				what the app does. Great descriptions also list key
				functionalities and what customers can expect to gain by
				deploying your app. If you'd like to see an example, you can
				check out our description of the Social Office app on the
				<a href="https://www.liferay.com/marketplace">Markeplace</a>. 
				<p>
					It's important that you specify any plugin dependencies
					(eg., plugins that must be installed prior to running your
					app) and environment compatibilities (eg., compatibility
					with specific app servers) here, so that both potential
					buyers and the Liferay app review team will be aware of
					these requirements.
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
				Compatibility with Future Minor Releases of Liferay Portal
			</td>
			<td class="">
				Please include a "+" at the end of the latest version when
				specifying version constraints in your
				liferay-plugin-package.properties file (eg.,
				"liferay-versions=6.1.1+, 6.1.20+"). This will ensure that your
				app will continue to be deployable to future versions of Liferay
				within a minor release. If, in the future, you discover your app
				does NOT work with a particular Liferay version, you can modify
				the list to exclude that version.
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
				Increase Your Potential User Base </td>
			<td class="">
				In most cases, an app that is compatible with CE portal will
				also run under EE portal, and vice versa. Specifying
				compatibility with both EE and CE versions of the portal will
				ensure that a wider audience has access to your app!
				<p>
					You can <a href="/web/developer/marketplace/license">request
					a Liferay EE Developer License </a> to support testing and
					confirm compatibility.
				</p>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Icon </td>
			<td class="">
				Icons for your app must be exactly 90 pixels in both height and
				width and must be in PNG, JPG, or GIF format. The image size
				cannot exceed 512kb. Animated images are prohibited.
				<p>
					The use of the Liferay logo, including any permitted
					alternate versions of the Liferay logo, is permitted only
					with Liferay's explicit approval. Please refer to our <a
					href="https://www.liferay.com/trademark">trademark
					policy</a> for details.
				</p>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Screenshots </td>
			<td class=""> Screenshots for your app must not exceed 1080 pixels
				in width x 678 pixels in height and must be in the JPG format.
				The file size of each screenshot must not exceed 384KB. Each
				screenshot should preferably be the same size (each will be
				automatically scaled to match the aspect ratio of the above
				dimensions), and it is preferable if they are named
				sequentially, for example fluffy-puppies-01.png,
				fluffy-puppies-02.png, and so on.
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

Make sure your icons, images, descriptions, and tags are free of profanity or
other offensive material. 

During the publication process, you upload your app's individual plugins (`.war`
files) along with its metadata (name, description, version, icon,
etc.). 

**Additional Requirements for Themes/Site Templates**
 
A theme without content is like an empty house. If you're trying to sell an
empty house, it may be difficult for prospective buyers to see its full beauty.
However, staging the house with some furniture and decorations helps prospective
buyers imagine what the house might look like with their belongings. Liferay's
resources importer application is a tool that allows a theme developer to have
files and web content automatically imported into the portal when a theme is
deployed.

All standalone themes that are uploaded to Liferay Marketplace must use the
resources importer to include files/web content to provide a sample context for
their theme. This ensures a uniform experience for Marketplace users: a user can
download a theme from Marketplace, install it on their portal, go to Sites or
Site Templates in the Control Panel and immediately see their new theme in
action. You can read more about themes and even find examples in the [Tutorials](/develop/tutorials).

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
    - WAR files must contain a `WEB-INF/liferay-plugin-package.properties` file.
    - WAR files must not contain any `WEB-INF/liferay-plugin-package.xml` file.
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

Apps usually consist of multiple components (e.g., multiple
`.war` file plugins) that are spread across multiple plugin types. In addition,
you may want to consider how to package your app for running on different
versions of Liferay. 

## Considering Package Variations to Target Different Versions of Liferay [](id=considering-package-variations-to-target-different-versions-of-liferay)

Apps can be written to work across many different versions of Liferay. For
example, suppose you want to publish version 1.0 of your app, which you're
supporting on Liferay 6.1 and 6.2. Due to incompatibilities between these
Liferay versions, it may not be possible to create a single binary `.war` file
that works across both Liferay versions. In this case, you must compile your app
twice: once against Liferay 6.1 and once against 6.2, producing 2 different
*packages* (also called variations) of your version 1.0 app. Each package has
the same functionality, but they're different files. You can upload such
packages to support your app on different versions of Liferay. With regards to
Liferay apps, packages are sometimes referred to as files that make up your app.

Next, you'll drill down into the app packaging directives for each of your app's
plugin projects. 

## Specifying Packaging Directives [](id=specifying-packaging-directives)

When publishing your app, each plugin you upload is packaged into one or more
*packages* for each Liferay release you're supporting. When you upload your
plugins to the Liferay Marketplace, your app is scanned, and the embedded
packaging directives you've specified are extracted and used to create different
downloadable *packages* of your app for the different Liferay releases. You must
insert this information into each plugin in your app before you can publish it
to the Marketplace.

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
packs; so 6.2 EE GA2 is `6.2.20`.

For each plugin that makes up your app, packaging directives must be specified
in the `liferay-plugin-package.properties` file (in the `WEB-INF/` directory of
your plugin's `.war` file). In this properties file, you must specify a
comma-separated list of Liferay releases with which your app is compatible and
for which packages should be generated using the `liferay-versions` keyword.
Marketplace creates packages that contain your plugins based on these packaging
directives (and groups them together as each plugin is uploaded). You should
specify CE versions first, followed by EE versions, using this form:
`liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE` are replaced with
the corresponding Liferay Releases with which your app is compatible).

+$$$

**Note:** If your app is compatible with both CE and EE, you must specify a set
of versions for both CE and EE releases. If you only specify compatibility with
CE, then your app will not be compatible with (and will fail to deploy to) any
EE release.

$$$
 
For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA3 (and later), and 6.1 EE GA3 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.2+,6.1.30+

This means that the app works with any 6.1 CE release starting with CE GA3 and
6.1 EE releases starting with EE GA3. Marketplace creates two packages: one that
is compatible with the 6.1 CE GA3 release and *later*, and another that is
compatible with 6.1 EE GA3 release and *later*.

+$$$

**Note:** Any CE or EE versions you include in your packaging directives *must*
be terminated with a version using the plus (`+`) symbol. This ensures that your
app will be deployable onto future versions of Liferay (but does not guarantee
your app will work in future versions). So, `liferay-versions=6.1.1,6.1.2`
doesn't work, but `liferay-versions=6.1.1,6.1.2+` does. Similarly,
`liferay-versions=6.1.2+,6.1.30,6.1.31` doesn't work (as the EE versions are not
properly terminated), but `liferay=versions=6.1.2+,6.1.30,6.1.31+` does.

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
ways, customizing that plugin for different Liferay releases. The section below
contains an example that demonstrates doing this. 

+$$$

**Example: Using Different Versions of a Hook in an App**

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

$$$

Now that you've prepared your app's files and specified its metadata, it's
time to get it to submit it to Liferay for publishing on the Marketplace! 
