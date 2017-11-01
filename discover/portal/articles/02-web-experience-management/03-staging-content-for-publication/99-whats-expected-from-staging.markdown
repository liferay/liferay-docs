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

## Web Content [](id=web-content)

The sections below describe how Staging handles a specific web content entity
and the various actions that can be performed on its attached/related entities.

### Web Content Article [](id=web-content-article)

The following table describes how entities that are attached/related to a web
content (WC) article are handled during the Staging process.

| Related entity | Action performed | How does Staging handle this? 
| :------------- | :--------------  | :----------------------------
| Folder | WC article is moved into/out of folder | The article is marked as modified and will be published.
| WC structure | WC article is published | Structure (and its parent structure) and template are published.
|              | Structure is modified | All articles using it are reindexed. The structure will be published.
|              | Parent structure is modified  | All articles using the structure or child structure are reindexed. The structure will be published.
|              | Structure is deleted | Structures with articles using it or other structures using it can not be deleted. There could be an issue on import deletion that cannot happen because there are still dependencies using this in live.
| WC template | WC article is published | Template is published and the template reference is validated. It will fail if the template can not be found in a parent site or the global site.
|             | Template is modified | Template is marked to be published. WC articles are ignored.
|             | Template is deleted | Templates with articles using it can not be deleted.
|             | Linked structure is modified | Template is marked to be published. Template could break, so warning is issued.
| Image or image URL contained in WC | WC article is published | Image(s) are published with article.
|                                    | WC article is deleted | Image(s) in article are deleted.
|                                    | Image is modified | The article will be published.
|                                    | Image is deleted | The article will be published.
| Embedded file contained in WC | WC article is published | Embedded file is published with article.
|                               | WC article is deleted | No changes to embedded file.
|                               | File entry is modified | The file entry is published. The article is not modified or published.
|                               | File entry is deleted | The article will be published. Be aware that the missing file could create a link to a non-existing document, resulting in a broken link.
|                               | File entry's permissions are modified | No changes to WC article.
| Page link contained in WC | WC article is published | The page is imported; publishing won't fail if page is missing but users will get a warning message for a potential broken link.
|                           | WC article is deleted | No changes.
|                           | Page is deleted | The article will be published. A message displays warning that there may be a broken link.
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
|         | Custom field value is modified | The article is marked as modified and it will be published.
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
| WC folder | WC article moved into/out of folder during workflow | The article will inherit the workflow configuration of its folder.
|           | Folder is deleted | All articles and folders within are deleted too and those deletions are published.
|           | Folder is modified | The folder will be published.
|           | Folder restriction is changed | The folder and its entries are changed and published.
|           | Folder's permissions are modified | The folder will be published.
|           | Folder is moved into/out of folder without workflow | The folder and its entries are changed and published.
|           | Folder is moved into/out of folder with workflow | The folder and its entries will have workflow enabled.
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
|      | Portlet is removed from page | The feed can live without a portlet ID; the portlet will be removed upon publication.
| Display page | WC article with display pages is configured | The page, Asset Publisher portlet, and content are published.
|              | Display page is deleted | The page and its content are deleted.
|              | WC article is deleted | The article deletion is published; the display page is not affected.
|              | WC article with a display page is added but the page was not published earlier and it's not included in the publication | The page reference is validated and the publication fails when the page is not there. A message displays explaining this to the user.
