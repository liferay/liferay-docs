---
header-id: rendering-web-pages-in-your-ios-app
---

# iOSアプリでのWebページのレンダリング

[TOC levels=1-4]

[Webコンテンツのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-content-in-your-ios-app) チュートリアルでは、iOSアプリで@product@サイトから [Webコンテンツ](/docs/7-1/user/-/knowledge_base/u/introduction-web-content) を表示する方法を示します。コンテンツの表示は素晴らしいですが、ページ全体を表示したい場合はどうでしょうか？ 問題ない\！ [Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-ios) では、任意のWebページを表示できます。 ローカルまたはリモートのJavaScriptファイルとCSSファイルを挿入して、ページをカスタマイズすることもできます。 @product@のサーバー側のカスタマイズ機能（例： [アプリケーション表示テンプレート](/docs/7-1/user/-/knowledge_base/u/styling-apps-and-assets)）と組み合わせると、Web ScreenletはiOSアプリでWebページを表示するためのほぼ無限の可能性を提供します。

このチュートリアルでは、Web Screenletを使用してiOSアプリでWebページを表示する方法を学習します。

## アプリにWebスクリーンレットを挿入する

アプリにWebスクリーンレットを挿入することは、アプリにスクリーンレットを挿入する [と同じです](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)：

1.  Interface Builderで、新しいView Controllerに新しいビュー（`UIView`）を挿入します。 この新しいビューは、View Controllerの既存のビューの下にネストする必要があります。

2.  新しいビューを選択した状態で、IDインスペクターを開き、ビューのカスタムクラスを `WebScreenlet`設定します。

3.  シーン内のスクリーンレットに必要な制約を設定します。

Web Screenletを構成する正確な手順は、Web Screenletに固有です。 最初に、View ControllerをWeb Screenletのデリゲートプロトコルに適合させます。

## Web Screenletのデリゲートプロトコルに準拠

Screenletを使用するには、それを含むView ControllerのクラスをScreenletのデリゲートプロトコルに準拠させる必要があります。 Web Screenletのデリゲートプロトコルは `WebScreenletDelegate`です。 以下の手順に従って、View Controllerを `WebScreenletDelegate`に適合させます：

1.  `個のLiferayScreens` をインポートし、 `WebScreenletDelegate` プロトコルを採用するようにView Controllerを設定します。
   
        import UIKit
        import LiferayScreens
       
        class ViewController: UIViewController, WebScreenletDelegate {...

2.  `WebScreenletDelegate` メソッド `onWebLoad（_：url :)`実装し` 。 このメソッドは、スクリーンレットがページを正常にロードしたときに呼び出されます。 どのように実装するかは、ページのロード時に何をしたいか（もしあれば）によって異なります。 その引数は、 <code>WebScreenlet` インスタンスとページURLです。 この例では、ページがロードされたことを示すメッセージをコンソールに出力します。
   
        func onWebLoad(_ screenlet: WebScreenlet, url: String) {
            // Called when the page is loaded
            print("\(url) was just loaded")
        }

3.  `WebScreenletDelegate` メソッド `screenlet（_：onError :)`実装し` 。 このメソッドは、ページの読み込み中にエラーが発生したときに呼び出されるため、 <code>NSError` オブジェクトが含まれます。 これにより、エラーを記録または印刷できます。 たとえば、この実装はエラーの説明を含むメッセージを出力します：
   
        func screenlet(_ screenlet: WebScreenlet, onError error: NSError) {
            print("Failed to load the page: \(error.localizedDescription)")
        }

4.  `WebScreenletDelegate` メソッド `screenlet（_：onScriptMessageNamespace：onScriptMessage :)`実装します。 このメソッドは、スクリーンレットの `WKWebView`</a>

送信したときに呼び出されます。 このメソッドの引数には、メッセージの名前空間とメッセージが含まれます。 このメソッドを実装する方法は、メッセージが送信されたときに何をしたいかによって異なります。 たとえば、セグエを実行し、メッセージをセグエの `送信者`として含めることができます。</p> 
   
        func screenlet(_ screenlet: WebScreenlet,
            onScriptMessageNamespace namespace: String,
            onScriptMessage message: String) {
       
            performSegue(withIdentifier: "detail", sender: message)
        }
       </li> 
   
   5  Interface Builderを使用してView Controllerでアウトレットを作成し、ストーリーボード上のWeb Screenletへの参照を取得します。 それはScreenletそれが参照した後Screenletコンセントに名前を付けることをお勧めです、または単に `screenlet`。 次に、Webスクリーンレットアウトレットの例を示します。
  
       @IBOutlet weak var webScreenlet: WebScreenlet?
      

6  View Controllerの `viewDidLoad（）` メソッドで、作成したWeb Screenlet参照を使用して、View ControllerをScreenletのデリゲートとして設定します。 これを行うには、 `super.viewDidLoad（）` 呼び出しのすぐ下に次のコード行を追加します。
  
       self.webScreenlet?.delegate = self
      </ol> 

次に、同じWebスクリーンレット参照を使用して、スクリーンレットのパラメーターを設定します。



## Webスクリーンレットのパラメーターの設定

Web Screenletには、Screenletが機能するために必要なパラメーターを提供する `個のWebScreenletConfiguration` および `WebScreenletConfigurationBuilder` オブジェクトがあります。 これらのパラメーターには、ロードするページのURLと、ページをカスタマイズするJavaScriptファイルまたはCSSファイルの場所が含まれます。 これらのパラメーターのほとんどは、 `WebScreenletConfigurationBuilder`のメソッドを介して設定します。

| **注：** `WebScreenletConfigurationBuilder`のメソッドの完全なリスト、および|それぞれの説明については、表を参照してください| [構成セクション](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-ios#configuration) | Web Screenletのリファレンスドキュメントの

Web Screenletのパラメータを設定するには、Web Screenletを使用するView Controllerの `viewDidLoad（）` メソッドで次の手順を実行します。

1.  `WebScreenletConfigurationBuilder` オブジェクトを作成するには、 `WebScreenletConfigurationBuilder（<url>）`（ `<url>` はWebページのURL文字列）を使用します。 ページに@product@認証が必要な場合、ユーザーは [Login Screenlet](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios) または `SessionContext` メソッドを介してログインする必要があり、 `WebScreenletConfigurationBuilder` コンストラクターに相対URLを提供する必要があります。 たとえば、そのようなページの完全なURLが `http：//your.liferay.instance/web/guest/blog`である場合、コンストラクターの引数は `/ web / guest / blog`です。 @product@認証を必要としないその他のページについては、コンストラクターに完全なURLを指定する必要があります。

2.  `WebScreenletConfigurationBuilder` メソッドを呼び出して、必要なパラメーターを設定します。
   
   | **注：** もしあなたがに供給されるURL `WebScreenletConfigurationBuilder` |コンストラクターは、@product@認証を必要としないページに対するものです。 `WebScreenletConfigurationBuilder` メソッドを呼び出す必要があります| `set（webType：.other）`。 デフォルトの `WebType` は `。.liferayAuthenticated`、|認証が必要な@product@ページを読み込むために必要です。 もし| `.liferayAuthenticated` 手動で設定する必要があります。 `set（webType：.liferayAuthenticated）`。

3.  `WebScreenletConfigurationBuilder` インスタンスの `load（）` メソッドを呼び出して、 `WebScreenletConfiguration` オブジェクトを返します。

4.  `WebScreenletConfiguration` オブジェクトをWeb Screenletインスタンスの `構成` プロパティに設定します。

5.  Web Screenletインスタンスの `load（）` メソッドを呼び出します。

以下に、Web Screenletインスタンスが `webScreenlet`で、 `WebScreenletConfiguration` オブジェクトが `webScreenletConfiguration`であるView Controllerの `viewDidLoad（）` メソッドのこれらのステップのスニペットの例を示します。

    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.webScreenlet?.delegate = self
    
        let webScreenletConfiguration = 
            WebScreenletConfigurationBuilder(url: "/web/westeros-hybrid/companynews")
                .addCss(localFile: "blogs")
                .addJs(localFile: "blogs")
                .load()
        webScreenlet.configuration = webScreenletConfiguration
        webScreenlet.load()
    }
    

`WebScreenletConfigurationBuilder` コンストラクターに提供される相対URL `/ web / westeros-hybrid / companynews` 、および `set（webType：.other）` 呼び出しがないことは、このWeb Screenletインスタンスが@product@ページをロードすることを示します。認証が必要です。 `addCss` および `addJs` 呼び出しは、それぞれローカルCSSファイルとJavaScriptファイルを追加します。 両方のファイルの名前は `ブログ`です。

すばらしいです\！ これで、iOSアプリでWebスクリーンレットを使用する方法がわかりました。



## 関連トピック

[iOS用Webスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-screenlet-for-ios)

[iOSアプリでCordovaでWebスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-web-screenlet-with-cordova-in-your-ios-app)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[iOSアプリでのWebコンテンツのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-web-content-in-your-ios-app)
