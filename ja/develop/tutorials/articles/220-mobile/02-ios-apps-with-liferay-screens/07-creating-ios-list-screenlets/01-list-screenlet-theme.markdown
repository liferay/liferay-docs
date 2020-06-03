---
header-id: creating-the-ios-list-screenlets-theme
---

# iOSリストスクリーンレットのテーマの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

各Screenletには、UIとして機能するテーマが必要であることを思い出してください。 テーマには、UIのコンポーネントとレイアウトを定義するXIBファイルが必要です。 リストスクリーンレットにはエンティティのリストが表示されるため、XIBファイルにはテーブルビューが含まれている必要があります。 次の手順を使用して、テーマのXIBファイルを作成します。

1.  Xcodeで、新しいXIBファイルを作成し、それに応じて名前を付ける [これらの命名規則](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices#naming-conventions)。 たとえば、ブックマークリストスクリーンレットのデフォルトテーマのXIBは `BookmarkListView_default.xib`です。

2.  Interface Builderで、オブジェクトライブラリからキャンバスにビューをドラッグアンドドロップします。 次に、ビューにテーブルビューを追加します。

3.  テーブルビューのサイズを変更してビュー全体を表示し、テーブルビューがこのサイズを動的に維持するために必要な制約を設定します。 これにより、iOSデバイスのサイズや向きに関係なく、リストがスクリーンレットのUIに表示されます。

たとえば、 [ブックマークリストスクリーンレットのXIBファイル](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/Default/BookmarkListView_default.xib) は、親ビュー内で `UITableView` 使用してブックマークのリストを表示します。

次に、テーマのViewクラスを作成します。 すべてのテーマには、その動作を制御するViewクラスが必要です。 XIBファイルを使用しているので `のUITableView` ゲストブックのリストを表示するには、あなたのViewクラスを拡張する必要があります [ `BaseListTableView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift)。 Liferay Screensは、リストScreenletsのビュークラスの基本クラスとして機能するこのクラスを提供します。 `BaseListTableView` は必要な機能のほとんどを提供するため、拡張すると、スクリーンレットに固有のViewクラスの部分に集中できます。 Viewクラスを使用するには、XIBファイルも構成する必要があります。

以下の手順に従って、スクリーンレットのViewクラスを作成し、それを使用するようにXIBファイルを構成します。

1.  テーマのViewクラスを作成し、これらの命名規則 [に従って命名します](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices#naming-conventions)。 XIBは `UITableView`使用するため、Viewクラスは `BaseListTableView`拡張する必要があります。 たとえば、これはブックマークリストスクリーンレットのビュークラス宣言です。
   
        public class BookmarkListView_default: BaseListTableView {...

2.  次に、テーブルセルのコンテンツを埋めるViewクラスメソッドをオーバーライドする必要があります。 これには、セルのタイプに応じて2つの方法があります。

      - **通常のセル：** エンティティを表示するセル。 これらの細胞は、典型的には、使用 `UILabel`、 `UIImage`エンティティを示すために、または別のUIコンポーネント。 `doFillLoadedCell` メソッドをオーバーライドして、これらのセルを塗りつぶします。 たとえば、ブックマークリストスクリーンレットのViewクラスは、 `doFillLoadedCell` をオーバーライドして、各セルの `textLabel` をブックマークの名前に設定します。

        ``` 
          override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, 
              object: AnyObject) {

                  let bookmark = object as! Bookmark

                  cell.textLabel?.text = bookmark.name
          }
        ```

      - **進行状況セル：** リストの次のページのアイテムをロードしていることを示すリストの下部のセル。 `doFillInProgressCell` メソッドをオーバーライドして、このセルを塗りつぶします。 たとえば、ブックマークリストスクリーンレットのViewクラスはこのメソッドをオーバーライドして、セルの `textLabel` を文字列 `"Loading ..."`ます。

        ``` 
          override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
              cell.textLabel?.text = "Loading..."
          }
        ```

3.  Interface BuilderでテーマのXIBに戻り、Viewクラスを親Viewのカスタムクラスとして設定します。 たとえば、ブックマークリストスクリーンレットに対してこれを行っていた場合、テーブルビューの親ビューを選択し、IDインスペクターをクリックして、カスタムクラスとして `BookmarkListView_default` を入力し` 。</p></li>
<li><p spaces-before="0"> テーマのXIBをInterface Builderで開いたまま、親ビューの <code>tableView` アウトレットをTableビューに設定します。 これを行うには、親ビューを選択し、接続インスペクターをクリックします。 [アウトレット]セクションで、 `tableView`の円形アイコン（マウスオーバーでプラスアイコンに変わります）からXIBのテーブルビューにドラッグアンドドロップします。 接続インスペクターに新しいアウトレットが表示されます。

それでおしまい\！ テーマが完成したので、コネクタを作成できます。
