---
header-id: using-and-creating-progress-presenters
---

# プログレスプレゼンターの使用と作成

[TOC levels=1-4]

多くのアプリは、操作の実行中に進行状況インジケーターを表示します。 たとえば、iOSアプリのスピナーを見て、アプリが何らかの作業を実行していることを確認できます。 詳細については、 [進捗インジケータに関するiOSヒューマンインターフェイスガイドラインの記事](https://developer.apple.com/ios/human-interface-guidelines/ui-controls/progress-indicators/)参照してください。

[`ProgressPresenter` プロトコル](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift)準拠するクラスを使用して、これらをスクリーンレットに表示できます。 Liferay Screensには、次の2つのクラスが含まれます。

  - [`MBProgressHUDPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/MBProgressHUDPresenter.swift)：画面の中央にスピナーでメッセージを表示します。 Liferay Screensはデフォルトでこのプレゼンターを表示します。

  - [`NetworkActivityIndicatorPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/NetworkActivityIndicatorPresenter.swift)：iOSネットワークアクティビティインジケーターを使用して進行状況を表示します。 このプレゼンターはメッセージをサポートしていません。

このチュートリアルでのショーは、あなたがどのようにコードを使用して、使用して、進行状況のプレゼンターを作成するには [サンプル追加の高度なバージョンブックマークScreenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 例として。 最初に、プログレスプレゼンターの使用方法を学習します。

## プログレスプレゼンターの使用

[ `BaseScreenletView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) は、デフォルトのプログレスプレゼンター機能が含まれています。 したがって、デフォルトの `MBProgressHUDPresenter`以外のプレゼンターを表示するには、Viewクラスが特定の `BaseScreenletView` 機能をオーバーライドする必要があります。 これを行うには、次の手順を実行します。

1.  Viewクラスで、 `BaseScreenletView` メソッド `createProgressPresenter` をオーバーライドして、目的のプレゼンターのインスタンスを返します。 たとえば、サンプルAdd Bookmark Screenletで `NetworkActivityIndicatorPresenter` を使用するには、 `AddBookmarkView_default` `createProgressPresenter` メソッドをオーバーライドして、 `NetworkActivityIndicatorPresenter` インスタンスを返す必要があります。

    ``` 
     override func createProgressPresenter() -> ProgressPresenter {
         return NetworkActivityIndicatorPresenter()
     }
    ```

2.  Viewクラスで、 `BaseScreenletView` プロパティ `progressMessages` をオーバーライドして、プレゼンターで使用するメッセージを返します。 プレゼンターがメッセージを表示しない場合、空の文字列を返します。 `progressMessages` プロパティは、ASメッセージを返す必要が `：[ProgressMessages文字列]`、 `列` Screenletのアクション名です。 [`ProgressMessages` は、プログレスタイプがキーであり、実際のメッセージが値である辞書を表すタイプエイリアス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift) です。 ：三つの可能な進展の種類はScreenletアクションのステータスに対応して `ワーキング`、 `失敗`、または `成功`。 したがって、 `progressMessages` プロパティを使用すると、プレゼンターはスクリーンレットアクションの現在のステータスに適したメッセージを表示できます。

    たとえば、次のコードは、Add Bookmark ScreenletのViewクラス（`AddBookmarkView_default`）の `progressMessages` プロパティをオーバーライドします。 Screenletアクション（`AddBookmarkAction` および `GetTitleAction`）ごとに、メッセージ（`NoProgressMessage`）がScreenlet操作の `Working` ステータスに割り当てられます。 `NoProgressMessage` は空の文字列のエイリアスであるため、プレゼンターは、スクリーンレットがブックマークを追加またはタイトルを取得しようとしたときにメッセージを表示しないように指示します。 ただし、プレゼンターは進行状況インジケーターを表示することに注意してください。

    ``` 
     override var progressMessages: [String : ProgressMessages] {
         return [
             AddBookmarkScreenlet.AddBookmarkAction : [.Working: NoProgressMessage],
             AddBookmarkScreenlet.GetTitleAction : [.Working: NoProgressMessage],
         ]
     }
    ```

    メッセージを表示するには、 `NoProgressMessage` をメッセージに置き換えます。 例えば、次のコードは、のために別々のメッセージを定義 `ワーキング`、 `成功`、及び `失敗`：

    ``` 
     override var progressMessages: [String : ProgressMessages] {
         return [
             AddBookmarkScreenlet.AddBookmarkAction : [
                 .Working: "Saving bookmark...",
                 .Success: "Bookmark saved!",
                 .Failure: "An error occurred saving the bookmark"
             ],
             AddBookmarkScreenlet.GetTitleAction : [
                 .Working: "Getting site title...",
                 .Failure: "An error occurred retrieving the title"
             ],
         ]
     }
    ```

すばらしいです\！ これで、プログレスプレゼンターの使用方法がわかりました。 次に、独自の作成方法を学びます。

## プログレスプレゼンターの作成

独自のプログレスプレゼンターの作成は、あなたが考えるほど複雑ではありません。 Liferay Screensのプレゼンターは、 [`ProgressPresenter` プロトコル](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift)準拠するクラスであることを思い出してください。 プレゼンターを作成するには、このプロトコルを最初から適合させるか、このプロトコルに既に適合しているScreensの既存のプレゼンターのいずれかを拡張します（`MBProgressHUDPresenter` または `NetworkActivityIndicatorPresenter`）。 ほとんどの場合、 `MBProgressHUDPresenter` 拡張するだけで十分です。

たとえば、 [ブックマークスクリーンレットの追加 `AddBookmarkProgressPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/ProgressPresenter/AddBookmarkProgressPresenter.swift) は `MBProgressHUDPresenter` を拡張して、スクリーンレットのタイトル取得アクションの異なる進行状況インジケーターを表示します。 次の手順を使用して、既存のプレゼンターから拡張するプログレスプレゼンターを作成します。 例として、これらの手順は `MBProgressHUDPresenter` を拡張して、タイトル取得ボタンの進行状況インジケーターを追加します。

1.  ビューのXIBファイルに、使用するアクティビティインジケーターを追加します。 たとえば、Add Bookmark ScreenletのXIBファイルには、タイトル取得ボタンの上にiOS `UIActivityIndicatorView` が含まれています。

    ![図1：更新されたブックマークの追加スクリーンレットのXIBファイルには、タイトル取得ボタンの上に新しいアクティビティインジケーターが含まれています。](../../../images/screens-ios-xcode-add-bookmark-advanced-progress.png)

2.  Viewクラスで、XIBの新しいアクティビティインジケーターのアウトレットを作成します。 たとえば、Add Bookmark ScreenletのViewクラス（`AddBookmarkView_default`）には、XIBからの `UIActivityIndicatorView` `@IBOutlet` が含まれています。

    ``` 
     @IBOutlet weak var activityIndicatorView: UIActivityIndicatorView?
    ```

次に、プレゼンタークラスを作成する必要があります。 ここでは、既存のプレゼンタークラスを拡張してこれを行います。 これを行うには、次の手順を使用します。

1.  プレゼンターのベースにする既存のプレゼンタークラスを拡張します。 プレゼンタークラスには、プレゼンターのアクティビティインジケーターとその他のUIコンポーネントのプロパティが含まれている必要があります。 これらのプロパティを設定する初期化子も含まれている必要があります。 たとえば、 `AddBookmarkProgressPresenter` は `MBProgressHUDPresenter` を拡張し、タイトル取得ボタンと `UIActivityIndicatorView`プロパティを含みます。 その初期化子はこれらのプロパティを設定します：

    ``` 
     public class AddBookmarkProgressPresenter: MBProgressHUDPresenter {

         let button: UIButton?

         let activityIndicator: UIActivityIndicatorView?

         public init(button: UIButton?, activityIndicator: UIActivityIndicatorView?) {
             self.button = button
             self.activityIndicator = activityIndicator
             super.init()
         }
         ...
    ```

2.  拡張するプレゼンタークラスから適切なメソッドをオーバーライドすることにより、プレゼンターの動作を実装します。 たとえば、 `AddBookmarkProgressPresenter` `MBProgressHUDPresenter`の `showHUDInView` および `hideHUDFromView` メソッドをオーバーライドします。 オーバーライドされた `showHUDInView` メソッドはボタンを非表示にし、アクティビティインジケーターのアニメーションを開始します。 オーバーライドされた `hideHUDFromView` メソッドは、このアニメーションを停止し、ボタンを復元します。

    ``` 
     public override func showHUDInView(view: UIView, message: String?, 
         forInteractor interactor: Interactor) {
             guard interactor is GetWebTitleInteractor else {
                 return super.showHUDInView(view, message: message, 
                     forInteractor: interactor)
             }

             button?.hidden = true
             activityIndicator?.startAnimating()
     }

     public override func hideHUDFromView(view: UIView?, message: String?, 
         forInteractor interactor: Interactor, withError error: NSError?) {
             guard interactor is GetWebTitleInteractor else {
                 return super.hideHUDFromView(view, message: message, 
                     forInteractor: interactor, withError: error)
             }

             activityIndicator?.stopAnimating()
             button?.hidden = false
         }

     }
    ```

素晴らしい、それだけです！ これで、他のプレゼンターと同じ方法でプレゼンターを使用できます。

## 関連トピック

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)
