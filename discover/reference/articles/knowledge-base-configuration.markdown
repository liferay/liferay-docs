# Knowledge Base Configuration [](id=knowledge-base-configuration)

Here you'll find a list of the available system settings for each Knowledge Base
application's configuration, what the corresponding portlet properties were if
you're migrating from Liferay 6.2., and how to override configuration settings. 

This reference doc is organized into the following sections:

- Knowledge Base System Settings
- Overriding Settings

## Knowledge Base System Settings [](id=knowledge-base-system-settings)

The next few sections show the available system settings for each Knowledge Base 
application along with possible values. Note that any configuration changes
you make in System Settings will set the default configuration for the 
corresponding application across all sites. The default values can be
overwritten by the individual application instance in your site through the 
*Configuration* menu of the portlet.

### Knowledge Base Article Configuration [](id=knowledge-base-article-configuration)

-  **Resource Prim key:** Sets the primary key that identifies a particular 
   Knowledge Base article or Knowledge Base folder (depending on the resource 
   class name ID). The default value is `0`.
   
-  **Resource class name id:** Sets the class name of either the kb article or 
   Knowledge Base folder. This value needs to match the value of the 
   `Resource Prim key`. If the classname is a Knowledge Base article, the 
   resource prim key needs to be the prim key of a Knowledge Base article. If 
   the class name belongs to a Knowledge Base folder the resource prim key needs 
   to belong to a Knowledge Base folder. The default value is `0`.

-  **Enable KBArticle description:** Sets whether to display the description
   field in the edit view of the application. This is disabled by default.

-  **Enable KBArticle ratings:** Sets whether to allow users to add ratings to
   the Knowledge Base Article. This is enabled by default.

-  **Show KBArticle asset entries:** Sets whether to show the asset entries for
   the Knowledge Base Article. This is displayed by default.

-  **Show KBArticle attachments:** Sets whether to show the attachments for the 
   Knowledge Base article. This is displayed by default.

-  **Show KBArticle asset links:** Sets whether to show the asset links for the
   Knowledge Base Article. This is displayed by default.

-  **Enable KBArticle view count increment:** Sets whether to add a view counter 
   to the Knowledge Base article. This is disabled by default.
   
-  **Enable KBArticle subscriptions:** Sets whether to allow user to subscribe
   to the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle history:** Sets whether to allow users to view the history
   for the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle print:** Sets whether to give users the option to print
   the Knowledge Base article. This is enabled by default.
   
-  **Enable social bookmarks:** Sets whether to display social bookmarks 
   when viewing a Knowledge Base article. This is disabled by default.
   
-  **Social bookmarks display style:** Sets the display style of the social
   bookmarks. Possible values are `menu`, `simple`, `vertical`, or `horizontal`.
   The default value is `menu`.
   
-  **Social bookmarks display position:** Sets the position to display social 
   bookmarks. Possible values are `top` or `bottom`. The default value is 
   `bottom`.
   
-  **Social bookmarks types:** A comma-separated list of the possible bookmark 
   types (twitter,facebook,plusone for example). Overwrite with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.

### Knowledge Base Display Configuration [](id=knowledge-base-display-configuration)

-  **Resource Prim key:** Sets the primary key that identifies a particular 
   Knowledge Base article or Knowledge Base folder (depending on the resource 
   class name ID). The default value is `0`.
   
-  **Resource class name id:** Sets the class name of either the kb article or 
   Knowledge Base folder. This value needs to match the value of the 
   `Resource Prim key`. If the classname is a Knowledge Base article, the 
   resource prim key needs to be the prim key of a Knowledge Base article. If 
   the class name belongs to a Knowledge Base folder the resource prim key needs 
   to belong to a Knowledge Base folder. The default value is `0`.

-  **Enable KBArticle description:** Sets whether to display the description
   field in the edit view of the application. This is disabled by default.

-  **Enable KBArticle ratings:** Sets whether to allow users to add ratings to
   the Knowledge Base Article. This is enabled by default.

-  **Show KBArticle asset entries:** Sets whether to show the asset entries for
   the Knowledge Base Article. This is displayed by default.

-  **Show KBArticle attachments:** Sets whether to show the attachments for the 
   Knowledge Base article. This is displayed by default.

-  **Show KBArticle asset links:** Sets whether to show the asset links for the
   Knowledge Base Article. This is displayed by default.

-  **Enable KBArticle view count increment:** Sets whether to add a view counter 
   to the Knowledge Base article. This is enabled by default.
   
-  **Enable KBArticle subscriptions:** Sets whether to allow user to subscribe
   to the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle history:** Sets whether to allow users to view the history
   for the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle print:** Sets whether to give users the option to print
   the Knowledge Base article. This is enabled by default.
   
-  **Enable social bookmarks:** Sets whether to display social bookmarks 
   when viewing a Knowledge Base article. This is disabled by default.
   
-  **Social bookmarks display style:** Sets the display style of the social
   bookmarks. Possible values are `menu`, `simple`, `vertical`, or `horizontal`.
   The default value is `menu`.

-  **Social bookmarks display position:** Sets the position to display social 
   bookmarks. Possible values are `top` or `bottom`. The default value is 
   `bottom`.
   
-  **Social bookmarks types:** A comma-separated list of the possible bookmark 
   types (twitter,facebook,plusone for example). Overwrite with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.

-  **Content root prefix:** Sets the default value for the content root prefix.

### Knowledge Base Service Configuration [](id=knowledge-base-service-configuration)

The list below describes what each configuration option does in the System
Settings UI.

-  **Get editor name:**  Sets the default editor. Possible values are: 
   `alloyeditor`, `ckeditor`, `simple`, `tinymce`, and `tinymce_simple`. The
   default value is `alloyeditor`.

-  **Article increment priority enabled:** Sets whether to increment article 
   priority by `1.0`. This is enabled by default.
   
-  **Markdown importer article extensions:** Sets the supported article file
   extensions for the Markdown importer. Add a new field for each file extension
   you wish to use. Note that the abbreviated and full file extensions will need 
   to be specified if you wish to use both (`.markdown` and `.md` for example).
   The default values are `.markdown` and `.md`.
  
-  **Markdown importer article intro:** Sets the article parent file suffix for
   the Markdown importer. The default value is `intro.markdown`. Any Markdown
   files named `[name]-intro.markdown` would be considered the parent article in
   the folder, with the default setting. The default value is `intro.markdown`.
  
-  **Markdown importer image file extensions:** Sets the supported image file
   extensions for the Markdown importer. Add a new field for each file extension
   you wish to use. Note that the abbreviated and full file extensions will need 
   to be specified if you wish to use both (`.jpeg` and `jpg` for example). The
   default values are `.bmp`, `.gif`, `.jpeg`, `.jpg`, and `.png`.

-  **Markdown importer image folder:** Sets the image folder path the Markdown 
   importer looks for in the ZIP file. The default value is `/images`.
   
-  **Source URLEnabled:** Sets whether to allow users to access the source 
   location of importable Markdown files. If enabled, users will have a button 
   displayed above each article that links them to the source location for the 
   article. This is disabled by default.

-  **Source URLEdit message key:** Sets the language key to use for the source 
   URL button label. The default value is `edit-on-github`.
   
-  **Email from name:** Sets the Sender's name for automated emails. Overwrite 
   with a `portal-ext.properties` file. The default value is
   `${server-property://com.liferay.portal/admin.email.from.name}`.
   
-  **Email from address:** Sets the Sender's email address for automated emails.
   Overwrite with a `portal-ext.properties` file. The default value is
   `${server-property://com.liferay.portal/admin.email.from.address}`.
   
-  **Email KBArticle added enabled:** Sets whether to send an automated email
   when a Knowledge Base article is added. This is enabled by default.
   
-  **Email KBArticle added subject:** Sets the subject template for the 
   Knowledge Base article added email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_article_added_subject.tmpl}`.
   
-  **Email KBArticle added body:** Sets the body template for the Knowledge Base 
   article added email. The default value is 
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_article_added_body.tmpl}`.
   
-  **Email KBArticle updated enabled:** Sets whether to send an automated email
   when a Knowledge Base article is updated. This is enabled by default.
   
-  **Email KBArticle updated subject:** Specifies the email template for the
   subject of the updated Knowledge Base article email. This is the default
   value 
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_article_updated_subject.tmpl}`.
   
-  **Email KBArticle updated body:** Specifies the email template for the
   body of the updated Knowledge Base article email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_article_updated_body.tmpl}`.
   
-  **Email KBArticle suggestion in progress enabled:** Sets whether to send an 
   automated email when a Knowledge Base article suggestion is moved to in 
   progress. This is enabled by default.
   
-  **Email KBArticle suggestion in progress subject:** Specifies the email 
   template for the subject of the in progress email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_in_progress_subject.tmpl}`.
   
-  **Email KBArticle suggestion in progress body:** Specifies the email template 
   for the body of the in progress email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_in_progress_body.tmpl}`.
   
-  **Email KBArticle suggestion received enabled:** Sets whether to send an 
   automated email when a Knowledge Base article suggestion is received. This is
   enabled by default.
   
-  **Email KBArticle suggestion received subject:** Specifies the email 
   template for the subject of the suggestion received email. the default value
   is `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_received_subject.tmpl}`.
   
-  **Email KBArticle suggestion received body:** Specifies the email template 
   for the body of the suggestion received email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_received_body.tmpl}`.
   
-  **Email KBArticle suggestion resolved enabled:** Sets whether to send an 
   automated email when a Knowledge Base article suggestion is resolved. This is
   enabled by default.
   
-  **Email KBArticle suggestion resolved subject:** Specifies the email 
   template for the subject of the suggestion resolved email. The default value
   is `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_resolved_subject.tmpl}`.
   
-  **Email KBArticle suggestion resolved body:** Specifies the email template 
   for the body of the suggestion resolved email. The default value is
   `${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_resolved_body.tmpl}`.

-  **Enable RSS:** Sets whether to enable the RSS feed. This is enabled by
   default.
   
-  **Rss delta:** Sets the pagination for RSS entries. The default value is
   `20`.

-  **Rss display style:** Sets the display style of the RSS. Possible values are 
   `abstract`, `full-content`, or `title`. The default value is `full-content`.
   
-  **Rss format:** Sets the web feed language to use for your RSS feed. Possible 
   values are `atom10` (Atom 1.0 the default), `rss10`(RSS 1.0), or 
   `rss20`(RSS 2.0). The default value is `atom10`.

-  **Rss feed type:** Sets the default feed type from the feed types defined in 
   the `rss.feed.types` portal property. The default value is
   `${server-property://com.liferay.portal/rss.feed.type.default}`.

### Knowledge Base Search Configuration [](id=knowledge-base-search-configuration)

-  **Show KBArticle author column:** Sets whether to display the author column
   in the search results of the application. This is enabled by default.
   
-  **Show KBArticle create date column:** Sets whether to display the create 
   date column in the search results of the application. This is enabled by 
   default.
   
-  **Show KBArticle modified date column:** Sets whether to display the modified 
   date column in the search results of the application. This is displayed by 
   default.
   
-  **Show KBArticle views column:** Sets whether to display the views column in 
   the search results of the application. This is displayed by default.
   
-  **Enable KBArticle description:** Sets whether to display the description
   field in the edit view of the application. This is disabled by default.

-  **Enable KBArticle ratings:** Sets whether to allow users to add ratings to
   the Knowledge Base Article. This is enabled by default.

-  **Show KBArticle asset entries:** Sets whether to show the asset entries for
   the Knowledge Base Article. This is displayed by default.

-  **Show KBArticle attachments:** Sets whether to show the attachments for the 
   Knowledge Base article. This is displayed by default.

-  **Show KBArticle asset links:** Sets whether to show the asset links for the
   Knowledge Base Article. This is displayed by default.

-  **Enable KBArticle view count increment:** Sets whether to add a view counter 
   to the Knowledge Base article. This is enabled by default. This is enabled by 
   default.
   
-  **Enable KBArticle subscriptions:** Sets whether to allow user to subscribe
   to the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle history:** Sets whether to allow users to view the history
   for the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle print:** Sets whether to give users the option to print
   the Knowledge Base article. This is enabled by default.
   
-  **Enable social bookmarks:** Sets whether to display social bookmarks 
   when viewing a Knowledge Base article. This is disabled by default.
   
-  **Social bookmarks display style:** Sets the display style of the social
   bookmarks. Possible values are `menu`, `simple`, `vertical`, or `horizontal`.
   The default value is `menu`.

-  **Social bookmarks display position:** Sets the position to display social 
   bookmarks. Possible values are `top` or `bottom`. The default value is 
   `bottom`.
   
-  **Social bookmarks types:** A comma-separated list of the possible bookmark 
   types (twitter,facebook,plusone for example). Overwrite with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.
   
### Knowledge Base Section Configuration [](id=knowledge-base-section-configuration)

-  **Show KBArticles sections title:** Sets whether to show the sections title
   in the application. This is enabled by default.

-  **kb articles sections:** Sets the section portlet's title with a comma 
   comma-separated list. The default value is `general`.

-  **kb article display style:** Sets the default kb article display style. 
   Possible values are `title` or `abstract`. The default value is `title`.
   
-  **Show KBArticles pagination:** Sets whether to show pagination in the
   application. This is enabled by default.
   
-  **Enable KBArticle description:** Sets whether to display the description
   field in the edit view of the application. This is disabled by default.

-  **Enable KBArticle ratings:** Sets whether to allow users to add ratings to
   the Knowledge Base Article. This is enabled by default.

-  **Show KBArticle attachments:** Sets whether to show the attachments for the 
   Knowledge Base article. This is displayed by default.
   
-  **Show KBArticle asset entries:** Sets whether to show the asset entries for
   the Knowledge Base Article. This is displayed by default.

-  **Show KBArticle asset links:** Sets whether to show the asset links for the
   Knowledge Base Article. This is displayed by default.

-  **Enable KBArticle view count increment:** Sets whether to add a view counter 
   to the Knowledge Base article. This is enabled by default.
   
-  **Enable KBArticle subscriptions:** Sets whether to allow user to subscribe
   to the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle history:** Sets whether to allow users to view the history
   for the Knowledge Base article. This is enabled by default.

-  **Enable KBArticle print:** Sets whether to give users the option to print
   the Knowledge Base article. This is enabled by default.
   
-  **Enable social bookmarks:** Sets whether to display social bookmarks 
   when viewing a Knowledge Base article. This is disabled by default.
   
-  **Social bookmarks display style:** Sets the display style of the social
   bookmarks. Possible values are `menu`, `simple`, `vertical`, or `horizontal`.
   The default value is `menu`.

-  **Social bookmarks display position:** Sets the position to display social 
   bookmarks. Possible values are `top` or `bottom`. The default value is 
   `bottom`.
   
-  **Social bookmarks types:** A comma-separated list of the possible bookmark 
   types (twitter,facebook,plusone for example). Overwrite with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.
   
-  **Admin KBArticle sections:** Sets the Knowledge Base article sections 
   available to the portlet.
   
-  **Admin KBArticle sections default:** Sets the default selected section for
   Knowledge Base articles.

## Overriding Settings [](id=overriding-settings)

So far, this document has covered the default system settings for the Knowledge
Base apps. Some of these settings can be overwritten through the configuration 
menu. However, some of the configuration settings are set to variables and must 
be overwritten through either a portal properties file or a configuration file. 
For example, the Knowledge Base Article configuration has the value shown below 
for the *Social bookmarks types* setting:

    ${server-property://com.liferay.portal/social.bookmark.types}
    
the `server-property` prefix specifies that this value is set through a server
property. You can view the default value by going to *Configuration* &rarr; 
*Server Administration* and selecting the *Properties* tab and 
*Portal Properties* sub-tab. From here you can Search for the property you 
want(e.g., `social.bookmark.types`) to see the default value. To overwrite the 
property's default value, add the updated property values to the 
`portal-ext.properties` file in your app server's root directory.

The second kind of variable prefix is `resource`. For example, the
*Email KBArticle suggestion in progress body* setting for the Knowledge Base 
Service configuration has the following value:

    ${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_in_progress_body.tmpl}

This points to the resource that provides the template. To overwrite these 
resources, you need to either change the configuration in the System Settings UI 
to point to your new file, or using a configuration file in your app server's 
`osgi/config` directory, you need to overwrite the property to point to the 
absolute file path of the new file. 

+$$$

**Note:** Once you've exported the configuration files, as explained in the 
[System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation, the configuration files are located in a folder called
`liferay-system-settings`. Within this folder you'll find the 
configuration(.cfg) files, named after the full class name for that file. For
example, the *Knowledge Base Service* configuration file is called
`com.liferay.knowledge.base.configuration.KBGroupServiceConfiguration.cfg` and
the *Knowledge Base Section* configuration file is called
`com.liferay.knowledge.base.web.configuration.KBSectionPortletInstanceConfiguration.cfg`.
To overwrite these files, you must use the same name for your configuration file, 
and place it in the `osgi/config` directory.

$$$

You can view the current configuration files and settings by exporting the 
configuration file, as explained in the [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation.
    
### Equivalent Configuration Properties for 6.2 Portal Properties [](id=equivalent-configuration-properties-for-6-2-portal-properties)

Here is a list of the properties previously found in Knowledge Base's 
`portlet.properties` and the new properties to use in the Knowledge Base 
configuration files.

The properties below are for the Knowledge Base Service System Settings 
configuration file:

<style>
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max
.max-960 {
	max-width: none;
}
.system-settings-table td {
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

<div class="system-settings-table">
<div class="max-960">
<div class="aui-helper-clearfix section-1" >
<div class="aui-w100 block-1 content-column" >
<div class="content-column-content">
<table>
        <caption>Knowledge Base Service</caption>
	<thead>
		<td class="table-header left-header">
			portlet.properties
		</td>
		<td class="table-header second">
			System Setting
		</td>
	</thead>
	<tbody>
		<tr>
			<td>
		            admin.email.from.name
			</td>
			<td>
	                    emailFromName
			</td>			
		</tr>
		<tr>
			<td>
			    admin.email.from.address
			</td>
			<td>
		            emailFromAddress
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.added.body<br>
					admin.email.kb.article.added.enabled<br>
					admin.email.kb.article.added.subject<br>
			</td>
			<td>
					emailKBArticleAddedBody<br>
					emailKBArticleAddedEnabled<br>
					emailKBArticleAddedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.in.progress.body<br>
					admin.email.kb.article.suggestion.in.progress.enabled<br>
					admin.email.kb.article.suggestion.in.progress.subject<br>
			</td>
			<td>
					emailKBSuggestionInProgressBody<br>
					emailKBSuggestionInProgressEnabled<br>
					emailKBSuggestionInProgressSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.received.body<br>
					admin.email.kb.article.suggestion.received.enabled<br>
					admin.email.kb.article.suggestion.received.subject<br>
			</td>
			<td>
					emailKBSuggestionReceivedBody<br>
					emailKBSuggestionReceivedEnabled<br>
					emailKBSuggestionReceivedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.resolved.body<br>
					admin.email.kb.article.suggestion.resolved.enabled<br>
					admin.email.kb.article.suggestion.resolved.subject<br>
			</td>
			<td>
					emailKBSuggestionResolvedBody<br>
					emailKBSuggestionResolvedEnabled<br>
					emailKBSuggestionResolvedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.updated.body<br>
					admin.email.kb.article.updated.enabled<br>
					admin.email.kb.article.updated.resolved.subject<br>
			</td>
			<td>
					emailKBSuggestionUpdatedBody<br>
					emailKBSuggestionUpdatedEnabled<br>
					emailKBSuggestionUpdatedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.kb.article.increment.priority.enabled
			</td>
			<td>
					articleIncrementPriorityEnabled
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.article.extensions
			</td>
			<td>
					markdownImporterArticleExtensions
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.article.intro
			</td>
			<td>
					markdownImporterArticleIntro
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.image.file.extensions
			</td>
			<td>
					markdownImporterImageFileExtensions
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.image.folder
			</td>
			<td>
					markdownImporterImageFolder
			</td>
		</tr>
		<tr>
			<td>
				        knowledge.base.source.url.enabled
			</td>
			<td>
					sourceURLEnabled
			</td>
		</tr>
		<tr>
			<td>
				        knowledge.base.source.url.edit.message.key
			</td>
			<td>
					sourceURLEditMessageKey
			</td>
		</tr>		
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

The properties below are for the Knowledge Base Section configuration file:

<div class="system-settings-table">
<div class="max-960">
<div class="aui-helper-clearfix section-1" >
<div class="aui-w100 block-1 content-column" >
<div class="content-column-content">
<table>
        <caption>Knowledge Base Section</caption>
	<thead>
		<td class="table-header left-header">
			portlet property
		</td>
		<td class="table-header second">
			System Setting
		</td>
	</thead>
	<tbody>
		<tr>
			<td>
				        admin.kb.article.sections
			</td>
			<td>
					adminKBArticleSections
			</td>
		</tr>
		<tr>
			<td>
				        admin.kb.article.sections.default
			</td>
			<td>
					adminKBArticleSectionsDefault
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

+$$$

**Note:** The `knowledge.base.ratings.number.of.stars` property has been removed 
since Knowledge Base now has a Ratings Configuration Framework. You can change 
the ratings type in the Instance Settings configuration menu under the *Social* 
tab. You can also configure the ratings type for an individual site under the 
*Social* tab of the Site Settings configuration menu.

The `knowledge.base.social.bookmarks.display.style` property can now be found
under the *Social bookmarks display style* setting of each Knowledge Base 
app's configuration menu in System Settings.

$$$

The configuration properties covered above are the properties that were 
previously found in portlet properties. You can view the full list of available 
configuration files and properties by exporting the configuration file, as 
explained in the [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation.
