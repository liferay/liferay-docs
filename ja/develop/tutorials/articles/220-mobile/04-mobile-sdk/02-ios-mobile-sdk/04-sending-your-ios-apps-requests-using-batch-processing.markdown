---
header-id: sending-your-ios-apps-requests-using-batch-processing
---

# バッチ処理を使用してiOSアプリのリクエストを送信する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Mobile SDKでは、リクエストをバッチで送信することもできます。 これは、個別のリクエストを送信するよりもはるかに効率的です。 たとえば、サイトのブログポートレット内の10個のブログエントリを同時に削除するとします。 削除ごとに要求を行う代わりに、呼び出しのバッチを作成して、それらをまとめて送信できます。

このチュートリアルでは、iOSアプリにバッチ処理を実装する方法を示します。 iOSアプリからLiferayサービスを呼び出す方法を既に知っていることを前提としています。そうしない場合は、チュートリアルを参照 [あなたのiOS Appでの呼び出しのLiferayサービス](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)。 Objective-Cは、次のコードスニペットで使用されます。 さあ、新しいリクエストをまとめましょう！

## バッチ処理の実装

サービスコールをバッチで作成するには、一度に1つずつ行うのに2つの追加手順が必要です。

  - `LRBatchSession`バッチセッションを作成します。
  - `LRBatchSession``invoke` メソッドでバッチサービス呼び出しを行い` 。</li>
</ul>

<p spaces-before="0">残りの手順は、他のサービス呼び出しを行う場合と同じです。 それでもサービスオブジェクトが必要であり、そのサービスメソッドを呼び出す必要があります。 例として、ブログポートレットのブログエントリをバッチで同期的に削除するアプリのコードスニペットを次に示します。</p>

<pre><code>#import "LRBatchSession.h"

LRBatchSession *batch = [[LRBatchSession alloc] 
    initWithServer:@"http://localhost:8080" username:@"test@example.com" password:@"test"];
LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] initWithSession:batch];
NSError *error;

[service deleteEntryWithEntryId:1 error:&error];
[service deleteEntryWithEntryId:2 error:&error];
[service deleteEntryWithEntryId:3 error:&error];

NSArray *entries = [batch invoke:&error];
`</pre>

ここで何が起こっているのでしょうか？ インポート後、 `LRBatchSession` がLiferayインスタンスのURLとユーザーの資格情報とともに使用され、バッチセッションが作成されます。 または、既存の `セッション` をコンストラクターに渡すこともできます。 これは、セッションオブジェクトが既にあり、同じ資格情報を再利用する場合に便利です。 次に、通常どおりサービス呼び出しが行われます（この場合、 `deleteEntryWithEntryId`）。 非同期呼び出しでは、これらのメソッドはすぐに `nil` 返し` 。 最後に、 <code>[バッチ呼び出し：&エラー]`呼び出します。 これは、各サービス呼び出しの結果を含む `NSArray` を返します（バッチ呼び出しの戻り値の型は常に `NSArray`）。 3つの `deleteEntryWithEntryId` 呼び出しがあるため、 `エントリ` 配列には3つのオブジェクトが含まれます。 結果の順序は、サービス呼び出しの順序と一致します。

非同期でバッチ呼び出しを行う場合は、通常どおりセッションにコールバックを設定します。
    
        [batch setCallback:callback];

すばらしいです\！ これで、バッチ処理を利用してアプリのリクエストを高速化する方法がわかりました。

## 関連トピック

[iOSアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
