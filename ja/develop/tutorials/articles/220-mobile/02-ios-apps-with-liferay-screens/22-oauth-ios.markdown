---
header-id: using-oauth-2-in-liferay-screens-for-ios
---

# iOS用Liferay画面でのOAuth 2の使用

[TOC levels=1-4]

[OAuth 2](https://oauth.net/2/) を使用して、次の [OAuth 2付与タイプ](https://oauth.net/2/grant-types/)[ログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios) を使用して認証できます

</p> 

  - [**認証コード（ネイティブアプリの場合はPKCE）：**](https://oauth.net/2/grant-types/authorization-code/) ユーザーを資格情報を入力するモバイルブラウザーのページにリダイレクトします。 ログイン後、ブラウザはユーザーをモバイルアプリにリダイレクトします。ユーザーの資格情報はアクセスされないため、アプリを介して侵害されることはありません。取り消し可能なトークンを使用します。 これは、ユーザーがアプリに資格情報を入力したくない場合にも役立ちます。たとえば、ユーザーはサードパーティのTwitterアプリにTwitterの資格情報を直接入力したくない場合があり、代わりにTwitterの公式サイトを介して認証することを好みます。 認証のためにリダイレクトするサイトにはOAuth 2が実装されている必要があることに注意してください。

  - [**リソース所有者パスワード：**](https://oauth.net/2/grant-types/password/) ユーザーは、アプリに直接資格情報を入力して認証します。

  - [**クライアント資格情報：**](https://oauth.net/2/grant-types/client-credentials/) ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

このチュートリアルでは、これらの付与タイプをログインスクリーンレットで使用する方法を示します。 開始する前に、ポータルでOAuth 2.0アプリケーションを登録する手順について、 [@product@のOAuth 2.0ドキュメント](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0) を参照してください。



## 認証コード（PKCE）

ログインスクリーンレットで認証コード付与タイプを使用するには、次の手順に従います。

1.  ユーザーが認証された後にモバイルブラウザーがリダイレクトするURLを構成します。 これを行うには、 [Mobile SDKの認証コードの手順](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-ios-mobile-sdk#authorization-code-pkce)の最初の2つの手順に従います。 このURLは、ポータルとiOSアプリの両方で構成する必要があることに注意してください。

2.  ログインスクリーンレットの `loginMode` 属性を `oauth2Redirect`ます。 これを行うには2つの方法があります。
   
         - コードでは、Login Screenletインスタンスの `authType` または `loginMode` プロパティとして：
        
            loginScreenlet.authType = .oauth2Redirect
            // or
            loginScreenlet.loginMode = "oauth2redirect"
            
        
        `loginMode`に設定する場合、 `oauth2redirect` は文字列でなければならないことに注意してください。

      - Interface Builderで、 *ログインモード* 属性の値として。 これは、他のスクリーンレット属性を設定するのと同じ方法で行います（ストーリーボードで選択されたスクリーンレットで、属性インスペクターを使用して）。 必ずピリオドなしで `oauth2redirect` を入力してください。

3.  ログインスクリーンレットの `oauth2clientId` 属性を、使用するポータルのOAuth 2アプリケーションのIDに設定します。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。

4.  ログインスクリーンレットの `oauth2redirectUrl` 属性を手順1で構成したURLに設定します。

5.  `AppDelegate`の `application（_：open：options :)` メソッドで、URLを使用して `SessionContext` メソッド `oauth2ResumeAuthorization` を呼び出します。 これは、リダイレクトが実行されたときにLiferay Screensに通知します。 `application（_：open：options :)` メソッドの詳細については、カスタムURLの使用に関する [Appleのドキュメントのセクション *Incoming URLsの処理* を参照してください](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls)：
   
   func application（_ app：UIApplication、open url：URL、 options：[UIApplicationOpenURLOptionsKey：Any] = [：]）-> Bool { return SessionContext.oauth2ResumeAuthorization（url：url） }

`SessionContext.oauth2Cancel（）`呼び出すことにより、いつでも承認をキャンセルできることに注意してください。



## リソース所有者のパスワード

ログインスクリーンレットでリソース所有者パスワード付与タイプを使用するには、次の手順に従います。

1.  ログインスクリーンレットの `loginMode` 属性を `oauth2UsernameAndPassword`ます。 これを行うには2つの方法があります。
   
         - コードでは、Login Screenletインスタンスの `authType` または `loginMode` プロパティとして：
        
            loginScreenlet.authType = .oauth2UsernameAndPassword
            // or
            loginScreenlet.loginMode = "oauth2UsernameAndPassword"
            
        
        `loginMode`設定する場合、 `oauth2UsernameAndPassword` は文字列でなければならないことに注意してください。

      - Interface Builderで、 *ログインモード* 属性の値として。 これは、他のスクリーンレット属性を設定するのと同じ方法で行います（ストーリーボードで選択されたスクリーンレットで、属性インスペクターを使用して）。 必ずピリオドなしで `oauth2UsernameAndPassword` を入力してください。

2.  ログインスクリーンレットの `oauth2clientId` 属性を、使用するOAuth 2アプリケーションのIDに設定します。 この値を見つけるには、OAuth 2管理ポートレットでそのアプリケーションに移動します。

3.  ログインスクリーンレットの `oauth2clientSecret` 属性を同じOAuth 2アプリケーションのクライアントシークレットに設定します。



## クライアント資格情報

OAuth 2クライアント資格情報付与タイプは、ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

| **警告：** クライアント資格情報付与タイプは、セキュリティリスクをもたらします。ポータル。 ユーザー資格情報なしで認証するには、モバイルアプリに| OAuth 2アプリケーションのクライアントIDとクライアントシークレット。 アクセスできる人|モバイルアプリを介したこれらの値は、ユーザー資格情報なしでも認証できます。

Screensアプリでクライアント資格情報付与タイプを使用するには、次の手順に従います。

1.  クライアント資格情報付与タイプを使用するには、 [iOS Mobile SDKの指示](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-ios-mobile-sdk#client-credentials) 従ってください。

2.  セッションオブジェクトの `認証` プロパティには、有効な認証オブジェクトが含まれています。 `LROAuth2Authentication` キャストしてから、 `SessionContext` メソッド `loginWithOAuth2`の `authentication` 引数に渡します。
   
        let auth = session.authentication as! LROAuth2Authentication
       
        SessionContext.loginWithOAuth2(authentication: auth, userAttributes: [:])
       
   
   これにより、Screens `SessionContext` オブジェクトが初期化され、iOSアプリで使用するScreenletが認証されます。



## 関連トピック

[iOS Mobile SDKでOAuth 2を使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-ios-mobile-sdk)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[OAuth 2.0](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)
