---
header-id: filter-sort-and-search
---

# フィルタ、ソート、検索

[TOC levels=1-4]

@product@のヘッドレスREST APIを使用して、関心のあるコンテンツを検索することができます。また、コンテンツをソートおよびフィルタすることもできます。

## フィルタ

たいていの場合、大規模なコレクションをフィルタリングするのは必要なデータを正確に収集するのに有用です。
しかし、すべてのコレクションがフィルタリングを許可しているわけではありません。フィルタリングをサポートするものには、OpenAPIのプロファイルに`filter`というオプションのパラメータが含まれています。1つ以上のフィールド値に基づいてコレクションをフィルタするには、oData標準のサブセットに従う`filter`というパラメータを使用します。

フィルタリングは、主に@product@の検索でキーワードとしてインデックス付けされたフィールドに適用されます。テキストとしてインデックス付けされたフィールドに含まれる用語でコンテンツを検索するには、代わりに[search](#search)を使用する必要があります。

### 比較演算子

| 演算子 | 説明 | 例 |
|------------- |---------------------- | ------------------------------------- |
| `eq` | 等しい | `addressLocality eq 'Redmond'` |
|  | ヌルと等しい | `addressLocality eq null` |
| `ne` | 等しくない | `addressLocality ne 'London'` |
|  | ヌルではない | `addressLocality ne null` |
| `gt` | より大きい | `price gt 20` |
| `ge` | 以上 | `price ge 10` |
| `lt` | 未満 | `dateCreated lt 2018-02-13T12:33:12Z` |
| `le` | 以下 | `dateCreated le 2012-05-29T09:13:28Z` |
| `startsWith` | 前方一致 | `addressLocality startsWith 'Lond'` |

### 論理演算子

| 演算子 | 説明 | 例 |
| ------------ | ------------ | -------------------------------- |
| `and` | 論理積 | `price le 200 and price gt 3.5` |
| `or` | 論理和 | `price le 3.5 or price gt 200` |
| `not` | 論理否定 | `not (price le 3.5)` |

`not`演算子の後にスペース文字が必要なことに注意してください。

### グループ化演算子

| 演算子 | 説明 | 例 |
|---------- | ------------------- | ----------------------------------------------- |
| `( )` | 優先順位のグループ化 | `(price eq 5) or (addressLocality eq 'London')` |

### 文字列関数

| 関数 | 説明 | 例 |
| ----------- | ----------- | ------------------------- |
| `contains` | 含む | `contains(title,'edmon')` |

### ラムダ演算子

ラムダ演算子は、コレクションのブール式を評価します。コレクションを識別するナビゲーションパスを先頭に追加する必要があります。

| ラムダ演算子 | 説明 | 例 |
| --------------- | ----------- | ------------------------------------------ |
| `any` | いずれか | `keywords/any(k:contains(k,'substring1'))` |

`any`演算子は、ブール式を各コレクションの要素に適用し、式がいずれかの要素に対してtrueの場合、`true`と評価されます。

### クエリでのエスケープ

値内の単一引用符を別の単一引用符を追加することにより、エスケープすることができます。たとえば、見出しが`New Headless APIs`のブログ投稿をフィルタリングするには、以下のフィルタ文字列をリクエストURLに追加します。

    ?filter=headline eq 'New Headless APIs'

以下は、リクエスト全体の例です。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?filter=headline%20eq%20%27New%20Headless%20APIs%27"  -u 'test@liferay.com:test'
```

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

### 構造化コンテンツフィールド（ContentField）のフィルタリング

`ContentField`値（エンドユーザーによって作成された動的な値）をフィルタするには、独立した`ContentStructure`にスコープされたエンドポイントを使用する必要があります。
そのためには、`ContentStructure`のIDを見つけて、以下のURLの`{contentStructureId}`部分の代わりに使用します。

    "/content-structures/{contentStructureId}/structured-contents"

## 検索

多くの場合、キーワードを使って大規模なコレクションを検索することは有用です。特定のフィールドではなく、任意のフィールドの結果が必要な場合は、検索を使用します。検索を実行するには、`search`というオプションのパラメータを使用し、続いて検索する用語を入力します。たとえば、このリクエストではOAuthを含むすべての`BlogEntry`フィールドを検索します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?search=OAuth"  -u 'test@liferay.com:test'
```

```json
{
  "items": [
    {
      "alternativeHeadline": "How to work with OAuth",
      "articleBody": "<p>To configure OAuth...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T09:35:09Z",
      "dateModified": "2019-04-22T09:35:09Z",
      "datePublished": "2019-04-22T09:34:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "authenticated-requests",
      "headline": "Authenticated requests",
      "id": 59309,
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

## ソート

コレクションの結果のソートも一般的な作業の一つです。ただし、すべてのコレクションがソートを許可するわけではないことに注意してください。ソートをサポートするものには、OpenAPIのプロファイルに`{lb}?sort{rb}`というオプションのパラメータが含まれています。

ソートされたコレクション結果を取得するには、`?sort=<param-name>`をリクエストURLに追加します。たとえば、`?sort=title`をリクエストURLに追加すると、結果がタイトルでソートされます。

デフォルトのソート順は昇順です（つまり、0-1、A-Z）。降順ソートを実行するには、パラメータ名に`:desc`を追加します。たとえば、タイトルによる降順ソートを実行するには、リクエストURLに`?sort=title:desc`を追加します。

複数のパラメータで並べ替えるには、パラメータ名をコンマで区切り、優先度順に入力します。たとえば、最初にタイトルで並べ替えてから作成日で並べ替えるには、リクエストURLに`?sort=title,dateCreated`を追加します。

1つのパラメータのみに降順の並べ替えを指定するには、他のパラメータに昇順の並べ替え順序（`:asc`）を明示的に指定する必要があります。以下に例を示します。

    ?sort=headline:desc,dateCreated:asc

## Flatten

一部のコレクション（OpenAPIプロファイルで定義されているもの）では、すべてのリソースを返し、フォルダやその他の階層分類を無視する`flatten`というクエリパラメータを使用することたできます。このパラメータのデフォルト値は`false`なので、ルートフォルダへのドキュメントクエリは、そのフォルダ内のドキュメントのみを返します。
`flatten`を`true`に設定すると、これらのフォルダがどの程度深くネストされているのかに関係なく、同じクエリはサブフォルダ内のドキュメントも返します。つまり、`flatten`を`true`に設定し、サイトのルートフォルダ内のドキュメントを照会すると、サイト内のすべてのドキュメントが返されます。

## 関連トピック

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)

[API Formats and Content Negotiation](/docs/7-1/tutorials/-/knowledge_base/t/api-formats-and-content-negotiation)

[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)
