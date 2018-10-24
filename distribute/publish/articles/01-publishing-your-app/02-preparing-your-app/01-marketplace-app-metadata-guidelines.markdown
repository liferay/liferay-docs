# Marketplace App Metadata Guidelines [](id=marketplace-app-metadata-guidelines)

The following app metadata guidelines ensure that apps are submitted with
important and necessary supporting information. The metadata that you submit
with your app serves both as necessary information for your app's buyers (e.g.,
your contact info) and as promotional assets (e.g., description, screenshots,
etc.) that can help drive traffic to and downloads of your app!

![Figure 1: Check out how good your app can look on the Marketplace.](../../images/dev-portal-app-metadata-guidelines.png)

Think of a good name and description for your app. If you haven't already done
so, take some screenshots, design an icon, and create a website for your app.
The table below helps you address the Marketplace app metadata requirements and
produce an appealing app advertisement.

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
						than 18 characters are shortened with an ellipsis. 
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
					    Please try to conform the app name as closely as
					    possible to the actual plugin (portlet, module, theme,
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
					website URL for the "Support" field. If there's an issue
					with your app, they should be a way to contact you.
                </p>
				<p>
					If you choose not to offer Support Services (by unchecking
					"Offer Support" during the app submission process), you
					must provide support contact information so that buyers can
					ask you general questions about your app.
                </p>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Description </td>
			<td class="">
                <p>
				    Using English as the default language, you must provide a
				    description for your app. After providing the description in
				    English, you can provide other translations of the text. <p>
				    At a minimum, the description should provide a concise
				    overview of what the app does. Great descriptions also list
				    key functionality and what customers can expect to gain by
				    deploying your app. For a good description example, see the
				    <a
				    href="https://web.liferay.com/marketplace/-/mp/application/43707761">Audience
				    Targeting</a> app on the
				    <a href="https://web.liferay.com/marketplace">Marketplace</a>.
                </p>
				<p>
					Specify any plugin dependencies (plugins that must be
					installed prior to running your app) and environment
					compatibilities (compatibility with specific app
					servers) here, so that potential buyers and the Liferay app
					review team are aware of these requirements.
                </p>
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
				Compatibility with Future Minor Releases of Liferay
			</td>
			<td class="">
				Please include a "+" at the end of the latest version when
				specifying version constraints in your
				liferay-plugin-package.properties file (e.g.,
				"liferay-versions=6.1.1+, 6.1.20+"). This ensures that the app
				continues to be deployable to future @product@ versions within
				a minor release. If, in the future, you discover your app does
				NOT work with a particular version, you can modify the list to
				exclude that version.
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
				Increase Your Potential User Base </td>
			<td class="">
                <p>
				    In most cases, an app compatible with Liferay Portal
				    CE also runs on Liferay Digital Experience Platform
				    (DXP) (or Liferay Portal EE), and vice versa. Specifying
				    compatibility with both DXP/EE and CE versions of Liferay
				    ensures a wider audience for your app!
                </p>
				<p>
					You can <a
					href="https://www.liferay.com/web/developer/marketplace/license">request
					a Developer License </a> to support testing and confirm
					compatibility.
				</p>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Icon </td>
			<td class="">
				<p>
                    App icons must be exactly 90 pixels in both height and
					width in PNG, JPG, or GIF format. The image size cannot
					exceed 512kb. Animated images are prohibited.
                </p>
				<p>
					The use of the Liferay logo, including alternate versions
					of the Liferay logo, is permitted only with Liferay's
					explicit approval. Please refer to our <a
					href="https://www.liferay.com/trademark">trademark
					policy</a> for details.
				</p>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header"> Screenshots </td>
			<td class=""> App screenshots must not exceed 1080 pixels
				in width x 678 pixels in height and must be in the JPG format.
				Each screenshot's file size must not exceed 384KB. Each
				screenshot should be the same size (each is automatically
				scaled to match the aspect ratio of the above dimensions), and
				it is preferable to name screenshots sequentially, for example
				fluffy-puppies-01.jpg, fluffy-puppies-02.jpg, and so on.
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

## Additional Requirements for Themes/Site Templates

The
[Resources Importer](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme)
includes files and web content that imports automatically into Liferay on theme
deployment. 

All standalone themes uploaded to Liferay Marketplace must use the Resources
Importer to include files/web content to provide a sample context for their
theme. This ensures a uniform experience for Marketplace users: users can
download a theme from Marketplace, install it, go to Sites or Site Templates in
the Control Panel and immediately see the new theme in action. The 
[Themes tutorials](/develop/tutorials/-/knowledge_base/7-1/introduction-to-themes)
provide details. 
