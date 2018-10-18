# Marketplace App Metadata Guidelines [](id=marketplace-app-metadata-guidelines)

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
