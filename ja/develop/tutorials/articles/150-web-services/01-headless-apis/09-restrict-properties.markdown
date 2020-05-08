---
header-id: restrict-properties
---

# プロパティの制限

[TOC levels=1-4]

大きなエンティティやコレクションを取得すると、応答のサイズが大きくなり、より多くの帯域幅を使用しますが、応答に含めるフィールドをリクエストでサーバーに伝えることで軽減することができます。これは、*スパースフィールドセット*として知られています。スパースフィールドセットを使用してリクエストを行うには、各フィールドの属性名と共に、URLに`fields`パラメータを含めます。

たとえば、以下のリクエストはスパースフィールドセットを使用していないため、ブログ投稿のすべてのフィールドを返します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301"  -u 'test@liferay.com:test'
```

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

見出し、作成日、および作成者の情報のみを取得するには、`headline`、`dateCreated`、および`creator`を含めた`fields`パラメータをURLに追加します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301?fields=headline,dateCreated,creator"  -u 'test@liferay.com:test'
```

```json
{
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20130,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-22T07:04:47Z",
  "headline": "New Headless APIs"
}
```

応答では、`creator`属性はネストされたJSONオブジェクトです。作成者の名前のみを返すには、ネストされたフィールドをドット表記（`creator.name`）で指定します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301?fields=headline,dateCreated,creator.name"  -u 'test@liferay.com:test'
```

```json
{
  "creator": {
    "name": "Test Test"
  },
  "dateCreated": "2019-04-22T07:04:47Z",
  "headline": "New Headless APIs"
}
```

この`fields`パラメータは、コレクションリソースと連携して、すべてのコレクションアイテムの指定された属性を返します。たとえば、以下のリクエストは`20124`というIDを持つサイト内のすべてのブログ投稿の見出しを取得します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?fields=headline"  -u 'test@liferay.com:test'
```

```json
{
  "items": [
    {
      "headline": "New Headless APIs"
    },
    {
      "headline": "Authenticated requests"
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 2
}
```

## 関連トピック

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)

[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)
