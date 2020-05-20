---
header-id: invoking-services-asynchronously-from-your-android-app
---

# Androidアプリからのサービスの非同期呼び出し

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Androidでは、メインUIスレッドからの同期HTTPリクエストを許可していません。 Androidの [`AsyncTask`](http://developer.android.com/reference/android/os/AsyncTask.html) を使用して、メインUIスレッド以外のスレッドから同期リクエストを作成できます。 `AsyncTask`を使用したくない場合は、Mobile SDKを介して非同期リクエストを作成できます。 そのためには、コールバッククラスを実装およびインスタンス化し、それをセッションに設定する必要があります。 Mobile SDKがそのセッションのサービス呼び出しを行うと、非同期で呼び出しが行われます。 再度同期呼び出しを行うには、セッションのコールバックとして `null` を設定します。

このチュートリアルでは、次の手順を使用して、Androidアプリに非同期リクエストを実装する方法を示します。

1.  コールバッククラスを実装およびインスタンス化します。
2.  セッションにコールバックを設定します。
3.  Liferayサービスを呼び出します。

さあ、始めましょう\！

## コールバッククラスの実装とインスタンス化

コールバッククラスを実装およびインスタンス化する前に、必要なインポートを追加する必要があります。 追加するインポートは、呼び出しているサービスメソッドの戻り値のタイプによって異なります。 たとえば、サービスメソッド `getGroupEntries` を呼び出してサイトのブログポートレットからブログエントリを取得する必要がある場合、Mobile SDKの `AsyncTaskCallback` および `JSONArrayAsyncTaskCallback`をインポートする必要があります。

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;

これは、 `getGroupEntries` が `JSONArray`返すためです。 複数の `AsyncTaskCallback` 実装があり、メソッド戻りタイプごとに1つあります。

  - `JSONObjectAsyncTaskCallback`
  - `JSONArrayAsyncTackCallback`
  - `StringAsyncTaskCallback`
  - `BooleanAsyncTaskCallback`
  - `IntegerAsyncTaskCallback`
  - `LongAsyncTaskCallback`
  - `DoubleAsyncTaskCallback`

`GenericAsyncTaskCallback`と呼ばれる一般的な `AsyncTaskCallback` 実装を使用することもできます。 そのためには、変換メソッドを実装し、JSON解析を自分で処理する必要があります。

それでもこれらのコールバックを使用したくない場合は、 `AsyncTaskCallback` 直接実装できます。 ただし、その際には注意が必要です。 `onPostExecute（JSONArray jsonArray）` メソッドにパラメーターとして渡される `JSONArray` の最初の要素を常に取得する必要があります（たとえば、 `jsonArray.get（0）`）。

次に、コールバッククラスを実装してインスタンス化します。 コールバッククラスを実装する場合、 `onFailure` および `onSuccess` メソッドを実装する必要があります。 これらのメソッドは、リクエストが失敗または成功したときにアプリが何をするかを尊重します。 要求中に例外が発生した場合、 `onFailure（）` メソッドが呼び出されます。 これは、接続例外（要求タイムアウトなど）または `ServerException`によってトリガーされる可能性があります。 `ServerException` が発生した場合、サーバー側で何かがうまくいかなかったことが原因です。 たとえば、存在しない `groupId` を渡すと、Liferayインスタンスはそれについて文句を言い、Mobile SDKはエラーメッセージを `ServerException`でラップします。

`onSuccess` メソッドは、リクエストが終了した後、メインUIスレッドで呼び出されます。 要求は非同期であるため、サービス呼び出しはすぐに `null` オブジェクトを返します。 サービスは、代わりにサービスの実際の戻り値をコールバックの `onSuccess（）` メソッドに渡します。

`AsyncTaskCallback` および `JSONArrayAsyncTaskCallback`サンプルコードを以下に示します。

    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {
    
        public void onFailure(Exception exception) {
            // Implement exception handling code
        }
    
        public void onSuccess(JSONArray result) {
            // Called after request has finished successfully
        }
    
    };

コールバッククラスができたので、セッションに設定できます。

## セッションへのコールバックを設定する

コールバッククラスを実装してインスタンス化したら、セッションに設定する準備が整います。 セッションをまだ作成していない場合は、ここで作成してください。 チュートリアル [AndroidアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app) 、セッションの作成方法を示します。 これで、セッションへのコールバックを設定する準備が整いました。 たとえば、これは `AsyncTaskCallback`に対してここで実行されます。

    session.setCallback(callback);

ものすごく単純\！ これで、サービスコールを行う準備ができました。

## サービスコール

最後になりますが、間違いなく、サービスコールを行ってください。 これは、他のサービスを呼び出すのと同じように行われます。セッションからサービスオブジェクトを作成し、それを使用してサービスを呼び出します。 これは、チュートリアル [AndroidアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)でも説明されています。 サイトのブログポートレットからすべてのブログエントリを取得するサービス呼び出しの例を次に示します。

    service.getGroupEntries(10184, 0, 0, -1, -1);

上記のセクションのサンプルコードを以下に示します。

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;
    
    ...
    
    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {
    
        public void onFailure(Exception exception) {
            // Implement exception handling code
        }
    
        public void onSuccess(JSONArray result) {
            // Called after request has finished successfully
        }
    
    };
    
    // create a session first
    session.setCallback(callback);
    
    // create a service object first
    service.getGroupEntries(10184, 0, 0, -1, -1);

すばらしいです\！ これで、Androidアプリからサービスを非同期的に呼び出す方法がわかりました。

## 関連トピック

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)
