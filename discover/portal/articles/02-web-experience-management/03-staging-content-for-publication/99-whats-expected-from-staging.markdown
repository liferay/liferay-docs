# What's Expected from Staging? [](id=whats-expected-from-staging)

Have you ever wondered what happens to an entity during the Staging process?
Does Staging recognize the change? If so, how does it handle the update?

This article lists use cases for each Liferay entity. The use case tables have
three columns:

- *Related entity:* the entity attached/related to the table's main
  entity. For example, in the Web Content Article (main entity) table, an
  attached entity could be a structure.
- *Action performed:* the type of modification completed on the related entity.
- *How does Staging handle this?:* how the Staging framework handles the
  action performed.

Navigate to the entity section you want.

## Application Display Template (ADT)

The following table describes how entities characterized by an ADT are handled
by the Staging framework.

The related entities that apply to this table are listed below:

- Asset Publisher
- Blogs
- Breadcrumb
- Categories Navigation
- Documents and Media
- Language Selector
- Navigation
- RSS Publisher
- Site Map
- Tags Navigation
- Wiki

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Any entity from list above | ADT is added to entity | The ADT is published. ADTs and their related entities are handled separately by Staging.
|                            | Entity's ADT is modified | The ADT is changed and published.
|                            | Entity's ADT is deleted | The ADT deletion is published, removing it from the live site.
|                            | Existing ADT on live site is selected for entity | The entity portlet and its pages are published. If the page the entity resides on is published, its ADT is published too.
|                            | ADT referenced from entity is updated | The ADT is marked as modified and published.
|                            | ADT referenced from entity is deleted | The ADT is deleted on the live site and the entity falls back to the default template.
|                            | Entity with ADT is removed from page | The ADT is unaffected and is removed with the entity when the page is published.

## Asset Resources

The following table describes how asset tags and asset categories are handled by
the Staging framework.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Asset Category | Category moved into/out of vocabulary or another parent category | The category is marked as modified and is published.
|                | Vocabulary/parent category holding categories is deleted | All categories in the vocabulary/parent category are deleted, removing them from live site.
| Asset Tag | Two or more tags are merged into one tag | Merged tags are deleted, removing them from live site.

## Asset Publisher

The following table describes how entities that are displayed by the Asset
Publisher (AP) are handled on the staged and live sites during the Staging
process.

The related entities that apply to this table are listed below:

- Blog entry
- Bookmark
- Calendar event
- Calendar resource
- Knowledge Base article
- Documents and Media entities
    - Basic Document
    - Contract
    - Marketing Banner
    - Online Training
    - Sales Presentation

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Any entity from list above | Entity added to staged site | The entity displays in staged AP, but not live AP.
|                            | Entity published to live site | The entity displays on both staged and live AP.
|                            | Entity edited on staged site | The updated entity displays in staged AP, but not live AP.
|                            | Updated entity published to live site | The updated entity displays on both staged and live AP.
|                            | Entity deleted on the staged site | The entity does not display in staged AP, but does in live AP.
|                            | Deleted entity published to live site | The deleted entity does not display on both staged and live AP.
| Dynamic asset selection | AP is published | The content according to the dynamic selection is published.
|                         | Dynamic selection is edited and AP is published again | The content according to the dynamic selection is published again.
|                         | AP content is deleted | The content deletions are published by default, regardless of the AP.
|                         | AP removed from page | Once the page is published, the AP is removed. Content is not affected.
|                         | Dynamic selection exported is turned off | The content is not published with AP, but previously published content remains intact.
|                         | Dynamic export limit is changed | The content specified in the dynamic export limit is published for display in AP.
| Manual asset selection | AP is published | The content according to the manual selection is published.
|                        | Manual selection is changed and AP is published again | The content according to the manual selection is published again.
|                        | Manual selection content is deleted | The content according to the manual selection is deleted and not displayed in the AP.
|                        | AP removed from page | Once the page is published, the AP is removed. Content is not affected.
|                        | Manual selection exported is turned off | The AP does not publish any content.
|                        | No limit for manual export | The AP publishes the exact same manually selected content.

## Calendar

The sections below describe how Staging handles a specific Calendar entity and
the various actions that can be performed on its attached/related entities.

### Calendar Instance

The following table describes how entities that are attached/related to a
Calendar instance are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Calendar resource | Resource is created | The calendar is modified and published.
|                   | Resource is modified | The calendar is modified and published.
|                   | Resource is deleted | The calendar is modified and published.
| Tags | Tag for calendar is created | The tag is available on live site.
| Related assets | Web content added to calendar as related asset | The web content's event link is available on live site.

### Calendar Booking

The following table describes how entities that are attached/related to a
Calendar booking are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Calendar | Event is added | The event displays on live site.
|          | Event is added under new calendar | The event displays on live site.
|          | Calendar that contains booking is deleted | The booking is deleted.
|          | Calendar that contains booking is updated | The booking is not affected.
|          | Calendar booking is updated | The booking is published. The calendar is unaffected by changes in its bookings.
|          | Calendar booking is deleted | The booking is deleted. The calendar is unaffected by changes in its bookings.
|          | Calendar booking is moved to different calendar | If the booking was in the staging site and moved to another site's calendar, then the live version of the original booking is deleted when published. If the booking was moved into a site that is in the staging site, then it's not visible until publishing.
|          | Staged site is invited to event | The event is added to the staging site and an admin can accept the invite.
|          | Non-staging site is invited to event | The event only displays on live site after changes have been published.
|          | Staged site invites user calendar to event | The user does not receive an invitation until the staging site publishes the event to the live site.
| Calendar booking | Calendar booking is deleted after the event has occurred | The event is removed from the live site.
| Parent calendar booking | Parent calendar booking is deleted | The parent and its children are deleted once published.
|                         | Parent booking is changed | The parent booking is changed and published. The children are updated with the new changes. Their statuses should be preserved, except if the parent start/end time changes; in this case, children go back to pending. The reminder times of the children are never affected. If the parent or child is in the staging site, their changes should only propagate when it's published.
|                         | Child booking is deleted | Child bookings cannot be deleted.
|                         | Child booking is changed | The child booking is changed and published. The parent booking is unaffected.
|                         | Child booking's parent is changed | The child booking is changed and published.
| Calendar resource | Calendar resource is updated | The Calendar event is published with the correct resource.
|                   | Calendar event is published after resources were added to the Calendar | The event only remains under the site's Calendar.
|                   | Calendar resource is assigned to Calendar booking | If the resource is in the staging site, it will only appear on the booking once the resource's site is published. If the booking is in the staging site, the resource will only appear on the booking once the booking's site is published.
|                   | Calendar resource is removed while it's assigned to Calendar booking | If the resource is in the staging site, the resource will be removed from the booking only when the resource's site is published. If the calendar booking is in the staging site, the resource will be removed from the live site immediately, even if the booking's site is not published. Otherwise, the resource is removed from the booking with no other changes.
|                   | Calendar booking is deleted | This does not affect the Calendar resource.
|                   | Calendar booking is updated | The booking changes are published, but the update does not affect the resource.
|                   | Calendar resource is updated while assigned to Calendar booking | The resource is updated and published. The booking is not affected.
| Workflow | Page is published with Calendar event in a workflow | The event is pending on the live site until approval and new publication.
|          | User is invited to Calendar event on staged site in a workflow | The user doesn't receive a Calendar event invite until approval and new publication.

### Calendar Notification Template

The following table describes how entities that are attached/related to a
Calendar notification template are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Calendar | User published event with an invitation | The user receives the invitation.
|          | User configures reminder email | The user receives the reminder.
| Page link | Invitation notification received | The user has the link to the Calendar event.
|           | Reminder notification received | The user has the link to the Calendar event.
|           | Invitation template contains link to staged page | The template is published. The page URL must point to the live site's page during publication.
|           | Page link is removed from invitation template | The template is published with the removed page link.
|           | Referenced page is deleted from invitation template | The template is published, but possibly with a broken link.
|           | Referenced page is modified in invitation template | The page is published with no affect on the template.
|           | Reminder template contains link to staged page | The template is published. The page URL must point to the live site's page during publication.
|           | Page link is removed from reminder template | The template is published with the removed page link.
|           | Referenced page is deleted from reminder template | The template is published, but possibly with a broken link.
|           | Referenced page is modified in reminder template | The page is published with no affect on the template.
| Site link (not pointing to specific page) | Invitation notification received | The template body contains the correct site URL.
|                                           | Reminder notification received | The template body contains the correct site URL.
|                                           | Site link added to invitation template | The template is published.
|                                           | Site link added to invitation template and invitation is received | The URL points to the site.
|                                           | Site link removed from invitation template | The template is updated and published; the site is not affected by the change.
|                                           | Site referenced in invitation's site link is deleted | The template is not affected by the change.
|                                           | Site referenced in invitation's site link is updated | The template is not affected by the change.
|                                           | Site link added to reminder template | The template is published.
|                                           | Site link removed from reminder template | The template is updated and published; the site is not affected by the change.
|                                           | Site referenced in reminder's site link is deleted | The template is not affected by the change.
|                                           | Site referenced in reminder's site link is updated | The template is not affected by the change.
| Embedded image | Embedded image added to invitation template | The image is published with the template.
|                | Embedded image referenced in invitation template is deleted | The template is published even if the image is not available.
|                | Embedded image removed from invitation template | The template is published and the image is not affected.
|                | Invitation template containing embedded image is updated | The template is published and the image is not affected.
|                | Embedded image added to reminder template | The image is published with the template.
|                | Embedded image referenced in reminder template is deleted | The template is published even if the image is not available.
|                | Embedded image removed from reminder template | The template is published and the image is not affected.
|                | Reminder template containing embedded image is updated | The template is published and the image is not affected.

## DDL

The sections below describe how Staging handles a specific DDL entity and the
various actions that can be performed on its attached/related entities. For more
information on DDLs, see the
[Creating Data Lists](/discover/portal/-/knowledge_base/7-0/creating-data-lists)
article.

### DDL Record

The following table describes how entities that are attached/related to a DDL
record are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDL record set | DDL record is added | The DDL record is published.
|                | DDL record is edited | The modified DDL record is published.
|                | DDL record is deleted | The DDL record deletion is published.
|                | DDL record is submitted on live site | The DDL record does not appear on the staged site.
|                | DDL record is deleted on live site | The deleted DDL record is still present on the staged site.
| File entry from DDM form | User attaches file via Docs & Media field | The attached file cannot be viewed until the DDL list is published to the live site.
| Page from DDM form | Display portlet is configured to display in Form view | The currently selected list does not affect the Form view until it's published to the live site.
|                    | Display portlet is configured to display in Spreadsheet view | The currently selected list does not affect the Spreadsheet view until it's published to the live site.
|                    | Display portlet has no specific layout configurations | The currently selected list returns back to default view after it's published to the live site.
|                    | New DDL list is selected in Display portlet | The new DDL list is not visible on live site until it's published.
|                    | DDL list name is edited | The new name is available once it's published to the live site.

### DDL Record Version

The following table describes how entities that are attached/related to a DDL
record version are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDL record | DDL record version is deleted | A new version number is generated on the live site.
| Workflow | DDL record is pending in workflow | The workflow is not present on the live site until it's approved an published.

### DDL Record Set

The following table describes how entities that are attached/related to a DDL
record set are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDM structure | DDM structure is created | The new structure can only be viewed/edited on the staged site until it's published.
|               | DDM structure is edited | The edit is not reflected in the DDL list on the live site until it's published.
| DDM template | DDL list is configured to have a form template | The form template and its field(s) and data definition field(s) are visible in any new DDL record after publishing them to the live site.
|              | DDL Form template is removed | The default structure is displayed for DDL records after publishing the deletion to the live site.
|              | DDL list is configured to have a display template | The display template format is displayed for any new DDL record once it's published to the live site.
|              | DDL display template is removed | The default structure is displayed for DDL records after publishing the deletion to the live site.
| DDL record set (in Forms scope) | Form entry is submitted in staged site | The entry is not visible in the live site's Product Menu (*Content* &rarr; *Forms*) until it's published to the live site.
|                                 | Form entry is submitted in live site | The entry is only visible in the live site's Product Menu (*Content* &rarr; *Forms*).
|                                 | Form entry is deleted in live site | The entry is not removed from the live site's Product Menu (*Content* &rarr; *Forms*) until the deletion is published to the live site.
|                                 | Form entry is edited from Forms Admin portlet | The edited form is no display on live site until it's published to the live site.
|                                 | Form entry is deleted from Forms Admin portlet | The deleted form is still present on the staged site.

### Forms

The following table describes how entities that are attached/related to a DDL
record set are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Workflow | Form entry is submitted | The entry does not display on the live site until it's approved.

## DDM Structures and Templates

The sections below describe how Staging handles DDM structures and templates and
the various actions that can be performed on its attached/related entities. DDM
structures and templates are typically used with web content. Visit the
[Web Content](web-content) section for additional information on how Staging
handles these entities when attached to web content.

### DDM Structure

The following table describes how entities that are attached/related to a DDM
structure are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDM parent structure | Child structure is created | The parent structure is published with the new child structure.
|                      | Child structure is modified | The parent structure is published with the edited child structure.
|                      | Child structure is deleted | The parent structure is published to account for the removed child structure.
| DDM data provider | Data provider is added | The data provider is not added to the live site.
|                   | Data provider referencing a form | The data provider is added to the live site.
|                   | Data provider with form reference is modified | The data provider is modified on the live site.
|                   | Data provider with form reference is deleted | The data provider remains on the live site.
| DDM form layout | New form is selected on staging site | The new form is visible on the live site.

### DDM Template

The following table describes how entities that are attached/related to a DDM
template are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDM structure | DDM structure is edited and published to live via Admin portlet | The edited structure is available on the live site.
|               | DDM structure is edited and published to live via site page | The edited structure is available on the live site.
|               | DDM template is added to a DDM structure | The template is available on the live site.

## Polls

The sections below describe how Staging handles a specific polls entity and the
various actions that can be performed on its attached/related entities.

### Polls Choice

The following table describes how entities that are attached/related to a Polls
choice are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Polls question | New question is created with choices | The question and accompanying choices are published.
|                | Question is updated | The question is marked as modified and published.
|                | Choice is updated | The question is marked as modified and published with the updated choice.
|                | New choice is added to question | The question is marked as modified and published with the new choice.
|                | Question is deleted | The question and its accompanying choices are deleted and published.

### Polls Vote

The following table describes how entities that are attached/related to a Polls
vote are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Polls choice | Vote is added to the choice | The vote is published.
|              | Choice is updated | The question and accompanying choice are updated and published.
| Polls question | Question is deleted | The question and its accompanying choices and votes are deleted and published.
|                | Question is updated | The question is modified and published; the accompanying votes are not affected.

## Web Content [](id=web-content)

The sections below describe how Staging handles a specific web content entity
and the various actions that can be performed on its attached/related entities.

### Web Content Article [](id=web-content-article)

The following table describes how entities that are attached/related to a web
content (WC) article are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Folder | WC article is moved into/out of folder | The article is marked as modified and is published.
| WC structure | WC article is published | The structure (and its parent structure) and template are published.
|              | Structure is modified | All articles using it are reindexed. The structure is published.
|              | Parent structure is modified  | All articles using the structure or child structure are reindexed. The structure is published.
|              | Structure is deleted | Structures with articles using it or other structures using it can not be deleted. There could be an issue on import deletion that cannot happen because there are still dependencies using this in live.
| WC template | WC article is published | The template is published and the template reference is validated. It fails if the template can not be found in a parent site or the global site.
|             | Template is modified | The template is marked to be published. WC articles are ignored.
|             | Template is deleted | The templates with articles using it can not be deleted.
|             | Linked structure is modified | The template is marked to be published. Template could break, so warning is issued.
| Image or image URL contained in WC | WC article is published | The image(s) are published with article.
|                                    | WC article is deleted | The image(s) in article are deleted.
|                                    | Image is modified | The article is published.
|                                    | Image is deleted | The article is published.
| Embedded file contained in WC | WC article is published | The embedded file is published with article.
|                               | WC article is deleted | No changes to embedded file.
|                               | File entry is modified | The file entry is published. The article is not modified or published.
|                               | File entry is deleted | The article is published. Be aware that the missing file could create a link to a non-existing document, resulting in a broken link.
|                               | File entry's permissions are modified | No changes to WC article.
| Page link contained in WC | WC article is published | The page is imported; publishing won't fail if page is missing but users will get a warning message for a potential broken link.
|                           | WC article is deleted | No changes.
|                           | Page is deleted | The article is published. A message displays warning that there may be a broken link.
|                           | Page's friendly URL is modified | No changes.
| Site link (not pointing to specific page) contained in WC | WC article is exported | If the page exists, export the reference to it.
|                           | WC article is imported | Validate that the page exists.
|                           | WC article is deleted | No changes.
|                           | Page is deleted | The Web Content feature handles this action behind-the-scenes.
|                           | Page's friendly URL is modified | The Web Content feature handles this action behind-the-scenes.
| Virtual hosts | WC article is published | The site's virtual host is updated.
|               | WC article is deleted | No changes.
| Custom field (Expando) contained in WC | WC article is deleted | The custom field value is deleted.
|         | WC article is exported | The custom field values are exported with the columns.
|         | WC article is imported | The custom field values are imported. Columns are updated/added if they don't exist in the target system. If the Expando table does not exist, it is created.
|         | Custom field value is modified | The article is marked as modified and is published.
|         | Custom field properties are modified | The article is updated.
|         | Custom field column is deleted | The article is updated.
|         | Custom field locales are modified | The Custom field's locale is independent of the web content's locale; they depend on the portal languages. They shouldn't be validated when publishing.
| WC article deletion | WC article is deleted in Staging | The deletion is propagated and the article is deleted in the live site.
|                     | WC article is moved to trash in Staging  | The deletion is propagated and the article is deleted in the live site.
|                     | WC article is restored from trash | The article is published again.
| WC comments | WC article is deleted | The comments are deleted.
|             | WC article is exported | The comments are exported.
|             | WC article is imported | The comments are imported.
|             | Comment is modified | The comment is published by default (without the article, unless the article has also been modified, in which case the comment is also propagated).
|             | Comment is deleted | The comment is published by default (without the article, unless the article has also been modified, in which case the comment is also propagated).
| WC ratings | WC article is deleted | The ratings are deleted.
|            | WC article is exported | The ratings are exported.
|            | WC article is imported | The ratings are imported.
|            | Rating is modified | The rating is published by default (without the article, unless the article has also been modified, in which case the rating is also propagated).
| WC permissions | WC article is deleted | The permissions are deleted.
|                | WC article is exported | The permissions are exported.
|                | WC article's permissions are modified | The article is marked as modified and is published.
|                | WC role is created/modified/deleted | The article is reindexed, both in Staging and on the live site (because we are indexing the roles which can view an entity).
|                | WC team is created/modified/deleted | The article is reindexed, both in Staging and on the live site (because we are indexing the roles which can view an entity).
| Locales | The locales of the portal/site change | The article is imported properly, adding the value for the new default language from the previous default language.
| Asset entry | WC article is deleted | The asset entry is deleted.
|             | WC article is exported | No changes.
|             | WC article is imported | The asset entry is created or updated.
| Asset categories | WC article is deleted | No changes.
|                  | WC article is exported | The categories are exported.
|                  | WC article is imported | The categories are created or updated.
|                  | Category is deleted | The article is reindexed, but not published.
|                  | Category is modified or moved | The article is reindexed, but not published.
|                  | Category's permissions are changed | The article is reindexed, but not published.
| Asset tags | WC article is deleted | No changes.
|                  | WC article is exported | The tags are exported.
|                  | WC article is imported | The tags are created or updated.
|                  | Tag is deleted | The deletion is published (article should be reindexed, but not published).
|                  | Tag is merged with other tag | The merge is published (article should be reindexed, but not published).
|                  | Tag is modified | The tag is published (article should be reindexed, but not published).
| Asset links (current site) | WC article is deleted | The assets are deleted.
|                            | WC article is exported | The link is exported, but missing references are not added to related content.
|                            | WC article is imported | The asset links with existing assets are imported.
|                            | Related asset is deleted | The asset is ignored.
|                            | Related asset is modified | The modified asset is published.
|                            | Link to other asset is deleted from the other asset | The other asset is published, and it updates the asset link on import.
|                            | Link is deleted | 
| Asset links (parent or global site) | WC article is deleted | The assets are deleted.
|                            | WC article is exported | The link is exported, but missing references are not added to related content.
|                            | WC article is imported | The asset links with existing assets are imported.
|                            | Related asset is deleted | The asset is ignored.
|                            | Related asset is modified | No changes.
|                            | Link to other asset is deleted from the other asset | No changes.
|                            | Link is deleted | 

### Web Content Folder [](id=web-content-folder)

The following table describes how entities that are attached/related to a web
content (WC) folder are handled during the Staging process.

| Related Entity | Action performed | How does Staging handle this?     
| :------------- | :--------------  | :----------------------------
| Parent folder | Parent folder is modified | The articles residing in the folder are published.
| WC structure | Structure residing in folder is deleted | The structure can not be deleted if it is being used by a folder.
| WC folder | WC article moved into/out of folder during workflow | The article inherits the workflow configuration of its folder.
|           | Folder is deleted | All articles and folders within are deleted too and those deletions are published.
|           | Folder is modified | The folder is published.
|           | Folder restriction is changed | The folder and its entries are changed and published.
|           | Folder's permissions are modified | The folder is published.
|           | Folder is moved into/out of folder without workflow | The folder and its entries are changed and published.
|           | Folder is moved into/out of folder with workflow | The folder and its entries are workflow enabled.
|           | Folder's workflow restrictions are changed | The folder is updated and published.
|           | Folder with a workflow restriction is deleted | The workflow is not affected by the folder deletion; the deletion is included for publication.
|           | Folder's workflow restriction is deactivated | The folder should fall back to using no workflow. The folder is updated and included for publication.

### Web Content Feed [](id=web-content-feed)

The following table describes how entities that are attached/related to a web
content (WC) feed are handled during the Staging process.

| Related Entity | Action performed | How does Staging handle this?     
| :------------- | :--------------  | :----------------------------
| WC structure | Feed is exported | The structures and their reliant templates are exported too. If the structure or template doesn't exist, they are not exported.
|              | Feed is imported | The structures and their reliant templates are added/updated.
|              | Structure is deleted | The structure is deleted. <!--The structure can not be deleted if the feed is referencing it. -> That is expected result (LPS-73917). -->
| WC template | Template is deleted | The feed can exist without the template. It is not validated.
|             | Renderer template is deleted | The feed can exist without the template. It is not validated.
| Page | Page's friendly URL is modified | The link to the page still works.
|      | Portlet is removed from page | The feed can live without a portlet ID; the portlet is removed upon publication.
| Display page | WC article with display pages is configured | The page, Asset Publisher portlet, and content are published.
|              | Display page is deleted | The page and its content are deleted.
|              | WC article is deleted | The article deletion is published; the display page is not affected.
|              | WC article with a display page is added but the page was not published earlier and it's not included in the publication | The page reference is validated and the publication fails when the page is not there. A message displays explaining this to the user.
