---
header-id: using-oauth-2-in-liferay-screens-for-android
---

# Liferay Screens for AndroidでOAuth 2を使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[OAuth 2](https://oauth.net/2/) を使用して、次の [OAuth 2付与タイプ](https://oauth.net/2/grant-types/)[ログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android) を使用して認証できます

</p> 

  - [**認証コード（ネイティブアプリの場合はPKCE）：**](https://oauth.net/2/grant-types/authorization-code/) ユーザーを資格情報を入力するモバイルブラウザーのページにリダイレクトします。 ログイン後、ブラウザはユーザーをモバイルアプリにリダイレクトします。ユーザーの資格情報はアクセスできないため、アプリを介して侵害されることはありません。取り消し可能なトークンを使用します。 これは、ユーザーがアプリに資格情報を入力したくない場合にも役立ちます。たとえば、ユーザーはサードパーティのTwitterアプリにTwitterの資格情報を直接入力したくない場合があり、代わりにTwitterの公式サイトを介して認証することを好みます。 認証のためにリダイレクトするサイトにはOAuth 2が実装されている必要があることに注意してください。

  - [**リソース所有者パスワード：**](https://oauth.net/2/grant-types/password/) ユーザーは、アプリに直接資格情報を入力して認証します。

  - [**クライアント資格情報：**](https://oauth.net/2/grant-types/client-credentials/) ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

このチュートリアルでは、これらの付与タイプをログインスクリーンレットで使用する方法を示します。 開始する前に、ポータルでOAuth 2.0アプリケーションを登録する手順について、 [@product@のOAuth 2.0ドキュメント](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0) を参照してください。



## 認証コード（PKCE）

ログインスクリーンレットで認証コード付与タイプを使用するには、次の手順に従います。

1.  ユーザーが認証された後にモバイルブラウザーがリダイレクトするURLを構成します。 これを行うには、 [Mobile SDKの認証コードの手順](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-android-mobile-sdk#authorization-code-pkce)の最初の2つの手順に従います。 ポータルとAndroidアプリの両方でこのURLを構成する必要があることに注意してください。

2.  ログインスクリーンレットの `loginMode` 属性を `oauth2Redirect`ます。 これを行うには2つの方法があります。
   
         - コードでは、Login Screenletインスタンスの `authenticationType` 変数として。 この変数は、Login Screenletの `setAuthenticationType` メソッドを使用して、 `AuthenticationType` enum定数 `OAUTH2REDIRECT`を使用して設定する必要があります。 
        
        

        ``` 
        loginScreenlet.setAuthenticationType(AuthenticationType.OAUTH2REDIRECT); 
        ```


      - Login ScreenletのXMLを挿入するとき、 `loginMode` 属性を `oauth2Redirect`ます。

3.  ログインスクリーンレットのXMLで、ログインスクリーンレットの `oauth2ClientId` 属性を、使用するポータルのOAuth 2アプリケーションのIDに設定します。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。

4.  ログインスクリーンレットのXMLで、ログインスクリーンレットの `oauth2Redirect` 属性を手順1で構成したURLに設定します。
   
   上記の手順の属性を使用したログインスクリーンレットのXMLの例を次に示します。
   
   <com.liferay.mobile.screens.auth.login.LoginScreenlet android:id="@+id/login_screenlet" android:layout_width="match_parent" android:layout_height="match_parent" app:loginMode="oauth2Redirect" app:oauth2Redirect="my-app://my-app" app:oauth2ClientId="54321" app:credentialsStorage="shared_preferences" />

5.  Login Screenletを使用するアクティビティでは、 `onActivityResult` メソッドをオーバーライドして、最初のステップで設定したリダイレクトを実装する必要があります。 これを行うには、Login Screenletの `resumeOAuth2RedirectFlow` メソッドを呼び出します。
   
   @Override protected void onActivityResult（int requestCode、int resultCode、Intent intent）{ super.onActivityResult（requestCode、resultCode、intent）;
   
          if (requestCode == OAuth2SignIn.REDIRECT_REQUEST_CODE) {
              loginScreenlet.resumeOAuth2RedirectFlow(intent);
          }
       
   
   }



## リソース所有者のパスワード

ログインスクリーンレットでリソース所有者パスワード付与タイプを使用するには、次の手順に従います。

1.  ログインスクリーンレットの `loginMode` 属性を `oauth2UsernameAndPassword`ます。 これを行うには2つの方法があります。
   
         - コードでは、Login Screenletインスタンスの `authenticationType` 変数として。 この変数は、Login Screenletの `setAuthenticationType` メソッドを使用して、 `AuthenticationType` 列挙定数 `OAUTH2USERNAMEANDPASSWORD`を使用して設定する必要があります。
        
            loginScreenlet.setAuthenticationType(AuthenticationType.OAUTH2USERNAMEANDPASSWORD);
            

      - Login ScreenletのXMLを挿入するとき、 `loginMode` 属性を `oauth2UsernameAndPassword`ます。

2.  ログインスクリーンレットのXMLで、ログインスクリーンレットの `oauth2ClientId` 属性を、使用するOAuth 2アプリケーションのIDに設定します。 この値を見つけるには、OAuth 2管理ポートレットでそのアプリケーションに移動します。

3.  ログインスクリーンレットのXMLで、ログインスクリーンレットの `oauth2ClientSecret` 属性を同じOAuth 2アプリケーションのクライアントシークレットに設定します。
   
   上記の手順の属性を使用したログインスクリーンレットのXMLの例を次に示します。
   
   <com.liferay.mobile.screens.auth.login.LoginScreenlet android:id="@+id/login_screenlet" android:layout_width="match_parent" android:layout_height="match_parent" app:loginMode="oauth2UsernameAndPassword" app:oauth2ClientId="54321" app:oauth2ClientSecret="12345" app:basicAuthMethod="email" app:credentialsStorage="shared_preferences" />



## クライアント資格情報

OAuth 2クライアント資格情報付与タイプは、ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

| **警告：** クライアント資格情報付与タイプは、セキュリティリスクをもたらします。ポータル。 ユーザー資格情報なしで認証するには、モバイルアプリに| OAuth 2アプリケーションのクライアントIDとクライアントシークレット。 アクセスできる人|モバイルアプリを介したこれらの値は、ユーザー資格情報なしでも認証できます。

Androidアプリでクライアント資格情報付与タイプを使用するには、次の手順に従います。

1.  クライアント資格情報付与タイプを使用するには、 [Android Mobile SDKの指示](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-android-mobile-sdk#client-credentials) 従ってください。

2.  セッションオブジェクトには有効な認証オブジェクトが含まれています。 セッションを引数として `SessionContext` メソッド `createOAuth2Session`渡します。
   
        SessionContext.createOAuth2Session(session);
       
   
   これにより、Screens `SessionContext` オブジェクトが初期化され、Androidアプリで使用するScreenletが認証されます。



## 関連トピック

[Android Mobile SDKでOAuth 2を使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-android-mobile-sdk)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[OAuth 2.0](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)
