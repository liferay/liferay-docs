# OpenAPI Profiles [](id=api-vocabulary)

All the APIs exposed by @product@ are available under the 
[liferayinc SwaggerHub organization](https://app.swaggerhub.com/organizations/liferayinc). 

@product@'s headless APIs are categorized in two different use cases:

-   Delivering content (delivery APIs)
-   Managing and administering content (admin APIs)

The available APIs demonstrate this categorization. 

## Headless Delivery

The following table lists the APIs that 
[Headless Delivery](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0) 
contains. Note that the second column shows which internal model in @product@ 
that the API maps to. 

| API | &nbsp;Internal Model | 
| --------- | ----------------------- | 
| `BlogPosting` | `BlogsEntry` |
| `BlogPostingImage` | `DLFileEntry` (associated with a `BlogsEntry`) |
| `Comment` | `DiscussionComment` |
| `ContentDocument` | `DLFileEntry` (associated with a `JournalArticle`) |
| `ContentSet` | `AssetListEntry` |
| `ContentStructure` | `DDMStructure` |
| `Document` | `DLFileEntry` |
| `DocumentFolder` | `Folder` |
| `KnowledgeBaseArticle` | `KBArticle` |
| `KnowledgeBaseAttachment` | `FileEntry` (associated with a `KBArticle`) |
| `KnowledgeBaseFolder` | `KBFolder` |
| `MessageBoardAttachment` | `FileEntry` (associated with a `MBMessage`) |
| `MessageBoardMessage` | `MBMessage` |
| `MessageBoardSection` | `MBCategory` |
| `MessageBoardThread` | `MBThread` |
| `Rating` | `RatingsEntry` |
| `StructuredContent` | `JournalArticle` |
| `StructuredContentFolder` | `JournalFolder` |

## Headless Administration

There are several headless admin APIs, each containing its own set of APIs. The 
following tables list these, as well as any internal models in @prodcut@ that 
each API maps to. 

[Headless Admin User](https://app.swaggerhub.com/apis/liferayinc/headless-admin-user/1.0) 
contains the following APIs for retrieving and managing information about users 
and organizations. 

| API | &nbsp;Internal Model | 
| --------- | ----------------------- | 
| `EmailAddress` | N/A |
| `Organization` | N/A |
| `Phone` | N/A |
| `PostalAddress` | `Address` |
| `Role` | N/A |
| `Segment` | `SegmentEntry` |
| `SegmentUser` | N/A |
| `SiteBrief` | N/A |
| `UserAccount` | `User` |
| `WebUrl` | `WebSite` |

[Headless Admin Taxonomy](https://app.swaggerhub.com/apis/liferayinc/headless-admin-taxonomy/1.0) 
contains the following APIs for managing asset categories, asset vocabularies, 
and asset tags. 

| API | &nbsp;Internal Model | 
| --------- | ----------------------- | 
| `Keyword` | `AssetTag` |
| `TaxonomyCategory` | `AssetCategory` |
| `TaxonomyVocabulary` | `AssetVocabulary` |

[Headless Admin Workflow](https://app.swaggerhub.com/apis/liferayinc/headless-admin-workflow/1.0) 
contains APIs for transitioning workflows. 
