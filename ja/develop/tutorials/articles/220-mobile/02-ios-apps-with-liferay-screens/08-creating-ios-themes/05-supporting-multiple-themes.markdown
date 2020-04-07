---
header-id: supporting-multiple-themes-in-your-ios-screenlet
---

# iOSスクリーンレットで複数のテーマをサポートする

[TOC levels=1-4]

テーマを使用すると、同じスクリーンレットを異なるルックアンドフィールで表示できます。 たとえば、同じスクリーンレットを使用するアプリが複数ある場合、異なるテーマを使用して、スクリーンレットの外観を各アプリのスタイルに一致させることができます。 Liferay Screensに付属の各スクリーンレットは、複数のテーマの使用をサポートしています。 ただし、カスタムScreenletが異なるテーマをサポートするには、 *View Model* プロトコルが含まれている必要があります。 ビューモデルは、スクリーンレットの表示に使用されるテーマを抽象化し、開発者が他のテーマを使用できるようにします。 たとえば、 [のスクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets) のScreenletクラスの `createInteractor` メソッドは、Viewクラスへの参照を取得するときに、Viewクラス（`AddBookmarkView_default`）に直接アクセスします。

    let view = self.screenletView as! AddBookmarkView_default

これは、 `AddBookmarkView_default`\！で定義されたテーマをハードコードすることを除いて、すべて問題あり`ん。 別のテーマを使用するには、このコード行を書き換えて、そのテーマのViewクラスを使用する必要があります。 これは非常に柔軟ではありません\！ Screenletに高価なヨガのクラスを受講させる代わりに、View Modelプロトコルを介してテーマのViewクラスを抽象化できます。</p>

<p spaces-before="0">このチュートリアルでは、スクリーンレットにビューモデルを追加する方法を示します。 ブックマークScreenletで作成した追加 <a href="/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets">Screenlet作成チュートリアル</a> 一例として使用されています。 スクリーンレットの作成中にビューモデルを追加することもできます。</p>

<h2 spaces-before="0">ビューモデルの作成と使用</h2>

<p spaces-before="0">スクリーンレットでビューモデルを追加して使用するには、次の手順に従います。</p>

<ol start="1">
<li><p spaces-before="0"> スクリーンレットの属性を定義するビューモデルプロトコルを作成します。 これらの属性は、Screenletクラスが使用するViewクラスプロパティです。 たとえば、Add Bookmark Screenlet</a> の <a href="https://github.com/liferay/liferay-screens/blob/develop/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/AddBookmarkScreenlet.swift">Screenletクラスは、Viewクラスプロパティ <code>title` および `URL`ます。 したがって、ブックマークスクリーンレットのビューモデルプロトコル（`AddBookmarkViewModel`）を追加するには、これらのプロパティの変数を定義する必要があります。

    ``` 
     import UIKit

     @objc protocol AddBookmarkViewModel {

         var URL: String? {get}

         var title: String? {get}

     }
    ```
</li>

2

ViewクラスをScreenletのView Modelプロトコルに適合させます。 すべてのプロトコルのプロパティを取得/設定してください。 たとえば、 `AddBookmarkViewModel`準拠したAdd Bookmark Screenletのビュークラス（`AddBookmarkView_default`）は次のとおりです。

    ``` 
     import UIKit
     import LiferayScreens

     class AddBookmarkView_default: BaseScreenletView, AddBookmarkViewModel {

         @IBOutlet weak var URLTextField: UITextField?
         @IBOutlet weak var titleTextField: UITextField?

         var URL: String? {
             return URLTextField?.text
         }

         var title: String? {
             return titleTextField?.text
         }

     }
    ```

3

Screenletクラスでビューモデル参照を作成して使用します。 Viewクラスの直接参照ではなく、この参照からデータを取得することにより、他のテーマでScreenletを使用できます。 たとえば、 `AddBookmarkView_default`への直接参照の代わりに、 `viewModel` プロパティを持つ `AddBookmarkScreenlet` クラスがあります。 このクラスの `createInteractor` メソッドは、このプロパティを使用して、 `AddBookmarkInteractor` コンストラクターで `タイトル` および `URL` プロパティを取得します。

    ``` 
     ...
     //View Model reference
     var viewModel: AddBookmarkViewModel {
         return self.screenletView as! AddBookmarkViewModel
     }

     override public func createInteractor(name name: String?, sender: AnyObject?) -> Interactor? {

         let interactor = AddBookmarkInteractor(screenlet: self,
                                                folderId: folderId,
                                                title: viewModel.title!,
                                                url: viewModel.URL!)

         // Called when the Interactor finishes succesfully
         interactor.onSuccess = {
             let bookmarkName = interactor.resultBookmarkInfo!["name"] as! String
             print("Bookmark \"\(bookmarkName)\" saved!")
         }

         // Called when the Interactor finishes with an error
         interactor.onFailure = { _ in
             print("An error occurred saving the bookmark")
         }

         return interactor
     }
     ...
    ```
</ol>

それでおしまい\！ これで、スクリーンレットは、作成した他のテーマを使用する準備が整いました。 上のチュートリアルを参照してください [作成iOSのテーマ](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes) テーマの作成手順について。

## 関連トピック

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)
