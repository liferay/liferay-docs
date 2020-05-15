---
header-id: multipart-requests
---

# マルチパートリクエスト

[TOC levels=1-4]

一部の操作は、マルチパートリクエストを介してバイナリファイルを受け入れます。たとえば、`DocumentFolder`にファイルを投稿するための定義は、マルチパートリクエストを指定します。

```yaml
post:
    operationId: postDocumentFolderDocument
    parameters:
        - in: path
          name: documentFolderId
          required: true
          schema:
              format: int64
              type: integer
    requestBody:
        content:
            multipart/form-data:
                schema:
                    properties:
                        document:
                            $ref: "#/components/schemas/Document"
                        file:
                            format: binary
                            type: string
                    type: object
    responses:
        200:
            content:
                application/json:
                    schema:
                        $ref: "#/components/schemas/Document"
                application/xml:
                    schema:
                        $ref: "#/components/schemas/Document"
            description: ""
    tags: ["Document"]
```

この操作は、`Document`を（JSONまたはXMLで）返します。この`Document`を作成するには、操作のマルチパートリクエストに以下の2つのコンポーネントを指定する必要があります。

- `file`プロパティを介したバイナリファイル（バイト）
- `document`プロパティを介したバイナリファイルのメタデータを含むJSON文字列

このリクエストを送信するには、`Content-Type`が`multipart/form-data`である必要があり、境界名も指定する必要があります（境界名は任意です）。

`38549`というIDを持つフォルダにドキュメントを作成するリクエストの例（ファイルのバイトなし）は、以下のとおりです。

    curl -X "POST" "http://localhost:8080/o/headless-delivery/v1.0/document-folders/38549/documents" \
    -H 'Accept: application/json' \
    -H 'Content-Type: multipart/form-data; boundary=PART' \
    -u 'test@liferay.com:test' \
    -F "file=" \
    -F "document={\"title\": \"podcast\"}"

そして、これに対する応答は、以下のとおりです。

```json
{
  "contentUrl": "/documents/20123/38549/podcast.mp3/e978e316-620c-df9f-e0bd-7cc0447cca49?version=1.0&t=1556100111417",
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20129,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-24T10:01:51Z",
  "dateModified": "2019-04-24T10:01:51Z",
  "documentFolderId": 38549,
  "encodingFormat": "audio/mpeg",
  "fileExtension": "mp3",
  "id": 38553,
  "numberOfComments": 0,
  "sizeInBytes": 28482097,
  "title": "podcast"
}
```

## 関連トピック

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)

[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)
