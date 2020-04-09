---
header-id: preparing-ios-projects-for-liferay-screens
---

# Liferayスクリーン用のiOSプロジェクトの準備

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensを使用してiOSアプリを開発するには、まずiOSプロジェクトでScreensをインストールして構成する必要があります。 Screensは、標準の [CocoaPods](https://cocoapods.org) 依存関係としてリリースされます。 したがって、CocoaPodsを介してScreensをインストールする必要があります。 インストールの完了後、@product@インスタンスと通信するようにiOSプロジェクトを構成する必要があります。 このチュートリアルでは、これらのプロセスを順を追って説明します。 あなたはすぐに稼働します！

最初に、Liferayスクリーンの要件を確認します。

## 必要条件

iOS用Liferay Screensには、コンポーネントライブラリ（スクリーンレット）と、Swiftで記述されたサンプルプロジェクトが含まれています。 Screensは、Swiftと、機能的なSwiftコードと [Model View Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) アーキテクチャを活用する開発手法を使用して開発されています。 ScreensでSwiftまたはObjective-Cを使用でき、iOS 9以降でScreensアプリを実行できます。

iOS用Liferay Screensには、次のソフトウェアが必要です。

  - Xcode 9.3以降
  - iOS 11 SDK
  - [CocoaPods](http://cocoapods.org) 1以降
  - [Liferay Portal 6.2 CE / EE、Liferay CE Portal 7.0 / 7.1、またはLiferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
  - Liferay Screens Compatibility Plugin（ポータルエディションに応じて[CE](http://www.liferay.com/marketplace/-/mp/application/54365664) または [DXP / EE](http://www.liferay.com/marketplace/-/mp/application/54369726)）。 このアプリは、Liferay CE Portal 7.0 / 7.1 CEおよびLiferay DXPにプリインストールされています。

## JSON Webサービスの保護

Liferay Screensの各スクリーンレットは、1つ以上の@product@のJSON Webサービスを呼び出します。これらはデフォルトで有効になっています。 Screenlet [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) は、各Screenletが呼び出すWebサービスがリストされています。 スクリーンレットを使用するには、ポータルでそのWebサービスを有効にする必要があります。 ただし、使用していないスクリーンレットに必要なWebサービスを無効にすることは可能です。 この手順については、チュートリアル [JSON Webサービスの設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-json-web-services)参照してください。 [サービスアクセスポリシー](/docs/7-1/tutorials/-/knowledge_base/t/service-access-policies) を使用して、アクセス可能なサービスをより詳細に制御することもできます。

## CocoaPodsを使用したプロジェクトの構成

CocoaPodsを使用して、Liferayスクリーン用のiOS 9.0（またはそれ以上）プロジェクトを準備するには、次の手順を実行します。

1.  プロジェクトのルートフォルダーで、 `Podfile`という名前のファイルに次のコードを追加するか、このファイルが存在しない場合は作成します。 置き換えてください `ターゲット` ターゲットの名前で：
   
        source 'https://github.com/CocoaPods/Specs.git'
       
        platform :ios, '9.0'
        use_frameworks!
       
        target "Your Target" do
            pod 'LiferayScreens'
        end
       
        # the rest of your podfile

    Liferay Screensとその依存関係の一部は、Swift 3.2またはSwift 4.0と互換性がないことに注意してください。 iOSプロジェクトがSwift 3.2またはSwift 4.0でコンパイルされている場合、 `Podfile` はSwift 4.2でのコンパイルのためにScreensおよびそれらの依存関係を指定する必要があります。次の `Podfile` の `post_install` コードがこれを行います。 したがって、Swift 3.2またはSwift 4.0アプリで画面を使用する場合は、この `Podfile` 使用する必要があります。

    ``` 
    source 'https://github.com/CocoaPods/Specs.git'

    platform :ios, '9.0'
    use_frameworks!

    target "Your Target" do
        pod 'LiferayScreens'
    end

    post_install do |installer|
      incompatiblePods = [
        'Cosmos',
        'CryptoSwift',
        'KeychainAccess',
        'Liferay-iOS-SDK',
        'Liferay-OAuth',
        'LiferayScreens',
        'Kingfisher'
      ]

      installer.pods_project.targets.each do |target|
        if incompatiblePods.include? target.name
          target.build_configurations.each do |config|
            config.build_settings['SWIFT_VERSION'] = '4.2'
          end
        end
        target.build_configurations.each do |config|
            config.build_settings['CONFIGURATION_BUILD_DIR'] = '$PODS_CONFIGURATION_BUILD_DIR'
        end
      end
    end

    # the rest of your podfile 
    ```

2.  ターミナルで、プロジェクトのルートフォルダーに移動し、次のコマンドを実行します。
   
        pod repo update

    これにより、最新バージョンのCocoaPodsリポジトリがマシン上にあることが保証されます。 このコマンドの実行には時間がかかることに注意してください。

3.  ターミナルのプロジェクトのルートフォルダーで、次のコマンドを実行します。
   
        pod install

    これが完了したら、Xcodeを終了し、プロジェクトのルートフォルダーにある `* .xcworkspace` ファイルを使用してプロジェクトを再度開きます。 今後、このファイルを使用してプロジェクトを開く必要があります。

すばらしいです\！ プロジェクトと@product@との通信を構成するには、次のセクションをスキップして、最後のセクションの指示に従ってください。

## @product@との通信の構成

Screenletsと@product@間の通信の構成は簡単です。 Liferay Screensは、プロパティリスト（`.plist`）ファイルを使用して@product@インスタンスにアクセスします。 サーバーのURL、ポータルの会社ID、およびサイトのグループIDを含める必要があります。 `liferay-server-context.plist` ファイルを作成し、@product@インスタンスとの通信に必要な値を指定します。 例として、 [`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist)参照してください。

![図1： <code>liferay-context.plist</code>と呼ばれるプロパティリストファイルです。](../../../images/screens-ios-liferay-context.png)

`liferay-server-context.plist` 指定する必要がある値は次のとおりです。

  - `サーバー`：@product@インスタンスのURL。
  - `バージョン`：@product@インスタンスのバージョン。 サポートされる値は、Liferay CE Portal 7.1またはLiferay DXP 7.1の場合は `71` 、Liferay CE Portal 7.0またはLiferay DXP 7.0の場合は `70` 、Liferay Portal 6.2 CE / EEの場合は `62` です。
  - `companyId`：@product@インスタンスの識別子。 この値は、 *コントロールパネル* → *ポータルインスタンス*の *インスタンスID* 列にあります。
  - `groupId`：Screensが通信するデフォルトのサイトのID。 この値は、サイトの *サイト管理* → *構成* → *サイト設定* メニューのサイトIDフィールドにあります。
  - `connectorFactoryClassName`：コネクタのファクトリクラス名。 これはオプションです。 含めない場合は、バージョン</code> 値 使用して、@product@のそのバージョンに最も適したファクトリを決定します。</li>
</ul>

<p spaces-before="0">すばらしいです\！ iOSプロジェクトでLiferay Screensの準備ができました。</p>

<h2 spaces-before="0">関連トピック</h2>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps">iOSアプリでのスクリーンレットの使用</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets">iOSスクリーンレットでテーマを使用する</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens">Liferay画面用のAndroidプロジェクトの準備</a></p>
