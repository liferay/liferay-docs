---
header-id: jax-rs
---

# JAX-RS

[TOC levels=1-4]

JAX-RS Webサービスは、LiferayモジュールでもLiferay以外で動作する場合と同じように動作します。唯一の違いは、OSGiフレームワークにクラスを登録する必要があることです。この作業を簡単にするために、Liferayの開発ツールではテンプレートを提供しています。

Liferay Developer Studioで、以下の手順に従って*rest*テンプレートを使用した新しいモジュールを作成します。

1. *[ファイル]* → *[新規]* → *[Liferay Module Project]*をクリックします。

2. プロジェクトに名前を付けて、*rest*テンプレートを選択します。

3. *[次へ]*を選択し、サービスのクラス名とパッケージ名を入力します。

4. そして、*[完了]*をクリックします。

代わりに、[ Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)を使用してプロジェクトを作成することもできます。

| ** 注：**@product-ver@の開発ツールの初期リリースでは、| 無効な設定のプロジェクトが作成されました。クラスに|`@ApplicationPath`アノテーションが含まれている場合は、次のファイルを更新する必要があります。| | **Javaクラス：** | | 1．次のインポートを追加します。| | import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants; | | 2．`@ApplicationPath`アノテーションを削除します。
|
| 3．`@Component`アノテーションを次のように変更します。
|
|         @Component(
|   property = {
|    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings",
|    JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
|   },
|   service = Application.class)
|
| **build.gradle：**
|
| 次の依存関係を追加します。
|
|  compileOnly group: "org.osgi", name: "org.osgi.service.jaxrs", version: "1.0.0"
|
| **削除するファイル：** |
| `src/main/resources/configuration`の下にあるものをすべて削除します。

作成されるクラスには、動作しているJAX-RS Webサービスが含まれており、デプロイしてすぐに使用することができます。

この記事では、[JAX-RSホワイトボード](https://blog.osgi.org/2018/03/osgi-r7-highlights-jax-rs-whiteboard.html)全体を網羅することはしませんが、基本的には`@Component`アノテーションの設定プロパティを除いて、JAX-RSは変更されていません。これらのプロパティでは、以下の2点を宣言します。

1. サービスのエンドポイント

2. OAuth 2.0の設定に表示されるサービス名

作成されたクラスには、以下の設定が含まれます。

    @Component(
    property = {
    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings",
    JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
    },
    service = Application.class)

上記の設定は、このエンドポイントでサービスを以下のように登録します。

    https://[server-name]:[port]/o/greetings

Tomcatを使ってこれをローカルでテストしている場合、URLは以下の通りです。

    https://localhost:8080/o/greetings

上記のURLを呼び出すだけでは、サービスにアクセスすることはできないので、最初に認証する必要があります。認証方法の詳細は以下で説明します。

## OAuth 2.0を使用してJAX-RS Webサービスを呼び出す

JAX-RS Webサービスは、デフォルトでは認証が必要です。これを有効にするには、[OAuth 2.0アプリケーション](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application)を作成して、サービスへのアクセスを許可する方法を提供する必要があります。

1. *[コントロールパネル]* → *[設定]* → *[OAuth2の管理]*へと移動し、![[追加]](../../../images/icon-add.png)ボタンをクリックして追加のアプリケーションを追加します。

2. アプリケーションには、わかりやすい名前を付けます。

3. このサービスに適した、クライアントプロファイルを選択します。これらは、OAuth 2標準からの適切な認証タイプ、または「フロー」を自動選択するテンプレートです。この例では、*[Headless Server]*というプロファイルを選択してください。これにより、*[Client Credentials]*という認証タイプが自動選択されます。

4. *[保存]*をクリックします。

これにより、[クライアントID]と[クライアントシークレット]という2つの追加の作成フィールドとともに、フォームが再表示されます。これらを使用して、Webサービスの認証を行います。

分かりやすくするために、以下の例では[Curl](https://curl.haxx.se)を使用して認証します。アプリケーション用に作成された、クライアントIDおよびクライアントシークレットの2つの情報が必要です。たとえば、これらのフィールドに以下の値が含まれているとします。

**クライアントID：**`id-12e14a84-e558-35a7-cf9a-c64aafc7f`

**クライアントシークレット：**`secret-93f14320-dc39-d67f-9dec-97717b814f`

まず、OAuthトークンをリクエストする必要があります。ローカルでテストしている場合、以下のようなリクエストを送ります。

    curl http://localhost:8080/o/oauth2/token -d 'grant_type=client_credentials&client_id=id-12e14a84-e558-35a7-cf9a-c64aafc7f&client_secret=secret-93f14320-dc39-d67f-9dec-97717b814f'

応答は、JSONです。

    {"access_token":"a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5","token_type":"Bearer","expires_in":600,"scope":"GET POST PUT"}

この中には、クライアント用に作成されたトークンが含まれています。600秒で期限切れになり、このWebサービスのGET、POST、およびPUTを許可します。

サービスを呼び出す場合は、以下のようにHTTPヘッダーにトークンを指定する必要があります。

    curl --header "Authorization: Bearer a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5" http://localhost:8080/o/greetings/morning

認証を使用すると、Webサービスを呼び出してリクエストに応答できます。

    Good morning!

もちろん、これはOAuth 2.0の認証フローの1つにすぎません。バックエンドが@product@でホストされているJAX-RS WebサービスのWebベースのクライアントを作成している場合、上記以外のフローを使って行います。詳細については、[OAuth 2.0のドキュメンテーション](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)を参照してください。また、OAuth 2.0はセキュリティのためにHTTPSの使用を想定しており、上記のURLはローカルテストのみを目的としています。当然ながら、OAuthトークンはクライアントとサーバー間で平文で渡すには向いていません。
必ず、本番環境でサーバーがHTTPSを使用していることを確認してください。

 @product@のプラットフォームでJAX-RS Webサービスを作成、デプロイ、および呼び出す方法は、これで以上です。

## 関連トピック

[Service Builder Web Services](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)
