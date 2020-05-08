---
header-id: getting-collections
---

# コレクションの取得

[TOC levels=1-4]

コレクションリソースのリクエストは、非コレクションリソースのリクエストと同じです。たとえば 、`UserAccount`エンドポイントへの[認証されたリクエスト](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)は、ポータルのユーザーを含んだコレクションを返します。このリクエストを送信する際は、他のポータルユーザーを閲覧する権限を持つ管理ユーザーの資格情報を使用します。

```bash
curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts"  -u 'test@liferay.com:test'
```

応答（以下）には主要な部分が2つあります。

- 一つ目は、`items`属性内のコレクション要素のリストです。この例では、管理者（Test）とJavier Gamarraという名前の2人のユーザーに関するデータが含まれています。

- 二つ目は、コレクションに関するメタデータのセットです。これは、応答の残りのデータのことです。これにより、クライアントはコレクションの使用方法を把握することができます。

この応答はJSONであり、@product@のWeb APIのデフォルトの応答形式です。他の応答形式への指定については、[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)を参照してください。

```json
{
  "items": [
    {
      "alternateName": "test",
      "birthDate": "1970-01-01T00:00:00Z",
      "contactInformation": {},
      "dashboardURL": "/user/test",
      "dateCreated": "2019-04-17T20:37:19Z",
      "dateModified": "2019-04-22T09:56:35Z",
      "emailAddress": "test@liferay.com",
      "familyName": "Test",
      "givenName": "Test",
      "id": 20130,
      "name": "Test Test",
      "profileURL": "/web/test",
      ...
    },
    {
      "alternateName": "nhpatt",
      "birthDate": "1970-01-01T00:00:00Z",
      "contactInformation": {},
      "dateCreated": "2019-04-22T10:38:36Z",
      "dateModified": "2019-04-22T10:38:37Z",
      "emailAddress": "nhpatt@gmail.com",
      "familyName": "Gamarra",
      "givenName": "Javier",
      "id": 59347,
      "name": "Javier Gamarra",
      ...
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 2
}
```

## 関連トピック

[Pagination](/docs/7-1/tutorials/-/knowledge_base/t/pagination)

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)
