# 認証ベリファイア[](id=authentication-verifiers)

認証検証レイヤーは、@product@のAPIのリモート呼び出しを認証するための一元的かつ拡張可能な方法です。

認証検証レイヤーの主な役割は、以下の通りです。

1. 登録済み`AuthVerifier`インスタンスを使用して、提供された資格情報を確認すること

2. 検証結果に基づいてポータル認可コンテキストを作成すること

使用可能な`AuthVerifier`が要求の認証情報を検証できない場合、認証されていないアクセスをサポートする認可コンテキストがゲストユーザー用に作成されます。これにより、各APIは単一のAPIエンドポイントのみを公開できます。対照的に、旧バージョン（6.2より前）の@product@では、各APIに対して2つのAPIエンドポイントが公開されていました 。`/api/endpoint` URIは非認証アクセス用で、 `/api/secure/endpoint` URIは認証アクセス用でした。

リモートクライアントがHTTP基本認証またはHTTPダイジェスト認証を使用する場合や、要求パラメータで資格情報を送信する場合、認証済み`JSESSIONID`ファイルを送信する場合、共有秘密を使用して信頼を確立する場合など、最も一般的な状況に対する組み込みの`AuthVerifier`の実装があります 。他の`AuthVerifier`の実装は、OSGiランタイムにサービスとして登録されている`AuthVerifier`インターフェースの実装を含むモジュールとしてデプロイできます。

注：認証検証レイヤーの焦点は、認証情報の確認であり、資格情報の提供ではありません。認証検証レイヤーは、トークンの発行や、資格情報、あるいはサインインポートレットの表示を担当しません。代わりに、レイヤーは既存の認証情報と認証されたセッションを検証するため、認証エンドポイントを補完します。ただし、下位互換性を確保するために、デフォルトの実装ではユーザー名とパスワードの資格情報を提供する要求がサポートされています。したがって、認証検証レイヤーは認証と認可の境目に位置しています。

## 認証検証プロセスの概要[](id=authentication-verification-process-overview)

このレイヤーと関連プロセスは、`javax.servlet.Filter`インタフェースを実装している`AuthVerifierFilter`クラスによって提供されます。

**手順1：リクエスト資格情報の確認**

このレイヤーは、一連の責任設計パターンを使用して、組み込みとサードパーティの`AuthVerifier`実装の両方をサポートします。それぞれの`AuthVerifier`はマッピングされたURLや他のプロパティを指定する設定を提供できます。

それぞれの着信要求は、要求の処理に使用される`AuthVerifier`の最終リストを選択するために、登録されているすべての`AuthVerifier`と照合されます。
着信要求の資格情報の検証は、それぞれの`AuthVerifier`が担当します。

**認可コンテキストの作成**

要求がすべての一致する`AuthVerifiers`によって処理されると、@product@は解決されたユーザーへの認可コンテキストを作成します。

これには、解決されたユーザーID設定`ThreadLocals`を解決されたユーザーに返すように`HttpServletRequest` `remoteUser`を設定することが含まれています。

解決されたユーザーは、いずれかの`AuthVerifier`インスタンスから返されたユーザーになるか、どのインスタンスも提供された資格情報を検証できなかった場合は、ゲストユーザーになります。
`AuthVerifiers`は開発者によって作成され、OSGiランタイムに登録されている限り自動的に処理されます。各認証ベリファイアは、*[コントロールパネル]* → *[システム設定]* → *[セキュリティ]* → *[API認証]*で、独自の設定を取得します。製品に付属する認証ベリファイアの設定は以下の通りです。

- 基本認証ヘッダー
- ダイジェスト認証
- HTTPトンネルエクステンダー
- 画像リクエスト
- ポータルセッション
- リクエストパラメータ
- トンネル認証

デフォルトでは、以下の認証ベリファイアのみが有効になっており、すぐにリモートAPIにアクセスするために使用できます。

- 基本認証ヘッダー
- ポータルセッション

## 基本認証ヘッダー[](id=basic-auth-header)

この認証ベリファイアを使用すると、リモートクライアントは[HTTP基本認証](https://en.wikipedia.org/wiki/Basic_access_authentication)を使用して認証できます 。
この設定は、この方法で認証する必要があるURLパスの指定により設定されます。
[基本認証を使用する] というフィールドが選択されている場合は、HTTP基本認証が必要です。

デフォルトのURLは、Webサービス用の`/api/*,/xmlrpc*`です。`TunnelServlet`へのアクセスを防ぐため、マッピングでは`/api/liferay*` を除外しています。詳細については、トンネル認証ベリファイアを参照してください。

## ダイジェスト認証ヘッダー[](id=digest-auth-header)

この認証ベリファイアを使用すると、リモートクライアントは[HTTPダイジェスト認証](https://en.wikipedia.org/wiki/Digest_access_authentication)を使用して認証できます 。
この設定は、この方法で認証する必要があるURLパスの指定により設定されます。
[ダイジェスト認証を使用する] というフィールドが選択されている場合は、HTTP基本認証が必要です。

この認証ベリファイアはデフォルトでは有効になっていません。

## HTTPトンネルエクステンダー[](id=http-tunnel-extender)

Liferayはモジュール方式を採用しているため、この拡張モジュールはモジュールが`TunnelServlet`の一部になることを有効にするために書かれました。`TunnelServlet`と`TunnelingServletAuthVerifier`をモジュールサーブレットコンテキストにマッピングします。マニフェストに`Http-Tunnel`を持つモジュールはTunnel Servletを利用することができ、`/o/_module_/api/liferay/do`を介してAPIを公開することができます。

この設定は、トンネリングを許可するクライアントIPアドレスを設定することでできます。詳細については、[プロパティドキュメント](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)および[リモートステージング](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging)を参照してください 。

この方法は、リモートAPIをエクスポートするための推奨される方法ではないことに注意してください。

## 画像リクエストの認証ベリファイア[](id=image-request-authentication-verifier)

LibreOfficeやOpenOfficeに接続している場合、プロセスは@product@からイメージをダウンロードしてドキュメントをイメージでレンダリングする必要があります。これを行うために 、画像に安全にアクセスするための[JWTトークン](https://jwt.io)が作成されます。

許可するホスト、含めるURL、および必要に応じて除外するURLを設定して、これを設定します。

この認証ベリファイアはデフォルトでは有効になっていません。

## ポータルセッションの認証ベリファイア [](id=portal-sessions-auth-verifiers)

ブラウザ内のJavaScriptが既存のポータルセッションを使用して、Liferay JSON Webサービスにアクセスできるようにします。

デフォルト設定では、URLが含まれるフィールドは、旧バージョンのJSONリモートサービスレイヤーである`/api/json*,/api/jsonws*,/c/portal/json_service*`へのアクセスを保護します。

## リクエストパラメータの認証ベリファイア [](id=request-parameter-auth-verifiers)

`RequestParameterAutoLogin`との後方互換性のために、HTTPリクエストパラメータの`parameterAutoLoginLogin`および`parameterAutoLoginPassword`内の認証情報を使用して、ポータルエンドポイントを認証およびアクセスできます。

この認証ベリファイアはデフォルトでは有効になっていません。

## トンネル認証ベリファイア[](id=tunnel-authentication-verifiers)

`TunnelServlet`は、ポータルリモートサービスへのアクセスを提供するために`/api/liferay/do` にマッピングされた旧バージョンのリモートAPIエンドポイントです。トンネル認証ベリファイアは、信頼できるリモートクライアントに、ユーザーに代わって提供された任意のユーザーIDを使用した認証アクセスを許可します。

信頼できるリモートクライアントの例は、ステージングリモート公開機能です。

信頼できるリモートクライアントは、ポータルプロパティの`tunneling.servlet.shared.secret`に格納されている共有秘密を使用して認証します。デフォルト値は空で、すべてのアクセスを禁止します。

デフォルト設定はデフォルトで有効になっていますが、アクセスはローカルホストのみに制限されています。この設定は、トンネリングを許可するクライアントIPアドレスを設定することでできます。詳細については、[プロパティドキュメンテーション](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)および[リモートステージング](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging)を参照してください 。

## 関連トピック[](id=related-topics)

[サービスアクセスポリシー](/discover/deployment/-/knowledge_base/7-1/)
