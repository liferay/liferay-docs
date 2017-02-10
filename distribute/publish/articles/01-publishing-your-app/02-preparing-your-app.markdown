# Preparing Your App [](id=preparing-your-app)

As a Liferay developer, you're undoubtedly already familiar with the concept of
plugins (portlets, themes, etc). If you're not familiar with Liferay
plugins, see the 
[introductory section of Liferay developer tutorials](/develop/tutorials/-/knowledge_base/7-0/introduction).
A *Liferay App* (sometimes just called an *app*) is a collection of one or more
of these plugins, packaged together to represent the full functionality of an
application on the Liferay platform. In addition to the plugins contained within
an app, apps have metadata such as names, descriptions, versions, and other
information used to describe and track the app throughout its life cycle. 

Much like standard Liferay plugins, Liferay apps are also hot-deployable. 
Liferay Marketplace apps are distributed via a special file type with a `.lpkg` 
extension. To deploy these files, drop them into a running Liferay instance's 
hot-deploy folder (`[Liferay_Home]/deploy`), like any other plugin. 

As an app developer, you're not required to create the `.lpkg` files. Instead,
your app's individual plugins (WAR files for traditional plugins or JAR files
for OSGi modules) are uploaded as part of the publication process, along with
information (name, description, version, icon, etc.) that identifies the app.
The publication process is described in detail later.

At this point in preparing to publish your app, you've developed your app. And
if you're preparing a paid app, you've specified a permission descriptor (a
portal access control list
[\(PACL\)](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl) for
traditional plugins or a `OSGI-INF/permissions.perm` file for OSGi modules), so
that your app can be deployed on Liferay instances that have their
[Plugin Security Manager](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl#enabling-the-security-manager) 
running. But before you start the formal publishing process, you must prepare 
your app's files and app metadata. 

## Marketplace App Metadata Guidelines [](id=marketplace-app-metadata-guidelines)

The following app metadata guidelines are intended to ensure that apps are
submitted with important and necessary supporting information. The metadata that
you submit with your app will serve both as necessary information for your app's
buyers (e.g., your contact info) and as promotional assets (e.g., description,
screenshots, etc.) that can help drive traffic to and downloads of your app!

![Figure 1: Check out how good your app can look on the Markeplace.](../../images/dev-portal-app-metadata-guidelines.png)

You'll want to think of a good name and description of your app. If you haven't
already done so, take some screenshots, design an icon, and create a website for 
your app. The table below can help guide you address the Marketplace app 
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
						In some views within the Marketplace, app titles longer 
						than 18 characters will be shortened with an ellipsis. 
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
				Using English as the default langauge, you must provide a
				description for your app. After providing the description in
				English, you can provide other translations of the text.
				<p>
				At a minimum, the description should provide a concise overview
				of what the app does. Great descriptions also list key
				functionalities and what customers can expect to gain by
				deploying your app. If you'd like to see an example, you can
				see our description of the
				<a href="https://web.liferay.com/marketplace/-/mp/application/43707761">Audience Targeting</a>
				app on the
				<a href="https://web.liferay.com/marketplace">Markeplace</a>.
				<p>
					It's important that you specify any plugin dependencies
					(e.g., plugins that must be installed prior to running your
					app) and environment compatibilities (e.g., compatibility
					with specific app servers) here, so that potential buyers 
					and the Liferay app review team are aware of these 
					requirements.
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> What's New?* </td>
			<td class="">
				Using English as the default language, describe what's new and
				improved in your app. After this, you can provide other 
				translations of the text. This field is shown on updating an app 
				that you've already submitted, regardless of whether the app has 
				been published.
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
				Compatibility with Future Minor Releases of Liferay Portal
			</td>
			<td class="">
				Please include a "+" at the end of the latest version when
				specifying version constraints in your
				liferay-plugin-package.properties file (e.g.,
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
				also run under Liferay Digital Enterprise (DE) or  EE portal,
				and vice versa. Specifying compatibility with both DE/EE and CE
				versions of the portal will ensure that a wider audience has
				access to your app!
				<p>
					You can <a href="https://www.liferay.com/web/developer/marketplace/license">request
					a Liferay DE/EE Developer License </a> to support testing and
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
				sequentially, for example fluffy-puppies-01.jpg,
				fluffy-puppies-02.jpg, and so on.
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

During the publication process, you upload your app's individual plugin WAR
files and module JAR files along with the app's metadata (name, description,
version, icon, etc.).

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
action. You can read more about themes in the 
[Themes and Layout Templates tutorials](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates). 

## Deployment Requirements [](id=deployment-requirements)

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins, as explained in the tutorials on creating
[MVC Portlets](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet)
and 
[JSF Portlets](/develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application).

In addition to those requirements, there are some Marketplace-specific ones to
keep in mind:

- **Target the Appropriate Java JRE**: Regardless of the tools you use to 
  develop your app, your app's bytecode must be compatible with the target Java 
  JRE for your version of Liferay. Your app will be rejected if its bytecode is 
  not compatible with the Java JRE for the intended version of Liferay Digital Enterprise or Liferay Portal. Liferay Portal
  6.2 targets Java 1.7, and Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0 target Java 1.8. If you use the Liferay 
  Plugins SDK to develop your app, you can set the Java version by overriding 
  the `ant.build.javac.target` property in the Plugins SDK's `build.properties` 
  file. 

- **WAR (`.war`) files**:
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

- **`WEB-INF/`[`liferay-plugin-package.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
  file:**
    - Property `recommended.deployment.context` must not be set.
    - Setting property `security-manager-enabled` to `true` is mandatory for all
      paid apps on 6.1 CE GA3, 6.1 EE GA3, and later; the setting is optional
      for free apps. Setting this property to `true` enables Liferay's Plugin
      Security Manager. If you're enabling the security manager, you'll also
      need to define your Portal Access Control List (PACL) in this file. Read
      [Plugins Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
      for information on developing secure apps.
- **Deployment contexts**:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as a context of another plugin in the
      Marketplace.
    - Liferay reserves the right to replace app plugin WAR files that have
      the same deployment context as plugins built by Liferay.

There are some additional requirements for uploading Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0 apps:

- **OSGi modules in JAR (`.jar`) files**:
    - For more information, see 
      [OSGi and Modularity - Modules](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity#modules). 
    - The manifest file must at minimum contain the following manifest headers:
        - `Bundle-SymbolicName`: a unique name for the module
        - `Bundle-Version`: the module's version
        - `Web-ContextPath`: the servlet context path

- **WAR (`.war`) files**:
    - WAR-based plugins must be adapted to run on Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0. See 
      [this tutorial](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) 
      for information on adapting WAR plugins that run on Liferay Portal 6.2 and 
      earlier to run on Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0. 

- **Apps containing multiple file types:**
    - Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0 apps can contain a mix of WAR-based plugins and OSGi JAR-based 
      plugins. Regardless of file type, each plugin must be able to run on 
      Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0. 

+$$$

**Important:** If you're developing a paid app or want your free app to satisfy
Liferay's Plugin Security Manager, make sure to specify PACLs for your
traditional plugins and a `OSGI-INF/permissions.perm` file for each of your
modules. See the article [Plugin Security Manager](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Portal+Security+Manager) 
for details. Give yourself adequate time to develop your app's permission
descriptors and time to test your app thoroughly with the security manager
enabled. 

$$$

Apps usually consist of multiple plugins (e.g., multiple WAR or JAR files) and 
plugin types. In addition, you may want to consider how to package your app for 
running on different Liferay versions. 

## Considering Package Variations to Target Different Versions of Liferay [](id=considering-package-variations-to-target-different-versions-of-liferay)

Apps can be written to work across many different versions of Liferay. For
example, suppose you want to publish version 1.0 of your app, which you're
supporting on Liferay Portal 6.1 and 6.2. Due to incompatibilities between these
Liferay versions, it may be impossible to create a single binary WAR or JAR file 
that works across both Liferay versions. In this case, you must compile your app 
twice: once against Liferay Portal 6.1 and once against 6.2, producing 2 different
*packages* (also called variations) of your version 1.0 app. Each package has
the same functionality, but they're different files. You can upload such 
packages to support your app on different Liferay versions. With regards to 
Liferay apps, packages are sometimes referred to as files that make up your app. 

Now that you've prepared your app's files and specified its metadata, it's
time to get it to submit it to Liferay for publishing on the Marketplace!
