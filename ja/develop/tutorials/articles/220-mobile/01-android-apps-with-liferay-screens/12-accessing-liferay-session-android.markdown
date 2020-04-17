---
header-id: accessing-the-liferay-session-in-android
---

# AndroidでLiferayセッションにアクセスする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

セッションは、クライアントとサーバー間の会話状態です。 通常、2つの間の複数の要求と応答で構成されます。 この通信を容易にするには、セッションにサーバーIPアドレスとユーザーのログイン資格情報が必要です。 Liferay Screensは、Liferayセッションを使用して、Liferay Portalが提供するJSON Webサービスにアクセスしてクエリを実行します。 Liferayセッションを使用してログインすると、ポータルはユーザーの情報（名前、電子メール、ユーザーIDなど）を返します。 Screensは、この情報とアクティブなLiferayセッションをScreensの `SessionContext` クラスに保存します。

`SessionContext` クラスは非常に強力であり、さまざまなシナリオで画面を使用できます。 たとえば、 `SessionContext` を使用して、Liferayが提供するJSON WS APIで情報を要求できます。 `SessionContext` を使用して、匿名セッションを作成したり、ログインスクリーンレットを表示せずにユーザーをログインしたりすることもできます。

このチュートリアルでは、一般的な `SessionContext` 使用例を説明し、クラスの最も重要なメソッドについても説明します。

## 既存のセッションからセッションを作成する

Liferay Screensを使用する場合、Liferay Mobile SDKによって提供されるリモートJSON Webサービスを呼び出すことができます。 Liferay Mobile SDKを使用するすべての操作には、サーバーアドレス、ユーザー資格情報、およびその他の必要なパラメーターを提供するLiferayセッションが必要です。 ユーザーが正常にログインすると、ログインスクリーンレットがセッションを作成するため、 `SessionContext` メソッド `createSessionFromCurrentSession（）`を使用してこのセッションを取得できます。 その後、そのセッションを使用して、Mobile SDKサービスを呼び出すことができます。 次の例は、Mobile SDKの `BookmarksEntryService`を呼び出すためのこれを示しています。

    Session sessionFromCurrentSession = SessionContext.createSessionFromCurrentSession();
    sessionFromCurrentSession.setCallback(callback);
    
    new BookmarksEntryService(sessionFromCurrentSession).methodCall()

ユーザーがログインしているかどうかを最初に確認する必要がある場合は、 `SessionContext.isLoggedIn（）`使用できます。

すばらしいです\！ これで、アプリで既存のセッションを取得する方法がわかりました。しかし、ログインスクリーンレットを使用していない場合はどうでしょうか。 取得する既存のセッションはありません。 汗かいていない\！ `SessionContext` を使用して手動で作成できます。 次のセクションでは、これを行う方法を示します。

## セッションを手動で作成する

ログインスクリーンレットを使用しない場合、 `SessionContext` は取得するセッションがありません。 この場合、手動で作成する必要があります。 これを行うには、 `SessionContext` メソッド `createBasicSession`ます。 このメソッドは、ユーザー名とパスワードをパラメーターとして受け取り、それらの資格情報でセッションを作成します。 ユーザーの情報にもアクセスする必要がある場合は、 `User` JSON Webサービスを手動で呼び出すか、 `SessionContext.setLoggedUser（）`呼び出す必要があります。 次のコードは、 `createBasicSession` セッションを作成し、 `setLoggedUser` を使用して `SessionContext`ユーザーを設定します。

    LiferayScreensContext.init(this);
    
    Session session = SessionContext.createBasicSession(USERNAME, PASSWORD);
    SessionContext.setLoggedUser(USER);

インタラクターを直接呼び出すことで同じことを実現できることに注意してください。

    LoginBasicInteractor loginBasicInteractor = new LoginBasicInteractor(0);
    loginBasicInteractor.onScreenletAttached(this);
    loginBasicInteractor.setLogin(USERNAME);
    loginBasicInteractor.setPassword(PASSWORD);
    loginBasicInteractor.login();

スーパー\！ これで、セッションを手動で作成する方法がわかりました。 次のセクションでは、自動ログインを実装し、セッションを保存または復元する方法を示します。

## 自動ログインの実装とセッションの保存または復元

ログインスクリーンレットはすばらしいですが、ユーザーがアプリを開くたびに資格情報を入力したくない場合があります。アプリで必要なログインは1回だけです。 あなたのアプリでこれを実現するために、参照 [この動画](https://www.youtube.com/watch?v=kEZEahTzuck)。

つまり、ストレージタイプをログインスクリーンレットに渡してから、 `SessionContext.isLoggedIn（）` を使用してセッションをチェックする必要があります。 セッションが存在しない場合、 `CredentialsStorage` から `loadStoredCredentials（StorageType storageType）`、保存されたセッションをロードします。 次のコードは、これの典型的な実装を示しています。

    LiferayScreensContext.init(this); // If you haven't called a Screenlet yet
    SessionContext.loadStoredCredentials(SHARED_PREFERENCES);
    
    if (SessionContext.isLoggedIn()) {
        // logged in
        // consider doing a relogin here (see next section)
    } else {
        // send user to login form
    }

驚くばかり\！ これで、Liferay Screensアプリに自動ログインを実装する方法がわかりました。 利用可能な `SessionContext` メソッドの詳細については、このチュートリアルの最後にある [メソッドセクション](#methods) を参照してください。 次に、ユーザーがログインしている間にサーバー上でユーザーの資格情報が変更された場合に、再ログインを実装する方法を学習します。

## 再ログインの実装

セッションは、ログインスクリーンレットまたは自動ログインのどちらで作成された場合でも、Liferayインスタンスのユーザーを確認する基本的なユーザーデータを含みます。 サーバーでデータが変更されると、セッションが古くなり、アプリの動作が不安定になる可能性があります。 また、ユーザーがサーバーで削除、無効化、またはその他の方法で資格情報を変更した場合、自動ログイン機能はサーバートランザクションを実行しないためアクセスを拒否しません。ローカルストレージから既存のセッションを取得するだけです。 これは最適な状況ではありません\！

このようなシナリオでは、再ログイン機能を使用できます。 この機能は、現在のセッションがまだ有効かどうかを判断する簡単な方法で実装されます。 セッションがまだ有効な場合、ユーザーのデータはサーバーからの最新のデータで更新されます。 セッションが有効でない場合、ユーザーはログアウトされ、再度ログインして新しいセッションを作成する必要があります。

この機能を使用するには、 `LoginListener` インターフェイスを実装するオブジェクトを引数として、 `SessionContext` メソッド `relogin`呼び出します。

    SessionContext.relogin(listener);

このメソッドは、リスナーの `onLoginSuccess` および `onLoginFailure` メソッドを介して成功または失敗をそれぞれ処理します。 この操作はバックグラウンドスレッドで非同期に行われるため、リスナーはそのスレッドで呼び出されることに注意してください。 UI操作も実行する場合は、UIスレッドで実行する必要があります。 たとえば、

    @Override
    public void onLoginSuccess(final User user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // do any UI operation here
            }
        });
    }

すばらしいです\！ これで、アプリに再ログインを実装する方法がわかりました。また、 `SessionContext` がどれほど便利かを見てきました。 さらに多くのことができます\！ 次のセクションでは、追加の `SessionContext` メソッドと、このチュートリアルで使用されるメソッドの詳細をリストします。

## 方法

| 方法                                                     | 戻りタイプ     | 説明                                                                                                                                                                                                  |
| ------------------------------------------------------ | --------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `logout()`                                             | `void`    | 保存されているユーザー属性とセッションをクリアします。                                                                                                                                                                         |
| `relogin(LoginListener)`                               | `void`    | サーバーからユーザーデータを更新します。 これは、成功した場合は `currentUser` オブジェクトを再作成し、失敗した場合は `logout（）` を呼び出します。 サーバーデータを受信すると、リスナーメソッド `onLoginSuccess` が、受信したユーザーの属性で呼び出されます。 エラーが発生すると、リスナーメソッド `onLoginFailure` が呼び出されます。 |
| `isLoggedIn()`                                         | `boolean` | `SessionContext`Liferayセッションが保存されている場合、 `true` 返します。                                                                                                                                                |
| `createBasicSession(String username, String password)` | `Session` | デフォルトのサーバーと指定されたユーザー名とパスワードを使用してLiferayセッションを作成します。                                                                                                                                                 |
| `createSessionFromCurrentSession()`                    | `Session` | 保存された資格情報とサーバーに基づいてLiferayセッションを作成します。                                                                                                                                                              |
| `getCurrentUser()`                                     | `User`    | ログインしているユーザーのサーバー属性を含む `User` オブジェクトを返します。 これには、ユーザーの電子メール、ユーザーID、名前、およびポートレートIDが含まれます。                                                                                                             |
| `storeCredentials(StorageType storageType)`            | `void`    | パラメーターとして指定された `StorageType` 現在のセッションを格納します。                                                                                                                                                        |
| `removeStoredCredentials(StorageType storageType)`     | `void`    | ユーザー情報とセッションの `StorageType` をクリアし` 。</td>
</tr>
<tr>
  <td><code>loadStoredCredentials(StorageType storageType)`                                                                     | `void` | `StorageType` パラメーターからセッションおよびユーザー情報をロードし、それを現在のセッションおよびユーザーとして使用します。 |

詳細については、GitHub</a>の

`SessionContext` ソースコードを参照してください。</p> 



## 関連トピック

[Androidのログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
