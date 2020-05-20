---
header-id: adding-custom-interactors-to-android-screenlets
---

# Androidスクリーンレットへのカスタムインタラクターの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

インタラクターは、特定のユースケースのサーバー通信を実装するスクリーンレットコンポーネントです。 たとえば、ログインスクリーンレットのインタラクターは、ポータルに対してユーザーを認証するLiferay Mobile SDKサービスを呼び出します。 同様に、ブックマークの追加スクリーンレット</a>

のインタラクターは、ブックマークポートレットにブックマークを追加するLiferay Mobile SDKサービスを呼び出します。</p> 

それで十分ですが、Screenletのサーバー呼び出しをカスタマイズしたい場合はどうでしょうか？ スクリーンレットで別のバックエンドを使用する場合はどうなりますか？ 問題ない\！ スクリーンレットのカスタムインタラクターを実装できます。 カスタムロジックまたはネットワークコードを使用して、サーバー呼び出しを行う別のインタラクターをプラグインできます。 これを行うには、現在のインタラクターのインターフェイスを実装し、それをオーバーライドするスクリーンレットに渡す必要があります。 これは、アプリのコード内で、内部クラスまたは別のクラスで行う必要があります。

このチュートリアルでは、ログインスクリーンレットをオーバーライドして、パスワードなしで常に同じユーザーにログインするインタラクターの例を紹介します。 完全なコードは、GitHubの [テストアプリで見つけることができます。](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/CustomInteractorActivity.java) この例では、アクティビティの内部クラスでカスタムインタラクターを実装していることに注意してください。



## カスタムインタラクターの実装

1.  カスタムインタラクターを実装します。 次に示すように、元のインタラクターのインターフェイスを継承する必要があります。 
   
   

    ``` 
     private class CustomLoginInteractor extends LoginBasicInteractor {

         public CustomLoginInteractor(int targetScreenletId) {
             super(targetScreenletId);
         }

         @Override
         public void login() throws Exception {
             //custom implementation
         }
     }
    ```


2.  インタラクターのリスナーを呼び出します。 カスタムロジックでは、インタラクターのリスナーを呼び出す必要があります。 この例では、カスタムロジックの結果に応じて、 `onLoginFailure` および `onLoginSuccess`呼び出す必要があります`
<pre><code>     if (SUCCESS) {
         getListener().onLoginSuccess(fakeUser);
     }
     else {
         getListener().onLoginFailure(new AuthenticationException("bad login"));
     }
`</pre>

3.  カスタムリスナーでインタラクターを返します。 `actionName` （文字列）で作成されたInteractorを予期する特定のリスナーを設定するには、 `setCustomInteractorListener` を使用する必要があります`
<pre><code>     _screenlet.setCustomInteractorListener(this);

     @Override
     public LoginInteractor createInteractor(String actionName) {
         return new CustomLoginInteractor(_loginScreenlet.getScreenletId());
     }
`</pre>

すばらしいです\！ これで、Androidスクリーンレット用のカスタムインタラクターを実装する方法がわかりました。 次の例では、これに基づいて、カスタムインタラクターを使用してLiferay以外のバックエンドにアクセスする方法を示します。



## カスタムインタラクターを使用して他のバックエンドにアクセスする

カスタムインタラクターは、Liferay以外のバックエンドとも通信できます。 次の例では、 [Delicious](https://delicious.com)ブックマークを保存できる [Add Bookmarkスクリーンレット](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets) カスタムインタラクターを作成することでこれを示しています。 この要点</a>で、この例の完全なコード を見つけることができます。</p> 

1.  新しいカスタムインタラクターを作成します。 このインタラクターは、すべてのインタラクターの基本クラスである `BaseRemoteInteractor`継承し、 `AddBookmarkListener` を型パラメーターとして使用します。 また、 `AddBookmarkInteractor` クラスも実装し` 。 この新しいインタラクターの基本コードは次のとおりです。
<pre><code>     public class AddDeliciousInteractorImpl extends BaseRemoteInteractor<AddBookmarkListener>
         implements AddBookmarkInteractor {

         public AddDeliciousInteractorImpl(int targetScreenletId) {
             super(targetScreenletId);
         }

         public void addBookmark(final String url, final String title, long folderId) throws Exception {
             ...
         }
     }
`</pre>

2.  カスタムロジックを実装します。 この例では、Deliciousにアクセスし、Delicious APIで新しいブックマークを挿入するためのコードを実装する必要があります。 [OkHttpライブラリー](http://square.github.io/okhttp/) を使用して、APIにブックマークのURLと説明を渡すことができます。 次のコードはこれを示しています。 
   
   

    ``` 
     new Thread(new Runnable() {
         @Override
         public void run() {
             try {

                 Headers headers = Headers.of("Authorization", "Bearer _OAUTH_TOKEN_");

                 OkHttpClient client = new OkHttpClient();

                 Request add = new Request.Builder()
                     .url("https://api.del.icio.us/api/v1/posts/add?url=" + url + "&description=" + title)
                     .headers(headers)
                     .build();

                 com.squareup.okhttp.Response response = client.newCall(get).execute();

                 String text = response.body().string();

                 ...

             }
             catch (IOException e) {
                 LiferayLogger.e("Error sending", e);
                 ...
             }
         }
     }).start();
    ```


3.  結果をアプリに通知します。 このイベントをポストするには、 `EventBusUtil` クラスを使用する必要があります。 イベントを使用して、他のクラスがイベントをリッスンできるようにします。 次のコードは、 `EventBusUtil.post（text）` を使用してイベントをポストし、 `onEvent` メソッドを使用してリスナーに通知します。 
   
   

    ``` 
     EventBusUtil.post(text);

     ...

     public void onEvent(String text) {
         getListener().onAddBookmarkSuccess();
     }
    ```


[の要点](https://gist.github.com/nhpatt/7cbeb0df6f39ec8a9176) コードは、カスタム `BookmarkAdded` クラスを使用して操作の結果をモデル化していることに注意してください。

4.  Screenletを使用しているアクティビティまたはフラグメントで、 `CustomInteractorListener`実装し` 。 また、新しいカスタムインタラクターを参照し、スクリーンレットに接続する必要があります。
<pre><code>     _screenlet.setCustomInteractorListener(this);

     @Override
     public Interactor createInteractor(String actionName) {
         return new AddDeliciousInteractorImpl(_screenlet.getScreenletId());
     }
`</pre>

驚くばかり\！ これで、Liferay以外のバックエンドと通信できるカスタムインタラクターを作成する方法がわかりました。 これにより、アプリの可能性がさらに広がります。



## 関連トピック

[Android向けLiferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)

[Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)
