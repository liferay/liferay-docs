---
header-id: using-web-screenlet-with-cordova-in-your-android-app
---

# AndroidアプリでCordovaでWebスクリーンレットを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[Web Screenlet](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-android-app)で [Cordovaプラグイン](https://cordova.apache.org/plugins/) を使用することにより、ScreenletがレンダリングするWebページの機能を拡張できます。 これにより、アプリのニーズに合わせてそのページを調整できます。

Cordovaをインストールして構成することから始めます。 これを行うには、自動または手動の2つの方法があります。 最初に自動方式について説明します。

## Cordovaの自動インストールと構成

次の手順に従って、Cordovaを使用するように構成された空のAndroidプロジェクトを自動的に作成します。 あなたが持っている必要があることに注意 [gitの](https://git-scm.com/)、 [のNode.js、およびNPM](https://nodejs.org/en/) インストールを。

1.  `screens-cli`をインストールします：
   
        npm install -g screens-cli

2.  プロジェクトを作成するフォルダーにファイル `.plugins.screens` を作成します。 このファイルに、アプリで使用するすべてのCordovaプラグインを追加します。たとえば、CordovaまたはGitHubからプラグインを追加できます。
   
        https://github.com/apache/cordova-plugin-wkwebview-engine.git
        cordova-plugin-call-number
        cordova-plugin-camera

3.  `.plugins.screens` ファイルを含むフォルダーで、 `screens-cli` を実行してプロジェクトを作成します。
   
        screens-cli android <project-name>

    これにより、フォルダー `platforms / android /<project-name>`プロジェクトが作成されます。 Android Studioで開くことができます。

## Cordovaの手動インストールと構成

Cordovaを手動でインストールおよび構成するには、次の手順を実行します。

1.  フォロー [コルドバは、スタートガイド](https://cordova.apache.org/#getstarted) コルドバプロジェクトを作成し、コルドバをインストールすると、お使いのコルドバプロジェクトにAndroidプラットフォームを追加します。

2.  アプリで使用するCordovaプラグインをインストールします。たとえば、次のコマンドはCordovaプラグインに [追加します `cordova-plugin-call-number`](https://www.npmjs.com/package/cordova-plugin-call-number)：
   
        cordova plugin add cordova-plugin-call-number

    `cordova plugin` を使用して、現在インストールされているプラグインを表示できます。

3.  次のファイルとフォルダーをCordovaプロジェクトからAndroidプロジェクトのルートフォルダーにコピーします。

      - `/platforms/android/res/xml/config.xml`
      - `/platforms/android/assets/www`

    `AndroidManifest.xml`、リソースファイルなどの他のファイルも確認する必要があります。 一部のプラグインは、これらのプラグインがAndroidアプリで正しく機能するためにコピーする必要があるファイルに権限またはスタイルを追加します。

## Web ScreenletでCordovaを使用する

AndroidプロジェクトにCordovaをインストールして構成したので、Web ScreenletでCordovaを使用する準備ができました。 次の手順に従ってください。

1.  [アプリにWebスクリーンレットを挿入して設定します](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-android-app)。

2.  `WebScreenletConfiguration.Builder` オブジェクトを介してWeb Screenletのパラメーターを設定する場合、 `CordovaLifeCycleObserver` 引数を指定して `enableCordova` メソッドを呼び出してCordovaを有効にする必要があります。 `CordovaLifeCycleObserver` は、アクティビティのライフサイクルについてCordovaに通知します。 引数なしのコンストラクターを使用して、このオブザーバーのインスタンスを作成できます。

    たとえば、このコードは `CordovaLifeCycleObserver` オブジェクトを作成し、Web Screenletのパラメーターを設定するときに `enableCordova` とともに使用します。
   
        CordovaLifeCycleObserver observer = new CordovaLifeCycleObserver();
       
        WebScreenletConfiguration configuration =
                new WebScreenletConfiguration
                        .Builder("/")
                        .addLocalJs("call.js")
                        .enableCordova(observer)
                        .load();
       
        webScreenlet.setWebScreenletConfiguration(configuration);
        webScreenlet.load();

3.  次の `アクティビティ` メソッドをオーバーライドして、対応するオブザーバーメソッドを呼び出します。
   
        @Override
        protected void onStart() {
            super.onStart();
            observer.onStart();
        }
       
        @Override
        protected void onStop() {
            super.onStop();
       
            observer.onStop();
        }
       
        @Override
        public void onPause() {
            super.onPause();
       
            observer.onPause();
        }
       
        @Override
        public void onResume() {
            super.onResume();
       
            observer.onResume();
        }
       
        @Override
        public void onDestroy() {
            super.onDestroy();
       
            observer.onDestroy();
        }
       
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
       
            observer.onSaveInstanceState(outState);
        }
       
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
       
            observer.onActivityResult(requestCode, resultCode, data);
        }
       
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, 
            @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       
                observer.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
       
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
       
            observer.onConfigurationChanged(newConfig);
        }

それでおしまい\！ ただし、実行内容によっては、JavaScriptファイルからCordovaを呼び出す必要がある場合もあります。 たとえば、 [を使用してCordovaプラグイン `cordova-plugin-call-number`](https://www.npmjs.com/package/cordova-plugin-call-number) を呼び出して番号を呼び出すには、次のコードでJavaScriptファイルを追加する必要があります。

    function callNumber() {
        //This line triggers the Cordova plugin and makes a call
        window.plugins.CallNumber.callNumber(null, function(){ alert("Calling failed.") }, "900000000", true);
    }
    
    setTimeout(callNumber, 3000);

このコードを含むアプリを実行して3秒待機すると、プラグインがアクティブになり、JavaScriptファイルの番号を呼び出します。

すばらしいです\！ これで、CordovaでWeb Screenletを使用する方法がわかりました。

## 関連トピック

[AndroidアプリでのWebページのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-pages-in-your-android-app)

[Android用Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-android)
