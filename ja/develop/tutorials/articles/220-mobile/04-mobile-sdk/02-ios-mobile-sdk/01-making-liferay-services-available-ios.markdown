---
header-id: making-liferay-and-custom-portlet-services-available-in-your-ios-app
---

# LiferayおよびカスタムポートレットサービスをiOSアプリで利用可能にする

[TOC levels=1-4]

あなたのiOSアプリは間違いなく素晴らしいものであるか、少なくとも素晴らしいスタートを切っています。 これで、Liferayサービスにアクセスできるようになります。 これをどのように達成しますか？ もちろん、LiferayのiOS Mobile SDKを使用してください！ iOSプロジェクトに正しいMobile SDKをインストールして、アプリで必要なリモートサービスを呼び出す必要があります。まず、Liferayのビルド済みMobile SDKをインストールする必要があります。 これは、Liferayを活用するアプリに必要です。 カスタムポートレットのサービスを呼び出すには、そのために作成したMobile SDKもインストールする必要があります。 カスタムポートレット用のMobile SDKを構築する手順については、チュートリアル [Mobile SDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)参照してください。

このチュートリアルでは、Liferayのビルド済みMobile SDKおよびカスタムビルドされたMobile SDKをインストールする方法を示します。 最初に、 [CocoaPods](https://cocoapods.org/) を使用してLiferayのビルド済みMobile SDKをインストールする方法を学びます。 次に、Mobile SDKを手動でインストールする方法を学習します。これは、カスタム構築されたMobile SDKをインストールするために必要です。 今すぐ出かけ、リモートサービスを恐れない！

## CocoaPodsを使用したSDKのインストール

Liferayのビルド済みMobile SDKをインストールする最も簡単な方法は、CocoaPodsを使用することです。 そのための手順は次のとおりです。

1.  あなたが持っていることを確認してください [CocoaPods](https://cocoapods.org/) インストールを。

2.  プロジェクトに `Podfile` というファイルを作成します。 このファイルに次の行を追加します。

    ``` 
     pod 'Liferay-iOS-SDK'
    ```

3.  プロジェクトのディレクトリから `pod install` 実行します。 これにより、Liferay iOS Mobile SDKの最新バージョンがダウンロードされ、 `.xcworkspace` ファイルが作成されます。 CocoaPodsは、必要なすべての依存関係もダウンロードし、ワークスペースを構成します。 `ポッドインストール`実行する前に、 `ポッドリポジトリアップデート` を実行する必要がある場合があることに注意してください。これにより、マシンにCocoaPodsリポジトリの最新バージョンがインストールされます。

4.  `.xcworkspace` ファイルを使用して、Xcodeでプロジェクトを開きます。

5.  フレームワークとして依存関係をインポートする場合（Podfileで`use_frameworks！` ）、 `LRMobileSDK` モジュールをインポートする必要があります。

    ``` 
     @import LRMobileSDK; // (Objective-C)
     import LRMobileSDK // (Swift)
    ```

CocoaPodsの機能の詳細については、それらの [ドキュメント](http://guides.cocoapods.org/using/index.html)参照してください。 次に、Mobile SDKを手動でインストールする方法を学びます。

## iOS SDKの手動インストール

Mobile SDKを手動でインストールすることもできます。 これは、カスタムポートレットのサービス用にビルドした場合に必要です。 CocoaPodsを使用したくない場合は、Liferayのビルド済みMobile SDKを手動でインストールすることもできます。

1.  Liferayの構築済みのモバイルSDK、最初にインストールするには、 [ダウンロード](https://github.com/liferay/liferay-mobile-sdk/releases) LiferayのiOSモバイルSDK ZIPファイルの最新バージョンを。 独自のMobile SDKを構築した場合は、マシン上でそのZIPファイルを見つけます。 これについては、モバイルSDK [構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks) チュートリアルで詳しく説明してい

 。</p></li> 
   
   2  Xcodeプロジェクトにファイルを解凍します。

3  Xcodeの中で、プロジェクトを右クリックしてクリック *「プロジェクト名」にファイルの追加を*。

4  `コア` および `v7` フォルダーを追加します。 Liferayバージョンごとに `v7` フォルダーの名前が変わる可能性があることに注意してください。 この例では、SDKはLiferay 7.0用に構築されています。

5  Liferayのビルド済みMobile SDKを手動でインストールする場合、 [AFNetworking 2.6.3](https://github.com/AFNetworking/AFNetworking/releases/tag/2.6.3)も必要です。 ソースコードをプロジェクトに追加します。</ol> 

すばらしいです\！ これで、iOSアプリにMobile SDKを手動でインストールする方法がわかりました。



## LiferayとiOSの互換性について

各Liferay Mobile SDKは、特定のLiferayバージョンで動作するように設計されています。 Liferay Mobile SDKのバージョン番号はこれを反映しています。 各Mobile SDKのバージョン番号の最初の2桁は、互換性のあるLiferayバージョンに対応しています。 たとえば、Mobile SDKバージョン `6.2。*` はLiferay 6.2と互換性があり、Mobile SDKバージョン `7.0。*` はLiferay 7.0と互換性があります。最初の2つ以降の数字は、内部Liferay Mobile SDKビルドに対応しています。

Mobile SDKのサービスクラス名には、Mobile SDKのバージョン番号の接尾辞も付いています。 これにより、アプリで複数のLiferayバージョンをサポートできます。 たとえば、Mobile SDKバージョン `6.2.0.22` および `7.0.3` を同じプロジェクトに追加できます。 Liferayバージョン6.2および7.0をサポートするMobile SDKサービスクラスは、それぞれ `_v62.m` および `_v7.m`で終わります。 アプリが接続するLiferayバージョンを確認するには、 `[LRPortalVersionUtil getPortalVersion：...]` メソッドを使用します。

Liferay iOS Mobile SDKは、iOSバージョン7.0以降と互換性があります。 古いiOSバージョンは動作する可能性がありますが、互換性はテストされていません。



## iOSアプリでカスタムポートレットサービスを利用可能にする

カスタムポートレットのリモートWebサービスを呼び出す場合は、iOS Mobile SDKを自分で構築してクライアントライブラリを生成する必要があります。 SDKの構築については、チュートリアル [モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)ます。 SDKをZIPファイルにビルドしたら、上記の手動インストール手順を使用してインストールできます（Liferayのビルド済みZIPファイルの代わりにビルドしたZIPファイルを使用してください）。 カスタム構築されたSDKが含まれているためという注意 *のみ* カスタムポートレットサービスを呼び出すためのクライアントライブラリを、あなたはLiferayの事前作成SDKと一緒にそれらをインストールする必要があります。 Liferayのビルド済みSDKには、リモートサービス呼び出しを構築するために必要な追加のクラスが含まれています。



## 関連トピック

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
