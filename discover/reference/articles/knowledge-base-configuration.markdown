# Knowledge Base Configuration [](id=knowledge-base-configuration)

These are the settings to configure the Knowledge Base, including the migration 
of properties from Liferay Portal 6.2.

This reference doc has two sections:

- Knowledge Base System Settings
- Overriding Settings

## Knowledge Base System Settings [](id=knowledge-base-system-settings)

Configuration changes made in System Settings set the default configuration for
the corresponding app across all sites. Knowledge Base apps in your site can
override these default values through their *Configuration* menu.

### Knowledge Base Article Configuration [](id=knowledge-base-article-configuration)

-  **Resource Prim key:** Sets the primary key that identifies a 
   Knowledge Base article or Knowledge Base folder (depending on the resource 
   class name ID). The default value is `0`.
 
-  **Resource class name id:** Sets the class name of either the Knowledge Base 
   article or Knowledge Base folder. This value needs to match the value of the 
   `Resource Prim key`. If the class name is a Knowledge Base article, the 
   resource prim key needs to be the primary key of a Knowledge Base article. If 
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
   types (`twitter,facebook,plusone` for example). Override with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.

### Knowledge Base Display Configuration [](id=knowledge-base-display-configuration)

-  **Resource Prim key:** Sets the primary key that identifies a 
   Knowledge Base article or Knowledge Base folder (depending on the resource 
   class name ID). The default value is `0`.
 
-  **Resource class name id:** Sets the class name of either the Knowledge Base 
   article or Knowledge Base folder. This value needs to match the value of the 
   `Resource Prim key`. If the class name is a Knowledge Base article, the 
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

-  **Enable KBArticle print:** Sets whether users can print
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
   types (`twitter,facebook,plusone` for example). Override with a 
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
 
-  **Source URLEnabled:** Sets whether users can access the source 
   location of importable Markdown files. If enabled, a button appears above
   each article that links them to the source location for the article. This is
   disabled by default.

-  **Source URLEdit message key:** Sets the language key to use for the source 
   URL button label. The default value is `edit-on-github`.
 
-  **Email from name:** Sets the Sender's name for automated emails. Override 
   with a `portal-ext.properties` file. The default value is
   `${server-property://com.liferay.portal/admin.email.from.name}`.
 
-  **Email from address:** Sets the Sender's email address for automated emails.
   Override with a `portal-ext.properties` file. The default value is
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
   types (twitter,facebook,plusone for example). Override with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.
 
### Knowledge Base Section Configuration [](id=knowledge-base-section-configuration)

-  **Show KBArticles sections title:** Sets whether to show the sections title
   in the application. This is enabled by default.

-  **kb articles sections:** Sets the section portlet's title with a comma 
   comma-separated list. The default value is `general`.

-  **kb article display style:** Sets the default Knowledge Base article display 
   style. Possible values are `title` or `abstract`. The default value is 
   `title`.
 
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
   types (`twitter,facebook,plusone` for example). Override with a 
   `portal-ext.properties` file. The default value is 
   `${server-property://com.liferay.portal/social.bookmark.types}`.
 
-  **Admin KBArticle sections:** Sets the Knowledge Base article sections 
   available to the portlet.
 
-  **Admin KBArticle sections default:** Sets the default selected section for
   Knowledge Base articles.
 
Although some of these settings can be modified through the configuration menu, 
others require more effort to change. These settings are specified next.

## Overriding Settings [](id=overriding-settings)

Some of the configuration settings have values and must be overridden through
either a portal properties file or a configuration file. For example, the
Knowledge Base Article configuration has the value shown below for the *Social
bookmarks types* setting:

    ${server-property://com.liferay.portal/social.bookmark.types}
 
The `server-property` prefix specifies that this value is set through a server
property. You can view the default value by going to *Configuration* &rarr; 
*Server Administration* and selecting the *Properties* tab and 
*Portal Properties* sub-tab. From here you can Search for the property you 
want(e.g., `social.bookmark.types`) to see the default value.

To override server properties, add the updated property values to the 
`portal-ext.properties` file in your app server's root directory.

The second kind of variable prefix is `resource`. For example, the
*Email KBArticle suggestion in progress body* setting for the Knowledge Base 
Service configuration has the following value:

    ${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_in_progress_body.tmpl}

This points to the resource that provides the template. To override these 
resources, you must change the configuration in the System Settings UI 
to point to your new file, or using a configuration file in your app server's
`osgi/config` directory, override the property to point to the new file's
absolute file path. 

Note that email templates can also be modified through the Knowledge Base app's 
*Configuration* button in the Options menu.

+$$$

**Note:** Once you've exported the configuration files, as explained in the 
[System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation, the configuration files are located in a folder called
`liferay-system-settings`. Within this folder you'll find the 
configuration(.cfg) files, named after the full class name for that file.

For example, the *Knowledge Base Service* configuration file is called
`com.liferay.knowledge.base.configuration.KBGroupServiceConfiguration.cfg` and
the *Knowledge Base Section* configuration file is called
`com.liferay.knowledge.base.web.configuration.KBSectionPortletInstanceConfiguration.cfg`.

To override a configuration file, you must create a new configuration file with 
the same name, specify your property values, and place it in the `osgi/config` 
directory.

$$$

You can view the current configuration files and settings by exporting the 
configuration file as explained in the [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation.
 
### Equivalent Configuration Properties for 6.2 Portlet Properties [](id=equivalent-configuration-properties-for-6-2-portlet-properties)

The table below compares the 6.2 `portlet.properties` to the configuration
properties and equivalent System Settings for the current version of the
Knowledge Base. Although you can modify System Settings with a configuration 
file, it is recommended that you update them through the System Settings menus 
if possible.

The properties below are for the Knowledge Base Service System Settings 
configuration file:

<style>
.max-960 {
	margin: 0 auto;
	max-width: 1000px;
}
.no-max
.max-960 {
	max-width: none;
}
.system-settings-table td {
	border-bottom: 1px solid;
	border-top: 1px solid;
	padding: 8px;
	width: 100%;
}
.table-header {
	font-weight: bold;
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
	<tr>
		<th class="table-header left-header">
			File
		</th>
		<th class="table-header second">
			Value
		</th>
	</tr>
	<tbody>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.from.name<br>
	                    emailFromName<br>
	                    Email from name
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.from.address<br>
	                    emailFromAddress<br>
	                    Email from address
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.added.body<br>
	                    emailKBArticleAddedBody<br>
	                    Email KBArticle added body
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.added.enabled<br>
	                    emailKBArticleAddedEnabled<br>
	                    Email KBArticle added enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.added.subject<br>
	                    emailKBArticleAddedSubject<br>
	                    Email KBArticle added subject
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.in.progress.body<br>
	                    emailKBSuggestionInProgressBody<br>
	                    Email KBArticle suggestion in progress body
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.in.progress.enabled<br>
	                    emailKBSuggestionInProgressEnabled<br>
	                    Email KBArticle suggestion in progress enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.in.progress.subject<br>
	                    emailKBSuggestionInProgressSubject<br>
	                    Email KBArticle suggestion in progress subject
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.received.body<br>
	                    emailKBSuggestionReceivedBody<br>
	                    Email KBArticle suggestion received body
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.received.enabled<br>
	                    emailKBSuggestionReceivedEnabled<br>
	                    Email KBArticle suggestion received enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.received.subject<br>
	                    emailKBSuggestionReceivedSubject<br>
	                    Email KBArticle suggestion received subject
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.resolved.body<br>
	                    emailKBSuggestionResolvedBody<br>
	                    Email KBArticle suggestion resolved body
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.resolved.enabled<br>
	                    emailKBSuggestionResolvedEnabled<br>
	                    Email KBArticle suggestion resolved enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.suggestion.resolved.subject<br>
	                    emailKBSuggestionResolvedSubject<br>
	                    Email KBArticle suggestion resolved subject
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.updated.body<br>
	                    emailKBSuggestionUpdatedBody<br>
	                    Email KBArticle suggestion updated body
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.updated.enabled<br>
	                    emailKBSuggestionUpdatedEnabled<br>
	                    Email KBArticle suggestion updated enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.email.kb.article.updated.resolved.subject<br>
	                    emailKBSuggestionUpdatedSubject<br>
	                    Email KBArticle suggestion updated subject
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.kb.article.increment.priority.enabled<br>
	                    articleIncrementPriorityEnabled<br>
	                    Article increment priority enabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    markdown.importer.article.extensions<br>
	                    markdownImporterArticleExtensions<br>
	                    Markdown importer article extensions
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    markdown.importer.article.intro<br>
	                    markdownImporterArticleIntro<br>
	                    Markdown importer article intro
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    markdown.importer.image.file.extensions<br>
	                    markdownImporterImageFileExtensions<br>
	                    Markdown importer image file extensions
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    markdown.importer.image.folder<br>
	                    markdownImporterImageFolder<br>
	                    Markdown importer image folder
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    knowledge.base.source.url.enabled<br>
	                    sourceURLEnabled<br>
	                    Source URLEnabled
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    knowledge.base.source.url.edit.message.key<br>
	                    sourceURLEditMessageKey<br>
	                    Source URLEdit message key
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
	<tr>
		<th class="table-header left-header">
			File
		</th>
		<th class="table-header second">
			Value
		</th>
	</tr>
	<tbody>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.kb.article.sections<br>
	                    adminKBArticleSections<br>
	                    Admin KBArticle sections
			</td>			
		</tr>
		<tr>
			<td>
		            6.2 Portlet Property<br>
		            Configuration Property<br>
		            System Setting
			</td>
			<td>
	                    admin.kb.article.sections.default<br>
	                    adminKBArticleSectionsDefault<br>
	                    Admin KBArticle sections default
			</td>			
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

The properties below have been removed or relocated:

-  **knowledge.base.ratings.number.of.stars:** This property has been removed 
   since Knowledge Base now has a Ratings Configuration Framework. You can 
   change the ratings type in the Instance Settings configuration menu under the 
   *Social* tab. You can also configure the ratings type for an individual site 
   under the *Social* tab of the Site Settings configuration menu.

-  **knowledge.base.social.bookmarks.display.style:** This property can now be 
   found under the *Social bookmarks display style* setting of each Knowledge 
   Base app's configuration menu in System Settings.

You can view the full list of available configuration files and properties by 
exporting the configuration files, as explained in the [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide documentation.
