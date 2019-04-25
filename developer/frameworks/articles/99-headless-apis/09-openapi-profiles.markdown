# OpenAPI Profiles [](id=api-vocabulary)

All the APIs exposed by Liferay are available under the [liferay6 swaggerhub organization](https://app.swaggerhub.com/organizations/liferay6).

Regarding Headless APIs, we differenciate between two different use cases, delivering content (delivery APIs) and managing and administering content (admin APIs). This division is demonstrated in the APIs available:

**Headless Delivery**

* [Headless Delivery](https://app.swaggerhub.com/apis/liferay6/headless-delivery/v1.0) with the following APIs:

    * BlogPosting, that maps the internal model BlogsEntry.
    * BlogPostingImage, that maps the internal model DLFileEntry associated with a BlogsEntry.
    * Comment, that maps the internal model DiscussionComment.
    * ContentDocument, that maps the internal model DLFileEntry associated with a JournalArticle.
    * ContentSet, that maps the internal model AssetListEntry.
    * ContentStructure, that maps the internal model DDMStructure.
    * Document, that maps the internal model DLFileEntry.
    * DocumentFolder, that maps the internal model Folder.
    * KnowledgeBaseArticle, that maps the internal model KBArticle.
    * KnowledgeBaseAttachment, that maps the internal model FileEntry associated with a KBArticle.
    * KnowledgeBaseFolder, that maps the internal model KBFolder.
    * MessageBoardAttachment, that maps the internal model FileEntry associated with a MBMessage.
    * MessageBoardMessage, that maps the internal model MBMessage.
    * MessageBoardSection, that maps the internal model MBCategory.
    * MessageBoardThread, that maps the internal model MBThread.
    * Rating, that maps the internal model RatingsEntry.
    * StructuredContent, that maps the internal model JournalArticle.
    * StructuredContentFolder, that maps the internal model JournalFolder.

**Headless Administration**

* [Headless Admin User](https://app.swaggerhub.com/apis/liferay6/headless-admin-user/1.0) for managing and retrieving information about Users, Organizations:

    * EmailAddress.
    * Organization
    * Phone
    * PostalAddress, that maps the internal model Address.
    * Role
    * Segment, that maps the internal model SegmentEntry.
    * SegmentUser
    * SiteBrief
    * UserAccount, that maps the internal model User.
    * WebUrl, that maps the internal model WebSite.
    
* [Headless Admin Taxonomy](https://app.swaggerhub.com/apis/liferay6/headless-admin-taxonomy/1.0) for managing AssetCategories, AssetVocabularies and AssetTags.

    * Keyword, that maps the internal model AssetTag.
    * TaxonomyCategory, that maps AssetCategory.
    * TaxonomyVocabulary, that maps AssetVocabulary.


* [Headless Admin Workflow](https://app.swaggerhub.com/apis/liferay6/headless-admin-workflow/1.0) for transitioning workflows.