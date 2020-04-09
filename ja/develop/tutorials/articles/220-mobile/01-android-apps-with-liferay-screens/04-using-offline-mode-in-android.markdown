---
header-id: using-offline-mode-in-android
---

# Androidでオフラインモードを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensのオフラインモードでは、接続が利用できない場合や断続的にアプリが機能します。 技術が着実に進歩しているため、接続がより安定して普及していますが、インターネットには到達できない場所がまだたくさんあります。 大きな建物のある都市を含む複雑な地形の地域では、多くの場合、安定した接続がありません。 通常、遠隔地にはまったく接続がありません。 アプリでScreensのオフラインモードを使用すると、これらの状況に柔軟に対応できます。

このチュートリアルでは、スクリーンレットでオフラインモードを使用する方法を示します。 どのようにオフラインモードの動作の説明については、チュートリアルを参照 [Liferayの画面にオフラインモードのアーキテクチャを](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens)。 オフラインモードのアーキテクチャはiOSとAndroidで同じですが、これらのプラットフォームでの使用は異なります。

## オフラインモード用のスクリーンレットの構成

スクリーンレットのいずれかでオフラインモードを有効にする場合は、レイアウトにスクリーンレットのXMLを挿入するときに `offlinePolicy` 属性を構成する必要があります。 この属性は、次の4つの値を取ることができます。

  - `REMOTE_ONLY`
  - `CACHE_ONLY`
  - `REMOTE_FIRST`
  - `CACHE_FIRST`

これらの値の説明については、セクションを参照 [オフラインモードと使用ポリシー](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode) オフラインモードアーキテクチャチュートリアル。 オフラインモードでは、各スクリーンレットの動作が少し異なることに注意してください。 具体的な詳細については、 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android)参照してください。

## 同期の処理

一部のシナリオでは、ローカルキャッシュに格納されている値をポータルと同期する必要があります。 これを行うには、 `CacheSyncService` クラスを使用する必要があります。 このクラスは、ローカルキャッシュからポータルに情報を送信します。 `CacheSyncService` をアプリに登録するには、 `AndroidManifest.xml` ファイルに次のコードを追加する必要があります。

    <receiver android:name=".CacheReceiver">
        <intent-filter>
            <action android:name="com.liferay.mobile.screens.auth.login.success"/>
            <action android:name="com.liferay.mobile.screens.cache.resync"/>
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
        </intent-filter>
    </receiver>
    
    <service
        android:name=".CacheSyncService"
        android:exported="false"/>

このコードは、 `CacheReceiver` および `CacheSyncService` コンポーネントを登録します。 `CacheReceiver` は、次のシナリオで呼び出されます。

  - 接続の変更が発生したとき（たとえば、ネットワーク接続が復元されたとき）。
  - ログインスクリーンレットが正常にログインを完了すると。
  - 特定の `再同期` インテントがブロードキャストされる場合。 この場合、 `使用し<code> context.sendBroadcast（new Intent（ "com.liferay.mobile.screens.cache.resync"））;`

`CacheSyncService` は、上記の受信者から呼び出されたときに同期プロセスを実行します。 これは現在、支援されていないプロセスです。 将来のバージョンには、何らかの制御メカニズムが含まれる予定です。

## 関連トピック

[Liferay画面のオフラインモードのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[iOSでオフラインモードを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-offline-mode-in-ios)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)
