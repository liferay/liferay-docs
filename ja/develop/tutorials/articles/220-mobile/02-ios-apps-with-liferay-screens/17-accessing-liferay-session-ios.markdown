---
header-id: accessing-the-liferay-session-in-ios
---

# iOSでLiferayセッションにアクセスする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

セッションは、クライアントとサーバー間の会話状態です。 通常、2つの間の複数の要求と応答で構成されます。 この通信を容易にするには、セッションにサーバーIPアドレスとユーザーのログイン資格情報が必要です。 Liferay Screensは、Liferayセッションを使用して、Liferay Portalが提供するJSON Webサービスにアクセスしてクエリを実行します。 Liferayセッションを使用してログインすると、ポータルはユーザーの情報（名前、電子メール、ユーザーIDなど）を返します。 Screensは、この情報とアクティブなLiferayセッションをScreensの `SessionContext` クラスに保存します。

`SessionContext` クラスは非常に強力であり、さまざまなシナリオで画面を使用できます。 たとえば、 `SessionContext` を使用して、Liferayが提供するJSON WS APIまたはLiferay Mobile SDKで情報を要求できます。 `SessionContext` を使用して匿名セッションを作成したり、ログインスクリーンレットを表示せずにユーザーをログインしたりすることもできます。

このチュートリアルでは、一般的な `SessionContext` 使用例と、クラスの最も重要なメソッドについて説明します。

## 現在のセッションを取得する

ユーザーがログインスクリーンレットで正常にログインすると、現在のセッションが確立されます。 セッションを取得するには、 `SessionContext.currentContext` を使用します。 ユーザーがログインスクリーンレットでサインインしなかった場合、これは `nil` を返し` 。 <code>SessionContext` プロパティ `isLoggedIn` を使用して、セッションが存在するかどうかを判断することもできます。 現在のセッションがない場合、 `false` 返します。

## Liferayセッションの作成

Liferay Screensを使用する場合、Liferay Mobile SDKによって提供されるリモートJSON Webサービスを呼び出すことができます。 Liferay Mobile SDKを使用するすべての操作には、サーバーアドレス、ユーザー資格情報、およびその他の必要なパラメーターを提供するLiferayセッションが必要です。 ログインスクリーンレットは、ユーザーが正常にログインするとセッションを作成します。 このセッションは、 `SessionContext` メソッド `createRequestSession（）`取得できます。 通常、このメソッドは `currentContext` オブジェクトを介して呼び出します。 たとえば、

    SessionContext.currentContext?.createRequestSession()

その後、セッションを使用してMobile SDKサービス呼び出しを行うことができます。 ユーザーがログインしているかどうかを最初に確認する必要がある場合は、 `SessionContext` プロパティ `isLoggedIn`使用できます。

すばらしいです\！ これで、アプリで既存のセッションを取得する方法がわかりました。しかし、ログインスクリーンレットを使用していない場合はどうでしょうか。 取得する既存のセッションはありません。 汗かいていない\！ `SessionContext` を使用して手動で作成できます。 次のセクションでは、これを行う方法を示します。

## セッションを手動で作成する

Login Screenletを使用しない場合、 `SessionContext` は取得するセッションがありません。 この場合、手動で作成する必要があります。 `SessionContext` method `loginWithBasic`これを行うことができます。 このメソッドは、ユーザー名、パスワード、およびユーザー属性をパラメーターとして受け取り、それらの資格情報でセッションを作成します。 次のコードは、 `loginWithBasic` を使用してセッションを作成します。

    Session session = SessionContext.loginWithBasic(username: USERNAME, password: PASSWORD, userAttributes: [:]);

`userAttributes` パラメーターの場合、 `userId`など、ログインしているユーザーに関連付けられたいくつかの属性を指定する必要があります。 属性の完全なリストについては、 [ユーザーモデルインターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/User.html)参照してください。

スーパー\！ これで、セッションを手動で作成する方法がわかりました。 次のセクションでは、自動ログインを実装し、セッションを保存または復元する方法を示します。

## 自動ログインの実装とセッションの保存または復元

ログインスクリーンレットは素晴らしいですが、ユーザーがアプリを開くたびに資格情報を入力する必要はありません。アプリで必要なログインは1回だけです。 あなたのアプリでこれを実現するために、参照 [この動画](https://www.youtube.com/watch?v=kEZEahTzuck)。

つまり、Login Screenletで `saveCredentials` から `true` を設定する必要があります。 次のログインでは、保存された資格情報が使用されます。 アプリの再起動時にこれが機能することを確認するには、 `SessionContext` メソッド `loadStoredCredentials`を使用して、保存された資格情報を取得する必要があります。 次のSwiftコードは、これの典型的な実装を示しています。

    if SessionContext.loadStoredCredentials() {
        // user auto-logged in
        // consider doing a relogin here (see next section)
    }
    else {
        // send user to login screen with the login screenlet
    }

驚くばかり\！ これで、Liferay Screensアプリに自動ログインを実装する方法がわかりました。 利用可能な `SessionContext` メソッドの詳細については、このチュートリアルの最後にある [メソッドセクション](#methods) を参照してください。 次に、ユーザーがログインしている間にサーバー上でユーザーの資格情報が変更された場合に、再ログインを実装する方法を学習します。

## 再ログインの実装

セッションは、ログインスクリーンレットまたは自動ログインのどちらで作成された場合でも、Liferayインスタンスのユーザーを確認する基本的なユーザーデータを含みます。 サーバーでデータが変更されると、セッションが古くなり、アプリの動作が不安定になる可能性があります。 また、ユーザーがサーバーで削除、無効化、またはその他の方法で資格情報を変更した場合、自動ログイン機能はサーバートランザクションを実行しないためアクセスを拒否しません。ローカルストレージから既存のセッションを取得するだけです。 これは最適な状況ではありません\！

このようなシナリオでは、再ログイン機能を使用できます。 この機能は、現在のセッションがまだ有効かどうかを判断するメソッドに実装されます。 セッションがまだ有効な場合、ユーザーのデータはサーバーからの最新のデータで更新されます。 セッションが有効でない場合、ユーザーはログアウトされ、再度ログインして新しいセッションを作成する必要があります。

この機能に対して、 `SessionContext.currentContext` メソッド `relogin`呼び出します。

    SessionContext.currentContext?.relogin(closure)

この操作はバックグラウンドスレッドで非同期に実行されることに注意してください。 `クロージャ` 引数は、最終的に新しいユーザー属性を受け取る関数です。 エラーの場合、クロージャは `nil` 属性で呼び出され、ユーザーはセッションからログアウトされます。 完全な再ログインの典型的なSwiftコードは次のとおりです。 末尾のクロージャーが使用されていることに注意してください。

    SessionContext.currentContext?.relogin { userAttributes in
        if userAttributes == nil {
            // couldn't retrieve the user attributes: user invalidated or password changed?
        }
        else {
            // full re-login made. Everything is updated
        }
    }

すばらしいです\！ これで、アプリに再ログインを実装する方法がわかりました。また、 `SessionContext` がどれほど便利かを見てきました。 さらに多くのことができます\！ 次のセクションでは、追加の `SessionContext` メソッドと、このチュートリアルで使用されるメソッドの詳細をリストします。

## 方法

デフォルトのサーバーと提供されたOAuth 2トークンを使用してLiferayセッションを作成します。 これは、Liferay Screens</a>OAuth 2とともに使用することを目的としています。</td> </tr> 

</tbody> </table> 



## 物性

| 物件                  | タイプ              | 説明                                                                             |
| ------------------- | ---------------- | ------------------------------------------------------------------------------ |
| `currentContext`    | `SessionContext` | ログインスクリーンレット、または `loginWithBasic` または `loginWithOAuth2` メソッドによって確立された現在のセッション。 |
| `isLoggedIn`        | `Bool`           | `SessionContext` にLiferayセッションが含まれる場合、 `true` 返します。                            |
| `basicAuthUsername` | `String`         | 現在のセッションを確立するために使用されるユーザー名（存在する場合）。                                            |
| `basicAuthPassword` | `String`         | 現在のセッションを確立するために使用されるパスワード（ある場合）。                                              |
| `userId`            | `数値`             | 現在のセッションを確立するために使用されるユーザー識別子（存在する場合）。                                          |


詳細については、GitHub</a>の `SessionContext` ソースコードを参照してください。</p> 



## 関連トピック

[iOSのログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[iOS用Liferay画面でのOAuth 2の使用](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-liferay-screens-for-ios)
