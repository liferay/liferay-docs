---
header-id: rest-filterable-properties
---

# Filterable properties

[TOC levels=1-4]

Some APIs return data that can be filtered and sorted by several properties.
This is a non-comprehensive list of the properties that can be used to filter or
sort.

## Headless Delivery API

### [BlogPosting](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/BlogPosting)

| Key                 | Type                  | Example                                   |
|-------------------- |---------------------- | ----------------------------------------- |
| taxonomyCategoryIds | list<integer>         | `taxonomyCategoryIds/any(t:t eq 1)`       |
| keywords            | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| customFields        | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated         | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified        | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId           | integer               | `creatorId eq 1`                          |
| headline            | string                | `contains(headline,'substring1')`         |

### [BlogPostingImage](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/BlogPostingImage)

| Key                 | Type                  | Example                                |
|-------------------- |---------------------- | -------------------------------------- |
| encodingFormat      | id                    | `encodingFormat eq 1`                  |
| sizeInBytes         | integer               | `sizeInBytes eq 1`                     |
| fileExtension       | string                | `contains(fileExtension,'substring1')` |
| title               | string                | `contains(title,'substring1')`         |

### [Comment](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/Comment)

| Key          | Type                  | Example                               |
|------------- |---------------------- | ------------------------------------- |
| dateCreated  | date                  | `dateCreated lt 2018-02-13T12:33:12Z` |
| dateModified | date                  | `dateModified lt 2018-02-13T12:33:12Z`|
| creatorId    | integer               | `creatorId eq 1`                      |

### [ContentStructure](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/ContentStructure)

| Key                 | Type                  | Example                                   |
|-------------------- |---------------------- | ----------------------------------------- |
| dateCreated         | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified        | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| name                | string                | `contains(name,'substring1')`             |

### [Document](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/Document)

| Key                 | Type                  | Example                                   |
|-------------------- |---------------------- | ----------------------------------------- |
| taxonomyCategoryIds | list<integer>         | `taxonomyCategoryIds/any(t:t eq 1)`       |
| keywords            | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| customFields        | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated         | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified        | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| encodingFormat      | id                    | `encodingFormat eq 1`                     |
| creatorId           | integer               | `creatorId eq 1`                          |
| sizeInBytes         | integer               | `sizeInBytes eq 1`                        |
| fileExtension       | string                | `contains(fileExtension,'substring1')`    |
| title               | string                | `contains(title,'substring1')`            |

### [DocumentFolder](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/DocumentFolder)

| Key                 | Type                  | Example                                   |
|-------------------- |---------------------- | ----------------------------------------- |
| customFields        | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated         | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified        | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId           | integer               | `creatorId eq 1`                          |
| name                | string                | `contains(name,'substring1')`             |

### [KnowledgeBaseArticle](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/KnowledgeBaseArticle)

| Key                 | Type                  | Example                                   |
|-------------------- |---------------------- | ----------------------------------------- |
| taxonomyCategoryIds | list<integer>         | `taxonomyCategoryIds/any(t:t eq 1)`       |
| keywords            | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| customFields        | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated         | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified        | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| title               | string                | `contains(title,'substring1')`            |

### [MessageBoardMessage](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/MessageBoardMessage)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| showAsAnswer          | boolean               | `showAsAnswer eq true`                    |
| showAsQuestion        | boolean               | `showAsQuestion eq true`                  |
| taxonomyCategoryIds   | list<integer>         | `taxonomyCategoryIds/any(t:t eq 1)`       |
| keywords              | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| customFields          | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId             | integer               | `creatorId eq 1`                          |
| messageBoardSectionId | integer               | `messageBoardSectionId eq 1`              |
| headline              | string                | `contains(headline,'substring1')`         |

### [MessageBoardSection](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/MessageBoardSection)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| customFields          | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId             | integer               | `creatorId eq 1`                          |
| title                 | string                | `contains(title,'substring1')`            |

### [StructuredContent](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/StructuredContent)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| taxonomyCategoryIds   | list<integer>         | `taxonomyCategoryIds/any(t:t eq 1)`       |
| keywords              | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| contentFields         | complex               | `contentFields/Name eq 'Article1'`        |
| customFields          | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| datePublished         | date                  | `datePublished lt 2018-02-13T12:33:12Z`   |
| contentStructureId    | integer               | `contentStructureId eq 1`                 |
| title                 | string                | `contains(title,'substring1')`            |

### [StructuredContentFolder](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/StructuredContentFolder)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| customFields          | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId             | integer               | `creatorId eq 1`                          |
| name                  | string                | `contains(name,'substring1')`             |

### [WikiNode](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/WikiNode)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| creatorId             | integer               | `creatorId eq 1`                          |
| name                  | string                | `contains(name,'substring1')`             |

### [WikiPage](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0#/WikiPage)

| Key                   | Type                  | Example                                   |
|---------------------- |---------------------- | ----------------------------------------- |
| customFields          | complex               | `customFields/Name eq 'Article1'`         |
| dateCreated           | date                  | `dateCreated lt 2018-02-13T12:33:12Z`     |
| dateModified          | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| headline              | string                | `contains(headline,'substring1')`         |

## Headless Admin User API

### [Organization](https://app.swaggerhub.com/apis/liferayinc/headless-admin-user/v1.0#/Organization)

| Key                  | Type             | Example                                   |
|--------------------- |----------------- | ----------------------------------------- |
| keywords             | list<string>     | `keywords/any(k:contains(k,'substring1'))`|
| dateModified         | date             | `dateModified lt 2018-02-13T12:33:12Z`    |
| parentOrganizationId | id               | `parentOrganizationId eq 1`               |
| name                 | string           | `contains(name,'category')`               |

### [User](https://app.swaggerhub.com/apis/liferayinc/headless-admin-user/v1.0#/User)

| Key             | Type                  | Example                                   |
|---------------- |---------------------- | ----------------------------------------- |
| keywords        | list<string>          | `keywords/any(k:contains(k,'substring1'))`|
| dateModified    | date                  | `dateModified lt 2018-02-13T12:33:12Z`    |
| id              | id                    | `id eq 1`                                 |
| organizationIds | id                    | `organizationIds eq 1`                    |
| roleIds         | id                    | `roleIds eq 1`                            |
| userGroupIds    | id                    | `userGroupIds eq 1`                       |
| alternateName   | string                | `contains(alternateName,'substring1')`    |
| emailAddress    | string                | `contains(emailAddress,'substring1')`     |
| familyName      | string                | `contains(familyName,'substring1')`       |
| givenName       | string                | `contains(givenName,'substring1')`        |
| jobTitle        | string                | `contains(jobTitle,'substring1')`         |

## Headless Admin Taxonomy API

### [Category](https://app.swaggerhub.com/apis/liferayinc/headless-admin-taxonomy/v1.0#/Category)

| Key          | Type                  | Example                               |
|------------- |---------------------- | ------------------------------------- |
| dateCreated  | date                  | `dateCreated lt 2018-02-13T12:33:12Z` |
| dateModified | date                  | `dateModified lt 2018-02-13T12:33:12Z`|
| name         | string                | `contains(name,'category')`           |

### [Keyword](https://app.swaggerhub.com/apis/liferayinc/headless-admin-taxonomy/v1.0#/Keyword)

| Key          | Type                  | Example                               |
|------------- |---------------------- | ------------------------------------- |
| dateCreated  | date                  | `dateCreated lt 2018-02-13T12:33:12Z` |
| dateModified | date                  | `dateModified lt 2018-02-13T12:33:12Z`|
| name         | string                | `contains(name,'category')`           |

### [Vocabulary](https://app.swaggerhub.com/apis/liferayinc/headless-admin-taxonomy/v1.0#/Vocabulary)

| Key          | Type                  | Example                               |
|------------- |---------------------- | ------------------------------------- |
| dateCreated  | date                  | `dateCreated lt 2018-02-13T12:33:12Z` |
| dateModified | date                  | `dateModified lt 2018-02-13T12:33:12Z`|
| name         | string                | `contains(name,'category')`           |
