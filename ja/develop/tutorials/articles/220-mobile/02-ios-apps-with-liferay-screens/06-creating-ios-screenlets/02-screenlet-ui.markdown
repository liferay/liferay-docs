---
header-id: creating-the-ios-screenlets-ui
---

# iOSスクリーンレットのUIの作成

[TOC levels=1-4]

iOS用Liferay Screensでは、スクリーンレットのUIはテーマと呼ばれます。 すべてのスクリーンレットには、少なくとも1つのテーマが必要です。 テーマには次のコンポーネントがあります。

1.  XIBファイル：テーマがエンドユーザーに提示するUIコンポーネントを定義します。

2.  Viewクラス：UIをレンダリングし、ユーザーインタラクションを処理し、Screenletクラスと通信します。

最初に、新しいXIBファイルを作成し、Interface Builderを使用してスクリーンレットのUIを構築します。 多くの場合、スクリーンレットのアクションはテーマからトリガーする必要があります。 これを実現するには、 `restoreIdentifier` プロパティを使用して、アクションをトリガーする各UIコンポーネントに一意のIDを割り当てるようにしてください。 ユーザーは、UIコンポーネントと対話することでアクションをトリガーします。 アクションがUIの状態のみを変更する（つまり、UIコンポーネントのプロパティを変更する）場合、通常どおり、そのコンポーネントのイベントを `IBAction` メソッドに関連付けることができます。 `restoreIdentifier` を使用するアクションは、サーバー要求を行うアクションやデータベースからデータを取得するアクションなど、Interactorを必要とするアクションによる使用を目的としています。

たとえば、ブックマークの追加スクリーンレットのUIには、ブックマークのURLとタイトルを入力するためのテキストボックスが必要です。 このUIには、スクリーンレットのアクションをサポートするボタンも必要です。Liferayインスタンスにブックマークを送信します。 [XIBファイル `AddBookmarkView_default.xib`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.xib) は、このUIを定義します。 ボタンはスクリーンレットのアクションをトリガーするため、 `restoreIdentifier = "add-bookmark"`が含まれます。

![図1：Interface BuilderでレンダリングされたサンプルAdd Bookmark ScreenletのXIBファイルです。](../../../../images/screens-ios-xcode-add-bookmark.png)

| **注：** このチュートリアルのスクリーンレットは、複数のテーマをサポートしていません。 あなたが|スクリーンレットで複数のテーマをサポートするには、Viewクラスも必要です|作成する *View Model* プロトコルに準拠します。 この手順については、を参照してください|チュートリアル| [スクリーンレットで複数のテーマをサポートする](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)。

次に、スクリーンレットのビュークラスを作成する必要があります。 このクラスは、先ほど定義したUIを制御します。 [`BaseScreenletView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift)では、ScreensはすべてのViewクラスに必要なデフォルト機能を提供します。 したがって、Viewクラスは、 `BaseScreenletView` を拡張して、スクリーンレットに固有の機能を提供する必要があります。 UIをサポートするには、標準の `@IBOutlet`と `@IBAction`を使用して、すべてのXIBのUIコンポーネントとイベントをViewクラスに接続します。 また、ゲッターとセッターを実装して、UIコンポーネントから値を取得したり、UIコンポーネントに値を設定したりする必要があります。 Viewクラスは、必要なアニメーションまたはフロントエンドロジックも実装する必要があります。

たとえば、 [`AddBookmarkView_default`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.swift) はブックマークスクリーンレットのビュークラスの追加です。 このクラスは `BaseScreenletView` を拡張し、XIBのテキストフィールドへの `@IBOutlet` 参照を含みます。 これらの参照のゲッターにより、テーマは、ユーザーが対応するテキストフィールドに入力したデータを取得できます。

    import UIKit
    import LiferayScreens
    
    class AddBookmarkView_default: BaseScreenletView {
    
       @IBOutlet weak var URLTextField: UITextField?
       @IBOutlet weak var titleTextField: UITextField?
    
       var URL: String? {
           return URLTextField?.text
       }
    
       var title: String? {
           return titleTextField?.text
       }
    }

Interface Builderで、ViewクラスをXIBファイルのカスタムクラスとして指定する必要があります。 たとえば、Add Bookmark Screenletでは、 `AddBookmarkView_default` がInterface Builderで `AddBookmarkView_default.xib` ファイルのカスタムクラスとして設定されます。

CocoaPodsを使用している場合は、カスタムクラスに有効なモジュールを明示的に設定してください *グレー表示された*現在の* デフォルト値は、モジュールのみを示しています。</p>

![図2：このXIBファイルでは、カスタムクラスのモジュールは指定されていません。](../../../../images/screens-ios-theme-custom-module-wrong.png)

![図3：XIBファイルは、指定されたモジュールを使用して、カスタムクラス名にバインドされます。](../../../../images/screens-ios-theme-custom-module-right.png)
