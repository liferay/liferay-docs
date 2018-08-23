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

## Application Display Template (ADT) [](id=application-display-template-adt)

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

## Asset Resources [](id=asset-resources)

The following table describes how asset tags and asset categories are handled by
the Staging framework.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Asset Category | Category moved into/out of vocabulary or another parent category | The category is marked as modified and is published.
|                | Vocabulary/parent category holding categories is deleted | All categories in the vocabulary/parent category are deleted, removing them from the live site.
| Asset Tag | Two or more tags are merged into one tag | Merged tags are deleted, removing them from the live site.

## Asset Publisher [](id=asset-publisher)

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

## Audience Targeting [](id=audience-targeting)

The sections below describe how Staging handles a specific Audience Targeting
entity and the various actions that can be performed on its attached/related
entities.

### User Segment [](id=user-segment)

The following table describes how entities that are attached/related to an
Audience Targeting user segment are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| User Segment | User segment is deleted | The user segment deletion is published.
| Rule | User segment is published | All rules are published; if a rule refers to an entity created by the Web Content or Documents and Media portlets, it's published during their respective processes.
|      | User segment is published with remote staging | The rule is published if the rule type is available in the destination environment; if not, the rule is not published.
|      | User segment is deleted | The user segment's rules are deleted and those deletions are published.
|      | Rule is added | The rule is published when its user segment is published.
|      | Rule is modified | The rule modification is published when its user segment is published.
|      | Rule is deleted | The rule deletion is published when its user segment is published.
| Asset category | User segment is published | The associated asset category is published.
|                | User segment is deleted | The associated asset category is deleted.
| Report | User segment is deleted | The user segment's reports are deleted and those deletions are published.

### Campaign [](id=campaign)

The following table describes how entities that are attached/related to an
Audience Targeting campaign are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Campaign | Campaign is deleted | The campaign deletion is published.
| User Segment | Campaign is published | The campaign's associated user segments are published.

## Blogs [](id=blogs)

The following table describes how entities that are attached/related to Blogs
are handled by the Staging framework.

+$$$

**Note:** There are two types of images that can be used in Blog entries. They
stem from two different frameworks: the legacy Image framework and the Document
Library framework. The Document Library's `FileEntry` is the image entity
created for any new Blog entries. The legacy Image framework's `Image` entity is
provided for backwards compatibility for older Blog entries featuring images.
Since there are no upgrade processes available for legacy Blogs, the old and new
image entities are both supported. Therefore, they're both listed in the table
below. Images that do not define the type can be assumed as the `FileEntry`
type.

$$$

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Blogs entry | Blog entry is created | The new entry is published.
|             | Blog entry is exported | No changes to entry on site where it was exported from.
|             | Blog entry is exported without references | No changes to entry on site where it was exported from.
|             | Blog entry is imported | The entry and its references are available for publishing in the importation site. If any modifications were done on the entry before it's imported, the `modifiedDate` field is flagged in the resulting LAR, meaning when the entry is imported to a new site, it's only available in the staging site and not published by default to the live site.
|             | Blog entry is imported without references | The entry is available for publishing in the importation site. If any modifications were done on the entry before it's imported, the `modifiedDate` field is flagged in the resulting LAR, meaning when the entry is imported to a new site, it's only available in the staging site and not published by default to the live site.
|             | Blog entry is modified | The modified entry is published.
| Small image - (`FileEntry`) | Entry is deleted | The image is not deleted from the Blogs repo (e.g., Site Admin &rarr; *Content* &rarr; *Blogs* &rarr; *Images*).
|             | Small image is deleted | The blog entry is not updated, but the image deletion is published.
|             | Small image is updated | The blog entry and image are updated and published.
|             | Blog entry is published with references | The small image is published.
|             | Blog entry is published without references | If the small image has already been published or is not included for publication, it is not (re)published.
| Small image - (`Image`) | Entry is deleted | The image is not deleted from the Blogs repo.
|                         | Small image is deleted | The blog entry is not updated, but the image deletion is published.
|                         | Small image is updated | The blog entry and image are updated and published.
|                         | Blog entry is published with references | The small image is published.
|                         | Blog entry is published without references | If the small image has already been published or is not included for publication, it is not (re)published.
| Cover image | Add a blog entry with cover image before publishing to the live site and then delete the entry | The cover image is not deleted from the Blogs repo (e.g., Site Admin &rarr; *Content* &rarr; *Blogs* &rarr; *Images*).
|             | Cover image is deleted in the Blogs repo | The blog entry still contains the cover image on the live site, but the image is unavailable in the Blogs repo.
|             | Cover image is updated | The blog entry and image are updated and published.
|             | Blog entry is published with references | The cover image is published.
|             | Blog entry is published without references | If the cover image has already been published or is not included for publication, it is not (re)published.
| Friendly URL | Custom URL is added | The new URL is published.
|              | Custom URL is modified | The modified URL is published.
|              | Blog entry is deleted | The URL deletion is published.
|              | Blog entry is published | The URL is published when importing, adding, or updating an entry.
| Page link | Blog entry is published with references | The entry's references are added, validated, and published.
|           | Page is deleted | The page deletion is published and page URL is broken in blog entry.
| Embedded image | Embedded image is added | The blog entry and image are published.
|                | Embedded image is removed from blog entry | The blog entry is updated and published; the embedded image does not change.
|                | Embedded image is deleted | The blog entry is updated and published, and the image deletion is published.
|                | Blog entry is published with references | The embedded image is published.
|                | Blog entry is published without references | If the embedded image has already been published or is not included for publication, it is not (re)published.
| Embedded image - linking Liferay image with anchor | Embedded image is added with linking | The blog entry and embedded image are published.
|                                                    | Embedded image is removed from the blog entry | The blog entry is updated and published; the embedded image does not change.
|                                                    | Embedded image is deleted | The blog entry is published with a broken link for the deleted embedded image.
|                                                    | Blog entry is published with references | The embedded image is published.
|                                                    | Blog entry is published without references | If the embedded image has already been published or is not included for publication, it is not (re)published.
| Expando (custom field) | Blog entry is deleted | The custom field value is deleted and published.
|                        | Blog entry is exported | The Expando values are exported with the columns.
|                        | Blog entry is imported | The Expando values are imported. Columns are updated/added if they don't exist in the target system. If the Expando table does not exist, it's created.
|                        | Custom field is modified | The entry is modified and published.
|                        | Custom field's properties are modified | The entry is modified and published.
|                        | Custom field is deleted | The entry is modified and published.
|                        | Locales are modified | Locales are independent of a blog entry; they depend on portal languages. Therefore, they're not listed as modified for the blog entry.
| Deletions | Blog entry is deleted | The deletion is propagated and published.
|           | Blog entry is added to Recycle Bin | The entry's removal is propagated and published.
|           | Blog entry is restored from Recycle Bin | The entry is published again.
| Permissions | Blog entry is deleted | The permissions are deleted and published.
|             | Blog entry is exported | The permissions are exported.
|             | Blog entry is imported | The permissions are imported.
|             | Blog entry's permissions are modified | The entry is modified and published.
|             | Role is created | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
|             | Role is modified | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
|             | Role is deleted | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
|             | Team is created | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
|             | Team is modified | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
|             | Team is deleted | The entry is reindexed in the staging and live sites. This is required so the roles which view and entity are reindexed.
| Locales | Locales of the portal/site change | The article is imported, adding the value for the new default language from the previous default language.
| Asset entry | File entry is exported | No changes.
|             | File entry is imported | The asset entry is created automatically.
|             | File entry is deleted | The asset entry is deleted and the file deletion is published.
| Asset category | Category is added | The category is published.
|                | Category is added to file entry | The file entry is updated and published.
|                | Category is updated | The updated category is published.
|                | Category is deleted | The category deletion is published.
|                | Category assigned to file entry is deleted | The file entry is updated and published.
|                | Category is removed from file entry, but not deleted | The file entry is updated and published.
| Asset tag | File entry is exported | The tags are exported.
|           | File entry is imported | The tags are imported.
|           | Tag is added to file entry | The file entry is updated and published.
|           | Tag is removed from file entry | The file entry is updated and published.
| Asset link | New asset link is created in file entry | The file entry is updated and published.
|            | Asset link is removed that links file entry and another entity | The file entry is updated and published.
|            | File entry is exported | The asset link is exported.
|            | File entry is imported | The asset link is imported if the target exists.

## Bookmarks [](id=bookmarks)

The following table describes how entities that are attached/related to
Bookmarks are handled by the Staging framework.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Bookmarks entry | Entry is added to folder | The entry is published.
|                 | Entry is moved to a different folder | The entry is updated and published.
|                 | Entry is deleted | The entry deletion is published; the folder is not affected.
|                 | Folder is updated | The entry is not affected by the change.
|                 | Folder is deleted | The folder and its entities are deleted.
| Bookmarks folder | Folder is added to another folder | The folder addition is published to live.
|                  | Child folder is moved to a different folder | The child folder change is published by default; the other folder is not affected.
|                  | Parent folder is modified | The parent folder is modified and published. The children folders are not affected.
|                  | Parent folder is deleted | The parent folder and its contents are deleted and those deletions are published.
|                  | Child folder is modified | The child folder is published; the parent folder is not affected.
|                  | Child folder is deleted | The folder deletion is published.

## Calendar [](id=calendar)

The sections below describe how Staging handles a specific Calendar entity and
the various actions that can be performed on its attached/related entities.

### Calendar Instance [](id=calendar-instance)

The following table describes how entities that are attached/related to a
Calendar instance are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Calendar resource | Resource is created | The calendar is modified and published.
|                   | Resource is modified | The calendar is modified and published.
|                   | Resource is deleted | The calendar is modified and published.
| Tags | Tag for calendar is created | The tag is available on live site.
| Related assets | Web content added to calendar as related asset | The web content's event link is available on live site.

### Calendar Booking [](id=calendar-booking)

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

### Calendar Notification Template [](id=calendar-notification-template)

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

## DDL [](id=ddl)

The sections below describe how Staging handles a specific DDL entity and the
various actions that can be performed on its attached/related entities. For more
information on DDLs, see the
[Creating Data Lists](/discover/portal/-/knowledge_base/7-1/creating-data-lists)
article.

### DDL Record [](id=ddl-record)

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

### DDL Record Version [](id=ddl-record-version)

The following table describes how entities that are attached/related to a DDL
record version are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDL record | DDL record version is deleted | A new version number is generated on the live site.
| Workflow | DDL record is pending in workflow | The workflow is not present on the live site until it's approved an published.

### DDL Record Set [](id=ddl-record-set)

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

### Forms [](id=forms)

The following table describes how entities that are attached/related to a DDL
record set are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Workflow | Form entry is submitted | The entry does not display on the live site until it's approved.

## DDM Structures and Templates [](id=ddm-structures-and-templates)

The sections below describe how Staging handles DDM structures and templates and
the various actions that can be performed on its attached/related entities. DDM
structures and templates are typically used with web content. Visit the
[Web Content](web-content) section for additional information on how Staging
handles these entities when attached to web content.

### DDM Structure [](id=ddm-structure)

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

### DDM Template [](id=ddm-template)

The following table describes how entities that are attached/related to a DDM
template are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DDM structure | DDM structure is edited and published to live via Admin portlet | The edited structure is available on the live site.
|               | DDM structure is edited and published to live via site page | The edited structure is available on the live site.
|               | DDM template is added to a DDM structure | The template is available on the live site.

## Document Library [](id=document-library)

The section below describes how Staging handles a specific document library (DL)
entity and the various actions that can be performed on its attached/related
entities.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| DL folder | Document moved into/out of a folder (workflow disabled) | The document is updated is published.
|           | Document moved into/out of a folder (workflow enabled) | The document inherits the workflow configuration of its folder.
|           | Folder is modified | The modified folder is published.
|           | Folder is deleted | The folder and all files/folders contained within it are deleted.
|           | Folder restriction is changed | The folder and its entries are changed and published.
|           | Folder's permissions are modified | The folder is published.
|           | Folder is moved into/out of another folder (workflow disabled) | The folder and its file entries are updated and published.
|           | Folder is moved into/out of another folder (workflow enabled) | The folder and its file entries follow the configured workflow.
|           | Folder's workflow restrictions are changed | The folder is updated and published.
|           | Folder with workflow restriction is deleted | The folder deletion is published; the workflow is not affected by the folder deletion.
|           | Folder's workflow restriction is deactivated | The folder is updated and published; the folder falls back to using no workflow.
| DL file entry | File entry is added | The file entry is published.
|               | File entry is deleted | The file entry deletion is published.
|               | File entry is updated (adding new file version) | The latest approved file version is published.
|               | Add a new type for file entry | The file entry is published.
|               | File entry is restored from Recycle Bin | The file entry is published.
| DL file version | Add new file version (editing the file) | The latest approved file version is published.
|                 | Delete latest approved file version | The deletion is published.
|                 | Delete file version that is not the latest | No changes.
| DL file shortcut | File shortcut is added | The shortcut is published.
|                  | File shortcut is deleted | The deletion is published.
|                  | File shortcut is restored from Recycle Bin | The shortcut is published.
|                  | File shortcut's target file is changed | The shortcut on the live site redirects to the new target file.
|                  | File shortcut's target file is deleted | The shortcut and target file deletions are published.
| DL file entry type | File entry type is added | The new file entry type is published.
|                    | File entry type is updated | The updated file entry type is published.
|                    | File entry type is deleted | The file entry type deletion is published.
|                    | Delete file entry type that belongs to file entry | Not allowed; file entry type cannot be deleted, so nothing is published.
|                    | Update a file entry type assigned to a file entry | A new file version is created and published.
| Folder | External folder is updated | No changes.
| Entities accessed through WebDAV | Staging folder is set up to be accessed with WebDAV | The folder is published.
|                                  | New file is added to folder from the desktop through WebDAV | The file is added on the staging site and included in publication; however, it's not added immediately.
|                                  | File is deleted from folder on the desktop through WebDAV | The file is deleted on the staging site and the deletion is published to the live site.
|                                  | File is removed from the Documents & Media portlet through WebDAV | The file is deleted on the desktop and the deletion is published.
|                                  | Live folder is set up to be accessed with WebDAV | The user can access the folder's contents on the desktop.
|                                  | File is added to the live folder on the desktop | The file cannot be added since it has a write protection.
| Repository | Repository definition is added | The definition is published.
|            | Folder or file entry is published | The repository's metadata is published.
|            | Repository metadata is updated in @product@ | The repository definition is published.
|            | Repository definition is deleted | The deletion is published.
|            | Repository is exported/imported | All the repository's entries are exported/imported too.
| Repository entry | New repository entry is created | The entry is published when the repository definition is published.
|                  | Repository entry is deleted | The repository entry is immediately deleted even before publication.
| Expando (custom field) | File version is deleted | The custom field value is deleted.
|                        | Latest file version is exported | The custom field values are exported with the columns.
|                        | File version is imported | The custom field values are imported. Columns are updated/added if they don't exist in the target system. If the custom field's table does not exist, it's created.
|                        | Custom field value is modified | The new file version is created and published.
|                        | Custom field value is modified for a Document Library folder | The folder is updated and published.
|                        | Expando column is deleted | The file entry is updated and published.
|                        | Custom field value is deleted | The file entry is published.
| Deletions | File entry is deleted | The file entry deletion is published.
|           | File entry is moved to Recycle Bin | The file entry's removal is published.
|           | File entry is restored from Recycle Bin | The file entry is published again.
|           | Folder is deleted | The folder and its contents are deleted and those deletions are published.
|           | Folder is moved to Recycle Bin | The folder and its contents are removed and those removals are published.
|           | Folder is restored from Recycle Bin | The folder and its contents are restored and published.
| Comments | File entry is deleted | The file entry deletion is published.
|          | File entry is exported | The comments are not exported with the file entry.
|          | File entry is imported | The comments are not imported with the file entry.
|          | Comment is modified | The modified comment is not published.
|          | Comment is deleted | The comment deletion is not published.
| Ratings | File entry is deleted | The file entry deletion is published.
|         | File entry is exported | The ratings are not exported with the file entry.
|         | File entry is imported | The ratings are not imported with the file entry.
|         | Rating is modified | The modified rating is not published.
| Permissions | File entry is deleted | The permissions are deleted and published.
|             | File entry is exported | The permissions are exported.
|             | File entry is imported | The permissions are imported.
|             | File entry's permissions are modified | The file entry is marked as modified and published.
|             | Role is created | The file entry is reindexed in both the staging and live sites.
|             | Role is modified | The file entry is reindexed in both the staging and live sites.
|             | Role is deleted | The file entry is reindexed in both the staging and live sites.
|             | Team is created | The file entry is reindexed in both the staging and live sites.
|             | Team is modified | The file entry is reindexed in both the staging and live sites.
|             | Team is deleted | The file entry is reindexed in both the staging and live sites.
| Asset entry | File entry is exported | No changes.
|             | File entry is imported | The asset entry is created automatically.
|             | File entry is deleted | The asset entry deletion is published.
| Asset category | Category is added | The category is published.
|                | Category is added to file entry | The file entry is updated and published.
|                | Category is updated | The category is published.
|                | Category is deleted | The category deletion is published.
|                | File entry's category is deleted  | The file entry is updated and published.
|                | Category is removed from file entry, but not deleted | The file entry is updated and published.
| Asset tag | File entry with tags is exported | The tags are exported with the file entry.
|           | File entry with tags is imported | The tags are imported with the file entry.
|           | Tag is added to file entry | The file entry is updated and published.
|           | Tag is removed from file entry | The file entry is updated and published.
| Asset link | New asset link is created with file entry | The file entry is updated and published.
|            | Asset link is removed between a file entry and another entity | The file entry is updated and published.
|            | File entry is exported | The asset link is exported.
|            | File entry is imported | The asset link is imported if the target is there; if not, it's skipped.

## Polls [](id=polls)

The sections below describe how Staging handles a specific polls entity and the
various actions that can be performed on its attached/related entities.

### Polls Choice [](id=polls-choice)

The following table describes how entities that are attached/related to a Polls
choice are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Polls question | New question is created with choices | The question and accompanying choices are published.
|                | Question is updated | The question is marked as modified and published.
|                | Choice is updated | The question is marked as modified and published with the updated choice.
|                | New choice is added to question | The question is marked as modified and published with the new choice.
|                | Question is deleted | The question and its accompanying choices are deleted and published.

### Polls Vote [](id=polls-vote)

The following table describes how entities that are attached/related to a Polls
vote are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Polls choice | Vote is added to the choice | The vote is published.
|              | Choice is updated | The question and accompanying choice are updated and published.
| Polls question | Question is deleted | The question and its accompanying choices and votes are deleted and published.
|                | Question is updated | The question is modified and published; the accompanying votes are not affected.

## Site Administration [](id=site-administration)

The following table describes how entities that are hosted in @product@'s Site
Administration menu are handled by the Staging framework.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Parent page | Create child page | The child page is published.
|             | Change parent of a child page | The parent is updated and published.
|             | Delete child page via simple publication | The child page's deletion is published.
|             | Delete child page via advanced publication | The child page's deletion is published when the *Replicate Individual Deletions* functionality is enabled.
|             | Move child page to different parent | The child page is moved and published.
|             | Create child page in new site variation and publish via simple publication | The child page exists on the live site.
|             | Publish changes from the main variation without changes via simple publication | The child page does not exist on the live site.
| Page friendly URL | Friendly URL is set | The friendly URL is published.
|              | Friendly URL is modified on new site page variation | The friendly URL is modified in every site page variation and published.
| Page icon | Icon is uploaded | The icon is published with the page.
|           | Icon is replaced by another image | The icon is modified on the live site.
|           | Icon is deleted | The icon is deleted on the live site.
|           | Icon is modified in a new site page variation | The icon is published since it's set in the new site page variation.
|           | Page is published from the main variation without modification on the page icon | The icon from the main variation is display on the live site.
| Link to page | Link is set to a page | The link is published.
|              | Delete page that is linked | The page deletion is published; the link is removed
|              | Remove link | The link removal is published.
|              | Link page to a page that exists in a variation only | A page existing in a different variation is not offered in the *Select Page* option.
| Portlets | Portlet is added to page | The portlet is published.
|          | Portlet is added to a page in a new site page variation | The portlet is published.
|          | Portlet is removed from the page | The portlet's removal is published.
|          | Portlet is removed from a page in a new site page variation | The portlet's removal is published.
| Theme (reference only) | Theme settings are modified on specific page | The theme reference is updated on live site.
|                        | Theme settings are modified in a site page variation on specific page  | The theme reference is updated on live site.
|                        | New theme is configured for specific page | The new theme configuration is published.
|                        | Public page theme settings are modified | The theme reference is updated on live site.
|                        | Private page theme settings are modified | The theme reference is updated on live site.
| Layout template | Layout template is changed for page | The change is published.
| Logo (individual page)| New image is uploaded as logo | The logo modification is published.
|                       | Uploaded logo is deleted | The logo modification is published.
| Logo (page set) | New image is uploaded as logo for public pages | The logo modification is published.
|                 | Uploaded logo is deleted from public pages | The logo modification is published.
|                 | New image is uploaded as logo for private pages | The logo modification is published.
|                 | Uploaded logo is deleted from private pages | The logo modification is published.
|                 | *Show Site Name* option is disabled for public pages | The site name is not published.
|                 | *Show Site Name* option is disabled for private pages | The site name is not published.
| Page template | Public page is created based on a new page template containing a theme modification | The page template's theme modification is published.
| JavaScript | JavaScript is set for public pages | The JavaScript configuration is published.
|            | JavaScript is removed from public pages | The JavaScript removal is published.
|            | JavaScript is set for private pages | The JavaScript configuration is published.
|            | JavaScript is removed from private pages | The JavaScript removal is published.
| CSS | CSS is set | The CSS configuration is published.
|     | CSS is removed | The CSS removal is published.
| Page set (referenced by site template) | Change theme of new site template | The public pages created in the site template have the updated theme configured.

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

## Wiki [](id=wiki)

The following table describes how entities that are attached/related to Wikis
are handled by the Staging framework.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Attachment link | Add a link to an attachment of the same page to a wiki page | The attachment is published and the link in the wiki points to the new attachment.
|                 | Add a link to another wiki page of the same node to a wiki page | The wiki page is published, but the link is not.
| Parent wiki page | Add a wiki page and a child of that page | Both pages are published.
|                  | Parent page is deleted | The parent page and all its children are deleted.
|                  | Child page is deleted | The child page deletion is published.
|                  | Parent page is updated | The updated page is published.
|                  | Child page is updated | The updated page is published.
| Wiki redirect page | Page is moved to a new title | The pages are updated and published; the redirect works on the live site.
|                    | Intermediate page is deleted | The page deletion is published; the new page is not affected.
|                    | Target page is deleted | Both the intermediate and new page are deleted and those deletions are published.
| Site link in content | Add a link to a Liferay site page to wiki | The new wiki content and the site page referenced in the link are published.
| Wiki node | New node is added | The node is published.
|           | Node is updated | The node is published.
|           | Set of pages is imported into existing node | The pages are published.
|           | Node is deleted | The node and all its dependent pages are deleted and those deletions are published.
| Wiki page | Page with no child/parent relationships is copied | The new page is published.
|           | Page containing a child is moved, changing its title and parent | The page is published. After publication, the page is located under a new parent and has a new name. The child points to the new page.
|           | Page with no child/parent relationships is deleted | The page deletion is published.
|           | Attachment is removed from page | The page is published. The attachment is removed from the page on the live site.
|           | Category is removed from page | The page is published. The category is removed from the page on the live site.
|           | Page format is modified | The page is published.
|           | Image (not yet published) is added to page's content | The page and its image are published.
