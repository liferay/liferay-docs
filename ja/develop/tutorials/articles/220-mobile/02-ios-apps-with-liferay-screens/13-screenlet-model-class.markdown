---
header-id: creating-and-using-your-screenlets-model-class
---

# スクリーンレットのモデルクラスの作成と使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferayのスクリーンは、典型的にLiferayのインスタンスからエンティティを受け取る `：[ANYOBJECT文字列]`、 `列` エンティティの属性となる `ANYOBJECT` 属性の値です。 スクリーンレット全体でこれらのディクショナリオブジェクトを使用できますが、多くの場合、対応するLiferayエンティティを表すオブジェクトに変換する *モデルクラス* を作成する方が簡単です。 これは、多くの属性と値のペアで構成される複雑なエンティティにとって特に便利です。 Liferayの画面がすでに提供していることに注意してください [いくつかのモデルクラス](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Models) 、あなたのために。

この時点で、「うーん！ 複雑なエンティティがあり、Screensはそれらのモデルクラスを提供しません\！ 私はただあきらめて、サッカーを見るつもりです。」フレットnot \！ 私たちはあなたとサッカーの間には決して来ませんでしたが、独自のモデルクラスを作成して使用するのは簡単です。

使用 [サンプルの高度なバージョンをブックマークScreenlet追加](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 、あなたのScreenletでモデルクラスを作成して使用する方法このチュートリアルは、一例として。 最初に、モデルクラスを作成します。

## モデルクラスの作成

モデルクラスには、サーバーから返される各 `[String：AnyObject]` を、対応するLiferayエンティティを表すモデルオブジェクトに変換するために必要なすべてのコードが含まれている必要があります。 これには、各 `[String：AnyObject]`を保持するための定数、この定数を設定するイニシャライザー、および各属性値のパブリックプロパティが含まれます。

たとえば、サンプルのブックマークスクリーンレットの追加は、Liferayインスタンスのブックマークポートレットにブックマークを追加します。 ブックマークを追加するMobile SDKサービスメソッドも `[String：AnyObject]`として返すため、スクリーンレットはそれをブックマークを表すオブジェクトに変換できます。 [`ブックマーク` モデルクラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Model/Bookmark.swift)ます。 このクラスは、NSObject</code> を `拡張し、 <code>[String：AnyObject]` を初期化子を介して `属性` 定数に設定します。 このクラスは、各ブックマークの名前とURLの属性値を返す計算プロパティも定義します。

    @objc public class Bookmark : NSObject {
    
        public let attributes: [String:AnyObject]
    
        public var name: String {
            return attributes["name"] as! String
        }
    
        override public var description: String {
            return attributes["description"] as! String
        }
    
        public var url: String {
            return attributes["url"] as! String
        }
    
        public init(attributes: [String:AnyObject]) {
            self.attributes = attributes
        }
    
    }

次に、モデルクラスを機能させます。

## モデルクラスの使用

モデルクラスが存在するようになったので、スクリーンレットが結果を処理する任意の場所でモデルオブジェクトを使用できます。 正確な場所は、スクリーンレットが使用するスクリーンレットコンポーネントによって異なります。 たとえば、ブックマークの追加スクリーンレットのコネクタ、インタラクター、デリゲート、およびスクリーンレットクラスはすべて、スクリーンレットの結果を処理します。 したがって、ここの手順では、これらの各コンポーネントでモデルオブジェクトを使用する方法を示します。 ただし、スクリーンレットにはコネクタまたはデリゲートがない場合があることに注意してください。これらのコンポーネントはオプションです。 したがって、該当する場合、これらの手順のバリエーションが記載されています。

1.  `[String：AnyObject]` 結果が発生するモデルオブジェクトを作成します。 たとえば、 `[文字列：AnyObject]` 、ブックマークの追加スクリーンレットはコネクタで生成されます。 したがって、ここでスクリーンレットは `ブックマーク` オブジェクトを作成します。 スクリーンレットのコネクタ（`AddBookmarkLiferayConnector`）の次のコードはこれを行います。 サービス呼び出しに続く `if` ステートメントは、結果を `[String：AnyObject]`にキャストし、それらの結果で `Bookmark` イニシャライザーを呼び出し、結果の `Bookmark` オブジェクトをpublic `resultBookmarkInfo` 変数に格納します。 これは、サービス呼び出しを行い、 `Bookmark` オブジェクトを作成するコードのみであることに注意してください。 [完全な `AddBookmarkLiferayConnector` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Connector/AddBookmarkLiferayConnector.swift)を表示するには、ここをクリックしてください。

    ``` 
     ...
     // Public property for the results
     public var resultBookmarkInfo: Bookmark?

     ...

     override public func doRun(session session: LRSession) {
         let service = LRBookmarksEntryService_v7(session: session)

         do {
             let result = try service.addEntryWithGroupId(LiferayServerContext.groupId,
                                                          folderId: folderId,
                                                          name: title,
                                                          url: url,
                                                          description: "Added from Liferay Screens",
                                                          serviceContext: nil)

             // Creates Bookmark objects from the service call's results
             if let result = result as? [String: AnyObject] {
                 resultBookmarkInfo = Bookmark(attributes: result)
                 lastError = nil
             }
             ...
         }
         ...
     }
    ```

    ScreenletにConnectorがない場合、Interactorの `start` メソッドはサーバー呼び出しを行い、その結果を処理します。 それ以外の場合、 `[String：AnyObject]` から `ブックマーク` オブジェクトを作成するプロセスは同じです。

2.  スクリーンレットのインタラクターでモデルオブジェクトを処理します。 Interactorはスクリーンレットの結果を処理するため、モデルオブジェクトも処理する必要があります。 スクリーンレットがコネクタを使用しない場合は、前の手順の最後で説明したように、Interactorの `start` メソッドで既にこれを実行しています。 ただし、スクリーンレットがコネクタを使用する場合、これはInteractorの `completedConnector` メソッドで発生します。 たとえば、Add Bookmark ScreenletのInteractor（`AddBookmarkInteractor`）の `completedConnector` メソッドは、Connectorの `resultBookmarkInfo` 変数を介して `Bookmark` を取得します。 このメソッドは、Interactorのpublic `resultBookmark` 変数に `Bookmark` を割り当てます。 これは `Bookmark` オブジェクトを処理するコードのみであることに注意してください。 [完全な `AddBookmarkInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Interactor/AddBookmarkInteractor.swift)を表示するには、ここをクリックしてください。

    ``` 
     ...
     // Public property for the results
     public var resultBookmark: Bookmark?

     ...

     // The completedConnector method gets the results from the Connector
     override public func completedConnector(c: ServerConnector) { 
         if let addCon = (c as? AddBookmarkLiferayConnector), 
             bookmark = addCon.resultBookmarkInfo { 
                 self.resultBookmark = bookmark 
             }
     }
    ```

3.  Screenletがデリゲートを使用する場合、デリゲートプロトコルはモデルオブジェクトを考慮する必要があります。 デリゲートがない場合は、この手順をスキップしてください。 たとえば、ブックマークの追加スクリーンレットのデリゲート（`AddBookmarkScreenletDelegate`）は、 `ブックマーク` オブジェクトを通信する必要があります。 デリゲートの最初のメソッドは、2番目の引数を介してこれを行います。

    ``` 
     @objc public protocol AddBookmarkScreenletDelegate: BaseScreenletDelegate {

         optional func screenlet(screenlet: AddBookmarkScreenlet,
                         onBookmarkAdded bookmark: Bookmark)

         optional func screenlet(screenlet: AddBookmarkScreenlet,
                         onAddBookmarkError error: NSError)

     }
    ```

4.  Screenletクラスの `interactor.onSuccess` クロージャーのInteractorからモデルオブジェクトを取得します。 その後、必要に応じてモデルオブジェクトを使用できます。 たとえば、Add Bookmark ScreenletのScreenletクラス（`AddBookmarkScreenlet`）の `interactor.onSuccess` クロージャーは、Interactorの `resultBookmark` プロパティから `Bookmark` 取得します。 次に、デリゲートを介して `ブックマーク` を処理し` 。 この例では、クロージャは、ブックマークを追加するScreenletクラスのInteractorメソッドにあります（<code>createAddBookmarkInteractor`）。 Screenletクラスの `interactor.onSuccess` クロージャーがどこにある場合でも、必ずモデルオブジェクトを取得してください。 [完全な `を見るにはここをクリック<code> AddBookmarkScreenlet`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/AddBookmarkScreenlet.swift)：

    ``` 
     ...
     private func createAddBookmarkInteractor() -> Interactor {
         let interactor = AddBookmarkInteractor(screenlet: self,
                                            folderId: folderId,
                                            title: viewModel.title!,
                                            url: viewModel.URL!)

         // Called when the Interactor finishes successfully
         interactor.onSuccess = {
             if let bookmark = interactor.resultBookmark {
                 self.addBookmarkDelegate?.screenlet?(self, onBookmarkAdded: bookmark)
             }
         }

         // Called when the Interactor finishes with an error
         interactor.onFailure = { error in
             self.addBookmarkDelegate?.screenlet?(self, onAddBookmarkError: error)
         }

         return interactor
     }
     ...
    ```

驚くばかり\！ これで、スクリーンレットでモデルクラスを作成して使用する方法がわかりました。

## 関連トピック

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[スクリーンレットアクションの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-screenlet-actions)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)
