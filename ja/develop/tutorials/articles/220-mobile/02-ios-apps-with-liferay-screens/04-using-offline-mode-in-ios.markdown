---
header-id: using-offline-mode-in-ios
---

# iOSでオフラインモードを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensのオフラインモードでは、接続が利用できない場合や断続的にアプリが機能します。 技術が着実に進歩しているため、接続がより安定して普及していますが、インターネットには到達できない場所がまだたくさんあります。 大きな建物のある都市を含む複雑な地形の地域では、多くの場合、安定した接続がありません。 通常、遠隔地にはまったく接続がありません。 アプリでScreensのオフラインモードを使用すると、これらの状況に柔軟に対応できます。

このチュートリアルでは、スクリーンレットでオフラインモードを使用する方法を示します。 どのようにオフラインモードの動作の説明については、チュートリアルを参照 [Liferayの画面にオフラインモードのアーキテクチャを](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens)。 オフラインモードのアーキテクチャはiOSとAndroidで同じですが、これらのプラットフォームでの使用は異なります。

## オフラインモード用のスクリーンレットの構成

スクリーンレットのいずれかでオフラインモードを有効にする場合は、属性 `offlinePolicy`構成する必要があります。 この属性は、4つの可能な値を取ることができます。 これらの値の説明については、セクションを参照 [オフラインモードと使用ポリシー](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode) オフラインモードアーキテクチャチュートリアル。 オフラインモードでは、各スクリーンレットの動作が少し異なることに注意してください。 具体的な詳細については、 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)参照してください。

## 同期の処理

一部のシナリオでは、ローカルキャッシュに格納されている値をポータルと同期する必要があります。 そのためには、 `SyncManager` クラスを使用する必要があります。 このクラスは、ポータルにまだ送信されていないローカルキャッシュに格納されている情報を送信します。

次の手順を使用して、同期プロセスを開始します。

1.  `SyncManager` クラスのインスタンスを作成します。 コンストラクターで `CacheManager` オブジェクトを渡す必要があります。 `SessionContext.currentCacheManager`を使用して、現在のキャッシュマネージャーを取得できます。

2.  デリゲートプロパティを設定します。 このデリゲートオブジェクトは、同期プロセスで生成されたイベントを受け取ります。 デリゲートのメソッドの詳細については、以下を参照してください [のAPIリファレンスドキュメント `SyncManagerDelegate` クラス](/docs/7-1/reference/-/knowledge_base/r/syncmanagerdelegate)。

3.  プロセスの実行中は、 `SyncManager` オブジェクトへの強い参照を保持してください。

## 関連トピック

[Liferay画面のオフラインモードのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[Androidでオフラインモードを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-offline-mode-in-android)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
