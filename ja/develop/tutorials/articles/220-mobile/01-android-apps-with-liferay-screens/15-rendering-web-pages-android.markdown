---
header-id: rendering-web-pages-in-your-android-app
---

# AndroidアプリでのWebページのレンダリング

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[レンダリングWebコンテンツ](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-content-in-your-android-app) チュートリアルでは、Androidアプリの@product@サイトから [Webコンテンツ](/docs/7-1/user/-/knowledge_base/u/introduction-web-content) を表示する方法を示します。コンテンツの表示は素晴らしいですが、ページ全体を表示したい場合はどうでしょうか？ 問題ない\！ [Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-android) では、任意のWebページを表示できます。 ローカルまたはリモートのJavaScriptファイルとCSSファイルを挿入して、ページをカスタマイズすることもできます。 @product@のサーバー側カスタマイズ機能（例： [アプリケーション表示テンプレート](/docs/7-1/user/-/knowledge_base/u/styling-apps-and-assets)）と組み合わせると、Web ScreenletはAndroidアプリでWebページを表示するためのほぼ無限の可能性を提供します。

このチュートリアルでは、Web Screenletを使用して、AndroidアプリでWebページを表示する方法を学びます。

## アプリにWebスクリーンレットを挿入する

アプリにWebスクリーンレットを挿入することは、アプリにスクリーンレットを挿入する [と同じです](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)：

1.  スクリーンレットを使用するアクティビティまたはフラグメントのレイアウトにスクリーンレットのXMLを挿入します。 また、必要な属性を必ず設定してください。 ウェブScreenletの使用可能な属性のリストについては、 [属性セクション](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-android#attributes) のWeb Screenlet参照ドキュメントのを。

    たとえば、スクリーンレットの `layoutId` および `autoLoad` 属性がそれぞれ `web_default` および `false`に設定されたWebスクリーンレットのXMLは次のとおりです。

    ``` 
    <com.liferay.mobile.screens.web.WebScreenlet
        android:id="@+id/web_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutId="@layout/web_default"
        app:autoLoad="false"
        /> 
    ```

    `web_default` は、デフォルトのビューセットの一部であるスクリーンレットのデフォルトビューを指定することに注意してください。

2.  デフォルトビューなど、ビューセットの一部であるビューを使用するには、アプリまたはアクティビティテーマが、ビューセットのスタイルを設定するテーマを継承する必要があります。 デフォルトビューセットの場合、これは `default_theme`です。 たとえば、 `default_theme`を継承するようにアプリのテーマを設定するには、 `res / values / styles.xml` を開き、ベースアプリテーマの親を `default_theme`ます。 この例では、ベースアプリのテーマは `AppTheme`です。
   
        <style name="AppTheme" parent="default_theme">
            ...

次に、Web Screenletのリスナーを実装します。

## Web Screenletのリスナーの実装

アクティビティまたはフラグメントでScreenletを使用するには、そのアクティビティまたはフラグメントのクラスにScreenletのリスナーも実装する必要があります。 Web Screenletのリスナーは `WebListener`です。 `WebListener`を実装するには、次の手順に従います。

1.  クラス宣言を変更して `WebListener`を実装し、 `com.liferay.mobile.screens.web.WebListener`をインポートします。
   
        ...
        import com.liferay.mobile.screens.web.WebListener;
       ...
       
        パブリッククラスYourActivityは、AppCompatActivityがWebListener {...

2.  `WebListener`の `onPageLoaded` メソッドを実装し` 。 このメソッドは、スクリーンレットがページを正常にロードしたときに呼び出されます。 どのように実装するかは、ページのロード時に何をしたいか（もしあれば）によって異なります。 たとえば、この <code>onPageLoaded` 実装は、成功を示すトーストメッセージを表示します。
   
        @Override
        public void onPageLoaded（String url）{
            Toast.makeText（this、 "Page load successful！"、Toast.LENGTH_SHORT）.show（）;
       }

3.  `WebListener`の `onScriptMessageHandler` メソッドを実装し` 。 このメソッドは、スクリーンレットの <code>WebView`</a>

送信したときに呼び出されます。 `名前空間` 引数はソース名前空間キーであり、 `body` 引数はソース名前空間本体です。 たとえば、この `onScriptMessageHandler` 実装は、特定のネームスペースに一致する場合、ソースネームスペース本体からデータを解析し、インテントを介してそのデータで新しいアクティビティを開始します。</p> 
   
        @Override
        public void onScriptMessageHandler（String namespace、String body）{
            if（ "gallery" .equals（namespace））{
                String [] allImgSrc = body.split（ "、"）;
                int imgSrcPosition = Integer.parseInt（allImgSrc [allImgSrc.length-1]）;
       
                Intent intent = new Intent（getApplicationContext（）、DetailMediaGalleryActivity.class）;
                intent.putExtra（ "allImgSrc"、allImgSrc）;
                intent.putExtra（ "imgSrcPosition"、imgSrcPosition）;
                startActivity（intent）;
            }
       }
       </li> 
   
   4  `エラー` メソッドを実装します。 このメソッドは、プロセスでエラーが発生したときに呼び出されます。 `e` 引数には例外が含まれ、 `userAction` 引数にはエラーが発生した特定のアクションが区別されます。 ほとんどの場合、これらの引数を使用してエラーを記録または表示します。 たとえば、この `エラー` 実装は、例外のメッセージとともにトーストメッセージを表示します。
  
       @Override
       public void error（Exception e、String userAction）{
           Toast.makeText（this、 "Bad things happened：" + e.getMessage（）、Toast.LENGTH_LONG）.show（）;
      }
      

5  `WebScreenlet` 参照を取得し、アクティビティまたはフラグメントクラスをそのリスナーとして設定します。 これを行うには、 `com.liferay.mobile.screens.web.WebScreenlet` をインポートし、 `onCreate` メソッドの最後に次のコードを追加します。
  
       WebScreenletスクリーンレット=（WebScreenlet）findViewById（R.id.web_screenlet）;
       screenlet.setListener（this）;
      
  
  `findViewById` は、スクリーンレットのXMLで設定された `android：id` 値を参照することに注意してください。</ol> 

次に、同じ `WebScreenlet` 参照を使用して、スクリーンレットのパラメーターを設定します。



## Webスクリーンレットのパラメーターの設定

Web Screenletには、Screenletが機能するために必要なパラメーターを提供する `個のWebScreenletConfiguration` および `WebScreenletConfiguration.Builder` オブジェクトがあります。 これらのパラメーターには、ロードするページのURLと、ページをカスタマイズするJavaScriptファイルまたはCSSファイルの場所が含まれます。 これらのパラメーターのほとんどは、 `WebScreenletConfiguration.Builder`のメソッドを介して設定します。

| **注：** `WebScreenletConfiguration.Builder`のメソッドの完全なリスト、および|それぞれの説明については、|の表を参照してください。 [構成セクション](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-android#configuration) | Web Screenletのリファレンスドキュメントの

Web Screenletのパラメーターを設定するには、Screenletを含むアクティビティまたはフラグメントを初期化するメソッドで次の手順を実行します（たとえば、アクティビティの `onCreate` 、フラグメントの `onCreateView`）。 ただし、必要に応じて他の方法でこれを行うことができます。

1.  `WebScreenletConfiguration.Builder（<url>）`（ `<url>` はWebページのURL文字列）を使用して、 `WebScreenletConfiguration.Builder` オブジェクトを作成します。 ページで@product@認証が必要な場合、ユーザーは [Login Screenlet](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android) または `SessionContext` メソッドを介してログインする必要があり、 `WebScreenletConfiguration.Builder` コンストラクターに相対URLを提供する必要があります。 たとえば、そのようなページの完全なURLが `http：//your.liferay.instance/web/guest/blog`である場合、コンストラクターの引数は `/ web / guest / blog`です。 @product@認証を必要としないその他のページについては、コンストラクターに完全なURLを指定する必要があります。

2.  `WebScreenletConfiguration.Builder` メソッドを呼び出して、必要なパラメーターを設定します。
   
   | **注：** 指定したURLが `WebScreenletConfiguration.Builder` コンストラクターは、@product@認証を必要としないページに対するものです。 `WebScreenletConfiguration.Builder` メソッドを呼び出す必要があります| `setWebType（WebScreenletConfiguration.WebType.OTHER）`。 デフォルトは `WebType` | `LIFERAY_AUTHENTICATED`。これは、@product@ページを読み込むために必要です|認証が必要です。 `LIFERAY_AUTHENTICATED` 手動で設定する必要がある場合、|呼び出し `setWebType（WebScreenletConfiguration.WebType.LIFERAY_AUTHENTICATED）`

3.  `WebScreenletConfiguration.Builder` インスタンスの `load（）` メソッドを呼び出して、 `WebScreenletConfiguration` オブジェクトを返します。

4.  Web Screenletの `setWebScreenletConfiguration` メソッドを使用して、 `WebScreenletConfiguration` オブジェクトをWeb Screenletインスタンスに設定します。

5.  Web Screenletインスタンスの `load（）` メソッドを呼び出します。

以下は、Web Screenletインスタンスが `スクリーンレット`で、 `WebScreenletConfiguration` オブジェクトが `webScreenletConfiguration`であるアクティビティの `onCreate（）` メソッドのこれらのステップのスニペットの例です。

    WebScreenletConfiguration webScreenletConfiguration =
                new WebScreenletConfiguration.Builder（ "/ web / westeros-hybrid / companynews"）
                    .addRawCss（R.raw.portlet、 "portlet.css"）
                    .addLocalCss（ "gallery.css"）
                    .addLocalJs（ "gallery.js"）
                    .load（）;
    
            screenlet.setWebScreenletConfiguration（webScreenletConfiguration）;
            screenlet.load（）;
    

この例について注意すべき点がいくつかあります。

  - `WebScreenletConfiguration.Builder` コンストラクターに提供される相対URL `/ web / westeros-hybrid / companynews` 、および `setWebType（WebScreenletConfiguration.WebType.OTHER）` 呼び出しの欠如は、このWeb Screenletインスタンスが@productをロードすることを示します。認証が必要な@ページ。

  - `addRawCss` メソッドは、アプリの `res / raw` フォルダーからCSSファイル `portlet.css` 追加します。 メソッド `addRawCss` または `addRawJs` 使用して追加するファイルは、 `res / raw` 配置する必要があります（このフォルダーが存在しない場合は作成します）。 また、これらのファイルを `R.raw.yourfilename`で参照する必要があることに注意してください。 たとえば、この例の `portlet.css` ファイルは `R.raw.portlet`参照されています。

  - `addLocalCss` および `addLocalJs` メソッドは、ローカルファイル `gallery.css` および `gallery.js`をそれぞれ追加します。 これらの方法で追加するファイルは、アプリの `アセット` フォルダーの最初のレベルになければなりません。 このフォルダーは、アプリの `res` フォルダーと同じレベルに存在する必要があります。 存在しない場合は、その場所に `アセット` フォルダーを作成します。

すばらしいです\！ これで、AndroidアプリでWebスクリーンレットを使用する方法がわかりました。



## 関連トピック

[Android用Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-android)

[AndroidアプリでCordovaでWebスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-web-screenlet-with-cordova-in-your-android-app)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[AndroidアプリでのWebコンテンツのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-content-in-your-android-app)
