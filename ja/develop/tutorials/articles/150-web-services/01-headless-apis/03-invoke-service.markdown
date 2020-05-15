---
header-id: get-started-invoke-a-service
---

# はじめに：サービスの呼び出し

[TOC levels=1-4]

[OpenAPIのプロファイル](/docs/7-1/tutorials/-/knowledge_base/t/get-started-discover-the-api)を介して呼び出すAPIを把握したら、そのリソースのURLにリクエストを送信することができます。たとえば、サイトからすべてのブログエントリを取得するとします。@product@の配信API向けのOpenAPIのプロファイルを調べると、以下のエンドポイントが表示されます。

```yaml
"/sites/{siteId}/blog-postings":
        get:
            operationId: getSiteBlogPostingsPage
            parameters:
                - in: path
                  name: siteId
                  required: true
                  schema:
                      format: int64
                      type: integer
                - in: query
                  name: filter
                  schema:
                      type: string
                - in: query
                  name: page
                  schema:
                      type: integer
                - in: query
                  name: pageSize
                  schema:
                      type: integer
                - in: query
                  name: search
                  schema:
                      type: string
                - in: query
                  name: sort
                  schema:
                      type: string
            responses:
                200:
                    content:
                        application/json:
                            schema:
                                items:
                                    $ref: "#/components/schemas/BlogPosting"
                                type: array
                    description: ""
            tags: ["BlogPosting"]
```

唯一の必須パラメータは`siteId`で、ブログを投稿するサイトのIDのことです。
内部的には、`siteId`はデータベースやURL、あるいはサイト管理メニューを経由した@product@のUIから取得できる`groupId`です。以下のGETリクエストは、URLにあるサイトID（`20124`）を提供することにより、サイトのブログ投稿を取得します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/" -u 'test@liferay.com:test'
```

ブログエントリがあるサイトに対してこのようなリクエストを送信すると、レスポンスは以下のようになります。

```json
{
  "items": [
    {
      "alternativeHeadline": "The power of OpenAPI & Liferay",
      "articleBody": "<p>We are happy to announce...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "dateModified": "2019-04-22T07:04:51Z",
      "datePublished": "2019-04-22T07:02:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "new-headless-apis",
      "headline": "New Headless APIs",
      "id": 59301,
      "numberOfComments": 0,
      "siteId": 20124
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 1
}
```

この応答は、ブログのコレクションに関する情報を持つJSONオブジェクトです。
応答の属性には、リソース（この場合はブログ）に関する情報が含まれています。また、結果がページ分割されていることにも注意してください。`*page*`属性は、結果のページを参照しています。一般的な属性は、以下のとおりです。

`id`：各アイテムにはIDがあります。IDを使用して、アイテムに関する詳細情報を取得できます。たとえば、上記の応答には`id`属性が2つあります。ひとつはブログの投稿用（`59301`）で、もうひとつはブログ投稿の作成者用（`20130`）です。

`lastPage`：結果の最終ページのページ番号。上記の応答には1ページしか含まれていないため、最後のページは`1`です。

`page`：現在のページのページ番号。上記の応答でのページ番号は、`1`です。

`pageSize`：単一のページ内で保持できる、このリソースのアイテム数。上記の応答では、`20`です。

`totalCount`：このリソースの既存アイテムの総数（ページネーションとは無関係）。上記の応答では、サイト内のブログ投稿の総数（`1`）がリストされています。

特定のブログ投稿に関する情報を取得するには、ブログ投稿のID (`/blog-postings/{blogPostingId}`)を使って、`blogPostingId`リソースのURLに対してGETリクエストを送信します。たとえば、上記の応答でのブログ投稿に対するこのようなリクエストのURLは `/blog-postings/59301`です。以下は、応答の例です。

```json
{
  "alternativeHeadline": "The power of OpenAPI & Liferay",
  "articleBody": "<p>We are happy to announce...</p>",
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20130,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-22T07:04:47Z",
  "dateModified": "2019-04-22T07:04:51Z",
  "datePublished": "2019-04-22T07:02:00Z",
  "encodingFormat": "text/html",
  "friendlyUrlPath": "new-headless-apis",
  "headline": "New Headless APIs",
  "id": 59301,
  "numberOfComments": 0,
  "siteId": 20124
}
```

この応答はJSONですが、APIの利用者は他のフォーマット（XMLなど）を選択することもできます。詳細については、[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)を参照してください。

## 関連トピック

[Get Started: Discover the API](/docs/7-1/tutorials/-/knowledge_base/t/get-started-discover-the-api)

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)
