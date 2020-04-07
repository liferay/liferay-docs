---
header-id: using-custom-cells-with-list-screenlets
---

# リストスクリーンレットでのカスタムセルの使用

[TOC levels=1-4]

Liferayスクリーンに付属するものを含むほとんどのリストスクリーンレットでは、デフォルトテーマは [iOSの `UITableView`](https://developer.apple.com/reference/uikit/uitableview) のデフォルトセルを使用してリストを表示します。 [リストスクリーンレット作成チュートリアルのテーマ作成手順](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ios-list-screenlets-theme) では、これらのセルを使用するよう指示されています。 ただし、カスタムセルを使用して、リストをニーズに合わせることができます。 これを行うには、必要があります [拡張されたテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-ios-extended-theme) テーマから使用している `のUITableView`のデフォルトのセルを。 これは通常、リストスクリーンレットのデフォルトテーマを拡張することを意味します。 このチュートリアルでは、リストScreenletのカスタムセルを含む拡張テーマを作成する方法を示します。 例として、このチュートリアルでは、サンプルのブックマークリストスクリーンレットのカスタムテーマのコードを使用します。 このテーマの完成したコード [は、GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/TableView) でいつでも参照できます。

カスタムセルの作成に加えて、このチュートリアルは、拡張テーマ</a>を作成するための

テーマ作成チュートリアルと同じ基本手順に従うことに注意してください。 たとえば、テーマを作成する場所を決定し、テーマのXIBおよびViewクラスを作成する必要があります。</p> 

最初に、テーマのカスタムセルを作成します。



## カスタムセルの作成

あなたいったん [、あなたのテーマを作成する場所を決める](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes#determining-your-themes-location)、あなたが始めることができます。 最初に、カスタムセルのXIBファイルとそのコンパニオンクラスを作成します。 応じてそれらに名前を [チュートリアルのベストプラクティスで命名規則](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices#naming-conventions)。 XIBでセルのUIを定義したら、コンパニオンクラスで必要な数のアウトレットとアクションを作成します。 また、Interface BuilderでこのクラスをXIBのカスタムクラスとして割り当ててください。 行ごとに異なるレイアウトを使用する場合は、それぞれにXIBファイルとコンパニオンクラスを作成する必要があることに注意してください。

たとえば、次のスクリーンショットは、ブックマークリストスクリーンレットのカスタムセルのXIBファイル `BookmarkCell_default-custom.xib` を示しています。 このセルにはブックマークの名前とURLが表示される必要があるため、2つのラベルが含まれています。

![図1：ブックマークリストスクリーンレットのカスタムセルのXIBファイル。](../../../images/screens-ios-xcode-custom-cell.png)

このXIBのカスタムクラス `BookmarkCell_default_custom`には、各ラベルのアウトレットが含まれています。 `bookmark` 変数には、ブックマークの名前とURLをそれぞれのラベルに設定する `didSet` オブザーバーも含まれています。

    import UIKit
    
    class BookmarkCell_default_custom: UITableViewCell {
    
        @IBOutlet weak var nameLabel: UILabel?
        @IBOutlet weak var urlLabel: UILabel?
    
        var bookmark: Bookmark? {
            didSet {
                nameLabel?.text = bookmark?.name
                urlLabel?.text = bookmark?.url
            }
        }
    
    }
    

すばらしいです\！ これで、カスタムセルができました。 次に、残りのテーマを作成します。



## テーマのXIBおよびビュークラスの作成

これで、テーマのXIBファイルとViewクラスを作成する準備が整いました。 親テーマのXIBをコピーし、必要な変更を加えてXIBを作成します。 ファイル名とカスタムクラス名以外に変更を加える必要がない場合があります。 たとえば、カスタムセルは、ブックマークリストスクリーンレットのカスタムテーマとデフォルトテーマの唯一の違いです。 したがって、これらのテーマのXIBファイル（`BookmarkListView_default-custom.xib` および `BookmarkListView_default.xib`）は、名前とカスタムクラス以外は同一です。 UIコンポーネントのサイズと位置は同じです。

次に、親テーマのViewクラスを拡張して、Viewクラスを作成します。 セルIDとして機能する文字列定数も追加する必要があります。 すぐに、この定数を使用してカスタムセルを登録します。 たとえば、ブックマークリストスクリーンレットのカスタムテーマ（`BookmarkListView_default_custom`）のViewクラスは、デフォルトテーマのViewクラス（`BookmarkListView_default`）を拡張し、文字列定数 `BookmarkCellId`を定義します。

    public class BookmarkListView_default_custom: BookmarkListView_default {
    
        let BookmarkCellId = "bookmarkCell"
        …
    

次に、 `doRegisterCellNibs` メソッドをオーバーライドして、カスタムセルを登録します。 この方法では、作成 `UINib` あなたのセルのインスタンスをした後に登録 `のUITableView` インスタンス（`のtableView`から継承） [ `BaseListTableView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift)。 nibファイルを登録するときは、先ほど作成した文字列定数を `forCellReuseIdentifier`として使用する必要があります。 たとえば、 `BookmarkListView_default-custom`の `doRegisterCellNibs` メソッドは次のとおりです。



``` 
  public override func doRegisterCellNibs() {
      let nib = UINib(nibName: "BookmarkCell_default-custom", bundle: NSBundle.mainBundle())

      tableView?.registerNib(nib, forCellReuseIdentifier: BookmarkCellId)
}
```


また、Viewクラスで、 `doGetCellId` メソッドをオーバーライドして、各行のセルIDを返します。 このメソッドで行う必要があるのは、前に作成した文字列定数を返すことだけです。 たとえば、 `BookmarkListView_default-custom` の `doGetCellId` メソッドは、 `BookmarkCellId` 定数を返します。

    override public func doGetCellId(row row: Int, object: AnyObject?) -> String {
        return BookmarkCellId
    }
    

`doFillLoadedCell` メソッドをオーバーライドして、セルにデータを入力します。 このメソッドは、進行中のセルに対しては呼び出されないことに注意してください。データを表示するセルに対してのみ呼び出されます。 また、このメソッドの `object` 引数には、データが `AnyObject`として含まれていることに注意してください。 これを目的の型にキャストしてから、適切なセル変数に設定する必要があります。 たとえば、 `BookmarkListView_default-custom` の `doFillLoadedCell` メソッドは、 `object` 引数を `Bookmark` キャストし、それをセルの `bookmark` 変数に設定します。

    override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object:AnyObject) {
        if let bookmarkCell = cell as? BookmarkCell_default_custom, bookmark = object as? Bookmark {
            bookmarkCell.bookmark = bookmark
        }
    }
    

典型的なiOS [`UITableViewDelegate` プロトコル](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDelegate_Protocol/) および [`UITableViewDataSource` プロトコル](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDataSource_Protocol/) メソッドもViewクラスで使用できます。 必要な場合は、それらのいずれかをオーバーライドできます（最初にチェックして、それらが既にオーバーライドされていないことを確認します）。 たとえば、 `BookmarkListView_default-custom` は次のメソッドを実装して、行ごとに異なるセルの高さを使用します。

    public func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        return 80
    }
    

終了したら、ViewクラスをXIBファイルのカスタムクラスとして設定します。

驚くばかり\！ 完了です！ これで、リストスクリーンレットで使用する独自のカスタムセルを実装する方法がわかりました。



## 関連トピック

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[リストスクリーンレットの並べ替え](/docs/7-1/tutorials/-/knowledge_base/t/sorting-your-list-screenlet)

[リストスクリーンレットで複雑なリストを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-complex-lists-in-your-list-screenlet)

[iOSのベストプラクティス](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices)
