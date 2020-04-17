---
header-id: building-mobile-sdks
---

# モバイルSDKの構築

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Mobile SDKを使用すると、AndroidアプリとiOSアプリを@product@インスタンスに接続できます。 Liferayの事前に構築されたMobile SDKを介してビルトインポータルサービスにアクセスすることにより、アプリは@product@インスタンスのすぐに使用可能な機能にアクセスできます。 しかし、カスタムポートレットに属するカスタムサービスを呼び出す場合はどうでしょうか。 問題ない\！ この場合、これらのカスタムポートレットサービスを呼び出すことができる独自のMobile SDKを構築する必要があります。

ポートレットのためのモバイルSDKをビルドするとき、それが含まれていることに注意してください *のみ* そのポートレットのリモートサービスを呼び出すために必要なクラスを。 Liferayのビルド済みMobile SDKをアプリにインストールする必要があります。一般的にリモートサービス呼び出しを構築するために必要なフレームワークが含まれています。

Liferay Mobile SDKプロジェクトには、AndroidおよびiOSプラットフォーム用のカスタムMobile SDKを生成するMobile SDK Builderが含まれています。 Mobile SDK Builderは、ネイティブモバイルアプリがカスタムポートレットのリモートWebサービスを呼び出せるようにするクライアントライブラリを生成することでこれを行います。 Mobile SDK Builderは、クライアント側の [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services) 考えてください。

このチュートリアルでは、AndroidおよびiOS用のカスタムMobile SDKを構築する方法について説明します。 まず、お持ちのカスタムポートレット用にリモートサービスが構成されていることを確認します。

## ポートレットのリモートサービスの構成

Mobile SDK Builderがポートレットのリモートサービスを検出するには、サービスが利用可能であり、Webサービスデプロイメント記述子（WSDD）が必要です。 ポートレットのリモートサービスを作成し、WSDDを構築する手順については、 [ここをクリック](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)。

次に、Liferay Mobile SDKのソースコードをダウンロードします。

## Liferay Mobile SDKのダウンロード

カスタムポートレットのサービス用のMobile SDKを構築するには、ローカルマシンに [Liferay Mobile SDKのソースコード](https://github.com/liferay/liferay-mobile-sdk) が必要です。 このコードにはMobile SDK Builderも含まれています。 このコードを取得するには、Gitを介してMobile SDKプロジェクトを複製するか、GitHubからダウンロードします。 Gitを使用してMobile SDKプロジェクトのクローンを作成するには、ターミナルを開き、Mobile SDKを配置するマシンのディレクトリに移動します。 次に、次のコマンドを実行します。

    git clone git@github.com:liferay/liferay-mobile-sdk.git

Mobile SDKは頻繁に変更されるため、選択したモバイルプラットフォーム（AndroidまたはiOS）の最新の安定したリリースを確認する必要があります。 [利用可能な安定版リリースのリストを表示するには、ここをクリック](https://github.com/liferay/liferay-mobile-sdk/releases) してください。 安定リリースは、モバイルプラットフォームで始まり、LiferayモバイルSDKバージョンで終わるGitHubのタグに対応しています。 たとえば、 `android-7.0.6` タグは、Liferay Mobile SDK for Androidのバージョン7.0.6に対応しています。 同じ名前の新しいブランチでこのタグをチェックアウトするには、次のコマンドを使用できます。

    git checkout tags/android-7.0.6 -b android-7.0.6

または、GitHubの各タグの下にリストされているZIPまたはTAR.GZファイルをダウンロードできます。

これで、Mobile SDKをビルドする準備が整いました！

## Liferay Mobile SDKの構築

Mobile SDKのソースコードをダウンロードしたら、カスタムポートレットのMobile SDKをビルドするモジュールをビルドする必要があります。 Mobile SDK Builderには、このモジュールの構築に役立つコマンドラインウィザードが付属しています。 ウィザードを開始するには、Mobile SDKソースコードのルートフォルダーで次のコマンドを実行します。

    ./gradlew createModule

これにより、ポートレットのコードを生成するために必要な最も一般的に必要なプロパティでウィザードが起動します。 これらのプロパティをさらに制御する必要がある場合は、 `all` 引数を指定して同じコマンドを実行します。

    ./gradlew createModule -P=all

ウィザードはこのスクリーンショットのように見えるはずです。 デフォルト値は、青いテキストの付いた角括弧で囲まれていることに注意してください。

![図1：Mobile SDK Builderのウィザードでは、モジュールを構築するためのプロパティ値を指定できます。](../../../images/mobile-sdk-create-module.png)

それでは、どのプロパティが利用でき、何をするのでしょうか？ 素晴らしい質問\！ `createModule`実行中または実行後に、次のプロパティを設定できます。 `createModule`実行した後にこれらのプロパティを設定する必要がある場合は、モジュールの `gradle.properties` ファイルで設定できます。 括弧内の値は `gradle.properties`使用されるキーです：

  - `コンテキスト（コンテキスト）`：ポートレットのWebコンテキスト。 たとえば、一般に `カレンダー` コンテキストにデプロイされる@product@のカレンダーポートレット用のMobile SDKを生成する場合、コンテキスト値を `カレンダー`設定する必要があります。 指定されたコンテキストで利用可能なサービスがない場合、ポートレットのWSDDの生成を忘れている可能性があります。

  - `プラットフォーム（プラットフォーム）`：Mobile SDKを構築するプラットフォーム。 デフォルトでは、AndroidおよびiOSのコードを生成できます（`android、ios`）。

  - `サーバーURL（url）`：@product@インスタンスのURL。 サービスを検出するために、Mobile SDK Builderは指定されたコンテキストでこのインスタンスへの接続を試みます。

  - `フィルター（フィルター）`：Mobile SDKがアクセスできるポートレットエンティティを指定します。 空白の値は、すべてのポートレットエンティティサービスを指定します。 たとえば、Calendarポートレットのエンティティには、 `CalendarBooking` および `CalendarResource`ます。 `CalendarBooking` エンティティのみのMobile SDKを生成するには、フィルターの値を `calendarbooking` （すべて小文字）に設定します。

  - `モジュールバージョン（バージョン）`：Mobile SDKのJAR（Android）およびZIPファイル（iOS）に追加されるバージョン番号。 Mobile SDKのパッケージ化に関するセクションでこれについてさらに説明します。

  - `パッケージ名（packageName）`：Androidでは、これはMobile SDKのクラスが書き込まれるパッケージです（iOSはパッケージを使用しません）。 パッケージ名の末尾に@product@バージョンが追加されることに注意してください。 たとえば、Liferay Portal 7.0またはLiferay DXP Digital Enterprise 7.0を使用していて、パッケージ名として `com.liferay.mobile.android` を指定した場合、Mobile SDK Builderはパッケージ名に `v7` を追加して `comを生成します。 .liferay.mobile.android.v7` これにより、同じ名前のクラス間の衝突が回避され、同じアプリ内の複数のポータルバージョンでMobile SDKを使用できます。 `ポータルバージョン` プロパティを使用して、ポータルバージョンを変更できます。

  - `POM Description（description）`：POMファイルの説明。

`gradle.properties` ファイルでのみ設定できる `destination` プロパティもあります。 このプロパティは、生成されたソースファイルの宛先を指定します。 通常、これを変更する必要はありません。

必要なプロパティを設定すると、Mobile SDK Builderはフォルダー `modules /${your_portlet_context}`モジュールを生成します。

これで、Mobile SDKを構築できます。 これを行うには、モジュールに移動して次のコマンドを実行します。

    ../../gradlew generate

デフォルトでは、ビルダーはソースファイルを `android / src / gen / java` および `ios / Source` にモジュールのフォルダーに書き込みます。

サーバー側でポートレットのリモートサービスを更新し、Mobile SDKを更新する必要がある場合は、 `../../gradlew generate` 再度実行します。

驚くばかり\！ これで、カスタムポートレットのリモートサービス用のMobile SDKを作成および再生成する方法がわかりました。 次に、AndroidおよびiOS用のMobile SDKをパッケージ化することで終了します。

### Android用Mobile SDKのパッケージ化

Androidプロジェクトで使用するためにMobile SDKをJARファイルにパッケージ化するには、モジュールのフォルダーから次のコマンドを実行します。

    ../../gradlew jar

これにより、Mobile SDKが次のファイルにパッケージ化されます。

  - `modules/${your_portlet_context}/build/libs/liferay-${your_portlet_context}-android-sdk-${version}.jar`

ポートレットのリモートサービスを呼び出すには、最初にこのファイルをAndroidプロジェクトにインストールする必要があります。 そのためには、Androidアプリの `app / libs` フォルダーにファイルをコピーします。 Liferayのビルド済みMobile SDKもアプリにインストールする必要があることに注意してください。 [これを行う手順については、ここをクリック](/docs/7-1/tutorials/-/knowledge_base/t/making-liferay-and-custom-portlet-services-available-in-your-android-app#adding-the-sdk-to-your-gradle-project) してください。

また、Mobile SDKを再生成して新しい機能を含める場合、 `gradle.properties` ファイルでモジュールのバージョンを更新できることに注意してください。 たとえば、最初に構築したMobile SDKでサービスメソッドを追加または変更した場合、モジュールの `gradle.properties` ファイルで `version = 1.1` 設定して、そのバージョンを更新できます。

あなたのAndroidアプリでモバイルSDKを使用する方法を学ぶために、残りの参照 [のAndroidモバイルSDKのドキュメント](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)。 Mobile SDKを使用して、 [Liferayスクリーン](/docs/7-1/tutorials/-/knowledge_base/t/android-apps-with-liferay-screens)でカスタムスクリーンレットを作成することもできます。

### iOS用Mobile SDKのパッケージ化

iOSプロジェクトで使用するためにMobile SDKをZIPファイルにパッケージ化するには、モジュールのフォルダーから次のコマンドを実行します。

    ../../gradlew zip

これにより、Mobile SDKが次のファイルにパッケージ化されます。

  - `modules/${your_portlet_context}/build/liferay-${your_portlet_context}-ios-sdk-${version}.zip`

ポートレットのリモートサービスを呼び出すには、まずこのファイルをXcodeプロジェクトにインストールする必要があります。 これを行うには、単純に解凍し、そのファイルをXcodeプロジェクトに追加します。

iOSアプリでMobile SDKを使用する方法については、残りの [iOS Mobile SDKドキュメント](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)参照してください。 Mobile SDKを使用して、 [Liferayスクリーン](/docs/7-1/tutorials/-/knowledge_base/t/ios-apps-with-liferay-screens)でカスタムスクリーンレットを作成することもできます。

## 関連トピック

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)

[Liferayスクリーンを備えたAndroidアプリ](/docs/7-1/tutorials/-/knowledge_base/t/android-apps-with-liferay-screens)

[Liferayスクリーンを備えたiOSアプリ](/docs/7-1/tutorials/-/knowledge_base/t/ios-apps-with-liferay-screens)
