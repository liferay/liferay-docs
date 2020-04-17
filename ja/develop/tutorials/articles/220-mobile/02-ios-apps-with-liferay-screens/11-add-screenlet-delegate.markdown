---
header-id: add-a-screenlet-delegate
---

# スクリーンレットデリゲートを追加する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリーンレットデリゲートを使用すると、他のクラスがスクリーンレットのアクションに応答できます。 たとえば、 [Login Screenletのデリゲート](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios#delegate) 使用すると、アプリの開発者はログインの成功または失敗に応答するメソッドを実装できます。 なお、 [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) Liferayの画面リストScreenletのデリゲートメソッドが付属していた各Screenletについて。

また、独自のスクリーンレットのデリゲートを作成することもできます。 このチュートリアルでは、からのコードを使用して、これを行うために必要な手順を説明します [サンプル追加ブックマークScreenletの高度なバージョン](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 例として。 このチュートリアル存在のすべてのコード例 [このScreenletのScreenletクラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/AddBookmarkScreenlet.swift)。 このサンプルスクリーンレットには、Liferayインスタンスのブックマークポートレットにブックマークを追加することと、URLからブックマークのタイトルを取得することの2つのアクションがあることに注意してください。 このチュートリアルでは、ブックマークを追加するためのデリゲートの作成についてのみ説明します。

スクリーンレットにデリゲートを追加するには、次の手順に従います。

1.  [`BaseScreenletDelegate` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift)を拡張するデリゲートプロトコルを定義します。 このプロトコルでは、適合クラスがサーバー呼び出しの成功と失敗にそれぞれ応答できるように、成功メソッドと失敗メソッドを定義します。 パラメーターとして、これらのメソッドはScreenletインスタンスと成功または失敗オブジェクトを受け取る必要があります。 たとえば、Add Bookmark Screenletのデリゲートプロトコル（`AddBookmarkScreenletDelegate`）は、次の成功メソッドと失敗メソッドを定義します。

    ``` 
     @objc public protocol AddBookmarkScreenletDelegate: BaseScreenletDelegate {

         optional func screenlet(screenlet: AddBookmarkScreenlet,
                             onBookmarkAdded bookmark: [String: AnyObject])

         optional func screenlet(screenlet: AddBookmarkScreenlet,
                             onAddBookmarkError error: NSError)

     }
    ```

    どちらも、最初の引数として `AddBookmarkScreenlet` インスタンスを取ります。 2番目の引数では、成功メソッドにはサーバーに追加されたブックマークが含まれ、失敗メソッドには `NSError` オブジェクトが含まれます。 この例では、メソッドはオプションです。 これは、デリゲートクラスがそれらを実装する必要がないことを意味します。

2.  Screenletクラスで、デリゲートのプロパティを追加します。 このプロパティは、デリゲートのインスタンスとして `BaseScreenlet`の `デリゲート` プロパティを返す必要があります。 たとえば、 `AddBookmarkScreenlet` の `addBookmarkDelegate` プロパティは `self.delegate` プロパティを `AddBookmarkScreenletDelegate`として返します。

    ``` 
     var addBookmarkDelegate: AddBookmarkScreenletDelegate? {
         return self.delegate as? AddBookmarkScreenletDelegate
     }
    ```

3.  また、Screenletクラスで、Interactorのクロージャーで適切なデリゲートメソッドを呼び出します。 たとえば、 `AddBookmarkScreenlet` の `interactor.onSuccess` クロージャは、Screenletがブックマークを正常に追加したときに応答するデリゲートメソッドを呼び出します。 `interactor.onFailure` クロージャーは、スクリーンレットがブックマークの追加に失敗したときに応答するデリゲートメソッドを呼び出します。 この例では、これらのクロージャーはブックマーク（`createAddBookmarkInteractor`）を追加するScreenletクラスのInteractorメソッド内にあることに注意してください。 Screenletクラスに適切なInteractorのクロージャーがある場合は、必ずデリゲートメソッドを呼び出してください。

    ``` 
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
    ```

すばらしいです\！ これで、スクリーンレットにデリゲートを追加する方法がわかりました。

**関連トピック**

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[スクリーンレットアクションの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-screenlet-actions)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)
