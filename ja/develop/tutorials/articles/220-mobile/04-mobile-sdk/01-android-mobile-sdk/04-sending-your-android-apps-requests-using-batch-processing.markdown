---
header-id: sending-your-android-apps-requests-using-batch-processing
---

# バッチ処理を使用してAndroidアプリのリクエストを送信する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Mobile SDKでは、リクエストをバッチで送信することもできます。 これは、個別のリクエストを送信するよりもはるかに効率的です。 たとえば、サイトのブログポートレット内の10個のブログエントリを同時に削除するとします。 削除ごとに要求を行う代わりに、呼び出しのバッチを作成して、それらをまとめて送信できます。

このチュートリアルでは、Androidアプリにバッチ処理を実装する方法を示します。 AndroidアプリからLiferayサービスを呼び出す方法を既に知っていることを前提としています。そうしない場合は、チュートリアルを参照 [Androidアプリでの呼び出しのLiferayサービスを](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)。 さあ、サービスコールの新鮮なバッチを準備する準備をしてください！

## バッチ処理の実装

サービスコールをバッチで作成するには、一度に1つずつ行うのに2つの追加手順が必要です。

  - `BatchSessionImpl`バッチセッションを作成します。
  - `BatchSessionImpl``invoke` メソッドでバッチサービス呼び出しを行い` 。</li>
</ul>

<p spaces-before="0">残りの手順は、他のサービス呼び出しを行う場合と同じです。 それでもサービスオブジェクトが必要であり、そのサービスメソッドを呼び出す必要があります。 例として、ブログポートレットのブログエントリをバッチで同期的に削除するアプリのコードスニペットを次に示します。</p>

<pre><code>import com.liferay.mobile.android.service.BatchSessionImpl;

BatchSessionImpl batch = new BatchSessionImpl(session);
BlogsEntryService service = new BlogsEntryService(batch);

service.deleteEntry(1);
service.deleteEntry(2);
service.deleteEntry(3);

JSONArray jsonArray = batch.invoke();
`</pre>

ここで何が起こっているのでしょうか？ インポート後、既存のセッションで `BatchSessionImpl` を使用してバッチセッションを作成します。 `BatchSessionImpl` コンストラクターは、資格情報またはセッションのいずれかを取ることに注意してください。 あなたが既に持っているとき、コンストラクタにセッションを渡すと便利です `セッション` のオブジェクトを、同じ資格情報を再利用したいです。 サービスオブジェクトを作成した後、いくつかの `deleteEntry` サービスコールが作成されます。 サービスオブジェクトはバッチセッションで作成されるため、これらの呼び出しはすぐには行われません。代わりに `null` を返します。 呼び出しは、バッチセッションオブジェクトで `invoke（）` メソッドを呼び出してバッチで発行されるまで行われません。 各サービス呼び出しの結果を含む `JSONArray` を返します。 この例には3つの `deleteEntry` 呼び出しが含まれているため、 `jsonArray` は3つのオブジェクトが含まれています。 結果は、サービス呼び出しと同じ順序になります。

すばらしいです\！ しかし、バッチ呼び出しを非同期で行いたい場合はどうでしょうか？ 問題ない\！ コールバックを `BatchAsyncTaskCallback` インスタンスとして設定します。
    
        import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;
        
        batch.setCallback(new BatchAsyncTaskCallback() {
        
            public void onFailure(Exception exception) {
            }
        
            public void onSuccess(JSONArray results) {
                // The result is always a JSONArray 
            }
        
        });

これは、チュートリアルで説明したように、非同期呼び出しを行うための手順と同様である [Androidアプリから呼び出すサービス非同期](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-android-app)。 驚くばかり\！ これで、バッチで効率的なサービス呼び出しを行う方法がわかりました！

## 関連トピック

[AndroidアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)

[Androidアプリからのサービスの非同期呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-android-app)

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)
