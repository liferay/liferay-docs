---
header-id: making-authenticated-requests
---

# 認証されたリクエストの実行

[TOC levels=1-4]

認証されたリクエストを実行するには、特定のユーザーとして認証する必要があります。

Web APIを呼び出すときに使用できる認証メカニズムには、以下の2つがあります。

**Basic認証：**ユーザー資格情報をエンコードされたユーザー名とパスワードのペアとして送信します。これは、最も簡潔な認証プロトコルです（HTTP / 1.0以降で使用可能）。

**OAuth 2.0：**@product-ver@では、認証にOAuth 2.0を使用できます。詳細については、 [OAuth 2.0 documentation](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)を参照してください。

ここでは、まず最初に、Basic認証を使用したリクエストの送信方法について説明します。

## Basic認証

Basic認証では、エンコードされたユーザー名とパスワードを含むHTTPの`Authorization`ヘッダーを送信する必要があります。最初に、エンコードされた値を取得する必要があります。取得には、`openssl`または`Base64`エンコーダーが使用できます。いずれにしても、`user:password`文字列をエンコードする必要があります。以下は、`test@liferay.com`というユーザーの`user:password`文字列を`Liferay`というパスワードでエンコードするための`openssl`コマンドの例です。

```bash
openssl base64 <<< test@liferay.com:Liferay
```

これにより、以下のエンコードされた値が返されます。

    dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==

`openssl`をインストールしていない場合は、以下の`base64`コマンドを試してください。

```bash
base64 <<< test@liferay.com:Liferay
```

| **警告：**ここに示すように文字列をエンコードしても、結果の| 文字列の暗号化は行われません。このようにエンコードされた文字列は、元の文字列を返す| `base64 <<< the-encoded-string`を実行することで、簡単にデコードできます。
|
| したがって、リクエストを受けている人は誰でも`Authorization`| ヘッダーをデコードし、ユーザー名とパスワードを明らかにすることができます。これを防ぐには、すべての通信が| メッセージ全体|（ヘッダーを含む）を暗号化するHTTPSを介して行われるようにします。

リクエストを送信するときは、HTTP Authorizationヘッダーのエンコードされた値を使用します。

```bash
curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/
```

応答には、認証されていないリクエストが受信する403エラーの代わりに、データが含まれます。応答の構造の詳細については、[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)を参照してください 。

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
    },
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
  "totalCount": 2
}
```

## OAuth 2.0認証

@product-ver@は、トークンベースの認証メカニズムであるOAuth 2.0による承認をサポートしています。詳細については、[@product@'s OAuth 2.0 documentation](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)を参照してください。
以下のセクションでは、OAuth 2.0を使用してWeb APIのリクエストを認証する方法を解説します。

### OAuth 2.0トークンの取得

OAuth 2.0を使用してWeb APIを呼び出す前に、使用するアプリケーション（Web APIの利用者）を承認済みOAuthクライアントとして登録する必要があります。これを行うには、OAuth 2.0のドキュメンテーションにある[Creating an Application](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application)のセクションの指示に従ってください。アプリケーションを作成する際、以下の内容をフォームに入力します。

**Application Name:** アプリケーションの名前。

**Client Profile:** ヘッドレスサーバー。

**Allowed Authorization Types:** *クライアント資格情報*を確認します。

*[保存]*をクリックしてアプリケーションの作成が完了したら、フォームの上部に表示される[クライアントID]と[クライアントシークレット]の値を書き留めます。

次に、OAuth 2.0のアクセストークンを取得する必要があります。これを行うには、[Authorizing Account Access with OAuth 2](/docs/7-1/deploy/-/knowledge_base/d/authorizing-account-access-with-oauth2)のチュートリアルを参照してください。

### OAuth 2.0トークンを使用してサービスを呼び出す

有効なOAuth 2.0トークンを取得したら、それをリクエストの`Authorization`ヘッダーに含め 、認証タイプが[bearer token](https://tools.ietf.org/html/rfc6750)であることを指定します。
以下に例を示します。

```bash
curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/
```

応答には、Basic認証からの応答と同様に、認証されたユーザーがアクセス権限を持っているリソースが含まれます。

## 認証されていないリクエストの実行

@product@のヘッドレスREST APIでは、認証されていないリクエストはデフォルトで無効になっています。ただし、以下の手順に従うと、マニュアルで有効にすることができます。

1. `com.liferay.headless.delivery.internal.jaxrs.application.HeadlessDeliveryApplication-default.config`という設定ファイルを作成し、以下のコードを追加します。

```properties
oauth2.scopechecker.type="none"
auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes="*"
auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes="*"
auth.verifier.guest.allowed="true"
```

    最後のプロパティ（ `auth.verifier.guest.allowed`）により、ゲスト
はAPIを介して    公開コンテンツにアクセスできることに注意してください。これをオフにするには、プロパティを
    `false`に設定します。

2. 設定ファイルを`[Liferay Home]/osgi/configs`にデプロイします。通常、[Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)は、 アプリケーションサーバーの親フォルダであることに注意してください。

3. OpenAPIプロファイルのURLに以下のリクエストを送信して、APIをテストします。

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml"
```

リクエストを送信したAPIのOpenAPIプロファイルが取得されるはずです。

## 関連トピック

[Get Started: Invoke a Service](/docs/7-1/tutorials/-/knowledge_base/t/get-started-invoke-a-service)

[Working with Collections of Data](/docs/7-1/tutorials/-/knowledge_base/t/working-with-collections-of-data)
