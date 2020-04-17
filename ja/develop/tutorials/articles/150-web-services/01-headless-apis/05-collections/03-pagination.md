---
header-id: pagination
---

# ページネーション

[TOC levels=1-4]

コレクションリソースは、情報のページで返されます。
詳細については、[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)で説明しています。ここでは、コレクションページの操作方法を説明します。

たとえば、ポータルに123人のユーザーがいて、そのユーザーに関する情報を取得するとします。これを行うには、以下の [認証されたリクエスト](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)をUserAccount URLに送信します。

```bash
curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts"  -u 'test@liferay.com:test'
```

応答には、コレクションの残りの部分をナビゲートするための最初の30人のユーザーとIDが含まれます。コレクションをナビゲートするためのメタデータに注力できるように、ここではユーザーを含む`items`属性のほとんどのコンテンツが省略されていることに注意してください。

```json
{
      "items": [
        {
          "id": 20130,
          ...
        },
        {
          "id": 59347,
          ...
        }
      ],
      "lastPage": 5,
      "page": 1,
      "pageSize": 30,
      "totalCount": 123
    }
```

`page`および`pageSize`属性により、クライアントアプリケーションは結果をナビゲートすることができます。たとえば、このようなクライアントは特定のページのリクエストを送信できます。以下の例では、IDが`20124`のサイトに存在するドキュメントの2ページ目（`?page=2`）を取得します。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/documents?page=2"  -u 'test@liferay.com:test'
```

同様に、オプションの`pageSize`パラメータを使用して、ページごとの要素数をカスタマイズすることができます（例: `?pageSize=20`）。

## 関連トピック

[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)

[Making Authenticated Requests](/docs/7-1/tutorials/-/knowledge_base/t/making-authenticated-requests)
