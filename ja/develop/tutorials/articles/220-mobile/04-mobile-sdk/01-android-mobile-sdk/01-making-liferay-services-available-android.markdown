---
header-id: making-liferay-and-custom-portlet-services-available-in-your-android-app
---

# LiferayおよびカスタムポートレットサービスをAndroidアプリで利用可能にする

[TOC levels=1-4]

アプリで必要なリモートサービスを呼び出すには、Androidプロジェクトに正しいMobile SDKをインストールする必要があります。まず、Liferayのビルド済みMobile SDKをインストールする必要があります。 これは、Liferayを活用するアプリに必要です。 カスタムポートレットのサービスを呼び出すには、そのために作成したMobile SDKもインストールする必要があります。 カスタムポートレット用のMobile SDKを構築する手順については、チュートリアル [Mobile SDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)参照してください。

このチュートリアルでは、Liferayのビルド済みMobile SDKおよびカスタムビルドされたMobile SDKをインストールする方法を示します。 最初に、GradleまたはMavenを使用してLiferayのビルド済みMobile SDKをインストールする方法を学びます。 次に、Mobile SDKを手動でインストールする方法を学習します。これは、カスタム構築されたMobile SDKをインストールするために必要です。 今すぐ出かけ、リモートサービスを恐れない！

## GradleプロジェクトへのSDKの追加

AndroidプロジェクトがビルドシステムとしてGradleを使用している場合、Liferayのビルド済みMobile SDKをプロジェクトに [依存](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies) として追加できます。 すべてのバージョンは、JCenterおよびMaven Centralリポジトリで入手できます。 両方のリポジトリがここにリストされていますが、必要なのはアプリに1つだけです：

    repositories {
      jcenter()
      mavenCentral()
    }
    
    dependencies {
      compile group: 'com.liferay.mobile', name: 'liferay-android-sdk', version: '7.1.+'
    }

Gradleを使用してビルドするときにAPK META-INF / NOTICE</code> コピーされた `重複ファイルなどのエラーが発生した場合、これを <code>build.gradle` ファイルに追加します。

    android {
        ...
        packagingOptions {
            exclude 'META-INF/LICENSE'
            exclude 'META-INF/NOTICE'
        }
        ...
    }

これで完了です。 プロジェクトがGradleファイルと同期すると、Liferayのビルド済みMobile SDKがプロジェクトにダウンロードされます。 Mavenでこれを行うための手順を次に示します。

## SDKをMavenプロジェクトに追加する

Mavenを使用して、Liferayのビルド済みMobile SDKをプロジェクトに [依存](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies) として追加することもできます。 そのためには、次のコードを `pom.xml` ファイルに追加します。

    <dependency>
        <groupId>com.liferay.mobile</groupId>
        <artifactId>liferay-android-sdk</artifactId>
        <version>LATEST</version>
    </dependency>

驚くばかり\！ ただし、GradleまたはMavenを使用していない場合はどうなりますか？ カスタムビルドのモバイルSDKをインストールする場合はどうなりますか？ 問題ない\！ 次のセクションでは、Mobile SDKを手動でインストールする方法を示します。

## AndroidプロジェクトにSDKを手動で追加する

AndroidプロジェクトでMobile SDKを手動で設定するには、次の手順を使用します。

1.  Liferayの構築済みのモバイルSDK、最初にインストールするには、 [ダウンロード](https://github.com/liferay/liferay-mobile-sdk/releases/) の最新バージョン `のLiferay-アンドロイド-sdk-[version]の.jar`。 独自のMobile SDKを作成した場合は、マシン上でそのJARファイルを見つけます。 これについては、モバイルSDK [構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks) チュートリアルで詳しく説明してい

 。</p></li> 
   
   2  JARをAndroidプロジェクトの `/ libs` フォルダーにコピーします。

3  Liferayのビルド済みMobile SDKを手動でインストールする場合は、これらの依存関係をダウンロードして、Androidプロジェクトの `/ libs` フォルダーにコピーする必要もあります。
  
        - [`httpclient-android-4.3.3.jar`](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpclient-android/4.3.3/httpclient-android-4.3.3.jar)
      - [`httpmime-4.3.3.jar`](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpmime/4.3.3/httpmime-4.3.3.jar)
4  それを使い始めてください！</ol> 

すばらしいです\！ これで、AndroidアプリにMobile SDKを手動でインストールする方法がわかりました。



## Androidアプリでカスタムポートレットサービスを利用可能にする

カスタムポートレットのリモートWebサービスを呼び出す場合は、Android Mobile SDKを自分で構築してそのクライアントライブラリを生成する必要があります。 SDKの構築については、チュートリアル [モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)ます。 SDKをJARファイルにビルドしたら、上記の手動インストール手順を使用してインストールできます（Liferayのビルド済みJARファイルの代わりにビルドしたJARファイルを使用してください）。 カスタム構築されたSDKが含まれているためという注意 *のみ* カスタムポートレットサービスを呼び出すためのクライアントライブラリを、あなたはLiferayの事前作成SDKと一緒にそれらをインストールする必要があります。 Liferayのビルド済みSDKには、リモートサービス呼び出しを構築するために必要な追加のクラスが含まれています。

スーパー\！ 必要なリモートサービスがアプリで利用できるようになったので、それらを呼び出す準備ができました。



## 関連トピック

[AndroidアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)
