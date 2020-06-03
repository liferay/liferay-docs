---
header-id: adding-screenlet-actions
---

# スクリーンレットアクションの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

複数のインタラクターを使用すると、スクリーンレットに複数のアクションを含めることができます。 アクションごとにInteractorクラスを作成する必要があります。 たとえば、スクリーンレットで2つのサーバー呼び出しを行う必要がある場合は、呼び出しごとに1つずつ、2つのインターアクターが必要です。 Screenletクラスの `createInteractor` メソッドは、各Interactorのインスタンスを返す必要があります。 また、各アクション名は、それらをトリガーするUIコンポーネントの `restoreIdentifier` によって指定されることを思い出してください。 Screenletでこの `restoreIdentifier` を定数に設定する必要があります。

このチュートリアルでは、スクリーンレットにアクションを追加し、プログラムでアクションをトリガーするために必要な手順を説明します。 例として、このチュートリアルでは、サンプルAdd Bookmark Screenlet</a>高度なバージョン

使用し 。 このScreenletは、サンプルに似ているブックマークScreenletで作成した追加 [Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)。 ただし、高度なブックマークの追加スクリーンレットには、次の2つのアクションが含まれています。</p> 

1.  ブックマークの追加：@product@インストールのブックマークポートレットにブックマークを追加します。 これで作成したScreenletの主な行動であり、 [Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)。

2.  タイトルを取得：ユーザーが入力したブックマークURLからタイトルを取得します。 このチュートリアルでは、このアクションを実装する方法を示します。

このチュートリアルでは、一般的なスクリーンレットの作成については説明していません。 先に進む前に、必ず読んで作る [Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)。 さらに苦労することなく、今度はスクリーンレットのアクションを実装しましょう！



## アクションを実装する

次の手順を使用して、スクリーンレットにアクションを追加します。

1.  スクリーンレットのアクションごとに、スクリーンレットクラスに定数を作成します。 たとえば、Add Bookmark ScreenletのScreenletクラス（`AddBookmarkScreenlet`）の定数は次のとおりです。 
   
   

    ``` 
     static let AddBookmarkAction = "add-bookmark"
     static let GetTitleAction = "get-title"
    ```


2.  テーマのXIBファイルに、アクションの実行に必要な新しいUIコンポーネントを追加します。 たとえば、Add Boookmark ScreenletのXIBファイルには、URLのタイトルを取得するための新しいボタンが必要です。
   
   ![図1：Add Bookmark ScreenletのサンプルXIBファイルには、URLタイトルを取得するための* Title *フィールドの横に新しいボタンが含まれています。](../../../images/screens-ios-xcode-add-bookmark-advanced.png)

3.  XIBファイルのUIコンポーネントをViewクラスに配線します。 Viewクラスでは、反応するイベント（ボタンのクリックなど）も登録する必要があります。 [`BaseScreenletView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) は、Viewクラスで呼び出してアクションをプログラムで実行できる `userAction` メソッドのセットが含まれています。 たとえば、ユーザーが `URLTextField`離れるたびに、Add Bookmark Screenletの `GetTitleAction` 自動的にトリガーすることができます。 `BaseScreenletView` はデフォルトで `UITextField` オブジェクトすべてのデリゲートであるため、これはViewクラス（`AddBookmarkView_default`）で [`textFieldDidEndEditing` メソッド](https://developer.apple.com/reference/uikit/uitextfielddelegate/1619591-textfielddidendediting) を実装してアクション名で `userAction` メソッドを呼び出すことで行われます： 
   
   

    ``` 
     func textFieldDidEndEditing(textField: UITextField) {
         if textField == URLTextField {
             userAction(name: AddBookmarkScreenlet.GetTitleAction)
         }
     }
    ```


4.  ViewクラスまたはView Modelプロトコルを更新して、新しいアクションに対応します。 例えば、ブックマークScreenletビューモデル（含まれています`AddBookmarkViewModel`）それができるよう、 [をサポート、複数のテーマ](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)。 このビューモデルは、新しいアクションが `タイトル` 変数を設定できるようにする必要があります。 
   
   

    ``` 
     import UIKit

     @objc protocol AddBookmarkViewModel {
         var URL: String? {get}
         var title: String? {set get}
     }
    ```


5.  スクリーンレットがビューモデルを使用している場合、ビュークラスを更新されたビューモデルに適合させます。 たとえば、Add Bookmark ScreenletのViewクラスの `title` 変数（`AddBookmarkView_default`）は、前のステップのセッターを実装する必要があります。 
   
   

    ``` 
     var title: String? {
         get {
             return titleTextField?.text
         }
         set {
             self.titleTextField?.text = newValue
         }
     }
    ```


6.  新しいアクションの新しいInteractorクラスを作成します。 これを行うには、 [スクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ios-screenlets-interactor)説明されているのと同じ手順を使用します。 たとえば、ブックマークスクリーンレットの追加のタイトル取得アクションのInteractorクラスは次のとおりです。 
   
   

    ``` 
     import UIKit
     import LiferayScreens


     public class GetWebTitleInteractor: Interactor {

         public var resultTitle: String?

         var url: String

         //MARK: Initializer

         public init(screenlet: BaseScreenlet, url: String) {
             self.url = url
             super.init(screenlet: screenlet)
         }

         override public func start() -> Bool {
             if let URL = NSURL(string: url) {

                 // Use the NSURLSession class to retrieve the HTML
                 NSURLSession.sharedSession().dataTaskWithURL(URL) {
                         (data, response, error) in

                     if let errorValue = error {
                         self.callOnFailure(errorValue)
                     }
                     else {
                         if let data = data, html = NSString(data: data, encoding: NSUTF8StringEncoding) {
                             self.resultTitle = self.parseTitle(html)
                         }

                         self.callOnSuccess()
                     }
                 }.resume()

                 return true
             }

             return false
         }

         // Parse the title from a webpage HTML
         private func parseTitle(html: NSString) -> String {
             let range1 = html.rangeOfString("<title>")
             let range2 = html.rangeOfString("</title>")

             let start = range1.location + range1.length

             return html.substringWithRange(NSMakeRange(start, range2.location - start))
         }

     }
    ```


7.  Screenletクラスの `createInteractor` メソッドを更新して、各アクションに対して正しいInteractorを返すようにします。 たとえば、Add Bookmark ScreenletのScreenletクラス（`AddBookmarkScreenlet`）の `createInteractor` メソッドには、Add BookmarkまたはGet Titleアクションが呼び出されたときに `AddBookmarkInteractor` または `GetWebTitleInteractor` インスタンスをそれぞれ返す `switch` ステートメントが含まれています。 `createAddBookmarkInteractor（）` および `createGetTitleInteractor（）` メソッドがこれらのインスタンスを作成することに注意してください。 Interactorインスタンスを個別のメソッドで作成する必要はありませんが、そうすることでコードが簡潔になります。 
   
   

    ``` 
     ... 
     override public func createInteractor(name name: String, sender: AnyObject?) 
         -> Interactor? {
             switch name {
             case AddBookmarkScreenlet.AddBookmarkAction:
                 return createAddBookmarkInteractor()
             case AddBookmarkScreenlet.GetTitleAction:
                 return createGetTitleInteractor()
             default:
                 return nil
             }
         }

     private func createAddBookmarkInteractor() -> Interactor {
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

     private func createGetTitleInteractor() -> Interactor {
         let interactor = GetWebTitleInteractor(screenlet: self, url: viewModel.URL!)

         // Called when the Interactor finishes succesfully
         interactor.onSuccess = {
             let title = interactor.resultTitle
             self.viewModel.title = title
         }

         // Called when the Interactor finishes with an error
         interactor.onFailure = { _ in
             print("An error occurred retrieving the title")
         }

         return interactor
     }
     ...
    ```


すばらしいです\！ これで、スクリーンレットで複数のアクションをサポートする方法がわかりました。 次のセクションでは、プログラムでアクションをトリガーする方法を示します。



## 関連トピック

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[スクリーンレットでコネクタを作成して使用する](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)
