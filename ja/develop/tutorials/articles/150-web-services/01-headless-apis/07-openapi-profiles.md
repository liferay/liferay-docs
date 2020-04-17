---
header-id: openapi-profiles
---

# OpenAPIのプロファイル

[TOC levels=1-4]

@product@によって公開されるすべてのAPIは[liferayinc SwaggerHub organization](https://app.swaggerhub.com/organizations/liferayinc)で利用できます。

@product@のヘッドレスAPIは、以下の異なる2つのユースケースに分類されます。

- コンテンツの配信（配信API）
- コンテンツの運用と管理（管理API）

利用可能なAPIは、すべてこの分類方法で分けられます。

## ヘッドレス配信

以下の表では、[ヘッドレス配信](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0)に含まれているAPIをまとめています。2番目の列は、APIがマップする@product@の内部モデルを示していることに注意してください。

| API | &nbsp;内部モデル |
| --------- | ----------------------- | 
| `BlogPosting` | `BlogsEntry` |
| `BlogPostingImage` | `DLFileEntry` (`BlogsEntry`と関連) |
| `Comment` | `DiscussionComment` |
| `ContentDocument` | `DLFileEntry` (`JournalArticle`と関連) |
| `ContentSet` | `AssetListEntry` |
| `ContentStructure` | `DDMStructure` |
| `Document` | `DLFileEntry` |
| `DocumentFolder` | `Folder` |
| `KnowledgeBaseArticle` | `KBArticle` |
| `KnowledgeBaseAttachment` | `FileEntry` (`KBArticle`と関連) |
| `KnowledgeBaseFolder` | `KBFolder` |
| `MessageBoardAttachment` | `FileEntry` (`MBMessage`と関連) |
| `MessageBoardMessage` | `MBMessage` |
| `MessageBoardSection` | `MBCategory` |
| `MessageBoardThread` | `MBThread` |
| `Rating` | `RatingsEntry` |
| `StructuredContent` | `JournalArticle` |
| `StructuredContentFolder` | `JournalFolder` |

## ヘッドレス管理

ヘッドレス管理APIがいくつかあり、それぞれに独自のAPIセットが含まれています。以下の表では、これらAPIと各APIがマップする@prodcut@の内部モデルをまとめています。

[Headless Admin User](https://app.swaggerhub.com/apis/liferayinc/headless-admin-user/1.0)には、ユーザーと組織に関する情報を取得および管理するために以下のAPIが含まれています。

| API | &nbsp;内部モデル |
| --------- | ----------------------- | 
| `EmailAddress` | なし |
| `Organization` | なし |
| `Phone` | なし |
| `PostalAddress` | `Address` |
| `Role` | なし |
| `Segment` | `SegmentEntry` |
| `SegmentUser` | なし |
| `SiteBrief` | なし |
| `UserAccount` | `User` |
| `WebUrl` | `WebSite` |

[Headless Admin Taxonomy](https://app.swaggerhub.com/apis/liferayinc/headless-admin-taxonomy/1.0)には、アセットカテゴリ、アセットボキャブラリ、およびアセットタグを管理するために、以下のAPIが含まれています。

| API | &nbsp;内部モデル |
| --------- | ----------------------- | 
| `Keyword` | `AssetTag` |
| `TaxonomyCategory` | `AssetCategory` |
| `TaxonomyVocabulary` | `AssetVocabulary` |

[Headless Admin Workflow](https://app.swaggerhub.com/apis/liferayinc/headless-admin-workflow/1.0)には、ワークフローを移行するためのAPIが含まれています。

## 関連トピック

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)
