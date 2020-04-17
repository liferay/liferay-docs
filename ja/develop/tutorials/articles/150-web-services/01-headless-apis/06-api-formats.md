---
header-id: api-formats-and-content-negotiation
---

# API形式とコンテンツネゴシエーション

[TOC levels=1-4]

これまでに紹介した例の応答は、標準のJSON形式を使用しています。これは、@product@のヘッドレスREST APIのデフォルトの応答形式です。これだけではなく、XMLなどの他の形式も使用できます。通常、フォーマットはリソースメタデータの構造またはセマンティクスが異なります。最適なフォーマットはありませんので、ユースケースに最適なものを使用してください。

使用するさまざまなフォーマットは、*コンテンツネゴシエーション*を使用して指定します。コンテンツネゴシエーションでは、クライアントとサーバーがどのようにしてメッセージの交換に使用するフォーマットを確立するのかを決めます。クライアントは、HTTPヘッダーの`Accept`と`Content-Type`を介して、サーバーに希望するフォーマットを通知します。それぞれのフォーマットには、HTTPヘッダーでフォーマットを指定するために使用できる文字列識別子（MIMEタイプ）があります。以下の表では、サポートされている各フォーマットのMIMEタイプをまとめています。

| APIフォーマット | &nbsp;MIMEタイプ |
| --------- | ----------------------- | 
| application/json | [application/json](https://www.iana.org/assignments/media-types/application/json) |
| application/xml | [application/xml](https://www.iana.org/assignments/media-types/application/xml) |

API形式を指定せずにリクエストを送信すると、サーバーはデフォルトのJSONで応答します。たとえば、`20124`というIDを持つサイトからのフォルダのリストの要求は以下のとおりです。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/document-folders" -u 'test@liferay.com:test'
```

```json
{
  "items": [
    {
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T10:21:20Z",
      "dateModified": "2019-04-22T10:21:20Z",
      "id": 59319,
      "name": "REST APIs Documentation",
      "numberOfDocumentFolders": 0,
      "numberOfDocuments": 0,
      "siteId": 20124
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 1
}
```

ヘッダーを要求すると、`Content-Type`の応答属性にはコンテンツタイプのフォーマット（この場合はJSON）がリストされます。


```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/document-folders" -u 'test@liferay.com:test' --head
```

    HTTP/1.1 200
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    X-XSS-Protection: 1
    Set-Cookie: JSESSIONID=9F61AEB8721DD9149BD577ECBC31AE3F; Path=/; HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Cache-Control: private, no-cache, no-store, must-revalidate
    Pragma: no-cache
    Set-Cookie: COOKIE_SUPPORT=true; Max-Age=31536000; Expires=Tue, 21-Apr-2020 10:23:57 GMT; Path=/; HttpOnly
    Set-Cookie: GUEST_LANGUAGE_ID=en_US; Max-Age=31536000; Expires=Tue, 21-Apr-2020 10:23:57 GMT; Path=/; HttpOnly
    Date: Mon, 22 Apr 2019 10:23:57 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked

代わりに、XMLで応答を取得するには、要求の`Accept`ヘッダーで`application/xml`を指定します。XML応答にはJSONと同じ情報が含まれますが、構造が異なることに注意してください。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/documents/59203"  -H 'Accept: application/xml'  -u 'test@liferay.com:test'
```

```xml
    <Page>
        <items>
            <items>
                <creator>
                    <familyName>Test</familyName>
                    <givenName>Test</givenName>
                    <id>20130</id>
                    <name>Test Test</name>
                    <profileURL>/web/test</profileURL>
                    </creator>
                <dateCreated>2019-04-22T10:21:20Z</dateCreated>
                <dateModified>2019-04-22T10:21:20Z</dateModified>
                <id>59319</id>
                <name>REST APIs Documentation</name>
                <numberOfDocumentFolders>0</numberOfDocumentFolders>
                <numberOfDocuments>0</numberOfDocuments>
                <siteId>20124</siteId>
            </items>
        </items>
        <lastPage>1</lastPage>
        <page>1</page>
        <pageSize>20</pageSize>
        <totalCount>1</totalCount>
    </Page>
```

ヘッダーを要求すると、応答がXML（`application/xml`）であることがわかります。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/documents/59203"  -H 'Accept: application/xml'  -u 'test@liferay.com:test' --head
```

    HTTP/1.1 200
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    X-XSS-Protection: 1
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Cache-Control: private, no-cache, no-store, must-revalidate
    Pragma: no-cache
    Date: Mon, 22 Apr 2019 10:26:21 GMT
    Content-Type: application/xml
    Transfer-Encoding: chunked

## 言語ネゴシエーション

別の応答形式（コンテンツネゴシエーション）を要求するのに使用されるメカニズムは、別の言語でコンテンツを要求するためにも使用されます。

異なる言語で使用可能なAPIは、`availableLanguages`というブロックでオプションを返します。たとえば、以下の応答のこのブロックには、米国英語（`en-US`）およびスペイン/カスティリャのスペイン語（`es-ES`）がリストされています。

```json
{
  "availableLanguages": [
    "en-US",
    "es-ES"
  ],
  "contentFields": [
    {
      "dataType": "html",
      "name": "content",
      "repeatable": false,
      "value": {
        "data": "<p>The main reason is because Headless APIs have been designed with real use cases in mind...</p>"
      }
    }
  ],
  "contentStructureId": 36801,
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20130,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-22T10:29:40Z",
  "dateModified": "2019-04-22T10:30:31Z",
  "datePublished": "2019-04-22T10:28:00Z",
  "friendlyUrlPath": "why-headless-apis-are-better-than-json-ws-services-",
  "id": 59325,
  "key": "59323",
  "numberOfComments": 0,
  "renderedContents": [
    {
      "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
      "templateName": "Basic Web Content"
    }
  ],
  "siteId": 20124,
  "title": "Why Headless APIs are better than JSON-WS services?",
  "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
}
```

To request the content in another language, specify your desired locale in the
request's `Accept-Language` header:

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325"  -H 'Accept-Language: es-ES'  -u 'test@liferay.com:test'
```

```json
    {
      "availableLanguages": [
        "en-US",
        "es-ES"
      ],
      "contentFields": [
        {
          "dataType": "html",
          "name": "content",
          "repeatable": false,
          "value": {
            "data": "<p>La principal razón es porque las APIs Headless se han diseñado pensando en casos de uso reales...</p>"
          }
        }
      ],
      "contentStructureId": 36801,
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T10:29:40Z",
      "dateModified": "2019-04-22T10:30:31Z",
      "datePublished": "2019-04-22T10:28:00Z",
      "friendlyUrlPath": "%C2%BFpor-qu%C3%A9-las-apis-headless-son-mejores-que-json-ws-",
      "id": 59325,
      "key": "59323",
      "numberOfComments": 0,
      "renderedContents": [
        {
          "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
          "templateName": "Contenido web básico"
        }
      ],
      "siteId": 20124,
      "title": "¿Por qué las APIs Headless son mejores que JSON-WS?",
      "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
    }
```

### 異なる言語でコンテンツを作成する

デフォルトでは、POST/PUTリクエストを送信するときに、`Accept-Language`ヘッダーがコンテンツの言語として使用されます。ただし、例外が1つあります。一部のエンティティでは、最初のPOSTがサイトのデフォルト言語である必要があります。そのような場合、異なる言語のPOSTリクエストはエラーになります。

新しいリソースを作成した後、PUTリクエストは別の言語でその翻訳を追加します。そして、PATCHリクエストはエラーを返します（PATCHリクエストでは、作成ではなくアップデートをする必要があります）。

## 関連トピック

[Get Started: Discover the API](/docs/7-1/tutorials/-/knowledge_base/t/get-started-discover-the-api)

[Get Started: Invoke a Service](/docs/7-1/tutorials/-/knowledge_base/t/get-started-invoke-a-service)
