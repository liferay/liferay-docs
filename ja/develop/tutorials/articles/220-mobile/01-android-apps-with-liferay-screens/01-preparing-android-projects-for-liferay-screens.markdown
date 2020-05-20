---
header-id: preparing-android-projects-for-liferay-screens
---

# Liferay画面用のAndroidプロジェクトの準備

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensを使用するには、Androidプロジェクトにインストールし、@product@インスタンスと通信するように設定する必要があります。 ScreensはjCenterでホストされる [AAR](http://tools.android.com/tech-docs/new-build-system/aar-format) ファイルとしてリリースされることに注意してください。

Screensをインストールするには、3つの異なる方法があります。 このチュートリアルでは、それぞれを示します。

1.  Gradleの場合：Gradleは、Android StudioがAndroidプロジェクトをビルドするために使用するビルドシステムです。 したがって、Screensのインストールに使用することをお勧めします。
2.  Mavenを使用
3.  手動で

| **注：** インストール後、Liferay画面を構成して通信する必要があります| @product@インスタンスで。 このチュートリアルの最後のセクションでは、次の方法を示します。これを行う。

## 必要条件

Liferay Screens for Androidには、コンポーネントライブラリ（スクリーンレット）とサンプルプロジェクトが含まれています。 次のソフトウェアが必要です。

  - Android Studio 3.0以降。
  - Android SDK 4.1（APIレベル16）以上。
  - [Liferay Portal 6.2 CE / EE、Liferay CE Portal 7.0 / 7.1、またはLiferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
  - Liferay Screens Compatibility Plugin（ポータルエディションに応じて[CE](http://www.liferay.com/marketplace/-/mp/application/54365664) または [DXP / EE](http://www.liferay.com/marketplace/-/mp/application/54369726)）。 このアプリは、Liferay CE Portal 7.0 / 7.1 CEおよびLiferay DXPにプリインストールされています。
  - Liferay Screensのソースコード。

Liferay Screens for Androidは、内部で [EventBus](https://github.com/greenrobot/EventBus) 使用し

 。</p> 



## JSON Webサービスの保護

Liferay Screensの各スクリーンレットは、1つ以上の@product@のJSON Webサービスを呼び出します。これらはデフォルトで有効になっています。 Screenlet [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) は、各Screenletが呼び出すWebサービスがリストされています。 スクリーンレットを使用するには、ポータルでそのWebサービスを有効にする必要があります。 ただし、使用していないスクリーンレットに必要なWebサービスを無効にすることは可能です。 この手順については、チュートリアル [JSON Webサービスの設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-json-web-services)参照してください。 [サービスアクセスポリシー](/docs/7-1/tutorials/-/knowledge_base/t/service-access-policies) を使用して、アクセス可能なサービスをより詳細に制御することもできます。



## Gradleを使用してLiferayスクリーンをインストールする

Gradleを使用してAndroid StudioプロジェクトにLiferay Screensをインストールするには、アプリの `build.gradle` ファイルを編集する必要があります。 プロジェクトには2つの `build.gradle` ファイルがあることに注意してください。1つはプロジェクト用で、もう1つはアプリモジュール用です。 これらは、Android StudioプロジェクトのGradleスクリプトの下にあります。 このスクリーンショットは、アプリモジュールの `build.gradle` ファイルを強調しています：

![図1：アプリモジュールの <code>build.gradle</code> ファイル。](../../../images/screens-android-gradle-app-module.png)

appモジュールの `build.gradle` ファイルで、 `依存関係` 要素内に次のコード行を追加します。

    implementation 'com.liferay.mobile:liferay-screens:+'
    

`+` 記号は、Gradleに最新バージョンのScreensをインストールするよう指示することに注意してください。 アプリが特定のバージョンのスクリーンに依存している場合、 `+` 記号をそのバージョンに置き換えることができます。

上記の行を追加する場所がわからない場合は、次のスクリーンショットを参照してください。

あなたの編集いったん `build.gradle`、メッセージはあなたに尋ねたファイルの最上部に表示される *同期* そのGradleのファイルを使用してアプリケーションを。 Gradleファイルの同期には、ファイルに加えた変更が組み込まれます。 同期すると、追加したLiferay Screens依存関係など、新しい依存関係もダウンロードおよびインストールされます。 メッセージの *Sync Now* リンクをクリックして、Gradleファイルを今すぐ同期します。 次のスクリーンショットは、編集された `build.gradle` ファイルの上部を示しており、[今すぐ同期]リンクが赤いボックスで強調表示されています。

![図2：アプリモジュールの <code>build.gradle</code> ファイルを編集した後、[今すぐ同期]をクリックして、アプリに変更を反映します。](../../../images/screens-android-gradle-sync.png)

競合の場合は `APPCOMPAT-V7` またはその他のサポートライブラリ（`com.android.support:appcompat-v7`、 `com.android.support:support-v4`）、あなたはいくつかのオプションがあります。

  - 使用する競合ライブラリのバージョンを明示的に追加します。 たとえば、
    
        implementation 'com.android.support:design:27.0.2'
        implementation 'com.android.support:support-media-compat:27.0.2'
        implementation 'com.android.support:exifinterface:27.0.2'
        

  - プロジェクトから `com.android.support:appcompat-v7` 依存関係を削除し、Liferay Screensに埋め込まれた依存関係を使用します。

  - Liferay Screensから問題のあるライブラリを除外します。 たとえば、
    
        implementation ('com.liferay.mobile:liferay-screens:+') {
            exclude group: 'com.android.support:', module: 'design'
        }
        

  - 次のようなコメントを追加して、検査を無視します。
    
        //noinspection GradleCompatible
        

  - 警告を無視-Liferay Screensは問題なく動作します。

Gradleを使用してScreensをインストールすることを強くお勧めしますが、次のセクションではMavenでScreensをインストールする方法を示します。



## Mavenを使用してLiferay画面をインストールする

Gradleを使用してScreensをインストールすることを強くお勧めします。 ただし、Mavenを使用してScreensをインストールすることは可能です。 MavenプロジェクトでLiferay画面を構成するには、次の手順に従います。

1.  `pom.xml`次の依存関係を追加します`
<pre><code>     <dependency>
         <groupId>com.liferay.mobile</groupId>
         <artifactId>liferay-screens</artifactId>
         <version>LATEST</version>
     </dependency>
`</pre>

2.  Mavenの更新を強制して、すべての依存関係をダウンロードします。

Mavenがアーティファクトを自動的に見つけられない場合、jCenterをMaven設定（たとえば、 `.m2 / settings.xml` ファイル）に新しいリポジトリとして追加する必要があります。

    <profiles>
        <profile>
            <repositories>
                <repository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray-plugins</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </pluginRepository>
            </pluginRepositories>
            <id>bintray</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>bintray</activeProfile>
    </activeProfiles>
    

よくやった\！



## Gradleでの手動構成

Gradleを使用してScreensを自動的にインストールすることを強くお勧めしますが、Gradleを使用してScreensを手動でインストールすることもできます。 次の手順に従って、Gradleを使用してAndroidプロジェクトにScreensとその依存関係を手動でインストールします。

1.  [Android向けLiferay Screensの最新バージョンをダウンロード](https://github.com/liferay/liferay-screens/releases)。

2.  `Android / library` のコンテンツをプロジェクト外のフォルダーにコピーします。

3.  プロジェクトで、ライブラリフォルダーへのパスを使用して `settings.gradle` ファイルを構成します。 
   
   

    ``` 
     include ':core'
     project(':core').projectDir = new File(settingsDir, '../../library/core')
     project(':core').name = 'liferay-screens'
    ```


4.  `build.gradle` ファイルに必要な依存関係を含めます。 
   
   

    ``` 
     implementation project(':liferay-screens')
    ```


`.aar` バイナリファイル（ `Android / dist`）をローカル `.aar` ファイル依存関係として構成することもできます。 [jCenter](https://bintray.com/liferay/liferay-mobile/liferay-screens/view)から必要なファイルをすべてダウンロードできます。

構成を確認するには、空のアクティビティをコンパイルして実行し、Liferay Screensクラス（Login Screenletなど）をインポートします。

次に、@product@との通信を設定します。



## @product@との通信の構成 <iframe width="560" height="315" src="https://www.youtube.com/embed/QEJPEicgle8" frameborder="0" allowfullscreen mark="crwd-mark"></iframe> 

Liferay Screensを使用する前に、@product@インスタンスと通信するように設定する必要があります。 これを行うには、スクリーンに次の情報を提供する必要があります。

  - @product@インスタンスのID

  - アプリが通信する必要があるサイトのID

  - @product@インスタンスのバージョン

  - 特定のスクリーンレットに必要なその他の情報

幸いなことに、これは簡単です。 Androidプロジェクトの `res / values` フォルダーに、 `server_context.xml`という新しいファイルを作成します。 新しいファイルに次のコードを追加します。

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
    
        <!-- Change these values for your @product@ installation -->
    
        <string name="liferay_server">http://10.0.2.2:8080</string>
    
        <string name="liferay_company_id">10155</string>
        <string name="liferay_group_id">10182</string>
    
        <integer name="liferay_portal_version">71</integer>
    
    </resources>
    

上記のコメントが示すように、@product@インスタンスに一致するようにこれらの値を変更してください。 サーバーアドレス `http://10.0.2.2:8080` は、エミュレータを介した `localhost：8080` に対応するため、Android Studioのエミュレータでのテストに適しています。 ただし、Genymotionエミュレータを使用している場合は、 `localhost`代わりにアドレス `192.168.56.1` 使用する必要があります。

`liferay_company_id` 値は、@product@インスタンスのIDです。 @product@インスタンスの *コントロールパネル* → *構成* → *仮想インスタンス*ます。 インスタンスのIDは、 *インスタンスID* 列にあります。 この値をコピーして、 `server_context.xml``liferay_company_id` 値に貼り付けます。

`liferay_group_id` 値は、アプリが通信する必要があるサイトのIDです。 この値を見つけるには、まずアプリと通信する@product@インスタンスのサイトに移動します。 *Site Administration* メニューで、 *Configuration* → *Site Settings*選択します。 サイトIDは、 *General* タブの上部にリストされています。 この値をコピーして、 `server_context.xml``liferay_group_id` 値に貼り付けます。

`liferay_portal_version` 値 `71` は、Liferay CE Portal 7.1またはLiferay DXP 7.1インスタンスと通信していることをScreensに伝えます。 サポートされている `liferay_portal_version` 値と、それらに対応するポータルバージョンは次のとおりです。

  - `71`：Liferay CEポータル7.1またはLiferay DXP 7.1
  - `70`：Liferay CE Portal 7.0またはLiferay DXP 7.0
  - `62`：Liferay Portal 6.2 CE / EE

`server_context.xml` ファイルでScreenletプロパティを構成することもできます。 以下にリストされているプロパティ例、 `liferay_recordset_id` および `liferay_recordset_fields`は、DDLフォームスクリーンレットおよびDDLリストスクリーンレットが@product@インスタンスのDDLと対話できるようにします。 追加の例 `server_context.xml` file [here](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/src/main/res/values/server_context.xml)を見ることができます。

    <!-- Change these values for your @product@ installation -->
    
    <string name="liferay_recordset_id">20935</string>
    <string name="liferay_recordset_fields">Title</string>
    

スーパー\！ Liferay ScreensのAndroidプロジェクトの準備が整いました。



## サンプルアプリ

Screensを使用してアプリを開発するときに、それを使用するアプリの例を参照することもできます。 次の2つのデモアプリケーションを使用できます。

  - [test-app](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app)：現在利用可能なすべてのスクリーンレットを含むショーケースアプリ。
  - [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros)：スクリーンレットを使用して、 *Westeros Bank*技術的な問題を管理するサンプルアプリ。 [Google Play](https://play.google.com/store/apps/details?id=com.liferay.mobile.screens.bankofwesteros)でも利用可能です。

すばらしいです\！ これで、スクリーンを使用する準備ができました。 次のチュートリアルは、これを行う方法を示しています。



## 関連トピック

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[Androidスクリーンレットでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets)

[Liferayスクリーン用のiOSプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens)
