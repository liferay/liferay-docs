---
header-id: prepare-xamarin-projects-for-liferay-screens
---

# Liferay画面用のXamarinプロジェクトの準備

[TOC levels=1-4]

Liferay ScreensをXamarinで使用するには、XamarinプロジェクトにScreensをインストールする必要があります。 次に、@product@インスタンスと通信するようにプロジェクトを構成する必要があります。 XamarinのLiferay Screensは、 [NuGet.org](https://www.nuget.org/packages/LiferayScreens)ホストされる [NuGet](https://docs.microsoft.com/en-us/nuget/what-is-nuget) パッケージとしてリリースされることに注意してください。

| **注：** インストール後、Liferay画面を構成して通信する必要があります| @product@インスタンスで。 このチュートリアルの最後のセクションでは、次の方法を示します。これを行う。

## 要件とサンプルプロジェクト

XamarinのLiferay Screensには、Screensに含まれるすべてのScreenletを使用するために必要なバインディングが含まれています。 次のソフトウェアが必要です。

  - Visual Studio
  - Android SDK 4.1（APIレベル16）以上
  - [Liferay Portal CE 7.0 / 7.1、またはLiferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
  - [Liferay Screens NuGetパッケージ](https://www.nuget.org/packages/LiferayScreens)

また、XamarinのScreensを使用中に混乱またはスタックした場合、 [公式のLiferay Screensリポジトリ](https://github.com/liferay/liferay-screens) には、参照可能な2つのサンプルXamarinプロジェクトが含まれています。

  - [**Showcase-Android：**](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android) 現在利用可能なすべてのスクリーンレットを含むXamarin.Android用のサンプルアプリ。

  - [**Showcase-iOS：**](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS) 現在利用可能なすべてのスクリーンレットを含むXamarin.iOS用のサンプルアプリ。

## JSON Webサービスの保護

Liferay Screensの各スクリーンレットは、1つ以上の@product@のJSON Webサービスを呼び出します。これらはデフォルトで有効になっています。 [Android](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) および [iOS](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) のスクリーンレットリファレンスドキュメントには、各スクリーンレットが呼び出すWebサービスがリストされています。 スクリーンレットを使用するには、ポータルでそのWebサービスを有効にする必要があります。 ただし、使用していないスクリーンレットに必要なWebサービスを無効にすることは可能です。 この手順については、チュートリアル [JSON Webサービスの設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-json-web-services)参照してください。 [サービスアクセスポリシー](/docs/7-1/tutorials/-/knowledge_base/t/service-access-policies) を使用して、アクセス可能なサービスをより詳細に制御することもできます。

## LiferayスクリーンをXamarinソリューションにインストールする

次の手順に従って、Liferay ScreensをXamarinプロジェクトにインストールします。

1.  Visual Studioでプロジェクトを開きます。

2.  プロジェクトの *パッケージ* フォルダーを右クリックし、 *パッケージの追加...*選択します。

3.  *LiferayScreens* を探し、最新バージョンをインストールします。

4.  依存関係のライセンス契約に同意します。 これらは、XamarinでLiferay Screensを使用するために必要です。

5.  以下のいずれかの方法で構成を確認してください。

      - プロジェクトを再構築して実行し、Liferay Screensパス（例： `Com.Liferay.Mobile.Screens.Auth.Login`）をインポートします。
      - プロジェクトで、 *参照* → *パッケージ* を選択し、 `LiferayScreens *` ファイルを探します。 Assembly Browserでそのファイルを開きます。 その後、利用可能なLiferay Screensファイルがすべて表示されます。 <iframe width="560" height="315" src="https://www.youtube.com/embed/k59s0FYD9GY" frameborder="0" allowfullscreen mark="crwd-mark"></iframe>

次に、@product@との通信を設定します。

## @product@との通信の構成

Liferay Screensを使用する前に、@product@インスタンスと通信するようにプロジェクトを構成する必要があります。 これを行うには、プロジェクトに次の情報を提供する必要があります。

  - @product@インスタンスのID。
  - アプリが通信する必要がある@product@サイトのID。
  - @product@インスタンスのバージョン。
  - 特定のスクリーンレットに必要なその他の情報。 <iframe width="560" height="315" src="https://www.youtube.com/embed/kBojqzvtzDs" frameborder="0" allowfullscreen mark="crwd-mark"></iframe>

幸いなことに、これは簡単です。 Xamarinプロジェクトで以下を実行します。

  - Xamarin.Androidの場合、 `リソース/値` フォルダーに `server_context.xml` という新しいファイルを作成します。 このファイルに次のコードを追加します。
    
        <?xml version="1.0" encoding="utf-8"?>
        <resources>
        
            <!-- Change these values for your portal installation -->
            <string name="liferay_server">http://10.0.2.2:8080</string> <integer name="liferay_company_id">20116</integer> <integer name="liferay_group_id">20143</integer> <integer name="liferay_portal_version">71</integer> </resources>

  - Xamarin.iOSの場合、 `リソース` フォルダーに `liferay-server-context.plist` という新しいファイルを作成します。 このファイルに次のコードを追加します。
    
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
         <plist version="1.0">
        <dict>
            <key>server</key>
            <string>http://localhost:8080</string>
            <key>版</key>
            <integer>71</integer>
            <key>companyId</key>
            <real>20116</real>
            <key>groupId</key>
            <real>20143</real>
        </dict>
        </plist>

これらの値は、@product@インスタンスの値と一致するように変更してください。 サーバーアドレス `http://10.0.2.2:8080` は、エミュレータを介した `localhost：8080` に対応するため、Android Studioのエミュレータでのテストに適しています。 ただし、Genymotionエミュレータを使用している場合は、 `localhost`代わりに `192.168.56.1` 使用する必要があります。

`liferay_company_id` および `companyId` 値は、@product@インスタンスのIDです。 これは、@product@インスタンスの *コントロールパネル* → *構成* → *仮想インスタンス*ます。 インスタンスのIDは、 *インスタンスID* 列にあります。

`liferay_group_id` および `groupId` 値は、アプリが通信する必要があるサイトのIDです。 この値を見つけるには、まずアプリと通信する@product@インスタンスのサイトに移動します。 *Site Administration* メニューで、 *Configuration* → *Site Settings*選択します。 サイトIDは、 *General* タブの上部にリストされています。

`liferay_portal_version` および `バージョン` 値 `71` は、Liferay CE Portal 7.1またはLiferay DXP 7.1インスタンスと通信していることをScreensに伝えます。 サポートされている値と、それらが対応するポータルのバージョンは次のとおりです。

  - `71`：Liferay CEポータル7.1またはLiferay DXP 7.1
  - `70`：Liferay CE Portal 7.0またはLiferay DXP 7.0
  - `62`：Liferay Portal 6.2 CE / EE

`server_context.xml` および `liferay-server-context.plist`Screenletプロパティを構成することもできます。 例 `server_context.xml` 以下のプロパティ、 `liferay_recordset_id` および `liferay_recordset_fields`は、DDLフォームスクリーンレットおよびDDLリストスクリーンレットが@product@インスタンスのDDLと対話できるようにします。

    <!-- Change these values for your portal installation -->
    
    <integer name="liferay_recordset_id">20935</integer>
    <string name="liferay_recordset_fields">Title</string>

これらのファイルのその他の例については、 [Showcase-Android](https://github.com/liferay/liferay-screens/blob/develop/xamarin/Samples/Showcase-Android/Resources/values/server_context.xml) および [Showcase-iOS](https://github.com/liferay/liferay-screens/blob/develop/xamarin/Samples/Showcase-iOS/Resources/liferay-server-context.plist) サンプルプロジェクトを参照してください。

スーパー\！ XamarinプロジェクトはLiferay Screensの準備ができています。

## 関連トピック

[Xamarinアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-xamarin-apps)

[Xamarin.Androidでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-xamarin-android)

[Xamarin.iOSでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-xamarin-ios)

[Xamarinのビューとテーマを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-xamarin-views-and-themes)

[XamarinのLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-xamarin-troubleshooting-and-faqs)
