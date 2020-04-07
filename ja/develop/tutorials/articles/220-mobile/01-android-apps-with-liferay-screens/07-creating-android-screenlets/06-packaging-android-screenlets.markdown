---
header-id: packaging-your-screenlets
---

# スクリーンレットのパッケージ化

[TOC levels=1-4]

Screenletを別のアプリで再利用したり配布したりするには、モジュール（Androidライブラリ）にパッケージ化できます。 オプションで、jCenterまたはMaven Centralを介して他の開発者と共有できます。 開発者は、モジュールをプロジェクトの依存関係としてアプリに追加することにより、スクリーンレットを使用できます。 このチュートリアルでは、次の手順に従ってスクリーンレットをパッケージ化して配布する方法を説明します。

1.  新しいAndroidモジュールを作成します。
2.  各モジュール間の依存関係を構成します。
3.  モジュールをjCenterまたはMaven Centralにアップロードして配布します。

これで、プロのようにスクリーンレットをパッケージ化して配布する準備ができました！

## 新しいAndroidモジュールを作成する

Android Studioの *Create New Module* ウィザードは、モジュールを自動的に作成し、それを `settings.gradle` ファイルに追加できます。 *File* → *New Module ...*に移動し、More Modulesセクションで *Android Library* 選択して、 *Next*をクリックします。 次に、モジュールに名前を付けて *Next*をクリックします。 ウィザードの最終ステップでは、新しいアクティビティを追加できます。 モジュールには必要ないので、 *Blank Activity* を選択し、 *Finish*をクリックします。 Android Studioは、Androidライブラリ構成で新しい `build.gradle` ファイルを作成し、新しいモジュールを `settings.gradle` ファイルに追加します。

新しいモジュールを手動で作成する場合は、例として [マテリアルビューセット](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) または [Westerosアプリ](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle) から `build.gradle` ファイルを調べます。 モジュールを作成したら、 [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/settings.gradle)場所を指定してモジュールをインポートします。 構成例を次に示します。

    // Change YOUR_MODULE_NAME and RELATIVE_ROUTE_TO_YOUR_MODULE to match your module
    
    include ':YOUR_MODULE_NAME'
    project(':YOUR_MODULE_NAME').projectDir = new File(settingsDir, 'RELATIVE_ROUTE_TO_YOUR_MODULE')

モジュールができたので、その依存関係を構成する準備ができました。

## 各モジュール間の依存関係を構成する

次に、モジュールを使用するようにアプリを構成する必要があります。 そのためには、プロジェクト実装ステートメントを `build.gradle` ファイルの依存関係に追加します`</p>

<pre><code>// Change YOUR_MODULE_NAME to match your module's name

dependencies {
    ...
    implementation project (':YOUR_MODULE_NAME')
    ...
}
`</pre>

モジュールは、既存のスクリーンレットをオーバーライドして新しいスクリーンレットを作成するための依存関係も指定する必要があります。 これには通常、Liferayスクリーンとスクリーンレットが現在使用しているビューセットを `build.gradle` ファイルの依存関係に追加する必要があります。 Liferay Screensを依存関係として追加するには、 `build.gradle` ファイルの依存関係に次のプロジェクト実装ステートメントを追加します`</p>

<pre><code>implementation 'com.liferay.mobile:liferay-screens:+'
`</pre>

驚くばかり\！ これで、スクリーンレットを世界と共有する準備が整いました！

## モジュールをjCenterまたはMaven Centralにアップロードします

モジュールを誰でも利用できるようにするには、モジュールをjCenterまたはMaven Centralにアップロードできます。 そうする前に、それらのリポジトリに対して適切に `build.gradle` ファイルを構成する必要があります。 例として、マテリアルまたはWesterosビューセットの [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle) ファイルを使用します。 bintray apiキーを入力したら、 `gradlew bintrayupload` を実行してプロジェクトをjCenterにアップロードします。 開発者は、Gradleファイルでリポジトリ、アーティファクト、グループID、およびバージョンを指定することにより、他のAndroid依存関係としてScreenletを使用できます。 スクリーンレットの公開おめでとうございます！

## 関連トピック

[Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)
