---
header-id: creating-the-ios-screenlets-class
---

# iOSスクリーンレットのクラスを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletクラスは、Screenletの中心的なハブです。 Screenletのプロパティ、ScreenletのViewクラスへの参照、Interactor操作を呼び出すためのメソッドなどが含まれています。 Screenletを使用する場合、アプリ開発者は主にScreenletクラスと対話します。 つまり、Screenletが自己認識になると、そのScreenletクラスで発生します（これは発生しないと合理的に確信していますが）。

[Screensの `BaseScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift) は、ベースScreenletクラスの実装です。 `BaseScreenlet` はScreenletクラスに必要な機能のほとんどを提供するため、Screenletクラスは `BaseScreenlet`を拡張する必要があります。 これにより、スクリーンレットの独自の機能に集中できます。 Screenletクラスには、Screenletに必要な `@IBInspectable` プロパティとScreenletのViewクラスへの参照も含める必要があります。 Screenletのアクションを実行するには、Screenletクラスが `BaseScreenlet`の `createInteractor` メソッドをオーバーライドする必要があります。 このメソッドは、Interactorのインスタンスを作成し、Interactorの `onSuccess` および `onFailure` クロージャーを設定して、サーバー呼び出しが成功または失敗した場合の動作をそれぞれ定義する必要があります。

たとえば、 [`AddBookmarkScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/AddBookmarkScreenlet.swift) は、Add Bookmark ScreenletのScreenletクラスです。 このクラスは `BaseScreenlet` を拡張し、ブックマークフォルダーのID（`folderId`）の `@IBInspectable` 変数を含みます。 `AddBookmarkScreenlet` クラスの `createInteractor` メソッドは、最初にViewクラスへの参照を取得します（`AddBookmarkView_default`）。 次に、このScreenletクラス（`self`）、 `folderId`、ブックマークのタイトル、およびブックマークのURLで `AddBookmarkInteractor` インスタンスを作成します。 Viewクラス参照には、ユーザーがUIに入力したブックマークタイトルとURLが含まれていることに注意してください。 `createInteractor` メソッドは、サーバー呼び出しが成功したときに成功メッセージを出力するようにInteractorの `onSuccess` クロージャーを設定します。 同様に、サーバー呼び出しが失敗したときにエラーメッセージを出力するように、Interactorの `onFailure` クロージャーが設定されます。 ここでメッセージを印刷するだけに制限されていないことに注意してください。これらのクロージャを設定して、スクリーンレットに最適なものを実行する必要があります。 Interactorインスタンスを返すことにより、 `createInteractor` メソッドが終了します。 完全な `AddBookmarkScreenlet` クラスは次のとおりです。

    import UIKit
    import LiferayScreens
    
    
    public class AddBookmarkScreenlet: BaseScreenlet {
    
        //MARK: Inspectables
    
        @IBInspectable var folderId: Int64 = 0
    
        //MARK: BaseScreenlet
    
        override public func createInteractor(name name: String?, sender: AnyObject?) -> Interactor? {
    
            let view = self.screenletView as! AddBookmarkView_default
    
            let interactor = AddBookmarkInteractor(screenlet: self,
                                               folderId: folderId,
                                               title: view.title!,
                                               url: view.URL!)
    
            //Called when the Interactor's server call finishes succesfully
            interactor.onSuccess = {
                let bookmarkName = interactor.resultBookmarkInfo!["name"] as! String
                print("Bookmark \"\(bookmarkName)\" saved!")
            }
    
            //Called when the Interactor's server call fails
            interactor.onFailure = { _ in
                print("An error occurred saving the bookmark")
            }
    
            return interactor
        }
    
    }

参考までに、GitHub</a>のサンプルAdd Bookmark Screenletの最終コードは です。</p>
