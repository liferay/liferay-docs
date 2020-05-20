---
header-id: using-oauth-2-in-the-android-mobile-sdk
---

# Android Mobile SDKでOAuth 2を使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[OAuth 2](https://oauth.net/2/) を使用して、次の [OAuth 2許可タイプ](https://oauth.net/2/grant-types/)認証できます

</p> 

  - [**認証コード（ネイティブアプリの場合はPKCE）：**](https://oauth.net/2/grant-types/authorization-code/) ユーザーを資格情報を入力するモバイルブラウザーのページにリダイレクトします。 ログイン後、ブラウザはユーザーをモバイルアプリにリダイレクトします。ユーザーの資格情報はアクセスされないため、アプリを介して侵害されることはありません。取り消し可能なトークンを使用します。 これは、ユーザーがアプリに資格情報を入力したくない場合にも役立ちます。たとえば、ユーザーはサードパーティのTwitterアプリにTwitterの資格情報を直接入力したくない場合があり、代わりにTwitterの公式サイトを介して認証することを好みます。 認証のためにリダイレクトするサイトにはOAuth 2が実装されている必要があることに注意してください。

  - [**リソース所有者パスワード：**](https://oauth.net/2/grant-types/password/) ユーザーは、アプリに直接資格情報を入力して認証します。

  - [**クライアント資格情報：**](https://oauth.net/2/grant-types/client-credentials/) ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

このチュートリアルでは、Mobile SDKでこれらの付与タイプを使用する方法を示します。 開始する前に、ポータルでOAuth 2.0アプリケーションを登録する手順について、 [@product@のOAuth 2.0ドキュメント](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0) を参照してください。



## 認証コード（PKCE）

認証コード付与タイプで認証するには、この `OAuth2SignIn` メソッドを呼び出す必要があります。

    OAuth2SignIn.signInWithRedirect(Activity activity, Session session, String clientId, 
        List<String> scopes, Uri redirectUri, CustomTabsIntent customTabsIntent)
    

このメソッドのパラメーターの説明は次のとおりです。

  - `アクティビティ`：モバイルブラウザを表示するために使用するアクティビティ。
  - `セッション`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
  - `redirectUri`：モバイルブラウザーでのログインが成功した後にユーザーがリダイレクトされるURI。 OAuth 2 Adminポートレットを介してポータルでこのURIを構成し、URIをAndroidアプリに関連付ける必要があります。
  - `customTabsIntent`：認証用に表示されるモバイルブラウザーウィンドウの外観をカスタマイズするために使用されるオブジェクト。

この `signInWithRedirect` メソッドは、モバイルブラウザーを開いて認証を開始します。 また、AndroidアプリでリダイレクトURIを構成する必要があります。リダイレクトURIは、認証が完了するとユーザーをAndroidアプリに送り返します。

このワークフローの例を次に示します。

1.  OAuth2管理ポートレットを介してポータルでリダイレクトURIを構成します。 ポータルで、 *コントロールパネル* → *構成* → *OAuth2管理* 、使用するOAuth 2アプリケーションを選択または [作成](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application) します。 次に、 *Callback URIs* フィールドにリダイレクトURIを入力します。 この例のリダイレクトURIは `my-app：// my-app`です。
   
   ![図1：使用するポータルのOAuth 2アプリケーションにリダイレクトURIを入力します。](../../../../images/mobile-oauth2-redirect-url.png)

2.  Androidアプリで、 `AndroidManifest.xml` ファイルを編集してリダイレクトURIを登録します。 このファイルに次のコードを追加します。 <activity
        android:name="com.liferay.mobile.android.auth.oauth2.OAuth2RedirectActivity"
            tools:node="replace">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>
        
                <data android:scheme="<your-scheme>"/>
            </intent-filter>
        </activity>

    If you don't want to use a custom scheme and you need to use HTTPS, add this instead:
    
        <activity
            android:name="com.liferay.mobile.android.auth.oauth2.OAuth2RedirectActivity"
            tools:node="replace">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>
                <data android:scheme="https"
                      android:host="your.custom.domain"
                      android:path="/oauth2redirect"/>
            </intent-filter>
        </activity>
    

3.  認証を実行するアクティビティで、 `OAuth2SignIn.signInWithRedirect`呼び出します`</p>

<pre><code> private void doLogin() {
     Session session = new SessionImpl("http://my-server.com");
     OAuth2SignIn.signInWithRedirect(this, session, "54321", new ArrayList<>(), 
     Uri.parse("my-app://my-app"), null);
 }
`</pre> 
   
   これにより、ログインページでモバイルブラウザが開きます。</li> 
   
   4  同じアクティビティで、認証の結果を受け取るには、メソッド `onActivityResult` をオーバーライドする必要があります。 このメソッドでは、メソッド `OAuth2SignIn.resumeAuthorizationFlowWithIntent`呼び出してこれを行います。
  
       @Override
       protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
           super.onActivityResult(requestCode, resultCode, intent);
      
           if (requestCode == OAuth2SignIn.REDIRECT_REQUEST_CODE) {
               Session session = new SessionImpl("http://my-server.com");
               OAuth2SignIn.resumeAuthorizationFlowWithIntent(this, session, intent,
               new SessionCallback() {
                   @Override
                   public void onSuccess(Session session) {
                       // Login success
                   }
      
                   @Override
                   public void onFailure(Exception e) {
                       // Login error
                   }
               );
           }
       }
      </ol> 



## リソース所有者のパスワード

リソース所有者パスワード付与タイプによる認証は、リダイレクトURLを設定する必要がないことを除いて、PKCE付与タイプによる認証に似ています。 代わりに、別の `OAuth2SignIn` メソッドを使用して、Androidアプリでユーザーの資格情報を直接処理します。

    public static Session signInWithUsernameAndPassword(String username, String password, Session session,
        String clientId, String clientSecret, List<String> scopes, SessionCallback callback)
    

PKCE付与タイプに使用される `OAuth2SignIn.signInWithRedirect` メソッドと比較すると、このメソッドはリダイレクトURIではなくユーザーの資格情報を必要とします。 また、ポータルからのOAuth 2アプリケーションのクライアントシークレットとコールバックも必要です。

このメソッドのパラメーターの説明は次のとおりです。

  - `ユーザー名`：ユーザーのユーザー名。
  - `password`：ユーザーのパスワード。
  - `セッション`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `clientSecret`：ポータル内の同じOAuth 2アプリケーションのクライアントシークレット。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
  - `コールバック`：認証の結果を含む `SessionCallback` オブジェクト。 認証が成功すると、認証を含むnull以外のセッションを受け取ります。そうしないと、エラーが表示されます。

| **注：** `OAuth2SignIn.signInWithUsernameAndPassword` メソッドを呼び出すことができます|コールバックの代わりに `null` を渡すことにより、コールバックなしで。 これにより|同期的に実行するリクエスト。 コールバックを提供する場合、要求は|別のスレッドで非同期に実行され、コールバックは|応答。

以下は、リソース所有者パスワード付与タイプに対して `OAuth2SignIn.signInWithUsernameAndPassword` メソッドを呼び出す例です。 セッションを作成した後、匿名の内部クラスとして作成された `SessionCallback` でメソッドが呼び出されます。

    Session session = new SessionImpl("http://my-server.com");
    
    OAuth2SignIn.signInWithUsernameAndPassword("username", "password", session, "12345", "12345", 
        new ArrayList<>(), new SessionCallback() {
    
        @Override
        public void onSuccess(Session session) {
            // Login correct
        }
    
        @Override
        public void onFailure(Exception e) {
            // Login error
        }
    });
    



## クライアント資格情報

OAuth 2クライアント資格情報付与タイプは、ユーザーの操作を必要とせずに認証します。 これは、アプリが特定のユーザーのリソースではなく、独自のリソースにアクセスする必要がある場合に役立ちます。

| **警告：** クライアント資格情報付与タイプは、セキュリティリスクをもたらします。ポータル。 ユーザー資格情報なしで認証するには、モバイルアプリに| OAuth 2アプリケーションのクライアントIDとクライアントシークレット。 アクセスできる人|モバイルアプリを介したこれらの値は、ユーザー資格情報なしでも認証できます。

クライアント資格情報付与タイプで認証するには、 `OAuth2SignIn.clientCredentialsSignIn` メソッドを呼び出す必要があります。 このメソッドには、ユーザー資格情報またはリダイレクトURIの引数がないことに注意してください。

    public static Session clientCredentialsSignIn(Session session, String clientId, String clientSecret,
        List<String> scopes, SessionCallback callback)
    

このメソッドのパラメーターの説明は次のとおりです。

  - `セッション`：認証するセッション。 `サーバー` プロパティを設定する必要があります。
  - `clientId`：使用するポータルのOAuth 2アプリケーションのID。 この値を見つけるには、ポータルのOAuth 2管理ポートレットでそのアプリケーションに移動します。
  - `clientSecret`：ポータル内の同じOAuth 2アプリケーションのクライアントシークレット。
  - `スコープ`：要求するポータルのアクセス許可。 ポータルのOAuth 2管理ポートレットで、OAuth 2アプリケーションに関連付けられた一連の権限を定義できます。 このプロパティを使用して、これらの権限のサブセットを要求します。
  - `コールバック`：認証の結果を含む `SessionCallback` オブジェクト。 認証が成功すると、認証を含むnull以外のセッションを受け取ります。そうしないと、エラーが表示されます。

| **注：** `OAuth2SignIn.clientCredentialsSignIn` メソッドは、|なしで呼び出すことができます。コールバックの代わりに `null` を渡すことによるコールバック。 これによりリクエストが発生します|同期的に実行します。 コールバックを提供すると、リクエストが実行されます|別のスレッドで非同期に実行し、コールバックが応答を受け取ります。

以下は、リソース所有者パスワード付与タイプに対して `OAuth2SignIn.clientCredentialsSignIn` メソッドを呼び出す例です。 セッションを作成した後、匿名の内部クラスとして作成された `SessionCallback` でメソッドが呼び出されます。

    Session session = new SessionImpl("http://my-server.com");
    
    OAuth2SignIn.clientCredentialsSignIn(session, "12345", "12345", new ArrayList<>(), 
        new SessionCallback() {
    
        @Override
        public void onSuccess(Session session) {
            // Login correct
        }
    
        @Override
        public void onFailure(Exception e) {
            // Login error
        }
    });
    



## 関連トピック

[Liferay Screens for AndroidでOAuth 2を使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-liferay-screens-for-android)

[OAuth 2.0](/docs/7-1/deploy/-/knowledge_base/d/oauth-2-0)
