---
header-id: navigating-from-a-collection-to-its-elements
---

# コレクションから要素への移動

[TOC levels=1-4]

[コレクションを取得する](/docs/7-1/tutorials/-/knowledge_base/t/getting-collections)と、レスポンスを使用してそのコレクションの要素を取得することができます。これを行うには、以下の手順に従います。

1. コレクションを取得します。この例では、[認証されたリクエスト](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)を`user-accounts`コレクションに送信することにより、ユーザーのリストを取得します。

   ```bash
   curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts"  -u 'test@liferay.com:test'
   ```

   [Getting Collections](/docs/7-1/tutorials/-/knowledge_base/t/getting-collections)でも触れたように、応答の`items`属性にはコレクション要素が含まれていることに注意してください。今回の例の場合、コレクションにはTest TestとJavier Gamarraの2人のユーザーが含まれています。

   ```json
   {
    "totalItems": 2,
    "numberOfItems": 2,
    "view": {
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
              "roleBriefs": [
                {
                  "id": 20108,
                  "name": "Administrator"
                },
                {
                  "id": 20111,
                  "name": "Power User"
                },
                {
                  "id": 20112,
                  "name": "User"
                }
              ],
              "siteBriefs": [
                {
                  "id": 20128,
                  "name": "Global"
                },
                {
                  "id": 20124,
                  "name": "Guest"
                }
              ]
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
              "roleBriefs": [
                {
                  "id": 20112,
                  "name": "User"
                }
              ],
              "siteBriefs": [
                {
                  "id": 20128,
                  "name": "Global"
                },
                {
                  "id": 20124,
                  "name": "Guest"
                }
              ]
            }
          ],
          "lastPage": 1,
          "page": 1,
          "pageSize": 20,
          "totalCount": 2
        }
    }
    ```
   
2. 応答から必要な要素のIDを見つけ、OpenAPIのプロファイルで適切なGETアイテムのエンドポイントを探します。たとえば、`user-accounts`のGETアイテムのエンドポイントは、`/user-accounts/{userAccountId}`です。

3. そのエンドポイントにGETリクエストを送信します。たとえば、以下のリクエストは、ID `59347`（Javier Gamarra）を持つユーザーの情報を取得します。

   ```bash
   curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts/59347"  -u 'test@liferay.com:test'
   ```

## 関連トピック

[Getting Collections](/docs/7-1/tutorials/-/knowledge_base/t/getting-collections)

[Pagination](/docs/7-1/tutorials/-/knowledge_base/t/pagination)

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)
