---
header-id: using-web-screenlet-with-cordova-in-your-ios-app
---

# iOSアプリでCordovaでWebスクリーンレットを使用する

[TOC levels=1-4]

[Web Screenlet](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-ios-app)で [Cordovaプラグイン](https://cordova.apache.org/plugins/) を使用することにより、ScreenletがレンダリングするWebページの機能を拡張できます。 これにより、アプリのニーズに合わせてそのページを調整できます。 Cordovaをインストールすることから始めます。

## Cordovaの自動インストールと構成

次の手順に従って、Cordovaを使用するように構成された空のAndroidプロジェクトを自動的に作成します。 あなたが持っている必要があることに注意 [gitの](https://git-scm.com/)、 [のNode.jsとNPM](https://nodejs.org/en/)、および [CocoaPods](https://cocoapods.org/) インストールされています。

1.  `screens-cli`をインストールします：
   
        npm install -g screens-cli

2.  プロジェクトを作成するフォルダーにファイル `.plugins.screens` を作成します。 このファイルに、アプリで使用するすべてのCordovaプラグインを追加します。たとえば、CordovaまたはGitHubからプラグインを追加できます。
   
        https://github.com/apache/cordova-plugin-wkwebview-engine.git
        cordova-plugin-call-number
        cordova-plugin-camera

    `WKWebView` EngineプラグインはiOSでは必須です。

3.  `.plugins.screens` ファイルを含むフォルダーで、 `screens-cli` を実行してプロジェクトを作成します。
   
        screens-cli ios <project-name>

    これにより、フォルダー `platforms / ios /<project-name>`プロジェクトが作成されます。

4.  `platforms / ios /<project-name>`次を実行します。
   
        pod install

5.  Xcodeで `<project-name>.xcworkspace` ファイルを開きます。

## Cordovaの手動インストールと構成

Cordovaをインストールおよび構成するには、次の手順に従います。

1.  フォロー [コルドバは、スタートガイド](https://cordova.apache.org/#getstarted) コルドバプロジェクトを作成し、コルドバをインストールすると、お使いのコルドバプロジェクトへのiOSプラットフォームを追加します。

2.  [Cordova `WKWebView` エンジン](https://www.npmjs.com/package/cordova-plugin-ionic-wkwebview-engine)をインストールします。
   
        cordova plugin add cordova-plugin-wkwebview-engine

3.  アプリで使用する他のCordovaプラグインをインストールします。 `cordova plugin` を使用して、現在インストールされているプラグインを表示できます。

4.  次のファイルとフォルダーをCordovaプロジェクトからiOSプロジェクトのルートフォルダーにコピーします。

      - `platforms/ios/<your-cordova-project>/config.xml`
      - `platforms/ios/<your-cordova-project>/Plugins`
      - `platforms/ios/www`

5.  iOSプロジェクトのルートフォルダーにコピーした `config.xml` ファイルで、 `<access origin="*" />`下に `<allow-navigationhref="*" />` 追加します。

## Web ScreenletでCordovaを使用する

iOSプロジェクトにCordovaをインストールして構成したので、Web ScreenletでCordovaを使用する準備ができました。 次の手順に従ってください。

1.  [アプリにWebスクリーンレットを挿入して設定します](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-ios-app)。

2.  `WebScreenletConfigurationBuilder` オブジェクトを介してWeb Screenletのパラメーターを設定する場合、 `enableCordova（）` メソッドを呼び出します。 たとえば、次のコードは `addJs` を介してローカルJavaScriptファイルを追加し、構成とスクリーンレットを読み込む前に `enableCordova（）` 呼び出します。
   
        let configuration = WebScreenletConfigurationBuilder(url: "url")
            .addJs(localFile: "call")
            .enableCordova()
            .load()
       
        webScreenlet?.configuration = configuration
        webScreenlet?.load();

それでおしまい\！ ただし、実行内容によっては、JavaScriptファイルからCordovaを呼び出す必要がある場合もあります。 たとえば、 [を使用してCordovaプラグイン `cordova-plugin-call-number`](https://www.npmjs.com/package/cordova-plugin-call-number) を呼び出して番号を呼び出すには、次のコードでJavaScriptファイルを追加する必要があります。

    function callNumber() {
        //This line triggers the Cordova plugin and makes a call
        window.plugins.CallNumber.callNumber(null, function(){ alert("Calling failed.") }, "900000000", true);
    }
    
    setTimeout(callNumber, 3000);

このコードを含むアプリを実行して3秒待機すると、プラグインがアクティブになり、JavaScriptファイルの番号を呼び出します。

すばらしいです\！ これで、CordovaでWeb Screenletを使用する方法がわかりました。

## 関連トピック

[iOSアプリでのWebページのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-ios-app)

[iOS用Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-ios)
