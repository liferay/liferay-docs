---
header-id: liferay-screens-for-android-troubleshooting-and-faqs
---

# Android向けLiferay画面のトラブルシューティングとFAQ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayはAndroid用のScreensを細心の注意を払って開発しましたが、いくつかの一般的な問題に直面する可能性があります。 これらの問題を解決するためのソリューションとヒントを次に示します。 また、Android用スクリーンに関する一般的な質問への回答もあります。

## 一般的なトラブルシューティング

特定の問題を掘り下げる前に、まず最新のツールがインストールされていることを確認し、必要な場合に追加のヘルプが必要な場所を知る必要があります。 Android Studioの最新バージョンでは、最新のAndroid APIレベルを使用する必要があります。 画面が *缶* のEclipse ADTまたは手動のGradleと仕事のビルド、Androidのメーカーは、好適なIDEです。

Liferay Screensの使用に問題がある場合は、Liferayが開発したサンプルアプリの調査に役立つ場合があります。 どちらもスクリーンレットとビューの使用方法の良い例として役立ちます。

  - [ウェステロス銀行](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros)
  - [テストアプリ](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app)


<!--
When updating an app or Screenlet to a new version of Liferay Screens, make sure 
to see the 
[Android breaking changes reference article](/docs/7-0/reference/-/knowledge_base/r/android-breaking-changes). 
This article lists changes to Screens that break functionality in prior 
versions. In most cases, updating your code is relatively straightforward. 
-->

いつでも行き詰まったら、フォーラム [質問を投稿できます](https://www.liferay.com/community/forums/-/message_boards/category/42706063)。 喜んでお手伝いします！ バグを見つけた場合や改善を提案したい場合は、 [Jira](https://issues.liferay.com/browse/LMW/)チケットを提出してください。 注あなたがしなければならないことを [でログ](https://issues.liferay.com/login.jsp?os_destination=%2Fbrowse%2F) 最初のプロジェクトを見ることができるようにします。

## 一般的な問題

このセクションには、Liferay画面の使用時に発生する可能性のある一般的な問題に関する情報が含まれています。

1.  `com.liferay.mobile:liferay-screensが見つかりませんでした`

    このエラーは、GradleがLiferay画面またはリポジトリを見つけることができない場合に発生します。 最初に、使用しようとしているScreensのバージョン番号がjCenterに存在することを確認します。 この [リンク](https://bintray.com/liferay/liferay-mobile/liferay-screens/view) を使用して、アップロードされたすべてのバージョンを表示できます。

    デフォルトのリポジトリとしてjCenterを使用しない古いGradleプラグインを使用している可能性もあります。 Screensはバージョン1.2.3以降を使用します。 プロジェクトの `build.gradle` ファイルに次のコードを配置することにより、jCenterを新しいリポジトリとして追加できます。
   
        repositories {
            jcenter()
        }

2.  解決に失敗しました： `com.android.support:appcompat-v7`

    Liferay Screensは、最新のAndroid Studioで作成されたすべての新しいAndroidプロジェクトと同様に、Googleのappcompatライブラリを使用します。 appcompatライブラリはGoogleが管理するカスタムリポジトリを使用するため、Android SDK Managerを使用して手動で更新する必要があります。

    Android SDKマネージャー（Android Studioの *ツール* → *Android* → *SDKマネージャー* にあります）では、少なくともバージョン14のAndroidサポートリポジトリー（ *Extras* メニュー）とバージョン22.1をインストールする必要があります。 Androidサポートライブラリの1。

3.  `APK META-INF`コピーされた重複ファイル...

    これは、ライブラリを使用する場合の一般的なAndroidエラーです。 これは、Gradleがライセンスファイルや通知ファイルなどの重複ファイルをマージできないために発生します。 このエラーを防ぐには、次のコードを `build.gradle` ファイルに追加します。
   
        android {
            ...
            packagingOptions {
                exclude 'META-INF/LICENSE'
                exclude 'META-INF/NOTICE'
            }
            ...
        }

    このエラーはすぐには発生しませんが、開発プロセスの後半でのみ表示される場合があります。 このため、プロジェクトを作成した後、 `build.gradle` ファイルに上記のコードを配置することをお勧めします。

4.  接続失敗：ECONNREFUSED（接続拒否）、または `org.apache.http.conn.HttpHostConnectException`

    このエラーは、Liferay Screensおよび基盤となるLiferay Mobile SDKがLiferay Portalインスタンスに接続できない場合に発生します。 このエラーが発生した場合は、まずサーバーのIPアドレスをチェックして、使用可能であることを確認する必要があります。 `server_context.xml`</a>のデフォルトIPアドレスを


上書きした場合、正しいIPに設定したことを確認する必要があります。 また、Genymotionエミュレーターを使用している場合、ローカルLiferayインスタンスと通信するには、アプリがlocalhostの代わりに `192.168.56.1` 使用する必要があります。</p></li> 
   
   5  `java.io.IOException`：開くことに失敗しました：EACCES（許可が拒否されました）
  
  一部のスクリーンレットでは、ユーザーポートレートスクリーンレットが新しいポートレートをアップロードしたときや、DDLフォームスクリーンレットがポータルに新しいファイルをアップロードしたときなど、一時ファイルを使用して情報を保存します。 アプリには、特定のScreenletの機能を使用するために必要な権限が必要です。 この場合、次の行を `AndroidManifest.xml`に追加します：
  
       <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
      
  
  デバイスのカメラを使用している場合は、次の権限も追加する必要があります。
  
       <uses-permission android:name="android.permission.CAMERA"/>
      

6  パスを持つJSON Webサービスアクションはありません...
  
  このエラーは、 [Liferay Screens Compatibility Plugin](https://github.com/liferay/liferay-screens/tree/master/portal)インストールしていない場合に最もよく発生します。 このプラグインは、Liferayポータルに新しいAPI呼び出しを追加します。</ol> 



## よくある質問

1.  Android Studioを使用する必要がありますか？
   
   いいえ、Liferay ScreensはEclipse ADTでも動作します。 Gradleまたは別のビルドシステムを使用して、プロジェクトを手動でコンパイルすることもできます。 プロジェクトの `lib` フォルダーでコンパイル済みの `aar` を使用していることを確認してください。
   
   我々 *強く* あなたは、Android Studioを使用すること、しかし、お勧めします。 Android StudioはAndroidアプリを開発するための公式IDEであり、Eclipse ADTはサポートされなくなりました。 Eclipse ADTを使用するか、手動でコンパイルすると、克服が困難な予期しない問題が発生する場合があります。

2.  画面は方向の変更をどのように処理しますか？
   
   Liferay Screensは、イベントバス（ [は `EventBus` ライブラリ](http://greenrobot.github.io/EventBus/)）を使用して、インタラクターが作業を終了したときにアクティビティを通知します。

3.  スクリーンでは使用できないLiferay機能を使用するにはどうすればよいですか？
   
   Screensで現在利用できないLiferay機能を使用する方法はいくつかあります。 [Liferay Mobile SDK](https://github.com/liferay/liferay-mobile-sdk) を使用すると、LiferayのすべてのリモートAPIにアクセスできます。 また、カスタムScreenletを作成して、デフォルトでScreensに含まれていない機能をサポートすることもできます。

4.  新しいスクリーンレットを作成するにはどうすればよいですか？
   
   Screenletの作成について詳細に説明する [ここ](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)。

5.  スクリーンレットをカスタマイズするにはどうすればよいですか？
   
   新しいビューを作成して、スクリーンレットをカスタマイズできます。 幸いなことに、これには [チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views) があります！

6.  Screensにはオフラインサポートがありますか？
   
   はい、Liferay Screens 1.3以降です！



## 関連トピック

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)

[Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)

[Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)

[モバイルSDK](/docs/7-1/tutorials/-/knowledge_base/t/mobile-sdk)

<!--
[Android Breaking Changes](/docs/7-0/reference/-/knowledge_base/r/android-breaking-changes)
-->
