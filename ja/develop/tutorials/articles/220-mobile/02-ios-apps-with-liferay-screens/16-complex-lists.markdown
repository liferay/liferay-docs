---
header-id: creating-complex-lists-in-your-list-screenlet
---

# リストスクリーンレットで複雑なリストを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ほとんどのリストScreenletsのテーマは、シンプルなリストを表示するために [iOSの `UITableView`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableView_Class/) を使用します。 `UITableView` はこれに最適ですが、グリッドやスタックなどの複雑なリストにはあまり適していません。 複雑なリストを作成するには、リストScreenletのテーマで [iOSの `UICollectionView`](https://developer.apple.com/reference/uikit/uicollectionview) を使用する必要があります。

使用して、こうしたテーマを作成する方法これは、チュートリアルを示します [サンプルブックマーク一覧Screenletのコレクションのテーマ](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/CollectionView) 例として。 最初に、リストのセルを作成します。

## セルを作成する

リストScreenletのセルの作成に使用したのと同じ手順</a> シーケンスを

、リストのセルを作成します。 ただし、これらの手順を実行する方法は少し異なることに注意してください。</p> 

1.  新しいXIBファイルでセルのUIを定義します。 このセルは `UICollectionView`を使用するテーマの一部であるため、好きなように整形できます。 たとえば、ブックマークリストスクリーンレットのコレクションテーマのセルには、 `BookmarkCell_default-collection.xib` ファイルがあります。 これは、ブックマークのURLとURLの最初の文字を表示する単純な正方形です。
   
   ![図1：ブックマークリストスクリーンレットのカスタムビューのセルのXIBファイル。](../../../images/screens-ios-collectionview-cell.png)

2.  `UICollectionViewCell`拡張して、XIBファイルのクラスを作成します。 UIコンポーネントに必要な数のアウトレットとアクションを作成し、セルのUIが機能するために必要なロジックを記述します。 たとえば、 `BookmarkCell_default_collection` は、ブックマークリストスクリーンレットのカスタムテーマのXIBファイルのクラスです。 このクラスは `UICollectionViewCell` を拡張し、URL（`urlLabel`）およびURLの最初の文字（`centerLabel`）のアウトレットを含みます。 `ブックマーク` 変数の `didSet` オブザーバーは、ブックマークの名前とURLをそれぞれのラベルに設定します。 また、オーバーライドされた `prepareForReuse` メソッドは、ラベルを再利用のためにリセットすることに注意してください。
   
        import UIKit
        import LiferayScreens
       
        public class BookmarkCell_default_collection: UICollectionViewCell {
       
            //MARK: Outlets
       
            @IBOutlet weak var centerLabel: UILabel?
            @IBOutlet weak var urlLabel: UILabel?
       
            //MARK: Public properties
       
            public var bookmark: Bookmark? {
                didSet {
                    if let bookmark = bookmark, url = NSURL(string: bookmark.url),
                        firstLetter = url.host?.remove("www.").characters.first {
       
                            self.centerLabel?.text = String(firstLetter).uppercaseString
                            self.urlLabel?.text = bookmark.url.remove("http://").remove("https://").remove("www.")
                    }
                }
            }
       
            //MARK: UICollectionViewCell
       
            override public func prepareForReuse() {
                super.prepareForReuse()
       
                centerLabel?.text = "..."
                urlLabel?.text = "..."
            }
        }
       

セルが存在するようになったので、テーマの残りを作成できます。



## テーマのXIBおよびビュークラスの作成

あなたがあなたのテーマの残りの部分を作成します [同じ一連のステップ](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ios-list-screenlets-theme) 任意のリストScreenletのテーマを作成するために使用します。 セルの作成と同様に、テーマは `UITableView`ではなく `UICollectionView` 使用するため、これらのステップの実行方法は少し異なります。

最初に、新しいXIBファイルでテーマのUIを定義します。 このファイルに `UITableView` 代わりに `UICollectionView` を追加します。 たとえば、ブックマークリストスクリーンレットのカスタムテーマの [`BookmarkListView_default-collection.xib` ファイル](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/CollectionView/BookmarkListView_default-collection.xib) には、コレクションビューが含まれています。

次に、Viewクラスを作成します。 このクラスは、 `BaseListTableView`を拡張する代わりに、Screensの [`BaseListCollectionView` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/CollectionView/BaseListCollectionView.swift)拡張する必要があります。 `BaseListCollectionView` クラスは、スクリーンレットで `UICollectionView` を使用するために必要なコードのほとんどを実装します。 拡張することで、スクリーンレットに固有のコードに集中できます。 Viewクラスには、セルIDとして機能する文字列定数も含める必要があります。 セルを登録するときにこの定数を使用します。 たとえば、ブックマークリストスクリーンレットのコレクションテーマ（`BookmarkListView_default_collection`）のViewクラスは `BaseListCollectionView` を拡張し、文字列定数 `BookmarkCellId`を定義します。

    public class BookmarkListView_default_collection : BaseListCollectionView {
    
        let BookmarkCellId = "bookmarkCell"
        …
    

Interface Builderで、この新しいクラスをXIBのカスタムクラスとして設定します。

次に、 `doRegisterCellNibs` メソッドをオーバーライドして、前のセクションで作成したセルを登録します。 このメソッドでは、セルに `UINib` インスタンスを作成し、 `BaseListCollectionView`から継承した `UICollectionView` インスタンス（`collectionView`）に登録します。 nibファイルを登録するときは、先ほど作成した文字列定数を `forCellReuseIdentifier`として使用する必要があります。 たとえば、 `BookmarkListView_default_collection``doRegisterCellNibs` メソッドは次のとおりです。

    public override func doRegisterCellNibs() {
        let cellNib = UINib(nibName: "BookmarkCell_default-collection", bundle: nil)
        collectionView?.registerNib(cellNib, forCellWithReuseIdentifier: BookmarkCellId)
    }
    

また、Viewクラスで、 `doGetCellId` メソッドをオーバーライドして、セルを登録したIDを返します。 たとえば、 `BookmarkListView_default_collection` の `doGetCellId` メソッドは、文字列定数 `BookmarkCellId`返します。

    public override func doGetCellId(indexPath indexPath: NSIndexPath, object: AnyObject?) -> String {
        return BookmarkCellId
    }
    

次に、 `doFillLoadedCell` メソッドをオーバーライドして、セルにデータを入力します。 このメソッドの `オブジェクト` 引数には、データが `AnyObject`として含まれています。 これを目的の型にキャストしてから、適切なセル変数に設定する必要があります。 たとえば、 `BookmarkListView_default_collection` の `doFillLoadedCell` メソッドは、 `object` 引数を `Bookmark` キャストし、それをセルの `bookmark` 変数に設定します。

    public override func doFillLoadedCell(
            indexPath indexPath: NSIndexPath,
            cell: UICollectionViewCell,
            object: AnyObject) {
    
        if let cell = cell as? BookmarkCell_default_collection, bookmark = object as? Bookmark {
            cell.bookmark = bookmark
        }
    }
    

次に、レイアウトを作成します。



## レイアウトを作成する

レイアウトオブジェクトは、 `UICollectionView`重要な部分です。 このオブジェクトは、UI要素の位置、サイズなどを制御します。 レイアウトオブジェクトをカスタマイズするには、Viewクラスの `doCreateLayout` メソッドをオーバーライドします。 たとえば、ブックマークリストスクリーンレットのViewクラス（`BookmarkListView_default_collection`）の `doCreateLayout` メソッドは、レイアウトオブジェクトに対して `UICollectionViewFlowLayout` を返します。 これは、アイテムのサイズ、アイテム間の間隔、スクロール方向などを簡単にカスタマイズできる基本的なレイアウトです。

    public override func doCreateLayout() -> UICollectionViewLayout {
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: 110, height: 110)
        layout.minimumLineSpacing = 10
        layout.minimumInteritemSpacing = 10
    
        return layout
    }
    

すばらしいです\！ 完了です！ これで、他の</a>と同じ方法で新しいテーマ 使用できます。</p> 

あなたはLiferayの画面にそれを貢献するために、あなたのテーマをパッケージ化するプロジェクトまたはCocoaPodsでそれを配布する場合、参照 [テーマパッケージにチュートリアルを](/docs/7-1/tutorials/-/knowledge_base/t/packaging-ios-themes)。



## 関連トピック

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[リストスクリーンレットの並べ替え](/docs/7-1/tutorials/-/knowledge_base/t/sorting-your-list-screenlet)

[リストスクリーンレットでのカスタムセルの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-custom-cells-with-list-screenlets)

[iOSのベストプラクティス](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices)
