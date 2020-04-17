---
header-id: using-oauth-2-in-the-ios-mobile-sdk
---

# iOS Mobile SDKでOAuth 2を使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[OAuth 2](https://oauth.net/2/) を使用して、次の [OAuth 2許可タイプ](https://oauth.net/2/grant-types/)を使用して認証できます

</p> 

  - [**認証コード（ネイティブアプリの場合はPKCE）：**](https://oauth.net/2/grant-types/authorization-code/) ユーザーを資格情報を入力するモバイルブラウザーのページにリダイレクトします。 ログイン後、ブラウザはユーザーをモバイルアプリにリダイレクトします。ユーザーの資格情報はアクセスされないため、アプリを介して侵害されることはありません。簡単に取り消すことができるトークンを使用します。 これは、ユーザーがアプリに資格情報を入力したくない場合にも役立ちます。たとえば、ユーザーはサードパーティのTwitterアプリにTwitterの資格情報を直接入力したくない場合があり、代わりにTwitterの公式サイトを介して認証することを好みます。 認証のためにリダイレクトするサイトにはOAuth 2が実装されている必要があることに注意してください。

  - [**リソース所有者パスワード：**](https://oauth.net/2/grant-types/password/) ユーザーは、アプリに直接資格情報を入力して認証します。

  - [**クライアント資格情報：**](https://oauth.net/2/grant-types/client-credentials/) ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

このチュートリアルでは、Mobile SDKでこれらの付与タイプを使用する方法を示します。 開始する前に、ポータルでOAuth 2.0アプリケーションを登録する手順について、 [@product@のOAuth 2.0ドキュメント](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0) を参照してください。



## 認証コード（PKCE）

認証コード付与タイプで認証するには、次の `LROAuth2SignIn` メソッドを呼び出す必要があります`</p>

<pre><code>LROAuth2SignIn.signIn(withRedirectURL: URL, session: LRSession, clientId: String, 
    scopes: [String], callback: (LRSession?, Error?) -> Void) -> LROAuth2AuthorizationFlow
`</pre> 

このメソッドのパラメーターの説明は次のとおりです。

  - `withRedirectURL`：モバイルブラウザーでのログインに成功した後にユーザーがリダイレクトされるURL。 OAuth 2 Adminポートレットを介してポータルでこのURLを構成する必要があります [はURLをiOSアプリに関連付けます](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls)。
  - `セッション`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
    <!-- Why doesn't the example app use this property? -->
  - `コールバック`：認証の結果で呼び出される関数。 認証が成功すると、認証を含むnull以外のセッションを受け取ります。そうしないと、エラーが表示されます。

この `LROAuth2SignIn.signIn` メソッドは、進行中の認証要求を表す `LROAuth2AuthorizationFlow` オブジェクトを返します。 あなたはこれを保存しなければなりません `AppDelegate` プロパティ、次に呼び出す `LROAuth2AuthorizationFlow` 方法 `resumeAuthorizationFlowWithURL` で `アプリケーション（_：オープン：オプション:)` の方法を。

このワークフローの例を次に示します。

1.  OAuth2管理ポートレットを介してポータルでリダイレクトURIを構成します。 ポータルで、 *コントロールパネル* → *構成* → *OAuth2管理* 、使用するOAuth 2アプリケーションを選択または [作成](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application) します。 次に、 *Callback URIs* フィールドにリダイレクトURIを入力します。 この例のリダイレクトURIは `my-app：// my-app`です。
   
   ![図1：使用するポータルのOAuth 2アプリケーションにリダイレクトURLを入力します。](../../../../images/mobile-oauth2-redirect-url.png)

2.  iOSアプリで、プロジェクト設定の[ *情報* ]タブからリダイレクトURLを登録します。 この手順については、カスタムURLの使用に関するAppleのドキュメント [のセクション *URLスキームの登録* を参照してください](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls)。
   
   ![図2：iOSアプリにリダイレクトURLを登録します。](../../../../images/ios-register-url.png)

3.  `AppDelegate`で、 `LROAuth2AuthorizationFlow` プロパティを作成します。 後で `LROAuth2SignIn.signIn` メソッドを呼び出すときに、このプロパティを設定します。
   
        var authorizationFlow: LROAuth2AuthorizationFlow?
       

4.  `LROAuth2SignIn.signIn`呼び出すView Controllerで、認証の結果で実行されるコールバックを定義します。 このコールバックは、必要なアクションを実行できます。 この例では、認証が成功すると、コールバックは成功メッセージを出力し、セッションのユーザー資格情報をテストするサンプルメソッドを呼び出します。それ以外の場合、エラーメッセージが出力されます。
   
        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }
       

5.  同じView Controllerで、上記のパラメーターを指定して `LROAuth2SignIn.signIn` メソッドを呼び出します。 結果の `LROAuth2AuthorizationFlow` を、手順3で作成した `AppDelegate` プロパティに設定します。 この例では、 `loginWithRedirect（）` メソッドでこれを行います。
   
        func loginWithRedirect() {
            let session = LRSession(server: "http://my-server.com")
            let redirectUrl = URL(string: "my-app://my-app")!
            let clientIdRedirect = "54321"
       
            let authorizationFlow = LROAuth2SignIn.signIn(withRedirectURL: redirectUrl,
                session: session, clientId: clientIdRedirect, scopes: [], callback: oauth2Callback)
       
            (UIApplication.shared.delegate as! AppDelegate).authorizationFlow = authorizationFlow
        }
       

6.  `AppDelegate`の `application（_：open：options :)` メソッドで、URLで `LROAuth2AuthorizationFlow` メソッド `resumeAuthorizationFlow` を呼び出します。 `application（_：open：options :)` メソッドの詳細については、カスタムURLの使用に関する [Appleのドキュメントのセクション *Incoming URLsの処理* を参照してください](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls)：
   
        @UIApplicationMain
        class AppDelegate: UIResponder, UIApplicationDelegate {
       
            ...
       
            var authorizationFlow: LROAuth2AuthorizationFlow?
       
            func application(_ app: UIApplication, open url: URL,
                options: [UIApplicationOpenURLOptionsKey : Any] = [:]) -> Bool {
       
                if let authorizationFlow = authorizationFlow {
                    return authorizationFlow.resumeAuthorizationFlow(with: url)
                }
            }
       
            ...
       
        }
       



## リソース所有者のパスワード

リソース所有者パスワード付与タイプを介した認証は、リダイレクトURLを必要としないことを除いて、PKCE付与タイプを介した認証に似ています。 代わりに、わずかに異なる `LROAuth2SignIn.signIn` メソッドを使用して、iOSアプリでユーザーの資格情報を直接処理します。

    LROAuth2SignIn.signIn(withUsername: String, password: String, session: LRSession, clientId: String, 
        clientSecret: String, scopes: [String], callback: (LRSession?, Error?) -> Void) -> LRSession?
    

PKCE付与タイプに使用される `LROAuth2SignIn.signIn` メソッドと比較すると、このメソッドではリダイレクトURLではなくユーザーの資格情報が必要です。 また、ポータルからのOAuth 2アプリケーションのクライアントシークレットも必要です。

このメソッドのパラメーターの説明は次のとおりです。

  - `withUsername`：ユーザーのユーザー名。
  - `password`：ユーザーのパスワード。
  - `セッション`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `clientSecret`：ポータル内の同じOAuth 2アプリケーションのクライアントシークレット。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
    <!-- Why doesn't the example app use this property? -->
  - `コールバック`：認証の結果で呼び出される関数。 認証が成功すると、認証を含むnull以外のセッションを受け取ります。そうしないと、エラーが表示されます。

| **注：** コールバックなしで `LROAuth2SignIn.signIn` メソッドを呼び出すことができます| `コールバック` 引数として `nil` を渡します。 これにより、リクエストが実行されます|同期的に。 コールバックを提供すると、リクエストは非同期に実行されます|別のスレッドでコールバックが応答を受け取ります。

以下の手順に従って、リソース所有者パスワード付与タイプの `LROAuth2SignIn.signIn` メソッドを呼び出します。

1.  コールバックを提供する場合は、 `LROAuth2SignIn.signIn`呼び出すビューコントローラーでコールバックを定義します。 このコールバックは、必要なアクションを実行できます。 この例では、認証が成功すると、コールバックは成功メッセージを出力し、セッションのユーザー資格情報をテストするサンプルメソッドを呼び出します。それ以外の場合、エラーメッセージが出力されます。
   
        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }
       

2.  同じView Controllerで、上記のパラメーターを指定して `LROAuth2SignIn.signIn` メソッドを呼び出します。 この例では、 `loginWithUsernameAndPassword（）` メソッドでこれを行います。
   
        func loginWithUsernameAndPassword() {
            if password.isEmpty {
                fatalError("you have to enter the password")
            }
       
            let session = LRSession(server: "http://my-server.com")
            let clientId = "12345"
            let clientSecret = "12345"
       
            _ = try? LROAuth2SignIn.signIn(withUsername: "test@example.com", password: password,
                        session: session, clientId: clientId, clientSecret: clientSecret, scopes: [], 
                        callback: oauth2Callback)
        }
       



## クライアント資格情報

OAuth 2クライアント資格情報付与タイプは、ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

| **警告：** クライアント資格情報付与タイプは、セキュリティリスクをもたらします。ポータル。 ユーザー資格情報なしで認証するには、モバイルアプリに| OAuth 2アプリケーションのクライアントIDとクライアントシークレット。 アクセスできる人|モバイルアプリを介したこれらの値は、ユーザー資格情報なしでも認証できます。

クライアント資格情報付与タイプで認証するには、ユーザー資格情報またはリダイレクトURLの引数を持たない `LROAuth2SignIn.signIn` メソッドを呼び出す必要があります。

    LROAuth2SignIn.clientCredentialsSignIn(with: LRSession, clientId: String, 
        clientSecret: String, scopes: [String], callback: (LRSession?, Error?) -> Void)
    

このメソッドのパラメーターの説明は次のとおりです。

  - `with`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `clientSecret`：ポータル内の同じOAuth 2アプリケーションのクライアントシークレット。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
    <!-- Why doesn't the example app use this property? -->
  - `コールバック`：認証の結果で呼び出される関数。 認証が成功すると、認証を含むnull以外のセッションを受け取ります。そうしないと、エラーが表示されます。

| **注：** コールバックなしで `LROAuth2SignIn.signIn` メソッドを呼び出すことができます| `コールバック` 引数として `nil` を渡します。 これにより、リクエストが実行されます|同期的に。 コールバックを提供すると、リクエストは非同期に実行されます|別のスレッドでコールバックが応答を受け取ります。

次の手順に従って、クライアント資格情報付与タイプの `LROAuth2SignIn.signIn` メソッドを呼び出します。

1.  コールバックを提供する場合は、 `LROAuth2SignIn.signIn`呼び出すビューコントローラーでコールバックを定義します。 このコールバックは、必要なアクションを実行できます。 この例では、認証が成功すると、コールバックは成功メッセージを出力し、セッションのユーザー資格情報をテストするサンプルメソッドを呼び出します。それ以外の場合、エラーメッセージが出力されます。
   
        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }
       

2.  同じView Controllerで、上記のパラメーターを指定して `LROAuth2SignIn.signIn` メソッドを呼び出します。 この例では、 `loginWithClientCredentials（）` メソッドでこれを行います。
   
        func loginWithClientCredentials() {
       
            let session = LRSession(server: "http://my-server.com")
            let clientId = "12345"
            let clientSecret = "12345"
       
            _ = try? LROAuth2SignIn.clientCredentialsSignIn(with: session, clientId: clientId, 
                        clientSecret: clientSecret, scopes: [], callback: oauth2Callback)
        }
       



## 関連トピック

[iOS用Liferay画面でのOAuth 2の使用](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-liferay-screens-for-ios)

[OAuth 2.0](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)
